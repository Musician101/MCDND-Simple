package io.musician101.mcdndsimple.common.character.player.tab;

import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import java.util.ArrayList;
import java.util.List;

public class InventoryTab {

    private List<MCDNDItem> inventory = new ArrayList<>();
    private List<String> inventoryNotes = new ArrayList<>();
    private Wealth wealth = new Wealth();
    private Weight weight = new Weight();

    public void addItem(MCDNDItem item) {
        inventory.add(item);
    }

    public List<MCDNDItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<MCDNDItem> inventory) {
        this.inventory = inventory;
    }

    public List<String> getInventoryNotes() {
        return inventoryNotes;
    }

    public void setInventoryNotes(List<String> inventoryNotes) {
        this.inventoryNotes = inventoryNotes;
    }

    public Wealth getWealth() {
        return wealth;
    }

    public void setWealth(Wealth wealth) {
        this.wealth = wealth;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public void removeItem(MCDNDItem item) {
        inventory.remove(item);
    }
}
