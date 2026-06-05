package com.sungjujjang.ter.auth.dto;

import lombok.Builder;

@Builder
public record UserDeleteResponseDTO(
        boolean status
) {
}
