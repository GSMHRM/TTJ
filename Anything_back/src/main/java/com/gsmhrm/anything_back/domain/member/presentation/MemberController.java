package com.gsmhrm.anything_back.domain.member.presentation;

import com.gsmhrm.anything_back.domain.member.presentation.dto.request.ChangePasswordRequest;
import com.gsmhrm.anything_back.domain.member.service.ChangePasswordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MemberController {

    private ChangePasswordService changePasswordService;

    @PostMapping("/change")
    public ResponseEntity<?> changePasswordService(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        changePasswordService.execute(changePasswordRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
