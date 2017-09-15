package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class SpellDescriptionGUI extends MCDNDSimpleChestGUI {

    private final Spell spell;

    public SpellDescriptionGUI(Player player, Spell spell, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.DESCRIPTION, prevGUI);
        this.spell = spell;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.BOOK, MenuText.DESCRIPTION), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(player.getName());
            bookMeta.setPages(spell.getDescription());
            bookMeta.setTitle(spell.getName());
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                spell.setDescription(pages);
                open();
            });
        });
        set(1, ClickType.LEFT, createItem(Material.BOOK, MenuText.AT_HIGHER_LEVELS), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setAuthor(player.getName());
            bookMeta.setPages(spell.getAtHigherLevels());
            bookMeta.setTitle(spell.getName());
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                spell.setDescription(pages);
                open();
            });
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
