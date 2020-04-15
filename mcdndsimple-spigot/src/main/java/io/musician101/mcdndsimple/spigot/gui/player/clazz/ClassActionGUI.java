package io.musician101.mcdndsimple.spigot.gui.player.clazz;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.GainedFromGUI;
import io.musician101.mcdndsimple.spigot.gui.player.OutputOptionsGUI;
import io.musician101.mcdndsimple.spigot.gui.player.RechargeGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ClassActionGUI extends SpigotMCDNDSimpleGUI {

    public ClassActionGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getName(), 9);
        ClassAction classAction = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index);
        setButton(0, SpigotIconBuilder.of(Material.NAME_TAG, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                classAction.setName(s);
                new ClassActionGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.REDSTONE_TORCH, MenuText.used(classAction.getUsed())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                classAction.setUsed(i);
                new ClassActionGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(2, SpigotIconBuilder.of(Material.TORCH, MenuText.maxUses(classAction.getMaxUses())), ImmutableMap.of(ClickType.LEFT, p -> new RechargeGUI<>(mcdndPlayer, RechargeGUI.CLASS_ACTIONS, index, p)));
        setButton(3, SpigotIconBuilder.of(Material.RED_BED, MenuText.recharge(classAction.getRecharge())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                classAction.setUsed(i);
                new ClassActionGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(4, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.GAINED_FROM), ImmutableMap.of(ClickType.LEFT, p -> new GainedFromGUI(mcdndPlayer, index, p)));
        setButton(5, SpigotIconBuilder.of(Material.REDSTONE, MenuText.OUTPUT_OPTIONS), ImmutableMap.of(ClickType.LEFT, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new ClassActionsGUI(mcdndPlayer, p)));
    }
}
