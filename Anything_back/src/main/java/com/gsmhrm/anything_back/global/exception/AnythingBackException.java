package com.gsmhrm.anything_back.global.exception;

import lombok.Getter;

@Getter

public class AnythingBackException extends RuntimeException{

    private final ErrorCode errorCode;

    public AnythingBackException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
