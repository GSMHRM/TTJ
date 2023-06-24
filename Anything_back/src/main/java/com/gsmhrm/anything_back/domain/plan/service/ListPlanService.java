package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.entity.enums.TypeTrans;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.PlanListResponse;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.PlanResponse;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.TransTypeListResponse;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class ListPlanService {

    private final PlanRepository planRepository;

    public PlanListResponse execute() {

        List<Plan> planList = planRepository.findAll();

        return PlanListResponse.builder()
                .planList(
                        planList.stream()
                                .map(PlanResponse::planResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public TransTypeListResponse execute(String transType) {
        List<Plan> planList = planRepository.listByType(TypeTrans.valueOf(transType));

        return TransTypeListResponse.builder()
                .planList(
                        planList.stream()
                                .map(PlanResponse::planResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
