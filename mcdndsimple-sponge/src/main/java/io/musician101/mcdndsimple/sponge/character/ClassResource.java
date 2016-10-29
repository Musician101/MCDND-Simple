package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class ClassResource implements DataSerializable
{
    private int currentCharges = 0;
    private int maxCharges = 0;
    private Recharge recharge = Recharge.NONE;
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
                .set(MCDNDSimpleKeys.USES_LEFT, 0)
                .set(MCDNDSimpleKeys.MAX_USES, 0)
                .set(MCDNDSimpleKeys.RECHARGE, recharge.toContainer())
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public int getCurrentCharges()
    {
        return currentCharges;
    }

    public int getMaxCharges()
    {
        return maxCharges;
    }

    public String getName()
    {
        return name;
    }

    public Recharge getRecharge()
    {
        return recharge;
    }

    public void setCurrentCharges(int currentCharges)
    {
        this.currentCharges = currentCharges;
    }

    public void setMaxCharges(int maxCharges)
    {
        this.maxCharges = maxCharges;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRecharge(Recharge recharge)
    {
        this.recharge = recharge;
    }
}
