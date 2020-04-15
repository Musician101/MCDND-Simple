package io.musician101.mcdndsimple.common.character.player.clazz;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

public enum GainedFrom {

    BARBARIAN("Barbarian"),
    BARD("Bard"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    FEAT("Feat"),
    FIGHTER("Fighter"),
    MONK("Monk"),
    PALADIN("Paladin"),
    OTHER("Other"),
    RANGER("Ranger"),
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    WARLOCK("Warlock"),
    WIZARD("Wizard");

    @Nonnull
    private final String name;

    GainedFrom(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public static class Serializer extends BaseSerializer<GainedFrom> {

        @Override
        public GainedFrom deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(GainedFrom.values()).filter(gainedFrom -> gainedFrom.getName().equals(json.getAsString())).findFirst().orElse(GainedFrom.OTHER);
        }

        @Override
        public JsonElement serialize(GainedFrom src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
