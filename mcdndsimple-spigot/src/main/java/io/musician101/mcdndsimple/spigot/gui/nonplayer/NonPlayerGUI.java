package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.EditControllersGUI;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.TraitsBackgroundGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class NonPlayerGUI extends SpigotMCDNDSimpleGUI {

    public NonPlayerGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.name(nonPlayer), 9);
        setButton(0, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayer.setName(s);
                new NonPlayerGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.CLASS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayer.setClazz(s);
                new NonPlayerGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpigotIconBuilder.of(Material.INFESTED_STONE_BRICKS, MenuText.RACE), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayer.setClazz(s);
                new NonPlayerGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.REDSTONE_TORCH, MenuText.ACTIONS), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerActionsGUI(nonPlayer, p)));
        setButton(4, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.CHARACTER_SHEET), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSheetGUI(nonPlayer, p)));
        setButton(5, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SKILLS), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSkillsGUI(nonPlayer, p)));
        setButton(5, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.TRAITS_BACKGROUND), ImmutableMap.of(ClickType.LEFT, p -> new TraitsBackgroundGUI(nonPlayer, p)));
        if (player.hasPermission(Permissions.DM)) {
            setButton(6, SpigotIconBuilder.of(Material.PLAYER_HEAD, MenuText.EDIT_CONTROLLERS), ImmutableMap.of(ClickType.LEFT, p -> new EditControllersGUI(nonPlayer, EditControllersGUI.NPC, p)));
            setButton(7, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), ImmutableMap.of(ClickType.LEFT, p -> SpigotMCDNDSimple.instance().getNonPlayerStorage().remove(nonPlayer)));
        }
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, NonPlayersGUI::new));
    }
}
