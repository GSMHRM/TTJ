package com.gsmhrm.anything_back.domain.users.service;

import com.gsmhrm.anything_back.domain.auth.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.LoginResponse;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.users.entity.User;
import com.gsmhrm.anything_back.domain.users.exception.EmailNotFoundException;
import com.gsmhrm.anything_back.domain.users.exception.UserEmailException;
import com.gsmhrm.anything_back.domain.users.exception.WrongPasswordException;
import com.gsmhrm.anything_back.domain.users.presentation.dto.SignInRequest;
import com.gsmhrm.anything_back.domain.users.presentation.dto.SignUpRequest;
import com.gsmhrm.anything_back.domain.users.repository.UserRepository;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.security.jwt.properties.JwtProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final SignInRequest loginRequest;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signupRequest) throws Exception {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new UserEmailException("이메일이 이미 있는 이메일임");
        }

        User user = User.builder()
                .email(signupRequest.getEmail())
                .name(signupRequest.getName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(signupRequest.getRole())
                .build();

        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public LoginResponse execute(SignInRequest signinRequest) {
        User user = userRepository
                .findUserByEmail(signinRequest.getEmail())
                .orElseThrow(()->new EmailNotFoundException("이메일을 찾지 못했습니다"));

        if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new WrongPasswordException("패스워드가 일치 하지 않음");
        }

        String accessToken = tokenProvider.generatedAccessToken(loginRequest.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(loginRequest.getEmail());
        RefreshToken refreshTokenEntity = new RefreshToken(loginRequest.getEmail(), refreshToken,tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());

        refreshTokenRepository.save(refreshTokenEntity);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }

}
