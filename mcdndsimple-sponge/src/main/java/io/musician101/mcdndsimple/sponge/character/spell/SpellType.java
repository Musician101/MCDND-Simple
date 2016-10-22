package io.musician101.mcdndsimple.sponge.character.spell;

public enum SpellType
{
    ABJURATION("Abjuration"),
    CONJURATION("Conjuration"),
    DIVINATION("Divination"),
    ENCHANTMENT("Enchantment"),
    EVOCATION("Evocation"),
    ILLUSION("Illusion"),
    NECROMANCY("Necromancy"),
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
