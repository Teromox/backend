package com.sungjujjang.ter.auth.dto;

import lombok.Builder;

@Builder
public record AuthResponseDTO(
        Boolean status,
        String jwt,
        Integer code,
        Object object
) {}