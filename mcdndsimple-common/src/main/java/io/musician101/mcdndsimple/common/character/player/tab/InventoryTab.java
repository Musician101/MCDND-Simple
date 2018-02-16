package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.INVENTORY_TAB, typeAdapter = InventoryTab.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<InventoryTab>, JsonSerializer<InventoryTab> {

        @Override
        public InventoryTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            InventoryTab inventoryTab = new InventoryTab();
            Keys.INVENTORY.deserializeFromParent(jsonObject, context).ifPresent(inventoryTab::setInventory);
            Keys.INVENTORY_NOTES.deserializeFromParent(jsonObject, context).ifPresent(inventoryTab::setInventoryNotes);
            JsonKeyProcessor.<JsonObject, Wealth>getJsonKey(Keys.WEALTH).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(inventoryTab::setWealth));
            Keys.WEIGHT_OTHER.deserializeFromParent(jsonObject, context).ifPresent(other -> inventoryTab.getWeight().setOther(other));
            return inventoryTab;
        }

        @Override
        public JsonElement serialize(InventoryTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.INVENTORY.serialize(src.getInventory(), jsonObject, context);
            Keys.INVENTORY_NOTES.serialize(src.getInventoryNotes(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, Wealth>getJsonKey(Keys.WEALTH).ifPresent(jsonKey -> jsonKey.serialize(src.getWealth(), jsonObject, context));
            Keys.WEIGHT_OTHER.serialize(src.getWeight().getOther(), jsonObject, context);
            return jsonObject;
        }
    }
}
