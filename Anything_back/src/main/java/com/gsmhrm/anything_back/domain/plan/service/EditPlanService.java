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

        if (!plan.getMember().equals(util.currentUser())) {
            throw new NoEditPermissionException();
        }

        Boolean completed = getCompletedStatus(editPlanRequest);
        LocalDateTime startTime = getStartTime(editPlanRequest, plan);
        LocalDateTime endTime = getEndTime(editPlanRequest, plan);

        plan.editPlan(editPlanRequest, completed, startTime, endTime);

        planRepository.save(plan);
    }

    private Boolean getCompletedStatus(EditPlanRequest editPlanRequest) {
        String completedValue = editPlanRequest.getCompleted();
        if (Objects.equals(completedValue, "true")) {
            return true;
        } else if (Objects.equals(completedValue, "false")) {
            return false;
        }
        return !Objects.equals(completedValue, "true");
    }

    private LocalDateTime getStartTime(EditPlanRequest editPlanRequest, Plan plan) {
        LocalDateTime startTime = editPlanRequest.getStart_Time();
        return startTime != null ? startTime : plan.getStart_Time();
    }

    private LocalDateTime getEndTime(EditPlanRequest editPlanRequest, Plan plan) {
        LocalDateTime endTime = editPlanRequest.getEnd_Time();
        return endTime != null ? endTime : plan.getEnd_Time();
    }

}
