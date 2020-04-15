package io.musician101.mcdndsimple.common.character.player.equipment.armor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class Armor {

    @Nonnull
    private ArmorType armorType = ArmorType.NONE;
    private int baseArmorClass = 0;
    private int magicBonus = 0;
    @Nonnull
    private String name = "";
    private boolean speedPenalty = false;
    private boolean stealthPenalty = false;
    private boolean unarmored = false;
    private boolean worn = true;

    @Nonnull
    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(@Nonnull ArmorType armorType) {
        this.armorType = armorType;
    }

    public int getBaseArmorClass() {
        return baseArmorClass;
    }

    public void setBaseArmorClass(int baseArmorClass) {
        this.baseArmorClass = baseArmorClass;
    }

    public int getMagicBonus() {
        return magicBonus;
    }

    public void setMagicBonus(int magicBonus) {
        this.magicBonus = magicBonus;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public int getTotalArmorClass() {
        return baseArmorClass + magicBonus;
    }

    public boolean hasSpeedPenalty() {
        return speedPenalty;
    }

    public boolean hasStealthPenalty() {
        return stealthPenalty;
    }

    public boolean isUnarmored() {
        return unarmored;
    }

    public boolean isWorn() {
        return worn;
    }

    public void setIsUnarmored(boolean unarmored) {
        this.unarmored = unarmored;
    }

    public void setIsWorn(boolean worn) {
        this.worn = worn;
    }

    public void setSpeedPenalty(boolean speedPenalty) {
        this.speedPenalty = speedPenalty;
    }

    public void setStealthPenalty(boolean stealthPenalty) {
        this.stealthPenalty = stealthPenalty;
    }

    public static class Serializer extends BaseSerializer<Armor> {

        @Override
        public Armor deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Armor armor = new Armor();
            armor.setArmorType(deserialize(jsonObject, context, Keys.ARMOR_TYPE));
            armor.setSpeedPenalty(deserialize(jsonObject, context, Keys.SPEED_PENALTY));
            armor.setStealthPenalty(deserialize(jsonObject, context, Keys.STEALTH_PENALTY));
            armor.setIsUnarmored(deserialize(jsonObject, context, Keys.UNARMORED));
            armor.setIsWorn(deserialize(jsonObject, context, Keys.WORN));
            armor.setBaseArmorClass(deserialize(jsonObject, context, Keys.BASE_ARMOR_CLASS));
            armor.setMagicBonus(deserialize(jsonObject, context, Keys.MAGIC_BONUS));
            armor.setName(deserialize(jsonObject, context, Keys.NAME));
            return armor;
        }

        @Override
        public JsonElement serialize(Armor src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.ARMOR_TYPE, src.getArmorType());
            serialize(jsonObject, context, Keys.SPEED_PENALTY, src.hasSpeedPenalty());
            serialize(jsonObject, context, Keys.STEALTH_PENALTY, src.hasStealthPenalty());
            serialize(jsonObject, context, Keys.UNARMORED, src.isUnarmored());
            serialize(jsonObject, context, Keys.WORN, src.isWorn());
            serialize(jsonObject, context, Keys.BASE_ARMOR_CLASS, src.getBaseArmorClass());
            serialize(jsonObject, context, Keys.MAGIC_BONUS, src.getMagicBonus());
            serialize(jsonObject, context, Keys.NAME, src.getName());
            return jsonObject;
        }
    }
}
