package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.CharacterStorage;
import java.io.File;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

public abstract class PlayerSheetStorage extends CharacterStorage<PlayerSheet> {

    public PlayerSheetStorage(@Nonnull File storageDir) {
        super(storageDir);
        load();
    }

    @Nonnull
    @Override
    public Optional<PlayerSheet> createNewCharacter(@Nonnull UUID uuid, @Nonnull String name) {
        if (!getCharacter(uuid, name).isPresent()) {
            PlayerSheet playerSheet = new PlayerSheet();
            playerSheet.setName(name);
            return Optional.of(playerSheet);
        }

        return Optional.empty();
    }
}
