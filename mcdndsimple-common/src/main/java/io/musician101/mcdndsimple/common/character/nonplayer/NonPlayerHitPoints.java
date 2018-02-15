package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;

@JsonKey(key = Keys.HIT_POINTS, typeAdapter = NonPlayerHitPoints.Serializer.class)
public class NonPlayerHitPoints extends HitPoints {

    private Dice hitDice = new Dice(0);

    public Dice getHitDice() {
        return hitDice;
    }

    public void setHitDice(Dice hitDice) {
        this.hitDice = hitDice;
    }

    public static class Serializer implements JsonDeserializer<NonPlayerHitPoints>, JsonSerializer<NonPlayerHitPoints> {

        @Override
        public NonPlayerHitPoints deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            NonPlayerHitPoints hitPoints = new NonPlayerHitPoints();
            Keys.CURRENT.deserializeFromParent(jsonObject, context).ifPresent(hitPoints::setCurrent);
            Keys.MAXIMUM.deserializeFromParent(jsonObject, context).ifPresent(hitPoints::setMax);
            Keys.TEMPORARY.deserializeFromParent(jsonObject, context).ifPresent(hitPoints::setTemp);
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.HIT_DICE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(hitPoints::setHitDice));
            return hitPoints;
        }

        @Override
        public JsonElement serialize(NonPlayerHitPoints src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.CURRENT.serialize(src.getCurrent(), jsonObject, context);
            Keys.MAXIMUM.serialize(src.getMax(), jsonObject, context);
            Keys.TEMPORARY.serialize(src.getTemp(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.HIT_DICE).ifPresent(jsonKey -> jsonKey.serialize(src.getHitDice(), jsonObject, context));
            return jsonObject;
        }
    }
}
