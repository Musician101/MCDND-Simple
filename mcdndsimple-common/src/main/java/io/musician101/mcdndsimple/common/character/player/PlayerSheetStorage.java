package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.CharacterStorage;
import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

public abstract class PlayerSheetStorage<C, D extends MCDNDDeserializer<C>, S extends MCDNDSerializer<C>> extends CharacterStorage<C, D, S, PlayerSheet> {

    protected PlayerSheetStorage(File storageDir, S serializer, D deserializer) {
        super(storageDir, serializer, deserializer);
    }

    @Override
    public Optional<PlayerSheet> createNewCharacter(UUID uuid, String name) {
        if (!getCharacter(uuid, name).isPresent()) {
            PlayerSheet playerSheet = new PlayerSheet();
            playerSheet.setName(name);
            return Optional.of(playerSheet);
        }

        return Optional.empty();
    }

    @Override
    protected PlayerSheet deserialize(C data) {
        return deserializer.deserializePC(data);
    }

    @Override
    protected C serialize(PlayerSheet playerSheet) {
        return serializer.serialize(playerSheet);
    }
}
