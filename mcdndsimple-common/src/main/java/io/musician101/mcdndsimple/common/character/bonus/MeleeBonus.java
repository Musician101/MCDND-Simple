package io.musician101.mcdndsimple.common.character.bonus;

public class MeleeBonus
{
    private int attack = 0;
    private int damage = 0;

    public int getAttack()
    {
        return attack;
    }

    public int getDamage()
    {
        return damage;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }
}