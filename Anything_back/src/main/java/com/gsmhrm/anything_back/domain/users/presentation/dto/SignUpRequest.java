package com.gsmhrm.anything_back.domain.users.presentation.dto;

import com.gsmhrm.anything_back.domain.users.enums.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    @Email
    private final String email;
    private final String name;
    private final String password;
    private final Role role;
}
