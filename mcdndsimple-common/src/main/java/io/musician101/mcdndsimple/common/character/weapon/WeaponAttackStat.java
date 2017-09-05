package io.musician101.mcdndsimple.common.character.weapon;

public enum WeaponAttackStat {
    CHA,
    CON,
    DEX,
    FINESSE("Finesse"),
    INT,
    STR,
    WIS;

    private final String name;

    WeaponAttackStat() {
        this(null);
    }

    WeaponAttackStat(String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? toString() : name;
    }
}
