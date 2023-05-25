package com.gsmhrm.anything_back.global.exception;

import com.gsmhrm.anything_back.global.exception.handler.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException() {
        super(String.valueOf(ErrorCode.TOKEN_EXPIRATION));
    }
}
