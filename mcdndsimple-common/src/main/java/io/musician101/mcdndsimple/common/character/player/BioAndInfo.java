package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

//TODO see if it's possible to have a picture of some sort
@JsonKey(key = "bio_and_info", typeAdapter = BioAndInfo.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<BioAndInfo>, JsonSerializer<BioAndInfo> {

        @Override
        public BioAndInfo deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            BioAndInfo bioAndInfo = new BioAndInfo();
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(bioAndInfo::setName);
            Keys.BIO.deserializeFromParent(jsonObject, context).ifPresent(bioAndInfo::setBio);
            return bioAndInfo;
        }

        @Override
        public JsonElement serialize(BioAndInfo src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            Keys.BIO.serialize(src.getBio(), jsonObject, context);
            return jsonObject;
        }
    }
}
