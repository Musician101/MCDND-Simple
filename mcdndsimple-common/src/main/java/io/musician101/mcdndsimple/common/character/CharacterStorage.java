package io.musician101.mcdndsimple.common.character;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

public abstract class CharacterStorage<T extends AbstractPlayer> {

    @Nonnull
    protected final List<T> data = new ArrayList<>();
    @Nonnull
    protected final File storageDir;

    protected CharacterStorage(@Nonnull File storageDir) {
        this.storageDir = storageDir;
        load();
    }

    @Nonnull
    public abstract Optional<T> createNewCharacter(@Nonnull UUID uuid, @Nonnull String name);

    @Nonnull
    public Optional<T> getCharacter(@Nonnull UUID uuid, @Nonnull String name) {
        return data.stream().filter(sheet -> sheet.getName().equals(name) && sheet.isController(uuid)).findFirst();
    }

    @Nonnull
    public List<T> getCharacters() {
        return data;
    }

    public abstract void load();

    public void remove(@Nonnull T value) {
        data.remove(value);
    }

    public void removeFrom(@Nonnull T value, @Nonnull UUID uuid) {
        value.removeController(uuid);
    }

    public abstract void save();
}
