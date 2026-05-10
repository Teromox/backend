package com.sungjujjang.ter.auth;

import com.sungjujjang.ter.auth.dto.*;
import com.sungjujjang.ter.global.NoAuthAnno;
import com.sungjujjang.ter.global.error.exception.NotExistIdErr;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberRepo memberRepo;

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

    @PatchMapping("/email")
    public EmailChangeResponseDTO ChangeEmail(
            @RequestBody @Valid EmailChangeRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return authService.changeEmail(dto, member);
    }

    @DeleteMapping("/")
    public UserDeleteResponseDTO DeleteUser(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return authService.deleteUser(member);
    }
}