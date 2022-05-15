package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;

import java.lang.reflect.Type;

public class SimpleSlimefunItemMapper extends JsonMapper<SimpleSlimefunItem<?>> {
    @Override
    public SimpleSlimefunItem<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(SimpleSlimefunItem<?> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.addProperty("id", src.getId());
        main.addProperty("name", src.getItemName());

        return main;
    }
}
