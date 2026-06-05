package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record PortDeleteApiDTO(
        Integer ext_port
) {
}
