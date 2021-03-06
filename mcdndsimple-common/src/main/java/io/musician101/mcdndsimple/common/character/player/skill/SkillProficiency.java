package io.musician101.mcdndsimple.common.character.player.skill;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.stream.Stream;
import javax.annotation.Nonnull;

public enum SkillProficiency {
    EXPERTISE("Expertise"),
    JACK_OF_ALL_TRADES("Jack of all Trades"),
    NONE("None"),
    YES("Yes");

    @Nonnull
    private final String name;

    SkillProficiency(@Nonnull String name) {
        this.name = name;
    }

    public int getBonus(@Nonnull AbilityScore abilityScore, @Nonnull ClassLevels classLevels, @Nonnull Experience experience) {
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

    @Nonnull
    public String getName() {
        return name;
    }

    public static class Serializer extends BaseSerializer<SkillProficiency> {

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
