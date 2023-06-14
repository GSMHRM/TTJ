package com.gsmhrm.anything_back.domain.email.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchAuthKeyException extends RuntimeException{

    public MisMatchAuthKeyException() {
        super(String.valueOf(ErrorCode.MISMATCH_AUTHKEY));
    }
}
