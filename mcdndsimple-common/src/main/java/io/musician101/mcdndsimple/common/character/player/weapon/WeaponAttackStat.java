package io.musician101.mcdndsimple.common.character.player.weapon;

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

@JsonKey(key = Keys.ATTACK_STAT, typeAdapter = WeaponAttackStat.Serializer.class)
public enum WeaponAttackStat {
    CHA,
    CON,
    DEX,
    FINESSE("Finesse"),
    INT,
    STR,
    WIS;

    private final String name;

    WeaponAttackStat() {
        this(null);
    }

    WeaponAttackStat(String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? toString() : name;
    }

    public static class Serializer implements JsonDeserializer<WeaponAttackStat>, JsonSerializer<WeaponAttackStat> {

        @Override
        public WeaponAttackStat deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(WeaponAttackStat.values()).filter(weaponAttackStat -> weaponAttackStat.getName().equals(json.getAsString())).findFirst().orElse(WeaponAttackStat.STR);
        }

        @Override
        public JsonElement serialize(WeaponAttackStat src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
