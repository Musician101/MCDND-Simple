package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;


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

    public static class Serializer extends BaseSerializer<SpellHealing> {

        @Override
        public SpellHealing deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellHealing spellHealing = new SpellHealing();
            spellHealing.setHealAmount(deserialize(jsonObject, context, Keys.HEAL_AMOUNT));;
            spellHealing.setStatBonus(deserialize(jsonObject, context, Keys.STAT_BONUS));;
            return spellHealing;
        }

        @Override
        public JsonElement serialize(SpellHealing src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.HEAL_AMOUNT, src.getHealAmount());
            serialize(jsonObject, context, Keys.STAT_BONUS, src.getStatBonus());
            return jsonObject;
        }
    }
}
