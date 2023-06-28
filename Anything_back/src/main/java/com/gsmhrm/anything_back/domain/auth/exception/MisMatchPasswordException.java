package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MisMatchPasswordException extends AnythingBackException {

    public MisMatchPasswordException() {
        super(ErrorCode.WRONG_PASSWORD);
    }
}
