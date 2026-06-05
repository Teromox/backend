package com.sungjujjang.ter.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepo extends JpaRepository<Member, Integer> {
    boolean existsByid(String id);
    Optional<Member> findByid(String id);
}