package com.gsmhrm.anything_back.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private final String message;
    private final int status;

    public ErrorMessage(ErrorCode errorCode){
        this(errorCode.getMessage(), errorCode.getStatus());
    }

    public static ResponseEntity<ErrorMessage> toResponseEntity(HttpStatus httpStatus, String message) {
        return ResponseEntity
                .status(httpStatus)
                .body(ErrorMessage.builder()
                        .status(httpStatus.value())
                        .message(message)
                        .build()
                );
    }
}