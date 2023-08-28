package com.websafe.flipper;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class JSONParser {
    private static final Gson gson = new Gson();
    public JsonObject decode(String tj) {
        return gson.fromJson(tj, JsonObject.class);
    }
}
