package io.musician101.mcdndsimple.sponge.character;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class ClassLevels implements DataSerializable
{
    private int barbarian = 0;
    private int bard = 0;
    private int cleric = 0;
    private int druid = 0;
    private int fighter = 0;
    private int monk = 0;
    private int paladin = 0;
    private int ranger = 0;
    private int rogue = 0;
    private int sorcerer = 0;
    private int warlock = 0;
    private int wizard = 0;

    public int getBarbarian()
    {
        return barbarian;
    }

    public int getBard()
    {
        return bard;
    }

    public int getCleric()
    {
        return cleric;
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
                .set(MCDNDSimpleKeys.BARBARIAN_LEVEL, barbarian)
                .set(MCDNDSimpleKeys.BARD_LEVEL, bard)
                .set(MCDNDSimpleKeys.CLERIC_LEVEL, cleric)
                .set(MCDNDSimpleKeys.DRUID_LEVEL, druid)
                .set(MCDNDSimpleKeys.FIGHTER_LEVEL, fighter)
                .set(MCDNDSimpleKeys.MONK_LEVEL, monk)
                .set(MCDNDSimpleKeys.PALADIN_LEVEL, paladin)
                .set(MCDNDSimpleKeys.RANGER_LEVEL, ranger)
                .set(MCDNDSimpleKeys.ROGUE_LEVEL, rogue)
                .set(MCDNDSimpleKeys.SORCERER_LEVEL, sorcerer)
                .set(MCDNDSimpleKeys.WARLOCK_LEVEL, warlock)
                .set(MCDNDSimpleKeys.WIZARD_LEVEL, wizard);
    }

    public int getDruid()
    {
        return druid;
    }

    public int getFighter()
    {
        return fighter;
    }

    public int getMonk()
    {
        return monk;
    }

    public int getPaladin()
    {
        return paladin;
    }

    public int getRanger()
    {
        return ranger;
    }

    public int getRogue()
    {
        return rogue;
    }

    public int getSorcerer()
    {
        return sorcerer;
    }

    public int getWarlock()
    {
        return warlock;
    }

    public int getWizard()
    {
        return wizard;
    }

    public void setBarbarian(int barbarian)
    {
        this.barbarian = barbarian;
    }

    public void setBard(int bard)
    {
        this.bard = bard;
    }

    public void setCleric(int cleric)
    {
        this.cleric = cleric;
    }

    public void setDruid(int druid)
    {
        this.druid = druid;
    }

    public void setFighter(int fighter)
    {
        this.fighter = fighter;
    }

    public void setMonk(int monk)
    {
        this.monk = monk;
    }

    public void setPaladin(int paladin)
    {
        this.paladin = paladin;
    }

    public void setRanger(int ranger)
    {
        this.ranger = ranger;
    }

    public void setRogue(int rogue)
    {
        this.rogue = rogue;
    }

    public void setSorcerer(int sorcerer)
    {
        this.sorcerer = sorcerer;
    }

    public void setWarlock(int warlock)
    {
        this.warlock = warlock;
    }

    public void setWizard(int wizard)
    {
        this.wizard = wizard;
    }

    public static ClassLevels fromDataContainer(DataContainer dataContainer)
    {
        ClassLevels classLevels = new ClassLevels();
        dataContainer.getInt(MCDNDSimpleKeys.BARBARIAN_LEVEL.getQuery()).ifPresent(classLevels::setBarbarian);
        dataContainer.getInt(MCDNDSimpleKeys.BARD_LEVEL.getQuery()).ifPresent(classLevels::setBard);
        dataContainer.getInt(MCDNDSimpleKeys.CLERIC_LEVEL.getQuery()).ifPresent(classLevels::setCleric);
        dataContainer.getInt(MCDNDSimpleKeys.DRUID_LEVEL.getQuery()).ifPresent(classLevels::setDruid);
        dataContainer.getInt(MCDNDSimpleKeys.FIGHTER_LEVEL.getQuery()).ifPresent(classLevels::setFighter);
        dataContainer.getInt(MCDNDSimpleKeys.MONK_LEVEL.getQuery()).ifPresent(classLevels::setMonk);
        dataContainer.getInt(MCDNDSimpleKeys.PALADIN_LEVEL.getQuery()).ifPresent(classLevels::setPaladin);
        dataContainer.getInt(MCDNDSimpleKeys.RANGER_LEVEL.getQuery()).ifPresent(classLevels::setRanger);
        dataContainer.getInt(MCDNDSimpleKeys.ROGUE_LEVEL.getQuery()).ifPresent(classLevels::setRogue);
        dataContainer.getInt(MCDNDSimpleKeys.SORCERER_LEVEL.getQuery()).ifPresent(classLevels::setSorcerer);
        dataContainer.getInt(MCDNDSimpleKeys.WARLOCK_LEVEL.getQuery()).ifPresent(classLevels::setWarlock);
        dataContainer.getInt(MCDNDSimpleKeys.WIZARD_LEVEL.getQuery()).ifPresent(classLevels::setWizard);
        return classLevels;
    }
}
