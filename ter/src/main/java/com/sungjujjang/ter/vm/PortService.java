package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.global.error.exception.MaxPortErr;
import com.sungjujjang.ter.global.error.exception.NoExistVmErr;
import com.sungjujjang.ter.global.error.exception.NoOwnerErr;
import com.sungjujjang.ter.global.error.exception.NotExistPortErr;
import com.sungjujjang.ter.vm.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sound.sampled.Port;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortService {
    private final VmRepo vmRepo;
    private final PortsRepo portsRepo;

    @Value("${proxmox.api.key}")
    private String proxmoxApiKey;

    @Value("${proxmox.api.url}")
    private String proxmoxApiUrl;

    @Value("${ext.ip}")
    private String ip;

    @Value("${max.port}")
    private Long maxPort;

    public BlankPortDTO GetBlankPort() {
        BlankPortDTO blankPortDTO = WebClient.create(proxmoxApiUrl)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/vm/blank_port")
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .retrieve()
                .bodyToMono(BlankPortDTO.class)
                .block();
        return blankPortDTO;
    }

    @Transactional
    public PortCreateResponseDTO CreatePort(
            PortCreateRequestDTO requestDTO,
            Member member
    ) {
        Vm vm = vmRepo.findById(requestDTO.VmId())
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);

        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }

        if (portsRepo.countByVm(vm) >= maxPort) {
            throw MaxPortErr.EXCEPTION;
        }

        BlankPortDTO blankPortDTO = GetBlankPort();
        PortCreateApiRequestDTO portCreateApiRequestDTO = PortCreateApiRequestDTO.builder()
                .ext_port(blankPortDTO.data())
                .ip(vm.getIp())
                .in_port(requestDTO.InPort())
                .build();

        PortCreateApiDTO portCreateApiDTO = WebClient.create(proxmoxApiUrl)
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/port")
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .bodyValue(portCreateApiRequestDTO)
                .retrieve()
                .bodyToMono(PortCreateApiDTO.class)
                .block();

        Ports port = Ports.builder()
                .outPort(blankPortDTO.data())
                .vm(vm)
                .InPort(requestDTO.InPort())
                .build();
        portsRepo.save(port);

        return PortCreateResponseDTO.builder()
                .status(Boolean.TRUE)
                .outPort(blankPortDTO.data())
                .build();
    }

    @Transactional
    public PortDeleteResponseDTO DeletePort(
            PortDeleteRequestDTO requestDTO,
            Member member
    ) {
        Vm vm = vmRepo.findById(requestDTO.VmId())
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);

        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }

        Ports port = portsRepo.findByOutPortAndVm(requestDTO.OutPort(), vm)
                .orElseThrow(() -> NotExistPortErr.EXCEPTION);

        PortDeleteApiDTO portDeleteApiDTO = PortDeleteApiDTO.builder()
                .ext_port(requestDTO.OutPort())
                .build();

        PortCreateApiDTO portCreateApiDTO = WebClient.create(proxmoxApiUrl)
                .method(HttpMethod.DELETE)
                .uri(uriBuilder -> uriBuilder
                        .path("/api/port")
                        .build()
                )
                .header("api-key", proxmoxApiKey)
                .bodyValue(portDeleteApiDTO)
                .retrieve()
                .bodyToMono(PortCreateApiDTO.class)
                .block();

        portsRepo.delete(port);

        return PortDeleteResponseDTO.builder()
                .status(true)
                .build();
    }

    public PortGetResponseDTO GetPort(
            String VmId,
            Member member
    ) {
        Vm vm = vmRepo.findById(VmId)
                .orElseThrow(() -> NoExistVmErr.EXCEPTION);

        if (vm.getOwner() != member) {
            throw NoOwnerErr.EXCEPTION;
        }

        List<PortDTO> VVS = portsRepo.findByVm(vm).stream()
                .map(
                port -> PortDTO.builder()
                        .outPort(port.getOutPort())
                        .inPort(port.getInPort())
                        .build()
                ).toList();

        return PortGetResponseDTO.builder()
                .ports(VVS)
                .build();
    }
}
