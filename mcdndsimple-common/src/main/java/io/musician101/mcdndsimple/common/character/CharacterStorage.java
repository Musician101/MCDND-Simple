package io.musician101.mcdndsimple.common.character;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

public abstract class CharacterStorage<T extends AbstractPlayer> {

    @Nonnull
    protected final List<T> characters = new ArrayList<>();
    @Nonnull
    protected final File storageDir;

    protected CharacterStorage(@Nonnull File storageDir) {
        this.storageDir = storageDir;
        load();
    }

    @Nonnull
    public abstract Optional<T> createNewCharacter(@Nonnull String id);

    public boolean delete(@Nonnull UUID uuid, @Nonnull String id) {
        return characters.removeIf(nonPlayer -> isDM(uuid) && id.equals(nonPlayer.getID()));
    }

    @Nonnull
    public Optional<T> getCharacter(@Nonnull String id) {
        return characters.stream().filter(sheet -> sheet.getID().equals(id)).findFirst();
    }

    @Nonnull
    public List<T> getCharacters(@Nonnull UUID uuid) {
        return characters.stream().filter(character -> isDM(uuid) || character.getControllers().contains(uuid)).collect(Collectors.toList());
    }

    @Nonnull
    public List<T> getCharacters() {
        return characters;
    }

    public abstract void load();

    public void remove(@Nonnull T value) {
        characters.remove(value);
    }

    public void removeFrom(@Nonnull T value, @Nonnull UUID uuid) {
        value.removeController(uuid);
    }

    public abstract void save();

    protected abstract boolean isDM(@Nonnull UUID uuid);
}
