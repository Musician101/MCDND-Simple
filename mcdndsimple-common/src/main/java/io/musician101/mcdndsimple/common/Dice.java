package io.musician101.mcdndsimple.common;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Dice {

    private final int amount;
    private final int sides;

    public Dice(int sides) {
        this(1, sides);
    }

    public Dice(int amount, int sides) {
        this.amount = amount;
        this.sides = sides;
    }

    @Nullable
    public static Dice parse(String s) {
        int amount;
        int sides = 1;
        try {
            if (s.contains("d")) {
                String[] split = s.split("d");
                amount = Integer.parseInt(split[0]);
                sides = Integer.parseInt(split[1]);
            }
            else {
                amount = Integer.parseInt(s);
            }

            return new Dice(amount, sides);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public static int total(@Nonnull List<Entry<Dice, Integer>> rolls) {
        return total(rolls, 0);
    }

    public static int total(@Nonnull List<Entry<Dice, Integer>> rolls, int bonus) {
        int total = 0;
        for (Entry<Dice, Integer> roll : rolls) {
            total = +roll.getValue();
        }

        return total + bonus;
    }

    public int getAmount() {
        return amount;
    }

    public int getAverageRoll() {
        return (amount * (sides / 2)) + 1;
    }

    public int getSides() {
        return sides;
    }

    @Nonnull
    public List<Entry<Dice, Integer>> roll() {
        List<Entry<Dice, Integer>> list = new ArrayList<>();
        for (int x = 0; x < amount; x++) {
            list.add(new SimpleEntry<>(new Dice(sides), new Random().nextInt(sides - 1) + 1));
        }

        return list;
    }

    @Override
    public String toString() {
        return amount + "d" + sides;
    }

    public static class Serializer extends BaseSerializer<Dice> {

        @Override
        public Dice deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            return new Dice(deserialize(jsonObject, context, Keys.AMOUNT), deserialize(jsonObject, context, Keys.SIDES));
        }

        @Override
        public JsonElement serialize(Dice src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.AMOUNT, src.getAmount());
            serialize(jsonObject, context, Keys.SIDES, src.getSides());
            return jsonObject;
        }
    }
}
