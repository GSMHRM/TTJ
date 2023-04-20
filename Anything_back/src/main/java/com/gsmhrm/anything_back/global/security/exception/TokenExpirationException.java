package com.gsmhrm.anything_back.global.security.exception;

import lombok.Getter;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
@Getter
public class TokenExpirationException extends RuntimeException {

    public TokenExpirationException() {
        super(String.valueOf(ErrorCode.TOKEN_IS_EXPIRED));
    }
}