package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.List;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class SpellBookGUI extends SpongeMCDNDSimpleGUI {

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
        setButton(17, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellbookTabGUI(mcdndPlayer, p)));
    }

    private void setSlot(@Nonnull MCDNDPlayer mcdndPlayer, int slot, String name) {
        List<Spell> spells = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells();
        setButton(slot, SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK).name(Text.of(name)).description(Text.of(MenuText.total(slot, spells))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellsGUI(mcdndPlayer, slot, p)));
    }
}
