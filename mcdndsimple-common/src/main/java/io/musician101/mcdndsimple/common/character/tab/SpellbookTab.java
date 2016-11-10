package io.musician101.mcdndsimple.common.character.tab;

import io.musician101.mcdndsimple.common.character.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellbookTab
{
    private int invocations = 0;
    private int sorceryPoints = 0;
    private List<Spell> spells = new ArrayList<>();
    private List<SpellcasterClass> spellcasterClasses = new ArrayList<>();

    public int getInvocations()
    {
        return invocations;
    }

    public int getSorceryPoints()
    {
        return sorceryPoints;
    }

    public List<SpellcasterClass> getSpellcasterClasses()
    {
        return spellcasterClasses;
    }

    public List<Spell> getSpells()
    {
        return spells;
    }

    public void addSpell(Spell spell)
    {
        spells.add(spell);
    }

    public void removeSpell(Spell spell)
    {
        spells.remove(spell);
    }

    public void setInvocations(int level)
    {
        if (spellcasterClasses.contains(SpellcasterClass.WARLOCK))
        {
            if (level == 1)
                this.invocations = 0;
            else if (level >= 2 && level <=4)
                this.invocations = 2;
            else if (level >= 5 && level <= 6)
                this.invocations = 3;
        }

        this.invocations = 0;
    }

    public void setSorceryPoints(int level)
    {
        if (spellcasterClasses.contains(SpellcasterClass.SORCERER))
        {
            if (level == 1)
                this.sorceryPoints = 0;
            else
                this.sorceryPoints = level;
        }
    }

    public void setSpellcasterClasses(List<SpellcasterClass> spellcasterClasses)
    {
        this.spellcasterClasses = spellcasterClasses;
    }

    public void setSpells(List<Spell> spells)
    {
        this.spells = spells;
    }

    public void addSpellcasterClass(SpellcasterClass spellcasterClass)
    {
        spellcasterClasses.add(spellcasterClass);
    }

    public void removeSpellcasterClass(SpellcasterClass spellcasterClass)
    {
        spellcasterClasses.remove(spellcasterClass);
    }
}
