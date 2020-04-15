package io.musician101.mcdndsimple.common.character;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;

public abstract class AbstractPlayer {

    @Nonnull
    private String clazz;
    @Nonnull
    private List<UUID> controllers = new ArrayList<>();
    @Nonnull
    private String id;
    @Nonnull
    private String name;
    @Nonnull
    private String race;

    protected AbstractPlayer(@Nonnull String id) {
        this(id, "", "Your Name here", "");
    }

    protected AbstractPlayer(@Nonnull String id, @Nonnull String clazz, @Nonnull String name, @Nonnull String race) {
        this.id = id;
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

    @Nonnull
    public String getID() {
        return id;
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

    public void setId(@Nonnull String id) {
        this.id = id;
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
        controllers.remove(uuid);
    }
}
