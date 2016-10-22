package io.musician101.mcdndsimple.sponge.character.weapon;

public class RangedWeapon extends AbstractWeapon
{
    private int ammo = 0;

    public RangedWeapon()
    {
        setAttackStat("DEX");
    }

    public int getAmmo()
    {
        return ammo;
    }

    public void setAmmo(int ammo)
    {
        this.ammo = ammo;
    }
}
