package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.CharacterSheet;
import io.musician101.mcdndsimple.common.character.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.tab.CoreStatsTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.armor.ArmorTabGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.clazz.ClassTabGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats.CoreStatsTabGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.inventory.InventoryTabGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.skills.SkillsTabGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.SpellbookTabGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons.WeaponsTabGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CharacterSheetGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final CharacterSheet characterSheet;

    public CharacterSheetGUI(Player player, BioAndInfo bioAndInfo, CharacterSheet characterSheet, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.CHARACTER_SHEET, prevGUI);
        this.characterSheet = characterSheet;
        this.bioAndInfo = bioAndInfo;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.DIAMOND_CHESTPLATE, MenuText.ARMOR), player -> new ArmorTabGUI(player, characterSheet.getArmorTab(), this));
        set(1, createItem(Material.BOOK, MenuText.BACKGROUND), player -> new BackgroundTabGUI(player, characterSheet.getBackgroundTab(), this));
        ClassTab classTab = characterSheet.getClassTab();
        CoreStatsTab coreStatsTab = characterSheet.getCoreStatsTab();
        set(2, createItem(Material.ENCHANTED_BOOK, MenuText.CLASS), player -> new ClassTabGUI(player, characterSheet.getClassTab(), this));
        set(3, createItem(Material.DIAMOND, MenuText.CORE_STATS), player -> new CoreStatsTabGUI(player, bioAndInfo, classTab.getClassLevels(), coreStatsTab, this));
        set(4, createItem(Material.CHEST, MenuText.INVENTORY), player -> new InventoryTabGUI(player, characterSheet.getInventoryTab(), coreStatsTab.getCoreStats(), this));
        set(5, createItem(Material.ENCHANTED_BOOK, MenuText.SKILLS), player -> new SkillsTabGUI(player, bioAndInfo, coreStatsTab.getBonuses().getAbilitiesAndSkills(), characterSheet.getSkillsTab(), this));
        set(6, createItem(Material.ENCHANTMENT_TABLE, MenuText.SPELLBOOK), player -> new SpellbookTabGUI(player, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), characterSheet.getSpellbookTab(), this));
        set(7, createItem(Material.DIAMOND_SWORD, MenuText.WEAPONS), player -> new WeaponsTabGUI(player, characterSheet.getWeaponsTab(), bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), coreStatsTab.getBonuses().getMelee(), this));
        setBackButton(8, Material.BARRIER);
    }
}
