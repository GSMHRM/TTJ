package com.gsmhrm.anything_back.domain.member.service;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ChangePasswordServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private ChangePasswordService changePasswordService;

    @Test
    @DisplayName("비밀번호 변경 서비스 테스트")
    void changePasswordTest() {

        Member member = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("name")
                .password("1234")
                .build();

        memberRepository.save(member);

        System.out.println("Before Change = " + member.getPassword());

        String rePassword = "12345";
        member.changePassword(rePassword);

        System.out.println("After Change = " + member.getPassword());
        memberRepository.save(member);

        Assertions.assertEquals(rePassword, member.getPassword());
    }
}
