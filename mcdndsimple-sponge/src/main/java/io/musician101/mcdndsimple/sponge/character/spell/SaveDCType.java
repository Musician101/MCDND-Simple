package io.musician101.mcdndsimple.sponge.character.spell;

import io.musician101.mcdndsimple.sponge.character.AbilityScore;

import java.util.function.BiFunction;

public class SaveDCType
{
    private final BiFunction<AbilityScore, Integer, Integer> biFunction;
    private final String name;

    public SaveDCType(String name, BiFunction<AbilityScore, Integer, Integer> biFunction)
    {
        this.name = name;
        this.biFunction = biFunction;
    }

    public Integer getSaveDC(AbilityScore abilityScore, int proficiencyBonus)
    {
        return biFunction.apply(abilityScore, proficiencyBonus);
    }

    public String getName()
    {
        return name;
    }
}
