package io.musician101.mcdndsimple.common.character.player.weapon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import java.lang.reflect.Type;

public class RangedWeapon extends Weapon {

    private int ammo = 0;

    public RangedWeapon() {
        super(WeaponAttackStat.DEX);
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public static class Serializer extends Weapon.Serializer<RangedWeapon> {

        @Override
        public RangedWeapon deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            RangedWeapon weapon = super.deserialize(json, type, context);
            weapon.setAmmo(deserialize(jsonObject, context, Keys.AMMO));
            return weapon;
        }

        @Override
        public JsonElement serialize(RangedWeapon src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = super.serialize(src, type, context).getAsJsonObject();
            serialize(jsonObject, context, Keys.AMMO, src.getAmmo());
            return jsonObject;
        }
    }
}
