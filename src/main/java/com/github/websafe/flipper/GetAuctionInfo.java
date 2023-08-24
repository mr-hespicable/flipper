package com.github.websafe.flipper;

import com.github.websafe.apiProcessing.GetInfo;
import com.github.websafe.helper.Checker;
import com.github.websafe.helper.Decoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.nullicorn.nedit.type.NBTCompound;

import java.io.IOException;
import java.util.Objects;

public class GetAuctionInfo {
    //declare Classes
    private static final GetInfo g = new GetInfo();
    private static final Checker check = new Checker();
    private static final Decoder decode = new Decoder();

    //declare url
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";


    //declare variables
    private final int pageCount = g.getResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();

    public void getAuction() throws IOException {
        for (int i = 0; i < pageCount; i++) { //for each page
            String pageNum = AHurl + "?page=" + i;
            JsonArray totalAuctions = g.getResponse(pageNum).getAsJsonArray("auctions");
            for (int j = 0; j < totalAuctions.size(); j++ ) { //for each auction
                    JsonObject auction = totalAuctions.get(j).getAsJsonObject();
                    if (check.isBin(auction).equals(Boolean.TRUE) && check.isSold(auction).equals(Boolean.TRUE)) {
                        NBTCompound ExtraAttr = decode.itemBytes(auction.getAsJsonPrimitive("item_bytes").getAsString());
                        String item_id = ExtraAttr.getString("id");
                    }
            }
        }
    }


}