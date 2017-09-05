package io.musician101.mcdndsimple.spigot.gui.chest.playersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BioAndInfoGUI extends MCDNDSimpleChestGUI
{
    private final BioAndInfo bioAndInfo;

    public BioAndInfoGUI(Player player, BioAndInfo bioAndInfo, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI)
    {
        super(player, 9, MenuText.BIO_AND_INFO, prevGUI);
        this.bioAndInfo = bioAndInfo;
    }

    @Override
    protected void build()
    {
        set(0, createItem(Material.PAPER, MenuText.name(bioAndInfo)), player -> new StringInputAnvilGUI(player, (p, s) -> bioAndInfo.setName(s)));
        set(1, createItem(Material.BOOK, MenuText.BIO), player ->
        {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            bookMeta.setPages(bioAndInfo.getBio());
            book.setItemMeta(bookMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, pages -> {
                bioAndInfo.setBio(pages);
                open();
            });
            //TODO need to pass an update to charactersheet
        });

        setBackButton(8, Material.BARRIER);
    }
}
