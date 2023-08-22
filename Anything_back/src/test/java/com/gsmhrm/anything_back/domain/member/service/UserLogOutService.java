package com.gsmhrm.anything_back.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gsmhrm.anything_back.domain.auth.entity.RefreshToken;
import com.gsmhrm.anything_back.domain.auth.exception.RefreshTokenNotFoundException;
import com.gsmhrm.anything_back.domain.auth.exception.UserNotFoundException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignInRequest;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.response.SignInResponse;
import com.gsmhrm.anything_back.domain.auth.repository.RefreshTokenRepository;
import com.gsmhrm.anything_back.domain.auth.service.MemberLoginService;
import com.gsmhrm.anything_back.domain.auth.service.MemberLogoutService;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.domain.member.util.LogoutTestUtil;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Transactional
public class UserLogOutService {

    @Autowired private MemberRepository memberRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private TokenProvider tokenProvider;
    @Autowired private UserUtil util;
    @Autowired private MemberLogoutService memberLogoutService;
    @Autowired private MemberLoginService memberLoginService;
    @Autowired private LogoutTestUtil logoutTestUtil;
    @Autowired private RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    @DisplayName("유저 로그인 확인 테스트")
    void currentMember() {

        System.out.println("실행여부");

        //GIVEN
        Member member = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("테스트이름")
                .password(passwordEncoder.encode("1234"))
                .build();

        memberRepository.save(member);

        SignInRequest signInRequest = new SignInRequest(
                "zadzed1100@gmail.com",
                "1234"
        );

        memberLoginService.execute(signInRequest);

        //WHEN
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getEmail(),
                member.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(token);
        System.out.println("context = " + token);

        //then
        Member currentMember = (Member) util.getMember()
                .orElseThrow(UserNotFoundException::new);

        System.out.println(currentMember.getEmail());
        assertEquals("zadzed1100@gmail.com", currentMember.getEmail());
    }

    @Test
    @DisplayName("유저 로그아웃 테스트")
    void user_LogOut() throws JsonProcessingException {

        SignInRequest signInRequest = new SignInRequest(
                "zadzed1100@gmail.com",
                "1234"
        );

        SignInResponse response = memberLoginService.execute(signInRequest);

        memberLogoutService.execute(response.getRefreshToken());
        assertNull(refreshTokenRepository.findById(response.getRefreshToken()));
    }
}
