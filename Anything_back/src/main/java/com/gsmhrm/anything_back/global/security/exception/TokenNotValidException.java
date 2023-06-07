package com.gsmhrm.anything_back.global.security.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TokenNotValidException extends RuntimeException{

    public TokenNotValidException() {
        super(String.valueOf(ErrorCode.TOKEN_NOT_VALID));
    }
}
