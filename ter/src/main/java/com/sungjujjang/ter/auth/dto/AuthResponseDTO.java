package com.sungjujjang.ter.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponseDTO(
        Boolean status,
        String message,
        Integer code,
        Object object
) {}