package io.musician101.mcdndsimple.spigot.gui;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.nonplayer.NonPlayerAbilityScoreGUI;
import io.musician101.mcdndsimple.spigot.gui.nonplayer.NonPlayerSheetGUI;
import io.musician101.mcdndsimple.spigot.gui.player.PlayerAbilityScoreGUI;
import io.musician101.mcdndsimple.spigot.gui.player.corestats.CoreStatsTabGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.function.BiConsumer;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class CoreStatsGUI<P extends AbstractPlayer> extends SpigotMCDNDSimpleGUI {

    public static final CoreStatsBridger<MCDNDPlayer> PLAYER = (mcdndPlayer, gui) -> {
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        CoreStats<PlayerAbilityScore> coreStats = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getCoreStats();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
        PlayerAbilityScore str = coreStats.getStrength();
        PlayerAbilityScore dex = coreStats.getDexterity();
        PlayerAbilityScore con = coreStats.getConstitution();
        PlayerAbilityScore intel = coreStats.getIntelligence();
        PlayerAbilityScore wis = coreStats.getWisdom();
        PlayerAbilityScore cha = coreStats.getCharisma();
        gui.setButton(0, SpigotIconBuilder.builder(Material.IRON_SWORD).name(str.getName()).description(MenuText.scoreLore(str, classLevels, experience)).build(), ImmutableMap.of(ClickType.LEFT, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.STR, p)));
        gui.setButton(1, SpigotIconBuilder.builder(Material.BOW).name(dex.getName()).description(MenuText.scoreLore(dex, classLevels, experience)).build(), ImmutableMap.of(ClickType.LEFT, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.DEX, p)));
        gui.setButton(2, SpigotIconBuilder.builder(Material.GOLDEN_APPLE).name(con.getName()).description(MenuText.scoreLore(con, classLevels, experience)).build(), ImmutableMap.of(ClickType.LEFT, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.CON, p)));
        gui.setButton(3, SpigotIconBuilder.builder(Material.WRITABLE_BOOK).name(intel.getName()).description(MenuText.scoreLore(intel, classLevels, experience)).build(), ImmutableMap.of(ClickType.LEFT, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.INTEL, p)));
        gui.setButton(4, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(wis.getName()).description(MenuText.scoreLore(wis, classLevels, experience)).build(), ImmutableMap.of(ClickType.LEFT, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.WIS, p)));
        gui.setButton(5, SpigotIconBuilder.builder(Material.PLAYER_HEAD).name(cha.getName()).description(MenuText.scoreLore(cha, classLevels, experience)).build(), ImmutableMap.of(ClickType.LEFT, p -> new PlayerAbilityScoreGUI(mcdndPlayer, PlayerAbilityScoreGUI.CHA, p)));
        gui.setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    };
    public static final CoreStatsBridger<NonPlayer> NPC = (nonPlayer, gui) -> {
        CoreStats<NonPlayerAbilityScore> coreStats = nonPlayer.getNonPlayerSheet().getCoreStats();
        NonPlayerAbilityScore str = coreStats.getStrength();
        NonPlayerAbilityScore dex = coreStats.getDexterity();
        NonPlayerAbilityScore con = coreStats.getConstitution();
        NonPlayerAbilityScore intel = coreStats.getIntelligence();
        NonPlayerAbilityScore wis = coreStats.getWisdom();
        NonPlayerAbilityScore cha = coreStats.getCharisma();
        gui.setButton(0, SpigotIconBuilder.builder(Material.IRON_SWORD).name(str.getName()).description(MenuText.scoreLore(str)).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.STR, p)));
        gui.setButton(1, SpigotIconBuilder.builder(Material.BOW).name(dex.getName()).description(MenuText.scoreLore(dex)).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.DEX, p)));
        gui.setButton(2, SpigotIconBuilder.builder(Material.GOLDEN_APPLE).name(con.getName()).description(MenuText.scoreLore(con)).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.CON, p)));
        gui.setButton(3, SpigotIconBuilder.builder(Material.WRITABLE_BOOK).name(intel.getName()).description(MenuText.scoreLore(intel)).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.INTEL, p)));
        gui.setButton(4, SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(wis.getName()).description(MenuText.scoreLore(wis)).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.WIS, p)));
        gui.setButton(5, SpigotIconBuilder.builder(Material.PLAYER_HEAD).name(cha.getName()).description(MenuText.scoreLore(cha)).build(), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerAbilityScoreGUI(nonPlayer, NonPlayerAbilityScoreGUI.CHA, p)));
        gui.setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerSheetGUI(nonPlayer, p)));
    };

    public CoreStatsGUI(@Nonnull P abstractPlayer, @Nonnull CoreStatsBridger<P> bridger, @Nonnull Player player) {
        super(player, MenuText.CORE_STATS, 9);
        bridger.accept(abstractPlayer, this);
    }

    private interface CoreStatsBridger<P extends AbstractPlayer> extends BiConsumer<P, CoreStatsGUI<P>> {

    }
}
