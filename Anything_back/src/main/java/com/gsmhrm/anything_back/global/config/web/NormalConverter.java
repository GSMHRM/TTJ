package com.gsmhrm.anything_back.global.config.web;

import javassist.bytecode.ByteArray;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NormalConverter implements JsonViewConverters {

    @Override
    public String convert(byte[] obj) {
        return new String(obj, StandardCharsets.UTF_8);
    }
}
