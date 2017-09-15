package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.tab.ArmorTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ArmorTabGUI extends MCDNDSimpleChestGUI {

    private final ArmorTab armorTab;

    public ArmorTabGUI(Player player, ArmorTab armorTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.ARMOR, prevGUI);
        this.armorTab = armorTab;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.CHAINMAIL_CHESTPLATE, MenuText.armoredAC(armorTab)));
        set(1, createItem(Material.LEATHER_CHESTPLATE, MenuText.unarmoredAC(armorTab)));
        set(2, ClickType.LEFT, createItem(Material.DIAMOND_CHESTPLATE, MenuText.ARMOR), player -> new ArmorListGUI(player, armorTab.getArmorList(), 0, this));
        set(3, ClickType.LEFT, createItem(Material.IRON_CHESTPLATE, MenuText.UNARMORED_BONUS, MenuText.current(armorTab)), player -> new UnarmoredBonusGUI(player, armorTab, this));
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
