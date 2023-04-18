package com.gsmhrm.anything_back.domain.users.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public EmailNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_NOT_FOUND;
    }
}
