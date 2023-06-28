package com.gsmhrm.anything_back.domain.email.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ManyRequestForEmailException extends AnythingBackException {

    public ManyRequestForEmailException() {
        super(ErrorCode.MANY_REQUEST_EMAIL_AUTH);
    }
}
