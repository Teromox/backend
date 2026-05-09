package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import com.sungjujjang.ter.vm.dto.BlankPortDTO;
import com.sungjujjang.ter.vm.dto.PortCreateRequestDTO;
import com.sungjujjang.ter.vm.dto.PortCreateResponseDTO;
import com.sungjujjang.ter.vm.dto.VmCreateRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PortService {
    @Value("${proxmox.api.key}")
    private String proxmoxApiKey;

    @Value("${proxmox.api.url}")
    private String proxmoxApiUrl;

    @Value("ext.ip")
    private String ip;

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

    public PortCreateResponseDTO CreatePort(
            PortCreateRequestDTO requestDTO,
            Member member
    ) {
        return null;
    }
}
