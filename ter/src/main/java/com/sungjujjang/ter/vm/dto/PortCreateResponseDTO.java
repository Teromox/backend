package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record PortCreateResponseDTO(
    Boolean status,
    Integer outPort
) {
}
