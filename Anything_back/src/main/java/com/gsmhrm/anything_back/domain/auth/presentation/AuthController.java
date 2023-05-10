package com.gsmhrm.anything_back.domain.auth.presentation;

import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignInRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignUpRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.LoginResponse;
import com.gsmhrm.anything_back.domain.auth.service.impl.LogoutServiceImpl;
import com.gsmhrm.anything_back.domain.auth.service.impl.SignInServiceImpl;
import com.gsmhrm.anything_back.domain.auth.service.impl.SignupServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignupServiceImpl signupService;
    private final SignInServiceImpl signInService;
    private final LogoutServiceImpl logoutService;

    @PostMapping ("/users/join")
    public ResponseEntity<HttpStatus> memberJoin(@RequestBody SignUpRequest request) {
        signupService.execute(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/users/login")
    public ResponseEntity<LoginResponse> memberLogin(@RequestBody SignInRequest request) {
        LoginResponse loginResponse = signInService.execute(request);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<HttpStatus> logout(@RequestHeader("Authorization")String accessToken){
        logoutService.execute(accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
