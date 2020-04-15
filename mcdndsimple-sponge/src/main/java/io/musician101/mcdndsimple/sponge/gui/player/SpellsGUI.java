package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SpellsGUI extends SpongeMCDNDSimpleGUI {

    private final int level;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public SpellsGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, @Nonnull Player player) {
        super(player, MenuText.spellLevel(level), 54);
        this.mcdndPlayer = mcdndPlayer;
        this.level = level;
        updateSlots();
        setButton(48, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.NEW_MELEE_WEAPON)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Spell spell = new Spell();
                spell.setName(s);
                List<Spell> spells = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells();
                spells.add(spell);
                new SpellGUI(mcdndPlayer, level, (int) spells.stream().filter(sp -> sp.getLevel() == level).count() - 1, player);
            }
        }));
        setButton(50, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellBookGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<Spell> allSpells = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells();
        List<Spell> spells = allSpells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList());
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                Spell spell = spells.get(index);
                ItemStack itemStack = ItemRepresentation.spell(spell);
                List<Text> lore = Stream.concat(Stream.of(Text.of(TextColors.GREEN, "LEFT-CLICK", TextColors.RESET, " to edit."), Text.of(TextColors.RED, "RIGHT-CLICK", TextColors.RESET, " to delete.")), itemStack.get(Keys.ITEM_LORE).orElse(Collections.emptyList()).stream()).collect(Collectors.toList());
                setButton(x, SpongeIconBuilder.builder(itemStack).description(lore).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellGUI(mcdndPlayer, level, index, p), ClickInventoryEvent.Secondary.class, p -> {
                    spells.remove(index);
                    allSpells.removeIf(s -> s.getLevel() == level);
                    allSpells.addAll(spells);
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException e) {
                removeButton(x);
            }
        });

        if (page == 1) {
            removeButton(45);
        }
        else {
            setButton(45, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.PREVIOUS_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(spells.size() / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.NEXT_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
