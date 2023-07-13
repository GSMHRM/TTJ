package com.gsmhrm.anything_back.domain.auth.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignInRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignUpRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.NewTokenResponse;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.SignInResponse;
import com.gsmhrm.anything_back.domain.auth.service.MemberLoginService;
import com.gsmhrm.anything_back.domain.auth.service.MemberLogoutService;
import com.gsmhrm.anything_back.domain.auth.service.MemberSignUpService;
import com.gsmhrm.anything_back.domain.auth.service.NewTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

        private final MemberSignUpService signUpService;
        private final MemberLoginService loginService;
        private final MemberLogoutService logoutService;
        private final NewTokenService newTokenService;

        @PostMapping("/signup")
        public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
            signUpService.execute(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        @PostMapping("/login")
        public ResponseEntity<SignInResponse> login(@Valid @RequestBody SignInRequest request) {
            SignInResponse response = loginService.execute(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @DeleteMapping
        public ResponseEntity<?> logout(@RequestHeader("Authorization")String accessToken) throws JsonProcessingException {
            logoutService.execute(accessToken);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PatchMapping
        public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
            NewTokenResponse newTokenResponse = newTokenService.execute(token);
            return new ResponseEntity<>(newTokenResponse, HttpStatus.OK);
        }
}
