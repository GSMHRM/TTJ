package com.gsmhrm.anything_back.domain.plan.presentation;

import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.EditPlanRequest;
import com.gsmhrm.anything_back.domain.plan.service.CreatePlanService;
import com.gsmhrm.anything_back.domain.plan.service.DeletePlanService;
import com.gsmhrm.anything_back.domain.plan.service.EditPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {

    private final CreatePlanService createPlanService;

    private final EditPlanService editPlanService;

    private final DeletePlanService deletePlanService;

    @PostMapping
    public ResponseEntity<Void> createPlan(@RequestBody @Valid CreatePlanRequest createPlanRequest) {
        createPlanService.execute(createPlanRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Void> editPlan(@PathVariable Long id, @RequestBody @Valid EditPlanRequest editPlanRequest) {
        editPlanService.execute(id, editPlanRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        deletePlanService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
