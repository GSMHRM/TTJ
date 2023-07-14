package com.gsmhrm.anything_back.domain.plan.service;

import com.gsmhrm.anything_back.domain.plan.entity.Plan;
import com.gsmhrm.anything_back.domain.plan.exception.NoEditPermissionException;
import com.gsmhrm.anything_back.domain.plan.exception.NotChangeContentException;
import com.gsmhrm.anything_back.domain.plan.exception.NotFoundPlanException;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.EditPlanRequest;
import com.gsmhrm.anything_back.domain.plan.repository.PlanRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@RollbackService
@RequiredArgsConstructor
public class EditPlanService {

    private final PlanRepository planRepository;
    private final UserUtil util;

    public boolean hasValues(EditPlanRequest editPlanRequest) {
        return Stream.of(
                        editPlanRequest.getTitle(),
                        editPlanRequest.getContent(),
                        editPlanRequest.getCompleted(),
                        editPlanRequest.getStart_Time(),
                        editPlanRequest.getEnd_Time()
                )
                .anyMatch(Objects::nonNull);
    }

    public void execute(Long id, EditPlanRequest editPlanRequest, Boolean nullChecking) {

        if (!nullChecking) {
            throw new NotChangeContentException();
        }

        Plan plan = planRepository.findById(id)
                .orElseThrow(NotFoundPlanException::new);

        Boolean completed = plan.getCompleted();
        LocalDateTime sTime;
        LocalDateTime eTime;

        if (!(plan.getMember() == util.currentUser())) {
            throw new NoEditPermissionException();
        }

        if (!Objects.equals(editPlanRequest.getCompleted(), "true") && !Objects.equals(editPlanRequest.getCompleted(), "false")) {
            String comp = editPlanRequest.getCompleted();
            completed = !Objects.equals(comp, "true");
        }

        if (editPlanRequest.getStart_Time() == null) {
            sTime = plan.getStart_Time();
        } else sTime = editPlanRequest.getStart_Time();

        if(editPlanRequest.getEnd_Time() == null) {
            eTime = plan.getEnd_Time();
        } else eTime = editPlanRequest.getEnd_Time();

        plan.editPlan(editPlanRequest, completed, sTime, eTime);

        planRepository.save(plan);
    }
}
