package io.musician101.mcdndsimple.spigot.gui.player.corestats;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.CoreStatsGUI;
import io.musician101.mcdndsimple.spigot.gui.InitiativeGUI;
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

public class CoreStatsTabGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final CoreStatsTab coreStatsTab;

    public CoreStatsTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.CORE_STATS, 18);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        this.coreStatsTab = characterSheet.getCoreStatsTab();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        CoreStats<PlayerAbilityScore> coreStats = coreStatsTab.getCoreStats();
        PlayerAbilityScore dex = coreStats.getDexterity();
        Experience experience = coreStatsTab.getExperience();
        HitPoints hitPoints = coreStatsTab.getHitPoints();
        Initiative initiative = coreStatsTab.getInitiative();
        setButton(0, SpigotIconBuilder.builder(Material.POTION).name(MenuText.CORE_STATS).potionEffect(PotionType.STRENGTH).build(), ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsGUI<>(mcdndPlayer, CoreStatsGUI.PLAYER, p)));
        setButton(1, SpigotIconBuilder.builder(Material.GOLDEN_APPLE).name(MenuText.HIT_POINTS).description(MenuText.hitPoints(hitPoints)).build(), ImmutableMap.of(ClickType.LEFT, p -> new HitPointsGUI(mcdndPlayer, p)));
        setButton(2, SpigotIconBuilder.builder(Material.POTION).name(MenuText.speed(coreStatsTab)).potionEffect(PotionType.SPEED).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                coreStatsTab.setSpeed(i);
                new CoreStatsTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.builder(Material.POTION).name(MenuText.initiative(initiative, dex)).potionEffect(PotionType.SPEED).build(), ImmutableMap.of(ClickType.LEFT, p -> new InitiativeGUI(mcdndPlayer, InitiativeGUI.PLAYER, p)));
        setButton(4, SpigotIconBuilder.builder(Material.EXPERIENCE_BOTTLE).name(MenuText.LEVEL_AND_XP).description(MenuText.experience(experience, classLevels)).build(), ImmutableMap.of(ClickType.LEFT, p -> new ExperienceGUI(mcdndPlayer, p)));
        setButton(5, SpigotIconBuilder.builder(Material.POTION).name(MenuText.HIT_DICE).potionEffect(PotionType.REGEN).build(), ImmutableMap.of(ClickType.LEFT, p -> new HitDiceGUI(mcdndPlayer, p)));
        setButton(6, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.BONUSES_PENALTIES), ImmutableMap.of(ClickType.LEFT, p -> new BonusesGUI(mcdndPlayer, p)));
        updateHasInspiration();
        setButton(8, SpigotIconBuilder.of(Material.PLAYER_HEAD, MenuText.DEATH_SAVING_THROWS), ImmutableMap.of(ClickType.LEFT, p -> new DeathSavingThrowsGUI(mcdndPlayer, p)));
        setButton(17, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }

    private void updateHasInspiration() {
        setButton(7, SpigotIconBuilder.of(Material.GOLD_NUGGET, MenuText.INSPIRATION), ImmutableMap.of(ClickType.LEFT, p -> {
            coreStatsTab.setHasInspiration(!coreStatsTab.hasInspiration());
            updateHasInspiration();
        }));
    }
}
