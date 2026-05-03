package com.sungjujjang.ter.auth.dto;

import lombok.Builder;

@Builder
public record MeResponseDTO(
        Boolean status,
        String id,
        String email,
        Integer credit
) {
}
