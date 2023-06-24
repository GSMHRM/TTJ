package com.gsmhrm.anything_back.domain.plan.repository;


import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
