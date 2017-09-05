package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.tab.SpellbookTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpellDashboardGUI extends MCDNDSimpleChestGUI {

    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final SpellbookTab spellbookTab;

    public SpellDashboardGUI(Player player, ClassLevels classLevels, CoreStats coreStats, Experience experience, SpellbookTab spellbookTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 36, MenuText.SPELLS_DASHBOARD, prevGUI);
        this.spellbookTab = spellbookTab;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
    }

    @Override
    protected void build() {
        set(0, arcaneTrickster());
        set(1, bard());
        set(2, cleric());
        set(3, druid());
        set(4, eldritchKnight());
        set(7, createItem(Material.LINGERING_POTION, MenuText.SORCERY_POINTS, MenuText.sorceryPoints(spellbookTab.getSorceryPointsUsed(), spellbookTab.getSorceryPointsMax(classLevels))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellbookTab.setSorceryPointsUsed(i);
            delayedOpen();
        }));
        set(8, createItem(Material.ENCHANTMENT_TABLE, MenuText.SPELL_SLOTS), player -> new SpellSlotsGUI(player, spellbookTab.getSpellSlots(), this));
        set(9, paladin());
        set(10, ranger());
        set(11, sorcerer());
        set(12, warlock());
        set(13, wizard());
        set(17, createItem(Material.ENCHANTMENT_TABLE, MenuText.SPELL_SLOTS, MenuText.spellSlots(spellbookTab.getWarlockSpellSlotsUsed(), getWarlockSlots(), getWarlockSpellAmount())), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            spellbookTab.setWarlockSpellSlotsUsed(i);
            delayedOpen();
        }));
        setBackButton(26, Material.BARRIER);
    }

    private int getWarlockSlots() {
        SpellcasterClass sc = SpellcasterClass.WARLOCK;
        int level = classLevels.getWarlock();
        switch(level) {
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
        else if (level >= 2 || level <= 10) {
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

    private ItemStack arcaneTrickster() {
        SpellcasterClass sc = SpellcasterClass.ARCANE_TRICKSTER;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack bard() {
        SpellcasterClass sc = SpellcasterClass.BARD;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack cleric() {
        SpellcasterClass sc = SpellcasterClass.CLERIC;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack druid() {
        SpellcasterClass sc = SpellcasterClass.DRUID;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack eldritchKnight() {
        SpellcasterClass sc = SpellcasterClass.ELDRITCH_KNIGHT;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack paladin() {
        SpellcasterClass sc = SpellcasterClass.PALADIN;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack ranger() {
        SpellcasterClass sc = SpellcasterClass.RANGER;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack sorcerer() {
        SpellcasterClass sc = SpellcasterClass.SORCERER;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack warlock() {
        SpellcasterClass sc = SpellcasterClass.WARLOCK;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private ItemStack wizard() {
        SpellcasterClass sc = SpellcasterClass.WIZARD;
        ItemStack itemStack = createItem(Material.ENCHANTED_BOOK, sc.getName(), spellcastingTable(sc));
        if (spellbookTab.getSpellcasterClasses().contains(sc)) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private int getCantrips(SpellcasterClass spellcasterClass, ClassLevels classLevels) {
        return spellcasterClass.getCantripsAmount(classLevels.getRogue());
    }

    private int getSpellsAmount(SpellcasterClass spellcasterClass, ClassLevels classLevels) {
        return spellcasterClass.getSpellsAmount(classLevels.getRogue());
    }

    private int getPreparedSpells(SpellcasterClass spellcasterClass, ClassLevels classLevels, CoreStats coreStats) {
        return spellcasterClass.getPreparedSpells(coreStats, classLevels.getRogue());
    }

    private int getSaveDC(SpellcasterClass spellcasterClass, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        return spellcasterClass.getSpellSaveDC(classLevels, coreStats, experience);
    }

    private String[] spellcastingTable(SpellcasterClass spellcasterClass) {
        return MenuText.spellcastingTable(getCantrips(spellcasterClass, classLevels), getSpellsAmount(spellcasterClass, classLevels), getPreparedSpells(spellcasterClass, classLevels, coreStats), getSaveDC(spellcasterClass, classLevels, coreStats, experience));
    }
}
