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
import java.util.HashMap;
import java.util.Map;

@JsonKey(key = Keys.HIT_DICE, typeAdapter = HitDice.Serializer.class)
public class HitDice {

    private Map<Integer, Integer> hitDiceMap = new HashMap<>();

    public HitDice() {
        hitDiceMap.put(6, 0);
        hitDiceMap.put(8, 0);
        hitDiceMap.put(10, 0);
        hitDiceMap.put(12, 0);
    }

    public Dice getHitDice(int sides) {
        if (hitDiceMap.containsKey(sides)) {
            return new Dice(sides, hitDiceMap.get(sides));
        }

        return null;
    }

    public Map<Integer, Integer> getHitDice() {
        return hitDiceMap;
    }

    public void setHitDice(Dice dice) {
        hitDiceMap.put(dice.getSides(), dice.getAmount());
    }

    public void updateHitDie(int sides, int amount) {
        hitDiceMap.put(sides, amount);
    }

    public void useHitDice(int sides) {
        hitDiceMap.compute(sides, (s, amount) -> --amount);
    }

    public static class Serializer implements JsonDeserializer<HitDice>, JsonSerializer<HitDice> {

        @Override
        public HitDice deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            HitDice hitDice = new HitDice();
            jsonObject.entrySet().forEach(entry -> {
                try {
                    hitDice.updateHitDie(Integer.valueOf(entry.getKey()), entry.getValue().getAsInt());
                }
                catch (Exception e) {
                    //NOOP
                }
            });
            return hitDice;
        }

        @Override
        public JsonElement serialize(HitDice src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            src.getHitDice().forEach((sides, amount) -> jsonObject.addProperty(Integer.toString(sides), amount));
            return jsonObject;
        }
    }
}
