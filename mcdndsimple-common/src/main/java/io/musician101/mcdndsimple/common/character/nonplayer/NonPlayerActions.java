package io.musician101.mcdndsimple.common.character.nonplayer;

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

@JsonKey(key = Keys.NON_PLAYER_ACTIONS, typeAdapter = NonPlayerActions.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<NonPlayerActions>, JsonSerializer<NonPlayerActions> {

        @Override
        public NonPlayerActions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerActions nonPlayerActions = new NonPlayerActions();
            Keys.ACTIONS.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerActions::setActions);
            Keys.MULTI_ATTACK.deserializeFromParent(jsonObject, context).ifPresent(nonPlayerActions::setMultiAttack);
            return nonPlayerActions;
        }

        @Override
        public JsonElement serialize(NonPlayerActions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.ACTIONS.serialize(src.getActions(), jsonObject, context);
            Keys.MULTI_ATTACK.serialize(src.getMultiAttack(), jsonObject, context);
            return jsonObject;
        }
    }
}
