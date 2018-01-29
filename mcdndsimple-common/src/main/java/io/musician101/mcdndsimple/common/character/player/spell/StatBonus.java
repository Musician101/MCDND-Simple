package io.musician101.mcdndsimple.common.character.player.spell;

public enum StatBonus {

    CHA,
    CON,
    DEX,
    INT,
    NONE("None"),
    STR,
    WIS;

    private String name;

    StatBonus() {
        this(null);
    }

    StatBonus(String name) {
        this.name = name;
    }

    public String getName() {
        return name == null ? toString() : name;
    }
}
