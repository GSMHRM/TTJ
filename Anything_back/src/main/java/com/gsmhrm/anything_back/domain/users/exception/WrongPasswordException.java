package com.gsmhrm.anything_back.domain.users.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WrongPasswordException extends RuntimeException{

    private final ErrorCode errorCode;

    public WrongPasswordException(String message) {
        super(message);
        this.errorCode = ErrorCode.WRONG_PASSWORD;
    }
}
