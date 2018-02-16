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
import javax.annotation.Nonnull;

public class NonPlayerSheet {

    @Nonnull
    private String alignment = "";
    private int armorClass = 10;
    @Nonnull
    private String armorClassNote = "";
    private double challengeRating = 0D;
    private int climbSpeed = 0;
    @Nonnull
    private CoreStats coreStats = new CoreStats();
    private boolean dmOutputOnly = true;
    private int flySpeed = 0;
    @Nonnull
    private NonPlayerHitPoints health = new NonPlayerHitPoints();
    @Nonnull
    private List<String> languages = new ArrayList<>();
    @Nonnull
    private List<String> senses = new ArrayList<>();
    @Nonnull
    private String size = "";
    private int speed = 0;
    private int swimSpeed = 0;
    @Nonnull
    private String typeRace = "";
    private int xp = 0;

    @Nonnull
    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(@Nonnull String alignment) {
        this.alignment = alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    @Nonnull
    public String getArmorClassNote() {
        return armorClassNote;
    }

    public void setArmorClassNote(@Nonnull String armorClassNote) {
        this.armorClassNote = armorClassNote;
    }

    public double getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(double challengeRating) {
        this.challengeRating = challengeRating;
    }

    public int getClimbSpeed() {
        return climbSpeed;
    }

    public void setClimbSpeed(int climbSpeed) {
        this.climbSpeed = climbSpeed;
    }

    @Nonnull
    public CoreStats getCoreStats() {
        return coreStats;
    }

    public void setCoreStats(@Nonnull CoreStats coreStats) {
        this.coreStats = coreStats;
    }

    public int getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(int flySpeed) {
        this.flySpeed = flySpeed;
    }

    @Nonnull
    public NonPlayerHitPoints getHealth() {
        return health;
    }

    @Nonnull
    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(@Nonnull List<String> languages) {
        this.languages = languages;
    }

    @Nonnull
    public List<String> getSenses() {
        return senses;
    }

    public void setSenses(@Nonnull List<String> senses) {
        this.senses = senses;
    }

    @Nonnull
    public String getSize() {
        return size;
    }

    public void setSize(@Nonnull String size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSwimSpeed() {
        return swimSpeed;
    }

    public void setSwimSpeed(int swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    @Nonnull
    public String getTypeRace() {
        return typeRace;
    }

    public void setTypeRace(@Nonnull String typeRace) {
        this.typeRace = typeRace;
    }

    public int getXP() {
        return xp;
    }

    public boolean isDMOutputOnly() {
        return dmOutputOnly;
    }

    public void setDMOutputOnly(boolean dmOutputOnly) {
        this.dmOutputOnly = dmOutputOnly;
    }

    public void setHitPoints(NonPlayerHitPoints health) {
        this.health = health;
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
