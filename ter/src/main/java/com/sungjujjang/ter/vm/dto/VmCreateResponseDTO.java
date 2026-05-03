package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmCreateResponseDTO(
        String vmid,
        String private_key,
        String password,
        String ip,
        Integer ssh_port
) {
}
