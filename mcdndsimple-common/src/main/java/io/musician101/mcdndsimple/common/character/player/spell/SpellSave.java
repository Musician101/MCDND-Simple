package io.musician101.mcdndsimple.common.character.player.spell;

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


public class SpellSave {

    private int customDC = 0;
    @Nonnull
    private List<String> onSuccessfulSave = new ArrayList<>();
    @Nonnull
    private StatBonus savingStat = StatBonus.NONE;
    @Nonnull
    private SpellcasterClass spellcasterClass = SpellcasterClass.OTHER;

    public int getCustomDC() {
        return customDC;
    }

    public void setCustomDC(int customDC) {
        this.customDC = customDC;
    }

    @Nonnull
    public List<String> getOnSuccessfulSave() {
        return onSuccessfulSave;
    }

    public void setOnSuccessfulSave(@Nonnull List<String> onSuccessfulSave) {
        this.onSuccessfulSave = onSuccessfulSave;
    }

    public SpellcasterClass getSaveDCType() {
        return spellcasterClass;
    }

    public void setSaveDCType(SpellcasterClass spellcasterClass) {
        this.spellcasterClass = spellcasterClass;
    }

    @Nonnull
    public StatBonus getSavingStat() {
        return savingStat;
    }

    public void setSavingStat(@Nonnull StatBonus savingStat) {
        this.savingStat = savingStat;
    }

    public static class Serializer extends BaseSerializer<SpellSave> {

        @Override
        public SpellSave deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellSave spellSave = new SpellSave();
            spellSave.setCustomDC(deserialize(jsonObject, context, Keys.CUSTOM_SAVE_DC));
            spellSave.setOnSuccessfulSave(deserialize(jsonObject, context, Keys.ON_SUCCESSFUL_SAVE));
            spellSave.setSavingStat(deserialize(jsonObject, context, Keys.STAT_BONUS));;
            spellSave.setSaveDCType(deserialize(jsonObject, context, Keys.SPELLCASTER_CLASS));;
            return spellSave;
        }

        @Override
        public JsonElement serialize(SpellSave src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.CUSTOM_SAVE_DC, src.getCustomDC());
            serialize(jsonObject, context, Keys.ON_SUCCESSFUL_SAVE, src.getOnSuccessfulSave());
            serialize(jsonObject, context, Keys.STAT_BONUS, src.getSavingStat());
            serialize(jsonObject, context, Keys.SPELLCASTER_CLASS, src.getSaveDCType());
            return jsonObject;
        }
    }
}
