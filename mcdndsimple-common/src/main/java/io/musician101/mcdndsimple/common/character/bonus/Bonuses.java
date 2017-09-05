package io.musician101.mcdndsimple.common.character.bonus;

import io.musician101.mcdndsimple.common.Dice;

public class Bonuses {

    private Dice abilitiesAndSkills = new Dice(0);
    private MeleeBonus melee = new MeleeBonus();
    private RangedBonus ranged = new RangedBonus();
    private Dice saves = new Dice(0);
    private SpellcastingBonus spellcasting = new SpellcastingBonus();

    public Dice getAbilitiesAndSkills() {
        return abilitiesAndSkills;
    }

    public void setAbilitiesAndSkills(Dice abilitiesAndSkills) {
        this.abilitiesAndSkills = abilitiesAndSkills;
    }

    public MeleeBonus getMelee() {
        return melee;
    }

    public void setMelee(MeleeBonus melee) {
        this.melee = melee;
    }

    public RangedBonus getRanged() {
        return ranged;
    }

    public void setRanged(RangedBonus ranged) {
        this.ranged = ranged;
    }

    public Dice getSaves() {
        return saves;
    }

    public void setSaves(Dice saves) {
        this.saves = saves;
    }

    public SpellcastingBonus getSpellcasting() {
        return spellcasting;
    }

    public void setSpellcasting(SpellcastingBonus spellcasting) {
        this.spellcasting = spellcasting;
    }
}
