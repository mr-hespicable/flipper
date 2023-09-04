package com.websafe.flipper.algorithm;

import com.google.gson.JsonElement;
import com.websafe.flipper.api_processing.GetInfo;
import com.websafe.flipper.config.Config;
import com.websafe.flipper.helper.Checker;
import com.websafe.flipper.helper.Decoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.nullicorn.nedit.type.NBTCompound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class GetAuctionInfo {
    //declare Classes
    private static final GetInfo g = new GetInfo();
    private static final Checker check = new Checker();
    private static final Decoder decode = new Decoder();
    private static final GetValue value = new GetValue();
    private static final Config config = new Config();

    //declare url
    private final String AHurl = "https://api.hypixel.net/skyblock/auctions";
    private final String BZurl = "https://api.hypixel.net/skyblock/bazaar";

    //declare variables
    private final int pageCount = g.getResponse(AHurl).getAsJsonPrimitive( "totalPages").getAsInt();
    List<Thread> threads = new ArrayList<>();

    private JsonArray currentPage = new JsonArray();
    private final JsonArray totalAuctions = new JsonArray();

    public void getAuction() throws IOException, InterruptedException { //api is fetched pageCount+1, threads run all at once
        System.out.println("starting");
        AtomicLong startTime = new AtomicLong(System.currentTimeMillis());
        for (int p = 0; p < pageCount; p++) {
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
            threads.add(auctions);
            auctions.start();

            for (Thread thread : threads) {
                thread.join();
            }
        }
        System.out.println(System.currentTimeMillis() - startTime.get());

        //do things with the auctions
        Thread.sleep(500);

        ExecutorService threadPool = new ThreadPoolExecutor(
                10, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        for (JsonElement a : totalAuctions) {
            threadPool.submit(() -> {
                startTime.set(System.currentTimeMillis());
                JsonObject auction = a.getAsJsonObject();
                NBTCompound nbtData = decode.itemBytes(auction.getAsJsonPrimitive("item_bytes").getAsString());
                System.out.println(value.Value(nbtData));
                System.out.println(System.currentTimeMillis() - startTime.get());
            });

        }
    }

    private Boolean checkConfig(JsonObject auction) {
        if (auction.getAsJsonPrimitive("starting_bid").getAsInt() <= config.getBudget()) { //TODO: ADD CONFIGS HERE
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    } //return true/false if ahsettings are true/false

}










