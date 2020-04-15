package io.musician101.mcdndsimple.common.character.nonplayer;

import io.musician101.mcdndsimple.common.character.CharacterStorage;
import java.io.File;
import java.util.Optional;
import javax.annotation.Nonnull;

public abstract class NonPlayerStorage extends CharacterStorage<NonPlayer> {

    public NonPlayerStorage(File storageDir) {
        super(storageDir);
        load();
    }

    @Nonnull
    @Override
    public Optional<NonPlayer> createNewCharacter(@Nonnull String id) {
        if (getCharacter(id).isPresent()) {
            return Optional.empty();
        }

        NonPlayer nonPlayerSheet = new NonPlayer(id);
        nonPlayerSheet.setName(id);
        characters.add(nonPlayerSheet);
        return Optional.of(nonPlayerSheet);
    }
}
