package com.gsmhrm.anything_back.domain.auth.service;

import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.exception.RefreshTokenNotFoundException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.NewTokenResponse;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.security.exception.TokenNotValidException;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RollbackService
@RequiredArgsConstructor
public class NewTokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    public NewTokenResponse execute(String requestToken) {
        String email = tokenProvider.getUserEmail(requestToken, jwtProperties.getRefreshSecret());
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (!token.getRefreshToken().equals(requestToken)) {
            throw new TokenNotValidException();
        }

        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        ZonedDateTime AccessExpiredTime = tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret());

        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);

        return NewTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .AccessExpiredAt(AccessExpiredTime)
                .RefreshExpiredAt(tokenProvider.getExpiredRefreshToken())
                .build();
    }
}
