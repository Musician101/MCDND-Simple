package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats.bonuses.BonusesGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.effect.potion.PotionEffectTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class CoreStatsTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStatsTab coreStatsTab;

    public CoreStatsTabGUI(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStatsTab coreStatsTab, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.CORE_STATS, 9, prevGUI);
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStatsTab = coreStatsTab;
    }

    @Override
    protected void build() {
        CoreStats coreStats = coreStatsTab.getCoreStats();
        set(0, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.CORE_STATS)), PotionEffectTypes.STRENGTH), player -> new CoreStatsGUI(player, bioAndInfo, classLevels, coreStats, coreStatsTab.getExperience(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.GOLDEN_APPLE, Text.of(MenuText.HIT_POINTS), convertToText(MenuText.hitPoints(coreStatsTab.getHitPoints()))), player -> new HitPointsGUI(player, coreStatsTab.getHitPoints(), this));
        set(2, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.speed(coreStatsTab))), PotionEffectTypes.SPEED), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            coreStatsTab.setSpeed(i);
            open();
        }));
        AbilityScore dex = coreStats.getDexterity();
        set(3, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.initiative(coreStatsTab.getInitiative(), dex))), PotionEffectTypes.SPEED), player -> new InitiativeGUI(player, dex, bioAndInfo, coreStatsTab.getInitiative(), this));
        Experience experience = coreStatsTab.getExperience();
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.EXPERIENCE_BOTTLE, Text.of(MenuText.LEVEL_AND_XP), convertToText(MenuText.experience(experience, classLevels))), player -> new ExperienceGUI(player, classLevels, experience, this));
        HitDice hitDice = coreStatsTab.getHitDice();
        set(5, ClickInventoryEvent.class, setPotionEffect(createItem(ItemTypes.POTION, Text.of(MenuText.HIT_DICE), convertToText(MenuText.hitDice(hitDice))), PotionEffectTypes.REGENERATION), player -> new HitDiceGUI(player, coreStats.getConstitution(), bioAndInfo, hitDice, this));
        set(6, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.BONUSES_PENALTIES)), player -> new BonusesGUI(player, coreStatsTab.getBonuses(), this));
        set(7, ClickInventoryEvent.class, createItem(ItemTypes.GOLD_NUGGET, Text.of(MenuText.INSPIRATION)), player -> {
            coreStatsTab.setHasInspiration(!coreStatsTab.hasInspiration());
            open();
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
