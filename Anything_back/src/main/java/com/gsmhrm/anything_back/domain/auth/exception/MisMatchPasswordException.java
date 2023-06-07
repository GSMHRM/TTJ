package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchPasswordException extends RuntimeException{

    public MisMatchPasswordException() {
        super(String.valueOf(ErrorCode.WRONG_PASSWORD));
    }
}
