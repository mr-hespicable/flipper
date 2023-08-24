package com.github.websafe.flipper;

import com.github.websafe.apiProcessing.GetInfo;
import com.github.websafe.helper.Checker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;

import java.util.Objects;

public class GetAuctionInfo {
    private static final GetInfo g = new GetInfo();
    private static final Checker check = new Checker();
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";
    private final int totalPages = g.getResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();

    public void getAuction() {
        for (int i = 0; i < totalPages; i++) { //for each page
            String pageNum = AHurl + "?page=" + i;
            Minecraft.getMinecraft().thePlayer.sendChatMessage(pageNum);
            JsonArray totalAuctions = g.getResponse(pageNum).getAsJsonArray("auctions");
            for (int j = 0; j < totalAuctions.size(); j++ ) { //for each auction
                    JsonObject auction = totalAuctions.get(j).getAsJsonObject();
                    if (Objects.equals(check.isBin(auction), "true") && !Objects.equals(check.isSold(auction)[0], check.isSold(auction)[1])) {
                        //TODO: make class to decode item bytes
                    }
            }
        }
    }


}