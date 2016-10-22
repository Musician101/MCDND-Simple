package io.musician101.mcdndsimple.sponge.character;

//TODO needs dataserializable and builder for all
public class CharacterSheet
{
    private final BioAndInfo bioAndInfo;
    private final PlayerSheet playerSheet;
    private String clazz = null;
    private String name;
    private String race = null;

    public CharacterSheet(String name)
    {
        this.name = name;
        this.bioAndInfo = new BioAndInfo(name);
        this.playerSheet = new PlayerSheet();
    }

    public BioAndInfo getBioAndInfo()
    {
        return bioAndInfo;
    }

    public String getClazz()
    {
        return clazz;
    }

    public String getName()
    {
        return name;
    }

    public PlayerSheet getPlayerSheet()
    {
        return playerSheet;
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
