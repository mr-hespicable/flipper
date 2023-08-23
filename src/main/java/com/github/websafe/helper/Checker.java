package com.github.websafe.helper;

import com.google.gson.JsonObject;

public class Checker {
    public String isBin(JsonObject obj) {
        //TODO: when settings are made, add functionality to switch boolean if player wants to use auctions
        return obj.getAsJsonPrimitive("bin").getAsString();
    }
    public String[] isSold(JsonObject obj) {
        return new String[]{obj.getAsJsonPrimitive("highest_bid_amount").getAsString(), obj.getAsJsonPrimitive("starting_bid").getAsString()};
    }
}
