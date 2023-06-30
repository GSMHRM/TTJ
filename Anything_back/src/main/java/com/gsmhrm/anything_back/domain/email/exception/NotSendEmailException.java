package com.gsmhrm.anything_back.domain.email.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotSendEmailException extends AnythingBackException {

    public NotSendEmailException() {
        super(ErrorCode.CANT_SEND_EMAIL);
    }
}
