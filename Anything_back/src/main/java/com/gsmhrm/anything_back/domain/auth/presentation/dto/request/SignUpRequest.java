package com.gsmhrm.anything_back.domain.auth.presentation.dto.request;

import com.gsmhrm.anything_back.domain.member.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
@Component
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 특수문자가 1개 이상 포함되어야 하고 글자 수는 8자 이상, 16자 이내로 입력해야 합니다.")
    private String password;

    @NotBlank(message = "역할은 필수 입력 값입니다.")
    private Role role;
}
