package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;

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

    public static class Serializer extends BaseSerializer<MacroOptions> {

        @Override
        public MacroOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MacroOptions macroOptions = new MacroOptions();
            macroOptions.setAtHigherLevelsEnabled(deserialize(jsonObject, context, Keys.AT_HIGHER_LEVELS_ENABLED));
            macroOptions.setAttackRollEnabled(deserialize(jsonObject, context, Keys.ATTACK_ROLL_ENABLED));
            macroOptions.setDamageEnabled(deserialize(jsonObject, context, Keys.DAMAGE_ENABLED));
            macroOptions.setDescriptionEnabled(deserialize(jsonObject, context, Keys.DESCRIPTION_ENABLED));
            macroOptions.setEffectsEnabled(deserialize(jsonObject, context, Keys.EFFECTS_ENABLED));
            macroOptions.setHealingEnabled(deserialize(jsonObject, context, Keys.HEALING_ENABLED));
            macroOptions.setInfoBlockEnabled(deserialize(jsonObject, context, Keys.INFO_BLOCK_ENABLED));
            macroOptions.setSavingThrowEnabled(deserialize(jsonObject, context, Keys.SAVING_THROW_ENABLED));
            macroOptions.setSecondAttackRollEnabled(deserialize(jsonObject, context, Keys.SECOND_ATTACK_ROLL_ENABLED));
            return macroOptions;
        }

        @Override
        public JsonElement serialize(MacroOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.AT_HIGHER_LEVELS_ENABLED, src.isAtHigherLevelsEnabled());
            serialize(jsonObject, context, Keys.ATTACK_ROLL_ENABLED, src.isAttackRollEnabled());
            serialize(jsonObject, context, Keys.DAMAGE_ENABLED, src.isDamageEnabled());
            serialize(jsonObject, context, Keys.DESCRIPTION_ENABLED, src.isDescriptionEnabled());
            serialize(jsonObject, context, Keys.EFFECTS_ENABLED, src.isEffectsEnabled());
            serialize(jsonObject, context, Keys.HEALING_ENABLED, src.isHealingEnabled());
            serialize(jsonObject, context, Keys.INFO_BLOCK_ENABLED, src.isInfoBlockEnabled());
            serialize(jsonObject, context, Keys.SAVING_THROW_ENABLED, src.isSavingThrowEnabled());
            serialize(jsonObject, context, Keys.SECOND_ATTACK_ROLL_ENABLED, src.isSecondAttackRollEnabled());
            return jsonObject;
        }
    }
}
