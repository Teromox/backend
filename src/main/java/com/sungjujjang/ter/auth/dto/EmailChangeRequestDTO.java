package com.sungjujjang.ter.auth.dto;

import jakarta.validation.constraints.Email;

public record EmailChangeRequestDTO(
        @Email
        String newEmail
) {
}
