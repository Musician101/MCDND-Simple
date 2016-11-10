package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CharacterSheetStorage<M extends MCDNDSerializer<S>, N extends MCDNDDeserializer<S>, S>
{
    protected M serializer;
    protected N deserializer;
    protected final File storageDir;
    protected final Map<UUID, CharacterSheet> map = new HashMap<>();

    protected CharacterSheetStorage(File storageDir, M serializer, N deserializer)
    {
        this.storageDir = storageDir;
        this.serializer = serializer;
        this.deserializer = deserializer;
        load();
    }

    public CharacterSheet getCharacterSheet(UUID uuid)
    {
        return map.containsKey(uuid) ? map.get(uuid) : map.put(uuid, new CharacterSheet());
    }

    public Map<UUID, CharacterSheet> getCharacterSheets()
    {
        return map;
    }

    public void createNewCharacterSheet(UUID uuid)
    {
        if (!map.containsKey(uuid))
            map.put(uuid, new CharacterSheet());
    }

    public void removeCharacterSheet(UUID uuid)
    {
        map.remove(uuid);
    }

    public abstract void load();

    public abstract void save();

    protected CharacterSheet deserialize(S data)
    {
        return deserializer.deserialize(data);
    }

    protected S serialize(CharacterSheet characterSheet)
    {
        return serializer.serialize(characterSheet);
    }
}
