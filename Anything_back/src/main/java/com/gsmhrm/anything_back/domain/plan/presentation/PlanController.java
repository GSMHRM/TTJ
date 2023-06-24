package com.gsmhrm.anything_back.domain.plan.presentation;

import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.service.CreatePlanService;
import com.gsmhrm.anything_back.domain.plan.service.ListPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/plan")
@RequiredArgsConstructor
public class PlanController {

    private final CreatePlanService createPlanService;

    @PostMapping
    public ResponseEntity<Void> createPlan(@RequestBody @Valid CreatePlanRequest createPlanRequest) {
        createPlanService.execute(createPlanRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
