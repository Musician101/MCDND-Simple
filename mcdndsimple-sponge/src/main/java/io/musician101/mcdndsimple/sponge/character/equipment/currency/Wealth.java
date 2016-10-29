package io.musician101.mcdndsimple.sponge.character.equipment.currency;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class Wealth implements DataSerializable
{
    private Coin copper = new Coin("Copper", "CP");
    private Coin electrum = new Coin("Electrum", "EP");
    private Coin gold = new Coin("Gold", "GP");
    private Coin platinum = new Coin("Platinum", "PP");
    private Coin silver = new Coin("Silver", "SP");

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
                .set(MCDNDSimpleKeys.COPPER, copper.toContainer())
                .set(MCDNDSimpleKeys.ELECTRUM, electrum.toContainer())
                .set(MCDNDSimpleKeys.GOLD, gold.toContainer())
                .set(MCDNDSimpleKeys.PLATINUM, platinum.toContainer())
                .set(MCDNDSimpleKeys.SILVER, silver.toContainer());
    }

    public Coin getCopper()
    {
        return copper;
    }

    public Coin getElectrum()
    {
        return electrum;
    }

    public Coin getGold()
    {
        return gold;
    }

    public Coin getPlatinum()
    {
        return platinum;
    }

    public Coin getSilver()
    {
        return silver;
    }

    public void setCopper(Coin copper)
    {
        this.copper = copper;
    }

    public void setElectrum(Coin electrum)
    {
        this.electrum = electrum;
    }

    public void setGold(Coin gold)
    {
        this.gold = gold;
    }

    public void setPlatinum(Coin platinum)
    {
        this.platinum = platinum;
    }

    public void setSilver(Coin silver)
    {
        this.silver = silver;
    }
}
