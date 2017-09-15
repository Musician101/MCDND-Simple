package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.MCDNDItem;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.DoubleInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class ItemGUI extends MCDNDSimpleChestGUI {

    private final MCDNDItem item;
    private final List<MCDNDItem> items;

    public ItemGUI(Player player, MCDNDItem item, List<MCDNDItem> items, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, item.getName(), prevGUI);
        this.item = item;
        this.items = items;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.PAPER, item.getName()), player -> new StringInputAnvilGUI(player, (p, s) -> {
            item.setName(s);
            delayedOpen();
        }));

        set(1, ClickType.LEFT, createItem(item.isCarried() ? Material.REDSTONE_TORCH_ON : Material.REDSTONE_TORCH_OFF, MenuText.carried(item)), player -> {
            item.setIsCarried(!item.isCarried());
            open();
        });

        set(2, ClickType.LEFT, createItem(Material.CHEST, MenuText.quantity(item)), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            item.setQuantity(i);
            delayedOpen();
        }));

        set(3, ClickType.LEFT, createItem(Material.OBSIDIAN, MenuText.weight(item)), player -> new DoubleInputAnvilGUI(player, (p, d) -> {
            item.setWeight(d);
            delayedOpen();
        }));

        set(4, ClickType.LEFT, createItem(Material.BOOK, MenuText.DESCRIPTION), player -> {
            ItemStack book = createItem(Material.BOOK_AND_QUILL, MenuText.DESCRIPTION);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(item.getDescription());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                item.setDescription(pages);
                open();
            });
        });

        set(5, ClickType.LEFT, createItem(Material.ENDER_CHEST, MenuText.DELETE), player -> {
            items.remove(item);
            if (prevGUI != null) {
                prevGUI.open();
            }
            else {
                player.closeInventory();
                player.sendMessage(ChatColor.GREEN + Messages.ITEM_DELETED);
            }
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
