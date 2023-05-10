package com.gsmhrm.anything_back.domain.auth.service.impl;

import com.gsmhrm.anything_back.domain.auth.entity.BlackList;
import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.repository.BlackListRepository;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.auth.service.UserLogoutService;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutServiceImpl implements UserLogoutService {

    private final UserUtil userUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlackListRepository blackListRepository;
    private final TokenProvider tokenProvider;

    @Override
    public void execute(String accessToken) {
        Member member = userUtil.currentUser();
        String email = member.getEmail();

        RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new  RuntimeException("존재하지 않는 refreshToken"));

        refreshTokenRepository.delete(refreshToken);
    }

    private void inBlackList(String email, String accessToken) {
        if (blackListRepository.existsById(accessToken)) {
            throw new RuntimeException("이미 있는 블랙리스트");
        }

        long expiredTime = tokenProvider.getACCESS_TOKEN_EXPIRE_TIME();

        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(accessToken)
                .timeToLive(expiredTime)
                .build();

        blackListRepository.save(blackList);
    }
}
