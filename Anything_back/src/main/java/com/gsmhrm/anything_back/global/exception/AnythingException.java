package com.gsmhrm.anything_back.global.exception;

import lombok.Getter;

@Getter
public class AnythingException extends RuntimeException{

    private final ErrorCode errorCode;

    public AnythingException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
