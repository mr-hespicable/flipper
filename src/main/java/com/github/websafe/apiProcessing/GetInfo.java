package com.github.websafe.apiProcessing;

import com.github.websafe.helper.JSONParser;

public class GetInfo {
    private static final APIRequest api = new APIRequest();
    public static Object GetResponse(String url) {
        String jse = api.getData(url, Boolean.FALSE);
        return JSONParser.decode(jse);
    }
}
