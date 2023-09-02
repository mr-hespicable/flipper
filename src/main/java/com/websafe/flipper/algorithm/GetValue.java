package com.websafe.flipper.algorithm;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.websafe.flipper.apiProcessing.GetInfo;
import me.nullicorn.nedit.type.NBTCompound;

import java.util.*;

public class GetValue {
    public static final GetItemInfo info = new GetItemInfo();
    public static final GetInfo response = new GetInfo();

    private final JsonObject enchants = new JsonObject(); //TODO: use this with initEnchantments
    private JsonObject bazaarProducts = new JsonObject();

    public Integer Value(NBTCompound nbtData) {

        initBZ();
        initEnchants();

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
        Map<String, String> reforge_stones = new HashMap<>(); //TODO: make method initReforges and add ^ and < to the method.

        //get price of enchants
        if (info.getEnchantments() != null){
            for (Map.Entry<String, Double> map: info.getEnchantments().entrySet()) {

                String itemEnchName = map.getKey().toUpperCase();
                int itemEnchLvl = map.getValue().intValue();
                String enchID = "ENCHANTMENT" + itemEnchName + "_" + itemEnchLvl;
                if (enchID.equals("ENCHANTMENT_TELEKINESIS_1")) continue;
                price += enchants.getAsJsonObject(enchID).getAsJsonObject("quick_status").get("buyPrice").getAsInt();
            }
        }
        return price;
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
