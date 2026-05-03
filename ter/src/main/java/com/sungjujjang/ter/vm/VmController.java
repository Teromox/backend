package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.auth.MemberRepo;
import com.sungjujjang.ter.global.TaskStorage;
import com.sungjujjang.ter.global.error.exception.NotExistIdErr;
import com.sungjujjang.ter.vm.dto.VmCreateRequestDTO;
import com.sungjujjang.ter.vm.dto.VmCreateResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vm")
@RequiredArgsConstructor
public class VmController {
    private final TaskStorage taskStorage;
    private final VmService vmService;
    private final MemberRepo memberRepo;

    @PostMapping("/")
    public VmCreateResponseDTO createVm(
            @RequestBody VmCreateRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return vmService.CreateVm(dto, member);
    }
}
