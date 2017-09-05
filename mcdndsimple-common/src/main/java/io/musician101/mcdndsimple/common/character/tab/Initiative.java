package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.AbilityScore;

public class Initiative {
    private int bonus = 0;

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getInitiative(AbilityScore score) {
        if ("Dexterity".equals(score.getName()) && "DEX".equals(score.getShortName())) {
            return score.getMod() + bonus;
        }

        throw new IllegalArgumentException("Initiative relies on the DEX modifier.");
    }
}
