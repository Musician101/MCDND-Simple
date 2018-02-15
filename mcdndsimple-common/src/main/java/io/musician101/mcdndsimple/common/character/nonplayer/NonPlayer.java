package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkills;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;

@TypeOf(NonPlayer.Serializer.class)
public class NonPlayer extends AbstractPlayer {

    private NonPlayerActions nonPlayerActions = new NonPlayerActions();
    private NonPlayerSheet nonPlayerSheet = new NonPlayerSheet();
    private NonPlayerSkills nonPlayerSkills = new NonPlayerSkills();
    private TraitsBackground traitsBackground = new TraitsBackground();

    public NonPlayerActions getNonPlayerActions() {
        return nonPlayerActions;
    }

    public void setNonPlayerActions(NonPlayerActions nonPlayerActions) {
        this.nonPlayerActions = nonPlayerActions;
    }

    public NonPlayerSheet getNonPlayerSheet() {
        return nonPlayerSheet;
    }

    public void setNonPlayerSheet(NonPlayerSheet NonPlayerSheet) {
        this.nonPlayerSheet = NonPlayerSheet;
    }

    public NonPlayerSkills getSkills() {
        return nonPlayerSkills;
    }

    public void setSkills(NonPlayerSkills nonPlayerSkills) {
        this.nonPlayerSkills = nonPlayerSkills;
    }

    public TraitsBackground getTraitsBackground() {
        return traitsBackground;
    }

    public void setTraitsBackground(TraitsBackground traitsBackground) {
        this.traitsBackground = traitsBackground;
    }

    public static class Serializer implements JsonDeserializer<NonPlayer>, JsonSerializer<NonPlayer> {

        @Override
        public NonPlayer deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayer nonPlayerSheet = new NonPlayer();
            JsonKeyProcessor.<JsonObject, NonPlayerActions>getJsonKey(Keys.NON_PLAYER_ACTIONS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setNonPlayerActions));
            JsonKeyProcessor.<JsonObject, NonPlayerSheet>getJsonKey(Keys.NON_PLAYER_SHEET).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setNonPlayerSheet));
            JsonKeyProcessor.<JsonObject, NonPlayerSkills>getJsonKey(Keys.NON_PLAYER_SKILLS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setSkills));
            JsonKeyProcessor.<JsonObject, TraitsBackground>getJsonKey(Keys.TRAITS_BACKGROUND).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerSheet::setTraitsBackground));
            return nonPlayerSheet;
        }

        @Override
        public JsonElement serialize(NonPlayer src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, NonPlayerActions>getJsonKey(Keys.NON_PLAYER_ACTIONS).ifPresent(jsonKey -> jsonKey.serialize(src.getNonPlayerActions(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, NonPlayerSheet>getJsonKey(Keys.NON_PLAYER_SHEET).ifPresent(jsonKey -> jsonKey.serialize(src.getNonPlayerSheet(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, NonPlayerSkills>getJsonKey(Keys.NON_PLAYER_SKILLS).ifPresent(jsonKey -> jsonKey.serialize(src.getSkills(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, TraitsBackground>getJsonKey(Keys.TRAITS_BACKGROUND).ifPresent(jsonKey -> jsonKey.serialize(src.getTraitsBackground(), jsonObject, context));
            return jsonObject;
        }
    }
}
