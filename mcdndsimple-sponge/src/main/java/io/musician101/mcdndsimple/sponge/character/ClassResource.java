package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.Optional;

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
                .set(MCDNDSimpleKeys.USES_LEFT, currentCharges)
                .set(MCDNDSimpleKeys.MAX_USES, maxCharges)
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

    public static ClassResource fromDataContainer(DataContainer dataContainer)
    {
        ClassResource classResource = new ClassResource();
        dataContainer.getInt(MCDNDSimpleKeys.USES_LEFT.getQuery()).ifPresent(classResource::setCurrentCharges);
        dataContainer.getInt(MCDNDSimpleKeys.MAX_USES.getQuery()).ifPresent(classResource::setMaxCharges);
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.RECHARGE).ifPresent(data ->
        {
            Optional<Recharge> optional = Recharge.fromDataContainer(data);
            if (optional.isPresent())
                classResource.setRecharge(optional.get());
        });
        dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(classResource::setName);
        return classResource;
    }
}
