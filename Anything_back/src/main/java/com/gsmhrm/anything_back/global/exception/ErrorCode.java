package com.gsmhrm.anything_back.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EMAIL_ALREADY_EXIST("존재하는 이메일 입니다", 409),
    EMAIL_NOT_FOUND("존재하지 않는 이메일 입니다", 404);

    private final String message;
    private final int status;
}
