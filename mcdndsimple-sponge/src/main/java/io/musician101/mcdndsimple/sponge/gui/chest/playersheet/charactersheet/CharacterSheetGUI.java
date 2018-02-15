package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.armor.ArmorTabGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.clazz.ClassTabGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.corestats.CoreStatsTabGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.inventory.InventoryTabGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.skills.SkillsTabGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.SpellbookTabGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons.WeaponsTabGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class CharacterSheetGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final CharacterSheet characterSheet;

    public CharacterSheetGUI(Player player, BioAndInfo bioAndInfo, CharacterSheet characterSheet, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.CHARACTER_SHEET, 9, prevGUI);
        this.characterSheet = characterSheet;
        this.bioAndInfo = bioAndInfo;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_CHESTPLATE, Text.of(MenuText.ARMOR)), player -> new ArmorTabGUI(player, characterSheet.getArmorTab(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.BACKGROUND)), player -> new BackgroundTabGUI(player, characterSheet.getBackgroundTab(), this));
        ClassTab classTab = characterSheet.getClassTab();
        CoreStatsTab coreStatsTab = characterSheet.getCoreStatsTab();
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.CLASS)), player -> new ClassTabGUI(player, characterSheet.getClassTab(), this));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND, Text.of(MenuText.CORE_STATS)), player -> new CoreStatsTabGUI(player, bioAndInfo, classTab.getClassLevels(), coreStatsTab, this));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.CHEST, Text.of(MenuText.INVENTORY)), player -> new InventoryTabGUI(player, characterSheet.getInventoryTab(), coreStatsTab.getCoreStats(), this));
        set(5, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.SKILLS)), player -> new SkillsTabGUI(player, bioAndInfo, coreStatsTab.getBonuses().getAbilitiesAndSkills(), characterSheet.getSkillsTab(), this));
        set(6, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.SPELLBOOK)), player -> new SpellbookTabGUI(player, bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), characterSheet.getSpellbookTab(), this));
        Bonuses bonuses = coreStatsTab.getBonuses();
        set(7, ClickInventoryEvent.class, createItem(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.WEAPONS)), player -> new WeaponsTabGUI(player, characterSheet.getWeaponsTab(), bioAndInfo, classTab.getClassLevels(), coreStatsTab.getCoreStats(), coreStatsTab.getExperience(), bonuses.getMelee(), bonuses.getRanged(), this));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
