package com.gsmhrm.anything_back.domain.email.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailCheckDto {


    @NotBlank(  message = "인증키를 입력해주세요")
    private String key;
}
