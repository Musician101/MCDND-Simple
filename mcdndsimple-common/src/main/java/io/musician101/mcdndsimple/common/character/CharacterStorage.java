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
import javax.annotation.Nonnull;

public abstract class CharacterStorage<T extends AbstractPlayer> {

    @Nonnull
    protected final Multimap<T, UUID> data = ArrayListMultimap.create();
    @Nonnull
    protected final File storageDir;

    protected CharacterStorage(@Nonnull File storageDir) {
        this.storageDir = storageDir;
        load();
    }

    @Nonnull
    public abstract Optional<T> createNewCharacter(@Nonnull UUID uuid, @Nonnull String name);

    @Nonnull
    public Optional<T> getCharacter(@Nonnull UUID uuid, @Nonnull String name) {
        return data.keys().stream().filter(sheet -> name.equals(sheet.getName()) && data.get(sheet).contains(uuid)).findFirst();
    }

    @Nonnull
    public Multimap<T, UUID> getCharacters() {
        return data;
    }

    public abstract void load();

    public void remove(@Nonnull T value) {
        data.removeAll(value);
    }

    public void removeFrom(@Nonnull T value, @Nonnull UUID uuid) {
        data.remove(value, uuid);
    }

    public abstract void save();

    protected interface Serializer<T extends AbstractPlayer> extends JsonDeserializer<Entry<T, List<UUID>>>, JsonSerializer<Entry<T, List<UUID>>> {

    }
}
