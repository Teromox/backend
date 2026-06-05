package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record PortCreateApiRequestDTO(
        Integer ext_port,
        String ip,
        Integer in_port
) {
}
