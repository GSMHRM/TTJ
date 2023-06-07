package com.gsmhrm.anything_back.global.security.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException() {
        super(String.valueOf(ErrorCode.TOKEN_EXPIRATION));
    }
}
