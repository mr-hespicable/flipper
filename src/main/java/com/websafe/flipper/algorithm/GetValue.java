package com.websafe.flipper.algorithm;

import com.google.gson.JsonObject;
import com.websafe.flipper.apiProcessing.GetInfo;
import me.nullicorn.nedit.type.NBTCompound;

import java.util.*;

public class GetValue {
    public static final GetItemInfo info = new GetItemInfo();
    public static final GetInfo response = new GetInfo();

    public void Value(NBTCompound nbtData) {
        info.ItemInfo(nbtData);
        int price = 0;

        //add recomb value
        if (info.getRecombValue() == 1) {
            price += getBZPrice("RECOMBOBULATOR_3000");
        }

        //add potato book value
        if (info.getHPBCount() > 0 && info.getHPBCount() <= 10) {
            price += getBZPrice("HOT_POTATO_BOOK") * (info.getHPBCount());
        } else if (info.getHPBCount() > 10) { //if fumings are present
            price += getBZPrice("FUMING_POTATO_BOOK") * (info.getHPBCount() - 10) + (getBZPrice("HOT_POTATO_BOOK") * 10);
        }

        //get reforge value:
        String[] normalReforges = {"epic", "fair", "fast", "gentle", "heroic", "legendary", "odd", "sharp", "spicy", "awkward", "deadly", "fine", "grand", "hasty", "neat", "rapid", "rich", "unreal", "clean", "fierce", "heavy", "light", "mythic", "pure", "titanic", "smart", "wise", "unyielding", "prospector", "excellent", "sturdy", "fortunate", "great", "rugged", "lush", "lumberjack", "double_bit", "robust", "zooming", "peasant", "green_thumb"};
        Map<String, String> reforge_stones = new HashMap<>(); //TODO: make reforge stone price map

        //get price of enchants
        if (info.getEnchantments() != null){


            for (Map.Entry<String, Double> entry: info.getEnchantments().entrySet()) {
                String enchName = entry.getKey();
                Double enchValue = entry.getValue();
                String enchID = "ENCHANTMENT_" + enchName.toUpperCase() + "_" + enchValue.intValue();

                int avgPrice = getBZPrice(enchID);
                price += avgPrice;

            }
        }


    }

    private Integer getBZPrice(String itemID) {
        final JsonObject products = response.getResponse("https://api.hypixel.net/skyblock/bazaar").getAsJsonObject("products");
        return products.getAsJsonObject(itemID).getAsJsonObject("quick_status").getAsJsonPrimitive("buyPrice").getAsInt();

    }
}
