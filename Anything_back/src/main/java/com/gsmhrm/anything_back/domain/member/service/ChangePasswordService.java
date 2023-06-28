package com.gsmhrm.anything_back.domain.member.service;

import com.gsmhrm.anything_back.domain.auth.exception.MisMatchPasswordException;
import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.presentation.dto.request.ChangePasswordRequest;
import com.gsmhrm.anything_back.domain.member.repository.MemberRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@RollbackService
@RequiredArgsConstructor
public class ChangePasswordService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @ApiResponses({
            @ApiResponse(code=204, message="비밀번호 변경 완료"),
            @ApiResponse(code=401, message="비밀번호 변경 권한 없음, 로그인 필요")
    })
    @ApiOperation(value = "비밀번호를 변경하는 메소드")
    public void execute(ChangePasswordRequest changePasswordRequest) {

        Member member = memberRepository.findByPassword(passwordEncoder.encode(changePasswordRequest.getPassword()))
                .orElseThrow(MisMatchPasswordException::new);

        if (!Objects.equals(changePasswordRequest.getWant_ps(), changePasswordRequest.getWant_ps_too())) {
            throw new MisMatchPasswordException();
        }

        member.changePassword(passwordEncoder.encode(changePasswordRequest.getWant_ps()));
    }
}
