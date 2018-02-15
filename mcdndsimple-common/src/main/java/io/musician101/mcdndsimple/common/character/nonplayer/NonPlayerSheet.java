package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NonPlayerSheet {

    private boolean dmOutputOnly = true;
    private CoreStats coreStats = new CoreStats();
    private double challengeRating = 0D;
    private int armorClass = 10;
    private int climbSpeed = 0;
    private int flySpeed = 0;
    private int speed = 0;
    private int swimSpeed = 0;
    private int xp = 0;
    private NonPlayerHitPoints health = new NonPlayerHitPoints();
    private String armorClassNote = "";
    private String alignment = "";
    private List<String> languages = new ArrayList<>();
    private List<String> senses = new ArrayList<>();
    private String size = "";
    private String typeRace = "";

    public String getAlignment() {
        return alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public String getArmorClassNote() {
        return armorClassNote;
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public int getClimbSpeed() {
        return climbSpeed;
    }

    public CoreStats getCoreStats() {
        return coreStats;
    }

    public int getFlySpeed() {
        return flySpeed;
    }

    public NonPlayerHitPoints getHealth() {
        return health;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getSenses() {
        return senses;
    }

    public String getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSwimSpeed() {
        return swimSpeed;
    }

    public String getTypeRace() {
        return typeRace;
    }

    public int getXP() {
        return xp;
    }

    public boolean isDMOutputOnly() {
        return dmOutputOnly;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public void setArmorClassNote(String armorClassNote) {
        this.armorClassNote = armorClassNote;
    }

    public void setChallengeRating(double challengeRating) {
        this.challengeRating = challengeRating;
    }

    public void setClimbSpeed(int climbSpeed) {
        this.climbSpeed = climbSpeed;
    }

    public void setCoreStats(CoreStats coreStats) {
        this.coreStats = coreStats;
    }

    public void setDMOutputOnly(boolean dmOutputOnly) {
        this.dmOutputOnly = dmOutputOnly;
    }

    public void setFlySpeed(int flySpeed) {
        this.flySpeed = flySpeed;
    }

    public void setHitPoints(NonPlayerHitPoints health) {
        this.health = health;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setSenses(List<String> senses) {
        this.senses = senses;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSwimSpeed(int swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    public void setTypeRace(String typeRace) {
        this.typeRace = typeRace;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public static class Serializer implements JsonDeserializer<NonPlayerSheet>, JsonSerializer<NonPlayerSheet> {

        @Override
        public NonPlayerSheet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerSheet nonPlayerSheet = new NonPlayerSheet();
            Keys.DM_OUTPUT_ONLY.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setDMOutputOnly);
            JsonKeyProcessor.<JsonObject, CoreStats>getJsonKey(Keys.CORE_STATS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setCoreStats));
            Keys.CHALLENGE_RATING.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setChallengeRating);
            Keys.ARMOR_CLASS.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setArmorClass);
            Keys.CLIMB_SPEED.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setClimbSpeed);
            Keys.FLY_SPEED.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setFlySpeed);
            Keys.SPEED.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setSpeed);
            Keys.SWIM_SPEED.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setSwimSpeed);
            Keys.XP.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setXp);
            JsonKeyProcessor.<JsonObject, NonPlayerHitPoints>getJsonKey(Keys.HIT_POINTS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setHitPoints));
            Keys.ARMOR_CLASS_NOTE.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setArmorClassNote);
            Keys.ALIGNMENT.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setAlignment);
            Keys.LANGUAGES.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setLanguages);
            Keys.SENSES.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setSenses);
            Keys.SIZE.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setSize);
            Keys.TYPE_RACE.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setTypeRace);
            return nonPlayerSheet;
        }

        @Override
        public JsonElement serialize(NonPlayerSheet src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.DM_OUTPUT_ONLY.serialize(src.isDMOutputOnly(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, CoreStats>getJsonKey(Keys.CORE_STATS).ifPresent(jsonKey -> jsonKey.serialize(src.getCoreStats(), jsonObject, context));
            Keys.CHALLENGE_RATING.serialize(src.getChallengeRating(), jsonObject, context);
            Keys.ARMOR_CLASS.serialize(src.getArmorClass(), jsonObject, context);
            Keys.CLIMB_SPEED.serialize(src.getClimbSpeed(), jsonObject, context);
            Keys.FLY_SPEED.serialize(src.getFlySpeed(), jsonObject, context);
            Keys.SPEED.serialize(src.getSpeed(), jsonObject, context);
            Keys.SWIM_SPEED.serialize(src.getClimbSpeed(), jsonObject, context);
            Keys.XP.serialize(src.getXP(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, NonPlayerHitPoints>getJsonKey(Keys.HIT_POINTS).ifPresent(jsonKey -> jsonKey.serialize(src.getHealth(), jsonObject, context));
            Keys.ARMOR_CLASS_NOTE.serialize(src.getArmorClassNote(), jsonObject, context);
            Keys.ALIGNMENT.serialize(src.getAlignment(), jsonObject, context);
            Keys.LANGUAGES.serialize(src.getLanguages(), jsonObject, context);
            Keys.SENSES.serialize(src.getSenses(), jsonObject, context);
            Keys.SIZE.serialize(src.getSize(), jsonObject, context);
            Keys.TYPE_RACE.serialize(src.getTypeRace(), jsonObject, context);
            return jsonObject;
        }
    }
}
