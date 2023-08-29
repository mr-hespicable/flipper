package com.websafe.flipper.algorithm;

import com.websafe.flipper.apiProcessing.GetInfo;
import com.websafe.flipper.helper.Checker;
import com.websafe.flipper.helper.Decoder;
import com.websafe.flipper.algorithm.GetValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.nullicorn.nedit.type.NBTCompound;

import java.io.IOException;
import java.util.Date;

public class GetAuctionInfo {
    //declare Classes
    private static final GetInfo g = new GetInfo();
    private static final Checker check = new Checker();
    private static final Decoder decode = new Decoder();
    private static final GetItemInfo info = new GetItemInfo();
    private static final GetValue value = new GetValue();

    //declare url
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";

    //declare variables
    private final int pageCount = g.getResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();

    public void getAuction() throws IOException {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < pageCount; i++) { //for each page
            String pageNum = AHurl + "?page=" + i;
            JsonArray totalAuctions = g.getResponse(pageNum).getAsJsonArray("auctions");
            for (int j = 0; j < totalAuctions.size(); j++ ) { //for each auction
                JsonObject auction = totalAuctions.get(j).getAsJsonObject();
                if (check.isBin(auction).equals(Boolean.TRUE) && check.isSold(auction).equals(Boolean.FALSE)) {
                    NBTCompound nbtData = decode.itemBytes(auction.getAsJsonPrimitive("item_bytes").getAsString());
                    value.Value(nbtData);

                }
            }
        }
        System.out.println(new Date().getTime() - startTime);
        System.out.println("DONE WITH THAT SHIT.");
    }


}










