package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicatedNameException extends RuntimeException{

    public DuplicatedNameException() {
        super(String.valueOf(ErrorCode.NAME_ALREADY_EXIST));
    }
}
