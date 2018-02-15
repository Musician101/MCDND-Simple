package io.musician101.mcdndsimple.common.character.player.tab;

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

@JsonKey(key = Keys.SPELL_SLOTS, typeAdapter = SpellSlots.Serializer.class)
public class SpellSlots {

    private int eighthLevelSpellsUsed = 0;
    private int fifthLevelSpellsUsed = 0;
    private int firstLevelSpellsUsed = 0;
    private int fourthLevelSpellsUsed = 0;
    private int ninthLevelSpellsUsed = 0;
    private int secondLevelSpellsUsed = 0;
    private int seventhLevelSpellsUsed = 0;
    private int sixthLevelSpellsUsed = 0;
    private int thirdLevelSpellsUsed = 0;

    public int getEighthLevelSpellsUsed() {
        return eighthLevelSpellsUsed;
    }

    public void setEighthLevelSpellsUsed(int eighthLevelSpellsUsed) {
        this.eighthLevelSpellsUsed = eighthLevelSpellsUsed;
    }

    public int getFifthLevelSpellsUsed() {
        return fifthLevelSpellsUsed;
    }

    public void setFifthLevelSpellsUsed(int fifthLevelSpellsUsed) {
        this.fifthLevelSpellsUsed = fifthLevelSpellsUsed;
    }

    public int getFirstLevelSpellsUsed() {
        return firstLevelSpellsUsed;
    }

    public void setFirstLevelSpellsUsed(int firstLevelSpellsUsed) {
        this.firstLevelSpellsUsed = firstLevelSpellsUsed;
    }

    public int getFourthLevelSpellsUsed() {
        return fourthLevelSpellsUsed;
    }

    public void setFourthLevelSpellsUsed(int fourthLevelSpellsUsed) {
        this.fourthLevelSpellsUsed = fourthLevelSpellsUsed;
    }

    public int getNinthLevelSpellsUsed() {
        return ninthLevelSpellsUsed;
    }

    public void setNinthLevelSpellsUsed(int ninthLevelSpellsUsed) {
        this.ninthLevelSpellsUsed = ninthLevelSpellsUsed;
    }

    public int getSecondLevelSpellsUsed() {
        return secondLevelSpellsUsed;
    }

    public void setSecondLevelSpellsUsed(int secondLevelSpellsUsed) {
        this.secondLevelSpellsUsed = secondLevelSpellsUsed;
    }

    public int getSeventhLevelSpellsUsed() {
        return seventhLevelSpellsUsed;
    }

    public void setSeventhLevelSpellsUsed(int seventhLevelSpellsUsed) {
        this.seventhLevelSpellsUsed = seventhLevelSpellsUsed;
    }

    public int getSixthLevelSpellsUsed() {
        return sixthLevelSpellsUsed;
    }

    public void setSixthLevelSpellsUsed(int sixthLevelSpellsUsed) {
        this.sixthLevelSpellsUsed = sixthLevelSpellsUsed;
    }

    public int getThirdLevelSpellsUsed() {
        return thirdLevelSpellsUsed;
    }

    public void setThirdLevelSpellsUsed(int thirdLevelSpellsUsed) {
        this.thirdLevelSpellsUsed = thirdLevelSpellsUsed;
    }

    public static class Serializer implements JsonDeserializer<SpellSlots>, JsonSerializer<SpellSlots> {

        @Override
        public SpellSlots deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellSlots spellSlots = new SpellSlots();
            Keys.FIRST_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setFirstLevelSpellsUsed);
            Keys.SECOND_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setSecondLevelSpellsUsed);
            Keys.THIRD_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setThirdLevelSpellsUsed);
            Keys.FOURTH_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setFourthLevelSpellsUsed);
            Keys.FIFTH_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setFifthLevelSpellsUsed);
            Keys.SIXTH_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setSixthLevelSpellsUsed);
            Keys.SEVENTH_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setSeventhLevelSpellsUsed);
            Keys.EIGHTH_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setEighthLevelSpellsUsed);
            Keys.NINTH_LEVEL_SPELLS_USED.deserializeFromParent(jsonObject, context).ifPresent(spellSlots::setNinthLevelSpellsUsed);
            return spellSlots;
        }

        @Override
        public JsonElement serialize(SpellSlots src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            Keys.FIRST_LEVEL_SPELLS_USED.serialize(src.getFirstLevelSpellsUsed(), jsonObject, context);
            Keys.SECOND_LEVEL_SPELLS_USED.serialize(src.getSecondLevelSpellsUsed(), jsonObject, context);
            Keys.THIRD_LEVEL_SPELLS_USED.serialize(src.getThirdLevelSpellsUsed(), jsonObject, context);
            Keys.FOURTH_LEVEL_SPELLS_USED.serialize(src.getFourthLevelSpellsUsed(), jsonObject, context);
            Keys.FIFTH_LEVEL_SPELLS_USED.serialize(src.getFifthLevelSpellsUsed(), jsonObject, context);
            Keys.SIXTH_LEVEL_SPELLS_USED.serialize(src.getSixthLevelSpellsUsed(), jsonObject, context);
            Keys.SEVENTH_LEVEL_SPELLS_USED.serialize(src.getSeventhLevelSpellsUsed(), jsonObject, context);
            Keys.EIGHTH_LEVEL_SPELLS_USED.serialize(src.getEighthLevelSpellsUsed(), jsonObject, context);
            Keys.NINTH_LEVEL_SPELLS_USED.serialize(src.getNinthLevelSpellsUsed(), jsonObject, context);
            return jsonObject;
        }
    }
}
