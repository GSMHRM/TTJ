package com.gsmhrm.anything_back.domain.plan.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NoEditPermissionException extends AnythingBackException {

    public NoEditPermissionException() {
        super(ErrorCode.NO_AUTH_EDIT);
    }
}
