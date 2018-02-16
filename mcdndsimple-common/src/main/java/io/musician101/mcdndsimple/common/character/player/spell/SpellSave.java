package io.musician101.mcdndsimple.common.character.player.spell;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.SPELL_SAVE, typeAdapter = SpellSave.Serializer.class)
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

    public static class Serializer implements JsonDeserializer<SpellSave>, JsonSerializer<SpellSave> {

        @Override
        public SpellSave deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellSave spellSave = new SpellSave();
            Keys.CUSTOM_SAVE_DC.deserializeFromParent(jsonObject, context).ifPresent(spellSave::setCustomDC);
            Keys.ON_SUCCESSFUL_SAVE.deserializeFromParent(jsonObject, context).ifPresent(spellSave::setOnSuccessfulSave);
            JsonKeyProcessor.<JsonObject, StatBonus>getJsonKey(Keys.STAT_BONUS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spellSave::setSavingStat));
            JsonKeyProcessor.<JsonObject, SpellcasterClass>getJsonKey(Keys.SPELLCASTER_CLASS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(spellSave::setSaveDCType));
            return spellSave;
        }

        @Override
        public JsonElement serialize(SpellSave src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.CUSTOM_SAVE_DC.serialize(src.getCustomDC(), jsonObject, context);
            Keys.ON_SUCCESSFUL_SAVE.serialize(src.getOnSuccessfulSave(), jsonObject, context);
            JsonKeyProcessor.<JsonObject, StatBonus>getJsonKey(Keys.STAT_BONUS).ifPresent(jsonKey -> jsonKey.serialize(src.getSavingStat(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SpellcasterClass>getJsonKey(Keys.SPELLCASTER_CLASS).ifPresent(jsonKey -> jsonKey.serialize(src.getSaveDCType(), jsonObject, context));
            return jsonObject;
        }
    }
}
