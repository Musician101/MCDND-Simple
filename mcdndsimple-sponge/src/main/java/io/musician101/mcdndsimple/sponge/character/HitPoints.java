package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class HitPoints implements DataSerializable
{
    private int current = 0;
    private int max = 0;
    private int temp = 0;

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
                .set(MCDNDSimpleKeys.CURRENT_HP, current)
                .set(MCDNDSimpleKeys.MAX_HP, max)
                .set(MCDNDSimpleKeys.TEMP_HP, temp);
    }

    public int getCurrent()
    {
        return current;
    }

    public int getMax()
    {
        return max;
    }

    public int getTemp()
    {
        return temp;
    }

    public void setCurrent(int current)
    {
        this.current = current;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public void setTemp(int temp)
    {
        this.temp = temp;
    }

    public static HitPoints fromDataContainer(DataContainer dataContainer)
    {
        HitPoints hitPoints = new HitPoints();
        dataContainer.getInt(MCDNDSimpleKeys.CURRENT_HP.getQuery()).ifPresent(hitPoints::setCurrent);
        dataContainer.getInt(MCDNDSimpleKeys.MAX_HP.getQuery()).ifPresent(hitPoints::setMax);
        dataContainer.getInt(MCDNDSimpleKeys.TEMP_HP.getQuery()).ifPresent(hitPoints::setTemp);
        return hitPoints;
    }
}
