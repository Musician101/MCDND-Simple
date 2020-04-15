package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class SpellDescriptionGUI extends SpongeMCDNDSimpleGUI {

    public SpellDescriptionGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.DESCRIPTION, 9);
        Spell spell = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(s -> s.getLevel() == level).collect(Collectors.toList()).get(index);
        setButton(0, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.DESCRIPTION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, spell.getName(), spell.getDescription()), (ply, pages) -> {
            spell.setDescription(textListToStringList(pages));
            new SpellDescriptionGUI(mcdndPlayer, level, index, ply);
        })));
        setButton(1, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.AT_HIGHER_LEVELS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, spell.getName(), spell.getAtHigherLevels()), (ply, pages) -> {
            spell.setAtHigherLevels(textListToStringList(pages));
            new SpellDescriptionGUI(mcdndPlayer, level, index, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }
}
