package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.common.character.spell.SpellcasterClass;
import java.util.ArrayList;
import java.util.List;

public class SpellbookTab {

    private int sorceryPointsUsed = 0;
    private SpellSlots spellSlots = new SpellSlots();
    private List<SpellcasterClass> spellcasterClasses = new ArrayList<>();
    private List<Spell> spells = new ArrayList<>();
    private int warlockSpellSlotsUsed = 0;

    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public void addSpellcasterClass(SpellcasterClass spellcasterClass) {
        spellcasterClasses.add(spellcasterClass);
    }

    public int getInvocations(ClassLevels classLevels) {
        int level = classLevels.getSorcerer();
        if (spellcasterClasses.contains(SpellcasterClass.WARLOCK)) {
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

    public int getSorceryPointsMax(ClassLevels classLevels) {
        int level = classLevels.getSorcerer();
        if (spellcasterClasses.contains(SpellcasterClass.SORCERER)) {
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

    public SpellSlots getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(SpellSlots spellSlots) {
        this.spellSlots = spellSlots;
    }

    public List<SpellcasterClass> getSpellcasterClasses() {
        return spellcasterClasses;
    }

    public void setSpellcasterClasses(List<SpellcasterClass> spellcasterClasses) {
        this.spellcasterClasses = spellcasterClasses;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public int getWarlockSpellSlotsUsed() {
        return warlockSpellSlotsUsed;
    }

    public void setWarlockSpellSlotsUsed(int warlockSpellSlotsUsed) {
        this.warlockSpellSlotsUsed = warlockSpellSlotsUsed;
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    public void removeSpellcasterClass(SpellcasterClass spellcasterClass) {
        spellcasterClasses.remove(spellcasterClass);
    }
}
