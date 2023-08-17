package com.gsmhrm.anything_back.domain.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsmhrm.anything_back.domain.auth.entity.KakaoAuth;
import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.SignInResponse;
import com.gsmhrm.anything_back.domain.auth.repository.KakaoAuthRepository;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.auth.service.MemberSignUpService;
import com.gsmhrm.anything_back.domain.kakao.presentation.dto.KakaoUserInfo;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.security.auth.MemberDetails;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RollbackService
@RequiredArgsConstructor
@Slf4j
public class KakaoUserService {

    private final GetKakaoAccessTokenService getKakaoAccessTokenService;
    private final TokenProvider tokenProvider;
    private final GetKakaoInfoService getKakaoUserInfo;
    private final MemberSignUpService memberSignUpService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final KakaoAuthRepository kakaoAuthRepository;

    public SignInResponse kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String[] tokens = getKakaoAccessTokenService.getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        KakaoUserInfo kakaoUserInfo = getKakaoUserInfo.getKakaoUserInfo(tokens[0]);

        // 3. 카카오ID로 회원가입 처리
        Member kakaoMember = memberSignUpService.execute(kakaoUserInfo);

        // 4. 강제 로그인 처리
        forceLogin(kakaoMember, tokens);

        // 5. response Header에 JWT 토큰 추가
        return kakaoUsersAuthorizationInput(response, kakaoMember);
    }

    private void forceLogin(Member kakaoMember, String[] tokens) {
        MemberDetails memberDetails = new MemberDetails(kakaoMember);
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        KakaoAuth kakaoAuth = new KakaoAuth(kakaoMember.getEmail(), tokens[0], tokens[1], tokenProvider.getTokenTimeProperties().getAccessTime());
        kakaoAuthRepository.save(kakaoAuth);
    }

    private SignInResponse kakaoUsersAuthorizationInput(HttpServletResponse response, Member kakaoMember) {

        String accessToken = tokenProvider.generateAccessToken(kakaoMember.getEmail());
        String refreshToken = tokenProvider.generateRefreshToken(kakaoMember.getEmail());
        RefreshToken redisToken = new RefreshToken(kakaoMember.getEmail(), refreshToken, tokenProvider.getTokenTimeProperties().getRefreshTime());
        refreshTokenRepository.save(redisToken);

        return SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .AccessExpiredAt(tokenProvider.accessExpiredTime())
                .RefreshExpiredAt(tokenProvider.refreshExpiredTime())
                .build();
    }
}
