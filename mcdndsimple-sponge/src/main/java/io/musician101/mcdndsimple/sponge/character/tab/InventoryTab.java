package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.character.MCDNDItem;
import io.musician101.mcdndsimple.sponge.character.Weight;
import io.musician101.mcdndsimple.sponge.character.equipment.currency.Wealth;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class InventoryTab implements DataSerializable
{
    private List<MCDNDItem> inventory = new ArrayList<>();
    private List<String> inventoryNotes = new ArrayList<>();
    private Wealth wealth = new Wealth();
    private Weight weight = new Weight();

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.INVENTORY, DataUtils.serialize(inventory))
                .set(MCDNDSimpleKeys.INVENTORY_NOTES, inventoryNotes)
                .set(MCDNDSimpleKeys.WEALTH, wealth.toContainer())
                .set(MCDNDSimpleKeys.WEIGHT_CLASS, weight.toContainer());
    }


    public List<MCDNDItem> getInventory()
    {
        return inventory;
    }

    public List<String> getInventoryNotes()
    {
        return inventoryNotes;
    }

    public Wealth getWealth()
    {
        return wealth;
    }

    public void addItem(MCDNDItem item)
    {
        inventory.add(item);
    }

    public Weight getWeight()
    {
        return weight;
    }

    public void removeItem(MCDNDItem item)
    {
        inventory.remove(item);
    }

    public void setInventory(List<MCDNDItem> inventory)
    {
        this.inventory = inventory;
    }

    public void setInventoryNotes(List<String> inventoryNotes)
    {
        this.inventoryNotes = inventoryNotes;
    }

    public void setWealth(Wealth wealth)
    {
        this.wealth = wealth;
    }

    public void setWeight(Weight weight)
    {
        this.weight = weight;
    }

    public static InventoryTab fromDataContainer(DataContainer dataContainer, int strengthScore)
    {
        InventoryTab inventoryTab = new InventoryTab();
        DataUtils.getDataContainerList(dataContainer, MCDNDSimpleKeys.INVENTORY).ifPresent(list -> list.forEach(data -> inventoryTab.addItem(MCDNDItem.fromDataContainer(data))));
        dataContainer.getStringList(MCDNDSimpleKeys.INVENTORY_NOTES.getQuery()).ifPresent(inventoryTab::setInventoryNotes);
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.WEALTH).ifPresent(data -> inventoryTab.setWealth(Wealth.fromDataContainer(data)));
        DataUtils.getDataContainer(dataContainer, MCDNDSimpleKeys.WEIGHT_CLASS).ifPresent(data -> inventoryTab.setWeight(Weight.fromDataContainer(data, strengthScore, inventoryTab.getInventory(), inventoryTab.getWealth())));
        return inventoryTab;
    }
}
