package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.character.player.AbilityScore;

public abstract class Skill {

    private final String name;
    private int bonus = 0;

    public Skill(String name) {
        this.name = name;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public int getPass(AbilityScore abilityScore) {
        return 10 + abilityScore.getMod();
    }
}
