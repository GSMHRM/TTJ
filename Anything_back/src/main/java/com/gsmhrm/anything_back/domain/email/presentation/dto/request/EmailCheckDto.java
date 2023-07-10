package com.gsmhrm.anything_back.domain.email.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailCheckDto {

    @NotBlank(message = "이메일이 전달되지 않았습니다.")
    private String email;

    @NotBlank(message = "인증키를 입력해주세요")
    private String key;
}
