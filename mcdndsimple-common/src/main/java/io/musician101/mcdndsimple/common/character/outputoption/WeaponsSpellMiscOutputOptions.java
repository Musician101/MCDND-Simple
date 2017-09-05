package io.musician101.mcdndsimple.common.character.outputoption;

public class WeaponsSpellMiscOutputOptions {

    private boolean hitDice = false;
    private boolean initiative = false;
    private boolean meleeWeapons = false;
    private boolean rangedWeapons = false;
    private boolean spellCast = false;
    private boolean spellInfo = false;

    public boolean isHitDiceEnabled() {
        return hitDice;
    }

    public void setHitDiceEnabled(boolean hitDice) {
        this.hitDice = hitDice;
    }

    public boolean isInitiativeEnabled() {
        return initiative;
    }

    public void setInitiativeEnabled(boolean initiative) {
        this.initiative = initiative;
    }

    public boolean isMeleeWeaponsEnabled() {
        return meleeWeapons;
    }

    public void setMeleeWeaponsEnabled(boolean meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    public boolean isRangedWeaponsEnabled() {
        return rangedWeapons;
    }

    public void setRangedWeaponsEnabled(boolean rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
    }

    public boolean isSpellCastEnabled() {
        return spellCast;
    }

    public void setSpellCastEnabled(boolean spellCast) {
        this.spellCast = spellCast;
    }

    public boolean isSpellInfoEnabled() {
        return spellInfo;
    }

    public void setSpellInfoEnabled(boolean spellInfo) {
        this.spellInfo = spellInfo;
    }
}
