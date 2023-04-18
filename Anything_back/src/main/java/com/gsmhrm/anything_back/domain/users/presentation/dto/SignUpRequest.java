package com.gsmhrm.anything_back.domain.users.presentation.dto;

import com.gsmhrm.anything_back.domain.users.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotBlank(message = "Role은 필수입니다.")
    private Role role;
}
