package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.MCDNDItem;
import io.musician101.mcdndsimple.common.character.Weight;
import io.musician101.mcdndsimple.common.character.equipment.currency.Wealth;

import java.util.ArrayList;
import java.util.List;

public class InventoryTab
{
    private List<MCDNDItem> inventory = new ArrayList<>();
    private List<String> inventoryNotes = new ArrayList<>();
    private Wealth wealth = new Wealth();
    private Weight weight = new Weight();


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
}
