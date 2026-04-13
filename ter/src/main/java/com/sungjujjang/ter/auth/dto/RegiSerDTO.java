package com.sungjujjang.ter.auth.dto;

import com.sungjujjang.ter.auth.Member;
import lombok.Builder;

@Builder
public record RegiSerDTO(
        String id,
        String email,
        Integer credit
) {
    public static RegiSerDTO from(Member member) {
        return RegiSerDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .credit(member.getCredit())
                .build();
    }
}