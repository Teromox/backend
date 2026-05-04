package com.sungjujjang.ter.vm;

import com.sungjujjang.ter.auth.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VmRepo extends JpaRepository<Vm, String> {
    List<Vm> findByOwner(Member member);
}
