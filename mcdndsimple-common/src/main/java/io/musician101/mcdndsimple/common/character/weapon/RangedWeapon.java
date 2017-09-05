package io.musician101.mcdndsimple.common.character.weapon;

public class RangedWeapon extends AbstractWeapon {

    private int ammo = 0;

    public RangedWeapon() {
        setAttackStat(WeaponAttackStat.DEX);
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
