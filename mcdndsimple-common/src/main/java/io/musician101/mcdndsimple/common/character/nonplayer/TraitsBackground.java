package io.musician101.mcdndsimple.common.character.nonplayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;


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

    public static class Serializer extends BaseSerializer<TraitsBackground> {

        @Override
        public TraitsBackground deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            TraitsBackground traitsBackground = new TraitsBackground();
            traitsBackground.setTraits(deserialize(jsonObject, context, Keys.TRAITS));
            traitsBackground.setConditionImmunity(deserialize(jsonObject, context, Keys.CONDITION_IMMUNITY));
            traitsBackground.setDamageImmunity(deserialize(jsonObject, context, Keys.DAMAGE_IMMUNITY));
            traitsBackground.setDamageResistance(deserialize(jsonObject, context, Keys.DAMAGE_RESISTANCE));
            traitsBackground.setDamageVulnerability(deserialize(jsonObject, context, Keys.DAMAGE_VULNERABILITY));
            return traitsBackground;
        }

        @Override
        public JsonElement serialize(TraitsBackground src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.TRAITS, src.getTraits());
            serialize(jsonObject, context, Keys.CONDITION_IMMUNITY, src.getConditionImmunity());
            serialize(jsonObject, context, Keys.DAMAGE_IMMUNITY, src.getDamageImmunity());
            serialize(jsonObject, context, Keys.DAMAGE_RESISTANCE, src.getDamageResistance());
            serialize(jsonObject, context, Keys.DAMAGE_VULNERABILITY, src.getDamageVulnerability());
            return jsonObject;
        }
    }
}
