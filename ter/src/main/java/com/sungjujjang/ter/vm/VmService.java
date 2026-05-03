package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.auth.MemberRepo;
import com.sungjujjang.ter.global.TaskStorage;
import com.sungjujjang.ter.global.error.exception.NoCreditErr;
import com.sungjujjang.ter.vm.dto.VmCreateRequestDTO;
import com.sungjujjang.ter.vm.dto.VmCreateResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class VmService {
    private final VmRepo vmRepo;
    private final MemberRepo memberRepo;

    @Value("${proxmox.api.key}")
    private String proxmoxApiKey;

    @Value("${proxmox.api.url}")
    private String proxmoxApiUrl;

    @Transactional
    public VmCreateResponseDTO CreateVm(VmCreateRequestDTO requestDTO, Member member) {
        Integer credit = member.getCredit();
        if (0 > credit-1) {
            throw NoCreditErr.EXCEPTION;
        }
        member.setCredit(credit-1);
        memberRepo.save(member);

        VmCreateResponseDTO responseDTO = WebClient.create(proxmoxApiUrl)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/vm")
                        .queryParam("username", requestDTO.userName())
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .retrieve()
                .bodyToMono(VmCreateResponseDTO.class)
                .block();

        Vm vm = Vm.builder()
                .id(responseDTO.vmid())
                .ssh_port(responseDTO.ssh_port())
                .ip(responseDTO.ip())
                .owner(member)
                .username(requestDTO.userName())
                .name(requestDTO.name())
                .build();
        vmRepo.save(vm);

        return responseDTO;
    }
}

