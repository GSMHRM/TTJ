package com.gsmhrm.anything_back.domain.member.util;

import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.exception.RefreshTokenNotFoundException;
import com.gsmhrm.anything_back.domain.auth.repository.BlackListRepository;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RollbackService
@RequiredArgsConstructor
public class LogoutTestUtil {

    private final BlackListRepository blackListRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserUtil util;

    public void execute(String email) {
        Optional<?> member = util.getMember();

        RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(RefreshTokenNotFoundException::new);

        refreshTokenRepository.delete(refreshToken);
    }
}
