package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.stream.Stream;
import javax.annotation.Nonnull;


public enum NonPlayerActionType {

    BONUS("Bonus"),
    LAIR("Lair"),
    LEGENDARY("Legendary"),
    NORMAL("Normal"),
    OTHER("Other"),
    REACTION("Reaction"),
    SPECIAL("Special");

    @Nonnull
    private final String name;

    NonPlayerActionType(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public static class Serializer extends BaseSerializer<NonPlayerActionType> {

        @Override
        public NonPlayerActionType deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(NonPlayerActionType.values()).filter(nonPlayerActionType -> nonPlayerActionType.getName().equals(json.getAsString())).findFirst().orElse(NonPlayerActionType.BONUS);
        }

        @Override
        public JsonElement serialize(NonPlayerActionType src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
