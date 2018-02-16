package io.musician101.mcdndsimple.common.character;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.CORE_STATS, typeAdapter = CoreStatsTab.Serializer.class)
public class CoreStats {

    private AbilityScore charisma = new AbilityScore("Charisma", "cha");
    private AbilityScore constitution = new AbilityScore("Constitution", "con");
    private AbilityScore dexterity = new AbilityScore("Dexterity", "dex");
    private AbilityScore intelligence = new AbilityScore("Intelligence", "int");
    private AbilityScore strength = new AbilityScore("Strength", "str");
    private AbilityScore wisdom = new AbilityScore("Wisdom", "wis");

    @Nonnull
    public AbilityScore getCharisma() {
        return charisma;
    }

    @Nonnull
    public AbilityScore getConstitution() {
        return constitution;
    }

    @Nonnull
    public AbilityScore getDexterity() {
        return dexterity;
    }

    @Nonnull
    public AbilityScore getIntelligence() {
        return intelligence;
    }

    @Nonnull
    public AbilityScore getStrength() {
        return strength;
    }

    @Nonnull
    public AbilityScore getWisdom() {
        return wisdom;
    }

    public static class Serializer implements JsonDeserializer<CoreStats>, JsonSerializer<CoreStats> {

        @Override
        public CoreStats deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreStats coreStats = new CoreStats();
            deserialize(coreStats.getCharisma(), jsonObject.getAsJsonObject(Keys.CHARISMA), context);
            deserialize(coreStats.getConstitution(), jsonObject.getAsJsonObject(Keys.CONSTITUTION), context);
            deserialize(coreStats.getDexterity(), jsonObject.getAsJsonObject(Keys.DEXTERITY), context);
            deserialize(coreStats.getIntelligence(), jsonObject.getAsJsonObject(Keys.INTELLIGENCE), context);
            deserialize(coreStats.getStrength(), jsonObject.getAsJsonObject(Keys.STRENGTH), context);
            deserialize(coreStats.getWisdom(), jsonObject.getAsJsonObject(Keys.WISDOM), context);
            return coreStats;
        }

        private void deserialize(AbilityScore abilityScore, JsonObject jsonObject, JsonDeserializationContext context) {
            Keys.IS_PROFICIENT.deserializeFromParent(jsonObject, context).ifPresent(abilityScore::setIsProficient);
            Keys.SCORE.deserializeFromParent(jsonObject, context).ifPresent(abilityScore::setScore);
        }

        @Override
        public JsonElement serialize(CoreStats src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(src.getCharisma(), jsonObject.getAsJsonObject(Keys.CHARISMA), context);
            serialize(src.getConstitution(), jsonObject.getAsJsonObject(Keys.CONSTITUTION), context);
            serialize(src.getDexterity(), jsonObject.getAsJsonObject(Keys.DEXTERITY), context);
            serialize(src.getIntelligence(), jsonObject.getAsJsonObject(Keys.INTELLIGENCE), context);
            serialize(src.getStrength(), jsonObject.getAsJsonObject(Keys.STRENGTH), context);
            serialize(src.getWisdom(), jsonObject.getAsJsonObject(Keys.WISDOM), context);
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.CHARISMA).ifPresent(jsonKey -> jsonKey.serialize(src.getCharisma(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.CONSTITUTION).ifPresent(jsonKey -> jsonKey.serialize(src.getConstitution(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.DEXTERITY).ifPresent(jsonKey -> jsonKey.serialize(src.getDexterity(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.INTELLIGENCE).ifPresent(jsonKey -> jsonKey.serialize(src.getIntelligence(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.STRENGTH).ifPresent(jsonKey -> jsonKey.serialize(src.getIntelligence(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.WISDOM).ifPresent(jsonKey -> jsonKey.serialize(src.getWisdom(), jsonObject, context));
            return jsonObject;
        }

        private void serialize(AbilityScore abilityScore, JsonObject jsonObject, JsonSerializationContext context) {
            Keys.IS_PROFICIENT.serialize(abilityScore.isProficient(), jsonObject, context);
            Keys.SCORE.serialize(abilityScore.getScore(), jsonObject, context);
        }
    }
}
