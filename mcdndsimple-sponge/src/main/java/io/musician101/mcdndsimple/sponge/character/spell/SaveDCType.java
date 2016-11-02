package io.musician101.mcdndsimple.sponge.character.spell;

import io.musician101.mcdndsimple.sponge.character.AbilityScore;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.Optional;
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

    public static Optional<SaveDCType> fromDataContainer(DataContainer dataContainer)//NOSONAR
    {
        Optional<String> nameOptional = dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery());
        if (!nameOptional.isPresent())
            return Optional.empty();

        String name = nameOptional.get();
        if ("Custom DC".equals(name))
        {
            Optional<Integer> custom = dataContainer.getInt(MCDNDSimpleKeys.CUSTOM_DC.getQuery());
            if (custom.isPresent())
                return Optional.of(SaveDCTypes.custom(custom.get()));
        }
        else if ("Arcane Trickster DC".equals(name))
            return Optional.of(SaveDCTypes.ARCANE_TRICKSTER);
        else if ("Bard DC".equals(name))
            return Optional.of(SaveDCTypes.BARD);
        else if ("Cleric DC".equals(name))
            return Optional.of(SaveDCTypes.CLERIC);
        else if ("Druid DC".equals(name))
            return Optional.of(SaveDCTypes.DRUID);
        else if ("Eldritch Knight DC".equals(name))
            return Optional.of(SaveDCTypes.ELDRITCH_KNIGHT);
        else if ("Paladin DC".equals(name))
            return Optional.of(SaveDCTypes.PALADIN);
        else if ("Sorcerer DC".equals(name))
            return Optional.of(SaveDCTypes.SORCERER);
        else if ("Warlock DC".equals(name))
            return Optional.of(SaveDCTypes.WARLOCK);
        else if ("Wizard DC".equals(name))
            return Optional.of(SaveDCTypes.WIZARD);

        return Optional.empty();
    }
}
