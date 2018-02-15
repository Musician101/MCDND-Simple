package io.musician101.mcdndsimple.common.character.player;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeys;
import java.lang.reflect.Type;

@JsonKeys(keys = {Keys.CHARISMA, Keys.CONSTITUTION, Keys.DEXTERITY, Keys.INTELLIGENCE, Keys.STRENGTH, Keys.WISDOM}, typeAdapter = AbilityScore.Serializer.class)
public class AbilityScore {

    private final String name;
    private final String shortName;
    private boolean proficient = false;
    private int score = 0;

    public AbilityScore(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public int getMod() {
        return (score - 10) / 2;
    }

    public String getName() {
        return name;
    }

    public int getSaveMod(ClassLevels classLevels, Experience experience) {
        int mod = getMod();
        if (proficient) {
            return mod + experience.getProficiencyBonus(classLevels);
        }

        return mod;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean isProficient() {
        return proficient;
    }

    public void setIsProficient(boolean proficient) {
        this.proficient = proficient;
    }

    public static class Serializer implements JsonDeserializer<AbilityScore>, JsonSerializer<AbilityScore> {

        @Override
        public AbilityScore deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String name = Keys.NAME.deserializeFromParent(jsonObject, context).orElseThrow(() -> new JsonParseException("AbilityScore must have a name."));
            String shortName = Keys.SHORT_NAME.deserializeFromParent(jsonObject, context).orElseThrow(() -> new JsonParseException("AbilityScore must have a short name."));
            AbilityScore abilityScore = new AbilityScore(name, shortName);
            Keys.IS_PROFICIENT.deserializeFromParent(jsonObject, context).ifPresent(abilityScore::setIsProficient);
            Keys.SCORE.deserializeFromParent(jsonObject, context).ifPresent(abilityScore::setScore);
            return abilityScore;
        }

        @Override
        public JsonElement serialize(AbilityScore src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.NAME.serialize(src.getName(), jsonObject, context);
            Keys.SHORT_NAME.serialize(src.getShortName(), jsonObject, context);
            Keys.IS_PROFICIENT.serialize(src.isProficient(), jsonObject, context);
            Keys.SCORE.serialize(src.getScore(), jsonObject, context);
            return jsonObject;
        }
    }
}
