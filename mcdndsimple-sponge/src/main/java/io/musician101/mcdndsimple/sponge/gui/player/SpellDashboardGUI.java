package io.musician101.mcdndsimple.sponge.gui.player;

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
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SpellDashboardGUI extends SpongeMCDNDSimpleGUI {

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
        setButton(7, SpongeIconBuilder.builder(ItemTypes.LINGERING_POTION).name(Text.of(MenuText.SORCERY_POINTS)).description(stringArrayToTextList(MenuText.sorceryPoints(classLevels, spellbookTab))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
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
        setButton(16, SpongeIconBuilder.builder(ItemTypes.ENCHANTING_TABLE).name(Text.of(MenuText.SPELL_SLOTS)).description(stringArrayToTextList(MenuText.spellSlots(classLevels, spellbookTab))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                spellbookTab.setWarlockSpellSlotsUsed(i);
                new SpellDashboardGUI(mcdndPlayer, player);
            }
        }));
        setButton(22, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellbookTabGUI(mcdndPlayer, p)));
    }

    @Nonnull
    protected ItemStack spellcasterIcon(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull SpellcasterClass spellcasterClass) {
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        CoreStats<PlayerAbilityScore> coreStats = characterSheet.getCoreStatsTab().getCoreStats();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
        return SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(Text.of(characterSheet.getSpellbookTab().getSpellcasterClasses(classLevels).contains(spellcasterClass) ? TextColors.GREEN : TextColors.RED, spellcasterClass.getName())).description(stringArrayToTextList(MenuText.spellcastingTable(classLevels, coreStats, experience, spellcasterClass))).build();
    }
}
