package com.gsmhrm.anything_back.domain.plan.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "시작 시간은 필수입니다.")
    private LocalDateTime start_Time;

    @NotBlank(message = "끝나는 시간은 필수입니다.")
    private LocalDateTime end_Time;
}
