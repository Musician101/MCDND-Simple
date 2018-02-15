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

    public static class Serializer implements JsonDeserializer<CoreSkillsOutputOptions>, JsonSerializer<CoreSkillsOutputOptions> {

        @Override
        public CoreSkillsOutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreSkillsOutputOptions coreSkillsOutputOptions = new CoreSkillsOutputOptions();
            Keys.ACROBATICS_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setAcrobaticsEnabled);
            Keys.ANIMAL_HANDLING_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setAnimalHandlingEnabled);
            Keys.ARCANA_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setArcanaEnabled);
            Keys.ATHLETICS_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setAthleticsEnabled);
            Keys.DECEPTION_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setDeceptionEnabled);
            Keys.HISTORY_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setHistoryEnabled);
            Keys.INSIGHT_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setInsightEnabled);
            Keys.INTIMIDATION_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setIntimidationEnabled);
            Keys.INVESTIGATION_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setInvestigationEnabled);
            Keys.MEDICINE_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setMedicineEnabled);
            Keys.NATURE_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setNatureEnabled);
            Keys.PERCEPTION_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setPerceptionEnabled);
            Keys.PERSUASION_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setPersuasionEnabled);
            Keys.RELIGION_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setReligionEnabled);
            Keys.SLEIGHT_OF_HAND_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setSleightOfHandEnabled);
            Keys.SURVIVAL_BOOLEAN.deserializeFromParent(jsonObject, context).ifPresent(coreSkillsOutputOptions::setSurvivalEnabled);
            return coreSkillsOutputOptions;
        }

        @Override
        public JsonElement serialize(CoreSkillsOutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.ACROBATICS_BOOLEAN.serialize(src.isAcrobaticsEnabled(), jsonObject, context);
            Keys.ANIMAL_HANDLING_BOOLEAN.serialize(src.isAnimalHandlingEnabled(), jsonObject, context);
            Keys.ARCANA_BOOLEAN.serialize(src.isArcanaEnabled(), jsonObject, context);
            Keys.ATHLETICS_BOOLEAN.serialize(src.isAthleticsEnabled(), jsonObject, context);
            Keys.DECEPTION_BOOLEAN.serialize(src.isDeceptionEnabled(), jsonObject, context);
            Keys.HISTORY_BOOLEAN.serialize(src.isHistoryEnabled(), jsonObject, context);
            Keys.INSIGHT_BOOLEAN.serialize(src.isInsightEnabled(), jsonObject, context);
            Keys.INTIMIDATION_BOOLEAN.serialize(src.isIntimidationEnabled(), jsonObject, context);
            Keys.INVESTIGATION_BOOLEAN.serialize(src.isInvestigationEnabled(), jsonObject, context);
            Keys.MEDICINE_BOOLEAN.serialize(src.isMedicineEnabled(), jsonObject, context);
            Keys.NATURE_BOOLEAN.serialize(src.isNatureEnabled(), jsonObject, context);
            Keys.PERCEPTION_BOOLEAN.serialize(src.isPerceptionEnabled(), jsonObject, context);
            Keys.PERSUASION_BOOLEAN.serialize(src.isPersuasionEnabled(), jsonObject, context);
            Keys.RELIGION_BOOLEAN.serialize(src.isReligionEnabled(), jsonObject, context);
            Keys.SLEIGHT_OF_HAND_BOOLEAN.serialize(src.isSleightOfHandEnabled(), jsonObject, context);
            Keys.SURVIVAL_BOOLEAN.serialize(src.isSurvivalEnabled(), jsonObject, context);
            return jsonObject;
        }
    }
}
