package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.character.SpellcasterClass;
import io.musician101.mcdndsimple.sponge.character.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellbookTab
{
    private int invocations = 0;
    private int sorceryPoints = 0;
    private List<Spell> spells = new ArrayList<>();
    private SpellcasterClass spellcasterClass = null;

    public int getInvocations()
    {
        return invocations;
    }

    public int getSorceryPoints()
    {
        return sorceryPoints;
    }

    public SpellcasterClass getSpellcasterClass()
    {
        return spellcasterClass;
    }

    public List<Spell> getSpells()
    {
        return spells;
    }

    public void setInvocations(int level)
    {
        if (spellcasterClass == SpellcasterClass.WARLOCK)
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
        if (level == 1)
            this.sorceryPoints = 0;
        else
            this.sorceryPoints = level;
    }

    public void setSpellcasterClass(SpellcasterClass spellcasterClass)
    {
        this.spellcasterClass = spellcasterClass;
    }

    public void setSpells(List<Spell> spells)
    {
        this.spells = spells;
    }
}
