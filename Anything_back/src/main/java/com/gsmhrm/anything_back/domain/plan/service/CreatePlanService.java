package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RollbackService
@RequiredArgsConstructor
public class CreatePlanService {

    private final PlanRepository planRepository;
    private final UserUtil util;

    public void execute(CreatePlanRequest createPlanRequest) {

        Plan plan = Plan.builder()
                .member(util.currentUser())
                .title(createPlanRequest.getTitle())
                .content(createPlanRequest.getContent())
                .completed(false)
                .start_Time(createPlanRequest.getStart_Time())
                .end_Time(createPlanRequest.getEnd_Time())
                .createTime(LocalDateTime.now())
                .editTime(LocalDateTime.now())
                .build();

        planRepository.save(plan);
    }
}
