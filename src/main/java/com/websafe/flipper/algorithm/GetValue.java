package com.websafe.flipper.algorithm;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.websafe.flipper.api_processing.GetInfo;
import me.nullicorn.nedit.type.NBTCompound;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GetValue {
    public static final GetItemInfo info = new GetItemInfo();
    public static final GetInfo response = new GetInfo();

    private final JsonObject enchants = new JsonObject(); //TODO: use this with initEnchantments
    private final JsonObject reforge_stones = new JsonObject();

    private JsonObject bazaarProducts = new JsonObject();
    public int Value(NBTCompound nbtData) {
        initBZ();

        initEnchants();
        initReforges();

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
        new Thread(() -> {
            info.getReforge();
            price.addAndGet(1);
        });

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
    private void initReforges() {
        Map<String, String> stoneMap = new HashMap<String, String>() {{
            put("CANDY_CORN", "candied");
            put("DEEP_SEA_ORB", "submerged");
            put("RARE_DIAMOND", "reinforced");
            put("MOLTEN_CUBE", "cubic");
            put("ENDSTONE_GEODE", "hyper");
            put("PREMIUM_FLESH", "undead");
            put("RED_NOSE", "ridiculous");
            put("NECROMANCER_BROOCH", "necrotic");
            put("DRAGON_SCALE", "spiked");
            put("JADERALD", "jaded");
            put("RED_SCARF", "loving");
            put("DIAMOND_ATOM", "perfect");
            put("DRAGON_HORN", "renowned");
            put("GIANT_TOOTH", "giant");
            put("SADAN_BROOCH", "empowered");
            put("PRECURSOR_GEAR", "ancient");
            put("SKYMART_BROCHURE", "bustling");
            put("OVERGROWN_GRASS", "mossy");
            put("MOIL_LOG", "moil");
            put("BLESSED_FRUIT", "blessed");
            put("TOIL_LOG", "toil");
            put("GOLDEN_BALL", "bountiful");
            put("LARGE_WALNUT", "earthy");
            put("SALMON_OPAL", "headstrong");
            put("OPTICAL_LENS", "precise");
            put("SPIRIT_DECOY", "spiritual");
            put("ONYX", "fruitful");
            put("LAPIS_CRYSTAL", "magnetic");
            put("DIAMONITE", "fleet");
            put("PURE_MITHRIL", "mithraic");
            put("ROCK_GEMSTONE", "auspicious");
            put("REFINED_AMBER", "refined");
            put("PETRIFIED_STARFALL", "stellar");
            put("HOT_STUFF", "heated");
            put("AMBER_MATERIAL", "ambered");
            put("JERRY_STONE", "jerry");
            put("DIRT_BOTTLE", "dirty");
            put("DRAGON_CLAW", "fabled");
            put("SUSPICIOUS_VIAL", "suspicious");
            put("MIDAS_JEWEL", "gilded");
            put("AOTE_STONE", "warped");
            put("WITHER_BLOOD", "withered");
            put("BULKY_STONE", "bulky");
            put("ENTROPY_SUPPRESSOR", "coldfused");
            put("SALT_CUBE", "salty");
            put("RUSTY_ANCHOR", "treacherous");
            put("HARDENED_WOOD", "stiff");
            put("PITCHIN_KOI", "pitchin");
            put("LUCKY_DICE", "lucky");
            put("KUUDRA_MANDIBLE", "chomp");
            put("BLAZE_WAX", "waxed");
            put("METEOR_SHARD", "fortified");
            put("SEARING_STONE", "strengthened");
            put("SHINY_PRISM", "glistening");
            put("FLOWERING_BOUQUET", "blooming");
            put("BURROWING_SPORES", "rooted");

        }}; //keys are bz ids, values are modifier: values.
        for (Map.Entry<String, String> entry : stoneMap.entrySet()) {
            reforge_stones.addProperty(entry.getKey(), entry.getValue());
        }
    }

    private Integer getBZPrice(String itemID) {
        return bazaarProducts.getAsJsonObject(itemID).getAsJsonObject("quick_status").getAsJsonPrimitive("sellPrice").getAsInt();
    }
}
