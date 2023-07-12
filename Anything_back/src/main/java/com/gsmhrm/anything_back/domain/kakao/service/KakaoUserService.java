package com.gsmhrm.anything_back.domain.kakao.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public KakaoUserInfo kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getKakaoAccessTokenService.getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        KakaoUserInfo kakaoUserInfo = getKakaoUserInfo.getKakaoUserInfo(accessToken);

        // 3. 카카오ID로 회원가입 처리
        Member kakaoMember = memberSignUpService.execute(kakaoUserInfo);

        // 4. 강제 로그인 처리
        Authentication authentication = forceLogin(kakaoMember);

        // 5. response Header에 JWT 토큰 추가
        kakaoUsersAuthorizationInput(authentication, response);
        return kakaoUserInfo;
    }

    private Authentication forceLogin(Member kakaoMember) {
        MemberDetails memberDetails = new MemberDetails(kakaoMember);
        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private void kakaoUsersAuthorizationInput(Authentication authentication, HttpServletResponse response) {
        // response header에 token 추가
        MemberDetails userDetailsImpl = ((MemberDetails) authentication.getPrincipal());
        String token = tokenProvider.generatedAccessToken(userDetailsImpl.getEmail());
        log.info("accessToken : " + token);
        response.addHeader("Authorization", "BEARER" + " " + token);
    }
}
