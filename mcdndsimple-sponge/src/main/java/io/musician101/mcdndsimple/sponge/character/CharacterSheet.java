package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

//TODO needs builder for all
public class CharacterSheet implements DataSerializable
{
    private final BioAndInfo bioAndInfo;
    private final PlayerSheet playerSheet;
    private String clazz = null;
    private String name = "";
    private String race = null;

    public CharacterSheet(String name)
    {
        this.name = name;
        this.bioAndInfo = new BioAndInfo(name);
        this.playerSheet = new PlayerSheet();
    }

    public BioAndInfo getBioAndInfo()
    {
        return bioAndInfo;
    }

    public String getClazz()
    {
        return clazz;
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
                .set(MCDNDSimpleKeys.BIO_AND_INFO, bioAndInfo.toContainer())
                .set(MCDNDSimpleKeys.PLAYER_SHEET, playerSheet.toContainer())
                .set(MCDNDSimpleKeys.CLASS, clazz)
                .set(MCDNDSimpleKeys.NAME, name)
                .set(MCDNDSimpleKeys.RACE, race);
    }

    public String getName()
    {
        return name;
    }

    public PlayerSheet getPlayerSheet()
    {
        return playerSheet;
    }

    public String getRace()
    {
        return race;
    }

    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRace(String race)
    {
        this.race = race;
    }
}
