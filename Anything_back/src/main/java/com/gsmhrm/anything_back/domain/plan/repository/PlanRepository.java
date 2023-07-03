package com.gsmhrm.anything_back.domain.plan.repository;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByMember(Member member);

    Plan findByMemberAndId(Member member, Long id);
}
