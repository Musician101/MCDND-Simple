package io.musician101.mcdndsimple.common.character.player.weapon;

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


public enum WeaponAttackStat {
    CHA,
    CON,
    DEX,
    FINESSE("Finesse"),
    INT,
    STR,
    WIS;

    @Nullable
    private final String name;

    WeaponAttackStat() {
        this(null);
    }

    WeaponAttackStat(@Nullable String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name == null ? toString() : name;
    }

    public static class Serializer extends BaseSerializer<WeaponAttackStat> {

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
