package io.musician101.mcdndsimple.common.character.spell;

public class MacroOptions {

    private boolean atHigherLevelsEnabled = false;
    private boolean attackRollEnabled = false;
    private boolean damageEnabled = false;
    private boolean descriptionEnabled = false;
    private boolean effectsEnabled = false;
    private boolean healingEnabled = false;
    private boolean infoBlockEnabled = false;
    private boolean savingThrowEnabled = false;
    private boolean secondAttackRollEnabled = false;

    public boolean isAtHigherLevelsEnabled() {
        return atHigherLevelsEnabled;
    }

    public boolean isAttackRollEnabled() {
        return attackRollEnabled;
    }

    public boolean isDamageEnabled() {
        return damageEnabled;
    }

    public boolean isDescriptionEnabled() {
        return descriptionEnabled;
    }

    public boolean isEffectsEnabled() {
        return effectsEnabled;
    }

    public boolean isHealingEnabled() {
        return healingEnabled;
    }

    public boolean isInfoBlockEnabled() {
        return infoBlockEnabled;
    }

    public boolean isSavingThrowEnabled() {
        return savingThrowEnabled;
    }

    public boolean isSecondAttackRollEnabled() {
        return secondAttackRollEnabled;
    }

    public void setAtHigherLevelsEnabled(boolean atHigherLevelsEnabled) {
        this.atHigherLevelsEnabled = atHigherLevelsEnabled;
    }

    public void setAttackRollEnabled(boolean attackRollEnabled) {
        this.attackRollEnabled = attackRollEnabled;
    }

    public void setDamageEnabled(boolean damageEnabled) {
        this.damageEnabled = damageEnabled;
    }

    public void setDescriptionEnabled(boolean descriptionEnabled) {
        this.descriptionEnabled = descriptionEnabled;
    }

    public void setEffectsEnabled(boolean effectsEnabled) {
        this.effectsEnabled = effectsEnabled;
    }

    public void setHealingEnabled(boolean healingEnabled) {
        this.healingEnabled = healingEnabled;
    }

    public void setInfoBlockEnabled(boolean infoBlockEnabled) {
        this.infoBlockEnabled = infoBlockEnabled;
    }

    public void setSavingThrowEnabled(boolean savingThrowEnabled) {
        this.savingThrowEnabled = savingThrowEnabled;
    }

    public void setSecondAttackRollEnabled(boolean secondAttackRollEnabled) {
        this.secondAttackRollEnabled = secondAttackRollEnabled;
    }
}
