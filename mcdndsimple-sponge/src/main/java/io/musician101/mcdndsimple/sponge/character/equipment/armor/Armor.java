package io.musician101.mcdndsimple.sponge.character.equipment.armor;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.Optional;

public class Armor implements DataSerializable
{
    private boolean speedPenalty = false;
    private boolean stealthPenalty = false;
    private boolean unarmored = false;
    private boolean worn = true;
    private int baseArmorClass = 0;
    private int magicBonus = 0;
    private MCDNDArmorType armorType = MCDNDArmorType.NONE;
    private String name = "";

    public MCDNDArmorType getArmorType()
    {
        return armorType;
    }

    public int getBaseArmorClass()
    {
        return baseArmorClass;
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
                .set(MCDNDSimpleKeys.SPEED_PENALTY, speedPenalty)
                .set(MCDNDSimpleKeys.STEALTH_PENALTY, stealthPenalty)
                .set(MCDNDSimpleKeys.UNARMORED, unarmored)
                .set(MCDNDSimpleKeys.WORN, worn)
                .set(MCDNDSimpleKeys.BASE_ARMOR_CLASS, baseArmorClass)
                .set(MCDNDSimpleKeys.MAGIC_BONUS, magicBonus)
                .set(MCDNDSimpleKeys.ARMOR_TYPE, armorType.toContainer())
                .set(MCDNDSimpleKeys.NAME, name);
    }

    public int getMagicBonus()
    {
        return magicBonus;
    }

    public String getName()
    {
        return name;
    }

    public boolean hasSpeedPenalty()
    {
        return speedPenalty;
    }

    public boolean hasStealthPenalty()
    {
        return stealthPenalty;
    }

    public boolean isUnarmored()
    {
        return unarmored;
    }

    public boolean isWorn()
    {
        return worn;
    }

    public void setArmorType(MCDNDArmorType armorType)
    {
        this.armorType = armorType;
    }

    public void setBaseArmorClass(int baseArmorClass)
    {
        this.baseArmorClass = baseArmorClass;
    }

    public void setMagicBonus(int magicBonus)
    {
        this.magicBonus = magicBonus;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSpeedPenalty(boolean speedPenalty)
    {
        this.speedPenalty = speedPenalty;
    }

    public void setStealthPenalty(boolean stealthPenalty)
    {
        this.stealthPenalty = stealthPenalty;
    }

    public void setUnarmored(boolean unarmored)
    {
        this.unarmored = unarmored;
    }

    public void setWorn(boolean worn)
    {
        this.worn = worn;
    }

    public static Armor fromDataContainer(DataContainer dataContainer)
    {
        Armor armor = new Armor();
        dataContainer.getBoolean(MCDNDSimpleKeys.SPEED_PENALTY.getQuery()).ifPresent(armor::setSpeedPenalty);
        dataContainer.getBoolean(MCDNDSimpleKeys.STEALTH_PENALTY.getQuery()).ifPresent(armor::setStealthPenalty);
        dataContainer.getBoolean(MCDNDSimpleKeys.UNARMORED.getQuery()).ifPresent(armor::setUnarmored);
        dataContainer.getBoolean(MCDNDSimpleKeys.WORN.getQuery()).ifPresent(armor::setWorn);
        dataContainer.getInt(MCDNDSimpleKeys.BASE_ARMOR_CLASS.getQuery()).ifPresent(armor::setBaseArmorClass);
        dataContainer.getInt(MCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(armor::setMagicBonus);
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.ARMOR_TYPE).ifPresent(data ->
        {
            Optional<MCDNDArmorType> optional = MCDNDArmorType.fromDataContainer(data);
            if (optional.isPresent())
                armor.setArmorType(optional.get());
        });
        dataContainer.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(armor::setName);
        return armor;
    }
}
