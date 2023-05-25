package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.handler.ErrorCode;
import lombok.Getter;

@Getter
public class RefreshTokenNotFoundException extends RuntimeException{

    public RefreshTokenNotFoundException() {
        super(String.valueOf(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
    }
}
