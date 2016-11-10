package io.musician101.mcdndsimple.common.character.skill;

public enum SkillProficiency
{
    EXPERTISE("Expertise"),
    JACK_OF_ALL_TRADES("Jack of all Trades"),
    NONE("None"),
    YES("Yes");

    private final String name;

    SkillProficiency(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
