package com.singularity.prediction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@ToString
public class NumberImagePredictionResDto extends BasePredictionResDto {
    List<List<BigDecimal>> predictions;
    private List<ValIdxPredictionResDto> results;
    private List<ValIdxPredictionResDto> sortedResults;
    private ValIdxPredictionResDto maxValIdxPrediction;
    @Setter
    private List<String> processedImgs;
    @Setter
    private Long trainDataHistorySeq;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    @ToString
    public static class ValIdxPredictionResDto {
        private int idx;
        private BigDecimal value;
    }
}
