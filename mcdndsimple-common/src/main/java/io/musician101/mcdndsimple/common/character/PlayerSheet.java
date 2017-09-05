package io.musician101.mcdndsimple.common.character;

public class PlayerSheet
{
    private BioAndInfo bioAndInfo = new BioAndInfo();
    private CharacterSheet characterSheet = new CharacterSheet();
    private String clazz = "";
    private String name = "Your Name Here";
    private String race = "";

    public String getClazz()
    {
        return clazz;
    }

    public BioAndInfo getBioAndInfo()
    {
        return bioAndInfo;
    }

    public CharacterSheet getCharacterSheet()
    {
        return characterSheet;
    }

    public void setBioAndInfo(BioAndInfo bioAndInfo)
    {
        this.bioAndInfo = bioAndInfo;
    }

    public void setCharacterSheet(CharacterSheet characterSheet)
    {
        this.characterSheet = characterSheet;
    }

    public String getName()
    {
        return name;
    }

    public String getRace()
    {
        return race;
    }

    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRace(String race)
    {
        this.race = race;
    }
}
