package io.musician101.mcdndsimple.common.character;

public class PlayerSheet {

    private BioAndInfo bioAndInfo = new BioAndInfo();
    private CharacterSheet characterSheet = new CharacterSheet();
    private String clazz = "";
    private String name = "Your Name Here";
    private String race = "";

    public BioAndInfo getBioAndInfo() {
        return bioAndInfo;
    }

    public void setBioAndInfo(BioAndInfo bioAndInfo) {
        this.bioAndInfo = bioAndInfo;
    }

    public CharacterSheet getCharacterSheet() {
        return characterSheet;
    }

    public void setCharacterSheet(CharacterSheet characterSheet) {
        this.characterSheet = characterSheet;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
