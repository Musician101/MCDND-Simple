package io.musician101.mcdndsimple.common.character.player;

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
import java.util.function.BiFunction;
import java.util.stream.Stream;

@JsonKey(key = Keys.UNARMORED_BONUS, typeAdapter = UnarmoredBonus.Serializer.class)
public enum UnarmoredBonus {
    BARBARIAN("Barbarian"),
    DRACONIC_RESILIENCE("Draconic Resilience (Sorcerer Subclass)", (dexMod, secondMod) -> 13 + dexMod),
    MONK("Monk"),
    NONE("None", (dexMod, secondMod) -> 0);

    private final BiFunction<Integer, Integer, Integer> biFunction;
    private final String name;

    UnarmoredBonus(String name) {
        this(name, (dexMod, secondMod) -> 10 + dexMod + secondMod);
    }

    UnarmoredBonus(String name, BiFunction<Integer, Integer, Integer> biFunction) {
        this.name = name;
        this.biFunction = biFunction;
    }

    public int getArmorClass(int dexMod, int secondMod) {
        return biFunction.apply(dexMod, secondMod);
    }

    public String getName() {
        return name;
    }

    public static class Serializer implements JsonDeserializer<UnarmoredBonus>, JsonSerializer<UnarmoredBonus> {

        @Override
        public UnarmoredBonus deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(UnarmoredBonus.values()).filter(UnarmoredBonus -> UnarmoredBonus.getName().equals(json.getAsString())).findFirst().orElse(UnarmoredBonus.NONE);
        }

        @Override
        public JsonElement serialize(UnarmoredBonus src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
