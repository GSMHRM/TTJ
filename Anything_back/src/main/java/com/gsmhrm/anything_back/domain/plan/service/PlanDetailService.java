package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.DetailPlanResponse;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.ReadOnlyService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class PlanDetailService  {

    private final PlanRepository planRepository;

    private final UserUtil util;

    public DetailPlanResponse execute(Long id) {

        Member member = util.currentUser();

        Plan plan = planRepository.findByMemberAndId(member, id);

        return DetailPlanResponse.builder()
                .planId(plan.getId())
                .title(plan.getTitle())
                .content(plan.getContent())
                .check(plan.getCompleted())
                .start_Time(plan.getStart_Time())
                .end_Time(plan.getEnd_Time())
                .createDate(plan.getCreateTime())
                .editedDate(plan.getEditTime())
                .build();
    }
}
