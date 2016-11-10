package io.musician101.mcdndsimple.common.character.bonus;

public class Bonuses
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
