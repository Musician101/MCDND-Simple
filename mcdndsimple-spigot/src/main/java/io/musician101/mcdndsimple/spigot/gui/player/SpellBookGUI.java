package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class SpellBookGUI extends SpigotMCDNDSimpleGUI {

    public SpellBookGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.SPELLS, 18);
        setSlot(mcdndPlayer, 0, MenuText.CANTRIPS);
        setSlot(mcdndPlayer, 1, MenuText.SPELL_SLOT_1);
        setSlot(mcdndPlayer, 2, MenuText.SPELL_SLOT_2);
        setSlot(mcdndPlayer, 3, MenuText.SPELL_SLOT_3);
        setSlot(mcdndPlayer, 4, MenuText.SPELL_SLOT_4);
        setSlot(mcdndPlayer, 5, MenuText.SPELL_SLOT_5);
        setSlot(mcdndPlayer, 6, MenuText.SPELL_SLOT_6);
        setSlot(mcdndPlayer, 7, MenuText.SPELL_SLOT_7);
        setSlot(mcdndPlayer, 8, MenuText.SPELL_SLOT_8);
        setSlot(mcdndPlayer, 9, MenuText.SPELL_SLOT_9);
        setButton(17, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new SpellbookTabGUI(mcdndPlayer, p)));
    }

    private void setSlot(@Nonnull MCDNDPlayer mcdndPlayer, int slot, String name) {
        List<Spell> spells = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells();
        setButton(slot, SpigotIconBuilder.builder(Material.WRITABLE_BOOK).name(name).description(MenuText.total(slot, spells)).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpellsGUI(mcdndPlayer, slot, p)));
    }
}
