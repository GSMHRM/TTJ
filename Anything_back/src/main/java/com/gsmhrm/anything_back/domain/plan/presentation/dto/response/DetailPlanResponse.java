package com.gsmhrm.anything_back.domain.plan.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class DetailPlanResponse {

    private Long planId;

    private String title;

    private String content;

    private Boolean check;

    private LocalDateTime start_Time;

    private LocalDateTime end_Time;

    private LocalDateTime createDate;

    private LocalDateTime editedDate;
}
