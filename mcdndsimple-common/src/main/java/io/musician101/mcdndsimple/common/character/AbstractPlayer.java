package io.musician101.mcdndsimple.common.character;

import javax.annotation.Nonnull;

public abstract class AbstractPlayer {

    @Nonnull
    private String clazz = "";
    @Nonnull
    private String name = "Your Name Here";
    @Nonnull
    private String race = "";

    protected AbstractPlayer() {

    }

    protected AbstractPlayer(@Nonnull String clazz, @Nonnull String name, @Nonnull String race) {
        this.clazz = clazz;
        this.name = name;
        this.race = race;
    }

    @Nonnull
    public String getClazz() {
        return clazz;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getRace() {
        return race;
    }

    public void setClazz(@Nonnull String clazz) {
        this.clazz = clazz;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public void setRace(@Nonnull String race) {
        this.race = race;
    }
}
