package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmCreateRequestDTO(
        String name,
        String userName
) {
}
