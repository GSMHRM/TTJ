package com.gsmhrm.anything_back.domain.auth.exception;

import com.gsmhrm.anything_back.global.exception.handler.ErrorCode;
import lombok.Getter;

@Getter
public class BlackListAlreadyExistException extends RuntimeException{

    public BlackListAlreadyExistException() {
        super(String.valueOf(ErrorCode.BLACK_LIST_ALREADY_EXIST));
    }
}
