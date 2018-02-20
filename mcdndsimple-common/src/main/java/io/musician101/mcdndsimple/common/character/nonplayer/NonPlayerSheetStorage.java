package io.musician101.mcdndsimple.common.character.nonplayer;

import io.musician101.mcdndsimple.common.character.CharacterStorage;
import java.io.File;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

public abstract class NonPlayerSheetStorage extends CharacterStorage<NonPlayer> {

    public NonPlayerSheetStorage(File storageDir) {
        super(storageDir);
        load();
    }

    @Nonnull
    @Override
    public Optional<NonPlayer> createNewCharacter(@Nonnull UUID uuid, @Nonnull String name) {
        if (!getCharacter(uuid, name).isPresent()) {
            NonPlayer nonPlayerSheet = new NonPlayer();
            nonPlayerSheet.setName(name);
            return Optional.of(nonPlayerSheet);
        }

        return Optional.empty();
    }
}
