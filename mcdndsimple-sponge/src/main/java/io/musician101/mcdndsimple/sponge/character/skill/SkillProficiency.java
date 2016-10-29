package io.musician101.mcdndsimple.sponge.character.skill;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public enum SkillProficiency implements DataSerializable
{
    EXPERTISE("Expertise"),
    JACK_OF_ALL_TRADES("Jack of all Trades"),
    NONE("None"),
    YES("Yes");

    private final String name;

    SkillProficiency(String name)
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
