package com.gsmhrm.anything_back.domain.email.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchAuthKeyException extends AnythingBackException {

    public MisMatchAuthKeyException() {
        super(ErrorCode.MISMATCH_AUTHKEY);
    }
}
