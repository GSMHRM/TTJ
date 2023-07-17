package com.gsmhrm.anything_back.domain.member.presentation;

import com.gsmhrm.anything_back.domain.member.presentation.dto.request.ChangePasswordRequest;
import com.gsmhrm.anything_back.domain.member.presentation.dto.request.CreateInfoRequest;
import com.gsmhrm.anything_back.domain.member.service.ChangePasswordService;
import com.gsmhrm.anything_back.domain.member.service.CreateMemberInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = {"유저관련 API 정보를 제공하는 Controller"})
@RequiredArgsConstructor
public class MemberController {

    private ChangePasswordService changePasswordService;
    private CreateMemberInfoService createMemberInfoService;

    @ApiOperation(value = "비밀번호를 변경하는 메소드")
    @PostMapping("/change")
    public ResponseEntity<?> changePasswordService(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        changePasswordService.execute(changePasswordRequest);
        return new ResponseEntity<>(HttpStatus.IM_USED);
    }

    @RequestMapping("/info")
    @PostMapping
    public ResponseEntity<?> createInfo(@RequestBody @Valid CreateInfoRequest createInfoRequest) {
        createMemberInfoService.execute(createInfoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
