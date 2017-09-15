package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.HitDice;
import io.musician101.mcdndsimple.common.character.tab.CoreStatsTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats.bonuses.BonusesGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class CoreStatsTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStatsTab coreStatsTab;

    public CoreStatsTabGUI(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStatsTab coreStatsTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.CORE_STATS, prevGUI);
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStatsTab = coreStatsTab;
    }

    @Override
    protected void build() {
        CoreStats coreStats = coreStatsTab.getCoreStats();
        set(0, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.CORE_STATS), PotionType.STRENGTH), player -> new CoreStatsGUI(player, bioAndInfo, classLevels, coreStats, coreStatsTab.getExperience(), this));
        set(1, ClickType.LEFT, createItem(Material.GOLDEN_APPLE, MenuText.HIT_POINTS, MenuText.hitPoints(coreStatsTab.getHitPoints())), player -> new HitPointsGUI(player, coreStatsTab.getHitPoints(), this));
        set(2, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.speed(coreStatsTab)), PotionType.SPEED), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            coreStatsTab.setSpeed(i);
            open();
        }));
        AbilityScore dex = coreStats.getDexterity();
        set(3, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.initiative(coreStatsTab.getInitiative(), dex)), PotionType.SPEED), player -> new InitiativeGUI(player, dex, bioAndInfo, coreStatsTab.getInitiative(), this));
        Experience experience = coreStatsTab.getExperience();
        set(4, ClickType.LEFT, createItem(Material.EXP_BOTTLE, MenuText.LEVEL_AND_XP, MenuText.experience(experience, classLevels)), player -> new ExperienceGUI(player, classLevels, experience, this));
        HitDice hitDice = coreStatsTab.getHitDice();
        set(5, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.HIT_DICE, MenuText.hitDice(hitDice)), PotionType.REGEN), player -> new HitDiceGUI(player, coreStats.getConstitution(), bioAndInfo, hitDice, this));
        set(6, ClickType.LEFT, createItem(Material.ENCHANTED_BOOK, MenuText.BONUSES_PENALTIES), player -> new BonusesGUI(player, coreStatsTab.getBonuses(), this));
        set(7, ClickType.LEFT, createItem(Material.GOLD_NUGGET, MenuText.INSPIRATION), player -> {
            coreStatsTab.setInspiration(!coreStatsTab.isInspiration());
            open();
        });
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
