package com.gsmhrm.anything_back.global.exception.handler;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomParameterHandler {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor ste = new StringTrimmerEditor(true); // emptyAsNull: true (빈문자열은 null로 파싱함)
        dataBinder.registerCustomEditor(String.class, ste);
    }
}
