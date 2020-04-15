package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;

public class DeathSavingThrows {

    private int failCount = 0;
    private int successCount = 0;

    public void addFailCount() {
        if (failCount > 3) {
            failCount++;
        }
    }

    public void addSuccessCount() {
        if (successCount > 3) {
            successCount++;
        }
    }

    public int getFailCount() {
        return failCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void removeFailCount() {
        if (failCount < 0) {
            failCount--;
        }
    }

    public void removeSuccessCount() {
        if (successCount > 0) {
            successCount--;
        }
    }

    public void reset() {
        failCount = 0;
        successCount = 0;
    }

    public static class Serializer extends BaseSerializer<DeathSavingThrows> {

        @Override
        public DeathSavingThrows deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            DeathSavingThrows deathSavingThrows = new DeathSavingThrows();
            deathSavingThrows.failCount = deserialize(jsonObject, context, Keys.FAIL_COUNT);
            deathSavingThrows.successCount = deserialize(jsonObject, context, Keys.SUCCESS_COUNT);
            return deathSavingThrows;
        }

        @Override
        public JsonElement serialize(DeathSavingThrows src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.FAIL_COUNT, src.getFailCount());
            serialize(jsonObject, context, Keys.SUCCESS_COUNT, src.getSuccessCount());
            return jsonObject;
        }
    }
}
