package com.gsmhrm.anything_back.domain.member;

import com.gsmhrm.anything_back.domain.auth.exception.DuplicatedEmailException;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("유저 SignUp 테스트")
    void Member_save() {

        Member member = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("테스트이름")
                .password("1234")
                .build();

        memberRepository.save(member);

        assertThat(memberRepository.findByEmail("zadzed1100@gmail.com").get().getName().equals("테스트이름"));
    }

    @Test
    @DisplayName("유저 SignUp 실패 테스트")
    void Member_save_Failed() {

        Member member = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("테스트이름")
                .password("1234")
                .build();

        Member member2 = Member.builder()
                .email("zadzed1100@gmail.com")
                .name("테스트이름2")
                .password("12345")
                .build();

        memberRepository.save(member);

        System.out.println(member == member2);

        Assertions.assertThrows(DuplicatedEmailException.class, () ->
                memberRepository.save(member2)
        );
    }
}
