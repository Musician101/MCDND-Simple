package io.musician101.mcdndsimple.common.character.player.equipment.currency;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class Wealth {

    @Nonnull
    private final Coin copper = new Coin("Copper", "CP");
    @Nonnull
    private final Coin electrum = new Coin("Electrum", "EP");
    @Nonnull
    private final Coin gold = new Coin("Gold", "GP");
    @Nonnull
    private final Coin platinum = new Coin("Platinum", "PP");
    @Nonnull
    private final Coin silver = new Coin("Silver", "SP");

    @Nonnull
    public Coin getCopper() {
        return copper;
    }

    @Nonnull
    public Coin getElectrum() {
        return electrum;
    }

    @Nonnull
    public Coin getGold() {
        return gold;
    }

    @Nonnull
    public Coin getPlatinum() {
        return platinum;
    }

    @Nonnull
    public Coin getSilver() {
        return silver;
    }

    public static class Serializer extends BaseSerializer<Wealth> {

        @Override
        public Wealth deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Wealth wealth = new Wealth();
            wealth.getCopper().setAmount(deserialize(jsonObject, context, Keys.COPPER));
            wealth.getElectrum().setAmount(deserialize(jsonObject, context, Keys.ELECTRUM));
            wealth.getGold().setAmount(deserialize(jsonObject, context, Keys.GOLD));
            wealth.getPlatinum().setAmount(deserialize(jsonObject, context, Keys.PLATINUM));
            wealth.getSilver().setAmount(deserialize(jsonObject, context, Keys.SILVER));
            return wealth;
        }

        @Override
        public JsonElement serialize(Wealth src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.COPPER, src.getCopper().getAmount());
            serialize(jsonObject, context, Keys.ELECTRUM, src.getElectrum().getAmount());
            serialize(jsonObject, context, Keys.GOLD, src.getGold().getAmount());
            serialize(jsonObject, context, Keys.PLATINUM, src.getPlatinum().getAmount());
            serialize(jsonObject, context, Keys.SILVER, src.getSilver().getAmount());
            return jsonObject;
        }
    }
}
