package com.sungjujjang.ter.vm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ports {
    @Id
    private Integer OutPort;

    @ManyToOne
    @JoinColumn(name="vm_id")
    private String vm;

    private Integer InPort;

    private String name;
}