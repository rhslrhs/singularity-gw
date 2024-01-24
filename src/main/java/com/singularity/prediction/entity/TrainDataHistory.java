package com.singularity.prediction.entity;

import com.singularity.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_train_data_history")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TrainDataHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("SEQ")
    private Long seq;

    @Column(nullable = false, updatable = false)
    @Comment("모델 명")
    private String modelName;

    @Column(nullable = false, updatable = false)
    @Comment("업로드 시간")
    private LocalDateTime uploadDateTime;

    @Column()
    @Comment("입력 레이블")
    private String inputLabel;

    @Column(nullable = false, columnDefinition = "longtext", updatable = false)
    @Comment("데이터(Base64 String)")
    private String dataBase64;

    @Column(nullable = false, columnDefinition = "longtext", updatable = false)
    @Comment("데이터(실수 array)")
    private String dataFloat32;

    @Column(nullable = false)
    @Comment("재학습 횟수")
    private int trainCnt;

    @Column(columnDefinition = "longtext", updatable = false)
    @Comment("예측 결과")
    private String dataPrediction;

    @Column
    @Comment("마지막 학습 시간")
    private LocalDateTime lastTrainDateTime;

    @Builder
    public TrainDataHistory(String modelName, String dataBase64, String dataFloat32) {
        this.modelName = modelName;
        this.dataBase64 = dataBase64;
        this.dataFloat32 = dataFloat32;
        this.uploadDateTime = LocalDateTime.now();
        this.trainCnt = 0;
    }
    public void updateDataPrediction(String dataPrediction) {
        this.dataPrediction = dataPrediction;
    }
    public void updateInputLabel(String inputLabel) {
        this.inputLabel = inputLabel;
    }
}
