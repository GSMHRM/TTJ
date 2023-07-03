package com.gsmhrm.anything_back.global.util;

import com.gsmhrm.anything_back.domain.auth.exception.UserNotFoundException;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final MemberRepository memberRepository;

    public Member currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}