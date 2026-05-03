package com.sungjujjang.ter.code;

import lombok.Builder;

@Builder
public record CodeRequestDTO(
        String code
) {
}
