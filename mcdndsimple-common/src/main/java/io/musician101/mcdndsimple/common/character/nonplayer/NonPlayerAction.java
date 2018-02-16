package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

@TypeOf(NonPlayerAction.Serializer.class)
public class NonPlayerAction {

    @Nonnull
    private NonPlayerActionType actionType = NonPlayerActionType.NORMAL;
    @Nonnull
    private List<String> description = new ArrayList<>();
    @Nonnull
    private List<String> effect = new ArrayList<>();
    private boolean isMultiAttack = false;
    @Nonnull
    private String name = "";

    @Nonnull
    public NonPlayerActionType getActionType() {
        return actionType;
    }

    public void setActionType(@Nonnull NonPlayerActionType actionType) {
        this.actionType = actionType;
    }

    @Nonnull
    public List<String> getDescription() {
        return description;
    }

    public void setDescription(@Nonnull List<String> description) {
        this.description = description;
    }

    @Nonnull
    public List<String> getEffect() {
        return effect;
    }

    public void setEffect(@Nonnull List<String> effect) {
        this.effect = effect;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public boolean isMultiAttack() {
        return isMultiAttack;
    }

    public void setIsMultiAttack(boolean multiAttack) {
        this.isMultiAttack = multiAttack;
    }

    public static class Serializer implements JsonDeserializer<NonPlayerAction>, JsonSerializer<NonPlayerAction> {

        @Override
        public NonPlayerAction deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerAction nonPlayerAction = new NonPlayerAction();
            Keys.IS_MULTI_ATTACK.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerAction::setIsMultiAttack);
            Keys.DESCRIPTION.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerAction::setDescription);
            Keys.EFFECT.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerAction::setEffect);
            JsonKeyProcessor.<JsonObject, NonPlayerActionType>getJsonKey(Keys.NON_PLAYER_ACTION_TYPE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerAction::setActionType));
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerAction::setName);
            return nonPlayerAction;
        }

        @Override
        public JsonElement serialize(NonPlayerAction src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.IS_MULTI_ATTACK.serialize(src.isMultiAttack(), jsonObject, context);
            Keys.DESCRIPTION.serialize(src.getDescription(), jsonObject, context);
            Keys.EFFECT.serialize(src.getEffect(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, NonPlayerActionType>getJsonKey(Keys.NON_PLAYER_ACTION_TYPE).ifPresent(jsonKey -> jsonKey.serialize(src.getActionType(), jsonObject, context));
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            return jsonObject;
        }
    }
}
