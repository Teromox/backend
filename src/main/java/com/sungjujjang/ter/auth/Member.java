package com.sungjujjang.ter.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Member {
    @Id
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    private Integer credit;
}
