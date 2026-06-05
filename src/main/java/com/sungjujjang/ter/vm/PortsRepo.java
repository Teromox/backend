package com.sungjujjang.ter.vm;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;

public interface PortsRepo extends JpaRepository<Ports, Integer> {
    boolean existsByOutPort(Integer OutPort);
    Long countByVm(Vm vm);
    Optional<Ports> findByOutPortAndVm(Integer Outport, Vm vm);
    List<Ports> findByVm(Vm vm);
}
