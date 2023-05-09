package com.gsmhrm.anything_back.domain.auth.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {

    private String email;
    private String password;
}
