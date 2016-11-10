package io.musician101.mcdndsimple.common.character.spell;

public enum SpellType
{
    ABJURATION("Abjuration"),
    CONJURATION("Conjuration"),
    DIVINATION("Divination"),
    ENCHANTMENT("Enchantment"),
    EVOCATION("Evocation"),
    ILLUSION("Illusion"),
    NECROMANCY("Necromancy"),
    OTHER("Other"),
    TRANSMUTATION("Transmutation");

    private final String name;

    SpellType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
