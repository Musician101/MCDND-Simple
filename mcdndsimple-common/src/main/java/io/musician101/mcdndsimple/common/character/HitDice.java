package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.Dice;

import java.util.HashMap;
import java.util.Map;

public class HitDice
{
    private Map<Integer, Integer> hitDiceMap = new HashMap<>();

    public HitDice()
    {
        hitDiceMap.put(6, 0);
        hitDiceMap.put(8, 0);
        hitDiceMap.put(10, 0);
        hitDiceMap.put(12, 0);
    }

    public void addHitDie(int sides, int amount)
    {
        hitDiceMap.put(sides, amount);
    }

    public void removeHitDice(int sides)
    {
        hitDiceMap.remove(sides);
    }

    public Dice getHitDice(int sides)
    {
        if (hitDiceMap.containsKey(sides))
            return new Dice(sides, hitDiceMap.get(sides));

        return null;
    }

    public Map<Integer, Integer> getHitDice()
    {
        return hitDiceMap;
    }

    public void setHitDice(Dice dice)
    {
        hitDiceMap.put(dice.getSides(), dice.getAmount());
    }
}
