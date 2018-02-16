package io.musician101.mcdndsimple.common.character.player.weapon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;

@TypeOf(MeleeWeapon.Serializer.class)
public class MeleeWeapon extends AbstractWeapon {

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

    public static class Serializer extends AbstractWeapon.Serializer<MeleeWeapon> {

        @Override
        public MeleeWeapon deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MeleeWeapon weapon = super.deserialize(json, type, context);
            Keys.PLUS_STAT.deserializeFromParent(jsonObject, context).ifPresent(weapon::setPlusStat);
            return weapon;
        }

        @Override
        public JsonElement serialize(MeleeWeapon src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = super.serialize(src, type, context).getAsJsonObject();
            Keys.PLUS_STAT.serialize(src.plusStat(), jsonObject, context);
            return jsonObject;
        }
    }
}
