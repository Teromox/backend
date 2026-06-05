package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmResetApiDTO(
        String vmid,
        String private_key,
        String password
) {
}
