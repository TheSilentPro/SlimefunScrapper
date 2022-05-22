package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

import java.lang.reflect.Type;

public class ItemStackMapper extends JsonMapper<ItemStack> {

    private final boolean compact;

    public ItemStackMapper(boolean compact) {
        this.compact = compact;
    }

    public ItemStackMapper() {
        this(true);
    }

    public boolean isCompact() {
        return compact;
    }

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("deprecation")
    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.addProperty("type", src.getType().name());
        main.addProperty("amount", src.getAmount());

        ItemMeta im = src.getItemMeta();
        if (im != null && src.hasItemMeta()) {
            JsonObject meta = new JsonObject();
            meta.addProperty("display_name", im.getDisplayName());

            // Lore
            if (im.getLore() != null) {
                JsonArray lore = new JsonArray();
                for (String line : im.getLore()) {
                    lore.add(line);
                }

                meta.add("lore", lore);
            }

            if (im instanceof PotionMeta) {
                JsonObject pm = new JsonObject();
                PotionData data = ((PotionMeta) im).getBasePotionData();
                pm.addProperty("type", data.getType().name());
                pm.addProperty("extended", data.isExtended());
                pm.addProperty("upgraded", data.isUpgraded());

                meta.add("base_potion_data", pm);
            }

            // Detailed
            if (!compact) {
                meta.addProperty("localized_name", im.getLocalizedName());
                meta.addProperty("unbreakable", im.isUnbreakable());

                // Flags
                if (!im.getItemFlags().isEmpty()) {
                    JsonArray flags = new JsonArray();
                    for (ItemFlag flag : im.getItemFlags()) {
                        flags.add(flag.name());
                    }

                    meta.add("flags", flags);
                }

                // Enchantments
                if (im.hasEnchants()) {
                    JsonArray enchantments = new JsonArray();
                    im.getEnchants().forEach(((enchantment, level) -> {
                        JsonObject jsonEnchantment = new JsonObject();
                        jsonEnchantment.addProperty("key", enchantment.getKey().toString());
                        jsonEnchantment.addProperty("name", enchantment.getName());
                        jsonEnchantment.addProperty("level", level);
                        jsonEnchantment.addProperty("target", enchantment.getItemTarget().name());
                        jsonEnchantment.addProperty("start_level", enchantment.getStartLevel());
                        jsonEnchantment.addProperty("max_level", enchantment.getMaxLevel());

                        enchantments.add(jsonEnchantment);
                    }));

                    meta.add("enchantments", enchantments);
                }
            }

            main.add("meta", meta);
        }

        return main;
    }

}
