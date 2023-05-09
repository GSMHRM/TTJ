package com.gsmhrm.anything_back.domain.auth.service;

import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignUpRequest;

public interface UserSignupService {

    void execute(SignUpRequest request);
}
