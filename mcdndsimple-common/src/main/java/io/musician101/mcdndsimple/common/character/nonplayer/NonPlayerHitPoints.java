package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;


public class NonPlayerHitPoints extends HitPoints {

    @Nonnull
    private Dice hitDice = new Dice(0);

    @Nonnull
    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(@Nonnull Dice hitDice) {
        this.hitDice = hitDice;
    }

    public static class Serializer extends BaseSerializer<NonPlayerHitPoints> {

        @Override
        public NonPlayerHitPoints deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerHitPoints hitPoints = new NonPlayerHitPoints();
            hitPoints.setCurrent(deserialize(jsonObject, context, Keys.CURRENT));
            hitPoints.setMax(deserialize(jsonObject, context, Keys.MAXIMUM));
            hitPoints.setTemp(deserialize(jsonObject, context, Keys.TEMPORARY));
            hitPoints.setHitDice(deserialize(jsonObject, context, Keys.HIT_DICE_NPC));;
            return hitPoints;
        }

        @Override
        public JsonElement serialize(NonPlayerHitPoints src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.CURRENT, src.getCurrent());
            serialize(jsonObject, context, Keys.MAXIMUM, src.getMax());
            serialize(jsonObject, context, Keys.TEMPORARY, src.getTemp());
            serialize(jsonObject, context, Keys.HIT_DICE_NPC, src.getHitDice());
            return jsonObject;
        }
    }
}
