package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmDTO(
        String id,
        String name,
        String username,
        Integer ssh_port,
        String ip
) {
}
