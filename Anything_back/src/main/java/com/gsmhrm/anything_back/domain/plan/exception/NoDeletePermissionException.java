package com.gsmhrm.anything_back.domain.plan.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;

public class NoDeletePermissionException extends AnythingBackException {

    public NoDeletePermissionException() {
        super(ErrorCode.NO_AUTH_DELETE);
    }
}
