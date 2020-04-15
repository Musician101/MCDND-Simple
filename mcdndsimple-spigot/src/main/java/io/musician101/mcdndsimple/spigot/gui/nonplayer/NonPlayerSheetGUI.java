package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.CoreStatsGUI;
import io.musician101.mcdndsimple.spigot.gui.InitiativeGUI;
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

public class NonPlayerSheetGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final NonPlayer nonPlayer;

    public NonPlayerSheetGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.CHARACTER_SHEET, 18);
        this.nonPlayer = nonPlayer;
        NonPlayerSheet nonPlayerSheet = nonPlayer.getNonPlayerSheet();
        setButton(0, SpigotIconBuilder.of(Material.TOTEM_OF_UNDYING, MenuText.alignment(nonPlayerSheet)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setAlignment(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.IRON_CHESTPLATE, MenuText.armorClass(nonPlayerSheet)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                nonPlayerSheet.setArmorClass(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(2, SpigotIconBuilder.builder(Material.BOOK).name(MenuText.ARMOR_CLASS_NOTE).description(nonPlayerSheet.getArmorClassNote()).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setArmorClassNote(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.PLAYER_HEAD, MenuText.challengeRating(nonPlayerSheet)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                double d;
                try {
                    d = Double.parseDouble(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                nonPlayerSheet.setChallengeRating(d);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(4, SpigotIconBuilder.builder(Material.POTION).name(MenuText.climbSpeed(nonPlayerSheet)).potionEffect(PotionType.JUMP).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                nonPlayerSheet.setClimbSpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(5, SpigotIconBuilder.of(Material.ELYTRA, MenuText.flySpeed(nonPlayerSheet)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                nonPlayerSheet.setFlySpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(6, SpigotIconBuilder.builder(Material.POTION).name(MenuText.speed(nonPlayerSheet)).potionEffect(PotionType.SPEED).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                nonPlayerSheet.setSpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(7, SpigotIconBuilder.builder(Material.POTION).name(MenuText.swimSpeed(nonPlayerSheet)).potionEffect(PotionType.WATER_BREATHING).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                nonPlayerSheet.setSwimSpeed(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(8, SpigotIconBuilder.builder(Material.POTION).name(MenuText.CORE_STATS).potionEffect(PotionType.STRENGTH).build(), ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsGUI<>(nonPlayer, CoreStatsGUI.NPC, p)));
        updateDMOutputOnly();
        setButton(10, SpigotIconBuilder.builder(Material.POTION).name(MenuText.HIT_POINTS).potionEffect(PotionType.INSTANT_HEAL).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerHitPointsGUI(nonPlayer, p)));
        setButton(11, SpigotIconBuilder.of(Material.BOOK, MenuText.KNOWN_LANGUAGES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.KNOWN_LANGUAGES, nonPlayerSheet.getLanguages()), (ply, pages) -> {
            nonPlayerSheet.setLanguages(pages);
            new NonPlayerSheetGUI(nonPlayer, ply);
        })));
        setButton(12, SpigotIconBuilder.of(Material.BOOK, MenuText.SENSES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.SENSES, nonPlayerSheet.getSenses()), (ply, pages) -> {
            nonPlayerSheet.setSenses(pages);
            new NonPlayerSheetGUI(nonPlayer, player);
        })));
        setButton(13, SpigotIconBuilder.of(Material.LEATHER, MenuText.size(nonPlayerSheet)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setSize(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(14, SpigotIconBuilder.of(Material.VILLAGER_SPAWN_EGG, MenuText.typeRace(nonPlayerSheet)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerSheet.setTypeRace(s);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(15, SpigotIconBuilder.of(Material.EXPERIENCE_BOTTLE, MenuText.experience(nonPlayerSheet)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                nonPlayerSheet.setXP(i);
                new NonPlayerSheetGUI(nonPlayer, player);
            }
        }));
        setButton(16, SpigotIconBuilder.builder(Material.POTION).name(MenuText.initiative(nonPlayer.getInitiative(), nonPlayer.getNonPlayerSheet().getCoreStats().getDexterity())).potionEffect(PotionType.SPEED).build(), ImmutableMap.of(ClickType.LEFT, p -> new InitiativeGUI(nonPlayer, InitiativeGUI.NPC, p)));
        setButton(17, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerGUI(nonPlayer, p)));
    }

    private void updateDMOutputOnly() {
        NonPlayerSheet nonPlayerSheet = nonPlayer.getNonPlayerSheet();
        setButton(9, SpigotIconBuilder.builder(Material.DIAMOND).name(MenuText.dmOutputOnly(nonPlayerSheet)).addGlow(nonPlayerSheet.isDMOutputOnly()).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            nonPlayerSheet.setDMOutputOnly(!nonPlayerSheet.isDMOutputOnly());
            updateDMOutputOnly();
        }));
    }
}
