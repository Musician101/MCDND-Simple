package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import io.musician101.mcdndsimple.common.character.CharacterStorage;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.adapter.AdapterOf;
import io.musician101.musicianlibrary.java.json.adapter.AdapterType;
import java.io.File;
import java.lang.reflect.Type;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;

public abstract class NonPlayerSheetStorage extends CharacterStorage<NonPlayer> {

    public NonPlayerSheetStorage(File storageDir) {
        super(storageDir);
        load();
    }

    @Nonnull
    @Override
    public Optional<NonPlayer> createNewCharacter(@Nonnull UUID uuid, @Nonnull String name) {
        if (!getCharacter(uuid, name).isPresent()) {
            NonPlayer nonPlayerSheet = new NonPlayer();
            nonPlayerSheet.setName(name);
            return Optional.of(nonPlayerSheet);
        }

        return Optional.empty();
    }

    @AdapterOf
    public static class Serializer implements CharacterStorage.Serializer<NonPlayer> {

        @AdapterType
        public static final TypeToken<Entry<NonPlayer, List<UUID>>> ENTRY_CLASS = new TypeToken<Entry<NonPlayer, List<UUID>>>() {

        };

        @Override
        public Entry<NonPlayer, List<UUID>> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            List<UUID> controllers = Keys.CONTROLLERS.deserializeFromParent(jsonObject, context).orElse(Collections.emptyList());
            NonPlayer nonPlayerSheet = context.deserialize(jsonObject, NonPlayer.class);
            return new SimpleEntry<>(nonPlayerSheet, controllers);
        }

        @Override
        public JsonElement serialize(Entry<NonPlayer, List<UUID>> src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = context.serialize(src.getKey()).getAsJsonObject();
            Keys.CONTROLLERS.serialize(src.getValue(), jsonObject, context);
            return jsonObject;
        }
    }
}
