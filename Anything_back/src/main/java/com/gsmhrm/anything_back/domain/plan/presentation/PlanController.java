package com.gsmhrm.anything_back.domain.plan.presentation;

import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.CreatePlanRequest;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.request.EditPlanRequest;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.DetailPlanResponse;
import com.gsmhrm.anything_back.domain.plan.presentation.dto.response.ListPlanResponse;
import com.gsmhrm.anything_back.domain.plan.service.*;
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

    private final PlanListService planListService;

    private final PlanDetailService planDetailService;

    @PostMapping
    public ResponseEntity<Void> createPlan(@RequestBody @Valid CreatePlanRequest createPlanRequest) {
        createPlanService.execute(createPlanRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Void> editPlan(@PathVariable Long id, @RequestBody @Valid EditPlanRequest editPlanRequest) {
        Boolean nullChecking = editPlanService.hasValues(editPlanRequest);
        editPlanService.execute(id, editPlanRequest, nullChecking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        deletePlanService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<ListPlanResponse> getList() {
        var list = planListService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailPlanResponse> getDetailPlan(@PathVariable Long id) {
        var detail = planDetailService.execute(id);
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }
}
