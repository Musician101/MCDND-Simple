package io.musician101.mcdndsimple.sponge.character.skill;

import io.musician101.mcdndsimple.sponge.character.AbilityScore;

public class Skill
{
    private int bonus = 0;
    private int pass = 10;
    private int total = 0;
    private SkillProficiency skillProficiency = SkillProficiency.NONE;
    private final String name;

    public Skill(String name)
    {
        this.name = name;
    }

    public int getBonus()
    {
        return bonus;
    }

    public String getName()
    {
        return name;
    }

    public int getPass()
    {
        return pass;
    }

    public SkillProficiency getSkillProficiency()
    {
        return skillProficiency;
    }

    public int getTotal()
    {
        return total;
    }

    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }

    public void update(AbilityScore abilityScore)
    {
        this.total = abilityScore.getMod();
        this.pass = 10 + total;
    }

    public void setSkillProficiency(SkillProficiency skillProficiency)
    {
        this.skillProficiency = skillProficiency;
    }
}
