package com.singularity.prediction.dto;

import com.singularity.base.dto.BaseReqDto;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
@ToString
public class NumberImageLabelReqDto extends BaseReqDto {
    private final Long seq;
    private final String label;
}