package com.github.websafe.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class JSONParser {
    public static Gson gson = new Gson();
    public static String decode(String tj) {
        JsonObject obj = gson.fromJson(tj, JsonObject.class);
        return obj.getAsJsonObject("mayor").get("name").getAsString();
    }
}
