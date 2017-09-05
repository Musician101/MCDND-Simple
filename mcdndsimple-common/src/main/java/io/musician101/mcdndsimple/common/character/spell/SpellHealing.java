package io.musician101.mcdndsimple.common.character.spell;

import io.musician101.mcdndsimple.common.Dice;

public class SpellHealing {

    private Dice healAmount = new Dice(0);
    private String statBonus = "None";

    public Dice getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(Dice healAmount) {
        this.healAmount = healAmount;
    }

    public String getStatBonus() {
        return statBonus;
    }

    public void setStatBonus(String statBonus) {
        this.statBonus = statBonus;
    }
}
