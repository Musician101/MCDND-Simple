package io.musician101.mcdndsimple.common.character;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

public abstract class CharacterStorage<C, D extends MCDNDDeserializer<C>, S extends MCDNDSerializer<C>, T extends AbstractPlayer> {

    protected final Multimap<T, UUID> data = ArrayListMultimap.create();
    protected final File storageDir;
    protected final D deserializer;
    protected final S serializer;

    protected CharacterStorage(File storageDir, S serializer, D deserializer) {
        this.storageDir = storageDir;
        this.serializer = serializer;
        this.deserializer = deserializer;
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

    protected  abstract T deserialize(C data);

    protected abstract C serialize(T t);
}
