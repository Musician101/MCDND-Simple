package io.musician101.mcdndsimple.sponge.character.spell;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class SpellSave implements DataSerializable
{
    private SaveDCType saveDCType = null;
    private String onSuccessfulSave = "";
    private String savingStat = "";

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
                .set(MCDNDSimpleKeys.SAVE_DC_TYPE, saveDCType.toContainer())
                .set(MCDNDSimpleKeys.ON_SUCCESSFUL_SAVE, onSuccessfulSave)
                .set(MCDNDSimpleKeys.SAVING_STAT, savingStat);
    }

    public String getOnSuccessfulSave()
    {
        return onSuccessfulSave;
    }

    public SaveDCType getSaveDCType()
    {
        return saveDCType;
    }

    public String getSavingStat()
    {
        return savingStat;
    }

    public void setOnSuccessfulSave(String onSuccessfulSave)
    {
        this.onSuccessfulSave = onSuccessfulSave;
    }

    public void setSaveDCType(SaveDCType saveDCType)
    {
        this.saveDCType = saveDCType;
    }

    public void setSavingStat(String savingStat)
    {
        this.savingStat = savingStat;
    }
}
