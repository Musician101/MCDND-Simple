package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;


public class InventoryTab {

    @Nonnull
    private List<MCDNDItem> inventory = new ArrayList<>();
    @Nonnull
    private List<String> inventoryNotes = new ArrayList<>();
    @Nonnull
    private Wealth wealth = new Wealth();
    @Nonnull
    private Weight weight = new Weight();

    public void addItem(MCDNDItem item) {
        inventory.add(item);
    }

    @Nonnull
    public List<MCDNDItem> getInventory() {
        return inventory;
    }

    public void setInventory(@Nonnull List<MCDNDItem> inventory) {
        this.inventory = inventory;
    }

    @Nonnull
    public List<String> getInventoryNotes() {
        return inventoryNotes;
    }

    public void setInventoryNotes(@Nonnull List<String> inventoryNotes) {
        this.inventoryNotes = inventoryNotes;
    }

    @Nonnull
    public Wealth getWealth() {
        return wealth;
    }

    public void setWealth(@Nonnull Wealth wealth) {
        this.wealth = wealth;
    }

    @Nonnull
    public Weight getWeight() {
        return weight;
    }

    public void setWeight(@Nonnull Weight weight) {
        this.weight = weight;
    }

    public void removeItem(MCDNDItem item) {
        inventory.remove(item);
    }

    public static class Serializer extends BaseSerializer<InventoryTab> {

        @Override
        public InventoryTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            InventoryTab inventoryTab = new InventoryTab();
            inventoryTab.setInventory(deserialize(jsonObject, context, Keys.INVENTORY));
            inventoryTab.setInventoryNotes(deserialize(jsonObject, context, Keys.INVENTORY_NOTES));
            inventoryTab.setWealth(deserialize(jsonObject, context, Keys.WEALTH));;
            inventoryTab.getWeight().setOther(deserialize(jsonObject, context, Keys.WEIGHT_OTHER));
            return inventoryTab;
        }

        @Override
        public JsonElement serialize(InventoryTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.INVENTORY, src.getInventory());
            serialize(jsonObject, context, Keys.INVENTORY_NOTES, src.getInventoryNotes());
            serialize(jsonObject, context, Keys.WEALTH, src.getWealth());
            serialize(jsonObject, context, Keys.WEIGHT_OTHER, src.getWeight().getOther());
            return jsonObject;
        }
    }
}
