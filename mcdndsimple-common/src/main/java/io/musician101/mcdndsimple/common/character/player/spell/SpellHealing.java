package io.musician101.mcdndsimple.common.character.player.spell;

import io.musician101.mcdndsimple.common.Dice;

public class SpellHealing {

    private Dice healAmount = new Dice(0);
    private StatBonus statBonus = StatBonus.NONE;

    public Dice getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(Dice healAmount) {
        this.healAmount = healAmount;
    }

    public StatBonus getStatBonus() {
        return statBonus;
    }

    public void setStatBonus(StatBonus statBonus) {
        this.statBonus = statBonus;
    }
}
