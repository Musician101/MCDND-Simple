package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkills;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class NonPlayer extends AbstractPlayer {

    @Nonnull
    private NonPlayerActions nonPlayerActions = new NonPlayerActions();
    @Nonnull
    private NonPlayerSheet nonPlayerSheet = new NonPlayerSheet();
    @Nonnull
    private NonPlayerSkills nonPlayerSkills = new NonPlayerSkills();
    @Nonnull
    private TraitsBackground traitsBackground = new TraitsBackground();
    @Nonnull
    private final Initiative initiative = new Initiative();

    public NonPlayer(@Nonnull String id) {
        super(id);
    }

    public NonPlayer(@Nonnull String id, @Nonnull String clazz, @Nonnull String name, @Nonnull String race) {
        super(id, clazz, name, race);
    }

    @Nonnull
    public Initiative getInitiative() {
        return initiative;
    }

    @Nonnull
    public NonPlayerActions getNonPlayerActions() {
        return nonPlayerActions;
    }

    private void setNonPlayerActions(@Nonnull NonPlayerActions nonPlayerActions) {
        this.nonPlayerActions = nonPlayerActions;
    }

    @Nonnull
    public NonPlayerSheet getNonPlayerSheet() {
        return nonPlayerSheet;
    }

    private void setNonPlayerSheet(@Nonnull NonPlayerSheet NonPlayerSheet) {
        this.nonPlayerSheet = NonPlayerSheet;
    }

    public NonPlayerSkills getSkills() {
        return nonPlayerSkills;
    }

    private void setSkills(@Nonnull NonPlayerSkills nonPlayerSkills) {
        this.nonPlayerSkills = nonPlayerSkills;
    }

    @Nonnull
    public TraitsBackground getTraitsBackground() {
        return traitsBackground;
    }

    private void setTraitsBackground(@Nonnull TraitsBackground traitsBackground) {
        this.traitsBackground = traitsBackground;
    }

    public static class Serializer extends BaseSerializer<NonPlayer> {

        @Override
        public NonPlayer deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayer nonPlayerSheet = new NonPlayer(jsonObject.get("id").getAsString());
            nonPlayerSheet.setControllers(deserialize(jsonObject, context, Keys.CONTROLLERS));
            nonPlayerSheet.setNonPlayerActions(deserialize(jsonObject, context, Keys.NON_PLAYER_ACTIONS));
            nonPlayerSheet.setNonPlayerSheet(deserialize(jsonObject, context, Keys.NON_PLAYER_SHEET));
            nonPlayerSheet.setSkills(deserialize(jsonObject, context, Keys.NON_PLAYER_SKILLS));
            nonPlayerSheet.setTraitsBackground(deserialize(jsonObject, context, Keys.TRAITS_BACKGROUND));
            nonPlayerSheet.getInitiative().setBonus(deserialize(jsonObject, context, Keys.INITIATIVE_BONUS));
            return nonPlayerSheet;
        }

        @Override
        public JsonElement serialize(NonPlayer src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.CONTROLLERS, src.getControllers());
            serialize(jsonObject, context, Keys.NON_PLAYER_ACTIONS, src.getNonPlayerActions());
            serialize(jsonObject, context, Keys.NON_PLAYER_SHEET, src.getNonPlayerSheet());
            serialize(jsonObject, context, Keys.NON_PLAYER_SKILLS, src.getSkills());
            serialize(jsonObject, context, Keys.TRAITS_BACKGROUND, src.getTraitsBackground());
            serialize(jsonObject, context, Keys.INITIATIVE_BONUS, src.getInitiative().getBonus());
            return jsonObject;
        }
    }
}
