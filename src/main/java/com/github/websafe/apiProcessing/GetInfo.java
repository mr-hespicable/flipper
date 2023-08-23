package com.github.websafe.apiProcessing;

import com.github.websafe.JSONParser;
import com.google.gson.JsonObject;

public class GetInfo {
    private static final APIRequest api = new APIRequest();
    public JsonObject getResponse(String url) {
        String jse = api.getData(url, Boolean.FALSE);
        return JSONParser.decode(jse);
    }
}
