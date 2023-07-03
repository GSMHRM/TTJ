package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.ListPlanResponse;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.PlanResponse;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.ReadOnlyService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ReadOnlyService
@RequiredArgsConstructor
public class PlanListService {

    private final PlanRepository planRepository;
    private final UserUtil util;

    public ListPlanResponse execute() {
        Member member = util.currentUser();

        List<Plan> planList = planRepository.findByMember(member);

        return ListPlanResponse.builder()
                .planList(
                        planList.stream()
                                .map(PlanResponse::planResponse)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
