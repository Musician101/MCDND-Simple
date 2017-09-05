package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.spell.SpellSave;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.SpellcasterClassGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class SpellSaveGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final SpellSave spellSave;

    public SpellSaveGUI(Player player, CoreStats coreStats, SpellSave spellSave, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.SAVE, prevGUI);
        this.spellSave = spellSave;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.SHIELD, MenuText.SAVING_STAT), player -> new SpellSavingStatGUI(player, coreStats, spellSave, this));
        set(1, createItem(Material.ENCHANTMENT_TABLE, MenuText.SAVE_DC), player -> new SpellcasterClassGUI<>(player, spellSave, SpellSave::setSaveDCType, spellcasterClass -> spellcasterClass.equals(spellSave.getSpellcasterClass()), this));
        set(2, createItem(Material.ENCHANTED_BOOK, MenuText.customDC(spellSave.getCustomDC())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellSave.setCustomDC(i);
            delayedOpen();
        }));
        set(3, createItem(Material.BOOK, MenuText.ON_SUCCESSFUL_SAVE), player -> {
            ItemStack book = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setTitle(MenuText.ON_SUCCESSFUL_SAVE);
            meta.setPages(spellSave.getOnSuccessfulSave());
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, book, spellSave::setOnSuccessfulSave);
        });
        setBackButton(8, Material.BARRIER);
    }
}
