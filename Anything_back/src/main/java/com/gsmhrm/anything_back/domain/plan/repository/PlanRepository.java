package com.gsmhrm.anything_back.domain.plan.repository;


import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.entity.enums.TypeTrans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> listByType(TypeTrans trans);
}
