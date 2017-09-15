package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.tab.SpellbookTab;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellbookTabGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final SpellbookTab spellbookTab;

    public SpellbookTabGUI(Player player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, SpellbookTab spellbookTab, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.SPELLBOOK, prevGUI);
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
        this.spellbookTab = spellbookTab;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.SPELL_DASHBOARD), player -> new SpellDashboardGUI(player, classLevels, coreStats, experience, spellbookTab, this));
        set(1, ClickType.LEFT, createItem(Material.ENCHANTED_BOOK, MenuText.SPELLS), player -> new SpellBookGUI(player, spellbookTab.getSpells(), bioAndInfo, classLevels, coreStats, experience, this));
        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
