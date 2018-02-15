package io.musician101.mcdndsimple.common.character;

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

@JsonKey(key = Keys.HIT_POINTS, typeAdapter = HitPoints.Serializer.class)
public class HitPoints {

    private int current = 0;
    private int max = 0;
    private int temp = 0;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public static class Serializer implements JsonDeserializer<HitPoints>, JsonSerializer<HitPoints> {

        @Override
        public HitPoints deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            HitPoints hitPoints = new HitPoints();
            Keys.CURRENT.deserializeFromParent(jsonObject, context).ifPresent(hitPoints::setCurrent);
            Keys.MAXIMUM.deserializeFromParent(jsonObject, context).ifPresent(hitPoints::setMax);
            Keys.TEMPORARY.deserializeFromParent(jsonObject, context).ifPresent(hitPoints::setTemp);
            return hitPoints;
        }

        @Override
        public JsonElement serialize(HitPoints src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.CURRENT.serialize(src.getCurrent(), jsonObject, context);
            Keys.MAXIMUM.serialize(src.getMax(), jsonObject, context);
            Keys.TEMPORARY.serialize(src.getTemp(), jsonObject, context);
            return jsonObject;
        }
    }
}
