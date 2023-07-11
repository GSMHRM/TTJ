package com.gsmhrm.anything_back.global.util;

import com.gsmhrm.anything_back.domain.auth.exception.UserNotFoundException;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final MemberRepository memberRepository;

    public Member currentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByName(name)
                .orElseThrow(UserNotFoundException::new);
    }

    public static String getMemberName() {
        try {
            String memberName;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal instanceof UserDetails) {
                memberName = ((Member) principal).getName();
            } else {
                memberName = principal.toString();
            }
            return memberName;
        } catch (NullPointerException e) {
            return "no";
        }
    }
}