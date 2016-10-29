package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class MCDNDItem implements DataSerializable
{
    private boolean carried = true;
    private double weight = 0.0;
    private String description = "";
    private String name = "";

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
                .set(MCDNDSimpleKeys.CARRIED, carried)
                .set(MCDNDSimpleKeys.WEIGHT_DOUBLE, weight)
                .set(MCDNDSimpleKeys.DESCRIPTION, description)
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public String getDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    public double getWeight()
    {
        return weight;
    }

    public boolean isCarried()
    {
        return carried;
    }

    public void setCarried(boolean carried)
    {
        this.carried = carried;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}
