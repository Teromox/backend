package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmResponseDTO(
        Double cpu,  // per
        Long mem, // b
        Long maxmem, // b
        Long uptime, // sec
        String innerIp,
        String outIp,
        Integer sshPort,
        String name,
        String username,
        String status
) {
}
