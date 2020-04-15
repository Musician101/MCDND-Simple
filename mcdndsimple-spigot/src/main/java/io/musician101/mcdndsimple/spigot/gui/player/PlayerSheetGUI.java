package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Permissions;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.EditControllersGUI;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class PlayerSheetGUI extends SpigotMCDNDSimpleGUI {

    public PlayerSheetGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.PLAYER_SHEET, 9);
        setButton(0, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setName(s);
                new PlayerSheetGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.CLASS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setClazz(s);
                new PlayerSheetGUI(mcdndPlayer, player);
            }
        }));
        setButton(2, SpigotIconBuilder.of(Material.INFESTED_STONE_BRICKS, MenuText.RACE), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                mcdndPlayer.setRace(s);
                new PlayerSheetGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.BOOK, MenuText.BIO_AND_INFO), ImmutableMap.of(ClickType.LEFT, p -> new BioAndInfoGUI(mcdndPlayer, p)));
        setButton(4, SpigotIconBuilder.of(Material.WRITABLE_BOOK, MenuText.CHARACTER_SHEET), ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
        if (player.hasPermission(Permissions.DM)) {
            setButton(5, SpigotIconBuilder.builder(Material.PLAYER_HEAD).name(MenuText.EDIT_CONTROLLERS).build(), ImmutableMap.of(ClickType.LEFT, p -> new EditControllersGUI(mcdndPlayer, EditControllersGUI.PLAYER, p)));
            setButton(6, SpigotIconBuilder.of(Material.ENDER_CHEST, MenuText.DELETE), ImmutableMap.of(ClickType.LEFT, p -> SpigotMCDNDSimple.instance().getPlayerStorage().remove(mcdndPlayer)));
        }

        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, PlayerSheetsGUI::new));
    }
}
