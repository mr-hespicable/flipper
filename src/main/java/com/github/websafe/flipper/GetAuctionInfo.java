package com.github.websafe.flipper;

import com.github.websafe.apiProcessing.GetInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GetAuctionInfo {
    private static final GetInfo g = new GetInfo();
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";
    public int totalPages = g.GetResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();

    private void GetValue(JsonObject obj) {
        for (int i = 0; i <= totalPages; i++) {
            String pageNum = AHurl + "?page=" + i;
            JsonArray totalAuctions = g.GetResponse(pageNum).getAsJsonObject("auctions").getAsJsonArray();
            for (int j = 0; j <= totalAuctions.size(); j++ ) {
                totalAuctions.get(j);
                //TODO: find value of each item relative to the price.
            }


        }
    }
}

//g.GetResponse(AHurl);