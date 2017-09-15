package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.spellbook.spell;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimplePagedGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellsGUI extends MCDNDSimplePagedGUI {

    private final BioAndInfo bioAndInfo;
    private final ClassLevels classLevels;
    private final CoreStats coreStats;
    private final Experience experience;
    private final int level;
    private final List<Spell> spells;

    public SpellsGUI(@Nonnull Player player, int level, List<Spell> spells, int page, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, @Nullable AbstractSpigotChestGUI<SpigotMCDNDSimple> prevMenu) {
        super(player, 54, getName(level), page, prevMenu);
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
        setBackButton(53, ClickType.LEFT, Material.BARRIER);
    }
}
