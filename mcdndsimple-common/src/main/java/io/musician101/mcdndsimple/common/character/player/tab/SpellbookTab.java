package io.musician101.mcdndsimple.common.character.player.tab;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.musicianlibrary.java.json.JsonKey;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;


public class SpellbookTab {

    private int sorceryPointsUsed = 0;
    @Nonnull
    private SpellSlots spellSlots = new SpellSlots();
    @Nonnull
    private List<Spell> spells = new ArrayList<>();
    private int warlockSpellSlotsUsed = 0;

    public void addSpell(@Nonnull Spell spell) {
        spells.add(spell);
    }

    public int getInvocations(@Nonnull ClassLevels classLevels) {
        int level = classLevels.getSorcerer();
        if (getSpellcasterClasses(classLevels).contains(SpellcasterClass.WARLOCK)) {
            if (level == 1) {
                return 0;
            }
            else if (level >= 2 && level <= 4) {
                return 2;
            }
            else if (level >= 5 && level <= 6) {
                return 3;
            }
        }

        return 0;
    }

    public int getSorceryPointsMax(@Nonnull ClassLevels classLevels) {
        int level = classLevels.getSorcerer();
        if (getSpellcasterClasses(classLevels).contains(SpellcasterClass.SORCERER)) {
            if (level == 1) {
                return 0;
            }
            else {
                return level;
            }
        }

        return 0;
    }

    public int getSorceryPointsUsed() {
        return sorceryPointsUsed;
    }

    public void setSorceryPointsUsed(int sorceryPointsUsed) {
        this.sorceryPointsUsed = sorceryPointsUsed;
    }

    @Nonnull
    public SpellSlots getSpellSlots() {
        return spellSlots;
    }

    private void setSpellSlots(@Nonnull SpellSlots spellSlots) {
        this.spellSlots = spellSlots;
    }

    public List<SpellcasterClass> getSpellcasterClasses(@Nonnull ClassLevels classLevels) {
        List<SpellcasterClass> list = new ArrayList<>();
        if (classLevels.getBard() > 0) {
            list.add(SpellcasterClass.BARD);
        }

        if (classLevels.getCleric() > 0) {
            list.add(SpellcasterClass.CLERIC);
        }

        if (classLevels.getDruid() > 0) {
            list.add(SpellcasterClass.DRUID);
        }

        if (classLevels.getCleric() > 0) {
            list.add(SpellcasterClass.CLERIC);
        }

        if (classLevels.getFighter() > 2) {
            list.add(SpellcasterClass.ELDRITCH_KNIGHT);
        }

        if (classLevels.getPaladin() > 0) {
            list.add(SpellcasterClass.PALADIN);
        }

        if (classLevels.getRanger() > 0) {
            list.add(SpellcasterClass.RANGER);
        }

        if (classLevels.getRogue() > 2) {
            list.add(SpellcasterClass.ARCANE_TRICKSTER);
        }

        if (classLevels.getSorcerer() > 0) {
            list.add(SpellcasterClass.SORCERER);
        }

        if (classLevels.getWarlock() > 0) {
            list.add(SpellcasterClass.WARLOCK);
        }

        if (classLevels.getWizard() > 0) {
            list.add(SpellcasterClass.WIZARD);
        }

        return list;
    }

    @Nonnull
    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(@Nonnull List<Spell> spells) {
        this.spells = spells;
    }

    public int getWarlockSpellSlotsUsed() {
        return warlockSpellSlotsUsed;
    }

    public void setWarlockSpellSlotsUsed(int warlockSpellSlotsUsed) {
        this.warlockSpellSlotsUsed = warlockSpellSlotsUsed;
    }

    public void removeSpell(@Nonnull Spell spell) {
        spells.remove(spell);
    }

    public static class Serializer extends BaseSerializer<SpellbookTab> {

        @Override
        public SpellbookTab deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            SpellbookTab spellBookTab = new SpellbookTab();
            spellBookTab.setSorceryPointsUsed(deserialize(jsonObject, context, Keys.SORCERY_POINTS_USED));
            spellBookTab.setSpellSlots(deserialize(jsonObject, context, Keys.SPELL_SLOTS));;
            spellBookTab.setSpells(deserialize(jsonObject, context, Keys.SPELLS));
            spellBookTab.setWarlockSpellSlotsUsed(deserialize(jsonObject, context, Keys.WARLOCK_SPELL_SLOTS_USED));
            return spellBookTab;
        }

        @Override
        public JsonElement serialize(SpellbookTab src, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            serialize(jsonObject, context, Keys.SORCERY_POINTS_USED, src.getSorceryPointsUsed());
            serialize(jsonObject, context, Keys.SPELL_SLOTS, src.getSpellSlots());
            serialize(jsonObject, context, Keys.SPELLS, src.getSpells());
            serialize(jsonObject, context, Keys.WARLOCK_SPELL_SLOTS_USED, src.getWarlockSpellSlotsUsed());
            return jsonObject;
        }
    }
}
