package com.sungjujjang.ter.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface MemberRepo extends JpaRepository<Member, String> {
    boolean existsByid(String id);
    Optional<Member> findByid(String id);

    @Modifying
    @Transactional
    @Query("update Member m set m.credit = m.credit - 1 where m.id = :id and m.credit > 0")
    int decrementCreditIfAvailable(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("update Member m set m.credit = m.credit + 1 where m.id = :id")
    int incrementCredit(@Param("id") String id);
}
