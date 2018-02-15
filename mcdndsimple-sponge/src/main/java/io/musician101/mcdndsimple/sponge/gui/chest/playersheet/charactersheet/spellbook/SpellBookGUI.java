package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell.SpellsGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class SpellBookGUI extends MCDNDSimpleChestGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final List<Spell> spells;

    public SpellBookGUI(@Nonnull Player player, List<Spell> spells, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, @Nullable AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevMenu) {
        super(player, MenuText.SPELLS, 18, prevMenu);
        this.spells = spells;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.CANTRIPS), Text.of(MenuText.total(getSpellAmountByLevel(0)))), player -> new SpellsGUI(player, 0, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_1), Text.of(MenuText.total(getSpellAmountByLevel(1)))), player -> new SpellsGUI(player, 1, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_2), Text.of(MenuText.total(getSpellAmountByLevel(2)))), player -> new SpellsGUI(player, 2, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_3), Text.of(MenuText.total(getSpellAmountByLevel(3)))), player -> new SpellsGUI(player, 3, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(4, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_4), Text.of(MenuText.total(getSpellAmountByLevel(4)))), player -> new SpellsGUI(player, 4, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(5, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_5), Text.of(MenuText.total(getSpellAmountByLevel(5)))), player -> new SpellsGUI(player, 5, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(6, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_6), Text.of(MenuText.total(getSpellAmountByLevel(6)))), player -> new SpellsGUI(player, 6, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(7, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_7), Text.of(MenuText.total(getSpellAmountByLevel(7)))), player -> new SpellsGUI(player, 7, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(8, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_8), Text.of(MenuText.total(getSpellAmountByLevel(8)))), player -> new SpellsGUI(player, 8, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        set(9, ClickInventoryEvent.class, createItem(ItemTypes.WRITABLE_BOOK, Text.of(MenuText.SPELL_SLOT_9), Text.of(MenuText.total(getSpellAmountByLevel(9)))), player -> new SpellsGUI(player, 9, spells, 0, bioAndInfo, classLevels, coreStats, experience, this));
        setBackButton(17, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }

    private int getSpellAmountByLevel(int level) {
        return spells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).size();
    }
}
