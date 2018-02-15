package io.musician101.mcdndsimple.common.character.player.clazz;

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

@JsonKey(key = Keys.CLASS_LEVELS, typeAdapter = ClassLevels.Serializer.class)
public class ClassLevels {

    private int barbarian = 0;
    private int bard = 0;
    private int cleric = 0;
    private int druid = 0;
    private int fighter = 0;
    private int monk = 0;
    private int paladin = 0;
    private int ranger = 0;
    private int rogue = 0;
    private int sorcerer = 0;
    private int warlock = 0;
    private int wizard = 0;

    public int getBarbarian() {
        return barbarian;
    }

    public void setBarbarian(int barbarian) {
        this.barbarian = barbarian;
    }

    public int getBard() {
        return bard;
    }

    public void setBard(int bard) {
        this.bard = bard;
    }

    public int getCleric() {
        return cleric;
    }

    public void setCleric(int cleric) {
        this.cleric = cleric;
    }

    public int getDruid() {
        return druid;
    }

    public void setDruid(int druid) {
        this.druid = druid;
    }

    public int getFighter() {
        return fighter;
    }

    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    public int getMonk() {
        return monk;
    }

    public void setMonk(int monk) {
        this.monk = monk;
    }

    public int getPaladin() {
        return paladin;
    }

    public void setPaladin(int paladin) {
        this.paladin = paladin;
    }

    public int getRanger() {
        return ranger;
    }

    public void setRanger(int ranger) {
        this.ranger = ranger;
    }

    public int getRogue() {
        return rogue;
    }

    public void setRogue(int rogue) {
        this.rogue = rogue;
    }

    public int getSorcerer() {
        return sorcerer;
    }

    public void setSorcerer(int sorcerer) {
        this.sorcerer = sorcerer;
    }

    public int getWarlock() {
        return warlock;
    }

    public void setWarlock(int warlock) {
        this.warlock = warlock;
    }

    public int getWizard() {
        return wizard;
    }

    public void setWizard(int wizard) {
        this.wizard = wizard;
    }

    public static class Serializer implements JsonDeserializer<ClassLevels>, JsonSerializer<ClassLevels> {

        @Override
        public ClassLevels deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ClassLevels classLevels = new ClassLevels();
            Keys.BARBARIAN.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setBarbarian);
            Keys.BARD.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setBard);
            Keys.DRUID.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setDruid);
            Keys.FIGHTER.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setFighter);
            Keys.MONK.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setMonk);
            Keys.PALADIN.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setPaladin);
            Keys.RANGER.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setRanger);
            Keys.ROGUE.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setRogue);
            Keys.SORCERER.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setSorcerer);
            Keys.WARLOCK.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setWarlock);
            Keys.WIZARD.deserializeFromParent(jsonObject, context).ifPresent(classLevels::setWizard);
            return classLevels;
        }

        @Override
        public JsonElement serialize(ClassLevels src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.BARBARIAN.serialize(src.getBarbarian(), jsonObject, context);
            Keys.BARD.serialize(src.getBard(), jsonObject, context);
            Keys.DRUID.serialize(src.getDruid(), jsonObject, context);
            Keys.FIGHTER.serialize(src.getFighter(), jsonObject, context);
            Keys.MONK.serialize(src.getMonk(), jsonObject, context);
            Keys.PALADIN.serialize(src.getPaladin(), jsonObject, context);
            Keys.RANGER.serialize(src.getRanger(), jsonObject, context);
            Keys.ROGUE.serialize(src.getRogue(), jsonObject, context);
            Keys.SORCERER.serialize(src.getSorcerer(), jsonObject, context);
            Keys.WARLOCK.serialize(src.getWarlock(), jsonObject, context);
            Keys.WIZARD.serialize(src.getWizard(), jsonObject, context);
            return jsonObject;
        }
    }
}
