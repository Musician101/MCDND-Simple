package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

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

    public static class Serializer extends BaseSerializer<MCDNDItem> {

        @Override
        public MCDNDItem deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            MCDNDItem item = new MCDNDItem();
            item.setIsCarried(deserialize(jsonObject, context, Keys.IS_CARRIED));;
            item.setWeight(deserialize(jsonObject, context, Keys.WEIGHT_DOUBLE));;
            item.setQuantity(deserialize(jsonObject, context, Keys.QUANTITY));;
            item.setDescription(deserialize(jsonObject, context, Keys.DESCRIPTION));;
            item.setName(deserialize(jsonObject, context, Keys.NAME));;
            return item;
        }

        @Override
        public JsonElement serialize(MCDNDItem src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.IS_CARRIED, src.isCarried());
            serialize(jsonObject, context, Keys.WEIGHT_DOUBLE, src.getWeight());
            serialize(jsonObject, context, Keys.QUANTITY, src.getQuantity());
            serialize(jsonObject, context, Keys.DESCRIPTION, src.getDescription());
            serialize(jsonObject, context, Keys.NAME, src.getName());
            return jsonObject;
        }
    }
}
