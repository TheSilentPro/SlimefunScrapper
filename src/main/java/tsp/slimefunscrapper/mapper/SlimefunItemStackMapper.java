package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import java.lang.reflect.Type;

public class SlimefunItemStackMapper extends JsonMapper<SlimefunItemStack> {

    @Override
    public SlimefunItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(SlimefunItemStack src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.addProperty("id", src.getItemId());
        main.addProperty("material", src.getType().name());
        if (src.getDisplayName() != null) {
            main.addProperty("name", src.getDisplayName());
        }
        src.getSkullTexture().ifPresent(texture -> main.addProperty("texture", texture));
        // main.add("item", context.serialize(src.getItem(), SlimefunItem.class));

        return main;
    }

}
