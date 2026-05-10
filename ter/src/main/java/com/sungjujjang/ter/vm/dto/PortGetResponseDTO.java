package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PortGetResponseDTO(
    List<PortDTO> ports
) {
}
