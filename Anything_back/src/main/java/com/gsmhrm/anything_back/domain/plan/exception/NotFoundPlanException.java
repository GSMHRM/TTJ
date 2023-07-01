package com.gsmhrm.anything_back.domain.plan.exception;

import com.gsmhrm.anything_back.global.exception.AnythingBackException;
import com.gsmhrm.anything_back.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundPlanException extends AnythingBackException {

    public NotFoundPlanException() {
        super(ErrorCode.NOT_FOUND_PLAN);
    }
}
