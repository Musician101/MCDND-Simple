package io.musician101.mcdndsimple.common.character.outputoption;

public class SavingThrowOutputOptions {

    private boolean charisma = false;
    private boolean constitution = false;
    private boolean death = false;
    private boolean dexterity = false;
    private boolean intelligence = false;
    private boolean strength = false;
    private boolean wisdom = false;

    public boolean isCharismaEnabled() {
        return charisma;
    }

    public void setCharismaEnabled(boolean charisma) {
        this.charisma = charisma;
    }

    public boolean isConstitutionEnabled() {
        return constitution;
    }

    public void setConstitutionEnabled(boolean constitution) {
        this.constitution = constitution;
    }

    public boolean isDeathEnabled() {
        return death;
    }

    public void setDeathEnabled(boolean death) {
        this.death = death;
    }

    public boolean isDexterityEnabled() {
        return dexterity;
    }

    public void setDexterityEnabled(boolean dexterity) {
        this.dexterity = dexterity;
    }

    public boolean isIntelligenceEnabled() {
        return intelligence;
    }

    public void setIntelligenceEnabled(boolean intelligence) {
        this.intelligence = intelligence;
    }

    public boolean isStrengthEnabled() {
        return strength;
    }

    public void setStrengthEnabled(boolean strength) {
        this.strength = strength;
    }

    public boolean isWisdomEnabled() {
        return wisdom;
    }

    public void setWisdomEnabled(boolean wisdom) {
        this.wisdom = wisdom;
    }
}
