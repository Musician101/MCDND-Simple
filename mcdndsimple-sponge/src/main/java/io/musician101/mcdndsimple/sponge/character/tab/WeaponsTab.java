package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.sponge.character.weapon.RangedWeapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponsTab
{
    private List<MeleeWeapon> meleeWeapons = new ArrayList<>();
    private List<RangedWeapon> rangedWeapons = new ArrayList<>();

    public List<MeleeWeapon> getMeleeWeapons()
    {
        return meleeWeapons;
    }

    public List<RangedWeapon> getRangedWeapons()
    {
        return rangedWeapons;
    }

    public void setMeleeWeapons(List<MeleeWeapon> meleeWeapons)
    {
        this.meleeWeapons = meleeWeapons;
    }

    public void setRangedWeapons(List<RangedWeapon> rangedWeapons)
    {
        this.rangedWeapons = rangedWeapons;
    }
}
