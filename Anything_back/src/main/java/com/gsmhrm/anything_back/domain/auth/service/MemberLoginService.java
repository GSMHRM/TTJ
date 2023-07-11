package com.gsmhrm.anything_back.domain.auth.service;

import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.exception.MisMatchPasswordException;
import com.gsmhrm.anything_back.domain.auth.exception.NotValidEmailException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignInRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.SignInResponse;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RollbackService
@RequiredArgsConstructor
public class MemberLoginService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional(rollbackFor = Exception.class)
    public SignInResponse execute(SignInRequest request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(NotValidEmailException::new);

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MisMatchPasswordException();
        }

        String accessToken = tokenProvider.generatedAccessToken(request.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(request.getEmail());
        RefreshToken tokenRedis = new RefreshToken(request.getEmail(), refreshToken, tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());

        refreshTokenRepository.save(tokenRedis);

        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .AccessExpiredAt(tokenProvider.getExpiredAtToken())
                .RefreshExpiredAt(tokenProvider.getExpiredRefreshToken())
                .build();
    }
}
