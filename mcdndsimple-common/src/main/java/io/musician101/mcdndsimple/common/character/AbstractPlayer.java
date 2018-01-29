package io.musician101.mcdndsimple.common.character;

public abstract class AbstractPlayer {

    private String clazz = "";
    private String name = "Your Name Here";
    private String race = "";

    public String getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
