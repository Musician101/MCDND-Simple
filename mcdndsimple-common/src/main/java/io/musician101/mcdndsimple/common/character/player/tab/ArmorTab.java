package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;


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

    public static class Serializer extends BaseSerializer<ArmorTab> {

        @Override
        public ArmorTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            ArmorTab armorTab = new ArmorTab();
            armorTab.setArmorClass(deserialize(jsonObject, context, Keys.ARMOR_CLASS));
            armorTab.setUnarmoredClass(deserialize(jsonObject, context, Keys.UNARMORED_ARMOR_CLASS));
            armorTab.setArmor(deserialize(jsonObject, context, Keys.ARMOR_LIST));
            armorTab.setUnarmoredBonus(deserialize(jsonObject, context, Keys.UNARMORED_BONUS));;
            return armorTab;
        }

        @Override
        public JsonElement serialize(ArmorTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.ARMOR_CLASS, src.getArmorClass());
            serialize(jsonObject, context, Keys.UNARMORED_ARMOR_CLASS, src.getUnarmoredClass());
            serialize(jsonObject, context, Keys.ARMOR_LIST, src.getArmorList());
            serialize(jsonObject, context, Keys.UNARMORED_BONUS, src.getUnarmoredBonus());
            return jsonObject;
        }
    }
}
