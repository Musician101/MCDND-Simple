package io.musician101.mcdndsimple.common.character;

public class CoreStats
{
    private AbilityScore charisma = new AbilityScore("Charisma", "cha");
    private AbilityScore constitution = new AbilityScore("Constitution", "con");
    private AbilityScore dexterity = new AbilityScore("Dexterity", "dex");
    private AbilityScore intelligence = new AbilityScore("Intelligence", "int");
    private AbilityScore strength = new AbilityScore("Strength", "str");
    private AbilityScore wisdom = new AbilityScore("Wisdom", "wis");

    public AbilityScore getCharisma()
    {
        return charisma;
    }

    public AbilityScore getConstitution()
    {
        return constitution;
    }

    public AbilityScore getDexterity()
    {
        return dexterity;
    }

    public AbilityScore getIntelligence()
    {
        return intelligence;
    }

    public AbilityScore getStrength()
    {
        return strength;
    }

    public AbilityScore getWisdom()
    {
        return wisdom;
    }

    public void setCharisma(AbilityScore charisma)
    {
        this.charisma = charisma;
    }

    public void setConstitution(AbilityScore constitution)
    {
        this.constitution = constitution;
    }

    public void setDexterity(AbilityScore dexterity)
    {
        this.dexterity = dexterity;
    }

    public void setIntelligence(AbilityScore intelligence)
    {
        this.intelligence = intelligence;
    }

    public void setStrength(AbilityScore strength)
    {
        this.strength = strength;
    }

    public void setWisdom(AbilityScore wisdom)
    {
        this.wisdom = wisdom;
    }
}
