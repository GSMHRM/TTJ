package com.gsmhrm.anything_back.global.config.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.nio.charset.StandardCharsets;

public class PrettyConverter implements JsonViewConverters {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final JsonParser jsonParser = new JsonParser();

    @Override
    public String convert(byte[] obj) {
        return gson.toJson(JsonParser.parseString(new String(obj, StandardCharsets.UTF_8)));
    }
}
