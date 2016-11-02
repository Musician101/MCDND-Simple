package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class CoreStats implements DataSerializable
{
    private AbilityScore charisma = new AbilityScore("Charisma", "cha");
    private AbilityScore constitution = new AbilityScore("Constitution", "con");
    private AbilityScore dexterity = new AbilityScore("Dexterity", "dex");
    private AbilityScore intelligence = new AbilityScore("Intelligence", "int");
    private AbilityScore strength = new AbilityScore("Strength", "str");
    private AbilityScore wisdom = new AbilityScore("Wisdom", "wis");

    public AbilityScore getCharisma()
    {
        return charisma;
    }

    public AbilityScore getConstitution()
    {
        return constitution;
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
                .set(MCDNDSimpleKeys.CHARISMA_SCORE, charisma.toContainer())
                .set(MCDNDSimpleKeys.CONSTITUTION_SCORE, constitution.toContainer())
                .set(MCDNDSimpleKeys.DEXTERITY_SCORE, dexterity.toContainer())
                .set(MCDNDSimpleKeys.INTELLIGENCE_SCORE, intelligence.toContainer())
                .set(MCDNDSimpleKeys.STRENGTH_SCORE, strength.toContainer())
                .set(MCDNDSimpleKeys.WISDOM_SCORE, wisdom.toContainer());
    }

    public AbilityScore getDexterity()
    {
        return dexterity;
    }

    public AbilityScore getIntelligence()
    {
        return intelligence;
    }

    public AbilityScore getStrength()
    {
        return strength;
    }

    public AbilityScore getWisdom()
    {
        return wisdom;
    }

    public void setCharisma(AbilityScore charisma)
    {
        this.charisma = charisma;
    }

    public void setConstitution(AbilityScore constitution)
    {
        this.constitution = constitution;
    }

    public void setDexterity(AbilityScore dexterity)
    {
        this.dexterity = dexterity;
    }

    public void setIntelligence(AbilityScore intelligence)
    {
        this.intelligence = intelligence;
    }

    public void setStrength(AbilityScore strength)
    {
        this.strength = strength;
    }

    public void setWisdom(AbilityScore wisdom)
    {
        this.wisdom = wisdom;
    }

    public static CoreStats fromDataContainer(DataContainer dataContainer)
    {
        CoreStats coreStats = new CoreStats();
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.CHARISMA_SCORE).ifPresent(data -> AbilityScore.fromDataContainer(data).ifPresent(coreStats::setCharisma));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.CONSTITUTION_SCORE).ifPresent(data -> AbilityScore.fromDataContainer(data).ifPresent(coreStats::setConstitution));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.DEXTERITY_SCORE).ifPresent(data -> AbilityScore.fromDataContainer(data).ifPresent(coreStats::setDexterity));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.INTELLIGENCE_SCORE).ifPresent(data -> AbilityScore.fromDataContainer(data).ifPresent(coreStats::setIntelligence));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.STRENGTH_SCORE).ifPresent(data -> AbilityScore.fromDataContainer(data).ifPresent(coreStats::setStrength));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.WISDOM_SCORE).ifPresent(data -> AbilityScore.fromDataContainer(data).ifPresent(coreStats::setWisdom));
        return coreStats;
    }
}
