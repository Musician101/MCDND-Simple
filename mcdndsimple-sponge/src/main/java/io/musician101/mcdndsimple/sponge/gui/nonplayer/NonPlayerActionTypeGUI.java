package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActionType;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class NonPlayerActionTypeGUI extends SpongeMCDNDSimpleGUI {

    private final int index;
    @Nonnull
    private final NonPlayer nonPlayer;
    private int page = 1;

    public NonPlayerActionTypeGUI(@Nonnull NonPlayer nonPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.ACTION_TYPE, 54);
        this.nonPlayer = nonPlayer;
        this.index = index;
        updateSlots();
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerActionGUI(nonPlayer, index, p)));
    }

    private void updateSlots() {
        NonPlayerAction nonPlayerAction = nonPlayer.getNonPlayerActions().getActions().get(index);
        IntStream.of(0, 45).forEach(x -> {
            try {
                NonPlayerActionType nonPlayerActionType = NonPlayerActionType.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpongeIconBuilder.of(ItemTypes.ENCHANTED_BOOK, Text.of((nonPlayerActionType == nonPlayerAction.getActionType() ? TextColors.GREEN : TextColors.RED), nonPlayerActionType.getName()));
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> updateSlots()));
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

        int maxPage = Double.valueOf(Math.ceil(NonPlayerActionType.values().length / 45D)).intValue();
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
