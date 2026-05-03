package com.sungjujjang.ter.auth;

import com.sungjujjang.ter.auth.dto.AuthResponseDTO;
import com.sungjujjang.ter.auth.dto.LoginRequestDTO;
import com.sungjujjang.ter.auth.dto.MeResponseDTO;
import com.sungjujjang.ter.auth.dto.RegisterRequestDTO;
import com.sungjujjang.ter.global.NoAuthAnno;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @NoAuthAnno
    public AuthResponseDTO registerUser(@RequestBody @Valid RegisterRequestDTO dto) {
        return authService.registerMember(dto);
    }

    @PostMapping("/login")
    @NoAuthAnno
    public AuthResponseDTO loginUser(@RequestBody @Valid LoginRequestDTO dto) {
        return authService.loginMember(dto);
    }

    @GetMapping("/")
    public MeResponseDTO getMeMember(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return authService.getMe(userId);
    }
}