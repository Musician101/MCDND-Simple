package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;

@JsonKey(key = Keys.SPELL_DAMAGE, typeAdapter = SpellDamage.Serializer.class)
public class SpellDamage {

    private int bonus = 0;
    private boolean canCrit = true;
    private Dice damage = new Dice(0);
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

    public Dice getDamage() {
        return damage;
    }

    public void setDamage(Dice damage) {
        this.damage = damage;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public void setCanCrit(boolean canCrit) {
        this.canCrit = canCrit;
    }

    public static class Serializer implements JsonDeserializer<SpellDamage>, JsonSerializer<SpellDamage> {

        @Override
        public SpellDamage deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellDamage spellDamage = new SpellDamage();
            Keys.CAN_CRIT.deserializeFromParent(jsonObject, context).ifPresent(spellDamage::setCanCrit);
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spellDamage::setDamage));
            Keys.BONUS.deserializeFromParent(jsonObject, context).ifPresent(spellDamage::setBonus);
            Keys.DAMAGE_TYPE.deserializeFromParent(jsonObject, context).ifPresent(spellDamage::setDamageType);
            return spellDamage;
        }

        @Override
        public JsonElement serialize(SpellDamage src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.CAN_CRIT.serialize(src.canCrit(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.serialize(src.getDamage(), jsonObject, context));
            Keys.BONUS.serialize(src.getBonus(), jsonObject, context);
            Keys.DAMAGE_TYPE.serialize(src.getDamageType(), jsonObject, context);
            return jsonObject;
        }
    }
}
