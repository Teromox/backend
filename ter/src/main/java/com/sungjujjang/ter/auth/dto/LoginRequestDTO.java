package com.sungjujjang.ter.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
public record LoginRequestDTO(
    String id,
    String password
) {
}
