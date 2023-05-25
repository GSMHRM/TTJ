package com.gsmhrm.anything_back.global.exception;

import com.gsmhrm.anything_back.global.exception.handler.ErrorCode;
import lombok.Getter;

@Getter
public class TokenNotValidException extends RuntimeException{

    public TokenNotValidException() {
        super(String.valueOf(ErrorCode.TOKEN_NOT_VALID));
    }
}
