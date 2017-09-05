package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CharacterSheetStorage<M extends MCDNDSerializer<S>, N extends MCDNDDeserializer<S>, S> {

    protected final Map<UUID, PlayerSheet> map = new HashMap<>();
    protected final File storageDir;
    protected N deserializer;
    protected M serializer;

    protected CharacterSheetStorage(File storageDir, M serializer, N deserializer) {
        this.storageDir = storageDir;
        this.serializer = serializer;
        this.deserializer = deserializer;
        load();
    }

    public void createNewCharacterSheet(UUID uuid) {
        if (!map.containsKey(uuid)) {
            map.put(uuid, new PlayerSheet());
        }
    }

    protected PlayerSheet deserialize(S data) {
        return deserializer.deserialize(data);
    }

    public PlayerSheet getCharacterSheet(UUID uuid) {
        return map.containsKey(uuid) ? map.get(uuid) : map.put(uuid, new PlayerSheet());
    }

    public Map<UUID, PlayerSheet> getCharacterSheets() {
        return map;
    }

    public abstract void load();

    public void removeCharacterSheet(UUID uuid) {
        map.remove(uuid);
    }

    public abstract void save();

    protected S serialize(PlayerSheet playerSheet) {
        return serializer.serialize(playerSheet);
    }
}
