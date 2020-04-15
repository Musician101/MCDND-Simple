package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum StatBonus {

    CHA,
    CON,
    DEX,
    INT,
    NONE("None"),
    STR,
    WIS;

    @Nullable
    private final String name;

    StatBonus() {
        this(null);
    }

    StatBonus(@Nullable String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name == null ? toString() : name;
    }

    public static class Serializer extends BaseSerializer<StatBonus> {

        @Override
        public StatBonus deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(StatBonus.values()).filter(statBonus -> statBonus.getName().equals(json.getAsString())).findFirst().orElse(StatBonus.NONE);
        }

        @Override
        public JsonElement serialize(StatBonus src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
