package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException() {
        super(String.valueOf(ErrorCode.MEMBER_NOT_FOUND));
    }
}
