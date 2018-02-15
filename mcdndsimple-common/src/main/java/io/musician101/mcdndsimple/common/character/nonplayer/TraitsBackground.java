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

@JsonKey(key = Keys.TRAITS_BACKGROUND, typeAdapter = TraitsBackground.Serializer.class)
public class TraitsBackground {

    private String conditionImmunity = "None";
    private String damageImmunity = "None";
    private String damageResistance = "None";
    private String damageVulnerability = "None";
    private List<String> traits = new ArrayList<>(Collections.singletonList("None"));

    public String getConditionImmunity() {
        return conditionImmunity;
    }

    public void setConditionImmunity(String conditionImmunity) {
        this.conditionImmunity = conditionImmunity;
    }

    public String getDamageImmunity() {
        return damageImmunity;
    }

    public void setDamageImmunity(String damageImmunity) {
        this.damageImmunity = damageImmunity;
    }

    public String getDamageResistance() {
        return damageResistance;
    }

    public void setDamageResistance(String damageResistance) {
        this.damageResistance = damageResistance;
    }

    public String getDamageVulnerability() {
        return damageVulnerability;
    }

    public void setDamageVulnerability(String damageVulnerability) {
        this.damageVulnerability = damageVulnerability;
    }

    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
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
