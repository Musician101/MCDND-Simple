package io.musician101.mcdndsimple.sponge.gui.player;

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
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.InitiativeGUI;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CoreStatsTabGUI extends SpongeMCDNDSimpleGUI {

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
        setButton(0, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.CORE_STATS)).potionEffect(PotionEffectTypes.STRENGTH).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new PlayerCoreStatsGUI(mcdndPlayer, p)));
        setButton(1, SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE).name(Text.of(MenuText.HIT_POINTS)).description(stringArrayToTextList(MenuText.hitPoints(hitPoints))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new HitPointsGUI(mcdndPlayer, p)));
        setButton(2, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.speed(coreStatsTab))).potionEffect(PotionEffectTypes.SPEED).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                coreStatsTab.setSpeed(i);
                new CoreStatsTabGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.initiative(initiative, dex))).potionEffect(PotionEffectTypes.SPEED).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InitiativeGUI(mcdndPlayer, InitiativeGUI.PLAYER, p)));
        setButton(4, SpongeIconBuilder.builder(ItemTypes.EXPERIENCE_BOTTLE).name(Text.of(MenuText.LEVEL_AND_XP)).description(stringArrayToTextList(MenuText.experience(experience, classLevels))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ExperienceGUI(mcdndPlayer, p)));
        setButton(5, SpongeIconBuilder.builder(ItemTypes.POTION).name(Text.of(MenuText.HIT_DICE)).potionEffect(PotionEffectTypes.REGENERATION).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new HitDiceGUI(mcdndPlayer, p)));
        setButton(6, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.BONUSES_PENALTIES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new BonusesGUI(mcdndPlayer, p)));
        updateHasInspiration();
        setButton(8, SpongeIconBuilder.of(ItemTypes.SKULL, Text.of(MenuText.DEATH_SAVING_THROWS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new DeathSavingThrowsGUI(mcdndPlayer, p)));
        setButton(17, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }

    private void updateHasInspiration() {
        setButton(7, SpongeIconBuilder.of(ItemTypes.GOLD_NUGGET, Text.of(MenuText.INSPIRATION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            coreStatsTab.setHasInspiration(!coreStatsTab.hasInspiration());
            updateHasInspiration();
        }));
    }
}
