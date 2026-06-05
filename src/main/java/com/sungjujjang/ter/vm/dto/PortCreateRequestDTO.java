package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record PortCreateRequestDTO(
        String VmId,
        Integer InPort
) {}
