package com.websafe.flipper.algorithm;

import com.google.gson.JsonElement;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.websafe.flipper.DebugTimer;
import com.websafe.flipper.apiProcessing.GetInfo;
import com.websafe.flipper.config.Config;
import com.websafe.flipper.helper.Checker;
import com.websafe.flipper.helper.Decoder;
import com.websafe.flipper.algorithm.GetValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.nullicorn.nedit.type.NBTCompound;

import java.io.IOException;
import java.util.ArrayList;
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
    private static final DebugTimer timer = new DebugTimer();

    //declare url
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";

    //declare variables
    private final int pageCount = g.getResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();
    List<Thread> threads = new ArrayList<>();

    private JsonArray currentPage = new JsonArray();
    private final JsonArray totalAuctions = new JsonArray();

    public void getAuction() throws IOException, InterruptedException { //api is called pageCount+1, threads run at once
        System.out.println("starting");
        for (int p = 0; p < pageCount; p++) {
            timer.Start();
            currentPage = g.getResponse(AHurl + "?page=" + p).getAsJsonArray("auctions");
            Thread auctions = new Thread(() -> {
                for (int i = 0; i < currentPage.size(); i++) {
                    //System.out.println(p + ":" + i);
                    JsonObject auction = currentPage.get(i).getAsJsonObject();
                    if (check.isBin(auction).equals(Boolean.TRUE) && check.isSold(auction).equals(Boolean.FALSE) && checkConfig(auction)) {
                        synchronized (totalAuctions) {
                            totalAuctions.add(auction);
                        }
                    }
                }
            });
            System.out.println(timer.Stop());
            threads.add(auctions);
            auctions.start();

            for (Thread thread : threads) {
                thread.join();
            }
        }

        //do things with the auctions
        Thread.sleep(500);
        timer.Start();
        for (JsonElement a : totalAuctions) {
            JsonObject auction = a.getAsJsonObject();
            NBTCompound nbtData = decode.itemBytes(auction.getAsJsonPrimitive("item_bytes").getAsString());
            System.out.println(value.Value(nbtData));
        }
        System.out.println(timer.Stop());

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
    } //return true/false if ahsettings are true/false


}










