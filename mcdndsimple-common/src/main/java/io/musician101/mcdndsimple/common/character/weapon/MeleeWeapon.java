package io.musician101.mcdndsimple.common.character.weapon;

public class MeleeWeapon extends AbstractWeapon
{
    private boolean plusStat = true;

    public MeleeWeapon()
    {
        setAttackStat(WeaponAttackStat.STR);
    }

    public boolean isPlusStat()
    {
        return plusStat;
    }

    public void setPlusStat(boolean plusStat)
    {
        this.plusStat = plusStat;
    }
}
