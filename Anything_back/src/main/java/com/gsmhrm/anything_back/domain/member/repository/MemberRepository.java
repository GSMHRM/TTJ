package com.gsmhrm.anything_back.domain.member.repository;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByName(String name);
}
