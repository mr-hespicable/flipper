package com.github.websafe.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class JSONParser {
    public static Gson gson = new Gson();
    public static JsonArray decode(String tj) {
        JsonObject obj = new JsonObject();
        return obj.getAsJsonArray(tj);
    }
}
