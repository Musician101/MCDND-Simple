package io.musician101.mcdndsimple.sponge.character.spell;

public class SaveDCTypes
{
    public static final SaveDCType ARCANE_TRICKSTER = new SaveDCType("Arcane Trickster DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType BARD = new SaveDCType("Bard DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType CLERIC = new SaveDCType("Cleric DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType DRUID = new SaveDCType("Druid DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType ELDRITCH_KNIGHT = new SaveDCType("Eldritch Knight DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType PALADIN = new SaveDCType("Paladin DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType SORCERER = new SaveDCType("Sorcerer DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType WARLOCK = new SaveDCType("Warlock DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());
    public static final SaveDCType WIZARD = new SaveDCType("Wizard DC", (abilityScore, proficiencyBonus) -> 8 + proficiencyBonus + abilityScore.getMod());

    public static SaveDCType custom(int saveDC)
    {
        return new SaveDCType("Custom DC", (abilityScore, integer) -> saveDC);
    }
}
