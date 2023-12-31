package com.websafe.flipper.algorithm;

import com.websafe.flipper.helper.Parser;

import com.google.gson.Gson;
import me.nullicorn.nedit.type.NBTCompound;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetItemInfo {
    private static Integer isRecombed;
    private static Integer isHPBed;
    private static String isReforged;
    private static Integer isStarred;
    private static String itemID;
    private static Map<String, Double> isEnchanted = new HashMap<>();

    private static final Gson gson = new Gson();
    private static final Parser dec = new Parser();

    public void ItemInfo(NBTCompound nbtInfo) {
        NBTCompound attr = nbtInfo.getCompound("tag").getCompound("ExtraAttributes"); //ExtraAttributes tag
        isRecombed = attr.getInt("rarity_upgrades", 0); //recombs
        isHPBed = attr.getInt("hot_potato_count", 0); //hpbs, 15 is fully maxed
        isReforged = attr.getString("modifier", null);//reforge
        isStarred = attr.getInt("upgrade_level", 0); //stars

        try {
            isEnchanted = gson.fromJson(attr.getCompound("enchantments").toString(), HashMap.class);
        } catch (NullPointerException e) {
            isEnchanted = null;
        }

        itemID = attr.getString("id");
        if (Objects.equals(itemID, "PET")) {
            itemID = "PET_" + (dec.decode(attr.getString("petInfo")).getAsJsonObject().get("type").getAsString());
        }
    }

    public Integer getRecombValue() {
        return isRecombed;
    }

    public Integer getHPBCount() {
        return isHPBed;
    }

    public String getReforge() {
        return isReforged;
    }

    public Integer getStarCount() {
        return isStarred;
    }

    public String getItemID() {
        return itemID;
    }

    public Map<String, Double> getEnchantments() {
        return isEnchanted;
    }
}
