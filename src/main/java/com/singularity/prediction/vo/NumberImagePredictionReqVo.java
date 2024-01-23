package com.singularity.prediction.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class NumberImagePredictionReqVo extends BasePredictionReqVo {
    // { "instances": [[ 28 * 28 ], [ 28 * 28 ], [ 28 * 28 ], [ 28 * 28 ], ...] }
    private final List<List<BigDecimal>> instances;
}