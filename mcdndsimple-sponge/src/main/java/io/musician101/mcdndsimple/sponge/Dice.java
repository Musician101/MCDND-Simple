package io.musician101.mcdndsimple.sponge;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class Dice implements DataSerializable
{
    private final int amount;
    private final int sides;

    public Dice(int sides)
    {
        this(1, sides);
    }

    public Dice(int amount, int sides)
    {
        this.amount = amount;
        this.sides = sides;
    }

    public int getAverageRoll()
    {
        return (amount * (sides / 2)) + 1;
    }

    public int getAmount()
    {
        return amount;
    }

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.AMOUNT, amount)
                .set(MCDNDSimpleKeys.SIDES, sides);
    }

    public int getSides()
    {
        return sides;
    }

    public List<Entry<Dice, Integer>> roll()
    {
        List<Entry<Dice, Integer>> list = new ArrayList<>();
        for (int x = 0; x < amount; x++)
            list.add(new SimpleEntry<>(new Dice(sides), new Random().nextInt(sides - 1) + 1));

        return list;
    }

    public static int total(List<Entry<Dice, Integer>> rolls)
    {
        return total(rolls, 0);
    }

    public static int total(List<Entry<Dice, Integer>> rolls, int bonus)
    {
        int total = 0;
        for (Entry<Dice, Integer> roll : rolls)
            total =+ roll.getValue();

        return total + bonus;
    }

    public static Dice fromDataContainer(DataContainer dataContainer)
    {
        return dataContainer.getInt(MCDNDSimpleKeys.SIDES.getQuery()).map(sides -> dataContainer.getInt(MCDNDSimpleKeys.AMOUNT.getQuery()).map(amount -> new Dice(amount, sides)).orElse(new Dice(sides))).orElse(new Dice(6));
    }
}
