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

    public void setAtHigherLevelsEnabled(boolean atHigherLevelsEnabled) {
        this.atHigherLevelsEnabled = atHigherLevelsEnabled;
    }

    public boolean isAttackRollEnabled() {
        return attackRollEnabled;
    }

    public void setAttackRollEnabled(boolean attackRollEnabled) {
        this.attackRollEnabled = attackRollEnabled;
    }

    public boolean isDamageEnabled() {
        return damageEnabled;
    }

    public void setDamageEnabled(boolean damageEnabled) {
        this.damageEnabled = damageEnabled;
    }

    public boolean isDescriptionEnabled() {
        return descriptionEnabled;
    }

    public void setDescriptionEnabled(boolean descriptionEnabled) {
        this.descriptionEnabled = descriptionEnabled;
    }

    public boolean isEffectsEnabled() {
        return effectsEnabled;
    }

    public void setEffectsEnabled(boolean effectsEnabled) {
        this.effectsEnabled = effectsEnabled;
    }

    public boolean isHealingEnabled() {
        return healingEnabled;
    }

    public void setHealingEnabled(boolean healingEnabled) {
        this.healingEnabled = healingEnabled;
    }

    public boolean isInfoBlockEnabled() {
        return infoBlockEnabled;
    }

    public void setInfoBlockEnabled(boolean infoBlockEnabled) {
        this.infoBlockEnabled = infoBlockEnabled;
    }

    public boolean isSavingThrowEnabled() {
        return savingThrowEnabled;
    }

    public void setSavingThrowEnabled(boolean savingThrowEnabled) {
        this.savingThrowEnabled = savingThrowEnabled;
    }

    public boolean isSecondAttackRollEnabled() {
        return secondAttackRollEnabled;
    }

    public void setSecondAttackRollEnabled(boolean secondAttackRollEnabled) {
        this.secondAttackRollEnabled = secondAttackRollEnabled;
    }
}
