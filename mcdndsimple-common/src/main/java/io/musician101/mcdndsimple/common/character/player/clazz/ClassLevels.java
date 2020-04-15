package io.musician101.mcdndsimple.common.character.player.clazz;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;

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

    public static class Serializer extends BaseSerializer<ClassLevels> {

        @Override
        public ClassLevels deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ClassLevels classLevels = new ClassLevels();
            classLevels.setBarbarian(deserialize(jsonObject, context, Keys.BARBARIAN));;
            classLevels.setBard(deserialize(jsonObject, context, Keys.BARD));;
            classLevels.setDruid(deserialize(jsonObject, context, Keys.DRUID));;
            classLevels.setFighter(deserialize(jsonObject, context, Keys.FIGHTER));;
            classLevels.setMonk(deserialize(jsonObject, context, Keys.MONK));;
            classLevels.setPaladin(deserialize(jsonObject, context, Keys.PALADIN));;
            classLevels.setRanger(deserialize(jsonObject, context, Keys.RANGER));;
            classLevels.setRogue(deserialize(jsonObject, context, Keys.ROGUE));;
            classLevels.setSorcerer(deserialize(jsonObject, context, Keys.SORCERER));;
            classLevels.setWarlock(deserialize(jsonObject, context, Keys.WARLOCK));;
            classLevels.setWizard(deserialize(jsonObject, context, Keys.WIZARD));;
            return classLevels;
        }

        @Override
        public JsonElement serialize(ClassLevels src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.BARBARIAN, src.getBarbarian());
            serialize(jsonObject, context, Keys.BARD, src.getBard());
            serialize(jsonObject, context, Keys.DRUID, src.getDruid());
            serialize(jsonObject, context, Keys.FIGHTER, src.getFighter());
            serialize(jsonObject, context, Keys.MONK, src.getMonk());
            serialize(jsonObject, context, Keys.PALADIN, src.getPaladin());
            serialize(jsonObject, context, Keys.RANGER, src.getRanger());
            serialize(jsonObject, context, Keys.ROGUE, src.getRogue());
            serialize(jsonObject, context, Keys.SORCERER, src.getSorcerer());
            serialize(jsonObject, context, Keys.WARLOCK, src.getWarlock());
            serialize(jsonObject, context, Keys.WIZARD, src.getWizard());
            return jsonObject;
        }
    }
}
