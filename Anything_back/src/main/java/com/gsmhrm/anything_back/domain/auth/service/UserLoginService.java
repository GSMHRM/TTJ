package com.gsmhrm.anything_back.domain.auth.service;

import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignInRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.LoginResponse;

public interface UserLoginService {
    LoginResponse execute(SignInRequest request);
}
