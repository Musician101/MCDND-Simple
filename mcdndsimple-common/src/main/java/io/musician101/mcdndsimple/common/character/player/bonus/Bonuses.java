package io.musician101.mcdndsimple.common.character.player.bonus;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.JsonKeyProcessor;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

@JsonKey(key = Keys.BONUSES, typeAdapter = Bonuses.Serializer.class)
public class Bonuses {

    @Nonnull
    private Dice abilitiesAndSkills = new Dice(0);
    @Nonnull
    private MeleeBonus melee = new MeleeBonus();
    @Nonnull
    private RangedBonus ranged = new RangedBonus();
    @Nonnull
    private Dice saves = new Dice(0);
    @Nonnull
    private SpellcastingBonus spellcasting = new SpellcastingBonus();

    @Nonnull
    public Dice getAbilitiesAndSkills() {
        return abilitiesAndSkills;
    }

    public void setAbilitiesAndSkills(@Nonnull Dice abilitiesAndSkills) {
        this.abilitiesAndSkills = abilitiesAndSkills;
    }

    @Nonnull
    public MeleeBonus getMelee() {
        return melee;
    }

    private void setMelee(@Nonnull MeleeBonus melee) {
        this.melee = melee;
    }

    @Nonnull
    public RangedBonus getRanged() {
        return ranged;
    }

    private void setRanged(@Nonnull RangedBonus ranged) {
        this.ranged = ranged;
    }

    @Nonnull
    public Dice getSaves() {
        return saves;
    }

    public void setSaves(@Nonnull Dice saves) {
        this.saves = saves;
    }

    @Nonnull
    public SpellcastingBonus getSpellcasting() {
        return spellcasting;
    }

    private void setSpellcasting(@Nonnull SpellcastingBonus spellcasting) {
        this.spellcasting = spellcasting;
    }

    public static class Serializer implements JsonDeserializer<Bonuses>, JsonSerializer<Bonuses> {

        @Override
        public Bonuses deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Bonuses coreStatsTab = new Bonuses();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.ABILITIES_AND_SKILLS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setAbilitiesAndSkills));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.SAVES).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setSaves));
            JsonKeyProcessor.<JsonObject, MeleeBonus>getJsonKey(Keys.MELEE_BONUS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setMelee));
            JsonKeyProcessor.<JsonObject, RangedBonus>getJsonKey(Keys.RANGED_BONUS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setRanged));
            JsonKeyProcessor.<JsonObject, SpellcastingBonus>getJsonKey(Keys.SPELLCASTING_BONUS).ifPresent(jsonKey -> jsonKey.deserializeFromParent(jsonObject, context).ifPresent(coreStatsTab::setSpellcasting));
            return coreStatsTab;
        }

        @Override
        public JsonElement serialize(Bonuses src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.ABILITIES_AND_SKILLS).ifPresent(jsonKey -> jsonKey.serialize(src.getAbilitiesAndSkills(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, Dice>getJsonKey(Keys.SAVES).ifPresent(jsonKey -> jsonKey.serialize(src.getSaves(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, MeleeBonus>getJsonKey(Keys.MELEE_BONUS).ifPresent(jsonKey -> jsonKey.serialize(src.getMelee(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, RangedBonus>getJsonKey(Keys.RANGED_BONUS).ifPresent(jsonKey -> jsonKey.serialize(src.getRanged(), jsonObject, context));
            JsonKeyProcessor.<JsonObject, SpellcastingBonus>getJsonKey(Keys.SPELLCASTING_BONUS).ifPresent(jsonKey -> jsonKey.serialize(src.getSpellcasting(), jsonObject, context));
            return jsonObject;
        }
    }
}
