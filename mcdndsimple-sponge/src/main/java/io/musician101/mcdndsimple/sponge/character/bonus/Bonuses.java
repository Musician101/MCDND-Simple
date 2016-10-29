package io.musician101.mcdndsimple.sponge.character.bonus;

import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;

import javax.annotation.Nonnull;

public class Bonuses implements DataSerializable
{
    private MeleeBonus melee = new MeleeBonus();
    private RangedBonus ranged = new RangedBonus();
    private SpellcastingBonus spellcasting = new SpellcastingBonus();
    private int saves = 0;
    private int abilitiesAndSkills = 0;

    public int getAbilitiesAndSkills()
    {
        return abilitiesAndSkills;
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
                .set(MCDNDSimpleKeys.MELEE_BONUS, melee.toContainer())
                .set(MCDNDSimpleKeys.RANGED_BONUS, ranged.toContainer())
                .set(MCDNDSimpleKeys.SPELLCASTING_BONUS, spellcasting.toContainer())
                .set(MCDNDSimpleKeys.SAVES, saves)
                .set(MCDNDSimpleKeys.ABILITIES_AND_SKILLS, abilitiesAndSkills);
    }

    public MeleeBonus getMelee()
    {
        return melee;
    }

    public RangedBonus getRanged()
    {
        return ranged;
    }

    public int getSaves()
    {
        return saves;
    }

    public SpellcastingBonus getSpellcasting()
    {
        return spellcasting;
    }

    public void setAbilitiesAndSkills(int abilitiesAndSkills)
    {
        this.abilitiesAndSkills = abilitiesAndSkills;
    }

    public void setMelee(MeleeBonus melee)
    {
        this.melee = melee;
    }

    public void setRanged(RangedBonus ranged)
    {
        this.ranged = ranged;
    }

    public void setSaves(int saves)
    {
        this.saves = saves;
    }

    public void setSpellcasting(SpellcastingBonus spellcasting)
    {
        this.spellcasting = spellcasting;
    }
}
