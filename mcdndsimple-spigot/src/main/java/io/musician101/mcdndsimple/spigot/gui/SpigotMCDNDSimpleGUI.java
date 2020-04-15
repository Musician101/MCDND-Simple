package io.musician101.mcdndsimple.spigot.gui;

import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public abstract class SpigotMCDNDSimpleGUI extends SpigotChestGUI<SpigotMCDNDSimple> {

    protected static final ItemStack BACK_ICON = SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK);

    protected SpigotMCDNDSimpleGUI(@Nonnull Player player, @Nonnull String name, int size) {
        super(player, name, size, SpigotMCDNDSimple.instance(), false);
    }

    @Nonnull
    protected final ItemStack createBook(@Nonnull Player player, @Nonnull String title, List<String> pages) {
        ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setTitle(title);
        bookMeta.setPages(pages);
        bookMeta.setAuthor(player.getName());
        book.setItemMeta(bookMeta);
        return book;
    }

    @Nonnull
    protected final String openBookCommand(@Nonnull BioAndInfo bioAndInfo, @Nonnull List<String> pages, @Nonnull Spell spell) {
        return "/callback " + SpigotMCDNDSimple.instance().getCallbackTracker().getIdForCallback(sender -> {
            Player p = (Player) sender;
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setAuthor(bioAndInfo.getName());
            meta.setPages(pages);
            meta.setTitle(spell.getName());
            book.setItemMeta(meta);
            SpigotBookGUI.openWrittenBook(p, book);
        }).toString();
    }
}
