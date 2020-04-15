package io.musician101.mcdndsimple.spigot.gui.player.corestats;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
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

public class HitPointsGUI extends SpigotMCDNDSimpleGUI {

    public HitPointsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.HIT_POINTS, 9);
        HitPoints hitPoints = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getHitPoints();
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
                new HitPointsGUI(mcdndPlayer, player);
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
                new HitPointsGUI(mcdndPlayer, player);
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
                new HitPointsGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }
}
