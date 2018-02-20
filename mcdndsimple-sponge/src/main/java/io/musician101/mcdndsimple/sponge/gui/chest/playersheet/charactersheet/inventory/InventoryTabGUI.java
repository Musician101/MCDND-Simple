package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeMusicianLibrary;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

public class InventoryTabGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final InventoryTab inventoryTab;

    public InventoryTabGUI(Player player, InventoryTab inventoryTab, CoreStats coreStats, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.INVENTORY, 9, prevGUI);
        this.inventoryTab = inventoryTab;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        Wealth wealth = inventoryTab.getWealth();
        set(0, ClickInventoryEvent.class, createItem(ItemTypes.EMERALD, Text.of(MenuText.COIN_CARRIED), convertToText(MenuText.coinCarriedDescription(wealth))), player -> new WealthGUI(player, inventoryTab.getWealth(), this));
        set(1, ClickInventoryEvent.class, createItem(ItemTypes.STONE, Text.of(MenuText.WEIGHT)), player -> new WeightGUI(player, inventoryTab.getWeight(), coreStats, this));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.CHEST, Text.of(MenuText.INVENTORY)), player -> new InventoryGUI(player, inventoryTab.getInventory(), 0, this));
        set(3, ClickInventoryEvent.class, createItem(ItemTypes.BOOK, Text.of(MenuText.INVENTORY_NOTES)), player -> {
            SpongeMusicianLibrary.instance().getSpongeBookGUIManager().addPlayer(player, ItemStack.builder().itemType(ItemTypes.WRITABLE_BOOK).add(Keys.ITEM_LORE, inventoryTab.getInventoryNotes().stream().map(Text::of).collect(Collectors.toList())).build(), pages -> {
                inventoryTab.setInventoryNotes(pages.stream().map(TextSerializers.PLAIN::serialize).collect(Collectors.toList()));
                open();
            });
        });
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
