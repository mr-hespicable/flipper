package com.websafe.flipper.algorithm;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.websafe.flipper.apiProcessing.GetInfo;
import me.nullicorn.nedit.type.NBTCompound;

import java.util.*;

public class GetValue {
    public static final GetItemInfo info = new GetItemInfo();
    public static final GetInfo response = new GetInfo();

    private Map<String, Integer> enchantmentPrices = new HashMap<>(); //TODO: use this with initEnchantments

    public Integer Value(NBTCompound nbtData) {
        info.ItemInfo(nbtData);
        int price = 0;

        //add recomb value
        if (info.getRecombValue() == 1) {
            System.out.println("recomb");
            price += getBZPrice("RECOMBOBULATOR_3000");
        }

        //add potato book value
        if (info.getHPBCount() > 0 && info.getHPBCount() <= 10) {
            System.out.println("hpb");
            price += getBZPrice("HOT_POTATO_BOOK") * (info.getHPBCount());
        } else if (info.getHPBCount() > 10) { //if fumings are present
            System.out.println("fpb");
            price += getBZPrice("FUMING_POTATO_BOOK") * (info.getHPBCount() - 10) + (getBZPrice("HOT_POTATO_BOOK") * 10);
        }

        //get reforge value:
        String[] normalReforges = {"epic", "fair", "fast", "gentle", "heroic", "legendary", "odd", "sharp", "spicy", "awkward", "deadly", "fine", "grand", "hasty", "neat", "rapid", "rich", "unreal", "clean", "fierce", "heavy", "light", "mythic", "pure", "titanic", "smart", "wise", "unyielding", "prospector", "excellent", "sturdy", "fortunate", "great", "rugged", "lush", "lumberjack", "double_bit", "robust", "zooming", "peasant", "green_thumb"};
        Map<String, String> reforge_stones = new HashMap<>(); //TODO: make method initReforges and add ^ and > to the method.

        //get price of enchants
        if (info.getEnchantments() != null){
            for (Map.Entry<String, Double> entry : info.getEnchantments().entrySet()) {
                String enchName = entry.getKey();
                Double enchValue = entry.getValue();
                String enchID = "ENCHANTMENT_" + enchName.toUpperCase() + "_" + enchValue.intValue();

                if (response.getResponse("https://api.hypixel.net/skyblock/bazaar").getAsJsonObject("products").has(enchID)) { //TODO: fetch bazaar enchantment products outside of the loop
                    int avgPrice = getBZPrice(enchID);
                    System.out.println("enchant");
                    price += avgPrice;
                } else {
                    System.out.println(enchID);
                }
            }
        }
        return price;
    }

    private Integer getBZPrice(String itemID) {
        final JsonObject products = response.getResponse("https://api.hypixel.net/skyblock/bazaar").getAsJsonObject("products");
        return products.getAsJsonObject(itemID).getAsJsonObject("quick_status").getAsJsonPrimitive("buyPrice").getAsInt();
    }

    private void initEnchantments() {
        response.getResponse("https://api.hypixel.net/skyblock/bazaar").getAsJsonObject("products");

    }
}
