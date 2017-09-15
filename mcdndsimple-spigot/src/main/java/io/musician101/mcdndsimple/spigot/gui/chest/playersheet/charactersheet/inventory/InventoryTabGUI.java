package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.tab.InventoryTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class InventoryTabGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final InventoryTab inventoryTab;

    public InventoryTabGUI(Player player, InventoryTab inventoryTab, CoreStats coreStats, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.INVENTORY, prevGUI);
        this.inventoryTab = inventoryTab;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        Wealth wealth = inventoryTab.getWealth();
        set(0, ClickType.LEFT, createItem(Material.EMERALD, MenuText.COIN_CARRIED, MenuText.coinCarriedDescription(wealth)), player -> new WealthGUI(player, inventoryTab.getWealth(), this));
        set(1, ClickType.LEFT, createItem(Material.STONE, MenuText.WEIGHT), player -> new WeightGUI(player, inventoryTab.getWeight(), coreStats, this));
        set(2, ClickType.LEFT, createItem(Material.CHEST, MenuText.INVENTORY), player -> new InventoryGUI(player, inventoryTab.getInventory(), 0, this));
        set(3, ClickType.LEFT, createItem(Material.BOOK, MenuText.INVENTORY_NOTES), player -> {
            ItemStack book = createItem(Material.BOOK_AND_QUILL, MenuText.INVENTORY_NOTES);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(inventoryTab.getInventoryNotes());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                inventoryTab.setInventoryNotes(pages);
                open();
            });
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
