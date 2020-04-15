package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class InventoryGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public InventoryGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.INVENTORY, 54);
        this.mcdndPlayer = mcdndPlayer;
        updateSlots();
        setButton(48, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.NEW_ARMOR)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                MCDNDItem item = new MCDNDItem();
                item.setName(s);
                List<MCDNDItem> items = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory();
                items.add(item);
                new ItemGUI(mcdndPlayer, items.size() - 1, player);
            }
        }));
        setButton(50, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InventoryTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<MCDNDItem> items = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                MCDNDItem item = items.get(index);
                ItemStack itemStack = SpongeIconBuilder.builder(ItemTypes.CHEST).name(Text.of(item.getName())).description(Stream.concat(Stream.of(Text.of(TextColors.GREEN + "LEFT-CLICK" + TextColors.RESET + " to edit."), Text.of(TextColors.RED + "RIGHT-CLICK" + TextColors.RESET + " to delete.")), stringArrayToTextList(MenuText.itemDesc(item)).stream()).collect(Collectors.toList())).build();
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ItemGUI(mcdndPlayer, index, p), ClickInventoryEvent.Secondary.class, p -> {
                    items.remove(index);
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

        int maxPage = Double.valueOf(Math.ceil(items.size() / 45D)).intValue();
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
