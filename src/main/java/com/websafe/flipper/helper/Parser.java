package com.websafe.flipper.helper;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class Parser {
    private static final Gson gson = new Gson();
    public JsonObject decode(String tj) {
        return gson.fromJson(tj, JsonObject.class);
    }
}
