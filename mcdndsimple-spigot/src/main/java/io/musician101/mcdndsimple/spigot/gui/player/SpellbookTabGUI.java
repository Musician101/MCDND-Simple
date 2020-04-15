package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellbookTabGUI extends SpigotMCDNDSimpleGUI {

    public SpellbookTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.SPELL_DASHBOARD, 9);
        setButton(0, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.SPELL_DASHBOARD), ImmutableMap.of(ClickType.LEFT, p -> new SpellDashboardGUI(mcdndPlayer, p)));
        setButton(1, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SPELLS), ImmutableMap.of(ClickType.LEFT, p -> new SpellBookGUI(mcdndPlayer, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
