package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.Dice;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class HitDice implements DataSerializable
{
    private Map<Integer, Integer> hitDiceMap = new HashMap<>();

    public HitDice()
    {
        hitDiceMap.put(6, 0);
        hitDiceMap.put(8, 0);
        hitDiceMap.put(10, 0);
        hitDiceMap.put(12, 0);
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
                .set(MCDNDSimpleKeys.HIT_DICE_MAP, hitDiceMap);
    }

    public Dice getHitDice(int sides)
    {
        if (hitDiceMap.containsKey(sides))
            return new Dice(sides, hitDiceMap.get(sides));

        return null;
    }

    public void setHitDice(Dice dice)
    {
        hitDiceMap.put(dice.getSides(), dice.getAmount());
    }
}
