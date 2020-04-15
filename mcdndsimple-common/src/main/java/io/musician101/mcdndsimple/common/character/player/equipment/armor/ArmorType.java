package io.musician101.mcdndsimple.common.character.player.equipment.armor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

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

    public static class Serializer extends BaseSerializer<ArmorType> {

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
