package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.DoubleInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.IntegerInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.List;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

public class ItemGUI extends MCDNDSimpleChestGUI {

    private final MCDNDItem item;
    private final List<MCDNDItem> items;

    public ItemGUI(Player player, MCDNDItem item, List<MCDNDItem> items, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, item.getName(), 9, prevGUI);
        this.item = item;
        this.items = items;
    }

    @Override
    protected void build() {
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.PAPER, Text.of(item.getName())), player -> new StringInputAnvilGUI(player, (p, s) -> {
            item.setName(s);
            delayedOpen();
        }));

        boolean carried = item.isCarried();
        set(1, ClickInventoryEvent.class, addGlowIfConditionsMet(createItem(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.carried(item))), () -> carried), player -> {
            item.setIsCarried(!carried);
            open();
        });

        set(2, ClickInventoryEvent.class, createItem(ItemTypes.CHEST, Text.of(MenuText.quantity(item))), player -> new IntegerInputAnvilGUI(player, (p, i) -> {
            item.setQuantity(i);
            delayedOpen();
        }));

        set(3, ClickInventoryEvent.class, createItem(ItemTypes.OBSIDIAN, Text.of(MenuText.weight(item))), player -> new DoubleInputAnvilGUI(player, (p, d) -> {
            item.setWeight(d);
            delayedOpen();
        }));

        set(4, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.DESCRIPTION)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.ITEM_LORE, item.getDescription().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                item.setDescription(pages.stream().map(TextSerializers.PLAIN::serialize).collect(Collectors.toList()));
                open();
            });
        });

        set(5, ClickInventoryEvent.class, createItem(ItemTypes.ENDER_CHEST, Text.of(MenuText.DELETE)), player -> {
            items.remove(item);
            closeGUI();
            player.sendMessage(Text.of(TextColors.GREEN, Messages.ITEM_DELETED));
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
