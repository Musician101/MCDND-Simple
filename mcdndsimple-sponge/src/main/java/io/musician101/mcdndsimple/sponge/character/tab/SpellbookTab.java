package io.musician101.mcdndsimple.sponge.character.tab;

import io.musician101.mcdndsimple.sponge.DataUtils;
import io.musician101.mcdndsimple.sponge.character.SpellcasterClass;
import io.musician101.mcdndsimple.sponge.character.spell.Spell;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SpellbookTab implements DataSerializable
{
    private int invocations = 0;
    private int sorceryPoints = 0;
    private List<Spell> spells = new ArrayList<>();
    private List<SpellcasterClass> spellcasterClasses = new ArrayList<>();

    @Override
    public int getContentVersion()
    {
        return 1;
    }

    @Nonnull
    @Override
    public DataContainer toContainer()
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CONTENT_VERSION, getContentVersion())
                .set(MCDNDSimpleKeys.INVOCATION_COUNT, invocations)
                .set(MCDNDSimpleKeys.SORCERY_POINTS, sorceryPoints)
                .set(MCDNDSimpleKeys.SPELLS, DataUtils.serialize(spells))
                .set(MCDNDSimpleKeys.SPELLCASTER_CLASSES, DataUtils.serialize(spellcasterClasses));
    }

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

    public static SpellbookTab fromDataContainer(DataContainer dataContainer, int level)
    {
        SpellbookTab spellbookTab = new SpellbookTab();
        DataUtils.getDataContainerList(dataContainer, MCDNDSimpleKeys.SPELLS).ifPresent(list -> list.forEach(data -> spellbookTab.addSpell(Spell.fromDataContainer(data))));
        DataUtils.getDataContainerList(dataContainer, MCDNDSimpleKeys.SPELLCASTER_CLASSES).ifPresent(list -> list.forEach(data -> SpellcasterClass.fromDataContainer(data).ifPresent(spellbookTab::addSpellcasterClass)));
        spellbookTab.setInvocations(level);
        spellbookTab.setSorceryPoints(level);
        return spellbookTab;
    }
}
