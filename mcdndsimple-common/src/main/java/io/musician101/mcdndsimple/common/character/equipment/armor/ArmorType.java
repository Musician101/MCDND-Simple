package io.musician101.mcdndsimple.common.character.equipment.armor;

public enum ArmorType {
    LIGHT("Light"), MEDIUM("Medium"), HEAVY("Heavy"), SHIELD("Shield"), NONE("None");

    private final String name;

    ArmorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
