package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;

public class CharacterSheet {

    private ArmorTab armorTab = new ArmorTab();
    private BackgroundTab backgroundTab = new BackgroundTab();
    private ClassTab classTab = new ClassTab();
    private CoreStatsTab coreStatsTab = new CoreStatsTab();
    private InventoryTab inventoryTab = new InventoryTab();
    private SkillsTab skillsTab = new SkillsTab();
    private SpellbookTab spellbookTab = new SpellbookTab();
    private WeaponsTab weaponsTab = new WeaponsTab();

    public ArmorTab getArmorTab() {
        return armorTab;
    }

    public void setArmorTab(ArmorTab armorTab) {
        this.armorTab = armorTab;
    }

    public BackgroundTab getBackgroundTab() {
        return backgroundTab;
    }

    public void setBackgroundTab(BackgroundTab backgroundTab) {
        this.backgroundTab = backgroundTab;
    }

    public ClassTab getClassTab() {
        return classTab;
    }

    public void setClassTab(ClassTab classTab) {
        this.classTab = classTab;
    }

    public CoreStatsTab getCoreStatsTab() {
        return coreStatsTab;
    }

    public void setCoreStatsTab(CoreStatsTab coreStatsTab) {
        this.coreStatsTab = coreStatsTab;
    }

    public InventoryTab getInventoryTab() {
        return inventoryTab;
    }

    public void setInventoryTab(InventoryTab inventoryTab) {
        this.inventoryTab = inventoryTab;
    }

    public SkillsTab getSkillsTab() {
        return skillsTab;
    }

    public void setSkillsTab(SkillsTab skillsTab) {
        this.skillsTab = skillsTab;
    }

    public SpellbookTab getSpellbookTab() {
        return spellbookTab;
    }

    public void setSpellbookTab(SpellbookTab spellbookTab) {
        this.spellbookTab = spellbookTab;
    }

    public WeaponsTab getWeaponsTab() {
        return weaponsTab;
    }

    public void setWeaponsTab(WeaponsTab weaponsTab) {
        this.weaponsTab = weaponsTab;
    }
}
