package com.gsmhrm.anything_back.domain.plan.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;

public class NotChangeContentException extends AnythingBackException {

    public NotChangeContentException() {
        super(ErrorCode.NOT_CHANGE_CONTENT);
    }
}
