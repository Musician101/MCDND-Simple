package io.musician101.mcdndsimple.common.character;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.reflect.TypeToken;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
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

    public CoreStats(Class<A> abilityScoreClass) {
        try {
            charisma = abilityScoreClass.getConstructor(String.class, String.class).newInstance("Charisma", "cha");
            constitution = abilityScoreClass.getConstructor(String.class, String.class).newInstance("Constitution", "con");
            dexterity = abilityScoreClass.getConstructor(String.class, String.class).newInstance("Dexterity", "dex");
            intelligence = abilityScoreClass.getConstructor(String.class, String.class).newInstance("Intelligence", "int");
            strength = abilityScoreClass.getConstructor(String.class, String.class).newInstance("Strength", "str");
            wisdom = abilityScoreClass.getConstructor(String.class, String.class).newInstance("Wisdom", "wis");
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

    public static class Serializer<A extends AbilityScore> extends BaseSerializer<CoreStats<A>> {

        private final DeserializerConsumer<A> deserialize;
        private final SerializerConsumer<A> serialize;
        private final Class<A> clazz;

        @SuppressWarnings("unchecked")
        public Serializer() {
            this.clazz = (Class<A>) ((ParameterizedType) new TypeToken<CoreStats<A>>() {

            }.getType()).getActualTypeArguments()[0].getClass();
            if (clazz.equals(NonPlayerAbilityScore.class)) {
                deserialize = (abilityScore, jsonObject, context) -> ((NonPlayerAbilityScore) abilityScore).setBonus(deserialize(jsonObject, context, Keys.BONUS));;
                serialize = (abilityScore, jsonObject, context) -> serialize(jsonObject, context, Keys.BONUS, ((NonPlayerAbilityScore) abilityScore).getBonus());
            }
            else if (clazz.equals(PlayerAbilityScore.class)) {
                deserialize = (abilityScore, jsonObject, context) -> ((PlayerAbilityScore) abilityScore).setIsProficient(deserialize(jsonObject, context, Keys.IS_PROFICIENT));;
                serialize = (abilityScore, jsonObject, context) -> serialize(jsonObject, context, Keys.IS_PROFICIENT, ((PlayerAbilityScore) abilityScore).isProficient());
            }
            else {
                throw new UnsupportedOperationException(clazz.getCanonicalName() + " is not a supported implementation of " + AbilityScore.class.getCanonicalName());
            }
        }

        @Override
        public CoreStats<A> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            CoreStats<A> coreStats = new CoreStats<>(clazz);
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
            abilityScore.setScore(deserialize(jsonObject, context, Keys.SCORE));;
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
            return jsonObject;
        }

        private void serialize(A abilityScore, JsonObject jsonObject, JsonSerializationContext context) {
            serialize.apply(abilityScore, jsonObject, context);
            serialize(jsonObject, context, Keys.SCORE, abilityScore.getScore());
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
