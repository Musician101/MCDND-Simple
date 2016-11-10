package io.musician101.mcdndsimple.common.character.weapon;

import io.musician101.mcdndsimple.common.Dice;

public abstract class AbstractWeapon
{
    private boolean isProficient = true;
    private Dice critDamageDice = new Dice(0);
    private Dice damageDice = new Dice(0);
    private int critMin = 20;
    private int damageBonus = 0;
    private int magicBonus = 0;
    private int toHit = 0;
    private String attackStat;
    private String name = "";

    public String getAttackStat()
    {
        return attackStat;
    }

    public Dice getCritDamageDice()
    {
        return critDamageDice;
    }

    public int getCritMin()
    {
        return critMin;
    }

    public int getDamageBonus()
    {
        return damageBonus;
    }

    public Dice getDamageDice()
    {
        return damageDice;
    }

    public int getMagicBonus()
    {
        return magicBonus;
    }

    public String getName()
    {
        return name;
    }

    public int getToHit()
    {
        return toHit;
    }

    public boolean isProficient()
    {
        return isProficient;
    }

    public void setAttackStat(String attackStat)
    {
        this.attackStat = attackStat;
    }

    public void setCritDamageDice(Dice critDamageDice)
    {
        this.critDamageDice = critDamageDice;
    }

    public void setCritMin(int critMin)
    {
        this.critMin = critMin;
    }

    public void setDamageBonus(int damageBonus)
    {
        this.damageBonus = damageBonus;
    }

    public void setDamageDice(Dice damageDice)
    {
        this.damageDice = damageDice;
    }

    public void setMagicBonus(int magicBonus)
    {
        this.magicBonus = magicBonus;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setProficient(boolean proficient)
    {
        isProficient = proficient;
    }

    public void setToHit(int toHit)
    {
        this.toHit = toHit;
    }
}
