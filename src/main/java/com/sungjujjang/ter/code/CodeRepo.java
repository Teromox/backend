package com.sungjujjang.ter.code;

import com.sungjujjang.ter.vm.Ports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepo extends JpaRepository<Code, String> {
    Optional<Code> findBycode(String code);
}
