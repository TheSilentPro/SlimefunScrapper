package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
        main.addProperty("name", fromRecipeType(src));
        //main.add("machine", context.serialize(src.getMachine(), SlimefunItem.class));
        return main;
    }

    /**
     * Attempt to retrieve the name of the {@link RecipeType} constant using reflection.
     *
     * @param type The type to check
     * @return The name, else null.
     */
    private String fromRecipeType(RecipeType type) {
        for (Field field : RecipeType.class.getDeclaredFields()) {
            int mod = field.getModifiers();
            if (Modifier.isPublic(mod) && Modifier.isStatic(mod) && Modifier.isFinal(mod)) {
                try {
                    if (field.get(null).equals(type)) {
                        return field.getName();
                    }
                } catch (IllegalAccessException ignored) {}
            }
        }

        return null;
    }

}
