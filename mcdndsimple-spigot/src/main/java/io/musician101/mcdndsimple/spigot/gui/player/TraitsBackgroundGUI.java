package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.TraitsBackground;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.nonplayer.NonPlayerGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class TraitsBackgroundGUI extends SpigotMCDNDSimpleGUI {

    public TraitsBackgroundGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.TRAITS_BACKGROUND, 9);
        TraitsBackground traitsBackground = nonPlayer.getTraitsBackground();
        setButton(0, SpigotIconBuilder.builder(Material.MILK_BUCKET).name(MenuText.CONDITION_IMMUNITY).description(traitsBackground.getConditionImmunity()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setConditionImmunity(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.builder(Material.DIAMOND_CHESTPLATE).name(MenuText.DAMAGE_IMMUNITY).description(traitsBackground.getDamageImmunity()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setDamageImmunity(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpigotIconBuilder.builder(Material.SHIELD).name(MenuText.DAMAGE_RESISTANCE).description(traitsBackground.getDamageResistance()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setDamageResistance(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.builder(Material.DIAMOND_SWORD).name(MenuText.DAMAGE_VULNERABILITY).description(traitsBackground.getDamageVulnerability()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                traitsBackground.setDamageVulnerability(s);
                new TraitsBackgroundGUI(nonPlayer, player);
            }
        }));
        setButton(4, SpigotIconBuilder.builder(Material.SHIELD).name(MenuText.TRAITS).description(traitsBackground.getDamageResistance()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.TRAITS, traitsBackground.getTraits()), (ply, pages) -> {
            traitsBackground.setTraits(pages);
            new TraitsBackgroundGUI(nonPlayer, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerGUI(nonPlayer, p)));
    }
}
