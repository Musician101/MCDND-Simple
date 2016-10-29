package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class AbilityScore implements DataSerializable
{
    private boolean proficient = false;
    private int score = 0;
    private final String shortName;
    private final String name;

    public AbilityScore(String name, String shortName)
    {
        this.name = name;
        this.shortName = shortName;
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
                .set(MCDNDSimpleKeys.PROFICIENT, proficient)
                .set(MCDNDSimpleKeys.SCORE, score)
                .set(MCDNDSimpleKeys.SHORT_NAME, shortName)
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }

    public int getMod()
    {
        return (score - 10) / 2;
    }

    public String getShortName()
    {
        return shortName;
    }

    public boolean isProficient()
    {
        return proficient;
    }

    public void setProficient(boolean proficient)
    {
        this.proficient = proficient;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
