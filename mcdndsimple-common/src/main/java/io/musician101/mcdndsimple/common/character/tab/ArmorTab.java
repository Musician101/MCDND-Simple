package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;

import java.util.ArrayList;
import java.util.List;

public class ArmorTab
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
}
