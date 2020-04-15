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


public class NonPlayerActions {

    @Nonnull
    private List<NonPlayerAction> actions = new ArrayList<>();
    @Nonnull
    private String multiAttack = "None";

    @Nonnull
    public List<NonPlayerAction> getActions() {
        return actions;
    }

    public void setActions(@Nonnull List<NonPlayerAction> actions) {
        this.actions = actions;
    }

    @Nonnull
    public String getMultiAttack() {
        return multiAttack;
    }

    public void setMultiAttack(@Nonnull String multiAttack) {
        this.multiAttack = multiAttack;
    }

    public static class Serializer extends BaseSerializer<NonPlayerActions> {

        @Override
        public NonPlayerActions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerActions nonPlayerActions = new NonPlayerActions();
            nonPlayerActions.setActions(deserialize(jsonObject, context, Keys.ACTIONS));
            nonPlayerActions.setMultiAttack(deserialize(jsonObject, context, Keys.MULTI_ATTACK));
            return nonPlayerActions;
        }

        @Override
        public JsonElement serialize(NonPlayerActions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.ACTIONS, src.getActions());
            serialize(jsonObject, context, Keys.MULTI_ATTACK, src.getMultiAttack());
            return jsonObject;
        }
    }
}
