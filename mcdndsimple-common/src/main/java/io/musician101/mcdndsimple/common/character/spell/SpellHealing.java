package io.musician101.mcdndsimple.common.character.spell;

import io.musician101.mcdndsimple.common.character.AbilityScore;

public class SpellHealing
{
    private int healAmount = 0;
    private String statBonus = "None";

    public int getHealTotal(AbilityScore abilityScore)
    {
        if ("None".equals(statBonus))
            return healAmount;

        return healAmount + abilityScore.getMod();
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    public String getStatBonus()
    {
        return statBonus;
    }

    public void setHealAmount(int healAmount)
    {
        this.healAmount = healAmount;
    }

    public void setStatBonus(String statBonus)
    {
        this.statBonus = statBonus;
    }
}
