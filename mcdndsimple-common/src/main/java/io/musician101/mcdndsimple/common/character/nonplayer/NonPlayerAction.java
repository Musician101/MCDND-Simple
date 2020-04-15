package io.musician101.mcdndsimple.common.character.nonplayer;

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

    public static class Serializer extends BaseSerializer<NonPlayerAction> {

        @Override
        public NonPlayerAction deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerAction nonPlayerAction = new NonPlayerAction();
            nonPlayerAction.setIsMultiAttack(deserialize(jsonObject, context, Keys.IS_MULTI_ATTACK));
            nonPlayerAction.setDescription(deserialize(jsonObject, context, Keys.DESCRIPTION));
            nonPlayerAction.setEffect(deserialize(jsonObject, context, Keys.EFFECT));
            nonPlayerAction.setActionType(deserialize(jsonObject, context, Keys.NON_PLAYER_ACTION_TYPE));;
            nonPlayerAction.setName(deserialize(jsonObject, context, Keys.NAME));
            return nonPlayerAction;
        }

        @Override
        public JsonElement serialize(NonPlayerAction src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.IS_MULTI_ATTACK, src.isMultiAttack());
            serialize(jsonObject, context, Keys.DESCRIPTION, src.getDescription());
            serialize(jsonObject, context, Keys.EFFECT, src.getEffect());
            serialize(jsonObject, context, Keys.NON_PLAYER_ACTION_TYPE, src.getActionType());
            serialize(jsonObject, context, Keys.NAME, src.getName());
            return jsonObject;
        }
    }
}
