package com.sungjujjang.ter.auth.dto;

import lombok.Builder;

@Builder
public record RegisterRequestDTO(
        String id,
        String email,
        String password
) {}
