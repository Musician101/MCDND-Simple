package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.CharacterStorage;
import java.io.File;
import java.util.Optional;
import javax.annotation.Nonnull;

public abstract class PlayerStorage extends CharacterStorage<MCDNDPlayer> {

    public PlayerStorage(@Nonnull File storageDir) {
        super(storageDir);
        load();
    }

    @Nonnull
    @Override
    public Optional<MCDNDPlayer> createNewCharacter(@Nonnull String id) {
        if (getCharacter(id).isPresent()) {
            return Optional.empty();
        }

        MCDNDPlayer MCDNDPlayer = new MCDNDPlayer(id);
        characters.add(MCDNDPlayer);
        return Optional.of(MCDNDPlayer);
    }
}
