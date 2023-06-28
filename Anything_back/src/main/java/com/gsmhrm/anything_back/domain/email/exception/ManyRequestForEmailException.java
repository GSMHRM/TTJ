package com.gsmhrm.anything_back.domain.email.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ManyRequestForEmailException extends RuntimeException{

    public ManyRequestForEmailException() {
        super(String.valueOf(ErrorCode.MANY_REQUEST_EMAIL_AUTH));
    }
}
