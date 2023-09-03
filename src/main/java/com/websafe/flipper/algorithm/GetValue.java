package com.websafe.flipper.algorithm;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.websafe.flipper.apiProcessing.GetInfo;
import me.nullicorn.nedit.type.NBTCompound;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GetValue {
    public static final GetItemInfo info = new GetItemInfo();
    public static final GetInfo response = new GetInfo();

    private final JsonObject enchants = new JsonObject(); //TODO: use this with initEnchantments
    private JsonObject bazaarProducts = new JsonObject();
    public int Value(NBTCompound nbtData) {
        initBZ();
        initEnchants();

        info.ItemInfo(nbtData);
        AtomicInteger price = new AtomicInteger();

        //add recomb value
        new Thread(() -> {
            if (info.getRecombValue() == 1) {
                //System.out.println("recomb");
                price.addAndGet(getBZPrice("RECOMBOBULATOR_3000"));
            }
        });

        //add potato book value
        new Thread(() -> {
            if (info.getHPBCount() > 0 && info.getHPBCount() <= 10) {
                //System.out.println("hpb");
                price.addAndGet(getBZPrice("HOT_POTATO_BOOK") * (info.getHPBCount()));
            } else if (info.getHPBCount() > 10) { //if fumings are present
                //System.out.println("fpb");
                price.addAndGet(getBZPrice("FUMING_POTATO_BOOK") * (info.getHPBCount() - 10) + (getBZPrice("HOT_POTATO_BOOK") * 10));
            }
        });
        //get reforge value:
        String[] normalReforges = {"epic", "fair", "fast", "gentle", "heroic", "legendary", "odd", "sharp", "spicy", "awkward", "deadly", "fine", "grand", "hasty", "neat", "rapid", "rich", "unreal", "clean", "fierce", "heavy", "light", "mythic", "pure", "titanic", "smart", "wise", "unyielding", "prospector", "excellent", "sturdy", "fortunate", "great", "rugged", "lush", "lumberjack", "double_bit", "robust", "zooming", "peasant", "green_thumb"};
        Map<String, String> reforge_stones = new HashMap<>(); //TODO: make method initReforges and add ^ and < to the method.

        //get price of enchants

        new Thread(() -> {
            if (info.getEnchantments() != null) {
                for (Map.Entry<String, Double> map: info.getEnchantments().entrySet()) {
                    String itemEnchName = map.getKey().toUpperCase();
                    if (itemEnchName.equals("TELEKINESIS") || itemEnchName.equals("RAINBOW")) continue;
                    int itemEnchLvl = map.getValue().intValue();
                    String enchID = "ENCHANTMENT_" + itemEnchName + "_" + itemEnchLvl;
                    //System.out.println(enchID);
                    price.addAndGet(enchants.getAsJsonObject(enchID).getAsJsonObject("quick_status").get("buyPrice").getAsInt());
                }
            }
        });

        return price.get();
    }

    private Integer getBZPrice(String itemID) {
        return bazaarProducts.getAsJsonObject(itemID).getAsJsonObject("quick_status").getAsJsonPrimitive("buyPrice").getAsInt();
    }
    private void initBZ() {
        bazaarProducts = response.getResponse("https://api.hypixel.net/skyblock/bazaar").getAsJsonObject("products");
    }
    private void initEnchants() {
        for (Map.Entry<String, JsonElement> enchant : bazaarProducts.entrySet()) {
            if (enchant.getKey().contains("ENCHANTMENT_")) {
                String enchName = enchant.getKey();
                JsonElement enchValue = enchant.getValue();
                enchants.add(enchName, enchValue);
            }
        }
    }
}
