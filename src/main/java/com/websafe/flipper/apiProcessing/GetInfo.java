package com.websafe.flipper.apiProcessing;

import com.websafe.flipper.helper.Parser;
import com.google.gson.JsonObject;

public class GetInfo {
    private static final APIRequest api = new APIRequest();
    private static final Parser dec = new Parser();

    public JsonObject getResponse(String url) {
        String jse = api.getData(url);
        return dec.decode(jse);
    }
}
