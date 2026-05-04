package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmDeleteResponseDTO(
        boolean status
) {
}
