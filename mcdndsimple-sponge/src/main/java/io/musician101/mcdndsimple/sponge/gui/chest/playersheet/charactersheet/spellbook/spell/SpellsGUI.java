package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;


public class SpellsGUI extends MCDNDSimplePagedGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final int level;
    private final List<Spell> spells;

    public SpellsGUI(@Nonnull Player player, int level, List<Spell> spells, int page, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, @Nullable AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevMenu) {
        super(player, getName(level), 54, page, prevMenu);
        this.level = level;
        this.spells = spells;
        this.bioAndInfo = bioAndInfo;
        this.classLevels = classLevels;
        this.coreStats = coreStats;
        this.experience = experience;
    }

    private static String getName(int level) {
        if (level == 0) {
            return MenuText.CANTRIPS;
        }
        else {
            return MenuText.spellLevel(level);
        }
    }

    @Override
    protected void build() {
        setContents(spells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()), ItemRepresentation::spell, (player, spell) -> p -> new SpellGUI(player, spell, bioAndInfo, classLevels, coreStats, experience, this));
        setBackButton(53, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
