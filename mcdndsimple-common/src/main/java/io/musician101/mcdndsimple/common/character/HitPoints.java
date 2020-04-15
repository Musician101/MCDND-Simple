package io.musician101.mcdndsimple.common.character;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;

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

    public static class Serializer extends BaseSerializer<HitPoints> {

        @Override
        public HitPoints deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            HitPoints hitPoints = new HitPoints();
            hitPoints.setCurrent(deserialize(jsonObject, context, Keys.CURRENT));;
            hitPoints.setMax(deserialize(jsonObject, context, Keys.MAXIMUM));;
            hitPoints.setTemp(deserialize(jsonObject, context, Keys.TEMPORARY));;
            return hitPoints;
        }

        @Override
        public JsonElement serialize(HitPoints src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.CURRENT, src.getCurrent());
            serialize(jsonObject, context, Keys.MAXIMUM, src.getMax());
            serialize(jsonObject, context, Keys.TEMPORARY, src.getTemp());
            return jsonObject;
        }
    }
}
