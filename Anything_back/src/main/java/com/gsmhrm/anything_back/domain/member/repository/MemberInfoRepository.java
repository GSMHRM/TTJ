package com.gsmhrm.anything_back.domain.member.repository;

import com.gsmhrm.anything_back.domain.member.entity.MemberIntro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberIntro, Long> {
}
