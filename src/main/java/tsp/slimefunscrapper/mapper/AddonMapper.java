package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;

import java.lang.reflect.Type;

public class AddonMapper extends JsonMapper<SlimefunAddon> {

    @Override
    public SlimefunAddon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(SlimefunAddon src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.addProperty("name", src.getName());
        main.addProperty("version", src.getPluginVersion());
        main.addProperty("bug_tracker", src.getBugTrackerURL());
        return main;
    }

}
