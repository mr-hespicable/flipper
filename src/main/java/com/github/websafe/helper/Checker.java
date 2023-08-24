package com.github.websafe.helper;

import com.google.gson.JsonObject;

import java.util.Objects;

public class Checker {
    public Boolean isBin(JsonObject obj) {
        //TODO: when settings are made, add functionality to switch boolean if player wants to use auctions
        return Objects.equals(obj.getAsJsonPrimitive("bin").getAsString(), "true");
    }
    public Boolean isSold(JsonObject obj) {
        return Objects.equals(obj.getAsJsonPrimitive("highest_bid_amount").getAsString(), obj.getAsJsonPrimitive("starting_bid").getAsString());
    }

}
