package io.musician101.mcdndsimple.sponge.character.bonus;

public class SpellcastingBonus
{
    private int attack = 0;
    private int damage = 0;
    private int saveDC = 0;

    public int getAttack()
    {
        return attack;
    }

    public int getDamage()
    {
        return damage;
    }

    public int getSaveDC()
    {
        return saveDC;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public void setSaveDC(int saveDC)
    {
        this.saveDC = saveDC;
    }
}
