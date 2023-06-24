package com.gsmhrm.anything_back.domain.plan.presentation;

import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.PlanListResponse;
import com.gsmhrm.anything_back.domain.plan.service.CreatePlanService;
import com.gsmhrm.anything_back.domain.plan.service.ListPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/plan")
@RequiredArgsConstructor
public class PlanController {

    private final CreatePlanService createPlanService;
    private final ListPlanService listPlanService;

    @PostMapping
    public ResponseEntity<Void> createPlan(@RequestBody @Valid CreatePlanRequest createPlanRequest) {
        createPlanService.execute(createPlanRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<PlanListResponse> getPlanList() {
        var list = listPlanService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
