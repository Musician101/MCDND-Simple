package io.musician101.mcdndsimple.common.character.player.outputoption;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class OutputOptions {

    @Nonnull
    private CoreSkillsOutputOptions coreSkillsOutputOptions = new CoreSkillsOutputOptions();
    @Nonnull
    private SavingThrowOutputOptions savingThrowOutputOptions = new SavingThrowOutputOptions();
    @Nonnull
    private WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions = new WeaponsSpellMiscOutputOptions();

    @Nonnull
    public CoreSkillsOutputOptions getCoreSkillsOutputOptions() {
        return coreSkillsOutputOptions;
    }

    private void setCoreSkillsOutputOptions(@Nonnull CoreSkillsOutputOptions coreSkillsOutputOptions) {
        this.coreSkillsOutputOptions = coreSkillsOutputOptions;
    }

    @Nonnull
    public SavingThrowOutputOptions getSavingThrowOutputOptions() {
        return savingThrowOutputOptions;
    }

    private void setSavingThrowOutputOptions(@Nonnull SavingThrowOutputOptions savingThrowOutputOptions) {
        this.savingThrowOutputOptions = savingThrowOutputOptions;
    }

    @Nonnull
    public WeaponsSpellMiscOutputOptions getWeaponsSpellMiscOutputOptions() {
        return weaponsSpellMiscOutputOptions;
    }

    private void setWeaponsSpellMiscOutputOptions(@Nonnull WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions) {
        this.weaponsSpellMiscOutputOptions = weaponsSpellMiscOutputOptions;
    }

    public static class Serializer extends BaseSerializer<OutputOptions> {

        @Override
        public OutputOptions deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            OutputOptions outputOptions = new OutputOptions();
            outputOptions.setCoreSkillsOutputOptions(deserialize(jsonObject, context, Keys.CORE_SKILLS_OUTPUT_OPTIONS));
            outputOptions.setSavingThrowOutputOptions(deserialize(jsonObject, context, Keys.SAVING_THROW_OUTPUT_OPTIONS));
            outputOptions.setWeaponsSpellMiscOutputOptions(deserialize(jsonObject, context, Keys.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS));
            return outputOptions;
        }

        @Override
        public JsonElement serialize(OutputOptions src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.CORE_SKILLS_OUTPUT_OPTIONS, src.getCoreSkillsOutputOptions());
            serialize(jsonObject, context, Keys.SAVING_THROW_OUTPUT_OPTIONS, src.getSavingThrowOutputOptions());
            serialize(jsonObject, context, Keys.WEAPONS_SPELL_MISC_OUTPUT_OPTIONS, src.getWeaponsSpellMiscOutputOptions());
            return jsonObject;
        }
    }
}
