package io.musician101.mcdndsimple.common.character.nonplayer.skill;

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

@JsonKey(key = Keys.NON_PLAYER_SKILLS, typeAdapter = NonPlayerSkills.Serializer.class)
public class NonPlayerSkills {

    private final NonPlayerSkill acrobatics = new NonPlayerSkill("Acrobatics (Dex)");
    private final NonPlayerSkill animalHandling = new NonPlayerSkill("Animal Handling (Wis)");
    private final NonPlayerSkill arcana = new NonPlayerSkill("Arcana (Int)");
    private final NonPlayerSkill athletics = new NonPlayerSkill("Athletics (Str)");
    private final NonPlayerSkill deception = new NonPlayerSkill("Deception (Cha)");
    private final NonPlayerSkill history = new NonPlayerSkill("history (Int)");
    private final NonPlayerSkill insight = new NonPlayerSkill("Insight (Wis)");
    private final NonPlayerSkill intimidation = new NonPlayerSkill("Intimidation (Cha)");
    private final NonPlayerSkill investigation = new NonPlayerSkill("Investigation (Int)");
    private final NonPlayerSkill medicine = new NonPlayerSkill("Medicine (Wis)");
    private final NonPlayerSkill nature = new NonPlayerSkill("Nature (Int)");
    private final NonPlayerSkill perception = new NonPlayerSkill("Perception (Wis)");
    private final NonPlayerSkill performance = new NonPlayerSkill("Performance (Cha)");
    private final NonPlayerSkill persuasion = new NonPlayerSkill("Persuasion (Cha)");
    private final NonPlayerSkill religion = new NonPlayerSkill("Religion (Int)");
    private final NonPlayerSkill sleightOfHand = new NonPlayerSkill("Sleight of Hand (Dex)");
    private final NonPlayerSkill stealth = new NonPlayerSkill("Stealth (Dex)");
    private final NonPlayerSkill survival = new NonPlayerSkill("Survival (Wis)");
    private final NonPlayerSkill unskilledCHA = new NonPlayerSkill("Unskilled CHA");
    private final NonPlayerSkill unskilledCON = new NonPlayerSkill("Unskilled CON");
    private final NonPlayerSkill unskilledDEX = new NonPlayerSkill("Unskilled DEX");
    private final NonPlayerSkill unskilledINT = new NonPlayerSkill("Unskilled INT");
    private final NonPlayerSkill unskilledSTR = new NonPlayerSkill("Unskilled STR");
    private final NonPlayerSkill unskilledWIS = new NonPlayerSkill("Unskilled WIS");

    public NonPlayerSkill getAcrobatics() {
        return acrobatics;
    }

    public NonPlayerSkill getAnimalHandling() {
        return animalHandling;
    }

    public NonPlayerSkill getArcana() {
        return arcana;
    }

    public NonPlayerSkill getAthletics() {
        return athletics;
    }

    public NonPlayerSkill getDeception() {
        return deception;
    }

    public NonPlayerSkill getHistory() {
        return history;
    }

    public NonPlayerSkill getInsight() {
        return insight;
    }

    public NonPlayerSkill getIntimidation() {
        return intimidation;
    }

    public NonPlayerSkill getInvestigation() {
        return investigation;
    }

    public NonPlayerSkill getMedicine() {
        return medicine;
    }

    public NonPlayerSkill getNature() {
        return nature;
    }

    public NonPlayerSkill getPerception() {
        return perception;
    }

    public NonPlayerSkill getPerformance() {
        return performance;
    }

    public NonPlayerSkill getPersuasion() {
        return persuasion;
    }

    public NonPlayerSkill getReligion() {
        return religion;
    }

    public NonPlayerSkill getSleightOfHand() {
        return sleightOfHand;
    }

    public NonPlayerSkill getStealth() {
        return stealth;
    }

    public NonPlayerSkill getSurvival() {
        return survival;
    }

    public NonPlayerSkill getUnskilledCHA() {
        return unskilledCHA;
    }

    public NonPlayerSkill getUnskilledCON() {
        return unskilledCON;
    }

    public NonPlayerSkill getUnskilledDEX() {
        return unskilledDEX;
    }

    public NonPlayerSkill getUnskilledINT() {
        return unskilledINT;
    }

    public NonPlayerSkill getUnskilledSTR() {
        return unskilledSTR;
    }

    public NonPlayerSkill getUnskilledWIS() {
        return unskilledWIS;
    }

    public static class Serializer implements JsonDeserializer<NonPlayerSkills>, JsonSerializer<NonPlayerSkills> {

        @Override
        public NonPlayerSkills deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerSkills nonPlayerSkills = new NonPlayerSkills();
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ACROBATICS), context, nonPlayerSkills.getAcrobatics());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ANIMAL_HANDLING), context, nonPlayerSkills.getAnimalHandling());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ARCANA), context, nonPlayerSkills.getArcana());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ATHLETICS), context, nonPlayerSkills.getAthletics());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.DECEPTION), context, nonPlayerSkills.getDeception());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.HISTORY), context, nonPlayerSkills.getHistory());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.INSIGHT), context, nonPlayerSkills.getInsight());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.INTIMIDATION), context, nonPlayerSkills.getIntimidation());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.INVESTIGATION), context, nonPlayerSkills.getInvestigation());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.MEDICINE), context, nonPlayerSkills.getMedicine());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.NATURE), context, nonPlayerSkills.getNature());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.PERCEPTION), context, nonPlayerSkills.getPerception());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.PERFORMANCE), context, nonPlayerSkills.getPerformance());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.PERSUASION), context, nonPlayerSkills.getPersuasion());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.RELIGION), context, nonPlayerSkills.getReligion());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.SLEIGHT_OF_HAND), context, nonPlayerSkills.getSleightOfHand());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.STEALTH), context, nonPlayerSkills.getStealth());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.SURVIVAL), context, nonPlayerSkills.getSurvival());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_CHA), context, nonPlayerSkills.getUnskilledCHA());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_CON), context, nonPlayerSkills.getUnskilledCON());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_DEX), context, nonPlayerSkills.getUnskilledDEX());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_INT), context, nonPlayerSkills.getUnskilledINT());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_STR), context, nonPlayerSkills.getUnskilledSTR());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_WIS), context, nonPlayerSkills.getUnskilledWIS());
            return nonPlayerSkills;
        }

        private void deserializeSkill(JsonObject jsonObject, JsonDeserializationContext context, NonPlayerSkill skill) {
            Keys.BONUS.deserializeFromParent(jsonObject, context).ifPresent(skill::setBonus);
        }

        @Override
        public JsonElement serialize(NonPlayerSkills src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serializeSkill(jsonObject.getAsJsonObject(Keys.ACROBATICS), context, src.getAcrobatics());
            serializeSkill(jsonObject.getAsJsonObject(Keys.ANIMAL_HANDLING), context, src.getAnimalHandling());
            serializeSkill(jsonObject.getAsJsonObject(Keys.ARCANA), context, src.getArcana());
            serializeSkill(jsonObject.getAsJsonObject(Keys.ATHLETICS), context, src.getAthletics());
            serializeSkill(jsonObject.getAsJsonObject(Keys.DECEPTION), context, src.getDeception());
            serializeSkill(jsonObject.getAsJsonObject(Keys.HISTORY), context, src.getHistory());
            serializeSkill(jsonObject.getAsJsonObject(Keys.INSIGHT), context, src.getInsight());
            serializeSkill(jsonObject.getAsJsonObject(Keys.INTIMIDATION), context, src.getIntimidation());
            serializeSkill(jsonObject.getAsJsonObject(Keys.INVESTIGATION), context, src.getInvestigation());
            serializeSkill(jsonObject.getAsJsonObject(Keys.MEDICINE), context, src.getMedicine());
            serializeSkill(jsonObject.getAsJsonObject(Keys.NATURE), context, src.getNature());
            serializeSkill(jsonObject.getAsJsonObject(Keys.PERCEPTION), context, src.getPerception());
            serializeSkill(jsonObject.getAsJsonObject(Keys.PERFORMANCE), context, src.getPerformance());
            serializeSkill(jsonObject.getAsJsonObject(Keys.PERSUASION), context, src.getPersuasion());
            serializeSkill(jsonObject.getAsJsonObject(Keys.RELIGION), context, src.getReligion());
            serializeSkill(jsonObject.getAsJsonObject(Keys.SLEIGHT_OF_HAND), context, src.getSleightOfHand());
            serializeSkill(jsonObject.getAsJsonObject(Keys.STEALTH), context, src.getStealth());
            serializeSkill(jsonObject.getAsJsonObject(Keys.SURVIVAL), context, src.getSurvival());
            serializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_CHA), context, src.getUnskilledCHA());
            serializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_CON), context, src.getUnskilledCON());
            serializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_DEX), context, src.getUnskilledDEX());
            serializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_INT), context, src.getUnskilledINT());
            serializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_STR), context, src.getUnskilledSTR());
            serializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_WIS), context, src.getUnskilledWIS());
            return jsonObject;
        }

        private void serializeSkill(JsonObject jsonObject, JsonSerializationContext context, NonPlayerSkill skill) {
            Keys.BONUS.serialize(skill.getBonus(), jsonObject, context);
        }
    }
}
