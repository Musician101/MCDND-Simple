package io.musician101.mcdndsimple.common.character.spell;

import io.musician101.mcdndsimple.common.character.AbilityScore;

import java.util.function.BiFunction;

public class SaveDCType
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
