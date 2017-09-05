package io.musician101.mcdndsimple.common.character.spell;

public class SaveDCTypes
{
    public static final SaveDCType ARCANE_TRICKSTER = new SaveDCType("Arcane Trickster DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getIntelligence().getMod());
    public static final SaveDCType BARD = new SaveDCType("Bard DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getCharisma().getMod());
    public static final SaveDCType CLERIC = new SaveDCType("Cleric DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getWisdom().getMod());
    public static final SaveDCType DRUID = new SaveDCType("Druid DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getWisdom().getMod());
    public static final SaveDCType ELDRITCH_KNIGHT = new SaveDCType("Eldritch Knight DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getIntelligence().getMod());
    public static final SaveDCType PALADIN = new SaveDCType("Paladin DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getCharisma().getMod());
    public static final SaveDCType SORCERER = new SaveDCType("Sorcerer DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getCharisma().getMod());
    public static final SaveDCType WARLOCK = new SaveDCType("Warlock DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getCharisma().getMod());
    public static final SaveDCType WIZARD = new SaveDCType("Wizard DC", (coreStats, proficiencyBonus) -> 8 + proficiencyBonus + coreStats.getIntelligence().getMod());

    public static SaveDCType custom(int saveDC)
    {
        return new SaveDCType("Custom DC", (abilityScore, integer) -> saveDC);
    }
}
