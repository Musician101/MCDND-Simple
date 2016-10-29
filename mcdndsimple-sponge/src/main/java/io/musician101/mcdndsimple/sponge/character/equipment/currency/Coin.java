package io.musician101.mcdndsimple.sponge.character.equipment.currency;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class Coin implements DataSerializable
{
    private final String name;
    private int amount = 0;
    private final String shortName;

    public Coin(String name, String shortName)
    {
        this.name = name;
        this.shortName = shortName;
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
                .set(MCDNDSimpleKeys.NAME, name)
                .set(MCDNDSimpleKeys.AMOUNT, amount)
                .set(MCDNDSimpleKeys.SHORT_NAME, shortName);
    }

    public String getName()
    {
        return name;
    }

    public String getShortName()
    {
        return shortName;
    }

    public double getWeight()
    {
        return 0.02 * getAmount();
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
