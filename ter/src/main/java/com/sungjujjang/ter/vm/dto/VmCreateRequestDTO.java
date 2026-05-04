package com.sungjujjang.ter.vm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record VmCreateRequestDTO(
        String name,
        @NotBlank(message = "사용자 이름은 필수입니다.")
        @Size(min = 1, max = 10, message = "사용자 이름은 최대 10글자까지 가능합니다.")
        @Pattern(
                regexp = "^[a-zA-Z0-9]*$",
                message = "사용자 이름은 영어와 숫자만 입력 가능합니다."
        )
        String userName
) {
}
