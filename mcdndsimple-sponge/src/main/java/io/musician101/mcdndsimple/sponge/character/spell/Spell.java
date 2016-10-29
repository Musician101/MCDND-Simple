package io.musician101.mcdndsimple.sponge.character.spell;

import io.musician101.mcdndsimple.sponge.character.SpellcasterClass;
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Spell implements DataSerializable
{
    private boolean needsConcentration = false;
    private boolean isPrepared = true;
    private boolean isRitual = false;
    private int duration = 0;
    private int level = 0;
    private int range = 0;
    private SpellDamage spellDamage = new SpellDamage();
    private SpellHealing spellHealing = new SpellHealing();
    private List<String> description = new ArrayList<>();
    private List<String> effects = new ArrayList<>();
    private SpellSave spellSave = new SpellSave();
    private SpellcasterClass gainedFrom = null;
    private SpellType spellType = null;
    private String attackStat = "";
    //Most spells simply state Action or Bonus Action which have actual numerical values
    //but to keep things simple, we'll just leave it as a string.
    private String castTime = "";
    private String components = "";
    private String targetArea = "";

    public String getAttackStat()
    {
        return attackStat;
    }

    public String getCastTime()
    {
        return castTime;
    }

    public String getComponents()
    {
        return components;
    }

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
                .set(MCDNDSimpleKeys.NEEDS_CONCENTRATION, needsConcentration)
                .set(MCDNDSimpleKeys.IS_PREPARED, isPrepared)
                .set(MCDNDSimpleKeys.DURATION, duration)
                .set(MCDNDSimpleKeys.SPELL_LEVEL, level)
                .set(MCDNDSimpleKeys.RANGE, range)
                .set(MCDNDSimpleKeys.SPELL_DAMAGE, spellDamage.toContainer())
                .set(MCDNDSimpleKeys.SPELL_HEALING, spellHealing.toContainer())
                .set(MCDNDSimpleKeys.SPELL_DESCRIPTION, description)
                .set(MCDNDSimpleKeys.EFFECTS, effects)
                .set(MCDNDSimpleKeys.SPELL_SAVE, spellSave.toContainer())
                .set(MCDNDSimpleKeys.SPELLCASTER_CLASS, gainedFrom.toContainer())
                .set(MCDNDSimpleKeys.SPELL_TYPE, spellType.toContainer())
                .set(MCDNDSimpleKeys.ATTACK_STAT, attackStat)
                .set(MCDNDSimpleKeys.CAST_TIME, castTime)
                .set(MCDNDSimpleKeys.TARGET_AREA, targetArea);
    }

    public List<String> getDescription()
    {
        return description;
    }

    public int getDuration()
    {
        return duration;
    }

    public List<String> getEffects()
    {
        return effects;
    }

    public SpellcasterClass getGainedFrom()
    {
        return gainedFrom;
    }

    public int getLevel()
    {
        return level;
    }

    public int getRange()
    {
        return range;
    }

    public SpellDamage getSpellDamage()
    {
        return spellDamage;
    }

    public SpellHealing getSpellHealing()
    {
        return spellHealing;
    }

    public SpellSave getSpellSave()
    {
        return spellSave;
    }

    public SpellType getSpellType()
    {
        return spellType;
    }

    public String getTargetArea()
    {
        return targetArea;
    }

    public boolean isNeedsConcentration()
    {
        return needsConcentration;
    }

    public boolean isPrepared()
    {
        return isPrepared;
    }

    public boolean isRitual()
    {
        return isRitual;
    }

    public void setAttackStat(String attackStat)
    {
        this.attackStat = attackStat;
    }

    public void setCastTime(String castTime)
    {
        this.castTime = castTime;
    }

    public void setComponents(String components)
    {
        this.components = components;
    }

    public void setDescription(List<String> description)
    {
        this.description = description;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public void setEffects(List<String> effects)
    {
        this.effects = effects;
    }

    public void setGainedFrom(SpellcasterClass gainedFrom)
    {
        this.gainedFrom = gainedFrom;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setNeedsConcentration(boolean needsConcentration)
    {
        this.needsConcentration = needsConcentration;
    }

    public void setPrepared(boolean prepared)
    {
        isPrepared = prepared;
    }

    public void setRange(int range)
    {
        this.range = range;
    }

    public void setRitual(boolean ritual)
    {
        isRitual = ritual;
    }

    public void setSpellDamage(SpellDamage spellDamage)
    {
        this.spellDamage = spellDamage;
    }

    public void setSpellHealing(SpellHealing spellHealing)
    {
        this.spellHealing = spellHealing;
    }

    public void setSpellSave(SpellSave spellSave)
    {
        this.spellSave = spellSave;
    }

    public void setSpellType(SpellType spellType)
    {
        this.spellType = spellType;
    }

    public void setTargetArea(String targetArea)
    {
        this.targetArea = targetArea;
    }
}
