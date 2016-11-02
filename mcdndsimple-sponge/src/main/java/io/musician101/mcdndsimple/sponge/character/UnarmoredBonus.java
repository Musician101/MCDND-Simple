package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.BiFunction;

public enum UnarmoredBonus implements DataSerializable
{
    BARBARIAN("Barbarian"),
    DRACONIC_RESILIENCE("Draconic Resilience (Sorcerer Subclass)", (dexMod, secondMod) -> 13 + dexMod),
    MONK("Monk"),
    NONE("None", (dexMod, secondMod) -> 0);

    private final BiFunction<Integer, Integer, Integer> biFunction;
    private final String name;

    UnarmoredBonus(String name)
    {
        this(name, (dexMod, secondMod) -> 10 + dexMod + secondMod);
    }

    UnarmoredBonus(String name, BiFunction<Integer, Integer, Integer> biFunction)
    {
        this.name = name;
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
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public int getArmorClass(int dexMod, int secondMod)
    {
        return biFunction.apply(dexMod, secondMod);
    }

    public String getName()
    {
        return name;
    }

    public static Optional<UnarmoredBonus> fromDataContainer(DataContainer dataContainer)
    {
        Optional<String> name = dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery());
        if (name.isPresent())
            for (UnarmoredBonus unarmoredBonus : values())
                if (unarmoredBonus.getName().equals(name.get()))
                    return Optional.of(unarmoredBonus);

        return Optional.empty();
    }
}
