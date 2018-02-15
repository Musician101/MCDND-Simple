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

@JsonKey(key = Keys.SPELL_TYPE, typeAdapter = SpellType.Serializer.class)
public enum SpellType {
    ABJURATION("Abjuration"),
    CONJURATION("Conjuration"),
    DIVINATION("Divination"),
    ENCHANTMENT("Enchantment"),
    EVOCATION("Evocation"),
    ILLUSION("Illusion"),
    NECROMANCY("Necromancy"),
    OTHER("Other"),
    TRANSMUTATION("Transmutation");

    private final String name;

    SpellType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Serializer implements JsonDeserializer<SpellType>, JsonSerializer<SpellType> {

        @Override
        public SpellType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(SpellType.values()).filter(spellType -> spellType.getName().equals(json.getAsString())).findFirst().orElse(SpellType.OTHER);
        }

        @Override
        public JsonElement serialize(SpellType src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
