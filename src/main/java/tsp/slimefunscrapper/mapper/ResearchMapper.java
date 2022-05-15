package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;

import java.lang.reflect.Type;

public class ResearchMapper extends JsonMapper<Research> {

    @Override
    public Research deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(Research src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        if (src != null) {
            json.addProperty("key", src.getKey().toString());
            json.addProperty("id", src.getID());
            json.addProperty("name", src.getUnlocalizedName());
            json.addProperty("cost", src.getCost());

            /*
            // Affected Items
            JsonArray affected = new JsonArray();
            for (SlimefunItem item : src.getAffectedItems()) {
                affected.add(context.serialize(item, SlimefunItem.class));
            }

            json.add("affected_items", affected);
            */
        }

        return json;
    }

}
