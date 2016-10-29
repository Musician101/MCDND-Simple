package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public enum Recharge implements DataSerializable
{
    LONG_REST("Long Rest"),
    NONE("None"),
    OTHER("Other"),
    SHORT_REST("Short Rest");

    private final String name;

    Recharge(String name)
    {
        this.name = name;
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
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public String getName()
    {
        return name;
    }
}
