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

    @Nonnull
    private final String name;

    SpellType(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public static class Serializer extends BaseSerializer<SpellType> {

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
