package com.gsmhrm.anything_back.domain.member.service;

import com.gsmhrm.anything_back.domain.auth.exception.MisMatchPasswordException;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.presentation.dto.request.ChangePasswordRequest;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@RollbackService
@RequiredArgsConstructor
public class ChangePasswordService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void execute(ChangePasswordRequest changePasswordRequest) {

        Member member = memberRepository.findByPassword(passwordEncoder.encode(changePasswordRequest.getPassword()))
                .orElseThrow(MisMatchPasswordException::new);

        if (!Objects.equals(changePasswordRequest.getWant_ps(), changePasswordRequest.getWant_ps_too())) {
            throw new MisMatchPasswordException();
        }

        member.changePassword(passwordEncoder.encode(changePasswordRequest.getWant_ps()));
    }
}
