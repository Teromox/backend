package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.auth.MemberRepo;
import com.sungjujjang.ter.global.TaskStorage;
import com.sungjujjang.ter.global.error.exception.NotExistIdErr;
import com.sungjujjang.ter.vm.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
            @RequestBody @Valid VmCreateRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return vmService.CreateVm(dto, member);
    }

    @DeleteMapping("/")
    public VmDeleteResponseDTO deleteVm(
            @RequestBody VmDeleteRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return vmService.DeleteVm(dto, member);
    }

    @PutMapping("/")
    public VmCreateResponseDTO resetVm(
            @RequestBody VmDeleteRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return vmService.ResetVm(dto, member);
    }

    @GetMapping("/")
    public VmResponseDTO statusVm(
            @RequestParam(name = "id") String vmId,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return vmService.StatusVm(vmId, member);
    }

    @PostMapping("/run")
    public VmRunResponseDTO startVm(
            @RequestBody VmRunRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return vmService.StartVm(dto.vmid(), member);
    }

    @DeleteMapping("/run")
    public VmRunResponseDTO stopVm(
            @RequestBody VmRunRequestDTO dto,
            HttpServletRequest request
    ) {
        String userId = (String) request.getAttribute("userId");
        Member member = memberRepo.findByid(userId)
                .orElseThrow(() -> NotExistIdErr.EXCEPTION);
        return vmService.StopVm(dto.vmid(), member);
    }
}
