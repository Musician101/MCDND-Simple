package io.musician101.mcdndsimple.sponge.character.equipment.armor;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.Optional;

public enum MCDNDArmorType implements DataSerializable
{
    LIGHT("Light"),
    MEDIUM("Medium"),
    HEAVY("Heavy"),
    NONE("None");

    private final String name;

    MCDNDArmorType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
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

    public static Optional<MCDNDArmorType> fromDataContainer(DataContainer dataContainer)
    {
        return dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
        {
            for (MCDNDArmorType armorType : values())
                if (armorType.getName().equals(name))
                    return Optional.of(armorType);

            return Optional.empty();
        });
    }
}
