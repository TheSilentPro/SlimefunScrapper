package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalMapper extends JsonMapper<Optional<?>> {

    @Override
    public Optional<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException();
        /*
        JsonArray asJsonArray = json.getAsJsonArray();
        JsonElement jsonElement = asJsonArray.get(0);
        T value = context.deserialize(jsonElement, ((ParameterizedType) typeOfT).getActualTypeArguments()[0]);
        return Optional.fromNullable(value);
        */
    }

    @Override
    public JsonElement serialize(Optional<?> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject main = new JsonObject();
        main.add("value", context.serialize(src.orElse(null)));
        return main;
    }
}
