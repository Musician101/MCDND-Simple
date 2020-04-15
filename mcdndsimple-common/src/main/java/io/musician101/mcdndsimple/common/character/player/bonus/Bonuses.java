package io.musician101.mcdndsimple.common.character.player.bonus;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;

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

    public static class Serializer extends BaseSerializer<Bonuses> {

        @Override
        public Bonuses deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Bonuses coreStatsTab = new Bonuses();
            coreStatsTab.setAbilitiesAndSkills(deserialize(jsonObject, context, Keys.ABILITIES_AND_SKILLS));;
            coreStatsTab.setSaves(deserialize(jsonObject, context, Keys.SAVES));;
            coreStatsTab.setMelee(deserialize(jsonObject, context, Keys.MELEE_BONUS));;
            coreStatsTab.setRanged(deserialize(jsonObject, context, Keys.RANGED_BONUS));;
            coreStatsTab.setSpellcasting(deserialize(jsonObject, context, Keys.SPELLCASTING_BONUS));;
            return coreStatsTab;
        }

        @Override
        public JsonElement serialize(Bonuses src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.ABILITIES_AND_SKILLS, src.getAbilitiesAndSkills());
            serialize(jsonObject, context, Keys.SAVES, src.getSaves());
            serialize(jsonObject, context, Keys.MELEE_BONUS, src.getMelee());
            serialize(jsonObject, context, Keys.RANGED_BONUS, src.getRanged());
            serialize(jsonObject, context, Keys.SPELLCASTING_BONUS, src.getSpellcasting());
            return jsonObject;
        }
    }
}
