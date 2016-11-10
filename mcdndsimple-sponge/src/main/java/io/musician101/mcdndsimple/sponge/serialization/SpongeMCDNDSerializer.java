package io.musician101.mcdndsimple.sponge.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.CharacterSheet;
import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.ClassResource;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.HitDice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.MCDNDItem;
import io.musician101.mcdndsimple.common.character.PlayerSheet;
import io.musician101.mcdndsimple.common.character.Recharge;
import io.musician101.mcdndsimple.common.character.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.Weight;
import io.musician101.mcdndsimple.common.character.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.equipment.armor.MCDNDArmorType;
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
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.MemoryDataContainer;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpongeMCDNDSerializer extends MCDNDSerializer<DataContainer>
{

    @Override
    public DataContainer serialize(CharacterSheet characterSheet)
    {
        //TODO left off here
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.BIO_AND_INFO, serialize(characterSheet.getBioAndInfo()))
                .set(MCDNDSimpleKeys.PLAYER_SHEET, serialize(characterSheet.getPlayerSheet()))
                .set(MCDNDSimpleKeys.CLASS, characterSheet.getClazz())
                .set(MCDNDSimpleKeys.NAME, characterSheet.getName())
                .set(MCDNDSimpleKeys.RACE, characterSheet.getRace());
    }

    @Override
    protected DataContainer serialize(BioAndInfo bioAndInfo)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, bioAndInfo.getName())
                .set(MCDNDSimpleKeys.BIO, bioAndInfo.getBio());
    }

    @Override
    protected DataContainer serialize(ArmorTab armorTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.ARMOR_CLASS, armorTab.getArmorClass())
                .set(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, armorTab.getUnarmoredClass())
                .set(MCDNDSimpleKeys.ARMOR_LIST, serialize(armorTab.getArmorList(), this::serialize))
                .set(MCDNDSimpleKeys.UNARMORED_BONUS, serialize(armorTab.getUnarmoredBonus()));
    }

    @Override
    protected DataContainer serialize(Armor armor)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.SPEED_PENALTY, armor.hasSpeedPenalty())
                .set(MCDNDSimpleKeys.STEALTH_PENALTY, armor.hasStealthPenalty())
                .set(MCDNDSimpleKeys.UNARMORED, armor.isUnarmored())
                .set(MCDNDSimpleKeys.WORN, armor.isWorn())
                .set(MCDNDSimpleKeys.BASE_ARMOR_CLASS, armor.getBaseArmorClass())
                .set(MCDNDSimpleKeys.MAGIC_BONUS, armor.getMagicBonus())
                .set(MCDNDSimpleKeys.ARMOR_TYPE, serialize(armor.getArmorType()))
                .set(MCDNDSimpleKeys.NAME, armor.getName());
    }

    @Override
    protected DataContainer serialize(UnarmoredBonus unarmoredBonus)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, unarmoredBonus.getName());
    }

    @Override
    protected DataContainer serialize(BackgroundTab backgroundTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.WEIGHT_DOUBLE, backgroundTab.getWeight())
                .set(MCDNDSimpleKeys.AGE, backgroundTab.getAge())
                .set(MCDNDSimpleKeys.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies())
                .set(MCDNDSimpleKeys.BACKGROUND, backgroundTab.getBackground())
                .set(MCDNDSimpleKeys.BONDS, backgroundTab.getBonds())
                .set(MCDNDSimpleKeys.FLAWS, backgroundTab.getFlaws())
                .set(MCDNDSimpleKeys.IDEALS, backgroundTab.getIdeals())
                .set(MCDNDSimpleKeys.OTHER_NOTES, backgroundTab.getOtherNotes())
                .set(MCDNDSimpleKeys.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits())
                .set(MCDNDSimpleKeys.RACIAL_TRAITS, backgroundTab.getRacialTraits())
                .set(MCDNDSimpleKeys.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies())
                .set(MCDNDSimpleKeys.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies())
                .set(MCDNDSimpleKeys.ALIGNMENT, backgroundTab.getAlignment())
                .set(MCDNDSimpleKeys.EYES, backgroundTab.getEyes())
                .set(MCDNDSimpleKeys.GENDER, backgroundTab.getGender())
                .set(MCDNDSimpleKeys.HAIR, backgroundTab.getHair())
                .set(MCDNDSimpleKeys.HEIGHT, backgroundTab.getHeight())
                .set(MCDNDSimpleKeys.LANGUAGES, backgroundTab.getLanguages())
                .set(MCDNDSimpleKeys.SIZE, backgroundTab.getSize())
                .set(MCDNDSimpleKeys.VISION, backgroundTab.getVision());
    }

    @Override
    protected DataContainer serialize(ClassTab classTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CLASS_LEVELS, serialize(classTab.getClassLevels()))
                .set(MCDNDSimpleKeys.CLASS_ACTIONS, serialize(classTab.getClassActions(), this::serialize))
                .set(MCDNDSimpleKeys.CLASS_RESOURCES, serialize(classTab.getClassResources(), this::serialize))
                .set(MCDNDSimpleKeys.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes())
                .set(MCDNDSimpleKeys.OTHER_NOTES, classTab.getOtherNotes());
    }

    @Override
    protected DataContainer serialize(ClassLevels classLevels)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.BARBARIAN_LEVEL, classLevels.getBarbarian())
                .set(MCDNDSimpleKeys.BARD_LEVEL, classLevels.getBard())
                .set(MCDNDSimpleKeys.CLERIC_LEVEL, classLevels.getCleric())
                .set(MCDNDSimpleKeys.DRUID_LEVEL, classLevels.getDruid())
                .set(MCDNDSimpleKeys.FIGHTER_LEVEL, classLevels.getFighter())
                .set(MCDNDSimpleKeys.MONK_LEVEL, classLevels.getMonk())
                .set(MCDNDSimpleKeys.PALADIN_LEVEL, classLevels.getPaladin())
                .set(MCDNDSimpleKeys.RANGER_LEVEL, classLevels.getRanger())
                .set(MCDNDSimpleKeys.ROGUE_LEVEL, classLevels.getRogue())
                .set(MCDNDSimpleKeys.SORCERER_LEVEL, classLevels.getSorcerer())
                .set(MCDNDSimpleKeys.WARLOCK_LEVEL, classLevels.getWarlock())
                .set(MCDNDSimpleKeys.WIZARD_LEVEL, classLevels.getWizard());
    }

    @Override
    protected DataContainer serialize(ClassAction classAction)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.MAX_USES, classAction.getMax())
                .set(MCDNDSimpleKeys.USES_LEFT, classAction.getUsesLeft())
                .set(MCDNDSimpleKeys.RECHARGE, serialize(classAction.getRecharge()))
                .set(MCDNDSimpleKeys.GAINED_FROM, classAction.getGainedFrom())
                .set(MCDNDSimpleKeys.NAME, classAction.getName());
    }

    @Override
    protected DataContainer serialize(ClassResource classResource)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.USES_LEFT, classResource.getUsesLeft())
                .set(MCDNDSimpleKeys.MAX_USES, classResource.getMaxUses())
                .set(MCDNDSimpleKeys.RECHARGE, serialize(classResource.getRecharge()))
                .set(MCDNDSimpleKeys.NAME, classResource.getName());
    }

    @Override
    protected DataContainer serialize(CoreStatsTab coreStatsTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.BONUSES, serialize(coreStatsTab.getBonuses()))
                .set(MCDNDSimpleKeys.CORE_STATS, serialize(coreStatsTab.getCoreStats()))
                .set(MCDNDSimpleKeys.EXPERIENCE, serialize(coreStatsTab.getExperience()))
                .set(MCDNDSimpleKeys.HIT_DICE, serialize(coreStatsTab.getHitDice()))
                .set(MCDNDSimpleKeys.HIT_POINTS, serialize(coreStatsTab.getHitPoints()))
                .set(MCDNDSimpleKeys.INITIATIVE_BONUS, coreStatsTab.getInitiativeBonus())
                .set(MCDNDSimpleKeys.SPEED, coreStatsTab.getSpeed());
    }

    @Override
    protected DataContainer serialize(Bonuses bonuses)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.MELEE_BONUS, serialize(bonuses.getMelee()))
                .set(MCDNDSimpleKeys.RANGED_BONUS, serialize(bonuses.getRanged()))
                .set(MCDNDSimpleKeys.SPELLCASTING_BONUS, serialize(bonuses.getSpellcasting()))
                .set(MCDNDSimpleKeys.SAVES, bonuses.getSaves())
                .set(MCDNDSimpleKeys.ABILITIES_AND_SKILLS, bonuses.getAbilitiesAndSkills());
    }

    @Override
    protected DataContainer serialize(MeleeBonus meleeBonus)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.ATTACK, meleeBonus.getAttack())
                .set(MCDNDSimpleKeys.DAMAGE, meleeBonus.getDamage());
    }

    @Override
    protected DataContainer serialize(RangedBonus rangedBonus)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.ATTACK, rangedBonus.getAttack())
                .set(MCDNDSimpleKeys.DAMAGE, rangedBonus.getDamage());
    }

    @Override
    protected DataContainer serialize(SpellcastingBonus spellcastingBonus)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.ATTACK, spellcastingBonus.getAttack())
                .set(MCDNDSimpleKeys.DAMAGE, spellcastingBonus.getDamage())
                .set(MCDNDSimpleKeys.SAVE_DC, spellcastingBonus.getSaveDC());
    }

    @Override
    protected DataContainer serialize(CoreStats coreStats)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CHARISMA_SCORE, serialize(coreStats.getCharisma()))
                .set(MCDNDSimpleKeys.CONSTITUTION_SCORE, serialize(coreStats.getConstitution()))
                .set(MCDNDSimpleKeys.DEXTERITY_SCORE, serialize(coreStats.getDexterity()))
                .set(MCDNDSimpleKeys.INTELLIGENCE_SCORE, serialize(coreStats.getIntelligence()))
                .set(MCDNDSimpleKeys.STRENGTH_SCORE, serialize(coreStats.getStrength()))
                .set(MCDNDSimpleKeys.WISDOM_SCORE, serialize(coreStats.getWisdom()));
    }

    @Override
    protected DataContainer serialize(AbilityScore abilityScore)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.PROFICIENT, abilityScore.isProficient())
                .set(MCDNDSimpleKeys.SCORE, abilityScore.getScore())
                .set(MCDNDSimpleKeys.SHORT_NAME, abilityScore.getShortName())
                .set(MCDNDSimpleKeys.NAME, abilityScore.getName());
    }

    @Override
    protected DataContainer serialize(Experience experience)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.EXPERIENCE_AMOUNT, experience.getExp())
                .set(MCDNDSimpleKeys.OVERALL_LEVEL, experience.getOverallLevel());
    }

    @Override
    protected DataContainer serialize(HitDice hitDice)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.HIT_DICE_MAP, hitDice.getHitDice());
    }

    @Override
    protected DataContainer serialize(Dice dice)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.AMOUNT, dice.getAmount())
                .set(MCDNDSimpleKeys.SIDES, dice.getSides());
    }

    @Override
    protected DataContainer serialize(HitPoints hitPoints)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CURRENT_HP, hitPoints.getCurrent())
                .set(MCDNDSimpleKeys.MAX_HP, hitPoints.getMax())
                .set(MCDNDSimpleKeys.TEMP_HP, hitPoints.getTemp());
    }

    @Override
    protected DataContainer serialize(InventoryTab inventoryTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.INVENTORY, serialize(inventoryTab.getInventory(), this::serialize))
                .set(MCDNDSimpleKeys.INVENTORY_NOTES, inventoryTab.getInventoryNotes())
                .set(MCDNDSimpleKeys.WEALTH, serialize(inventoryTab.getWealth()))
                .set(MCDNDSimpleKeys.WEIGHT_CLASS, serialize(inventoryTab.getWeight()));
    }

    @Override
    protected DataContainer serialize(MCDNDItem item)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CARRIED, item.isCarried())
                .set(MCDNDSimpleKeys.WEIGHT_DOUBLE, item.getWeight())
                .set(MCDNDSimpleKeys.DESCRIPTION, item.getDescription())
                .set(MCDNDSimpleKeys.NAME, item.getName());
    }

    @Override
    protected DataContainer serialize(Wealth wealth)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.COPPER, serialize(wealth.getCopper()))
                .set(MCDNDSimpleKeys.ELECTRUM, serialize(wealth.getElectrum()))
                .set(MCDNDSimpleKeys.GOLD, serialize(wealth.getGold()))
                .set(MCDNDSimpleKeys.PLATINUM, serialize(wealth.getPlatinum()))
                .set(MCDNDSimpleKeys.SILVER, serialize(wealth.getSilver()));
    }

    @Override
    protected DataContainer serialize(Coin coin)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, coin.getName())
                .set(MCDNDSimpleKeys.AMOUNT, coin.getAmount())
                .set(MCDNDSimpleKeys.SHORT_NAME, coin.getShortName());
    }

    @Override
    protected DataContainer serialize(Weight weight)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.OTHER, weight.getOther());
    }

    @Override
    protected DataContainer serialize(SkillsTab skillsTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.ACROBATICS, serialize(skillsTab.getAcrobatics()))
                .set(MCDNDSimpleKeys.ANIMAL_HANDLING, serialize(skillsTab.getAnimalHandling()))
                .set(MCDNDSimpleKeys.ARCANA, serialize(skillsTab.getArcana()))
                .set(MCDNDSimpleKeys.ATHLETICS, serialize(skillsTab.getAthletics()))
                .set(MCDNDSimpleKeys.DECEPTION, serialize(skillsTab.getDeception()))
                .set(MCDNDSimpleKeys.HISTORY, serialize(skillsTab.getHistory()))
                .set(MCDNDSimpleKeys.INSIGHT, serialize(skillsTab.getInsight()))
                .set(MCDNDSimpleKeys.INTIMIDATION, serialize(skillsTab.getIntimidation()))
                .set(MCDNDSimpleKeys.INVESTIGATION, serialize(skillsTab.getInvestigation()))
                .set(MCDNDSimpleKeys.MEDICINE, serialize(skillsTab.getMedicine()))
                .set(MCDNDSimpleKeys.NATURE, serialize(skillsTab.getNature()))
                .set(MCDNDSimpleKeys.PERFORMANCE, serialize(skillsTab.getPerformance()))
                .set(MCDNDSimpleKeys.PERCEPTION, serialize(skillsTab.getPerception()))
                .set(MCDNDSimpleKeys.PERSUASION, serialize(skillsTab.getPersuasion()))
                .set(MCDNDSimpleKeys.RELIGION, serialize(skillsTab.getReligion()))
                .set(MCDNDSimpleKeys.SLEIGHT_OF_HAND, serialize(skillsTab.getSleightOfHand()))
                .set(MCDNDSimpleKeys.STEALTH, serialize(skillsTab.getStealth()))
                .set(MCDNDSimpleKeys.SURVIVAL, serialize(skillsTab.getSurvival()))
                .set(MCDNDSimpleKeys.UNSKILLED_CHA, serialize(skillsTab.getUnskilledCHA()))
                .set(MCDNDSimpleKeys.UNSKILLED_CON, serialize(skillsTab.getUnskilledCON()))
                .set(MCDNDSimpleKeys.UNSKILLED_DEX, serialize(skillsTab.getUnskilledDEX()))
                .set(MCDNDSimpleKeys.UNSKILLED_INT, serialize(skillsTab.getUnskilledINT()))
                .set(MCDNDSimpleKeys.UNSKILLED_STR, serialize(skillsTab.getUnskilledSTR()))
                .set(MCDNDSimpleKeys.UNSKILLED_WIS, serialize(skillsTab.getUnskilledWIS()));
    }

    @Override
    protected DataContainer serialize(Skill skill)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.SKILL_PROFICIENCY, serialize(skill.getSkillProficiency()));
    }

    @Override
    protected DataContainer serialize(SkillProficiency skillProficiency)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, skillProficiency.getName());
    }

    @Override
    protected DataContainer serialize(SpellbookTab spellbookTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.INVOCATION_COUNT, spellbookTab.getInvocations())
                .set(MCDNDSimpleKeys.SORCERY_POINTS, spellbookTab.getSorceryPoints())
                .set(MCDNDSimpleKeys.SPELLS, serialize(spellbookTab.getSpells(), this::serialize))
                .set(MCDNDSimpleKeys.SPELLCASTER_CLASSES, serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
    }

    @Override
    protected DataContainer serialize(Spell spell)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NEEDS_CONCENTRATION, spell.needsConcentration())
                .set(MCDNDSimpleKeys.IS_PREPARED, spell.isPrepared())
                .set(MCDNDSimpleKeys.DURATION, spell.getDuration())
                .set(MCDNDSimpleKeys.SPELL_LEVEL, spell.getLevel())
                .set(MCDNDSimpleKeys.RANGE, spell.getRange())
                .set(MCDNDSimpleKeys.SPELL_DAMAGE, serialize(spell.getSpellDamage()))
                .set(MCDNDSimpleKeys.SPELL_HEALING, serialize(spell.getSpellHealing()))
                .set(MCDNDSimpleKeys.SPELL_DESCRIPTION, spell.getDescription())
                .set(MCDNDSimpleKeys.EFFECTS, spell.getEffects())
                .set(MCDNDSimpleKeys.SPELL_SAVE, serialize(spell.getSpellSave()))
                .set(MCDNDSimpleKeys.SPELLCASTER_CLASS, serialize(spell.getGainedFrom()))
                .set(MCDNDSimpleKeys.SPELL_TYPE, serialize(spell.getSpellType()))
                .set(MCDNDSimpleKeys.ATTACK_STAT, spell.getAttackStat())
                .set(MCDNDSimpleKeys.CAST_TIME, spell.getCastTime())
                .set(MCDNDSimpleKeys.TARGET_AREA, spell.getTargetArea());
    }

    @Override
    protected DataContainer serialize(SpellDamage spellDamage)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CAN_CRIT, spellDamage.canCrit())
                .set(MCDNDSimpleKeys.DICE, serialize(spellDamage.getDice()))
                .set(MCDNDSimpleKeys.BONUS, spellDamage.getBonus())
                .set(MCDNDSimpleKeys.DAMAGE_TYPE, spellDamage.getDamageType());
    }

    @Override
    protected DataContainer serialize(SpellHealing spellHealing)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.HEAL_AMOUNT, spellHealing.getHealAmount())
                .set(MCDNDSimpleKeys.STAT_BONUS, spellHealing.getStatBonus());
    }

    @Override
    protected DataContainer serialize(SpellSave spellSave)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.SAVE_DC_TYPE, serialize(spellSave.getSaveDCType()))
                .set(MCDNDSimpleKeys.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave())
                .set(MCDNDSimpleKeys.SAVING_STAT, spellSave.getSavingStat());
    }

    @Override
    protected DataContainer serialize(SaveDCType saveDCType)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.CUSTOM_DC, saveDCType.getSaveDC(null, 0))
                .set(MCDNDSimpleKeys.NAME, saveDCType.getName());
    }

    @Override
    protected DataContainer serialize(SpellcasterClass spellcasterClass)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, spellcasterClass.getName());
    }

    @Override
    protected DataContainer serialize(SpellType spellType)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, spellType.getName());
    }

    @Override
    protected DataContainer serialize(WeaponsTab weaponsTab)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.MELEE_WEAPONS, serialize(weaponsTab.getMeleeWeapons(), this::serialize))
                .set(MCDNDSimpleKeys.RANGED_WEAPONS, serialize(weaponsTab.getRangedWeapons(), this::serialize));
    }

    @Override
    protected DataContainer serialize(AbstractWeapon weapon, DataContainer weaponData)
    {
        return weaponData.set(MCDNDSimpleKeys.IS_PROFICIENT, weapon.isProficient())
                .set(MCDNDSimpleKeys.CRIT_DAMAGE_DICE, serialize(weapon.getCritDamageDice()))
                .set(MCDNDSimpleKeys.DAMAGE_DICE, serialize(weapon.getDamageDice()))
                .set(MCDNDSimpleKeys.CRIT_MINIMUM, weapon.getCritMin())
                .set(MCDNDSimpleKeys.DAMAGE_BONUS, weapon.getDamageBonus())
                .set(MCDNDSimpleKeys.MAGIC_BONUS, weapon.getMagicBonus())
                .set(MCDNDSimpleKeys.TO_HIT, weapon.getToHit())
                .set(MCDNDSimpleKeys.ATTACK_STAT, weapon.getAttackStat())
                .set(MCDNDSimpleKeys.NAME, weapon.getName());
    }

    @Override
    protected DataContainer serialize(MeleeWeapon meleeWeapon)
    {
        return serialize(meleeWeapon, new MemoryDataContainer())
                .set(MCDNDSimpleKeys.PLUS_STAT, meleeWeapon.isPlusStat());
    }

    @Override
    protected DataContainer serialize(RangedWeapon rangedWeapon)
    {
        return serialize(rangedWeapon, new MemoryDataContainer())
                .set(MCDNDSimpleKeys.AMMO, rangedWeapon.getAmmo());
    }

    @Override
    protected DataContainer serialize(PlayerSheet playerSheet)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.ARMOR_TAB, serialize(playerSheet.getArmorTab()))
                .set(MCDNDSimpleKeys.BACKGROUND_TAB, serialize(playerSheet.getBackgroundTab()))
                .set(MCDNDSimpleKeys.CLASS_TAB, serialize(playerSheet.getClassTab()))
                .set(MCDNDSimpleKeys.CORE_STATS_TAB, serialize(playerSheet.getCoreStatsTab()))
                .set(MCDNDSimpleKeys.INVENTORY_TAB, serialize(playerSheet.getInventoryTab()))
                .set(MCDNDSimpleKeys.SKILLS_TAB, serialize(playerSheet.getSkillsTab()))
                .set(MCDNDSimpleKeys.SPELL_BOOK_TAB, serialize(playerSheet.getSpellbookTab()))
                .set(MCDNDSimpleKeys.WEAPONS_TAB, serialize(playerSheet.getWeaponsTab()));
    }

    @Override
    protected DataContainer serialize(MCDNDArmorType armorType)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, armorType.getName());
    }

    @Override
    protected DataContainer serialize(Recharge recharge)
    {
        return new MemoryDataContainer()
                .set(MCDNDSimpleKeys.NAME, recharge.getName());
    }

    private <E> List<DataContainer> serialize(List<E> list, Function<E, DataContainer> mapper)//NOSONAR
    {
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}
