package com.sungjujjang.ter.auth.dto;

import com.sungjujjang.ter.auth.Member;
import lombok.Builder;

@Builder
public record LoginSerDTO(
        String id,
        String email,
        Integer credit
) {
    public static LoginSerDTO from(Member member) {
        return LoginSerDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .credit(member.getCredit())
                .build();
    }
}
