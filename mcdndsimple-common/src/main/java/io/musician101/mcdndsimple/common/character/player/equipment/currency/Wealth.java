package io.musician101.mcdndsimple.common.character.player.equipment.currency;

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
import javax.annotation.Nonnull;

@JsonKey(key = Keys.WEALTH, typeAdapter = Wealth.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<Wealth>, JsonSerializer<Wealth> {

        @Override
        public Wealth deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Wealth wealth = new Wealth();
            Keys.COPPER.deserializeFromParent(jsonObject, context).ifPresent(copper -> wealth.getCopper().setAmount(copper));
            Keys.ELECTRUM.deserializeFromParent(jsonObject, context).ifPresent(electrum -> wealth.getElectrum().setAmount(electrum));
            Keys.GOLD.deserializeFromParent(jsonObject, context).ifPresent(gold -> wealth.getGold().setAmount(gold));
            Keys.PLATINUM.deserializeFromParent(jsonObject, context).ifPresent(platinum -> wealth.getPlatinum().setAmount(platinum));
            Keys.SILVER.deserializeFromParent(jsonObject, context).ifPresent(silver -> wealth.getSilver().setAmount(silver));
            return wealth;
        }

        @Override
        public JsonElement serialize(Wealth src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.COPPER.serialize(src.getCopper().getAmount(), jsonObject, context);
            Keys.ELECTRUM.serialize(src.getElectrum().getAmount(), jsonObject, context);
            Keys.GOLD.serialize(src.getGold().getAmount(), jsonObject, context);
            Keys.PLATINUM.serialize(src.getPlatinum().getAmount(), jsonObject, context);
            Keys.SILVER.serialize(src.getSilver().getAmount(), jsonObject, context);
            return jsonObject;
        }
    }
}
