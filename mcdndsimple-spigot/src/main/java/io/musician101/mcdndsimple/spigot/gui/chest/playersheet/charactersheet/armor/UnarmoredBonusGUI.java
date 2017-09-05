package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.character.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.tab.ArmorTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UnarmoredBonusGUI extends MCDNDSimpleChestGUI {

    private ArmorTab armorTab;

    public UnarmoredBonusGUI(Player player, ArmorTab armorTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, "Unarmored Bonus", prevGUI);
        this.armorTab = armorTab;
    }

    @Override
    protected void build() {
        UnarmoredBonus[] unarmoredBonuses = UnarmoredBonus.values();
        for (int i = 0; i < unarmoredBonuses.length; i++) {
            UnarmoredBonus unarmoredBonus = unarmoredBonuses[i];
            ItemStack itemStack = ItemRepresentation.unarmoredBonus(unarmoredBonus);
            if (armorTab.getUnarmoredBonus() == unarmoredBonus) {
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                itemStack.setItemMeta(itemMeta);
            }

            set(i, itemStack, player -> {
                armorTab.setUnarmoredBonus(unarmoredBonus);
                if (prevGUI == null) {
                    player.closeInventory();
                }
                else {
                    prevGUI.open();
                }
            });
        }

        setBackButton(8, Material.BARRIER);
    }
}
