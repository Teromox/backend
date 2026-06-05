package com.sungjujjang.ter.code;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.auth.MemberRepo;
import com.sungjujjang.ter.auth.dto.AuthResponseDTO;
import com.sungjujjang.ter.auth.dto.RegisterRequestDTO;
import com.sungjujjang.ter.global.NoAuthAnno;
import com.sungjujjang.ter.global.error.exception.NotExistCodeErr;
import com.sungjujjang.ter.global.error.exception.NotExistIdErr;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {
    private final MemberRepo memberRepo;
    private final CodeRepo codeRepo;

    @PostMapping("/")
    @Transactional
    public CodeResponseDTO addCode(
            @RequestBody CodeRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                        .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        Code code = codeRepo.findBycode(dto.code())
                        .orElseThrow(() -> NotExistCodeErr.EXCEPTION);
        Integer credit = code.getCredit();
        member.setCredit(
                member.getCredit() + credit
        );
        memberRepo.save(member);
        codeRepo.delete(code);
        return CodeResponseDTO.builder()
                .status(Boolean.TRUE)
                .credit(credit)
                .build();
    }
}
