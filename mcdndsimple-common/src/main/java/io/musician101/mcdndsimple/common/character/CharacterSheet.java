package io.musician101.mcdndsimple.common.character;

public class CharacterSheet
{
    private BioAndInfo bioAndInfo = new BioAndInfo();
    private PlayerSheet playerSheet = new PlayerSheet();
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

    public PlayerSheet getPlayerSheet()
    {
        return playerSheet;
    }

    public void setBioAndInfo(BioAndInfo bioAndInfo)
    {
        this.bioAndInfo = bioAndInfo;
    }

    public void setPlayerSheet(PlayerSheet playerSheet)
    {
        this.playerSheet = playerSheet;
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
