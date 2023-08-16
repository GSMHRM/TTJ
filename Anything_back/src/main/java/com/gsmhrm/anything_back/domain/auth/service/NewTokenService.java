package com.gsmhrm.anything_back.domain.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsmhrm.anything_back.domain.auth.entity.KakaoAuth;
import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.exception.RefreshTokenNotFoundException;
import com.gsmhrm.anything_back.domain.auth.exception.UserNotFoundException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.NewTokenResponse;
import com.gsmhrm.anything_back.domain.auth.repository.KakaoAuthRepository;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.kakao.service.NewKakaoTokenService;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.security.exception.TokenNotValidException;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.Optional;

@RollbackService
@RequiredArgsConstructor
@Slf4j
public class NewTokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;
    private final KakaoAuthRepository kakaoAuthRepository;
    private final NewKakaoTokenService newKakaoTokenService;

    public NewTokenResponse execute(String requestToken) throws JsonProcessingException {
        String email = tokenProvider.exactEmailFromRefreshToken(requestToken);
        log.info(email);
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(RefreshTokenNotFoundException::new);

        KakaoAuth auth = kakaoAuthRepository.findById(email)
                .orElse(null);

        if(!(auth == null)) {
            String[] tokens = newKakaoTokenService.execute(auth);
            KakaoAuth kakaoAuth = kakaoAuthRepository.findById(email)
                            .orElseThrow(UserNotFoundException::new);

            if(tokens.length >= 3) {
                kakaoAuth.changeToken(tokens[0], tokens[2]);
            }
            else kakaoAuth.changeToken(tokens[0], auth.getRefreshToken());

            kakaoAuthRepository.save(kakaoAuth);
        }

        if (!token.getRefreshToken().equals(requestToken)) {
            throw new TokenNotValidException();
        }

        String accessToken = tokenProvider.generateAccessToken(email);
        String refreshToken = tokenProvider.generateRefreshToken(email);
        ZonedDateTime AccessExpiredTime = tokenProvider.accessExpiredTime();

        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);

        return NewTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .AccessExpiredAt(AccessExpiredTime)
                .RefreshExpiredAt(tokenProvider.refreshExpiredTime())
                .build();
    }
}
