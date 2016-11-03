package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class ClassAction implements DataSerializable
{
    private int max = 0;
    private int used = 0;
    private Recharge recharge = Recharge.NONE;
    private String gainedFrom = "---";
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
                .set(MCDNDSimpleKeys.MAX_USES, max)
                .set(MCDNDSimpleKeys.USES_LEFT, used)
                .set(MCDNDSimpleKeys.RECHARGE, recharge.toContainer())
                .set(MCDNDSimpleKeys.GAINED_FROM, gainedFrom)
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public String getGainedFrom()
    {
        return gainedFrom;
    }

    public int getMax()
    {
        return max;
    }

    public String getName()
    {
        return name;
    }

    public Recharge getRecharge()
    {
        return recharge;
    }

    public int getUsesLeft()
    {
        return used;
    }

    public void setGainedFrom(String gainedFrom)
    {
        this.gainedFrom = gainedFrom;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRecharge(Recharge recharge)
    {
        this.recharge = recharge;
    }

    public void setUsesLeft(int used)
    {
        this.used = used;
    }

    public static ClassAction fromDataContainer(DataContainer dataContainer)
    {
        ClassAction classAction = new ClassAction();
        dataContainer.getInt(MCDNDSimpleKeys.MAX_USES.getQuery()).ifPresent(classAction::setMax);
        dataContainer.getInt(MCDNDSimpleKeys.USES_LEFT.getQuery()).ifPresent(classAction::setUsesLeft);
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.RECHARGE).ifPresent(data -> Recharge.fromDataContainer(data).ifPresent(classAction::setRecharge));
        dataContainer.getString(MCDNDSimpleKeys.GAINED_FROM.getQuery()).ifPresent(classAction::setGainedFrom);
        dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(classAction::setName);
        return classAction;
    }
}
