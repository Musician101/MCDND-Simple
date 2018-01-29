package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.character.player.AbilityScore;

public class Skill {

    private final String name;
    private int bonus = 0;
    private int pass = 10;
    private int total = 0;

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

    public int getPass() {
        return pass;
    }

    public int getTotal() {
        return total;
    }

    public void update(AbilityScore abilityScore) {
        this.total = abilityScore.getMod();
        this.pass = 10 + total;
    }
}
