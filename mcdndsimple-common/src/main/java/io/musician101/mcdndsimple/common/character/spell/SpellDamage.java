package io.musician101.mcdndsimple.common.character.spell;

import io.musician101.mcdndsimple.common.Dice;

public class SpellDamage
{
    private boolean canCrit = true;
    private Dice dice = new Dice(0);
    private int bonus = 0;
    private String damageType = "";

    public int getBonus()
    {
        return bonus;
    }

    public String getDamageType()
    {
        return damageType;
    }

    public Dice getDice()
    {
        return dice;
    }

    public boolean canCrit()
    {
        return canCrit;
    }

    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }

    public void setCanCrit(boolean canCrit)
    {
        this.canCrit = canCrit;
    }

    public void setDamageType(String damageType)
    {
        this.damageType = damageType;
    }

    public void setDice(Dice dice)
    {
        this.dice = dice;
    }
}
