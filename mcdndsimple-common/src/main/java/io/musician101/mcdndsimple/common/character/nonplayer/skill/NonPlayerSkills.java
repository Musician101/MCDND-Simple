package io.musician101.mcdndsimple.common.character.nonplayer.skill;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class NonPlayerSkills {

    @Nonnull
    private final NonPlayerSkill acrobatics = new NonPlayerSkill("Acrobatics (Dex)");
    @Nonnull
    private final NonPlayerSkill animalHandling = new NonPlayerSkill("Animal Handling (Wis)");
    @Nonnull
    private final NonPlayerSkill arcana = new NonPlayerSkill("Arcana (Int)");
    @Nonnull
    private final NonPlayerSkill athletics = new NonPlayerSkill("Athletics (Str)");
    @Nonnull
    private final NonPlayerSkill deception = new NonPlayerSkill("Deception (Cha)");
    @Nonnull
    private final NonPlayerSkill history = new NonPlayerSkill("history (Int)");
    @Nonnull
    private final NonPlayerSkill insight = new NonPlayerSkill("Insight (Wis)");
    @Nonnull
    private final NonPlayerSkill intimidation = new NonPlayerSkill("Intimidation (Cha)");
    @Nonnull
    private final NonPlayerSkill investigation = new NonPlayerSkill("Investigation (Int)");
    @Nonnull
    private final NonPlayerSkill medicine = new NonPlayerSkill("Medicine (Wis)");
    @Nonnull
    private final NonPlayerSkill nature = new NonPlayerSkill("Nature (Int)");
    @Nonnull
    private final NonPlayerSkill perception = new NonPlayerSkill("Perception (Wis)");
    @Nonnull
    private final NonPlayerSkill performance = new NonPlayerSkill("Performance (Cha)");
    @Nonnull
    private final NonPlayerSkill persuasion = new NonPlayerSkill("Persuasion (Cha)");
    @Nonnull
    private final NonPlayerSkill religion = new NonPlayerSkill("Religion (Int)");
    @Nonnull
    private final NonPlayerSkill sleightOfHand = new NonPlayerSkill("Sleight of Hand (Dex)");
    @Nonnull
    private final NonPlayerSkill stealth = new NonPlayerSkill("Stealth (Dex)");
    @Nonnull
    private final NonPlayerSkill survival = new NonPlayerSkill("Survival (Wis)");
    @Nonnull
    private final NonPlayerSkill unskilledCHA = new NonPlayerSkill("Unskilled CHA");
    @Nonnull
    private final NonPlayerSkill unskilledCON = new NonPlayerSkill("Unskilled CON");
    @Nonnull
    private final NonPlayerSkill unskilledDEX = new NonPlayerSkill("Unskilled DEX");
    @Nonnull
    private final NonPlayerSkill unskilledINT = new NonPlayerSkill("Unskilled INT");
    @Nonnull
    private final NonPlayerSkill unskilledSTR = new NonPlayerSkill("Unskilled STR");
    @Nonnull
    private final NonPlayerSkill unskilledWIS = new NonPlayerSkill("Unskilled WIS");

    @Nonnull
    public NonPlayerSkill getAcrobatics() {
        return acrobatics;
    }

    @Nonnull
    public NonPlayerSkill getAnimalHandling() {
        return animalHandling;
    }

    @Nonnull
    public NonPlayerSkill getArcana() {
        return arcana;
    }

    @Nonnull
    public NonPlayerSkill getAthletics() {
        return athletics;
    }

    @Nonnull
    public NonPlayerSkill getDeception() {
        return deception;
    }

    @Nonnull
    public NonPlayerSkill getHistory() {
        return history;
    }

    @Nonnull
    public NonPlayerSkill getInsight() {
        return insight;
    }

    @Nonnull
    public NonPlayerSkill getIntimidation() {
        return intimidation;
    }

    @Nonnull
    public NonPlayerSkill getInvestigation() {
        return investigation;
    }

    @Nonnull
    public NonPlayerSkill getMedicine() {
        return medicine;
    }

    @Nonnull
    public NonPlayerSkill getNature() {
        return nature;
    }

    @Nonnull
    public NonPlayerSkill getPerception() {
        return perception;
    }

    @Nonnull
    public NonPlayerSkill getPerformance() {
        return performance;
    }

    @Nonnull
    public NonPlayerSkill getPersuasion() {
        return persuasion;
    }

    @Nonnull
    public NonPlayerSkill getReligion() {
        return religion;
    }

    @Nonnull
    public NonPlayerSkill getSleightOfHand() {
        return sleightOfHand;
    }

    @Nonnull
    public NonPlayerSkill getStealth() {
        return stealth;
    }

    @Nonnull
    public NonPlayerSkill getSurvival() {
        return survival;
    }

    @Nonnull
    public NonPlayerSkill getUnskilledCHA() {
        return unskilledCHA;
    }

    @Nonnull
    public NonPlayerSkill getUnskilledCON() {
        return unskilledCON;
    }

    @Nonnull
    public NonPlayerSkill getUnskilledDEX() {
        return unskilledDEX;
    }

    @Nonnull
    public NonPlayerSkill getUnskilledINT() {
        return unskilledINT;
    }

    @Nonnull
    public NonPlayerSkill getUnskilledSTR() {
        return unskilledSTR;
    }

    @Nonnull
    public NonPlayerSkill getUnskilledWIS() {
        return unskilledWIS;
    }

    public static class Serializer extends BaseSerializer<NonPlayerSkills> {

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
            skill.setBonus(deserialize(jsonObject, context, Keys.BONUS));;
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
            serialize(jsonObject, context, Keys.BONUS, skill.getBonus());
        }
    }
}
