package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.tab.ClassTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.classactions.ClassActionsGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class ClassTabGUI extends MCDNDSimpleChestGUI {

    private final ClassTab classTab;

    public ClassTabGUI(Player player, ClassTab classTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.CLASS, prevGUI);
        this.classTab = classTab;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.ENCHANTED_BOOK, MenuText.CLASS_LEVELS), player -> new ClassLevelsGUI(player, classTab.getClassLevels(), this));
        set(1, ClickType.LEFT, createItem(Material.DIAMOND, MenuText.CLASS_RESOURCES), player -> new ClassResourcesGUI(player, classTab.getClassResources(), 0, this));
        set(2, ClickType.LEFT, createItem(Material.BOOK, MenuText.CLASS_FEATURE_NOTES), player -> {
            ItemStack classFeatureNotes = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta classFeatureNotesMeta = (BookMeta) classFeatureNotes.getItemMeta();
            classFeatureNotesMeta.setLore(classTab.getClassFeatureNotes());
            classFeatureNotes.setItemMeta(classFeatureNotesMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, classFeatureNotes, pages -> {
                classTab.setClassFeatureNotes(pages);
                open();
            });
        });
        set(3, ClickType.LEFT, createItem(Material.BOOK, MenuText.CLASS_FEATURE_NOTES), player -> {
            ItemStack otherNotes = new ItemStack(Material.BOOK_AND_QUILL);
            BookMeta otherNotesMeta = (BookMeta) otherNotes.getItemMeta();
            otherNotesMeta.setLore(classTab.getClassFeatureNotes());
            otherNotes.setItemMeta(otherNotesMeta);
            new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), player, otherNotes, pages -> {
                classTab.setOtherNotes(pages);
                open();
            });
        });
        set(4, ClickType.LEFT, createItem(Material.REDSTONE_TORCH_ON, MenuText.CLASS_ACTIONS), player -> new ClassActionsGUI(player, classTab.getClassActions(), 0, this));
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
