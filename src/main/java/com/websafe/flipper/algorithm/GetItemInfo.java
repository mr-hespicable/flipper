package com.websafe.flipper.algorithm;

import me.nullicorn.nedit.type.NBTCompound;

import java.util.Map;

public class GetItemInfo {
    Integer isRecombed;
    Integer isHPBed;
    String isReforged;
    Integer isStarred;
    String itemID;
    Map isEnchanted;

    public void ItemInfo(NBTCompound nbtInfo) {
        //TODO: this class is basically just going to be a better layout of ExtraAttributes in item_bytes
        NBTCompound attr = nbtInfo.getCompound("tag").getCompound("ExtraAttributes"); //ExtraAttributes tag
        isRecombed = attr.getInt("rarity_upgrades", 0);
        isHPBed = attr.getInt("hot_potato_count", 0); //15 is fully maxed
        isReforged = attr.getString("modifier", null); //TODO: add null exception if item is not reforged
        isStarred = attr.getInt("upgrade_level", 0);
        itemID = attr.getString("id");
        isEnchanted = attr.getCompound("enchantments"); //TODO: add null exception if item is not enchanted && make it a MAP.
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

    public Map getEnchantments() {
        return isEnchanted;
    }
}
