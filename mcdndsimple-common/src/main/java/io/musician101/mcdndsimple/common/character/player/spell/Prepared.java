package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.PREPARED, typeAdapter = Prepared.Serializer.class)
public enum Prepared {

    ALWAYS("Always"),
    YES("Yes"),
    NO("No");

    @Nonnull
    private final String name;

    Prepared(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public boolean value() {
        return this != NO;
    }

    public static class Serializer implements JsonDeserializer<Prepared>, JsonSerializer<Prepared> {

        @Override
        public Prepared deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(Prepared.values()).filter(prepared -> prepared.getName().equals(json.getAsString())).findFirst().orElse(Prepared.NO);
        }

        @Override
        public JsonElement serialize(Prepared src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
