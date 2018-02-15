package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;

@JsonKey(key = Keys.MACRO_OPTIONS, typeAdapter = MacroOptions.Serializer.class)
public class MacroOptions {

    private boolean atHigherLevelsEnabled = false;
    private boolean attackRollEnabled = false;
    private boolean damageEnabled = false;
    private boolean descriptionEnabled = false;
    private boolean effectsEnabled = false;
    private boolean healingEnabled = false;
    private boolean infoBlockEnabled = false;
    private boolean savingThrowEnabled = false;
    private boolean secondAttackRollEnabled = false;

    public boolean isAtHigherLevelsEnabled() {
        return atHigherLevelsEnabled;
    }

    public void setAtHigherLevelsEnabled(boolean atHigherLevelsEnabled) {
        this.atHigherLevelsEnabled = atHigherLevelsEnabled;
    }

    public boolean isAttackRollEnabled() {
        return attackRollEnabled;
    }

    public void setAttackRollEnabled(boolean attackRollEnabled) {
        this.attackRollEnabled = attackRollEnabled;
    }

    public boolean isDamageEnabled() {
        return damageEnabled;
    }

    public void setDamageEnabled(boolean damageEnabled) {
        this.damageEnabled = damageEnabled;
    }

    public boolean isDescriptionEnabled() {
        return descriptionEnabled;
    }

    public void setDescriptionEnabled(boolean descriptionEnabled) {
        this.descriptionEnabled = descriptionEnabled;
    }

    public boolean isEffectsEnabled() {
        return effectsEnabled;
    }

    public void setEffectsEnabled(boolean effectsEnabled) {
        this.effectsEnabled = effectsEnabled;
    }

    public boolean isHealingEnabled() {
        return healingEnabled;
    }

    public void setHealingEnabled(boolean healingEnabled) {
        this.healingEnabled = healingEnabled;
    }

    public boolean isInfoBlockEnabled() {
        return infoBlockEnabled;
    }

    public void setInfoBlockEnabled(boolean infoBlockEnabled) {
        this.infoBlockEnabled = infoBlockEnabled;
    }

    public boolean isSavingThrowEnabled() {
        return savingThrowEnabled;
    }

    public void setSavingThrowEnabled(boolean savingThrowEnabled) {
        this.savingThrowEnabled = savingThrowEnabled;
    }

    public boolean isSecondAttackRollEnabled() {
        return secondAttackRollEnabled;
    }

    public void setSecondAttackRollEnabled(boolean secondAttackRollEnabled) {
        this.secondAttackRollEnabled = secondAttackRollEnabled;
    }

    public static class Serializer implements JsonDeserializer<MacroOptions>, JsonSerializer<MacroOptions> {

        @Override
        public MacroOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MacroOptions macroOptions = new MacroOptions();
            Keys.AT_HIGHER_LEVELS_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setAtHigherLevelsEnabled);
            Keys.ATTACK_ROLL_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setAttackRollEnabled);
            Keys.DAMAGE_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setDamageEnabled);
            Keys.DESCRIPTION_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setDescriptionEnabled);
            Keys.EFFECTS_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setEffectsEnabled);
            Keys.HEALING_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setHealingEnabled);
            Keys.INFO_BLOCK_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setInfoBlockEnabled);
            Keys.SAVING_THROW_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setSavingThrowEnabled);
            Keys.SECOND_ATTACK_ROLL_ENABLED.deserializeFromParent(jsonObject, context).ifPresent(macroOptions::setSecondAttackRollEnabled);
            return macroOptions;
        }

        @Override
        public JsonElement serialize(MacroOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.AT_HIGHER_LEVELS_ENABLED.serialize(src.isAtHigherLevelsEnabled(), jsonObject, context);
            Keys.ATTACK_ROLL_ENABLED.serialize(src.isAttackRollEnabled(), jsonObject, context);
            Keys.DAMAGE_ENABLED.serialize(src.isDamageEnabled(), jsonObject, context);
            Keys.DESCRIPTION_ENABLED.serialize(src.isDescriptionEnabled(), jsonObject, context);
            Keys.EFFECTS_ENABLED.serialize(src.isEffectsEnabled(), jsonObject, context);
            Keys.HEALING_ENABLED.serialize(src.isHealingEnabled(), jsonObject, context);
            Keys.INFO_BLOCK_ENABLED.serialize(src.isInfoBlockEnabled(), jsonObject, context);
            Keys.SAVING_THROW_ENABLED.serialize(src.isSavingThrowEnabled(), jsonObject, context);
            Keys.SECOND_ATTACK_ROLL_ENABLED.serialize(src.isSecondAttackRollEnabled(), jsonObject, context);
            return jsonObject;
        }
    }
}
