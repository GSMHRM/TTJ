package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.handler.ErrorCode;
import lombok.Getter;

@Getter
public class NotValidEmailException extends RuntimeException{

    public NotValidEmailException() {
        super(String.valueOf(ErrorCode.EMAIL_MISMATCH));
    }
}
