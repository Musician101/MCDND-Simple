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

public class SavingThrowOutputOptions {

    private boolean charisma = false;
    private boolean constitution = false;
    private boolean death = false;
    private boolean dexterity = false;
    private boolean intelligence = false;
    private boolean strength = false;
    private boolean wisdom = false;

    public boolean isCharismaEnabled() {
        return charisma;
    }

    public void setCharismaEnabled(boolean charisma) {
        this.charisma = charisma;
    }

    public boolean isConstitutionEnabled() {
        return constitution;
    }

    public void setConstitutionEnabled(boolean constitution) {
        this.constitution = constitution;
    }

    public boolean isDeathEnabled() {
        return death;
    }

    public void setDeathEnabled(boolean death) {
        this.death = death;
    }

    public boolean isDexterityEnabled() {
        return dexterity;
    }

    public void setDexterityEnabled(boolean dexterity) {
        this.dexterity = dexterity;
    }

    public boolean isIntelligenceEnabled() {
        return intelligence;
    }

    public void setIntelligenceEnabled(boolean intelligence) {
        this.intelligence = intelligence;
    }

    public boolean isStrengthEnabled() {
        return strength;
    }

    public void setStrengthEnabled(boolean strength) {
        this.strength = strength;
    }

    public boolean isWisdomEnabled() {
        return wisdom;
    }

    public void setWisdomEnabled(boolean wisdom) {
        this.wisdom = wisdom;
    }

    public static class Serializer implements JsonDeserializer<SavingThrowOutputOptions>, JsonSerializer<SavingThrowOutputOptions> {

        @Override
        public SavingThrowOutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SavingThrowOutputOptions savingThrowOutputOptions = new SavingThrowOutputOptions();
            Keys.CHARISMA_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(savingThrowOutputOptions::setCharismaEnabled);
            Keys.CONSTITUTION_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(savingThrowOutputOptions::setConstitutionEnabled);
            Keys.DEATH_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(savingThrowOutputOptions::setDeathEnabled);
            Keys.DEXTERITY_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(savingThrowOutputOptions::setDexterityEnabled);
            Keys.INTELLIGENCE_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(savingThrowOutputOptions::setIntelligenceEnabled);
            Keys.STRENGTH_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(savingThrowOutputOptions::setStrengthEnabled);
            Keys.WISDOM_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(savingThrowOutputOptions::setWisdomEnabled);
            return savingThrowOutputOptions;
        }

        @Override
        public JsonElement serialize(SavingThrowOutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.CHARISMA_BOOLEAN.serialize(src.isCharismaEnabled(), jsonObject, context);
            Keys.CONSTITUTION_BOOLEAN.serialize(src.isConstitutionEnabled(), jsonObject, context);
            Keys.DEATH_BOOLEAN.serialize(src.isDeathEnabled(), jsonObject, context);
            Keys.DEXTERITY_BOOLEAN.serialize(src.isDexterityEnabled(), jsonObject, context);
            Keys.INTELLIGENCE_BOOLEAN.serialize(src.isIntelligenceEnabled(), jsonObject, context);
            Keys.STRENGTH_BOOLEAN.serialize(src.isStrengthEnabled(), jsonObject, context);
            Keys.WISDOM_BOOLEAN.serialize(src.isWisdomEnabled(), jsonObject, context);
            return jsonObject;
        }
    }
}
