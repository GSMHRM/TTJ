package com.gsmhrm.anything_back.domain.member.service;

import com.gsmhrm.anything_back.domain.member.entity.Member;
import com.gsmhrm.anything_back.domain.member.entity.MemberIntro;
import com.gsmhrm.anything_back.domain.member.exception.NotAllowedLengthException;
import com.gsmhrm.anything_back.domain.member.presentation.dto.request.CreateInfoRequest;
import com.gsmhrm.anything_back.domain.member.repository.MemberInfoRepository;
import com.gsmhrm.anything_back.global.annotation.RollbackService;
import com.gsmhrm.anything_back.global.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RollbackService
@RequiredArgsConstructor
public class CreateMemberInfoService {

    private final MemberInfoRepository memberInfoRepository;
    private final UserUtil util;


    public void execute(CreateInfoRequest createInfoRequest) {

        Member member = util.currentUser();

        if (createInfoRequest.getIntro().length() > 30) {
            throw new NotAllowedLengthException();
        }

        MemberIntro memberIntro = MemberIntro.builder()
                .intro(createInfoRequest.getIntro())
                .member(member)
                .build();

        memberInfoRepository.save(memberIntro);
    }
}
