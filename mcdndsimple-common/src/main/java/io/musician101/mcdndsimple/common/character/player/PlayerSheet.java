package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

@TypeOf(PlayerSheet.Serializer.class)
public class PlayerSheet extends AbstractPlayer {

    @Nonnull
    private BioAndInfo bioAndInfo = new BioAndInfo();
    @Nonnull
    private CharacterSheet characterSheet = new CharacterSheet();

    @Nonnull
    public BioAndInfo getBioAndInfo() {
        return bioAndInfo;
    }

    public void setBioAndInfo(@Nonnull BioAndInfo bioAndInfo) {
        this.bioAndInfo = bioAndInfo;
    }

    @Nonnull
    public CharacterSheet getCharacterSheet() {
        return characterSheet;
    }

    public void setCharacterSheet(@Nonnull CharacterSheet characterSheet) {
        this.characterSheet = characterSheet;
    }

    @Override
    public void setName(@Nonnull String name) {
        super.setName(name);
        bioAndInfo.setName(name);
    }

    public static class Serializer implements JsonDeserializer<PlayerSheet>, JsonSerializer<PlayerSheet> {

        @Override
        public PlayerSheet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            PlayerSheet playerSheet = new PlayerSheet();
            JsonKeyProcessor.<JsonObject, BioAndInfo>getJsonKey(Keys.BIO_AND_INFO).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(playerSheet::setBioAndInfo));
            JsonKeyProcessor.<JsonObject, CharacterSheet>getJsonKey(Keys.CHARACTER_SHEET).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(playerSheet::setCharacterSheet));
            Keys.CLASS.deserializeFromParent(jsonObject, context).ifPresent(playerSheet::setClazz);
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(playerSheet::setName);
            Keys.RACE.deserializeFromParent(jsonObject, context).ifPresent(playerSheet::setRace);
            return playerSheet;
        }

        @Override
        public JsonElement serialize(PlayerSheet src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.CLASS.serialize(src.getClazz(), jsonObject, context);
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            Keys.RACE.serialize(src.getRace(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, BioAndInfo>getJsonKey(Keys.BIO_AND_INFO).ifPresent(jsonKey -> jsonKey.serialize(src.getBioAndInfo(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, CharacterSheet>getJsonKey(Keys.CHARACTER_SHEET).ifPresent(jsonKey -> jsonKey.serialize(src.getCharacterSheet(), jsonObject, context));
            return jsonObject;
        }
    }
}
