package io.musician101.mcdndsimple.common.character;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

public abstract class CharacterSheetStorage<S extends MCDNDSerializer<C>, D extends MCDNDDeserializer<C>, C> {

    protected final Multimap<PlayerSheet, UUID> playerSheets = ArrayListMultimap.create();
    protected final File storageDir;
    protected D deserializer;
    protected S serializer;

    protected CharacterSheetStorage(File storageDir, S serializer, D deserializer) {
        this.storageDir = storageDir;
        this.serializer = serializer;
        this.deserializer = deserializer;
        load();
    }

    public void createNewCharacterSheet(UUID uuid) {
        if (!playerSheets.containsKey(uuid)) {
            playerSheets.put(new PlayerSheet(), uuid);
        }
    }

    protected PlayerSheet deserialize(C data) {
        return deserializer.deserialize(data);
    }

    public Optional<PlayerSheet> getCharacterSheet(UUID uuid, String character) {
        return playerSheets.keys().stream().filter(playerSheet -> character.equalsIgnoreCase(playerSheet.getName()) && playerSheets.get(playerSheet).contains(uuid)).findFirst();
    }

    public Multimap<PlayerSheet, UUID> getCharacterSheets() {
        return playerSheets;
    }

    public abstract void load();

    public void removePlayerSheet(PlayerSheet playerSheet) {
        playerSheets.removeAll(playerSheet);
    }

    public void removeFromPlayerSheet(UUID uuid, PlayerSheet playerSheet) {
        playerSheets.remove(playerSheet, uuid);
    }

    public abstract void save();

    protected C serialize(PlayerSheet playerSheet) {
        return serializer.serialize(playerSheet);
    }
}
