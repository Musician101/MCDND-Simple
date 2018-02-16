package io.musician101.mcdndsimple.common.character.player.equipment.armor;

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

@JsonKey(key = Keys.ARMOR_TYPE, typeAdapter = ArmorType.Serializer.class)
public enum ArmorType {
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy"),
    SHIELD("Shield"),
    NONE("None");

    @Nonnull
    private final String name;

    ArmorType(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public static class Serializer implements JsonDeserializer<ArmorType>, JsonSerializer<ArmorType> {

        @Override
        public ArmorType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(ArmorType.values()).filter(armorType -> armorType.getName().equals(json.getAsString())).findFirst().orElse(ArmorType.NONE);
        }

        @Override
        public JsonElement serialize(ArmorType src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
