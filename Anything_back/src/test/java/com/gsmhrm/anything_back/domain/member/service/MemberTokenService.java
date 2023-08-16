package com.gsmhrm.anything_back.domain.member.service;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.global.security.jwt.TokenProvider;
import com.gsmhrm.anything_back.global.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MemberTokenService {

    @Autowired private MemberRepository memberRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private TokenProvider tokenProvider;
    @Autowired private UserUtil util;

    @Test
    @Disabled
    @DisplayName("유저 로그인 확인 테스트")
    void currentMember() {

        //GIVEN
        Member member = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("테스트이름")
                .password("1234")
                .build();

        memberRepository.save(member);

        //WHEN

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getEmail(),
                member.getPassword()
        );

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        System.out.println("context = " + context);

        //then
        String currentEmail = util.currentUser().getEmail();
        assertEquals("zadzed1100@gmail.com", currentEmail);
    }
}
