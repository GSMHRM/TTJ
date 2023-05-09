package com.gsmhrm.anything_back.domain.member.repository;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);
    Member findByPassword(String password);
}