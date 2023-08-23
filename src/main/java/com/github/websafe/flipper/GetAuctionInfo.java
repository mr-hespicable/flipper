package com.github.websafe.flipper;

import com.github.websafe.apiProcessing.GetInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.client.Minecraft;

import java.util.Objects;

public class GetAuctionInfo {
    private static final GetInfo g = new GetInfo();
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";
    private final int totalPages = g.GetResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();

    public void GetAuction() {
        for (int i = 0; i < totalPages; i++) { //for each page
            String pageNum = AHurl + "?page=" + i;
            Minecraft.getMinecraft().thePlayer.sendChatMessage(pageNum);
            JsonArray totalAuctions = g.GetResponse(pageNum).getAsJsonArray("auctions");
            for (int j = 0; j < totalAuctions.size(); j++ ) { //for each auction
                    JsonObject auction = totalAuctions.get(j).getAsJsonObject();
                    if (Objects.equals(isBin(auction), "true") && !Objects.equals(isSold(auction)[0], isSold(auction)[1])) {
                        //TODO: make class to decode item bytes, maybe move isBin() and isSold() to a new helper class?
                    }
            }
        }
    }
    private String isBin(JsonObject obj) {
        //TODO: when settings are made, add functionality to switch boolean if player wants to use auctions
        return obj.getAsJsonPrimitive("bin").getAsString();
    }
    private String[] isSold(JsonObject obj) {
        return new String[]{obj.getAsJsonPrimitive("highest_bid_amount").getAsString(), obj.getAsJsonPrimitive("starting_bid").getAsString()};
    }

}