package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record PortCreateRequestDTO(
        Integer OutPort,
        String VmId,
        Integer InPort
) {}
