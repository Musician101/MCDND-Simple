package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.character.tab.ArmorTab;
import io.musician101.mcdndsimple.sponge.character.tab.BackgroundTab;
import io.musician101.mcdndsimple.sponge.character.tab.ClassTab;
import io.musician101.mcdndsimple.sponge.character.tab.CoreStatsTab;
import io.musician101.mcdndsimple.sponge.character.tab.InventoryTab;
import io.musician101.mcdndsimple.sponge.character.tab.SkillsTab;
import io.musician101.mcdndsimple.sponge.character.tab.SpellbookTab;
import io.musician101.mcdndsimple.sponge.character.tab.WeaponsTab;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class PlayerSheet implements DataSerializable
{
    private ArmorTab armorTab = new ArmorTab();
    private BackgroundTab backgroundTab = new BackgroundTab();
    private ClassTab classTab = new ClassTab();
    private CoreStatsTab coreStatsTab = new CoreStatsTab();
    private InventoryTab inventoryTab = new InventoryTab();
    private SkillsTab skillsTab = new SkillsTab();
    private SpellbookTab spellbookTab = new SpellbookTab();
    private WeaponsTab weaponsTab = new WeaponsTab();

    public ArmorTab getArmorTab()
    {
        return armorTab;
    }

    public BackgroundTab getBackgroundTab()
    {
        return backgroundTab;
    }

    public ClassTab getClassTab()
    {
        return classTab;
    }

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.ARMOR_TAB, armorTab.toContainer())
                .set(MCDNDSimpleKeys.BACKGROUND_TAB, backgroundTab.toContainer())
                .set(MCDNDSimpleKeys.CLASS_TAB, classTab.toContainer())
                .set(MCDNDSimpleKeys.CORE_STATS_TAB, coreStatsTab.toContainer())
                .set(MCDNDSimpleKeys.INVENTORY_TAB, inventoryTab.toContainer())
                .set(MCDNDSimpleKeys.SKILLS_TAB, skillsTab.toContainer())
                .set(MCDNDSimpleKeys.SPELL_BOOK_TAB, spellbookTab.toContainer())
                .set(MCDNDSimpleKeys.WEAPONS_TAB, weaponsTab.toContainer());
    }

    public CoreStatsTab getCoreStatsTab()
    {
        return coreStatsTab;
    }

    public InventoryTab getInventoryTab()
    {
        return inventoryTab;
    }

    public SkillsTab getSkillsTab()
    {
        return skillsTab;
    }

    public SpellbookTab getSpellbookTab()
    {
        return spellbookTab;
    }

    public WeaponsTab getWeaponsTab()
    {
        return weaponsTab;
    }

    public void setArmorTab(ArmorTab armorTab)
    {
        this.armorTab = armorTab;
    }

    public void setBackgroundTab(BackgroundTab backgroundTab)
    {
        this.backgroundTab = backgroundTab;
    }

    public void setClassTab(ClassTab classTab)
    {
        this.classTab = classTab;
    }

    public void setCoreStatsTab(CoreStatsTab coreStatsTab)
    {
        this.coreStatsTab = coreStatsTab;
    }

    public void setInventoryTab(InventoryTab inventoryTab)
    {
        this.inventoryTab = inventoryTab;
    }

    public void setSkillsTab(SkillsTab skillsTab)
    {
        this.skillsTab = skillsTab;
    }

    public void setSpellbookTab(SpellbookTab spellbookTab)
    {
        this.spellbookTab = spellbookTab;
    }

    public void setWeaponsTab(WeaponsTab weaponsTab)
    {
        this.weaponsTab = weaponsTab;
    }

    public static PlayerSheet fromDataContainer(DataContainer dataContainer)
    {
        PlayerSheet playerSheet = new PlayerSheet();
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.ARMOR_TAB).ifPresent(data -> playerSheet.setArmorTab(ArmorTab.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.BACKGROUND_TAB).ifPresent(data -> playerSheet.setBackgroundTab(BackgroundTab.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.CLASS_TAB).ifPresent(data -> playerSheet.setClassTab(ClassTab.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.CORE_STATS_TAB).ifPresent(data -> playerSheet.setCoreStatsTab(CoreStatsTab.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.INVENTORY_TAB).ifPresent(data -> playerSheet.setInventoryTab(InventoryTab.fromDataContainer(data, playerSheet.getCoreStatsTab().getCoreStats().getStrength().getScore())));
        playerSheet.setSkillsTab(SkillsTab.fromCoreStats(playerSheet.getCoreStatsTab().getCoreStats()));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.SPELL_BOOK_TAB).ifPresent(data -> playerSheet.setSpellbookTab(SpellbookTab.fromDataContainer(data, playerSheet.getClassTab().getClassLevels().getSorcerer())));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.WEAPONS_TAB).ifPresent(data -> playerSheet.setWeaponsTab(WeaponsTab.fromDataContainer(data)));
        return playerSheet;
    }
}
