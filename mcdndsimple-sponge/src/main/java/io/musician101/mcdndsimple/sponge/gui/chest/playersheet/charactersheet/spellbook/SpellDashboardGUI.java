package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;


public class SpellDashboardGUI extends MCDNDSimpleChestGUI {

    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final SpellbookTab spellbookTab;

    public SpellDashboardGUI(Player player, ClassLevels classLevels, CoreStats coreStats, Experience experience, SpellbookTab spellbookTab, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.SPELLS_DASHBOARD, 36, prevGUI);
        this.spellbookTab = spellbookTab;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
    }

    private ItemStack arcaneTrickster() {
        SpellcasterClass sc = SpellcasterClass.ARCANE_TRICKSTER;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack bard() {
        SpellcasterClass sc = SpellcasterClass.BARD;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    @Override
    protected void build() {
        set(0, arcaneTrickster());
        set(1, bard());
        set(2, cleric());
        set(3, druid());
        set(4, eldritchKnight());
        set(7, ClickInventoryEvent.class, createItem(ItemTypes.LINGERING_POTION, Text.of(MenuText.SORCERY_POINTS), convertToText(MenuText.sorceryPoints(spellbookTab.getSorceryPointsUsed(), spellbookTab.getSorceryPointsMax(classLevels)))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellbookTab.setSorceryPointsUsed(i);
            delayedOpen();
        }));
        set(8, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.SPELL_SLOTS)), player -> new SpellSlotsGUI(player, spellbookTab.getSpellSlots(), this));
        set(9, paladin());
        set(10, ranger());
        set(11, sorcerer());
        set(12, warlock());
        set(13, wizard());
        set(16, ClickInventoryEvent.class, createItem(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.SPELL_SLOTS), convertToText(MenuText.spellSlots(spellbookTab.getWarlockSpellSlotsUsed(), getWarlockSlots(), getWarlockSpellAmount()))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellbookTab.setWarlockSpellSlotsUsed(i);
            delayedOpen();
        }));
        setBackButton(26, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }

    private ItemStack cleric() {
        SpellcasterClass sc = SpellcasterClass.CLERIC;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack druid() {
        SpellcasterClass sc = SpellcasterClass.DRUID;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack eldritchKnight() {
        SpellcasterClass sc = SpellcasterClass.ELDRITCH_KNIGHT;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private int getCantrips(SpellcasterClass spellcasterClass, ClassLevels classLevels) {
        return spellcasterClass.getCantripsAmount(classLevels.getRogue());
    }

    private int getPreparedSpells(SpellcasterClass spellcasterClass, ClassLevels classLevels, CoreStats coreStats) {
        return spellcasterClass.getPreparedSpells(coreStats, classLevels.getRogue());
    }

    private int getSaveDC(SpellcasterClass spellcasterClass, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        return spellcasterClass.getSpellSaveDC(classLevels, coreStats, experience);
    }

    private int getSpellsAmount(SpellcasterClass spellcasterClass, ClassLevels classLevels) {
        return spellcasterClass.getSpellsAmount(classLevels.getRogue());
    }

    private int getWarlockSlots() {
        SpellcasterClass sc = SpellcasterClass.WARLOCK;
        int level = classLevels.getWarlock();
        switch (level) {
            case 1:
                return sc.get1stLevelAmount(1);
            case 2:
                return sc.get1stLevelAmount(2);
            case 3:
                return sc.get2ndLevelAmount(3);
            case 4:
                return sc.get2ndLevelAmount(4);
            case 5:
                return sc.get3rdLevelAmount(5);
            case 6:
                return sc.get3rdLevelAmount(6);
            case 7:
                return sc.get4thLevelAmount(7);
            case 8:
                return sc.get4thLevelAmount(8);
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return sc.get5thLevelAmount(level);
        }

        return 0;
    }

    private int getWarlockSpellAmount() {
        int level = classLevels.getWarlock();
        if (level == 1) {
            return 1;
        }
        else if (level >= 2 && level <= 10) {
            return 2;
        }
        else if (level >= 11 && level <= 16) {
            return 3;
        }
        else if (level >= 17 && level <= 20) {
            return 4;
        }

        return 0;
    }

    private ItemStack paladin() {
        SpellcasterClass sc = SpellcasterClass.PALADIN;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack ranger() {
        SpellcasterClass sc = SpellcasterClass.RANGER;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack sorcerer() {
        SpellcasterClass sc = SpellcasterClass.SORCERER;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private String[] spellcastingTable(SpellcasterClass spellcasterClass) {
        return MenuText.spellcastingTable(getCantrips(spellcasterClass, classLevels), getSpellsAmount(spellcasterClass, classLevels), getPreparedSpells(spellcasterClass, classLevels, coreStats), getSaveDC(spellcasterClass, classLevels, coreStats, experience));
    }

    private ItemStack warlock() {
        SpellcasterClass sc = SpellcasterClass.WARLOCK;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack wizard() {
        SpellcasterClass sc = SpellcasterClass.WIZARD;
        ItemStack itemStack = createItem(ItemTypes.ENCHANTED_BOOK, Text.of(sc.getName()), convertToText(spellcastingTable(sc)));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }
}
