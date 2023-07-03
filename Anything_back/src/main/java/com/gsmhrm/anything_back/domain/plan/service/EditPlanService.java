package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.exception.NoEditPermissionException;
import com.gsmhrm.anything_back.domain.plan.exception.NotFoundPlanException;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.EditPlanRequest;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class EditPlanService {

    private final PlanRepository planRepository;
    private final UserUtil util;

    public void execute(Long id, EditPlanRequest editPlanRequest) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(NotFoundPlanException::new);

        if (!(plan.getMember() == util.currentUser())) {
            throw new NoEditPermissionException();
        }

        Boolean completed = editPlanRequest.getCompleted();

        if (completed == null) {
            completed = plan.getCompleted();
        }

        plan.editPlan(editPlanRequest, completed);

        planRepository.save(plan);
    }
}
