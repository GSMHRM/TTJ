package com.gsmhrm.anything_back.domain.auth.service;

import com.gsmhrm.anything_back.domain.auth.exception.DuplicatedEmailException;
import com.gsmhrm.anything_back.domain.auth.presentation.dto.request.SignUpRequest;
import com.gsmhrm.anything_back.domain.email.entity.Email;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RollbackService
@RequiredArgsConstructor
public class MemberSignUpService {

     private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
     private final EmailUtil emailUtil;

     public void execute(SignUpRequest request) {
         if (memberRepository.existsByEmail(request.getEmail())) {
             throw new DuplicatedEmailException();
         }

         Email emailAuth = emailUtil.getEmail(request.getEmail());

         emailUtil.checkEmailAuthentication(emailAuth);

         Member member = Member.builder()
                 .email(request.getEmail())
                 .name(request.getName())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .build();

         memberRepository.save(member);
     }
}
