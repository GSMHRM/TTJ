package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BlackListAlreadyExistException extends AnythingBackException {

    public BlackListAlreadyExistException() {
        super(ErrorCode.BLACK_LIST_ALREADY_EXIST);
    }
}
