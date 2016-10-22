package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.character.UnarmoredBonus;
import io.musician101.mcdndsimple.sponge.character.equipment.armor.Armor;

import java.util.ArrayList;
import java.util.List;

public class ArmorTab
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
