package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.Optional;

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

    public static Optional<AbilityScore> fromDataContainer(DataContainer dataContainer)
    {
        Optional<String> name = dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery());
        Optional<String> shortName = dataContainer.getString(MCDNDSimpleKeys.SHORT_NAME.getQuery());
        if (!name.isPresent() || !shortName.isPresent())
            return Optional.empty();

        // Intellij shows a warning on shortName.get(). Warning is invalid in this case as it's not called if
        // name.isPresent() returns false
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        AbilityScore abilityScore = new AbilityScore(name.get(), shortName.get());
        dataContainer.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(abilityScore::setProficient);
        dataContainer.getInt(MCDNDSimpleKeys.SCORE.getQuery()).ifPresent(abilityScore::setScore);
        return Optional.of(abilityScore);
    }
}
