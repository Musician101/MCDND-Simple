package io.musician101.mcdndsimple.common.character.player.bonus;

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
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;

@JsonKey(key = Keys.RANGED_BONUS, typeAdapter = RangedBonus.Serializer.class)
public class RangedBonus {

    private Dice attack = new Dice(0);
    private Dice damage = new Dice(0);

    public Dice getAttack() {
        return attack;
    }

    public void setAttack(Dice attack) {
        this.attack = attack;
    }

    public Dice getDamage() {
        return damage;
    }

    public void setDamage(Dice damage) {
        this.damage = damage;
    }

    public static class Serializer implements JsonDeserializer<RangedBonus>, JsonSerializer<RangedBonus> {

        @Override
        public RangedBonus deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            RangedBonus rangedBonus = new RangedBonus();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.ATTACK).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(rangedBonus::setAttack));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(rangedBonus::setDamage));
            return rangedBonus;
        }

        @Override
        public JsonElement serialize(RangedBonus src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.ATTACK).ifPresent(jsonKey -> jsonKey.serialize(src.getAttack(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.serialize(src.getDamage(), jsonObject, context));
            return jsonObject;
        }
    }
}
