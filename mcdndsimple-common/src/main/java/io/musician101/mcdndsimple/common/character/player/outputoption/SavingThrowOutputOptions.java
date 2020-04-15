package io.musician101.mcdndsimple.common.character.player.outputoption;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
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

    public static class Serializer extends BaseSerializer<SavingThrowOutputOptions> {

        @Override
        public SavingThrowOutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SavingThrowOutputOptions savingThrowOutputOptions = new SavingThrowOutputOptions();
            savingThrowOutputOptions.setCharismaEnabled(deserialize(jsonObject, context, Keys.CHARISMA_BOOLEAN));
            savingThrowOutputOptions.setConstitutionEnabled(deserialize(jsonObject, context, Keys.CONSTITUTION_BOOLEAN));
            savingThrowOutputOptions.setDeathEnabled(deserialize(jsonObject, context, Keys.DEATH_BOOLEAN));
            savingThrowOutputOptions.setDexterityEnabled(deserialize(jsonObject, context, Keys.DEXTERITY_BOOLEAN));
            savingThrowOutputOptions.setIntelligenceEnabled(deserialize(jsonObject, context, Keys.INTELLIGENCE_BOOLEAN));
            savingThrowOutputOptions.setStrengthEnabled(deserialize(jsonObject, context, Keys.STRENGTH_BOOLEAN));
            savingThrowOutputOptions.setWisdomEnabled(deserialize(jsonObject, context, Keys.WISDOM_BOOLEAN));
            return savingThrowOutputOptions;
        }

        @Override
        public JsonElement serialize(SavingThrowOutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.CHARISMA_BOOLEAN, src.isCharismaEnabled());
            serialize(jsonObject, context, Keys.CONSTITUTION_BOOLEAN, src.isConstitutionEnabled());
            serialize(jsonObject, context, Keys.DEATH_BOOLEAN, src.isDeathEnabled());
            serialize(jsonObject, context, Keys.DEXTERITY_BOOLEAN, src.isDexterityEnabled());
            serialize(jsonObject, context, Keys.INTELLIGENCE_BOOLEAN, src.isIntelligenceEnabled());
            serialize(jsonObject, context, Keys.STRENGTH_BOOLEAN, src.isStrengthEnabled());
            serialize(jsonObject, context, Keys.WISDOM_BOOLEAN, src.isWisdomEnabled());
            return jsonObject;
        }
    }
}
