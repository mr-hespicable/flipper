package com.github.websafe.apiProcessing;

import com.github.websafe.helper.JSONParser;

public class GetInfo {
    private static final APIRequest api = new APIRequest();
    public static Object ExString() {
        String url = "https://api.hypixel.net/resources/skyblock/election";
        String jse = api.getResponse(url, Boolean.FALSE);
        return JSONParser.decode(jse);
    }
}
