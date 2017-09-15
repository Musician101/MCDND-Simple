package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.inventory;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Weight;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.anvil.number.DoubleInputAnvilGUI;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;


public class WeightGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final Weight weight;

    public WeightGUI(Player player, Weight weight, CoreStats coreStats, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.WEIGHT, 9, prevGUI);
        this.weight = weight;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        set(0, createItem(ItemTypes.CHEST, Text.of(MenuText.inventoryWeight(weight.getInventory()))));
        set(1, createItem(ItemTypes.EMERALD, Text.of(MenuText.coinWeight(weight.getCoin()))));
        set(2, ClickInventoryEvent.class, createItem(ItemTypes.STICK, Text.of(MenuText.otherWeight(weight.getOther()))), player -> new DoubleInputAnvilGUI(player, (p, d) -> {
            weight.setOther(d);
            open();
        }));
        set(3, createItem(ItemTypes.STONE, Text.of(MenuText.totalWeight(weight))));
        set(4, createItem(ItemTypes.CHEST, Text.of(MenuText.carryingMax(weight.getCarryingMax()))));
        set(5, createItem(ItemTypes.PISTON, Text.of(MenuText.pushDragLift(weight.getPushDragLift()))));
        set(6, createItem(ItemTypes.STONE, Text.of(MenuText.encumbered(weight.getEncumbered(coreStats)))));
        set(7, createItem(ItemTypes.OBSIDIAN, Text.of(MenuText.heavilyEncumbered(weight.getHeavilyEncumbered(coreStats)))));
        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }
}
