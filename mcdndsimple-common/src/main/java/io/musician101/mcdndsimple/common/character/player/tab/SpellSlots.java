package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;


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

    public static class Serializer extends BaseSerializer<SpellSlots> {

        @Override
        public SpellSlots deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellSlots spellSlots = new SpellSlots();
            spellSlots.setFirstLevelSpellsUsed(deserialize(jsonObject, context, Keys.FIRST_LEVEL_SPELLS_USED));
            spellSlots.setSecondLevelSpellsUsed(deserialize(jsonObject, context, Keys.SECOND_LEVEL_SPELLS_USED));
            spellSlots.setThirdLevelSpellsUsed(deserialize(jsonObject, context, Keys.THIRD_LEVEL_SPELLS_USED));
            spellSlots.setFourthLevelSpellsUsed(deserialize(jsonObject, context, Keys.FOURTH_LEVEL_SPELLS_USED));
            spellSlots.setFifthLevelSpellsUsed(deserialize(jsonObject, context, Keys.FIFTH_LEVEL_SPELLS_USED));
            spellSlots.setSixthLevelSpellsUsed(deserialize(jsonObject, context, Keys.SIXTH_LEVEL_SPELLS_USED));
            spellSlots.setSeventhLevelSpellsUsed(deserialize(jsonObject, context, Keys.SEVENTH_LEVEL_SPELLS_USED));
            spellSlots.setEighthLevelSpellsUsed(deserialize(jsonObject, context, Keys.EIGHTH_LEVEL_SPELLS_USED));
            spellSlots.setNinthLevelSpellsUsed(deserialize(jsonObject, context, Keys.NINTH_LEVEL_SPELLS_USED));
            return spellSlots;
        }

        @Override
        public JsonElement serialize(SpellSlots src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.FIRST_LEVEL_SPELLS_USED, src.getFirstLevelSpellsUsed());
            serialize(jsonObject, context, Keys.SECOND_LEVEL_SPELLS_USED, src.getSecondLevelSpellsUsed());
            serialize(jsonObject, context, Keys.THIRD_LEVEL_SPELLS_USED, src.getThirdLevelSpellsUsed());
            serialize(jsonObject, context, Keys.FOURTH_LEVEL_SPELLS_USED, src.getFourthLevelSpellsUsed());
            serialize(jsonObject, context, Keys.FIFTH_LEVEL_SPELLS_USED, src.getFifthLevelSpellsUsed());
            serialize(jsonObject, context, Keys.SIXTH_LEVEL_SPELLS_USED, src.getSixthLevelSpellsUsed());
            serialize(jsonObject, context, Keys.SEVENTH_LEVEL_SPELLS_USED, src.getSeventhLevelSpellsUsed());
            serialize(jsonObject, context, Keys.EIGHTH_LEVEL_SPELLS_USED, src.getEighthLevelSpellsUsed());
            serialize(jsonObject, context, Keys.NINTH_LEVEL_SPELLS_USED, src.getNinthLevelSpellsUsed());
            return jsonObject;
        }
    }
}
