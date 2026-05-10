package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.auth.MemberRepo;
import com.sungjujjang.ter.global.error.exception.NotExistIdErr;
import com.sungjujjang.ter.vm.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/port")
@RequiredArgsConstructor
public class PortController {
    private final MemberRepo memberRepo;
    private final PortService portService;

    @PostMapping("/")
    public PortCreateResponseDTO CreatePort(
            @RequestBody @Valid PortCreateRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return portService.CreatePort(dto, member);
    }

    @DeleteMapping("/")
    public PortDeleteResponseDTO DeletePort(
            @RequestBody @Valid PortDeleteRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return portService.DeletePort(dto, member);
    }

    @GetMapping("/")
    public PortGetResponseDTO GetPort(
            @RequestParam(name = "id") String vmId,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return portService.GetPort(vmId, member);
    }

    @GetMapping("/blank")
    public BlankPortDTO GetBlankPort() {
        return portService.GetBlankPort();
    }
}
