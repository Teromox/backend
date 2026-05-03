package com.sungjujjang.ter.auth;

import com.sungjujjang.ter.auth.dto.*;
import com.sungjujjang.ter.global.JWTSetting;
import com.sungjujjang.ter.global.PasswordSetting;
import com.sungjujjang.ter.global.error.exception.DuplicateIdErr;
import com.sungjujjang.ter.global.error.exception.NotExistIdErr;
import com.sungjujjang.ter.global.error.exception.NotMatchPasswordErr;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepo memberRepo;
    private final PasswordSetting passwordSetting;
    private final JWTSetting jwtSetting;

    Long expTime = 1000 * 60 * 60 * 24 * 7L;

    @Transactional
    public AuthResponseDTO registerMember(RegisterRequestDTO requestDTO) {
        if (memberRepo.existsByid(requestDTO.id())) {
            throw DuplicateIdErr.EXCEPTION;
        }
        Member member = Member.builder()
                .id(requestDTO.id())
                .email(requestDTO.email())
                .credit(0)
                .password(passwordSetting.encode(requestDTO.password()))
                .build();
        memberRepo.save(member);

        String jwtToken = jwtSetting.createToken(member.getId(), expTime);

        return AuthResponseDTO.builder()
                .status(Boolean.TRUE)
                .jwt(jwtToken)
                .code(200)
                .object(RegiSerDTO.from(member))
                .build();
    }

    public AuthResponseDTO loginMember(LoginRequestDTO requestDTO) {
        Member member = memberRepo.findByid(requestDTO.id())
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        if (!passwordSetting.check(requestDTO.password(), member.getPassword())) {
            throw NotMatchPasswordErr.EXCEPTION;
        }

        String jwtToken = jwtSetting.createToken(requestDTO.id(), expTime);

        return AuthResponseDTO.builder()
                .status(Boolean.TRUE)
                .jwt(jwtToken)
                .code(200)
                .object(LoginSerDTO.from(member))
                .build();
    }

    public MeResponseDTO getMe(String UserId) {
        Member member = memberRepo.findByid(UserId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return MeResponseDTO.builder()
                .status(Boolean.TRUE)
                .id(member.getId())
                .email(member.getEmail())
                .credit(member.getCredit())
                .build();
    }
}
