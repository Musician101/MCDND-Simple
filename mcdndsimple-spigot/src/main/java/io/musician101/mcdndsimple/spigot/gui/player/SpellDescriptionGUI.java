package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellDescriptionGUI extends SpigotMCDNDSimpleGUI {

    public SpellDescriptionGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.DESCRIPTION, 9);
        Spell spell = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(s -> s.getLevel() == level).collect(Collectors.toList()).get(index);
        setButton(0, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, spell.getName(), spell.getDescription()), (ply, pages) -> {
            spell.setDescription(pages);
            new SpellDescriptionGUI(mcdndPlayer, level, index, ply);
        })));
        setButton(1, SpigotIconBuilder.of(Material.BOOK, MenuText.AT_HIGHER_LEVELS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, spell.getName(), spell.getAtHigherLevels()), (ply, pages) -> {
            spell.setAtHigherLevels(pages);
            new SpellDescriptionGUI(mcdndPlayer, level, index, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }
}
