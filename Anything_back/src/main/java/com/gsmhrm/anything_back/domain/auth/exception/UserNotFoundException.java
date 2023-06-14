package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super(String.valueOf(ErrorCode.USER_NOT_FOUND));
    }
}
