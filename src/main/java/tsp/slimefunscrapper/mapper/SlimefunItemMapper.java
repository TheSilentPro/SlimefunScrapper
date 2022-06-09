package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;
import java.util.Set;

public class SlimefunItemMapper extends JsonMapper<SlimefunItem> {

    @Override
    public SlimefunItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(SlimefunItem src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        // Basic Information
        main.addProperty("id", src.getId());
        main.addProperty("name", src.getItemName());
        //main.addProperty("state", src.getState().name());
        src.getWikipage().ifPresent(wiki -> main.addProperty("wiki", wiki));

        // Extra Info
        main.addProperty("enchantable", src.isEnchantable());
        main.addProperty("disenchantable", src.isDisenchantable());
        main.addProperty("workbench", src.isUseableInWorkbench());
        main.addProperty("placeable", !(src instanceof NotPlaceable));

        // RecipeType
        main.add("recipe_type", context.serialize(src.getRecipeType(), RecipeType.class));

        // Research
        if (src.getResearch() != null) {
            main.add("research", context.serialize(src.getResearch(), Research.class));
        }

        // ItemSettings
        Set<ItemSetting<?>> settings = src.getItemSettings();
        if (!settings.isEmpty()) {
            JsonArray json = new JsonArray();
            for (ItemSetting<?> setting : settings) {
                json.add(context.serialize(setting, ItemSetting.class));
            }

            main.add("settings", json);
        }

        // ItemGroup
        main.add("group", context.serialize(src.getItemGroup(), ItemGroup.class));

        // Addon
        main.add("addon", context.serialize(src.getAddon(), SlimefunAddon.class));

        // Recipe
        JsonObject recipe = new JsonObject();
        JsonArray ingredients = new JsonArray();
        ItemStack[] recipeItems = src.getRecipe();
        for (ItemStack item : recipeItems) {
            ingredients.add(context.serialize(item, ItemStack.class));
        }
        recipe.add("ingredients", ingredients);
        recipe.add("output", context.serialize(src.getRecipeOutput(), ItemStack.class));
        main.add("recipe", recipe);

        // Radioactivity
        if (src instanceof Radioactive radioactive) {
            JsonObject radioactivity = new JsonObject();
            radioactivity.addProperty("name", radioactive.getRadioactivity().name());
            radioactivity.addProperty("level", radioactive.getRadioactivity().getRadiationLevel());

            main.add("radioactivity", radioactivity);
        }

        // Energy
        if (src instanceof EnergyNetComponent component) {
            JsonObject json = new JsonObject();
            json.addProperty("type", component.getEnergyComponentType().name());
            json.addProperty("capacity", component.getCapacity());
            json.addProperty("rechargeable", component.isChargeable());

            main.add("energy", json);
        }

        // Protective Armor
        if (src instanceof ProtectiveArmor armor) {
            JsonObject json = new JsonObject();
            json.addProperty("full_required", armor.isFullSetRequired());

            if (armor.getArmorSetId() != null) {
                json.addProperty("key", armor.getArmorSetId().toString());
            }

            JsonArray types = new JsonArray();
            for (ProtectionType type : armor.getProtectionTypes()) {
                types.add(type.name());
            }

            json.add("protection_types", types);
            main.add("protection", json);
        }

        return main;
    }

}
