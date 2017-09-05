package io.musician101.mcdndsimple.common.character.outputoption;

public class SavingThrowOutputOptions {
    
    private boolean strength = false;
    private boolean dexterity = false;
    private boolean constitution = false;
    private boolean intelligence = false;
    private boolean wisdom = false;
    private boolean charisma = false;
    private boolean death = false;

    public boolean isCharismaEnabled() {
        return charisma;
    }

    public boolean isConstitutionEnabled() {
        return constitution;
    }

    public boolean isDeathEnabled() {
        return death;
    }

    public boolean isDexterityEnabled() {
        return dexterity;
    }

    public boolean isIntelligenceEnabled() {
        return intelligence;
    }

    public boolean isStrengthEnabled() {
        return strength;
    }

    public boolean isWisdomEnabled() {
        return wisdom;
    }

    public void setCharismaEnabled(boolean charisma) {
        this.charisma = charisma;
    }

    public void setConstitutionEnabled(boolean constitution) {
        this.constitution = constitution;
    }

    public void setDeathEnabled(boolean death) {
        this.death = death;
    }

    public void setDexterityEnabled(boolean dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligenceEnabled(boolean intelligence) {
        this.intelligence = intelligence;
    }

    public void setStrengthEnabled(boolean strength) {
        this.strength = strength;
    }

    public void setWisdomEnabled(boolean wisdom) {
        this.wisdom = wisdom;
    }
}
