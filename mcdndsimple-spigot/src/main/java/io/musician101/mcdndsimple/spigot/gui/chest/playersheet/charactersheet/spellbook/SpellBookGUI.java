package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell.SpellsGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellBookGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final List<Spell> spells;

    public SpellBookGUI(@Nonnull Player player, List<Spell> spells, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, @Nullable AbstractSpigotChestGUI<SpigotMCDNDSimple> prevMenu) {
        super(player, 18, MenuText.SPELLS, prevMenu);
        this.spells = spells;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(0))), player -> new SpellsGUI(player, 0, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(1))), player -> new SpellsGUI(player, 1, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(2))), player -> new SpellsGUI(player, 2, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(3))), player -> new SpellsGUI(player, 3, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(4))), player -> new SpellsGUI(player, 4, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(5))), player -> new SpellsGUI(player, 5, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(6))), player -> new SpellsGUI(player, 6, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(7))), player -> new SpellsGUI(player, 7, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(8))), player -> new SpellsGUI(player, 8, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(0, ClickType.LEFT, createItem(Material.BOOK_AND_QUILL, MenuText.CANTRIPS, MenuText.total(getSpellAmountByLevel(9))), player -> new SpellsGUI(player, 9, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        setBackButton(17, ClickType.LEFT, Material.BARRIER);
    }

    private int getSpellAmountByLevel(int level) {
        return spells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).size();
    }
}
