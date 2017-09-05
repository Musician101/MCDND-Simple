package io.musician101.mcdndsimple.common;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class Dice
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

    public int getSides()
    {
        return sides;
    }

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
}
