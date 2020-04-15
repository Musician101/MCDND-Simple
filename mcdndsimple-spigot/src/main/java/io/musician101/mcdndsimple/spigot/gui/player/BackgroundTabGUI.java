package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
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
import org.bukkit.potion.PotionType;

public class BackgroundTabGUI extends SpigotMCDNDSimpleGUI {

    public BackgroundTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.BACKGROUND, 27);
        BackgroundTab backgroundTab = mcdndPlayer.getCharacterSheet().getBackgroundTab();
        setButton(0, SpigotIconBuilder.of(Material.ARROW, MenuText.gender(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setGender(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.COD, MenuText.age(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int number;
                try {
                    number = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                backgroundTab.setAge(number);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(2, SpigotIconBuilder.of(Material.ARMOR_STAND, MenuText.height(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setHeight(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.IRON_BLOCK, MenuText.weight(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                double number;
                try {
                    number = Double.parseDouble(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                backgroundTab.setWeight(number);
                new BackgroundTabGUI(mcdndPlayer, player);

            }
        }));
        setButton(4, SpigotIconBuilder.of(Material.SPIDER_EYE, MenuText.eyes(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setEyes(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(5, SpigotIconBuilder.of(Material.RABBIT_HIDE, MenuText.hair(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setHair(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(6, SpigotIconBuilder.of(Material.LEATHER, MenuText.size(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setSize(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(7, SpigotIconBuilder.builder(Material.POTION).name(MenuText.vision(backgroundTab)).potionEffect(PotionType.NIGHT_VISION).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setVision(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, SpigotIconBuilder.of(Material.BOOK, MenuText.KNOWN_LANGUAGES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.KNOWN_LANGUAGES, backgroundTab.getLanguages()), (ply, pages) -> {
            backgroundTab.setLanguages(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(9, SpigotIconBuilder.of(Material.TOTEM_OF_UNDYING, MenuText.alignment(backgroundTab)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                backgroundTab.setAlignment(s);
                new BackgroundTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(10, SpigotIconBuilder.of(Material.BOOK, MenuText.BACKGROUND), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.BACKGROUND, backgroundTab.getBackground()), (ply, pages) -> {
            backgroundTab.setBackground(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(11, SpigotIconBuilder.of(Material.BOOK, MenuText.RACIAL_TRAITS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.RACIAL_TRAITS, backgroundTab.getRacialTraits()), (ply, pages) -> {
            backgroundTab.setRacialTraits(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(12, SpigotIconBuilder.of(Material.BOOK, MenuText.PERSONALITY_TRAITS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits()), (ply, pages) -> {
            backgroundTab.setPersonalityTraits(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(13, SpigotIconBuilder.of(Material.BOOK, MenuText.IDEALS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.IDEALS, backgroundTab.getIdeals()), (ply, pages) -> {
            backgroundTab.setIdeals(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(14, SpigotIconBuilder.of(Material.BOOK, MenuText.BONDS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.BONDS, backgroundTab.getBonds()), (ply, pages) -> {
            backgroundTab.setBonds(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(15, SpigotIconBuilder.of(Material.BOOK, MenuText.FLAWS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.FLAWS, backgroundTab.getFlaws()), (ply, pages) -> {
            backgroundTab.setFlaws(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(16, SpigotIconBuilder.of(Material.BOOK, MenuText.ARMOR_PROFICIENCIES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies()), (ply, pages) -> {
            backgroundTab.setArmorProficiencies(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(17, SpigotIconBuilder.of(Material.BOOK, MenuText.WEAPON_PROFICIENCIES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies()), (ply, pages) -> {
            backgroundTab.setWeaponProficiencies(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(18, SpigotIconBuilder.of(Material.BOOK, MenuText.TOOL_PROFICIENCIES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies()), (ply, pages) -> {
            backgroundTab.setToolProficiencies(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(19, SpigotIconBuilder.of(Material.BOOK, MenuText.OTHER_NOTES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.OTHER_NOTES, backgroundTab.getOtherNotes()), (ply, pages) -> {
            backgroundTab.setOtherNotes(pages);
            new BackgroundTabGUI(mcdndPlayer, p);
        })));
        setButton(22, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
