package com.gsmhrm.anything_back.domain.member.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotAllowedLengthException extends AnythingBackException {

    public NotAllowedLengthException() {
        super(ErrorCode.NOT_ALLOWD_LENGTH);
    }
}
