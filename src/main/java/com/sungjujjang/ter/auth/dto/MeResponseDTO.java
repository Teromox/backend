package com.sungjujjang.ter.auth.dto;

import com.sungjujjang.ter.vm.dto.VmDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record MeResponseDTO(
        Boolean status,
        String id,
        String email,
        Integer credit,
        List<VmDTO> vm
) {
}
