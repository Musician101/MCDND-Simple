package io.musician101.mcdndsimple.common.character.nonplayer;

import io.musician101.mcdndsimple.common.character.CoreStats;

public class NPCSheet {

    private boolean dmOutputOnly = true;
    private CoreStats coreStats = new CoreStats();
    private double challengeRating = 0D;
    private int armorClass = 10;
    private int climbSpeed = 0;
    private int flySpeed = 0;
    private int speed = 0;
    private int swimSpeed = 0;
    private int xp = 0;
    private NPCHitPoints health = new NPCHitPoints();
    private String armorClassNote = "";
    private String alignment = "";
    private String languages = "";
    private String senses = "";
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

    public NPCHitPoints getHealth() {
        return health;
    }

    public String getLanguages() {
        return languages;
    }

    public String getSenses() {
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

    public void setHealth(NPCHitPoints health) {
        this.health = health;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setSenses(String senses) {
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
}
