package com.gsmhrm.anything_back.domain.member.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    @NotBlank(message = "기존 비밀번호를 입력해 주세요")
    private String password;

    @NotBlank(message = "바꿀 비밀번호를 입력해 주세요")
    private String want_ps;

    @NotBlank(message = "바꿀 비밀번호를 다시 입력해 주세요")
    private String want_ps_too;
}
