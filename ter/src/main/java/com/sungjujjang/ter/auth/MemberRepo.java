package com.sungjujjang.ter.auth;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepo extends JpaRepository<Member, Integer> {
}