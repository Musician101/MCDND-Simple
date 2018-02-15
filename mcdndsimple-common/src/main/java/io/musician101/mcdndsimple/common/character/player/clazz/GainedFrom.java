package io.musician101.mcdndsimple.common.character.player.clazz;

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

@JsonKey(key = Keys.GAINED_FROM, typeAdapter = GainedFrom.Serializer.class)
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

    private final String name;

    GainedFrom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Serializer implements JsonDeserializer<GainedFrom>, JsonSerializer<GainedFrom> {

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
