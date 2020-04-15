package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
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
    private CoreStats<NonPlayerAbilityScore> coreStats = new CoreStats<>(NonPlayerAbilityScore.class);
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
    public CoreStats<NonPlayerAbilityScore> getCoreStats() {
        return coreStats;
    }

    private void setCoreStats(@Nonnull CoreStats<NonPlayerAbilityScore> coreStats) {
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

    public void setXP(int xp) {
        this.xp = xp;
    }

    public boolean isDMOutputOnly() {
        return dmOutputOnly;
    }

    public void setDMOutputOnly(boolean dmOutputOnly) {
        this.dmOutputOnly = dmOutputOnly;
    }

    private void setHitPoints(NonPlayerHitPoints health) {
        this.health = health;
    }

    public static class Serializer extends BaseSerializer<NonPlayerSheet> {

        @Override
        public NonPlayerSheet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerSheet nonPlayerSheet = new NonPlayerSheet();
            nonPlayerSheet.setDMOutputOnly(deserialize(jsonObject, context, Keys.DM_OUTPUT_ONLY));
            nonPlayerSheet.setCoreStats(deserialize(jsonObject, context, Keys.NON_PLAYER_CORE_STATS));
            nonPlayerSheet.setChallengeRating(deserialize(jsonObject, context, Keys.CHALLENGE_RATING));
            nonPlayerSheet.setArmorClass(deserialize(jsonObject, context, Keys.ARMOR_CLASS));
            nonPlayerSheet.setClimbSpeed(deserialize(jsonObject, context, Keys.CLIMB_SPEED));
            nonPlayerSheet.setFlySpeed(deserialize(jsonObject, context, Keys.FLY_SPEED));
            nonPlayerSheet.setSpeed(deserialize(jsonObject, context, Keys.SPEED));
            nonPlayerSheet.setSwimSpeed(deserialize(jsonObject, context, Keys.SWIM_SPEED));
            nonPlayerSheet.setXP(deserialize(jsonObject, context, Keys.XP));
            nonPlayerSheet.setHitPoints(deserialize(jsonObject, context, Keys.HIT_POINTS_NPC));;
            nonPlayerSheet.setArmorClassNote(deserialize(jsonObject, context, Keys.ARMOR_CLASS_NOTE));
            nonPlayerSheet.setAlignment(deserialize(jsonObject, context, Keys.ALIGNMENT));
            nonPlayerSheet.setLanguages(deserialize(jsonObject, context, Keys.LANGUAGES));
            nonPlayerSheet.setSenses(deserialize(jsonObject, context, Keys.SENSES));
            nonPlayerSheet.setSize(deserialize(jsonObject, context, Keys.SIZE));
            nonPlayerSheet.setTypeRace(deserialize(jsonObject, context, Keys.TYPE_RACE));
            return nonPlayerSheet;
        }

        @Override
        public JsonElement serialize(NonPlayerSheet src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.DM_OUTPUT_ONLY, src.isDMOutputOnly());
            serialize(jsonObject, context, Keys.NON_PLAYER_CORE_STATS, src.getCoreStats());
            serialize(jsonObject, context, Keys.CHALLENGE_RATING, src.getChallengeRating());
            serialize(jsonObject, context, Keys.ARMOR_CLASS, src.getArmorClass());
            serialize(jsonObject, context, Keys.CLIMB_SPEED, src.getClimbSpeed());
            serialize(jsonObject, context, Keys.FLY_SPEED, src.getFlySpeed());
            serialize(jsonObject, context, Keys.SPEED, src.getSpeed());
            serialize(jsonObject, context, Keys.SWIM_SPEED, src.getClimbSpeed());
            serialize(jsonObject, context, Keys.XP, src.getXP());
            serialize(jsonObject, context, Keys.HIT_POINTS, src.getHealth());
            serialize(jsonObject, context, Keys.ARMOR_CLASS_NOTE, src.getArmorClassNote());
            serialize(jsonObject, context, Keys.ALIGNMENT, src.getAlignment());
            serialize(jsonObject, context, Keys.LANGUAGES, src.getLanguages());
            serialize(jsonObject, context, Keys.SENSES, src.getSenses());
            serialize(jsonObject, context, Keys.SIZE, src.getSize());
            serialize(jsonObject, context, Keys.TYPE_RACE, src.getTypeRace());
            return jsonObject;
        }
    }
}
