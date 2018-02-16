package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.TRAITS_BACKGROUND, typeAdapter = TraitsBackground.Serializer.class)
public class TraitsBackground {

    @Nonnull
    private String conditionImmunity = "None";
    @Nonnull
    private String damageImmunity = "None";
    @Nonnull
    private String damageResistance = "None";
    @Nonnull
    private String damageVulnerability = "None";
    @Nonnull
    private List<String> traits = new ArrayList<>(Collections.singletonList("None"));

    @Nonnull
    public String getConditionImmunity() {
        return conditionImmunity;
    }

    public void setConditionImmunity(@Nonnull String conditionImmunity) {
        this.conditionImmunity = conditionImmunity;
    }

    @Nonnull
    public String getDamageImmunity() {
        return damageImmunity;
    }

    public void setDamageImmunity(@Nonnull String damageImmunity) {
        this.damageImmunity = damageImmunity;
    }

    @Nonnull
    public String getDamageResistance() {
        return damageResistance;
    }

    public void setDamageResistance(@Nonnull String damageResistance) {
        this.damageResistance = damageResistance;
    }

    @Nonnull
    public String getDamageVulnerability() {
        return damageVulnerability;
    }

    public void setDamageVulnerability(@Nonnull String damageVulnerability) {
        this.damageVulnerability = damageVulnerability;
    }

    @Nonnull
    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(@Nonnull List<String> traits) {
        this.traits = traits;
    }

    public static class Serializer implements JsonDeserializer<TraitsBackground>, JsonSerializer<TraitsBackground> {

        @Override
        public TraitsBackground deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            TraitsBackground traitsBackground = new TraitsBackground();
            Keys.TRAITS.deserializeFromParent(jsonObject, context).ifPresent(traitsBackground::setTraits);
            Keys.CONDITION_IMMUNITY.deserializeFromParent(jsonObject, context).ifPresent(traitsBackground::setConditionImmunity);
            Keys.DAMAGE_IMMUNITY.deserializeFromParent(jsonObject, context).ifPresent(traitsBackground::setDamageImmunity);
            Keys.DAMAGE_RESISTANCE.deserializeFromParent(jsonObject, context).ifPresent(traitsBackground::setDamageResistance);
            Keys.DAMAGE_VULNERABILITY.deserializeFromParent(jsonObject, context).ifPresent(traitsBackground::setDamageVulnerability);
            return traitsBackground;
        }

        @Override
        public JsonElement serialize(TraitsBackground src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.TRAITS.serialize(src.getTraits(), jsonObject, context);
            Keys.CONDITION_IMMUNITY.serialize(src.getConditionImmunity(), jsonObject, context);
            Keys.DAMAGE_IMMUNITY.serialize(src.getDamageImmunity(), jsonObject, context);
            Keys.DAMAGE_RESISTANCE.serialize(src.getDamageResistance(), jsonObject, context);
            Keys.DAMAGE_VULNERABILITY.serialize(src.getDamageVulnerability(), jsonObject, context);
            return jsonObject;
        }
    }
}
