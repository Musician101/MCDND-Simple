package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellType;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SpellTypeGUI extends SpongeMCDNDSimpleGUI {

    private final int index;
    private final int level;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public SpellTypeGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.SCHOOL, 54);
        this.mcdndPlayer = mcdndPlayer;
        this.level = level;
        this.index = index;
        updateSlots();
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }

    private void updateSlots() {
        Spell spell = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(s -> s.getLevel() == level).collect(Collectors.toList()).get(index);
        IntStream.of(0, 45).forEach(x -> {
            try {
                SpellType spellType = SpellType.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpongeIconBuilder.builder(ItemTypes.END_CRYSTAL).name(Text.of(spell.getSpellType() == spellType ? TextColors.GREEN : TextColors.RED, spellType.getName())).addGlow().build();
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                    spell.setSpellType(spellType);
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

        int maxPage = Double.valueOf(Math.ceil(SpellType.values().length / 45D)).intValue();
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
