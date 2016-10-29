package io.musician101.mcdndsimple.sponge.character.spell;

import io.musician101.mcdndsimple.sponge.character.AbilityScore;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.function.BiFunction;

public class SaveDCType implements DataSerializable
{
    private BiFunction<AbilityScore, Integer, Integer> biFunction;
    private int customDC = 0;
    private final String name;

    private SaveDCType(String name)
    {
        this.name = name;
    }

    public SaveDCType(String name, int customDC)
    {
        this(name);
        this.customDC = customDC;
    }

    public SaveDCType(String name, BiFunction<AbilityScore, Integer, Integer> biFunction)
    {
        this(name);
        this.biFunction = biFunction;
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
                .set(MCDNDSimpleKeys.CUSTOM_DC, customDC)
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public Integer getSaveDC(AbilityScore abilityScore, int proficiencyBonus)
    {
        if (biFunction != null)
            return biFunction.apply(abilityScore, proficiencyBonus);
        else
            return customDC;
    }

    public String getName()
    {
        return name;
    }
}
