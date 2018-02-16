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
import javax.annotation.Nonnull;

@JsonKey(key = Keys.SKILLS_TAB, typeAdapter = SkillsTab.Serializer.class)
public class SkillsTab {

    @Nonnull
    private final PlayerSkill acrobatics = new PlayerSkill("Acrobatics (Dex)");
    @Nonnull
    private final PlayerSkill animalHandling = new PlayerSkill("Animal Handling (Wis)");
    @Nonnull
    private final PlayerSkill arcana = new PlayerSkill("Arcana (Int)");
    @Nonnull
    private final PlayerSkill athletics = new PlayerSkill("Athletics (Str)");
    @Nonnull
    private final PlayerSkill deception = new PlayerSkill("Deception (Cha)");
    @Nonnull
    private final PlayerSkill history = new PlayerSkill("history (Int)");
    @Nonnull
    private final PlayerSkill insight = new PlayerSkill("Insight (Wis)");
    @Nonnull
    private final PlayerSkill intimidation = new PlayerSkill("Intimidation (Cha)");
    @Nonnull
    private final PlayerSkill investigation = new PlayerSkill("Investigation (Int)");
    @Nonnull
    private final PlayerSkill medicine = new PlayerSkill("Medicine (Wis)");
    @Nonnull
    private final PlayerSkill nature = new PlayerSkill("Nature (Int)");
    @Nonnull
    private final PlayerSkill perception = new PlayerSkill("Perception (Wis)");
    @Nonnull
    private final PlayerSkill performance = new PlayerSkill("Performance (Cha)");
    @Nonnull
    private final PlayerSkill persuasion = new PlayerSkill("Persuasion (Cha)");
    @Nonnull
    private final PlayerSkill religion = new PlayerSkill("Religion (Int)");
    @Nonnull
    private final PlayerSkill sleightOfHand = new PlayerSkill("Sleight of Hand (Dex)");
    @Nonnull
    private final PlayerSkill stealth = new PlayerSkill("Stealth (Dex)");
    @Nonnull
    private final PlayerSkill survival = new PlayerSkill("Survival (Wis)");
    @Nonnull
    private final PlayerSkill unskilledCHA = new PlayerSkill("Unskilled CHA");
    @Nonnull
    private final PlayerSkill unskilledCON = new PlayerSkill("Unskilled CON");
    @Nonnull
    private final PlayerSkill unskilledDEX = new PlayerSkill("Unskilled DEX");
    @Nonnull
    private final PlayerSkill unskilledINT = new PlayerSkill("Unskilled INT");
    @Nonnull
    private final PlayerSkill unskilledSTR = new PlayerSkill("Unskilled STR");
    @Nonnull
    private final PlayerSkill unskilledWIS = new PlayerSkill("Unskilled WIS");

    @Nonnull
    public PlayerSkill getAcrobatics() {
        return acrobatics;
    }

    @Nonnull
    public PlayerSkill getAnimalHandling() {
        return animalHandling;
    }

    @Nonnull
    public PlayerSkill getArcana() {
        return arcana;
    }

    @Nonnull
    public PlayerSkill getAthletics() {
        return athletics;
    }

    @Nonnull
    public PlayerSkill getDeception() {
        return deception;
    }

    @Nonnull
    public PlayerSkill getHistory() {
        return history;
    }

    @Nonnull
    public PlayerSkill getInsight() {
        return insight;
    }

    @Nonnull
    public PlayerSkill getIntimidation() {
        return intimidation;
    }

    @Nonnull
    public PlayerSkill getInvestigation() {
        return investigation;
    }

    @Nonnull
    public PlayerSkill getMedicine() {
        return medicine;
    }

    @Nonnull
    public PlayerSkill getNature() {
        return nature;
    }

    @Nonnull
    public PlayerSkill getPerception() {
        return perception;
    }

    @Nonnull
    public PlayerSkill getPerformance() {
        return performance;
    }

    @Nonnull
    public PlayerSkill getPersuasion() {
        return persuasion;
    }

    @Nonnull
    public PlayerSkill getReligion() {
        return religion;
    }

    @Nonnull
    public PlayerSkill getSleightOfHand() {
        return sleightOfHand;
    }

    @Nonnull
    public PlayerSkill getStealth() {
        return stealth;
    }

    @Nonnull
    public PlayerSkill getSurvival() {
        return survival;
    }

    @Nonnull
    public PlayerSkill getUnskilledCHA() {
        return unskilledCHA;
    }

    @Nonnull
    public PlayerSkill getUnskilledCON() {
        return unskilledCON;
    }

    @Nonnull
    public PlayerSkill getUnskilledDEX() {
        return unskilledDEX;
    }

    @Nonnull
    public PlayerSkill getUnskilledINT() {
        return unskilledINT;
    }

    @Nonnull
    public PlayerSkill getUnskilledSTR() {
        return unskilledSTR;
    }

    @Nonnull
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
