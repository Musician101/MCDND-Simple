package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class NonPlayerActionsGUI extends SpigotMCDNDSimpleGUI {

    public NonPlayerActionsGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.ACTIONS, 9);
        NonPlayerActions nonPlayerActions = nonPlayer.getNonPlayerActions();
        setButton(0, SpigotIconBuilder.builder(Material.IRON_SWORD).name(MenuText.MULTI_ATTACK).description(nonPlayerActions.getMultiAttack()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerActions.setMultiAttack(s);
                new NonPlayerActionsGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.GOLDEN_SWORD, MenuText.ACTIONS), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerActionsListGUI(nonPlayer, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerGUI(nonPlayer, p)));
    }
}
