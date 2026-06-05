package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record PortDeleteRequestDTO(
    Integer OutPort,
    String VmId
) {
}
