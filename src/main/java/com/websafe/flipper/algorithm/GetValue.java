package com.websafe.flipper.algorithm;

import com.google.gson.JsonObject;
import com.websafe.flipper.apiProcessing.GetInfo;
import me.nullicorn.nedit.type.NBTCompound;

public class GetValue {
    public static final GetItemInfo info = new GetItemInfo();
    public static final GetInfo response = new GetInfo();

    public void Value(NBTCompound nbtData) {
        info.ItemInfo(nbtData);
        JsonObject sponse = response.getResponse("https://api.hypixel.net/skyblock/bazaar");

        if (info.getEnchantments() != null) {
            for (int i = 0; i <= info.getEnchantments().entrySet().size() - 1; i++) {
                //look enchant up on bazaar
                Object o = info.getEnchantments().entrySet().toArray()[i];
            }
        }


    }
}
