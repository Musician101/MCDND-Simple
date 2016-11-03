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
    private int unarmoredClass = 10;
    private List<Armor> armorList = new ArrayList<>();
    private UnarmoredBonus unarmoredBonus = UnarmoredBonus.NONE;

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
                .set(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, unarmoredClass)
                .set(MCDNDSimpleKeys.ARMOR_LIST, DataUtils.serialize(armorList))
                .set(MCDNDSimpleKeys.UNARMORED_BONUS, unarmoredBonus.toContainer());
    }

    public void removeArmor(Armor armor)
    {
        armorList.add(armor);
    }

    public void setArmor(List<Armor> armor)
    {
        this.armorList = armor;
    }

    public int getUnarmoredClass()
    {
        return unarmoredClass;
    }

    public UnarmoredBonus getUnarmoredBonus()
    {
        return unarmoredBonus;
    }

    public void setArmorClass(int armorClass)
    {
        this.armorClass = armorClass;
    }

    public void setUnarmoredClass(int unarmoredArmorClass)
    {
        this.unarmoredClass = unarmoredArmorClass;
    }

    public void setUnarmoredBonus(UnarmoredBonus unarmoredBonus)
    {
        this.unarmoredBonus = unarmoredBonus;
    }

    public static ArmorTab fromDataContainer(DataContainer dataContainer)
    {
        ArmorTab armorTab = new ArmorTab();
        dataContainer.getInt(MCDNDSimpleKeys.ARMOR_CLASS.getQuery()).ifPresent(armorTab::setArmorClass);
        dataContainer.getInt(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS.getQuery()).ifPresent(armorTab::setUnarmoredClass);
        DataUtils.getDataContainerList(dataContainer, MCDNDSimpleKeys.ARMOR_LIST).ifPresent(list -> list.forEach(data -> armorTab.addArmor(Armor.fromDataContainer(data))));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.UNARMORED_BONUS).ifPresent(data -> UnarmoredBonus.fromDataContainer(data).ifPresent(armorTab::setUnarmoredBonus));
        return armorTab;
    }
}
