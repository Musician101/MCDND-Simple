package io.musician101.mcdndsimple.common.character.player.skill;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.lang.reflect.Type;
import java.util.stream.Stream;

@JsonKey(key = Keys.SKILL_PROFICIENCY, typeAdapter = SkillProficiency.Serializer.class)
public enum SkillProficiency {
    EXPERTISE("Expertise"),
    JACK_OF_ALL_TRADES("Jack of all Trades"),
    NONE("None"),
    YES("Yes");

    private final String name;

    SkillProficiency(String name) {
        this.name = name;
    }

    public int getBonus(AbilityScore abilityScore, ClassLevels classLevels, Experience experience) {
        switch (this) {
            case EXPERTISE:
                return 2 * YES.getBonus(abilityScore, classLevels, experience);
            case JACK_OF_ALL_TRADES:
                return 1;
            case YES:
                return abilityScore.getMod() + experience.getProficiencyBonus(classLevels);
            default:
                return 0;
        }
    }

    public String getName() {
        return name;
    }

    public static class Serializer implements JsonDeserializer<SkillProficiency>, JsonSerializer<SkillProficiency> {

        @Override
        public SkillProficiency deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return Stream.of(SkillProficiency.values()).filter(skillProficiency -> skillProficiency.getName().equals(json.getAsString())).findFirst().orElse(SkillProficiency.NONE);
        }

        @Override
        public JsonElement serialize(SkillProficiency src, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(src.getName());
        }
    }
}
