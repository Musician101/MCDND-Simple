package io.musician101.mcdndsimple.common.character.player.equipment.armor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import io.musician101.musicianlibrary.java.json.adapter.TypeOf;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

@TypeOf(Armor.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<Armor>, JsonSerializer<Armor> {

        @Override
        public Armor deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Armor armor = new Armor();
            JsonKeyProcessor.<JsonPrimitive, ArmorType>getJsonKey(Keys.ARMOR_TYPE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(armor::setArmorType));
            Keys.SPEED_PENALTY.deserializeFromParent(jsonObject, context).ifPresent(armor::setSpeedPenalty);
            Keys.STEALTH_PENALTY.deserializeFromParent(jsonObject, context).ifPresent(armor::setStealthPenalty);
            Keys.UNARMORED.deserializeFromParent(jsonObject, context).ifPresent(armor::setIsUnarmored);
            Keys.WORN.deserializeFromParent(jsonObject, context).ifPresent(armor::setIsWorn);
            Keys.BASE_ARMOR_CLASS.deserializeFromParent(jsonObject, context).ifPresent(armor::setBaseArmorClass);
            Keys.MAGIC_BONUS.deserializeFromParent(jsonObject, context).ifPresent(armor::setMagicBonus);
            Keys.NAME.deserializeFromParent(jsonObject, context).ifPresent(armor::setName);
            return armor;
        }

        @Override
        public JsonElement serialize(Armor src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonPrimitive, ArmorType>getJsonKey(Keys.ARMOR_TYPE).ifPresent(jsonKey -> jsonKey.serialize(src.getArmorType(), jsonObject, context));
            Keys.SPEED_PENALTY.serialize(src.hasSpeedPenalty(), jsonObject, context);
            Keys.STEALTH_PENALTY.serialize(src.hasStealthPenalty(), jsonObject, context);
            Keys.UNARMORED.serialize(src.isUnarmored(), jsonObject, context);
            Keys.WORN.serialize(src.isWorn(), jsonObject, context);
            Keys.BASE_ARMOR_CLASS.serialize(src.getBaseArmorClass(), jsonObject, context);
            Keys.MAGIC_BONUS.serialize(src.getMagicBonus(), jsonObject, context);
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            return jsonObject;
        }
    }
}
