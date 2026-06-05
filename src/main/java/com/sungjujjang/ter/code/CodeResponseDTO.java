package com.sungjujjang.ter.code;

import lombok.Builder;

@Builder
public record CodeResponseDTO(
    Boolean status,
    Integer credit
) {
}
