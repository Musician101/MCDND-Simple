package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;

@JsonKey(key = Keys.DEATH_SAVING_THROWS, typeAdapter = DeathSavingThrows.Serializer.class)
public class DeathSavingThrows {

    private final Dice d20 = new Dice(20);
    private int failCount = 0;
    private int successCount = 0;

    public int getFailCount() {
        return failCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void reset() {
        failCount = 0;
        successCount = 0;
    }

    public int roll() {
        int roll = Dice.total(d20.roll());
        if (roll == 1) {
            failCount = +2;
        }
        else if (roll >= 10 && roll < 20) {
            successCount++;
        }
        else if (roll > 1 && roll < 10) {
            failCount++;
        }

        return roll;
    }

    public static class Serializer implements JsonDeserializer<DeathSavingThrows>, JsonSerializer<DeathSavingThrows> {

        @Override
        public DeathSavingThrows deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            DeathSavingThrows deathSavingThrows = new DeathSavingThrows();
            Keys.FAIL_COUNT.deserializeFromParent(jsonObject, context).ifPresent(i -> deathSavingThrows.failCount = i);
            Keys.SUCCESS_COUNT.deserializeFromParent(jsonObject, context).ifPresent(i -> deathSavingThrows.successCount = i);
            return deathSavingThrows;
        }

        @Override
        public JsonElement serialize(DeathSavingThrows src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.FAIL_COUNT.serialize(src.getFailCount(), jsonObject, context);
            Keys.SUCCESS_COUNT.serialize(src.getSuccessCount(), jsonObject, context);
            return jsonObject;
        }
    }
}
