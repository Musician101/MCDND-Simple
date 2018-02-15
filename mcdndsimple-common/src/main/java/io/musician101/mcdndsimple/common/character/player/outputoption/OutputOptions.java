package io.musician101.mcdndsimple.common.character.player.outputoption;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;

public class OutputOptions {

    private CoreSkillsOutputOptions coreSkillsOutputOptions = new CoreSkillsOutputOptions();
    private SavingThrowOutputOptions savingThrowOutputOptions = new SavingThrowOutputOptions();
    private WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = new WeaponsSpellMiscOutputOptions();

    public CoreSkillsOutputOptions getCoreSkillsOutputOptions() {
        return coreSkillsOutputOptions;
    }

    public void setCoreSkillsOutputOptions(CoreSkillsOutputOptions coreSkillsOutputOptions) {
        this.coreSkillsOutputOptions = coreSkillsOutputOptions;
    }

    public SavingThrowOutputOptions getSavingThrowOutputOptions() {
        return savingThrowOutputOptions;
    }

    public void setSavingThrowOutputOptions(SavingThrowOutputOptions savingThrowOutputOptions) {
        this.savingThrowOutputOptions = savingThrowOutputOptions;
    }

    public WeaponsSpellMiscOutputOptions getWeaponsSpellMiscOutputOptions() {
        return weaponsSpellMiscOutputOptions;
    }

    public void setWeaponsSpellMiscOutputOptions(WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions) {
        this.weaponsSpellMiscOutputOptions = weaponsSpellMiscOutputOptions;
    }

    public static class Serializer implements JsonDeserializer<OutputOptions>, JsonSerializer<OutputOptions> {

        @Override
        public OutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            OutputOptions outputOptions = new OutputOptions();
            JsonKeyProcessor.<JsonObject, CoreSkillsOutputOptions>getJsonKey(Keys.CORE_SKILLS_OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(outputOptions::setCoreSkillsOutputOptions));
            JsonKeyProcessor.<JsonObject, SavingThrowOutputOptions>getJsonKey(Keys.SAVING_THROW_OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(outputOptions::setSavingThrowOutputOptions));
            JsonKeyProcessor.<JsonObject, WeaponsSpellMiscOutputOptions>getJsonKey(Keys.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(outputOptions::setWeaponsSpellMiscOutputOptions));
            return outputOptions;
        }

        @Override
        public JsonElement serialize(OutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, CoreSkillsOutputOptions>getJsonKey(Keys.CORE_SKILLS_OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.serialize(src.getCoreSkillsOutputOptions(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SavingThrowOutputOptions>getJsonKey(Keys.SAVING_THROW_OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.serialize(src.getSavingThrowOutputOptions(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, WeaponsSpellMiscOutputOptions>getJsonKey(Keys.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS).ifPresent(jsonKey -> jsonKey.serialize(src.getWeaponsSpellMiscOutputOptions(), jsonObject, context));
            return jsonObject;
        }
    }
}
