package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class CharacterSheet implements DataSerializable
{
    private BioAndInfo bioAndInfo = new BioAndInfo();
    private PlayerSheet playerSheet = new PlayerSheet();
    private String clazz = "";
    private String name = "Your Name Here";
    private String race = "";

    public String getClazz()
    {
        return clazz;
    }

    public BioAndInfo getBioAndInfo()
    {
        return bioAndInfo;
    }

    public PlayerSheet getPlayerSheet()
    {
        return playerSheet;
    }

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    public void setBioAndInfo(BioAndInfo bioAndInfo)
    {
        this.bioAndInfo = bioAndInfo;
    }

    public void setPlayerSheet(PlayerSheet playerSheet)
    {
        this.playerSheet = playerSheet;
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

    public static CharacterSheet fromDataContainer(DataContainer dataContainer)
    {
        CharacterSheet characterSheet = new CharacterSheet();
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.BIO_AND_INFO).ifPresent(bioData -> characterSheet.setBioAndInfo(BioAndInfo.fromDataContainer(bioData)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.PLAYER_SHEET).ifPresent(playerData -> characterSheet.setPlayerSheet(PlayerSheet.fromDataContainer(playerData)));
        dataContainer.getString(MCDNDSimpleKeys.CLASS.getQuery()).ifPresent(characterSheet::setClazz);
        dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(characterSheet::setName);
        dataContainer.getString(MCDNDSimpleKeys.RACE.getQuery()).ifPresent(characterSheet::setRace);
        return characterSheet;
    }
}
