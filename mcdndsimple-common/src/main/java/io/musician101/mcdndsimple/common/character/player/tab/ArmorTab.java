package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.ARMOR_TAB, typeAdapter = ArmorTab.Serializer.class)
public class ArmorTab {

    private int armorClass = 0;
    @Nonnull
    private List<Armor> armorList = new ArrayList<>();
    @Nonnull
    private UnarmoredBonus unarmoredBonus = UnarmoredBonus.NONE;
    private int unarmoredClass = 10;

    public void addArmor(Armor armor) {
        armorList.add(armor);
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    @Nonnull
    public List<Armor> getArmorList() {
        return armorList;
    }

    @Nonnull
    public UnarmoredBonus getUnarmoredBonus() {
        return unarmoredBonus;
    }

    public void setUnarmoredBonus(@Nonnull UnarmoredBonus unarmoredBonus) {
        this.unarmoredBonus = unarmoredBonus;
    }

    public int getUnarmoredClass() {
        return unarmoredClass;
    }

    public void setUnarmoredClass(int unarmoredArmorClass) {
        this.unarmoredClass = unarmoredArmorClass;
    }

    public void removeArmor(@Nonnull Armor armor) {
        armorList.add(armor);
    }

    public void setArmor(@Nonnull List<Armor> armor) {
        this.armorList = armor;
    }

    public static class Serializer implements JsonDeserializer<ArmorTab>, JsonSerializer<ArmorTab> {

        @Override
        public ArmorTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ArmorTab armorTab = new ArmorTab();
            Keys.ARMOR_CLASS.deserializeFromParent(jsonObject, context).ifPresent(armorTab::setArmorClass);
            Keys.UNARMORED_ARMOR_CLASS.deserializeFromParent(jsonObject, context).ifPresent(armorTab::setUnarmoredClass);
            Keys.ARMOR_LIST.deserializeFromParent(jsonObject, context).ifPresent(armorTab::setArmor);
            JsonKeyProcessor.<JsonPrimitive, UnarmoredBonus>getJsonKey(Keys.UNARMORED_BONUS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(armorTab::setUnarmoredBonus));
            return armorTab;
        }

        @Override
        public JsonElement serialize(ArmorTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.ARMOR_CLASS.serialize(src.getArmorClass(), jsonObject, context);
            Keys.UNARMORED_ARMOR_CLASS.serialize(src.getUnarmoredClass(), jsonObject, context);
            Keys.ARMOR_LIST.serialize(src.getArmorList(), jsonObject, context);
            JsonKeyProcessor.<JsonPrimitive, UnarmoredBonus>getJsonKey(Keys.UNARMORED_BONUS).ifPresent(jsonKey -> jsonKey.serialize(src.getUnarmoredBonus(), jsonObject, context));
            return jsonObject;
        }
    }
}
