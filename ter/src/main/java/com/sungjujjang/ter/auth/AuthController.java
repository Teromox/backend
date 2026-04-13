package com.sungjujjang.ter.auth;

import com.sungjujjang.ter.auth.dto.AuthResponseDTO;
import com.sungjujjang.ter.auth.dto.RegisterRequestDTO;
import com.sungjujjang.ter.global.NoAuthAnno;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @NoAuthAnno
    public AuthResponseDTO reg(@RequestBody @Valid RegisterRequestDTO dto) {
        return authService.registerMember(dto);
    }
}