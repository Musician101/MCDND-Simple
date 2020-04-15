package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
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
import org.bukkit.inventory.ItemStack;

public class SpellDashboardGUI extends SpigotMCDNDSimpleGUI {

    public SpellDashboardGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.SPELLS_DASHBOARD, 27);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        SpellbookTab spellbookTab = characterSheet.getSpellbookTab();
        setButton(0, spellcasterIcon(mcdndPlayer, SpellcasterClass.ARCANE_TRICKSTER));
        setButton(1, spellcasterIcon(mcdndPlayer, SpellcasterClass.BARD));
        setButton(2, spellcasterIcon(mcdndPlayer, SpellcasterClass.CLERIC));
        setButton(3, spellcasterIcon(mcdndPlayer, SpellcasterClass.DRUID));
        setButton(4, spellcasterIcon(mcdndPlayer, SpellcasterClass.ELDRITCH_KNIGHT));
        setButton(7, SpigotIconBuilder.builder(Material.LINGERING_POTION).name(MenuText.SORCERY_POINTS).description(MenuText.sorceryPoints(classLevels, spellbookTab)).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                spellbookTab.setSorceryPointsUsed(i);
                new SpellDashboardGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, spellcasterIcon(mcdndPlayer, SpellcasterClass.PALADIN));
        setButton(9, spellcasterIcon(mcdndPlayer, SpellcasterClass.RANGER));
        setButton(10, spellcasterIcon(mcdndPlayer, SpellcasterClass.SORCERER));
        setButton(11, spellcasterIcon(mcdndPlayer, SpellcasterClass.WARLOCK));
        setButton(12, spellcasterIcon(mcdndPlayer, SpellcasterClass.WIZARD));
        setButton(16, SpigotIconBuilder.builder(Material.ENCHANTING_TABLE).name(MenuText.SPELL_SLOTS).description(MenuText.spellSlots(classLevels, spellbookTab)).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                spellbookTab.setWarlockSpellSlotsUsed(i);
                new SpellDashboardGUI(mcdndPlayer, player);
            }
        }));
        setButton(22, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new SpellbookTabGUI(mcdndPlayer, p)));
    }

    @Nonnull
    protected ItemStack spellcasterIcon(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull SpellcasterClass spellcasterClass) {
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        CoreStats<PlayerAbilityScore> coreStats = characterSheet.getCoreStatsTab().getCoreStats();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
        return SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(spellcasterClass.getName()).description(MenuText.spellcastingTable(classLevels, coreStats, experience, spellcasterClass)).addGlow(characterSheet.getSpellbookTab().getSpellcasterClasses(classLevels).contains(spellcasterClass)).build();
    }
}
