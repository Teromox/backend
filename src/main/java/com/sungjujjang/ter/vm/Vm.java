package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Vm {
    @Id
    private String id;

    private String name;

    private String username;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member owner;

    private Integer ssh_port;

    @CreatedDate
    private LocalDateTime createdAt;

    private String ip;
}