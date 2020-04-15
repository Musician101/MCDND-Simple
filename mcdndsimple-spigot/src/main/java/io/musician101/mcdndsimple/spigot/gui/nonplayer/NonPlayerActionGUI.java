package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class NonPlayerActionGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final NonPlayerAction nonPlayerAction;

    public NonPlayerActionGUI(@Nonnull NonPlayer nonPlayer, int index, @Nonnull Player player) {
        super(player, nonPlayer.getNonPlayerActions().getActions().get(index).getName(), 9);
        nonPlayerAction = nonPlayer.getNonPlayerActions().getActions().get(index);
        setButton(0, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerAction.setName(s);
                new NonPlayerActionGUI(nonPlayer, index, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.actionType(nonPlayerAction.getActionType())), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerActionTypeGUI(nonPlayer, index, p)));
        setButton(2, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, nonPlayerAction.getName(), nonPlayerAction.getDescription()), (ply, pages) -> {
            nonPlayerAction.setDescription(pages);
            new NonPlayerActionGUI(nonPlayer, index, ply);
        })));
        setButton(3, SpigotIconBuilder.of(Material.BOOK, MenuText.EFFECT), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, nonPlayerAction.getName(), nonPlayerAction.getEffect()), (ply, pages) -> {
            nonPlayerAction.setEffect(pages);
            new NonPlayerActionGUI(nonPlayer, index, ply);
        })));
        updateIsMultiAttack();
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerActionsListGUI(nonPlayer, p)));
    }

    private void updateIsMultiAttack() {
        setButton(4, SpigotIconBuilder.builder(Material.GOLDEN_SWORD).name((nonPlayerAction.isMultiAttack() ? ChatColor.GREEN : ChatColor.RED) + MenuText.EFFECT).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            nonPlayerAction.setIsMultiAttack(!nonPlayerAction.isMultiAttack());
            updateIsMultiAttack();
        }));
    }
}
