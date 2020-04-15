package io.musician101.mcdndsimple.common.character.player.weapon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import java.lang.reflect.Type;

public class MeleeWeapon extends Weapon {

    private boolean plusStat = true;

    public MeleeWeapon() {
        super(WeaponAttackStat.STR);
    }

    public boolean plusStat() {
        return plusStat;
    }

    public void setPlusStat(boolean plusStat) {
        this.plusStat = plusStat;
    }

    public static class Serializer extends Weapon.Serializer<MeleeWeapon> {

        @Override
        public MeleeWeapon deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MeleeWeapon weapon = super.deserialize(json, type, context);
            weapon.setPlusStat(deserialize(jsonObject, context, Keys.PLUS_STAT));
            return weapon;
        }

        @Override
        public JsonElement serialize(MeleeWeapon src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = super.serialize(src, type, context).getAsJsonObject();
            serialize(jsonObject, context, Keys.PLUS_STAT, src.plusStat());
            return jsonObject;
        }
    }
}
