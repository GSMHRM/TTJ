package com.gsmhrm.anything_back.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EMAIL_ALREADY_EXIST("존재하는 이메일 입니다", 409),
    NAME_ALREADY_EXIST("존재하는 이름 입니다", 409),
    BLACKLIST_ALREADY_EXIST("블랙리스트에 이미 등록됨", 400),
    EMAIL_NOT_FOUND("존재하지 않는 이메일 입니다", 404),
    MEMBER_NOT_FOUND("존재하지 않는 유저입니다", 404),
    WRONG_PASSWORD("잘못된 비밀번호 입니다", 400),
    TOKEN_IS_EXPIRED("토큰이 만료 되었습니다.", 401),
    UNKNOWN_ERROR("알 수 없는 에러입니다.", 500),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401);

    private final String message;
    private final int status;
}
