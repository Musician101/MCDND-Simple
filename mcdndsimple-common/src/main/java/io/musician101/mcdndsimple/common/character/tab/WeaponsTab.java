package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.weapon.RangedWeapon;
import java.util.ArrayList;
import java.util.List;

public class WeaponsTab {

    private List<MeleeWeapon> meleeWeapons = new ArrayList<>();
    private List<RangedWeapon> rangedWeapons = new ArrayList<>();

    public void addMeleeWeapon(MeleeWeapon meleeWeapon) {
        meleeWeapons.add(meleeWeapon);
    }

    public void addRangedWeapon(RangedWeapon rangedWeapon) {
        rangedWeapons.add(rangedWeapon);
    }

    public List<MeleeWeapon> getMeleeWeapons() {
        return meleeWeapons;
    }

    public void setMeleeWeapons(List<MeleeWeapon> meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    public List<RangedWeapon> getRangedWeapons() {
        return rangedWeapons;
    }

    public void setRangedWeapons(List<RangedWeapon> rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
    }

    public void removeMeleeWeapon(MeleeWeapon meleeWeapon) {
        meleeWeapons.remove(meleeWeapon);
    }

    public void removeRangedWeapon(RangedWeapon rangedWeapon) {
        rangedWeapons.remove(rangedWeapon);
    }
}
