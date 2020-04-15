package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SpellSaveGUI extends SpongeMCDNDSimpleGUI {

    public SpellSaveGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.SAVE, 9);
        SpellSave spellSave = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellSave();
        setButton(0, SpongeIconBuilder.of(ItemTypes.SHIELD, Text.of(MenuText.SAVING_STAT)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new StatBonusGUI(mcdndPlayer, level, index, StatBonusGUI.SPELL_SAVE, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.ENCHANTING_TABLE, Text.of(MenuText.SAVE_DC)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellcasterClassGUI(mcdndPlayer, level, index, SpellcasterClassGUI.SPELL_SAVE, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of(MenuText.customDC(spellSave))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                spellSave.setCustomDC(i);
                new SpellSaveGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.ON_SUCCESSFUL_SAVE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave()), (ply, pages) -> {
            spellSave.setOnSuccessfulSave(textListToStringList(pages));
            new SpellSaveGUI(mcdndPlayer, level, index, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }
}
