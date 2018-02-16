package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

@TypeOf(MCDNDItem.Serializer.class)
public class MCDNDItem {

    private boolean carried = true;
    @Nonnull
    private List<String> description = new ArrayList<>();
    @Nonnull
    private String name = "";
    private int quantity = 1;
    private double weight = 0.0;

    @Nonnull
    public List<String> getDescription() {
        return description;
    }

    public void setDescription(@Nonnull List<String> description) {
        this.description = description;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isCarried() {
        return carried;
    }

    public void setIsCarried(boolean carried) {
        this.carried = carried;
    }

    public static class Serializer implements JsonDeserializer<MCDNDItem>, JsonSerializer<MCDNDItem> {

        @Override
        public MCDNDItem deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MCDNDItem item = new MCDNDItem();
            Keys.IS_CARRIED.deserializeFromParent(jsonObject, context).ifPresent(item::setIsCarried);
            Keys.WEIGHT_DOUBLE.deserializeFromParent(jsonObject, context).ifPresent(item::setWeight);
            Keys.QUANTITY.deserializeFromParent(jsonObject, context).ifPresent(item::setQuantity);
            Keys.DESCRIPTION.deserializeFromParent(jsonObject, context).ifPresent(item::setDescription);
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(item::setName);
            return item;
        }

        @Override
        public JsonElement serialize(MCDNDItem src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.IS_CARRIED.serialize(src.isCarried(), jsonObject, context);
            Keys.WEIGHT_DOUBLE.serialize(src.getWeight(), jsonObject, context);
            Keys.QUANTITY.serialize(src.getQuantity(), jsonObject, context);
            Keys.DESCRIPTION.serialize(src.getDescription(), jsonObject, context);
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            return jsonObject;
        }
    }
}
