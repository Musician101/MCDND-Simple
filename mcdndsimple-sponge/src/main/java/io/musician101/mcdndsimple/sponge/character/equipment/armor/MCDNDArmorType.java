package io.musician101.mcdndsimple.sponge.character.equipment.armor;

public enum MCDNDArmorType
{
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy");

    private final String name;

    MCDNDArmorType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
