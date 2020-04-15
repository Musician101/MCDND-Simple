package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class SpellDamage {

    private int bonus = 0;
    private boolean canCrit = true;
    @Nonnull
    private Dice damage = new Dice(0);
    @Nonnull
    private String damageType = "";

    public boolean canCrit() {
        return canCrit;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Nonnull
    public Dice getDamage() {
        return damage;
    }

    public void setDamage(@Nonnull Dice damage) {
        this.damage = damage;
    }

    @Nonnull
    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(@Nonnull String damageType) {
        this.damageType = damageType;
    }

    public void setCanCrit(boolean canCrit) {
        this.canCrit = canCrit;
    }

    public static class Serializer extends BaseSerializer<SpellDamage> {

        @Override
        public SpellDamage deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellDamage spellDamage = new SpellDamage();
            spellDamage.setCanCrit(deserialize(jsonObject, context, Keys.CAN_CRIT));
            spellDamage.setDamage(deserialize(jsonObject, context, Keys.DAMAGE));;
            spellDamage.setBonus(deserialize(jsonObject, context, Keys.BONUS));
            spellDamage.setDamageType(deserialize(jsonObject, context, Keys.DAMAGE_TYPE));
            return spellDamage;
        }

        @Override
        public JsonElement serialize(SpellDamage src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.CAN_CRIT, src.canCrit());
            serialize(jsonObject, context, Keys.DAMAGE, src.getDamage());
            serialize(jsonObject, context, Keys.BONUS, src.getBonus());
            serialize(jsonObject, context, Keys.DAMAGE_TYPE, src.getDamageType());
            return jsonObject;
        }
    }
}
