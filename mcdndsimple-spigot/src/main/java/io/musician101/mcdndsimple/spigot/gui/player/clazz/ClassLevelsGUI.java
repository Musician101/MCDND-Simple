package io.musician101.mcdndsimple.spigot.gui.player.clazz;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.CharacterSheetGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class ClassLevelsGUI extends SpigotMCDNDSimpleGUI {

    public ClassLevelsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CLASS_LEVELS, 18);
        ClassLevels classLevels = mcdndPlayer.getCharacterSheet().getClassTab().getClassLevels();
        setButton(0, SpigotIconBuilder.of(Material.WOODEN_SWORD, MenuText.BARBARIAN), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setBarbarian(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.NOTE_BLOCK, MenuText.BARD), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setBard(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(2, SpigotIconBuilder.builder(Material.POTION).name(MenuText.CLERIC).potionEffect(PotionType.INSTANT_HEAL).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setCleric(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.builder(Material.POTION).name(MenuText.DRUID).potionEffect(PotionType.LUCK).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setDruid(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(4, SpigotIconBuilder.of(Material.IRON_SWORD, MenuText.FIGHTER), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setFighter(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(5, SpigotIconBuilder.builder(Material.POTION).name(MenuText.MONK).potionEffect(PotionType.SPEED).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setMonk(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(9, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.PALADIN), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setPaladin(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(10, SpigotIconBuilder.of(Material.BOW, MenuText.RANGER), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setRanger(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(11, SpigotIconBuilder.of(Material.GOLDEN_SWORD, MenuText.ROGUE), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setRogue(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(12, SpigotIconBuilder.of(Material.MAGMA_CREAM, MenuText.SORCERER), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setSorcerer(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(13, SpigotIconBuilder.of(Material.END_CRYSTAL, MenuText.WARLOCK), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setWarlock(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(14, SpigotIconBuilder.of(Material.TOTEM_OF_UNDYING, MenuText.WIZARD), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + s + " is not a number.");
                    return;
                }

                classLevels.setWizard(i);
                new ClassLevelsGUI(mcdndPlayer, player);
            }
        }));
        setButton(17, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
