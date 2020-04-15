package io.musician101.mcdndsimple.common.character.player.outputoption;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;

public class CoreSkillsOutputOptions {

    private boolean acrobatics = false;
    private boolean animalHandling = false;
    private boolean arcana = false;
    private boolean athletics = false;
    private boolean deception = false;
    private boolean history = false;
    private boolean insight = false;
    private boolean intimidation = false;
    private boolean investigation = false;
    private boolean medicine = false;
    private boolean nature = false;
    private boolean perception = false;
    private boolean performance = false;
    private boolean persuasion = false;
    private boolean religion = false;
    private boolean sleightOfHand = false;
    private boolean stealth = false;
    private boolean survival = false;

    public boolean isAcrobaticsEnabled() {
        return acrobatics;
    }

    public void setAcrobaticsEnabled(boolean acrobatics) {
        this.acrobatics = acrobatics;
    }

    public boolean isAnimalHandlingEnabled() {
        return animalHandling;
    }

    public void setAnimalHandlingEnabled(boolean animalHandling) {
        this.animalHandling = animalHandling;
    }

    public boolean isArcanaEnabled() {
        return arcana;
    }

    public void setArcanaEnabled(boolean arcana) {
        this.arcana = arcana;
    }

    public boolean isAthleticsEnabled() {
        return athletics;
    }

    public void setAthleticsEnabled(boolean athletics) {
        this.athletics = athletics;
    }

    public boolean isDeceptionEnabled() {
        return deception;
    }

    public void setDeceptionEnabled(boolean deception) {
        this.deception = deception;
    }

    public boolean isHistoryEnabled() {
        return history;
    }

    public void setHistoryEnabled(boolean history) {
        this.history = history;
    }

    public boolean isInsightEnabled() {
        return insight;
    }

    public void setInsightEnabled(boolean insight) {
        this.insight = insight;
    }

    public boolean isIntimidationEnabled() {
        return intimidation;
    }

    public void setIntimidationEnabled(boolean intimidation) {
        this.intimidation = intimidation;
    }

    public boolean isInvestigationEnabled() {
        return investigation;
    }

    public void setInvestigationEnabled(boolean investigation) {
        this.investigation = investigation;
    }

    public boolean isMedicineEnabled() {
        return medicine;
    }

    public void setMedicineEnabled(boolean medicine) {
        this.medicine = medicine;
    }

    public boolean isNatureEnabled() {
        return nature;
    }

    public void setNatureEnabled(boolean nature) {
        this.nature = nature;
    }

    public boolean isPerceptionEnabled() {
        return perception;
    }

    public void setPerceptionEnabled(boolean perception) {
        this.perception = perception;
    }

    public boolean isPerformanceEnabled() {
        return performance;
    }

    public void setPerformanceEnabled(boolean performance) {
        this.performance = performance;
    }

    public boolean isPersuasionEnabled() {
        return persuasion;
    }

    public void setPersuasionEnabled(boolean persuasion) {
        this.persuasion = persuasion;
    }

    public boolean isReligionEnabled() {
        return religion;
    }

    public void setReligionEnabled(boolean religion) {
        this.religion = religion;
    }

    public boolean isSleightOfHandEnabled() {
        return sleightOfHand;
    }

    public void setSleightOfHandEnabled(boolean sleightOfHand) {
        this.sleightOfHand = sleightOfHand;
    }

    public boolean isStealthEnabled() {
        return stealth;
    }

    public void setStealthEnabled(boolean stealth) {
        this.stealth = stealth;
    }

    public boolean isSurvivalEnabled() {
        return survival;
    }

    public void setSurvivalEnabled(boolean survival) {
        this.survival = survival;
    }

    public static class Serializer extends BaseSerializer<CoreSkillsOutputOptions> {

        @Override
        public CoreSkillsOutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreSkillsOutputOptions coreSkillsOutputOptions = new CoreSkillsOutputOptions();
            coreSkillsOutputOptions.setAcrobaticsEnabled(deserialize(jsonObject, context, Keys.ACROBATICS_BOOLEAN));
            coreSkillsOutputOptions.setAnimalHandlingEnabled(deserialize(jsonObject, context, Keys.ANIMAL_HANDLING_BOOLEAN));
            coreSkillsOutputOptions.setArcanaEnabled(deserialize(jsonObject, context, Keys.ARCANA_BOOLEAN));
            coreSkillsOutputOptions.setAthleticsEnabled(deserialize(jsonObject, context, Keys.ATHLETICS_BOOLEAN));
            coreSkillsOutputOptions.setDeceptionEnabled(deserialize(jsonObject, context, Keys.DECEPTION_BOOLEAN));
            coreSkillsOutputOptions.setHistoryEnabled(deserialize(jsonObject, context, Keys.HISTORY_BOOLEAN));
            coreSkillsOutputOptions.setInsightEnabled(deserialize(jsonObject, context, Keys.INSIGHT_BOOLEAN));
            coreSkillsOutputOptions.setIntimidationEnabled(deserialize(jsonObject, context, Keys.INTIMIDATION_BOOLEAN));
            coreSkillsOutputOptions.setInvestigationEnabled(deserialize(jsonObject, context, Keys.INVESTIGATION_BOOLEAN));
            coreSkillsOutputOptions.setMedicineEnabled(deserialize(jsonObject, context, Keys.MEDICINE_BOOLEAN));
            coreSkillsOutputOptions.setNatureEnabled(deserialize(jsonObject, context, Keys.NATURE_BOOLEAN));
            coreSkillsOutputOptions.setPerceptionEnabled(deserialize(jsonObject, context, Keys.PERCEPTION_BOOLEAN));
            coreSkillsOutputOptions.setPersuasionEnabled(deserialize(jsonObject, context, Keys.PERSUASION_BOOLEAN));
            coreSkillsOutputOptions.setReligionEnabled(deserialize(jsonObject, context, Keys.RELIGION_BOOLEAN));
            coreSkillsOutputOptions.setSleightOfHandEnabled(deserialize(jsonObject, context, Keys.SLEIGHT_OF_HAND_BOOLEAN));
            coreSkillsOutputOptions.setSurvivalEnabled(deserialize(jsonObject, context, Keys.SURVIVAL_BOOLEAN));
            return coreSkillsOutputOptions;
        }

        @Override
        public JsonElement serialize(CoreSkillsOutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.ACROBATICS_BOOLEAN, src.isAcrobaticsEnabled());
            serialize(jsonObject, context, Keys.ANIMAL_HANDLING_BOOLEAN, src.isAnimalHandlingEnabled());
            serialize(jsonObject, context, Keys.ARCANA_BOOLEAN, src.isArcanaEnabled());
            serialize(jsonObject, context, Keys.ATHLETICS_BOOLEAN, src.isAthleticsEnabled());
            serialize(jsonObject, context, Keys.DECEPTION_BOOLEAN, src.isDeceptionEnabled());
            serialize(jsonObject, context, Keys.HISTORY_BOOLEAN, src.isHistoryEnabled());
            serialize(jsonObject, context, Keys.INSIGHT_BOOLEAN, src.isInsightEnabled());
            serialize(jsonObject, context, Keys.INTIMIDATION_BOOLEAN, src.isIntimidationEnabled());
            serialize(jsonObject, context, Keys.INVESTIGATION_BOOLEAN, src.isInvestigationEnabled());
            serialize(jsonObject, context, Keys.MEDICINE_BOOLEAN, src.isMedicineEnabled());
            serialize(jsonObject, context, Keys.NATURE_BOOLEAN, src.isNatureEnabled());
            serialize(jsonObject, context, Keys.PERCEPTION_BOOLEAN, src.isPerceptionEnabled());
            serialize(jsonObject, context, Keys.PERSUASION_BOOLEAN, src.isPersuasionEnabled());
            serialize(jsonObject, context, Keys.RELIGION_BOOLEAN, src.isReligionEnabled());
            serialize(jsonObject, context, Keys.SLEIGHT_OF_HAND_BOOLEAN, src.isSleightOfHandEnabled());
            serialize(jsonObject, context, Keys.SURVIVAL_BOOLEAN, src.isSurvivalEnabled());
            return jsonObject;
        }
    }
}
