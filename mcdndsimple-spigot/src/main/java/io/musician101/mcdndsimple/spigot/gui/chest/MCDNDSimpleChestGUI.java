package io.musician101.mcdndsimple.spigot.gui.chest;

import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.entity.Player;

/**
 * @deprecated need to change all the biconsumers for the anvil gui's so that they have delayed tasks to open the previous gui
 * need to look through each stat bonus that relies on core stats to make sure it highlights the selected option
 * need to make sure all uses of SpigotBookGUI actually reopens the inventory and doesn't use player.closeInventory() at any point
 * double check all back buttons are set to the proper value
 */
@Deprecated
public abstract class MCDNDSimpleChestGUI extends AbstractSpigotChestGUI<SpigotMCDNDSimple> {

    public MCDNDSimpleChestGUI(Player player, int size, String name, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, name, size, prevGUI, SpigotMCDNDSimple.instance());
    }
}
