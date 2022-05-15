package tsp.slimefunscrapper.mapper;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

public abstract class JsonMapper<T> implements JsonSerializer<T>, JsonDeserializer<T> {}