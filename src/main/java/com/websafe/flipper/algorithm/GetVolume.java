package com.websafe.flipper.algorithm;

import com.websafe.flipper.apiProcessing.GetInfo;
import com.google.gson.JsonArray;

public class GetVolume {
    private final GetInfo g = new GetInfo();
    public void getVol(String item_id) {
        String SOLDurl = "https://sky.coflnet.com/api/auctions/tag/" + item_id + "/sold";
        JsonArray obj = g.getResponse(SOLDurl).getAsJsonArray();

        for (int i = 0; i <= obj.size(); i++) {
            //TODO: add way to find items that match specific descriptors
        }
    }
}
