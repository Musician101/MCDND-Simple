package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.character.UnarmoredBonus;
import io.musician101.mcdndsimple.sponge.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ArmorTab implements DataSerializable
{
    private int armorClass = 0;
    private int unarmoredArmorClass = 10;
    private final List<Armor> armorList = new ArrayList<>();
    private UnarmoredBonus unarmoredBonus = null;

    public int getArmorClass()
    {
        return armorClass;
    }

    public List<Armor> getArmorList()
    {
        return armorList;
    }

    public void addArmor(Armor armor)
    {
        armorList.add(armor);
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
                .set(MCDNDSimpleKeys.ARMOR_CLASS, armorClass)
                .set(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, unarmoredArmorClass)
                .set(MCDNDSimpleKeys.ARMOR_LIST, DataUtils.serialize(armorList))
                .set(MCDNDSimpleKeys.UNARMORED_BONUS, unarmoredBonus.toContainer());
    }

    public void removeArmor(Armor armor)
    {
        armorList.remove(armor);
    }

    public int getUnarmoredArmorClass()
    {
        return unarmoredArmorClass;
    }

    public UnarmoredBonus getUnarmoredBonus()
    {
        return unarmoredBonus;
    }

    public void setArmorClass(int armorClass)
    {
        this.armorClass = armorClass;
    }

    public void setUnarmoredArmorClass(int unarmoredArmorClass)
    {
        this.unarmoredArmorClass = unarmoredArmorClass;
    }

    public void setUnarmoredBonus(UnarmoredBonus unarmoredBonus)
    {
        this.unarmoredBonus = unarmoredBonus;
    }
}
