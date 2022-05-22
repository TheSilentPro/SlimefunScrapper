package tsp.slimefunscrapper.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.inventory.ItemStack;
import tsp.slimefunscrapper.mapper.AddonMapper;
import tsp.slimefunscrapper.mapper.ItemGroupMapper;
import tsp.slimefunscrapper.mapper.ItemSettingMapper;
import tsp.slimefunscrapper.mapper.ItemStackMapper;
import tsp.slimefunscrapper.mapper.OptionalMapper;
import tsp.slimefunscrapper.mapper.RecipeTypeMapper;
import tsp.slimefunscrapper.mapper.ResearchMapper;
import tsp.slimefunscrapper.mapper.SimpleSlimefunItemMapper;
import tsp.slimefunscrapper.mapper.SlimefunItemMapper;
import tsp.slimefunscrapper.mapper.SlimefunItemStackMapper;

import java.util.Optional;

public class JsonUtils {

    public static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Optional.class, new OptionalMapper())
            .registerTypeAdapter(SlimefunAddon.class, new AddonMapper())
            .registerTypeAdapter(ItemGroup.class, new ItemGroupMapper())
            .registerTypeAdapter(ItemSetting.class, new ItemSettingMapper())
            .registerTypeAdapter(ItemStack.class, new ItemStackMapper())
            .registerTypeAdapter(RecipeType.class, new RecipeTypeMapper())
            .registerTypeAdapter(Research.class, new ResearchMapper())
            .registerTypeAdapter(SimpleSlimefunItem.class, new SimpleSlimefunItemMapper())
            .registerTypeAdapter(SlimefunItem.class, new SlimefunItemMapper())
            .registerTypeAdapter(SlimefunItemStack.class, new SlimefunItemStackMapper())
            .create();
    
}
