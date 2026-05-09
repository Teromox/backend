package com.sungjujjang.ter.vm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PortsRepo extends JpaRepository<Ports, Integer> {
    boolean existsByOutPort(Integer OutPort);
    Long countByVm(Vm vm);
}
