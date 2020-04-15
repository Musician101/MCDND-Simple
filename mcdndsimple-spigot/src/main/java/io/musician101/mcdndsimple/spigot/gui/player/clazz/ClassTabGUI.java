package io.musician101.mcdndsimple.spigot.gui.player.clazz;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.CharacterSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ClassTabGUI extends SpigotMCDNDSimpleGUI {

    public ClassTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CLASS, 9);
        ClassTab classTab = mcdndPlayer.getCharacterSheet().getClassTab();
        setButton(0, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.CLASS_LEVELS), ImmutableMap.of(ClickType.LEFT, p -> new ClassLevelsGUI(mcdndPlayer, p)));
        setButton(1, SpigotIconBuilder.of(Material.DIAMOND, MenuText.CLASS_RESOURCES), ImmutableMap.of(ClickType.LEFT, p -> new ClassResourcesGUI(mcdndPlayer, p)));
        setButton(2, SpigotIconBuilder.of(Material.BOOK, MenuText.CLASS_FEATURE_NOTES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes()), (ply, pages) -> {
            classTab.setClassFeatureNotes(pages);
            new ClassTabGUI(mcdndPlayer, p);
        })));
        setButton(3, SpigotIconBuilder.of(Material.BOOK, MenuText.OTHER_NOTES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.OTHER_NOTES, classTab.getOtherNotes()), (ply, pages) -> {
            classTab.setOtherNotes(pages);
            new ClassTabGUI(mcdndPlayer, p);
        })));
        setButton(4, SpigotIconBuilder.of(Material.REDSTONE_TORCH, MenuText.CLASS_ACTIONS), ImmutableMap.of(ClickType.LEFT, p -> new ClassActionsGUI(mcdndPlayer, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
