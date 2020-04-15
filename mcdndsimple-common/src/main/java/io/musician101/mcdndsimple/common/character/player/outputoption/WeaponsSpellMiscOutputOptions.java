package io.musician101.mcdndsimple.common.character.player.outputoption;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
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

    public static class Serializer extends BaseSerializer<WeaponsSpellMiscOutputOptions> {

        @Override
        public WeaponsSpellMiscOutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = new WeaponsSpellMiscOutputOptions();
            weaponsSpellMiscOutputOptions.setHitDiceEnabled(deserialize(jsonObject, context, Keys.HIT_DICE_BOOLEAN));
            weaponsSpellMiscOutputOptions.setInitiativeEnabled(deserialize(jsonObject, context, Keys.INITIATIVE_BOOLEAN));
            weaponsSpellMiscOutputOptions.setMeleeWeaponsEnabled(deserialize(jsonObject, context, Keys.MELEE_WEAPONS_BOOLEAN));
            weaponsSpellMiscOutputOptions.setRangedWeaponsEnabled(deserialize(jsonObject, context, Keys.RANGED_WEAPONS_BOOLEAN));
            weaponsSpellMiscOutputOptions.setSpellCastEnabled(deserialize(jsonObject, context, Keys.SPELL_CAST_BOOLEAN));
            weaponsSpellMiscOutputOptions.setSpellInfoEnabled(deserialize(jsonObject, context, Keys.SPELL_INFO_BOOLEAN));
            return weaponsSpellMiscOutputOptions;
        }

        @Override
        public JsonElement serialize(WeaponsSpellMiscOutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.HIT_DICE_BOOLEAN, src.isHitDiceEnabled());
            serialize(jsonObject, context, Keys.INITIATIVE_BOOLEAN, src.isInitiativeEnabled());
            serialize(jsonObject, context, Keys.MELEE_WEAPONS_BOOLEAN, src.isMeleeWeaponsEnabled());
            serialize(jsonObject, context, Keys.RANGED_WEAPONS_BOOLEAN, src.isRangedWeaponsEnabled());
            serialize(jsonObject, context, Keys.SPELL_CAST_BOOLEAN, src.isSpellCastEnabled());
            serialize(jsonObject, context, Keys.SPELL_INFO_BOOLEAN, src.isSpellCastEnabled());
            return jsonObject;
        }
    }
}
