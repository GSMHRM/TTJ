package com.gsmhrm.anything_back.domain.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsmhrm.anything_back.domain.auth.entity.BlackList;
import com.gsmhrm.anything_back.domain.auth.entity.KakaoAuth;
import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.exception.BlackListAlreadyExistException;
import com.gsmhrm.anything_back.domain.auth.exception.RefreshTokenNotFoundException;
import com.gsmhrm.anything_back.domain.auth.repository.BlackListRepository;
import com.gsmhrm.anything_back.domain.auth.repository.KakaoAuthRepository;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.kakao.service.KakaoLogOutService;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RollbackService
@RequiredArgsConstructor
public class MemberLogoutService {

    private final BlackListRepository blackListRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserUtil util;
    private final KakaoAuthRepository kakaoAuthRepository;
    private final KakaoLogOutService kakaoLogOutService;

    public void execute(String accessToken) throws JsonProcessingException {
        Member member = util.currentUser();
        String email = member.getEmail();

        KakaoAuth kakaoAuth = kakaoAuthRepository.findById(member.getEmail())
                .orElse(null);

        if (!(kakaoAuth == null)) {
            Long id = kakaoLogOutService.execute(member, kakaoAuth);
            deleteKakaoToken(kakaoAuth);
        }

        RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(RefreshTokenNotFoundException::new);
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(member.getEmail(), accessToken);
    }

    private void deleteKakaoToken(KakaoAuth kakaoAuth) {
        kakaoAuthRepository.deleteById(kakaoAuth.getEmail());
    }

    private void saveBlackList(String email, String accessToken) {
        if (blackListRepository.existsById(accessToken)) {
            throw new BlackListAlreadyExistException();
        }

        ZonedDateTime expiredTime = tokenProvider.accessExpiredTime();

        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(accessToken)
                .timeToLive(expiredTime)
                .build();

        blackListRepository.save(blackList);
    }
}
