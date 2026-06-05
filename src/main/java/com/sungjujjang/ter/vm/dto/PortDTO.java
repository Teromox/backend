package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record PortDTO(
        Integer outPort,
        Integer inPort
) {
}
