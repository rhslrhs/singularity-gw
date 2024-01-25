package com.singularity.prediction.dto;

import com.singularity.base.dto.BaseReqDto;
import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Getter
@ToString
public class NumberImageDeleteReqDto extends BaseReqDto {
    private final Long seq;
}