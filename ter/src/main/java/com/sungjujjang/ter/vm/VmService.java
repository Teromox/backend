package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.auth.MemberRepo;
import com.sungjujjang.ter.global.TaskStorage;
import com.sungjujjang.ter.global.error.exception.NoCreditErr;
import com.sungjujjang.ter.global.error.exception.NoExistVmErr;
import com.sungjujjang.ter.global.error.exception.NoOwnerErr;
import com.sungjujjang.ter.global.error.exception.NotExistIdErr;
import com.sungjujjang.ter.vm.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sound.sampled.Port;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VmService {
    private final VmRepo vmRepo;
    private final MemberRepo memberRepo;
    private final PortsRepo portsRepo;

    @Value("${proxmox.api.key}")
    private String proxmoxApiKey;

    @Value("${proxmox.api.url}")
    private String proxmoxApiUrl;

    @Value("${ext.ip}")
    private String ip;

    public VmCreateResponseDTO CreateVm(VmCreateRequestDTO requestDTO, Member member) {
        int updatedCredit = memberRepo.decrementCreditIfAvailable(member.getId());
        if (updatedCredit == 0) {
            throw NoCreditErr.EXCEPTION;
        }

        try {
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
        } catch (RuntimeException e) {
            memberRepo.incrementCredit(member.getId());
            throw e;
        }
    }

    @Transactional
    public Boolean DeleteVmBy(Vm vm) {
        WebClient.create(proxmoxApiUrl)
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/vm")
                        .queryParam("vmid", vm.getId())
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        List<Ports> ports = portsRepo.findByVm(vm);
        portsRepo.deleteAll(ports);
        vmRepo.delete(vm);
        return true;
    }

    @Transactional
    public VmDeleteResponseDTO DeleteVm(VmDeleteRequestDTO requestDTO, Member member) {
        Vm vm = vmRepo.findById(requestDTO.id())
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);
        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }

        Boolean status = DeleteVmBy(vm);

        // 400~ 에러 발생하면 500으로반환해줌 수정안해도댐
//        WebClient.create(proxmoxApiUrl)
//                .delete()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/api/vm")
//                        .queryParam("vmid", requestDTO.id())
//                        .build()
//                )
//                .header("api-key", proxmoxApiKey)
//                .retrieve()
//                .bodyToMono(Void.class)
//                .block();
        member.setCredit(member.getCredit()+1);
//        List<Ports> ports = portsRepo.findByVm(vm);
//        portsRepo.deleteAll(ports);
//        vmRepo.delete(vm);
        return VmDeleteResponseDTO.builder()
                .status(status)
                .build();
    }

    @Transactional
    public VmCreateResponseDTO ResetVm(VmDeleteRequestDTO requestDTO, Member member) {
        Vm vm = vmRepo.findById(requestDTO.id())
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);
        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }
        VmResetApiDTO responseDTO = WebClient.create(proxmoxApiUrl)
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/vm")
                        .queryParam("username", vm.getUsername())
                        .queryParam("vmid", requestDTO.id())
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .retrieve()
                .bodyToMono(VmResetApiDTO.class)
                .block();
        return VmCreateResponseDTO.builder()
                .ssh_port(vm.getSsh_port())
                .ip(vm.getIp())
                .private_key(responseDTO.private_key())
                .vmid(responseDTO.vmid())
                .password(responseDTO.password())
                .build();
    }

    public VmResponseDTO StatusVm(String vmId, Member member) {
        Vm vm = vmRepo.findById(vmId)
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);
        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }
        VmStatusResponse response = WebClient.create(proxmoxApiUrl)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/vm/status")
                        .queryParam("vmid", vmId)
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .retrieve()
                .bodyToMono(VmStatusResponse.class)
                .block();
        return VmResponseDTO.builder()
                .cpu(response.data().cpu())
                .mem(response.data().mem())
                .maxmem(response.data().maxmem())
                .uptime(response.data().uptime())
                .innerIp(vm.getIp())
                .outIp(ip)
                .sshPort(vm.getSsh_port())
                .name(vm.getName())
                .username(vm.getUsername())
                .status(response.data().status())
                .build();
    }

    public VmRunResponseDTO StartVm(String vmId, Member member) {
        Vm vm = vmRepo.findById(vmId)
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);
        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }
        WebClient.create(proxmoxApiUrl)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/vm/start")
                        .queryParam("vmid", vmId)
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return VmRunResponseDTO.builder()
                .status(Boolean.TRUE)
                .build();
    }

    public VmRunResponseDTO StopVm(String vmId, Member member) {
        Vm vm = vmRepo.findById(vmId)
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);
        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }
        WebClient.create(proxmoxApiUrl)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/vm/stop")
                        .queryParam("vmid", vmId)
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return VmRunResponseDTO.builder()
                .status(Boolean.TRUE)
                .build();
    }
}

