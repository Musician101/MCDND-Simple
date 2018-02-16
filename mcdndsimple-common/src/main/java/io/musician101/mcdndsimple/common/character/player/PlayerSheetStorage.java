package io.musician101.mcdndsimple.common.character.player;

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

public abstract class PlayerSheetStorage extends CharacterStorage<PlayerSheet> {

    public PlayerSheetStorage(@Nonnull File storageDir) {
        super(storageDir);
        load();
    }

    @Nonnull
    @Override
    public Optional<PlayerSheet> createNewCharacter(@Nonnull UUID uuid, @Nonnull String name) {
        if (!getCharacter(uuid, name).isPresent()) {
            PlayerSheet playerSheet = new PlayerSheet();
            playerSheet.setName(name);
            return Optional.of(playerSheet);
        }

        return Optional.empty();
    }

    @AdapterOf
    public static class Serializer implements CharacterStorage.Serializer<PlayerSheet> {

        @AdapterType
        public static final TypeToken<Entry<PlayerSheet, List<UUID>>> ENTRY_CLASS = new TypeToken<Entry<PlayerSheet, List<UUID>>>() {

        };

        @Override
        public Entry<PlayerSheet, List<UUID>> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            List<UUID> controllers = Keys.CONTROLLERS.deserializeFromParent(jsonObject, context).orElse(Collections.emptyList());
            PlayerSheet nonPlayerSheet = context.deserialize(jsonObject, PlayerSheet.class);
            return new SimpleEntry<>(nonPlayerSheet, controllers);
        }

        @Override
        public JsonElement serialize(Entry<PlayerSheet, List<UUID>> src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = context.serialize(src.getKey()).getAsJsonObject();
            Keys.CONTROLLERS.serialize(src.getValue(), jsonObject, context);
            return jsonObject;
        }
    }
}
