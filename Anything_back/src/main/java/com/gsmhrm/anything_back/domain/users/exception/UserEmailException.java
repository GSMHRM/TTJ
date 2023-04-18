package com.gsmhrm.anything_back.domain.users.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEmailException extends RuntimeException{
    private final ErrorCode errorCode;

    public UserEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_ALREADY_EXIST;
    }
}
