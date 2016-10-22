package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.character.tab.ArmorTab;
import io.musician101.mcdndsimple.sponge.character.tab.BackgroundTab;
import io.musician101.mcdndsimple.sponge.character.tab.ClassTab;
import io.musician101.mcdndsimple.sponge.character.tab.CoreStatsTab;
import io.musician101.mcdndsimple.sponge.character.tab.InventoryTab;
import io.musician101.mcdndsimple.sponge.character.tab.SkillsTab;
import io.musician101.mcdndsimple.sponge.character.tab.SpellbookTab;
import io.musician101.mcdndsimple.sponge.character.tab.WeaponsTab;

//TODO needs dataserializable
public class PlayerSheet
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
}
