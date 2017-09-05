package io.musician101.mcdndsimple.common.character.skill;

import io.musician101.mcdndsimple.common.character.AbilityScore;

public class Skill {

    private final String name;
    private int bonus = 0;
    private int pass = 10;
    private SkillProficiency skillProficiency = SkillProficiency.NONE;
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

    public SkillProficiency getSkillProficiency() {
        return skillProficiency;
    }

    public void setSkillProficiency(SkillProficiency skillProficiency) {
        this.skillProficiency = skillProficiency;
    }

    public int getTotal() {
        return total;
    }

    public void update(AbilityScore abilityScore) {
        this.total = abilityScore.getMod();
        this.pass = 10 + total;
    }
}
