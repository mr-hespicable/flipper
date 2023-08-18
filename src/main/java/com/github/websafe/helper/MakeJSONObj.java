package com.github.websafe.helper;

import com.google.gson.Gson;
import com.github.websafe.flipper.FlipperCommand;

public class MakeJSONObj {
    Gson gson = new Gson();
    public String decode(Object obj) {
        return gson.toJson(obj);
    }
}
