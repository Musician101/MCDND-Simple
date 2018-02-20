package io.musician101.mcdndsimple.common.character;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;

public abstract class AbstractPlayer {

    @Nonnull
    private String clazz = "";
    @Nonnull
    private List<UUID> controllers = new ArrayList<>();
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

    public void addController(@Nonnull UUID uuid) {
        if (!isController(uuid)) {
            controllers.add(uuid);
        }
    }

    @Nonnull
    public String getClazz() {
        return clazz;
    }

    public void setClazz(@Nonnull String clazz) {
        this.clazz = clazz;
    }

    @Nonnull
    public List<UUID> getControllers() {
        return controllers;
    }

    public void setControllers(@Nonnull List<UUID> controllers) {
        this.controllers = controllers;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getRace() {
        return race;
    }

    public void setRace(@Nonnull String race) {
        this.race = race;
    }

    public boolean isController(@Nonnull UUID uuid) {
        return controllers.contains(uuid);
    }

    public void removeController(@Nonnull UUID uuid) {
        if (!isController(uuid)) {
            controllers.remove(uuid);
        }
    }
}
