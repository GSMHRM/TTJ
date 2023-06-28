package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotValidEmailException extends AnythingBackException {

    public NotValidEmailException() {
        super(ErrorCode.EMAIL_MISMATCH);
    }
}
