package com.sungjujjang.ter.auth.dto;

import lombok.Builder;

@Builder
public record ErrorRsp(
        Integer code,
        String context,
        String message
) {

}
