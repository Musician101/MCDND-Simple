package io.musician101.mcdndsimple.common.character.player.bonus;

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

public class MeleeBonus {

    @Nonnull
    private Dice attack = new Dice(0);
    @Nonnull
    private Dice damage = new Dice(0);

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

    public static class Serializer extends BaseSerializer<MeleeBonus> {

        @Override
        public MeleeBonus deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MeleeBonus meleeBonus = new MeleeBonus();
            meleeBonus.setAttack(deserialize(jsonObject, context, Keys.ATTACK));;
            meleeBonus.setDamage(deserialize(jsonObject, context, Keys.DAMAGE));;
            return meleeBonus;
        }

        @Override
        public JsonElement serialize(MeleeBonus src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.ATTACK, src.getAttack());
            serialize(jsonObject, context, Keys.DAMAGE, src.getDamage());
            return jsonObject;
        }
    }
}
