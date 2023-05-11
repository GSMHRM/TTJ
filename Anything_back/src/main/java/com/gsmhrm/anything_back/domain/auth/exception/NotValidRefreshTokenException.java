package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotValidRefreshTokenException extends RuntimeException{

    public NotValidRefreshTokenException() {
        super(String.valueOf(ErrorCode.TOKEN_NOT_VALID));
    }
}
