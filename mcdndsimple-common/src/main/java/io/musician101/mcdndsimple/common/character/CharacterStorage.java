package io.musician101.mcdndsimple.common.character;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import java.io.File;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

public abstract class CharacterStorage<T extends AbstractPlayer> {

    protected final Multimap<T, UUID> data = ArrayListMultimap.create();
    protected final File storageDir;

    protected CharacterStorage(File storageDir) {
        this.storageDir = storageDir;
        load();
    }

    public abstract Optional<T> createNewCharacter(UUID uuid, String name);

    public Optional<T> getCharacter(UUID uuid, String name) {
        return data.keys().stream().filter(sheet -> name.equals(sheet.getName()) && data.get(sheet).contains(uuid)).findFirst();
    }

    public Multimap<T, UUID> getCharacters() {
        return data;
    }

    public void remove(T value) {
        data.removeAll(value);
    }

    public void removeFrom(T value, UUID uuid) {
        data.remove(value, uuid);
    }

    public abstract void load();

    public abstract void save();

    protected interface Serializer<T extends AbstractPlayer> extends JsonDeserializer<Entry<T, List<UUID>>>, JsonSerializer<Entry<T, List<UUID>>> {

    }
}
