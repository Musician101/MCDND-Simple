package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.armor.ArmorTabGUI;
import io.musician101.mcdndsimple.spigot.gui.player.clazz.ClassTabGUI;
import io.musician101.mcdndsimple.spigot.gui.player.corestats.CoreStatsTabGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class CharacterSheetGUI extends SpigotMCDNDSimpleGUI {

    public CharacterSheetGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CHARACTER_SHEET, 9);
        setButton(0, SpigotIconBuilder.of(Material.DIAMOND_CHESTPLATE, MenuText.ARMOR), ImmutableMap.of(ClickType.LEFT, p -> new ArmorTabGUI(mcdndPlayer, p)));
        setButton(1, SpigotIconBuilder.of(Material.BOOK, MenuText.BACKGROUND), ImmutableMap.of(ClickType.LEFT, p -> new BackgroundTabGUI(mcdndPlayer, p)));
        setButton(2, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.CLASS), ImmutableMap.of(ClickType.LEFT, p -> new ClassTabGUI(mcdndPlayer, p)));
        setButton(3, SpigotIconBuilder.of(Material.DIAMOND, MenuText.CORE_STATS), ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
        setButton(4, SpigotIconBuilder.of(Material.CHEST, MenuText.INVENTORY), ImmutableMap.of(ClickType.LEFT, p -> new InventoryTabGUI(mcdndPlayer, p)));
        setButton(5, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SKILLS), ImmutableMap.of(ClickType.LEFT, p -> new SkillsTabGUI(mcdndPlayer, p)));
        setButton(6, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, MenuText.SPELLBOOK), ImmutableMap.of(ClickType.LEFT, p -> new SpellbookTabGUI(mcdndPlayer, p)));
        setButton(7, SpigotIconBuilder.of(Material.ENCHANTING_TABLE, MenuText.INVENTORY), ImmutableMap.of(ClickType.LEFT, p -> new WeaponsTabGUI(mcdndPlayer, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
