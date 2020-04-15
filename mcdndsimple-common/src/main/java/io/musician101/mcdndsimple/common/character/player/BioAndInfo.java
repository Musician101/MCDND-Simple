package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

public class BioAndInfo {

    @Nonnull
    private List<String> bio = new ArrayList<>();
    @Nonnull
    private String name = "Not Named";

    @Nonnull
    public List<String> getBio() {
        return bio;
    }

    public void setBio(@Nonnull List<String> bio) {
        this.bio = bio;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public static class Serializer extends BaseSerializer<BioAndInfo> {

        @Override
        public BioAndInfo deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            BioAndInfo bioAndInfo = new BioAndInfo();
            bioAndInfo.setName(deserialize(jsonObject, context, Keys.NAME));;
            bioAndInfo.setBio(deserialize(jsonObject, context, Keys.BIO));;
            return bioAndInfo;
        }

        @Override
        public JsonElement serialize(BioAndInfo src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.NAME, src.getName());
            serialize(jsonObject, context, Keys.BIO, src.getBio());
            return jsonObject;
        }
    }
}
