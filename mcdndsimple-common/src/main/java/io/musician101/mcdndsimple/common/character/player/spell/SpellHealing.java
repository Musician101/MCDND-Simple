package io.musician101.mcdndsimple.common.character.player.spell;

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

@JsonKey(key = Keys.SPELL_HEALING, typeAdapter = SpellDamage.Serializer.class)
public class SpellHealing {

    @Nonnull
    private Dice healAmount = new Dice(0);
    @Nonnull
    private StatBonus statBonus = StatBonus.NONE;

    @Nonnull
    public Dice getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(@Nonnull Dice healAmount) {
        this.healAmount = healAmount;
    }

    @Nonnull
    public StatBonus getStatBonus() {
        return statBonus;
    }

    public void setStatBonus(@Nonnull StatBonus statBonus) {
        this.statBonus = statBonus;
    }

    public static class Serializer implements JsonDeserializer<SpellHealing>, JsonSerializer<SpellHealing> {

        @Override
        public SpellHealing deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellHealing spellHealing = new SpellHealing();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.HEAL_AMOUNT).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spellHealing::setHealAmount));
            JsonKeyProcessor.<JsonObject, StatBonus>getJsonKey(Keys.STAT_BONUS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spellHealing::setStatBonus));
            return spellHealing;
        }

        @Override
        public JsonElement serialize(SpellHealing src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.HEAL_AMOUNT).ifPresent(jsonKey -> jsonKey.serialize(src.getHealAmount(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, StatBonus>getJsonKey(Keys.STAT_BONUS).ifPresent(jsonKey -> jsonKey.serialize(src.getStatBonus(), jsonObject, context));
            return jsonObject;
        }
    }
}
