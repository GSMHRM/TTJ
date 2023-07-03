package com.gsmhrm.anything_back.domain.email.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ExistEmailException extends AnythingBackException {

    public ExistEmailException() {
        super(ErrorCode.EMAIL_ALREADY_EXIST);
    }
}
