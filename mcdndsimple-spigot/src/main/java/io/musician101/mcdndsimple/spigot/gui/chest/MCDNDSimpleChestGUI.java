package io.musician101.mcdndsimple.spigot.gui.chest;

import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

/**
 * @deprecated need to change all the biconsumers for the anvil gui's so that they have delayed tasks to open the previous gui
 * need to look through each stat bonus that relies on core stats to make sure it highlights the selected option
 * need to make sure all uses of SpigotBookGUI actually reopens the inventory and doesn't use player.closeInventory() at any point
 * double check all back buttons are set to the proper value
 */
@Deprecated
public abstract class MCDNDSimpleChestGUI extends AbstractSpigotChestGUI<SpigotMCDNDSimple> {

    public MCDNDSimpleChestGUI(Player player, int size, String name, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, size, name, prevGUI, SpigotMCDNDSimple.instance());
    }

    protected ItemStack addGlow(@Nonnull ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    protected ItemStack addGlowIfConditionsMet(@Nonnull ItemStack itemStack, @Nonnull Supplier<Boolean> conditionSupplier) {
        if (conditionSupplier.get()) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    protected void delayedOpen() {
        Bukkit.getScheduler().runTaskLater(SpigotMCDNDSimple.instance(), this::open, 1L);
    }

    protected <G extends MCDNDSimpleChestGUI> void delayedOpen(Supplier<G> gui) {
        Bukkit.getScheduler().runTaskLater(SpigotMCDNDSimple.instance(), gui::get, 1L);
    }

    protected ItemStack setDurability(ItemStack itemStack, int durability) {
        itemStack.setDurability((short) durability);
        return itemStack;
    }

    protected ItemStack setPotionEffect(@Nonnull ItemStack itemStack, @Nonnull PotionType potionType) {
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.setBasePotionData(new PotionData(potionType));
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemStack.setItemMeta(potionMeta);
        return itemStack;
    }
}
