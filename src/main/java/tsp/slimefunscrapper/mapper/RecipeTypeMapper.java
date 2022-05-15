package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import java.lang.reflect.Type;

public class RecipeTypeMapper extends JsonMapper<RecipeType> {

    @Override
    public RecipeType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonElement serialize(RecipeType src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.addProperty("key", src.getKey().toString());
        //main.add("machine", context.serialize(src.getMachine(), SlimefunItem.class));
        return main;
    }

}
