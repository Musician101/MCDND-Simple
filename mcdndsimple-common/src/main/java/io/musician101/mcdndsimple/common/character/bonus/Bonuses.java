package io.musician101.mcdndsimple.common.character.bonus;

import io.musician101.mcdndsimple.common.Dice;

public class Bonuses
{
    private MeleeBonus melee = new MeleeBonus();
    private RangedBonus ranged = new RangedBonus();
    private SpellcastingBonus spellcasting = new SpellcastingBonus();
    private Dice saves = new Dice(0);
    private Dice abilitiesAndSkills = new Dice(0);

    public Dice getAbilitiesAndSkills()
    {
        return abilitiesAndSkills;
    }

    public MeleeBonus getMelee()
    {
        return melee;
    }

    public RangedBonus getRanged()
    {
        return ranged;
    }

    public Dice getSaves()
    {
        return saves;
    }

    public SpellcastingBonus getSpellcasting()
    {
        return spellcasting;
    }

    public void setAbilitiesAndSkills(Dice abilitiesAndSkills)
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

    public void setSaves(Dice saves)
    {
        this.saves = saves;
    }

    public void setSpellcasting(SpellcastingBonus spellcasting)
    {
        this.spellcasting = spellcasting;
    }
}
