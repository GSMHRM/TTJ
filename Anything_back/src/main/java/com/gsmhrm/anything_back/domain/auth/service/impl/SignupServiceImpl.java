package com.gsmhrm.anything_back.domain.auth.service.impl;

import com.gsmhrm.anything_back.domain.auth.exception.DuplicatedEmailException;
import com.gsmhrm.anything_back.domain.auth.exception.DuplicatedNameException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignUpRequest;
import com.gsmhrm.anything_back.domain.auth.service.UserSignupService;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements UserSignupService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(SignUpRequest request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new DuplicatedEmailException();
        }

        if (memberRepository.existsByName(request.getName())) {
            throw new DuplicatedNameException();
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        memberRepository.save(member);
    }
}
