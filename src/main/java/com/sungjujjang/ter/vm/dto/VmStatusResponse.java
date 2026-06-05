package com.sungjujjang.ter.vm.dto;

import lombok.Builder;

@Builder
public record VmStatusResponse(StatusData data) {
    public record StatusData(
            Double cpu,  // per
            Long mem, // b
            Long maxmem, // b
            Long uptime, // sec
            String status
    ) {}
}