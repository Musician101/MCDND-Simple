package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerHitPoints;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class NonPlayerHitPointsGUI extends SpigotMCDNDSimpleGUI {

    public NonPlayerHitPointsGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.HIT_POINTS, 9);
        NonPlayerHitPoints hitPoints = nonPlayer.getNonPlayerSheet().getHealth();
        setButton(0, SpigotIconBuilder.builder(Material.POTION).name(MenuText.currentHitPoints(hitPoints)).potionEffect(PotionType.REGEN).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                hitPoints.setCurrent(i);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.builder(Material.POTION).name(MenuText.maxHitPoints(hitPoints)).potionEffect(PotionType.INSTANT_HEAL).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                hitPoints.setMax(i);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpigotIconBuilder.builder(Material.POTION).name(MenuText.tempHitPoints(hitPoints)).potionEffect(PotionType.STRENGTH).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                hitPoints.setTemp(i);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.hitDice(hitPoints)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                hitPoints.setHitDice(dice);
                new NonPlayerHitPointsGUI(nonPlayer, player);
            }
        }));
    }
}
