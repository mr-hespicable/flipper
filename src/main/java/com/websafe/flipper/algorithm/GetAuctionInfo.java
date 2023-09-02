package com.websafe.flipper.algorithm;

import com.google.gson.JsonElement;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.websafe.flipper.apiProcessing.GetInfo;
import com.websafe.flipper.config.Config;
import com.websafe.flipper.helper.Checker;
import com.websafe.flipper.helper.Decoder;
import com.websafe.flipper.algorithm.GetValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.nullicorn.nedit.type.NBTCompound;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetAuctionInfo {
    //declare Classes
    private static final GetInfo g = new GetInfo();
    private static final Checker check = new Checker();
    private static final Decoder decode = new Decoder();
    private static final GetItemInfo info = new GetItemInfo();
    private static final GetValue value = new GetValue();
    private static final Config config = new Config();

    //declare url
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";

    //declare variables
    private final int pageCount = g.getResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();

    private JsonArray currentPage = new JsonArray();
    private final List<JsonObject> totalAuctions = new CopyOnWriteArrayList<>();

    public void getAuction() throws IOException, InterruptedException {
        System.out.println("start?");
        new Thread(() -> { //get auctions and add them to the totalAuctions array.

            System.out.println("starting.");
            for (int p = 0; p < pageCount; p++) {
                long startTime = System.currentTimeMillis();
                currentPage = g.getResponse(AHurl + "?page=" + p).getAsJsonArray("auctions");
                for (int i = 0; i < currentPage.size(); i++) {
                    System.out.println(p + ":" + i);
                    JsonObject auction = currentPage.get(i).getAsJsonObject();
                    if (check.isBin(auction).equals(Boolean.TRUE) && check.isSold(auction).equals(Boolean.FALSE) && checkConfig(auction)) {
                        totalAuctions.add(auction);
                    }
                }
                //System.out.println(new Date().getTime() - startTime);
            }
        }).start();

        //do things with the auctions
        Thread.sleep(500);
        System.out.println("done");
        for (JsonElement a : totalAuctions) {
            System.out.println("1");
            JsonObject auction = a.getAsJsonObject();
            NBTCompound nbtData = decode.itemBytes(auction.getAsJsonPrimitive("item_bytes").getAsString());
            System.out.println(value.Value(nbtData));
        }

        /*long startTime = System.currentTimeMillis();

        for (int i = 0; i < pageCount; i++) { //for each page
            String pageNum = AHurl + "?page=" + i;
            JsonArray totalAuctions = g.getResponse(pageNum).getAsJsonArray("auctions");
            for (int j = 0; j < totalAuctions.size(); j++ ) { //for each auction
                JsonObject auction = totalAuctions.get(j).getAsJsonObject();
                if (check.isBin(auction).equals(Boolean.TRUE) && check.isSold(auction).equals(Boolean.FALSE) && auction.getAsJsonPrimitive("starting_bid").getAsInt() <= config.getBudget()) {
                    NBTCompound nbtData = decode.itemBytes(auction.getAsJsonPrimitive("item_bytes").getAsString());
                    System.out.println(value.Value(nbtData));
                    System.out.println(i + ":" + j);
                }
            }
            System.out.println(new Date().getTime() - startTime);
        }
        System.out.println(new Date().getTime() - startTime);
        System.out.println("DONE WITH THAT SHIT.");*/
    }

    private Boolean checkConfig(JsonObject auction) {
        if (auction.getAsJsonPrimitive("starting_bid").getAsInt() <= config.getBudget()) { //TODO: ADD CONFIGS HERE
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    } //return truefalse if ahsettings are true

}










