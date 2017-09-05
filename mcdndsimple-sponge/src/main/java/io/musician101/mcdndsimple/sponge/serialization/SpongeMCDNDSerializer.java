package io.musician101.mcdndsimple.sponge.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.PlayerSheet;
import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.ClassResource;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.HitDice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.MCDNDItem;
import io.musician101.mcdndsimple.common.character.CharacterSheet;
import io.musician101.mcdndsimple.common.character.Recharge;
import io.musician101.mcdndsimple.common.character.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.Weight;
import io.musician101.mcdndsimple.common.character.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.skill.Skill;
import io.musician101.mcdndsimple.common.character.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.spell.SaveDCType;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.common.character.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.spell.SpellType;
import io.musician101.mcdndsimple.common.character.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.character.weapon.AbstractWeapon;
import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.MemoryDataContainer;

public class SpongeMCDNDSerializer extends MCDNDSerializer<DataContainer>
{
    @Override
    public DataContainer serialize(PlayerSheet playerSheet)
    {
        //TODO left off here
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.BIO_AND_INFO, serialize(playerSheet.getBioAndInfo()))
                .set(SpongeMCDNDSimpleKeys.PLAYER_SHEET, serialize(playerSheet.getCharacterSheet()))
                .set(SpongeMCDNDSimpleKeys.CLASS, playerSheet.getClazz())
                .set(SpongeMCDNDSimpleKeys.NAME, playerSheet.getName())
                .set(SpongeMCDNDSimpleKeys.RACE, playerSheet.getRace());
    }

    @Override
    protected DataContainer serialize(BioAndInfo bioAndInfo)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, bioAndInfo.getName())
                .set(SpongeMCDNDSimpleKeys.BIO, bioAndInfo.getBio());
    }

    @Override
    protected DataContainer serialize(ArmorTab armorTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.ARMOR_CLASS, armorTab.getArmorClass())
                .set(SpongeMCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, armorTab.getUnarmoredClass())
                .set(SpongeMCDNDSimpleKeys.ARMOR_LIST, serialize(armorTab.getArmorList(), this::serialize))
                .set(SpongeMCDNDSimpleKeys.UNARMORED_BONUS, serialize(armorTab.getUnarmoredBonus()));
    }

    @Override
    protected DataContainer serialize(Armor armor)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.SPEED_PENALTY, armor.hasSpeedPenalty())
                .set(SpongeMCDNDSimpleKeys.STEALTH_PENALTY, armor.hasStealthPenalty())
                .set(SpongeMCDNDSimpleKeys.UNARMORED, armor.isUnarmored())
                .set(SpongeMCDNDSimpleKeys.WORN, armor.isWorn())
                .set(SpongeMCDNDSimpleKeys.BASE_ARMOR_CLASS, armor.getBaseArmorClass())
                .set(SpongeMCDNDSimpleKeys.MAGIC_BONUS, armor.getMagicBonus())
                .set(SpongeMCDNDSimpleKeys.ARMOR_TYPE, serialize(armor.getArmorType()))
                .set(SpongeMCDNDSimpleKeys.NAME, armor.getName());
    }

    @Override
    protected DataContainer serialize(UnarmoredBonus unarmoredBonus)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, unarmoredBonus.getName());
    }

    @Override
    protected DataContainer serialize(BackgroundTab backgroundTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.WEIGHT_DOUBLE, backgroundTab.getWeight())
                .set(SpongeMCDNDSimpleKeys.AGE, backgroundTab.getAge())
                .set(SpongeMCDNDSimpleKeys.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies())
                .set(SpongeMCDNDSimpleKeys.BACKGROUND, backgroundTab.getBackground())
                .set(SpongeMCDNDSimpleKeys.BONDS, backgroundTab.getBonds())
                .set(SpongeMCDNDSimpleKeys.FLAWS, backgroundTab.getFlaws())
                .set(SpongeMCDNDSimpleKeys.IDEALS, backgroundTab.getIdeals())
                .set(SpongeMCDNDSimpleKeys.OTHER_NOTES, backgroundTab.getOtherNotes())
                .set(SpongeMCDNDSimpleKeys.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits())
                .set(SpongeMCDNDSimpleKeys.RACIAL_TRAITS, backgroundTab.getRacialTraits())
                .set(SpongeMCDNDSimpleKeys.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies())
                .set(SpongeMCDNDSimpleKeys.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies())
                .set(SpongeMCDNDSimpleKeys.ALIGNMENT, backgroundTab.getAlignment())
                .set(SpongeMCDNDSimpleKeys.EYES, backgroundTab.getEyes())
                .set(SpongeMCDNDSimpleKeys.GENDER, backgroundTab.getGender())
                .set(SpongeMCDNDSimpleKeys.HAIR, backgroundTab.getHair())
                .set(SpongeMCDNDSimpleKeys.HEIGHT, backgroundTab.getHeight())
                .set(SpongeMCDNDSimpleKeys.LANGUAGES, backgroundTab.getLanguages())
                .set(SpongeMCDNDSimpleKeys.SIZE, backgroundTab.getSize())
                .set(SpongeMCDNDSimpleKeys.VISION, backgroundTab.getVision());
    }

    @Override
    protected DataContainer serialize(ClassTab classTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.CLASS_LEVELS, serialize(classTab.getClassLevels()))
                .set(SpongeMCDNDSimpleKeys.CLASS_ACTIONS, serialize(classTab.getClassActions(), this::serialize))
                .set(SpongeMCDNDSimpleKeys.CLASS_RESOURCES, serialize(classTab.getClassResources(), this::serialize))
                .set(SpongeMCDNDSimpleKeys.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes())
                .set(SpongeMCDNDSimpleKeys.OTHER_NOTES, classTab.getOtherNotes());
    }

    @Override
    protected DataContainer serialize(ClassLevels classLevels)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.BARBARIAN_LEVEL, classLevels.getBarbarian())
                .set(SpongeMCDNDSimpleKeys.BARD_LEVEL, classLevels.getBard())
                .set(SpongeMCDNDSimpleKeys.CLERIC_LEVEL, classLevels.getCleric())
                .set(SpongeMCDNDSimpleKeys.DRUID_LEVEL, classLevels.getDruid())
                .set(SpongeMCDNDSimpleKeys.FIGHTER_LEVEL, classLevels.getFighter())
                .set(SpongeMCDNDSimpleKeys.MONK_LEVEL, classLevels.getMonk())
                .set(SpongeMCDNDSimpleKeys.PALADIN_LEVEL, classLevels.getPaladin())
                .set(SpongeMCDNDSimpleKeys.RANGER_LEVEL, classLevels.getRanger())
                .set(SpongeMCDNDSimpleKeys.ROGUE_LEVEL, classLevels.getRogue())
                .set(SpongeMCDNDSimpleKeys.SORCERER_LEVEL, classLevels.getSorcerer())
                .set(SpongeMCDNDSimpleKeys.WARLOCK_LEVEL, classLevels.getWarlock())
                .set(SpongeMCDNDSimpleKeys.WIZARD_LEVEL, classLevels.getWizard());
    }

    @Override
    protected DataContainer serialize(ClassAction classAction)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.MAX_USES, classAction.getMax())
                .set(SpongeMCDNDSimpleKeys.USES_LEFT, classAction.getUsedCharges())
                .set(SpongeMCDNDSimpleKeys.RECHARGE, serialize(classAction.getRecharge()))
                .set(SpongeMCDNDSimpleKeys.GAINED_FROM, classAction.getGainedFrom())
                .set(SpongeMCDNDSimpleKeys.NAME, classAction.getName());
    }

    @Override
    protected DataContainer serialize(ClassResource classResource)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.USES_LEFT, classResource.getUsesLeft())
                .set(SpongeMCDNDSimpleKeys.MAX_USES, classResource.getMaxUses())
                .set(SpongeMCDNDSimpleKeys.RECHARGE, serialize(classResource.getRecharge()))
                .set(SpongeMCDNDSimpleKeys.NAME, classResource.getName());
    }

    @Override
    protected DataContainer serialize(CoreStatsTab coreStatsTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.BONUSES, serialize(coreStatsTab.getBonuses()))
                .set(SpongeMCDNDSimpleKeys.CORE_STATS, serialize(coreStatsTab.getCoreStats()))
                .set(SpongeMCDNDSimpleKeys.EXPERIENCE, serialize(coreStatsTab.getExperience()))
                .set(SpongeMCDNDSimpleKeys.HIT_DICE, serialize(coreStatsTab.getHitDice()))
                .set(SpongeMCDNDSimpleKeys.HIT_POINTS, serialize(coreStatsTab.getHitPoints()))
                .set(SpongeMCDNDSimpleKeys.INITIATIVE_BONUS, coreStatsTab.getInitiativeBonus())
                .set(SpongeMCDNDSimpleKeys.SPEED, coreStatsTab.getSpeed());
    }

    @Override
    protected DataContainer serialize(Bonuses bonuses)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.MELEE_BONUS, serialize(bonuses.getMelee()))
                .set(SpongeMCDNDSimpleKeys.RANGED_BONUS, serialize(bonuses.getRanged()))
                .set(SpongeMCDNDSimpleKeys.SPELLCASTING_BONUS, serialize(bonuses.getSpellcasting()))
                .set(SpongeMCDNDSimpleKeys.SAVES, bonuses.getSaves())
                .set(SpongeMCDNDSimpleKeys.ABILITIES_AND_SKILLS, bonuses.getAbilitiesAndSkills());
    }

    @Override
    protected DataContainer serialize(MeleeBonus meleeBonus)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.ATTACK, meleeBonus.getAttack())
                .set(SpongeMCDNDSimpleKeys.DAMAGE, meleeBonus.getDamage());
    }

    @Override
    protected DataContainer serialize(RangedBonus rangedBonus)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.ATTACK, rangedBonus.getAttack())
                .set(SpongeMCDNDSimpleKeys.DAMAGE, rangedBonus.getDamage());
    }

    @Override
    protected DataContainer serialize(SpellcastingBonus spellcastingBonus)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.ATTACK, spellcastingBonus.getAttack())
                .set(SpongeMCDNDSimpleKeys.DAMAGE, spellcastingBonus.getDamage())
                .set(SpongeMCDNDSimpleKeys.SAVE_DC, spellcastingBonus.getSaveDC());
    }

    @Override
    protected DataContainer serialize(CoreStats coreStats)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.CHARISMA_SCORE, serialize(coreStats.getCharisma()))
                .set(SpongeMCDNDSimpleKeys.CONSTITUTION_SCORE, serialize(coreStats.getConstitution()))
                .set(SpongeMCDNDSimpleKeys.DEXTERITY_SCORE, serialize(coreStats.getDexterity()))
                .set(SpongeMCDNDSimpleKeys.INTELLIGENCE_SCORE, serialize(coreStats.getIntelligence()))
                .set(SpongeMCDNDSimpleKeys.STRENGTH_SCORE, serialize(coreStats.getStrength()))
                .set(SpongeMCDNDSimpleKeys.WISDOM_SCORE, serialize(coreStats.getWisdom()));
    }

    @Override
    protected DataContainer serialize(AbilityScore abilityScore)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.PROFICIENT, abilityScore.isProficient())
                .set(SpongeMCDNDSimpleKeys.SCORE, abilityScore.getScore())
                .set(SpongeMCDNDSimpleKeys.SHORT_NAME, abilityScore.getShortName())
                .set(SpongeMCDNDSimpleKeys.NAME, abilityScore.getName());
    }

    @Override
    protected DataContainer serialize(Experience experience)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.EXPERIENCE_AMOUNT, experience.getXP())
                .set(SpongeMCDNDSimpleKeys.OVERALL_LEVEL, experience.getOverallLevel());
    }

    @Override
    protected DataContainer serialize(HitDice hitDice)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.HIT_DICE_MAP, hitDice.getHitDice());
    }

    @Override
    protected DataContainer serialize(Dice dice)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.AMOUNT, dice.getAmount())
                .set(SpongeMCDNDSimpleKeys.SIDES, dice.getSides());
    }

    @Override
    protected DataContainer serialize(HitPoints hitPoints)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.CURRENT_HP, hitPoints.getCurrent())
                .set(SpongeMCDNDSimpleKeys.MAX_HP, hitPoints.getMax())
                .set(SpongeMCDNDSimpleKeys.TEMP_HP, hitPoints.getTemp());
    }

    @Override
    protected DataContainer serialize(InventoryTab inventoryTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.INVENTORY, serialize(inventoryTab.getInventory(), this::serialize))
                .set(SpongeMCDNDSimpleKeys.INVENTORY_NOTES, inventoryTab.getInventoryNotes())
                .set(SpongeMCDNDSimpleKeys.WEALTH, serialize(inventoryTab.getWealth()))
                .set(SpongeMCDNDSimpleKeys.WEIGHT_CLASS, serialize(inventoryTab.getWeight()));
    }

    @Override
    protected DataContainer serialize(MCDNDItem item)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.CARRIED, item.isCarried())
                .set(SpongeMCDNDSimpleKeys.WEIGHT_DOUBLE, item.getWeight())
                .set(SpongeMCDNDSimpleKeys.DESCRIPTION, item.getDescription())
                .set(SpongeMCDNDSimpleKeys.NAME, item.getName());
    }

    @Override
    protected DataContainer serialize(Wealth wealth)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.COPPER, serialize(wealth.getCopper()))
                .set(SpongeMCDNDSimpleKeys.ELECTRUM, serialize(wealth.getElectrum()))
                .set(SpongeMCDNDSimpleKeys.GOLD, serialize(wealth.getGold()))
                .set(SpongeMCDNDSimpleKeys.PLATINUM, serialize(wealth.getPlatinum()))
                .set(SpongeMCDNDSimpleKeys.SILVER, serialize(wealth.getSilver()));
    }

    @Override
    protected DataContainer serialize(Coin coin)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, coin.getName())
                .set(SpongeMCDNDSimpleKeys.AMOUNT, coin.getAmount())
                .set(SpongeMCDNDSimpleKeys.SHORT_NAME, coin.getShortName());
    }

    @Override
    protected DataContainer serialize(Weight weight)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.OTHER, weight.getOther());
    }

    @Override
    protected DataContainer serialize(SkillsTab skillsTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.ACROBATICS, serialize(skillsTab.getAcrobatics()))
                .set(SpongeMCDNDSimpleKeys.ANIMAL_HANDLING, serialize(skillsTab.getAnimalHandling()))
                .set(SpongeMCDNDSimpleKeys.ARCANA, serialize(skillsTab.getArcana()))
                .set(SpongeMCDNDSimpleKeys.ATHLETICS, serialize(skillsTab.getAthletics()))
                .set(SpongeMCDNDSimpleKeys.DECEPTION, serialize(skillsTab.getDeception()))
                .set(SpongeMCDNDSimpleKeys.HISTORY, serialize(skillsTab.getHistory()))
                .set(SpongeMCDNDSimpleKeys.INSIGHT, serialize(skillsTab.getInsight()))
                .set(SpongeMCDNDSimpleKeys.INTIMIDATION, serialize(skillsTab.getIntimidation()))
                .set(SpongeMCDNDSimpleKeys.INVESTIGATION, serialize(skillsTab.getInvestigation()))
                .set(SpongeMCDNDSimpleKeys.MEDICINE, serialize(skillsTab.getMedicine()))
                .set(SpongeMCDNDSimpleKeys.NATURE, serialize(skillsTab.getNature()))
                .set(SpongeMCDNDSimpleKeys.PERFORMANCE, serialize(skillsTab.getPerformance()))
                .set(SpongeMCDNDSimpleKeys.PERCEPTION, serialize(skillsTab.getPerception()))
                .set(SpongeMCDNDSimpleKeys.PERSUASION, serialize(skillsTab.getPersuasion()))
                .set(SpongeMCDNDSimpleKeys.RELIGION, serialize(skillsTab.getReligion()))
                .set(SpongeMCDNDSimpleKeys.SLEIGHT_OF_HAND, serialize(skillsTab.getSleightOfHand()))
                .set(SpongeMCDNDSimpleKeys.STEALTH, serialize(skillsTab.getStealth()))
                .set(SpongeMCDNDSimpleKeys.SURVIVAL, serialize(skillsTab.getSurvival()))
                .set(SpongeMCDNDSimpleKeys.UNSKILLED_CHA, serialize(skillsTab.getUnskilledCHA()))
                .set(SpongeMCDNDSimpleKeys.UNSKILLED_CON, serialize(skillsTab.getUnskilledCON()))
                .set(SpongeMCDNDSimpleKeys.UNSKILLED_DEX, serialize(skillsTab.getUnskilledDEX()))
                .set(SpongeMCDNDSimpleKeys.UNSKILLED_INT, serialize(skillsTab.getUnskilledINT()))
                .set(SpongeMCDNDSimpleKeys.UNSKILLED_STR, serialize(skillsTab.getUnskilledSTR()))
                .set(SpongeMCDNDSimpleKeys.UNSKILLED_WIS, serialize(skillsTab.getUnskilledWIS()));
    }

    @Override
    protected DataContainer serialize(Skill skill)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY, serialize(skill.getSkillProficiency()));
    }

    @Override
    protected DataContainer serialize(SkillProficiency skillProficiency)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, skillProficiency.getName());
    }

    @Override
    protected DataContainer serialize(SpellbookTab spellbookTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.INVOCATION_COUNT, spellbookTab.getInvocations())
                .set(SpongeMCDNDSimpleKeys.SORCERY_POINTS, spellbookTab.getSorceryPointsMax())
                .set(SpongeMCDNDSimpleKeys.SPELLS, serialize(spellbookTab.getSpells(), this::serialize))
                .set(SpongeMCDNDSimpleKeys.SPELLCASTER_CLASSES, serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
    }

    @Override
    protected DataContainer serialize(Spell spell)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NEEDS_CONCENTRATION, spell.needsConcentration())
                .set(SpongeMCDNDSimpleKeys.IS_PREPARED, spell.isPrepared())
                .set(SpongeMCDNDSimpleKeys.DURATION, spell.getDuration())
                .set(SpongeMCDNDSimpleKeys.SPELL_LEVEL, spell.getLevel())
                .set(SpongeMCDNDSimpleKeys.RANGE, spell.getRange())
                .set(SpongeMCDNDSimpleKeys.SPELL_DAMAGE, serialize(spell.getSpellDamage()))
                .set(SpongeMCDNDSimpleKeys.SPELL_HEALING, serialize(spell.getSpellHealing()))
                .set(SpongeMCDNDSimpleKeys.SPELL_DESCRIPTION, spell.getDescription())
                .set(SpongeMCDNDSimpleKeys.EFFECTS, spell.getEffects())
                .set(SpongeMCDNDSimpleKeys.SPELL_SAVE, serialize(spell.getSpellSave()))
                .set(SpongeMCDNDSimpleKeys.SPELLCASTER_CLASS, serialize(spell.getGainedFrom()))
                .set(SpongeMCDNDSimpleKeys.SPELL_TYPE, serialize(spell.getSpellType()))
                .set(SpongeMCDNDSimpleKeys.ATTACK_STAT, spell.getAttackStat())
                .set(SpongeMCDNDSimpleKeys.CAST_TIME, spell.getCastTime())
                .set(SpongeMCDNDSimpleKeys.TARGET_AREA, spell.getTargetArea());
    }

    @Override
    protected DataContainer serialize(SpellDamage spellDamage)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.CAN_CRIT, spellDamage.canCrit())
                .set(SpongeMCDNDSimpleKeys.DICE, serialize(spellDamage.getDice()))
                .set(SpongeMCDNDSimpleKeys.BONUS, spellDamage.getBonus())
                .set(SpongeMCDNDSimpleKeys.DAMAGE_TYPE, spellDamage.getDamageType());
    }

    @Override
    protected DataContainer serialize(SpellHealing spellHealing)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.HEAL_AMOUNT, spellHealing.getHealAmount())
                .set(SpongeMCDNDSimpleKeys.STAT_BONUS, spellHealing.getStatBonus());
    }

    @Override
    protected DataContainer serialize(SpellSave spellSave)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.SAVE_DC_TYPE, serialize(spellSave.getSpellcasterClass()))
                .set(SpongeMCDNDSimpleKeys.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave())
                .set(SpongeMCDNDSimpleKeys.SAVING_STAT, spellSave.getSavingStat());
    }

    @Override
    protected DataContainer serialize(SaveDCType saveDCType)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.CUSTOM_DC, saveDCType.getSaveDC(null, 0))
                .set(SpongeMCDNDSimpleKeys.NAME, saveDCType.getName());
    }

    @Override
    protected DataContainer serialize(SpellcasterClass spellcasterClass)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, spellcasterClass.getName());
    }

    @Override
    protected DataContainer serialize(SpellType spellType)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, spellType.getName());
    }

    @Override
    protected DataContainer serialize(WeaponsTab weaponsTab)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.MELEE_WEAPONS, serialize(weaponsTab.getMeleeWeapons(), this::serialize))
                .set(SpongeMCDNDSimpleKeys.RANGED_WEAPONS, serialize(weaponsTab.getRangedWeapons(), this::serialize));
    }

    @Override
    protected DataContainer serialize(AbstractWeapon weapon, DataContainer weaponData)
    {
        return weaponData.set(SpongeMCDNDSimpleKeys.IS_PROFICIENT, weapon.isProficient())
                .set(SpongeMCDNDSimpleKeys.CRIT_DAMAGE_DICE, serialize(weapon.getCritDamageDice()))
                .set(SpongeMCDNDSimpleKeys.DAMAGE_DICE, serialize(weapon.getDamageDice()))
                .set(SpongeMCDNDSimpleKeys.CRIT_MINIMUM, weapon.getCritMin())
                .set(SpongeMCDNDSimpleKeys.DAMAGE_BONUS, weapon.getDamageBonus())
                .set(SpongeMCDNDSimpleKeys.MAGIC_BONUS, weapon.getMagicBonus())
                .set(SpongeMCDNDSimpleKeys.TO_HIT, weapon.getToHit())
                .set(SpongeMCDNDSimpleKeys.ATTACK_STAT, weapon.getAttackStat())
                .set(SpongeMCDNDSimpleKeys.NAME, weapon.getName());
    }

    @Override
    protected DataContainer serialize(MeleeWeapon meleeWeapon)
    {
        return serialize(meleeWeapon, new MemoryDataContainer())
                .set(SpongeMCDNDSimpleKeys.PLUS_STAT, meleeWeapon.isPlusStat());
    }

    @Override
    protected DataContainer serialize(RangedWeapon rangedWeapon)
    {
        return serialize(rangedWeapon, new MemoryDataContainer())
                .set(SpongeMCDNDSimpleKeys.AMMO, rangedWeapon.getAmmo());
    }

    @Override
    protected DataContainer serialize(CharacterSheet characterSheet)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.ARMOR_TAB, serialize(characterSheet.getArmorTab()))
                .set(SpongeMCDNDSimpleKeys.BACKGROUND_TAB, serialize(characterSheet.getBackgroundTab()))
                .set(SpongeMCDNDSimpleKeys.CLASS_TAB, serialize(characterSheet.getClassTab()))
                .set(SpongeMCDNDSimpleKeys.CORE_STATS_TAB, serialize(characterSheet.getCoreStatsTab()))
                .set(SpongeMCDNDSimpleKeys.INVENTORY_TAB, serialize(characterSheet.getInventoryTab()))
                .set(SpongeMCDNDSimpleKeys.SKILLS_TAB, serialize(characterSheet.getSkillsTab()))
                .set(SpongeMCDNDSimpleKeys.SPELL_BOOK_TAB, serialize(characterSheet.getSpellbookTab()))
                .set(SpongeMCDNDSimpleKeys.WEAPONS_TAB, serialize(characterSheet.getWeaponsTab()));
    }

    @Override
    protected DataContainer serialize(ArmorType armorType)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, armorType.getName());
    }

    @Override
    protected DataContainer serialize(Recharge recharge)
    {
        return new MemoryDataContainer()
                .set(SpongeMCDNDSimpleKeys.NAME, recharge.getName());
    }
}
