package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;

import java.lang.reflect.Type;

public class ItemSettingMapper extends JsonMapper<ItemSetting<?>> {

    @Override
    public ItemSetting<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(ItemSetting<?> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.addProperty("key", src.getKey());
        //main.addProperty("value", String.valueOf(src.getValue())); - Server based
        main.addProperty("default_value", String.valueOf(src.getDefaultValue()));

        return main;
    }

}
