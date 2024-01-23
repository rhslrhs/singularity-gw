package com.singularity.prediction.service;

import com.singularity.base.utils.ImageUtils;
import com.singularity.base.utils.JsonUtils;
import com.singularity.prediction.dto.*;
import com.singularity.prediction.entity.TrainDataHistory;
import com.singularity.prediction.repo.TrainDataHistoryRepository;
import com.singularity.prediction.vo.NumberImagePredictionReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NumberImagePredictionService extends BasePredictionService {
    private final TrainDataHistoryRepository repo;

    private static final int IMAGE_MODEL_CALC_SIZE = 28;
    @Value("${tf-serv.predict.number_image.url-v1}")
    private String tfServingUrl;

    @Transactional
    public NumberImagePredictionResDto predict(NumberImageJsonStrPredictionReqDto reqDto) {
        NumberImagePredictionReqVo reqVo = reqDto.toVo(NumberImagePredictionReqVo.class);
        return predict(reqVo);
    }

    @Transactional
    public NumberImagePredictionResDto predict(NumberImageFilesPredictionReqDto reqDto) {
        List<MultipartFile> files = reqDto.getFiles();

        final List<List<BigDecimal>> instances = new ArrayList<>();
        files.forEach(f -> {
            try {
                instances.add(ImageUtils.normalization(ImageUtils.resize(f.getInputStream(), IMAGE_MODEL_CALC_SIZE, IMAGE_MODEL_CALC_SIZE)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        NumberImagePredictionReqVo reqVo = new NumberImagePredictionReqVo(instances);
        return predict(reqVo);
    }

    @Transactional
    public NumberImagePredictionResDto predict(NumberImageBase64StrPredictionReqDto reqDto) {
        List<BigDecimal> singleInstance = ImageUtils.normalization(ImageUtils.resize(reqDto.getBase64Str(), IMAGE_MODEL_CALC_SIZE, IMAGE_MODEL_CALC_SIZE));

        NumberImagePredictionReqVo reqVo = new NumberImagePredictionReqVo(Collections.singletonList(singleInstance));
        return predict(reqVo);
    }

    private NumberImagePredictionResDto predict(NumberImagePredictionReqVo reqVo) {
        List<TrainDataHistory> eList = new ArrayList<>();
        List<String> processedImgs = reqVo.getInstances().stream()
                .map(arr -> {
                    String base64Str = ImageUtils.toBase64Str(arr, IMAGE_MODEL_CALC_SIZE, IMAGE_MODEL_CALC_SIZE);
                    eList.add(TrainDataHistory.builder()
                            .modelName("numImage")
                            .dataBase64(base64Str)
                            .dataFloat32(JsonUtils.stringify(arr))
                            .build());
                    return base64Str;
                })
                .collect(Collectors.toList());

        NumberImagePredictionResDto predict = predict(tfServingUrl, reqVo, NumberImagePredictionResDto.class);
        predict.setProcessedImgs(processedImgs);

        AtomicInteger i = new AtomicInteger(0);
        predict.getPredictions().forEach(p -> {
            eList.get(i.getAndIncrement()).updateDataPrediction(JsonUtils.stringify(p));
        });

        repo.saveAll(eList);

        predict.setTrainDataHistorySeq(eList.get(0).getSeq());
        return predict;
    }

    @Transactional
    public void label(NumberImageLabelReqDto reqDto) {
        TrainDataHistory data = repo.findById(reqDto.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("몰루"));
        data.updateInputLabel(reqDto.getLabel());
        repo.save(data);
    }
}
