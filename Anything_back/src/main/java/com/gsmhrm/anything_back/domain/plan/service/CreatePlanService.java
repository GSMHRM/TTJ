package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class CreatePlanService {

    private final PlanRepository planRepository;
    private final UserUtil util;

    public void execute(CreatePlanRequest createPlanRequest) {

        Member member = util.currentUser();

        Plan plan = Plan.builder()
                .member(member)
                .title(createPlanRequest.getTitle())
                .trans(createPlanRequest.getTypeTrans())
                .country(createPlanRequest.getCountry())
                .start_date(createPlanRequest.getStart_date())
                .end_date(createPlanRequest.getEnd_date())
                .build();

        planRepository.save(plan);
    }
}
