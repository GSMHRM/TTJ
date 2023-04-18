package com.gsmhrm.anything_back.domain.users.presentation.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {

    @Email
    private final String email;
    private final String password;
}
