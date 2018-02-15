package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;

@JsonKey(key = Keys.SKILLS_TAB, typeAdapter = SkillsTab.Serializer.class)
public class SkillsTab {

    private final PlayerSkill acrobatics = new PlayerSkill("Acrobatics (Dex)");
    private final PlayerSkill animalHandling = new PlayerSkill("Animal Handling (Wis)");
    private final PlayerSkill arcana = new PlayerSkill("Arcana (Int)");
    private final PlayerSkill athletics = new PlayerSkill("Athletics (Str)");
    private final PlayerSkill deception = new PlayerSkill("Deception (Cha)");
    private final PlayerSkill history = new PlayerSkill("history (Int)");
    private final PlayerSkill insight = new PlayerSkill("Insight (Wis)");
    private final PlayerSkill intimidation = new PlayerSkill("Intimidation (Cha)");
    private final PlayerSkill investigation = new PlayerSkill("Investigation (Int)");
    private final PlayerSkill medicine = new PlayerSkill("Medicine (Wis)");
    private final PlayerSkill nature = new PlayerSkill("Nature (Int)");
    private final PlayerSkill perception = new PlayerSkill("Perception (Wis)");
    private final PlayerSkill performance = new PlayerSkill("Performance (Cha)");
    private final PlayerSkill persuasion = new PlayerSkill("Persuasion (Cha)");
    private final PlayerSkill religion = new PlayerSkill("Religion (Int)");
    private final PlayerSkill sleightOfHand = new PlayerSkill("Sleight of Hand (Dex)");
    private final PlayerSkill stealth = new PlayerSkill("Stealth (Dex)");
    private final PlayerSkill survival = new PlayerSkill("Survival (Wis)");
    private final PlayerSkill unskilledCHA = new PlayerSkill("Unskilled CHA");
    private final PlayerSkill unskilledCON = new PlayerSkill("Unskilled CON");
    private final PlayerSkill unskilledDEX = new PlayerSkill("Unskilled DEX");
    private final PlayerSkill unskilledINT = new PlayerSkill("Unskilled INT");
    private final PlayerSkill unskilledSTR = new PlayerSkill("Unskilled STR");
    private final PlayerSkill unskilledWIS = new PlayerSkill("Unskilled WIS");

    public PlayerSkill getAcrobatics() {
        return acrobatics;
    }

    public PlayerSkill getAnimalHandling() {
        return animalHandling;
    }

    public PlayerSkill getArcana() {
        return arcana;
    }

    public PlayerSkill getAthletics() {
        return athletics;
    }

    public PlayerSkill getDeception() {
        return deception;
    }

    public PlayerSkill getHistory() {
        return history;
    }

    public PlayerSkill getInsight() {
        return insight;
    }

    public PlayerSkill getIntimidation() {
        return intimidation;
    }

    public PlayerSkill getInvestigation() {
        return investigation;
    }

    public PlayerSkill getMedicine() {
        return medicine;
    }

    public PlayerSkill getNature() {
        return nature;
    }

    public PlayerSkill getPerception() {
        return perception;
    }

    public PlayerSkill getPerformance() {
        return performance;
    }

    public PlayerSkill getPersuasion() {
        return persuasion;
    }

    public PlayerSkill getReligion() {
        return religion;
    }

    public PlayerSkill getSleightOfHand() {
        return sleightOfHand;
    }

    public PlayerSkill getStealth() {
        return stealth;
    }

    public PlayerSkill getSurvival() {
        return survival;
    }

    public PlayerSkill getUnskilledCHA() {
        return unskilledCHA;
    }

    public PlayerSkill getUnskilledCON() {
        return unskilledCON;
    }

    public PlayerSkill getUnskilledDEX() {
        return unskilledDEX;
    }

    public PlayerSkill getUnskilledINT() {
        return unskilledINT;
    }

    public PlayerSkill getUnskilledSTR() {
        return unskilledSTR;
    }

    public PlayerSkill getUnskilledWIS() {
        return unskilledWIS;
    }

    public static class Serializer implements JsonDeserializer<SkillsTab>, JsonSerializer<SkillsTab> {

        @Override
        public SkillsTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SkillsTab skillsTab = new SkillsTab();
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ACROBATICS), context, skillsTab.getAcrobatics());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ANIMAL_HANDLING), context, skillsTab.getAnimalHandling());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ARCANA), context, skillsTab.getArcana());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.ATHLETICS), context, skillsTab.getAthletics());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.DECEPTION), context, skillsTab.getDeception());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.HISTORY), context, skillsTab.getHistory());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.INSIGHT), context, skillsTab.getInsight());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.INTIMIDATION), context, skillsTab.getIntimidation());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.INVESTIGATION), context, skillsTab.getInvestigation());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.MEDICINE), context, skillsTab.getMedicine());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.NATURE), context, skillsTab.getNature());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.PERCEPTION), context, skillsTab.getPerception());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.PERFORMANCE), context, skillsTab.getPerformance());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.PERSUASION), context, skillsTab.getPersuasion());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.RELIGION), context, skillsTab.getReligion());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.SLEIGHT_OF_HAND), context, skillsTab.getSleightOfHand());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.STEALTH), context, skillsTab.getStealth());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.SURVIVAL), context, skillsTab.getSurvival());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_CHA), context, skillsTab.getUnskilledCHA());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_CON), context, skillsTab.getUnskilledCON());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_DEX), context, skillsTab.getUnskilledDEX());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_INT), context, skillsTab.getUnskilledINT());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_STR), context, skillsTab.getUnskilledSTR());
            deserializeSkill(jsonObject.getAsJsonObject(Keys.UNSKILLED_WIS), context, skillsTab.getUnskilledWIS());
            return skillsTab;
        }

        private void deserializeSkill(JsonObject jsonObject, JsonDeserializationContext context, PlayerSkill skill) {
            Keys.BONUS.deserializeFromParent(jsonObject, context).ifPresent(skill::setBonus);
            JsonKeyProcessor.<JsonPrimitive, SkillProficiency>getJsonKey(Keys.SKILL_PROFICIENCY).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(skill::setSkillProficiency));
        }

        @Override
        public JsonElement serialize(SkillsTab src, Type type, JsonSerializationContext context) {
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

        private void serializeSkill(JsonObject jsonObject, JsonSerializationContext context, PlayerSkill skill) {
            Keys.BONUS.serialize(skill.getBonus(), jsonObject, context);
            JsonKeyProcessor.<JsonPrimitive, SkillProficiency>getJsonKey(Keys.SKILL_PROFICIENCY).ifPresent(jsonKey -> jsonKey.serialize(skill.getSkillProficiency(), jsonObject, context));
        }
    }
}
