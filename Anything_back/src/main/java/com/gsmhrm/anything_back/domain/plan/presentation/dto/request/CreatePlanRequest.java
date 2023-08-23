package com.gsmhrm.anything_back.domain.plan.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlanRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull(message = "시작 시간은 필수입니다.")
    private LocalDateTime start_Time;

    @NotNull(message = "End 시간은 필수입니다.")
    private LocalDateTime end_Time;
}
