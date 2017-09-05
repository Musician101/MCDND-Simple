package io.musician101.mcdndsimple.common.character.outputoption;

public class WeaponsSpellMiscOutputOptions {

    private boolean initiative = false;
    private boolean hitDice = false;
    private boolean meleeWeapons = false;
    private boolean rangedWeapons = false;
    private boolean spellInfo = false;
    private boolean spellCast = false;

    public boolean isHitDiceEnabled() {
        return hitDice;
    }

    public boolean isInitiativeEnabled() {
        return initiative;
    }

    public boolean isMeleeWeaponsEnabled() {
        return meleeWeapons;
    }

    public boolean isRangedWeaponsEnabled() {
        return rangedWeapons;
    }

    public boolean isSpellCastEnabled() {
        return spellCast;
    }

    public boolean isSpellInfoEnabled() {
        return spellInfo;
    }

    public void setHitDiceEnabled(boolean hitDice) {
        this.hitDice = hitDice;
    }

    public void setInitiativeEnabled(boolean initiative) {
        this.initiative = initiative;
    }

    public void setMeleeWeaponsEnabled(boolean meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    public void setRangedWeaponsEnabled(boolean rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
    }

    public void setSpellCastEnabled(boolean spellCast) {
        this.spellCast = spellCast;
    }

    public void setSpellInfoEnabled(boolean spellInfo) {
        this.spellInfo = spellInfo;
    }
}
