package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

import java.lang.reflect.Type;

public class ItemGroupMapper extends JsonMapper<ItemGroup> {

    @Override
    public ItemGroup deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(ItemGroup src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.addProperty("key", src.getKey().toString());
        main.addProperty("name", src.getUnlocalizedName());
        main.addProperty("tier", src.getTier());
        main.addProperty("registered", src.isRegistered());
        main.add("addon", context.serialize(src.getAddon(), SlimefunAddon.class));

        /*
        // Items
        JsonArray items = new JsonArray();
        for (SlimefunItem item : src.getItems()) {
            items.add(context.serialize(item, SlimefunItem.class));
        }
        main.add("items", items);
        */

        return main;
    }

}