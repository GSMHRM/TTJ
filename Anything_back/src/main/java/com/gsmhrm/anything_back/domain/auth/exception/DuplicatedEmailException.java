package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicatedEmailException extends RuntimeException{

    public DuplicatedEmailException() {
        super(String.valueOf(ErrorCode.EMAIL_ALREADY_EXIST));
    }
}
