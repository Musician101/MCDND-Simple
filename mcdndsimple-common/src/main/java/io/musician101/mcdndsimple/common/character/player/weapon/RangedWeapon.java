package io.musician101.mcdndsimple.common.character.player.weapon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;

@TypeOf(RangedWeapon.Serializer.class)
public class RangedWeapon extends AbstractWeapon {

    private int ammo = 0;

    public RangedWeapon() {
        setAttackStat(WeaponAttackStat.DEX);
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public static class Serializer extends AbstractWeapon.Serializer<RangedWeapon> {

        @Override
        public RangedWeapon deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            RangedWeapon weapon = super.deserialize(json, type, context);
            Keys.AMMO.deserializeFromParent(jsonObject, context).ifPresent(weapon::setAmmo);
            return weapon;
        }

        @Override
        public JsonElement serialize(RangedWeapon src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = super.serialize(src, type, context).getAsJsonObject();
            Keys.AMMO.serialize(src.getAmmo(), jsonObject, context);
            return jsonObject;
        }
    }
}
