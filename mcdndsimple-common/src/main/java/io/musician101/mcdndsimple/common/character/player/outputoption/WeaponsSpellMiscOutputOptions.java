package io.musician101.mcdndsimple.common.character.player.outputoption;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import java.lang.reflect.Type;

public class WeaponsSpellMiscOutputOptions {

    private boolean hitDice = false;
    private boolean initiative = false;
    private boolean meleeWeapons = false;
    private boolean rangedWeapons = false;
    private boolean spellCast = false;
    private boolean spellInfo = false;

    public boolean isHitDiceEnabled() {
        return hitDice;
    }

    public void setHitDiceEnabled(boolean hitDice) {
        this.hitDice = hitDice;
    }

    public boolean isInitiativeEnabled() {
        return initiative;
    }

    public void setInitiativeEnabled(boolean initiative) {
        this.initiative = initiative;
    }

    public boolean isMeleeWeaponsEnabled() {
        return meleeWeapons;
    }

    public void setMeleeWeaponsEnabled(boolean meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    public boolean isRangedWeaponsEnabled() {
        return rangedWeapons;
    }

    public void setRangedWeaponsEnabled(boolean rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
    }

    public boolean isSpellCastEnabled() {
        return spellCast;
    }

    public void setSpellCastEnabled(boolean spellCast) {
        this.spellCast = spellCast;
    }

    public boolean isSpellInfoEnabled() {
        return spellInfo;
    }

    public void setSpellInfoEnabled(boolean spellInfo) {
        this.spellInfo = spellInfo;
    }

    public static class Serializer implements JsonDeserializer<WeaponsSpellMiscOutputOptions>, JsonSerializer<WeaponsSpellMiscOutputOptions> {

        @Override
        public WeaponsSpellMiscOutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = new WeaponsSpellMiscOutputOptions();
            Keys.HIT_DICE_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(weaponsSpellMiscOutputOptions::setHitDiceEnabled);
            Keys.INITIATIVE_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(weaponsSpellMiscOutputOptions::setInitiativeEnabled);
            Keys.MELEE_WEAPONS_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(weaponsSpellMiscOutputOptions::setMeleeWeaponsEnabled);
            Keys.RANGED_WEAPONS_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(weaponsSpellMiscOutputOptions::setRangedWeaponsEnabled);
            Keys.SPELL_CAST_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(weaponsSpellMiscOutputOptions::setSpellCastEnabled);
            Keys.SPELL_INFO_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(weaponsSpellMiscOutputOptions::setSpellInfoEnabled);
            return weaponsSpellMiscOutputOptions;
        }

        @Override
        public JsonElement serialize(WeaponsSpellMiscOutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.HIT_DICE_BOOLEAN.serialize(src.isHitDiceEnabled(), jsonObject, context);
            Keys.INITIATIVE_BOOLEAN.serialize(src.isInitiativeEnabled(), jsonObject, context);
            Keys.MELEE_WEAPONS_BOOLEAN.serialize(src.isMeleeWeaponsEnabled(), jsonObject, context);
            Keys.RANGED_WEAPONS_BOOLEAN.serialize(src.isRangedWeaponsEnabled(), jsonObject, context);
            Keys.SPELL_CAST_BOOLEAN.serialize(src.isSpellCastEnabled(), jsonObject, context);
            Keys.SPELL_INFO_BOOLEAN.serialize(src.isSpellCastEnabled(), jsonObject, context);
            return jsonObject;
        }
    }
}
