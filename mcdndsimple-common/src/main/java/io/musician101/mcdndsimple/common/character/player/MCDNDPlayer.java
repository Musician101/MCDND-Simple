package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class MCDNDPlayer extends AbstractPlayer {

    @Nonnull
    private BioAndInfo bioAndInfo = new BioAndInfo();
    @Nonnull
    private CharacterSheet characterSheet = new CharacterSheet();

    public MCDNDPlayer(@Nonnull String id) {
        super(id);
    }

    public MCDNDPlayer(@Nonnull String id, @Nonnull String clazz, @Nonnull String name, @Nonnull String race) {
        super(id, clazz, name, race);
    }

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

    public static class Serializer extends BaseSerializer<MCDNDPlayer> {

        @Override
        public MCDNDPlayer deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MCDNDPlayer mcdndPlayer = new MCDNDPlayer(jsonObject.get("id").getAsString());
            mcdndPlayer.setControllers(deserialize(jsonObject, context, Keys.CONTROLLERS));
            mcdndPlayer.setBioAndInfo(deserialize(jsonObject, context, Keys.BIO_AND_INFO));
            mcdndPlayer.setCharacterSheet(deserialize(jsonObject, context, Keys.CHARACTER_SHEET));
            mcdndPlayer.setClazz(deserialize(jsonObject, context, Keys.CLASS));
            mcdndPlayer.setName(deserialize(jsonObject, context, Keys.NAME));
            mcdndPlayer.setRace(deserialize(jsonObject, context, Keys.RACE));
            return mcdndPlayer;
        }

        @Override
        public JsonElement serialize(MCDNDPlayer src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.getID());
            serialize(jsonObject, context, Keys.CONTROLLERS, src.getControllers());
            serialize(jsonObject, context, Keys.CLASS, src.getClazz());
            serialize(jsonObject, context, Keys.NAME, src.getName());
            serialize(jsonObject, context, Keys.RACE, src.getRace());
            serialize(jsonObject, context, Keys.BIO_AND_INFO, src.getBioAndInfo());
            serialize(jsonObject, context, Keys.CHARACTER_SHEET, src.getCharacterSheet());
            return jsonObject;
        }
    }
}
