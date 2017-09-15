package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.armor;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ArmorTypeGUI extends MCDNDSimpleChestGUI {

    private final Armor armor;

    public ArmorTypeGUI(Player player, Armor armor, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.ARMOR_TYPE, prevGUI);
        this.armor = armor;
    }

    @Override
    protected void build() {
        ArmorType[] armorTypes = ArmorType.values();
        for (int i = 0; i < armorTypes.length; i++) {
            ArmorType armorType = armorTypes[i];
            ItemStack itemStack = ItemRepresentation.armorType(armorType);
            if (armorType == armor.getArmorType()) {
                itemStack = addGlow(itemStack);
            }

            set(i, ClickType.LEFT, itemStack, player -> {
                armor.setArmorType(armorType);
                if (prevGUI == null) {
                    player.closeInventory();
                }
                else {
                    prevGUI.open();
                }
            });
        }

        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
