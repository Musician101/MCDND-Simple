package io.musician101.mcdndsimple.common.character.player.tab;

import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import java.util.ArrayList;
import java.util.List;

public class ArmorTab {

    private int armorClass = 0;
    private List<Armor> armorList = new ArrayList<>();
    private UnarmoredBonus unarmoredBonus = UnarmoredBonus.NONE;
    private int unarmoredClass = 10;

    public void addArmor(Armor armor) {
        armorList.add(armor);
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public List<Armor> getArmorList() {
        return armorList;
    }

    public UnarmoredBonus getUnarmoredBonus() {
        return unarmoredBonus;
    }

    public void setUnarmoredBonus(UnarmoredBonus unarmoredBonus) {
        this.unarmoredBonus = unarmoredBonus;
    }

    public int getUnarmoredClass() {
        return unarmoredClass;
    }

    public void setUnarmoredClass(int unarmoredArmorClass) {
        this.unarmoredClass = unarmoredArmorClass;
    }

    public void removeArmor(Armor armor) {
        armorList.add(armor);
    }

    public void setArmor(List<Armor> armor) {
        this.armorList = armor;
    }
}
