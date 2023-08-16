package com.gsmhrm.anything_back.domain.member;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

        Assertions.assertThat(member.getName()).isEqualTo("테스트이름");
    }
}
