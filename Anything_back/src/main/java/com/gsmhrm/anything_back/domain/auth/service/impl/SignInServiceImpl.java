package com.gsmhrm.anything_back.domain.auth.service.impl;

import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.exception.MemberNotFoundException;
import com.gsmhrm.anything_back.domain.auth.exception.MisMatchPasswordException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignInRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.LoginResponse;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.auth.service.UserLoginService;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements UserLoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResponse execute(SignInRequest request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(MemberNotFoundException::new);

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MisMatchPasswordException();
        }

        String accessToken = tokenProvider.generatedAccessToken(request.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(request.getEmail());
        RefreshToken entityRefresh = new RefreshToken(request.getEmail(), refreshToken, tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());

        refreshTokenRepository.save(entityRefresh);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }
}
