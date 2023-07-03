package com.gsmhrm.anything_back.domain.email.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotAuthenticationEmailException extends AnythingBackException {

    public NotAuthenticationEmailException() {
        super(ErrorCode.EMAIL_NOT_VERIFIED);
    }
}
