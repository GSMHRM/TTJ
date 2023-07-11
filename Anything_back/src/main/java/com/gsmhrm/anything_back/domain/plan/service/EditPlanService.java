package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.exception.NoEditPermissionException;
import com.gsmhrm.anything_back.domain.plan.exception.NotFoundPlanException;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.EditPlanRequest;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RollbackService
@RequiredArgsConstructor
public class EditPlanService {

    private final PlanRepository planRepository;
    private final UserUtil util;

    public void execute(Long id, EditPlanRequest editPlanRequest) {

        Plan plan = planRepository.findById(id)
                .orElseThrow(NotFoundPlanException::new);

        Boolean completed = plan.getCompleted();

        if (!(plan.getMember() == util.currentUser())) {
            throw new NoEditPermissionException();
        }

        if (!Objects.equals(editPlanRequest.getCompleted(), "true") && !Objects.equals(editPlanRequest.getCompleted(), "false")) {
            String comp = editPlanRequest.getCompleted();

            completed = !Objects.equals(comp, "true");
        }

        plan.editPlan(editPlanRequest, completed);

        planRepository.save(plan);
    }
}
