package io.musician101.mcdndsimple.sponge.character.spell;

import io.musician101.mcdndsimple.sponge.character.AbilityScore;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class SpellHealing implements DataSerializable
{
    private int healAmount = 0;
    private String statBonus = "None";

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
                .set(MCDNDSimpleKeys.HEAL_AMOUNT, healAmount)
                .set(MCDNDSimpleKeys.STAT_BONUS, statBonus);
    }

    public int getHealTotal(AbilityScore abilityScore)
    {
        if ("None".equals(statBonus))
            return healAmount;

        return healAmount + abilityScore.getMod();
    }

    public int getHealAmount()
    {
        return healAmount;
    }

    public String getStatBonus()
    {
        return statBonus;
    }

    public void setHealAmount(int healAmount)
    {
        this.healAmount = healAmount;
    }

    public void setStatBonus(String statBonus)
    {
        this.statBonus = statBonus;
    }
}
