package com.gsmhrm.anything_back.domain.plan.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TransTypeListResponse {

    private List<PlanResponse> planList;
}
