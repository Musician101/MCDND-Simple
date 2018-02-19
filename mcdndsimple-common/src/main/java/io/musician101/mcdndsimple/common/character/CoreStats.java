package io.musician101.mcdndsimple.common.character;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

public class CoreStats<A extends AbilityScore> {

    private final A charisma;
    private final A constitution;
    private final A dexterity;
    private final A intelligence;
    private final A strength;
    private final A wisdom;

    @SuppressWarnings("unchecked")
    public CoreStats() {
        Class<?> clazz = ((ParameterizedType) new TypeToken<CoreStats<A>>() {

        }.getType()).getActualTypeArguments()[0].getClass();
        try {
            charisma = (A) clazz.getConstructor(String.class, String.class).newInstance("Charisma", "cha");
            constitution = (A) clazz.getConstructor(String.class, String.class).newInstance("Constitution", "con");
            dexterity = (A) clazz.getConstructor(String.class, String.class).newInstance("Dexterity", "dex");
            intelligence = (A) clazz.getConstructor(String.class, String.class).newInstance("Intelligence", "int");
            strength = (A) clazz.getConstructor(String.class, String.class).newInstance("Strength", "str");
            wisdom = (A) clazz.getConstructor(String.class, String.class).newInstance("Wisdom", "wis");
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @Nonnull
    public A getCharisma() {
        return charisma;
    }

    @Nonnull
    public A getConstitution() {
        return constitution;
    }

    @Nonnull
    public A getDexterity() {
        return dexterity;
    }

    @Nonnull
    public A getIntelligence() {
        return intelligence;
    }

    @Nonnull
    public A getStrength() {
        return strength;
    }

    @Nonnull
    public A getWisdom() {
        return wisdom;
    }

    public static class Serializer<A extends AbilityScore> implements JsonDeserializer<CoreStats<A>>, JsonSerializer<CoreStats<A>> {

        private final DeserializerConsumer<A> deserialize;
        private final SerializerConsumer<A> serialize;

        @SuppressWarnings("unchecked")
        public Serializer() {
            Class<?> clazz = ((ParameterizedType) new TypeToken<CoreStats<A>>() {

            }.getType()).getActualTypeArguments()[0].getClass();
            if (clazz.equals(NonPlayerAbilityScore.class)) {
                deserialize = (abilityScore, jsonObject, context) -> Keys.BONUS.deserializeFromParent(jsonObject, context).ifPresent(((NonPlayerAbilityScore) abilityScore)::setBonus);
                serialize = (abilityScore, jsonObject, context) -> Keys.BONUS.serialize(((NonPlayerAbilityScore) abilityScore).getBonus(), jsonObject, context);
            }
            else if (clazz.equals(PlayerAbilityScore.class)) {
                deserialize = (abilityScore, jsonObject, context) -> Keys.IS_PROFICIENT.deserializeFromParent(jsonObject, context).ifPresent(((PlayerAbilityScore) abilityScore)::setIsProficient);
                serialize = (abilityScore, jsonObject, context) -> Keys.IS_PROFICIENT.serialize(((PlayerAbilityScore) abilityScore).isProficient(), jsonObject, context);
            }
            else {
                throw new UnsupportedOperationException(clazz.getCanonicalName() + " is not a supported implementation of " + AbilityScore.class.getCanonicalName());
            }
        }

        @Override
        public CoreStats<A> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreStats<A> coreStats = new CoreStats<>();
            deserialize(coreStats.getCharisma(), jsonObject.getAsJsonObject(Keys.CHARISMA), context);
            deserialize(coreStats.getConstitution(), jsonObject.getAsJsonObject(Keys.CONSTITUTION), context);
            deserialize(coreStats.getDexterity(), jsonObject.getAsJsonObject(Keys.DEXTERITY), context);
            deserialize(coreStats.getIntelligence(), jsonObject.getAsJsonObject(Keys.INTELLIGENCE), context);
            deserialize(coreStats.getStrength(), jsonObject.getAsJsonObject(Keys.STRENGTH), context);
            deserialize(coreStats.getWisdom(), jsonObject.getAsJsonObject(Keys.WISDOM), context);
            return coreStats;
        }

        private void deserialize(A abilityScore, JsonObject jsonObject, JsonDeserializationContext context) {
            deserialize.apply(abilityScore, jsonObject, context);
            Keys.SCORE.deserializeFromParent(jsonObject, context).ifPresent(abilityScore::setScore);
        }

        @Override
        public JsonElement serialize(CoreStats<A> src, Type type, JsonSerializationContext context) {
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

        private void serialize(A abilityScore, JsonObject jsonObject, JsonSerializationContext context) {
            serialize.apply(abilityScore, jsonObject, context);
            Keys.SCORE.serialize(abilityScore.getScore(), jsonObject, context);
        }

        @FunctionalInterface
        private interface DeserializerConsumer<A extends AbilityScore> {

            void apply(A abilityScore, JsonObject jsonObject, JsonDeserializationContext context);
        }

        @FunctionalInterface
        private interface SerializerConsumer<A extends AbilityScore> {

            void apply(A abilityScore, JsonObject jsonObject, JsonSerializationContext context);
        }
    }
}
