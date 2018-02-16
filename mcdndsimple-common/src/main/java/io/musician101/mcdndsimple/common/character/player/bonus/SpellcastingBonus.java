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
import javax.annotation.Nonnull;

@JsonKey(key = Keys.SPELLCASTING_BONUS, typeAdapter = SpellcastingBonus.Serializer.class)
public class SpellcastingBonus {

    @Nonnull
    private Dice attack = new Dice(0);
    @Nonnull
    private Dice damage = new Dice(0);
    @Nonnull
    private Dice saveDC = new Dice(0);

    @Nonnull
    public Dice getAttack() {
        return attack;
    }

    public void setAttack(@Nonnull Dice attack) {
        this.attack = attack;
    }

    @Nonnull
    public Dice getDamage() {
        return damage;
    }

    public void setDamage(@Nonnull Dice damage) {
        this.damage = damage;
    }

    @Nonnull
    public Dice getSaveDC() {
        return saveDC;
    }

    public void setSaveDC(@Nonnull Dice saveDC) {
        this.saveDC = saveDC;
    }

    public static class Serializer implements JsonDeserializer<SpellcastingBonus>, JsonSerializer<SpellcastingBonus> {

        @Override
        public SpellcastingBonus deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellcastingBonus rangedBonus = new SpellcastingBonus();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.ATTACK).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(rangedBonus::setAttack));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(rangedBonus::setDamage));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.SAVE_DC).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(rangedBonus::setSaveDC));
            return rangedBonus;
        }

        @Override
        public JsonElement serialize(SpellcastingBonus src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.ATTACK).ifPresent(jsonKey -> jsonKey.serialize(src.getAttack(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.DAMAGE).ifPresent(jsonKey -> jsonKey.serialize(src.getDamage(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.SAVE_DC).ifPresent(jsonKey -> jsonKey.serialize(src.getSaveDC(), jsonObject, context));
            return jsonObject;
        }
    }
}
