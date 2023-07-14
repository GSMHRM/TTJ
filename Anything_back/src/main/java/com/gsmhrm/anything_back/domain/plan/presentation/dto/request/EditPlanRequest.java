package com.gsmhrm.anything_back.domain.plan.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditPlanRequest {

    private String title;

    private String content;

    private String completed;

    private LocalDateTime start_Time;

    private LocalDateTime end_Time;
}
