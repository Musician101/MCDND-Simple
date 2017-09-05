package io.musician101.mcdndsimple.common.character.spell;

import io.musician101.mcdndsimple.common.Dice;

public class SpellDamage {

    private int bonus = 0;
    private boolean canCrit = true;
    private String damageType = "";
    private Dice dice = new Dice(0);

    public boolean canCrit() {
        return canCrit;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void setCanCrit(boolean canCrit) {
        this.canCrit = canCrit;
    }
}
