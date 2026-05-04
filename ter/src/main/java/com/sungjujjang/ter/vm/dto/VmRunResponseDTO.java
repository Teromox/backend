package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmRunResponseDTO(
        Boolean status
) {
}
