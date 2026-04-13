package com.sungjujjang.ter.auth;

import com.sungjujjang.ter.auth.dto.AuthResponseDTO;
import com.sungjujjang.ter.auth.dto.RegiSerDTO;
import com.sungjujjang.ter.auth.dto.RegisterRequestDTO;
import com.sungjujjang.ter.global.PasswordSetting;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepo memberRepo;
    private final PasswordSetting passwordSetting;

    @Transactional
    public AuthResponseDTO registerMember(RegisterRequestDTO requestDto) {
        Member member = Member.builder()
                .id(requestDto.id())
                .email(requestDto.email())
                .credit(0)
                .password(passwordSetting.encode(requestDto.password()))
                .build();
        assert memberRepo != null;
        memberRepo.save(member);
        
        return AuthResponseDTO.builder()
                .status(Boolean.TRUE)
                .message("success")
                .code(200)
                .object(RegiSerDTO.from(member))
                .build();
    }
}
