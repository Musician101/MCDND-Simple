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

@JsonKey(key = Keys.CORE_STATS, typeAdapter = CoreStatsTab.Serializer.class)
public class CoreStats {

    private AbilityScore charisma = new AbilityScore("Charisma", "cha");
    private AbilityScore constitution = new AbilityScore("Constitution", "con");
    private AbilityScore dexterity = new AbilityScore("Dexterity", "dex");
    private AbilityScore intelligence = new AbilityScore("Intelligence", "int");
    private AbilityScore strength = new AbilityScore("Strength", "str");
    private AbilityScore wisdom = new AbilityScore("Wisdom", "wis");

    public AbilityScore getCharisma() {
        return charisma;
    }

    public void setCharisma(AbilityScore charisma) {
        this.charisma = charisma;
    }

    public AbilityScore getConstitution() {
        return constitution;
    }

    public void setConstitution(AbilityScore constitution) {
        this.constitution = constitution;
    }

    public AbilityScore getDexterity() {
        return dexterity;
    }

    public void setDexterity(AbilityScore dexterity) {
        this.dexterity = dexterity;
    }

    public AbilityScore getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(AbilityScore intelligence) {
        this.intelligence = intelligence;
    }

    public AbilityScore getStrength() {
        return strength;
    }

    public void setStrength(AbilityScore strength) {
        this.strength = strength;
    }

    public AbilityScore getWisdom() {
        return wisdom;
    }

    public void setWisdom(AbilityScore wisdom) {
        this.wisdom = wisdom;
    }

    public static class Serializer implements JsonDeserializer<CoreStats>, JsonSerializer<CoreStats> {

        @Override
        public CoreStats deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreStats coreStats = new CoreStats();
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.CHARISMA).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStats::setCharisma));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.CONSTITUTION).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStats::setConstitution));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.DEXTERITY).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStats::setDexterity));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.INTELLIGENCE).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStats::setIntelligence));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.STRENGTH).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStats::setStrength));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.WISDOM).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStats::setWisdom));
            return coreStats;
        }

        @Override
        public JsonElement serialize(CoreStats src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.CHARISMA).ifPresent(jsonKey -> jsonKey.serialize(src.getCharisma(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.CONSTITUTION).ifPresent(jsonKey -> jsonKey.serialize(src.getConstitution(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.DEXTERITY).ifPresent(jsonKey -> jsonKey.serialize(src.getDexterity(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.INTELLIGENCE).ifPresent(jsonKey -> jsonKey.serialize(src.getIntelligence(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.STRENGTH).ifPresent(jsonKey -> jsonKey.serialize(src.getIntelligence(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, AbilityScore>getJsonKey(Keys.WISDOM).ifPresent(jsonKey -> jsonKey.serialize(src.getWisdom(), jsonObject, context));
            return jsonObject;
        }
    }
}
