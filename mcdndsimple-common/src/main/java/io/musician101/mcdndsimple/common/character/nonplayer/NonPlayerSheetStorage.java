package io.musician101.mcdndsimple.common.character.nonplayer;

import io.musician101.mcdndsimple.common.character.CharacterStorage;
import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

public abstract class NonPlayerSheetStorage<C, D extends MCDNDDeserializer<C>, S extends MCDNDSerializer<C>> extends CharacterStorage<C, D, S, NonPlayerSheet> {

    public NonPlayerSheetStorage(File storageDir, S serializer, D deserializer) {
        super(storageDir, serializer, deserializer);
        load();
    }

    @Override
    public Optional<NonPlayerSheet> createNewCharacter(UUID uuid, String name) {
        if (!getCharacter(uuid, name).isPresent()) {
            NonPlayerSheet nonPlayerSheet = new NonPlayerSheet();
            nonPlayerSheet.setName(name);
            return Optional.of(nonPlayerSheet);
        }

        return Optional.empty();
    }

    @Override
    protected NonPlayerSheet deserialize(C data) {
        return deserializer.deserializeNPC(data);
    }

    @Override
    protected C serialize(NonPlayerSheet nonPlayerSheet) {
        return serializer.serialize(nonPlayerSheet);
    }
}
