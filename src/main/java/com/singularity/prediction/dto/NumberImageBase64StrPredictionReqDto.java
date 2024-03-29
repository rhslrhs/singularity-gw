package com.singularity.prediction.dto;

import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
@ToString
public class NumberImageBase64StrPredictionReqDto extends BasePredictionReqDto {
    private final String base64Str;
}