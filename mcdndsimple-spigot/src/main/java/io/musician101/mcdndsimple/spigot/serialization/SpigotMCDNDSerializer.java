package io.musician101.mcdndsimple.spigot.serialization;

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
import io.musician101.mcdndsimple.common.character.spell.SpellcasterClass;
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
import io.musician101.mcdndsimple.common.serialization.MCDNDSimpleKeys;
import org.bukkit.configuration.MemoryConfiguration;

public class SpigotMCDNDSerializer extends MCDNDSerializer<MemoryConfiguration> {

    @Override
    public MemoryConfiguration serialize(PlayerSheet playerSheet) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.BIO_AND_INFO, serialize(playerSheet.getBioAndInfo()));
        mc.set(MCDNDSimpleKeys.PLAYER_SHEET, serialize(playerSheet.getCharacterSheet()));
        mc.set(MCDNDSimpleKeys.CLASS, playerSheet.getClazz());
        mc.set(MCDNDSimpleKeys.NAME, playerSheet.getName());
        mc.set(MCDNDSimpleKeys.RACE, playerSheet.getRace());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(BioAndInfo bioAndInfo) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, bioAndInfo.getName());
        mc.set(MCDNDSimpleKeys.BIO, bioAndInfo.getBio());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ArmorTab armorTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.ARMOR_CLASS, armorTab.getArmorClass());
        mc.set(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, armorTab.getUnarmoredClass());
        mc.set(MCDNDSimpleKeys.ARMOR_LIST, serialize(armorTab.getArmorList(), this::serialize));
        mc.set(MCDNDSimpleKeys.UNARMORED_BONUS, serialize(armorTab.getUnarmoredBonus()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Armor armor) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.SPEED_PENALTY, armor.hasSpeedPenalty());
        mc.set(MCDNDSimpleKeys.STEALTH_PENALTY, armor.hasStealthPenalty());
        mc.set(MCDNDSimpleKeys.UNARMORED, armor.isUnarmored());
        mc.set(MCDNDSimpleKeys.WORN, armor.isWorn());
        mc.set(MCDNDSimpleKeys.BASE_ARMOR_CLASS, armor.getBaseArmorClass());
        mc.set(MCDNDSimpleKeys.MAGIC_BONUS, armor.getMagicBonus());
        mc.set(MCDNDSimpleKeys.ARMOR_TYPE, serialize(armor.getArmorType()));
        mc.set(MCDNDSimpleKeys.NAME, armor.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(UnarmoredBonus unarmoredBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, unarmoredBonus.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(BackgroundTab backgroundTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.WEIGHT_DOUBLE, backgroundTab.getWeight());
        mc.set(MCDNDSimpleKeys.AGE, backgroundTab.getAge());
        mc.set(MCDNDSimpleKeys.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies());
        mc.set(MCDNDSimpleKeys.BACKGROUND, backgroundTab.getBackground());
        mc.set(MCDNDSimpleKeys.BONDS, backgroundTab.getBonds());
        mc.set(MCDNDSimpleKeys.FLAWS, backgroundTab.getFlaws());
        mc.set(MCDNDSimpleKeys.IDEALS, backgroundTab.getIdeals());
        mc.set(MCDNDSimpleKeys.OTHER_NOTES, backgroundTab.getOtherNotes());
        mc.set(MCDNDSimpleKeys.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits());
        mc.set(MCDNDSimpleKeys.RACIAL_TRAITS, backgroundTab.getRacialTraits());
        mc.set(MCDNDSimpleKeys.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies());
        mc.set(MCDNDSimpleKeys.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies());
        mc.set(MCDNDSimpleKeys.ALIGNMENT, backgroundTab.getAlignment());
        mc.set(MCDNDSimpleKeys.EYES, backgroundTab.getEyes());
        mc.set(MCDNDSimpleKeys.GENDER, backgroundTab.getGender());
        mc.set(MCDNDSimpleKeys.HAIR, backgroundTab.getHair());
        mc.set(MCDNDSimpleKeys.HEIGHT, backgroundTab.getHeight());
        mc.set(MCDNDSimpleKeys.LANGUAGES, backgroundTab.getLanguages());
        mc.set(MCDNDSimpleKeys.SIZE, backgroundTab.getSize());
        mc.set(MCDNDSimpleKeys.VISION, backgroundTab.getVision());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassTab classTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.CLASS_LEVELS, serialize(classTab.getClassLevels()));
        mc.set(MCDNDSimpleKeys.CLASS_ACTIONS, serialize(classTab.getClassActions(), this::serialize));
        mc.set(MCDNDSimpleKeys.CLASS_RESOURCES, serialize(classTab.getClassResources(), this::serialize));
        mc.set(MCDNDSimpleKeys.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes());
        mc.set(MCDNDSimpleKeys.OTHER_NOTES, classTab.getOtherNotes());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassLevels classLevels) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.BARBARIAN_LEVEL, classLevels.getBarbarian());
        mc.set(MCDNDSimpleKeys.BARD_LEVEL, classLevels.getBard());
        mc.set(MCDNDSimpleKeys.CLERIC_LEVEL, classLevels.getCleric());
        mc.set(MCDNDSimpleKeys.DRUID_LEVEL, classLevels.getDruid());
        mc.set(MCDNDSimpleKeys.FIGHTER_LEVEL, classLevels.getFighter());
        mc.set(MCDNDSimpleKeys.MONK_LEVEL, classLevels.getMonk());
        mc.set(MCDNDSimpleKeys.PALADIN_LEVEL, classLevels.getPaladin());
        mc.set(MCDNDSimpleKeys.RANGER_LEVEL, classLevels.getRanger());
        mc.set(MCDNDSimpleKeys.ROGUE_LEVEL, classLevels.getRogue());
        mc.set(MCDNDSimpleKeys.SORCERER_LEVEL, classLevels.getSorcerer());
        mc.set(MCDNDSimpleKeys.WARLOCK_LEVEL, classLevels.getWarlock());
        mc.set(MCDNDSimpleKeys.WIZARD_LEVEL, classLevels.getWizard());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassAction classAction) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.MAX_USES, classAction.getMax());
        mc.set(MCDNDSimpleKeys.USES_LEFT, classAction.getUsedCharges());
        mc.set(MCDNDSimpleKeys.RECHARGE, serialize(classAction.getRecharge()));
        mc.set(MCDNDSimpleKeys.GAINED_FROM, classAction.getGainedFrom());
        mc.set(MCDNDSimpleKeys.NAME, classAction.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassResource classResource) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.USES_LEFT, classResource.getUsesLeft());
        mc.set(MCDNDSimpleKeys.MAX_USES, classResource.getMaxUses());
        mc.set(MCDNDSimpleKeys.RECHARGE, serialize(classResource.getRecharge()));
        mc.set(MCDNDSimpleKeys.NAME, classResource.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CoreStatsTab coreStatsTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.BONUSES, serialize(coreStatsTab.getBonuses()));
        mc.set(MCDNDSimpleKeys.CORE_STATS, serialize(coreStatsTab.getCoreStats()));
        mc.set(MCDNDSimpleKeys.EXPERIENCE, serialize(coreStatsTab.getExperience()));
        mc.set(MCDNDSimpleKeys.HIT_DICE, serialize(coreStatsTab.getHitDice()));
        mc.set(MCDNDSimpleKeys.HIT_POINTS, serialize(coreStatsTab.getHitPoints()));
        mc.set(MCDNDSimpleKeys.SPEED, coreStatsTab.getSpeed());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Bonuses bonuses) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.MELEE_BONUS, serialize(bonuses.getMelee()));
        mc.set(MCDNDSimpleKeys.RANGED_BONUS, serialize(bonuses.getRanged()));
        mc.set(MCDNDSimpleKeys.SPELLCASTING_BONUS, serialize(bonuses.getSpellcasting()));
        mc.set(MCDNDSimpleKeys.SAVES, bonuses.getSaves());
        mc.set(MCDNDSimpleKeys.ABILITIES_AND_SKILLS, bonuses.getAbilitiesAndSkills());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(MeleeBonus meleeBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.ATTACK, meleeBonus.getAttack());
        mc.set(MCDNDSimpleKeys.DAMAGE, meleeBonus.getDamage());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(RangedBonus rangedBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.ATTACK, rangedBonus.getAttack());
        mc.set(MCDNDSimpleKeys.DAMAGE, rangedBonus.getDamage());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellcastingBonus spellcastingBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.ATTACK, spellcastingBonus.getAttack());
        mc.set(MCDNDSimpleKeys.DAMAGE, spellcastingBonus.getDamage());
        mc.set(MCDNDSimpleKeys.SAVE_DC, spellcastingBonus.getSaveDC());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CoreStats coreStats) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.CHARISMA_SCORE, serialize(coreStats.getCharisma()));
        mc.set(MCDNDSimpleKeys.CONSTITUTION_SCORE, serialize(coreStats.getConstitution()));
        mc.set(MCDNDSimpleKeys.DEXTERITY_SCORE, serialize(coreStats.getDexterity()));
        mc.set(MCDNDSimpleKeys.INTELLIGENCE_SCORE, serialize(coreStats.getIntelligence()));
        mc.set(MCDNDSimpleKeys.STRENGTH_SCORE, serialize(coreStats.getStrength()));
        mc.set(MCDNDSimpleKeys.WISDOM_SCORE, serialize(coreStats.getWisdom()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(AbilityScore abilityScore) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.PROFICIENT, abilityScore.isProficient());
        mc.set(MCDNDSimpleKeys.SCORE, abilityScore.getScore());
        mc.set(MCDNDSimpleKeys.SHORT_NAME, abilityScore.getShortName());
        mc.set(MCDNDSimpleKeys.NAME, abilityScore.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Experience experience) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.EXPERIENCE_AMOUNT, experience.getXP());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(HitDice hitDice) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.HIT_DICE_MAP, hitDice.getHitDice());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Dice dice) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.AMOUNT, dice.getAmount());
        mc.set(MCDNDSimpleKeys.SIDES, dice.getSides());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(HitPoints hitPoints) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.CURRENT_HP, hitPoints.getCurrent());
        mc.set(MCDNDSimpleKeys.MAX_HP, hitPoints.getMax());
        mc.set(MCDNDSimpleKeys.TEMP_HP, hitPoints.getTemp());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(InventoryTab inventoryTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.INVENTORY, serialize(inventoryTab.getInventory(), this::serialize));
        mc.set(MCDNDSimpleKeys.INVENTORY_NOTES, inventoryTab.getInventoryNotes());
        mc.set(MCDNDSimpleKeys.WEALTH, serialize(inventoryTab.getWealth()));
        mc.set(MCDNDSimpleKeys.WEIGHT_CLASS, serialize(inventoryTab.getWeight()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(MCDNDItem item) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.CARRIED, item.isCarried());
        mc.set(MCDNDSimpleKeys.WEIGHT_DOUBLE, item.getWeight());
        mc.set(MCDNDSimpleKeys.DESCRIPTION, item.getDescription());
        mc.set(MCDNDSimpleKeys.NAME, item.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Wealth wealth) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.COPPER, serialize(wealth.getCopper()));
        mc.set(MCDNDSimpleKeys.ELECTRUM, serialize(wealth.getElectrum()));
        mc.set(MCDNDSimpleKeys.GOLD, serialize(wealth.getGold()));
        mc.set(MCDNDSimpleKeys.PLATINUM, serialize(wealth.getPlatinum()));
        mc.set(MCDNDSimpleKeys.SILVER, serialize(wealth.getSilver()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Coin coin) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, coin.getName());
        mc.set(MCDNDSimpleKeys.AMOUNT, coin.getAmount());
        mc.set(MCDNDSimpleKeys.SHORT_NAME, coin.getShortName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Weight weight) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.OTHER, weight.getOther());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SkillsTab skillsTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.ACROBATICS, serialize(skillsTab.getAcrobatics()));
        mc.set(MCDNDSimpleKeys.ANIMAL_HANDLING, serialize(skillsTab.getAnimalHandling()));
        mc.set(MCDNDSimpleKeys.ARCANA, serialize(skillsTab.getArcana()));
        mc.set(MCDNDSimpleKeys.ATHLETICS, serialize(skillsTab.getAthletics()));
        mc.set(MCDNDSimpleKeys.DECEPTION, serialize(skillsTab.getDeception()));
        mc.set(MCDNDSimpleKeys.HISTORY, serialize(skillsTab.getHistory()));
        mc.set(MCDNDSimpleKeys.INSIGHT, serialize(skillsTab.getInsight()));
        mc.set(MCDNDSimpleKeys.INTIMIDATION, serialize(skillsTab.getIntimidation()));
        mc.set(MCDNDSimpleKeys.INVESTIGATION, serialize(skillsTab.getInvestigation()));
        mc.set(MCDNDSimpleKeys.MEDICINE, serialize(skillsTab.getMedicine()));
        mc.set(MCDNDSimpleKeys.NATURE, serialize(skillsTab.getNature()));
        mc.set(MCDNDSimpleKeys.PERFORMANCE, serialize(skillsTab.getPerformance()));
        mc.set(MCDNDSimpleKeys.PERCEPTION, serialize(skillsTab.getPerception()));
        mc.set(MCDNDSimpleKeys.PERSUASION, serialize(skillsTab.getPersuasion()));
        mc.set(MCDNDSimpleKeys.RELIGION, serialize(skillsTab.getReligion()));
        mc.set(MCDNDSimpleKeys.SLEIGHT_OF_HAND, serialize(skillsTab.getSleightOfHand()));
        mc.set(MCDNDSimpleKeys.STEALTH, serialize(skillsTab.getStealth()));
        mc.set(MCDNDSimpleKeys.SURVIVAL, serialize(skillsTab.getSurvival()));
        mc.set(MCDNDSimpleKeys.UNSKILLED_CHA, serialize(skillsTab.getUnskilledCHA()));
        mc.set(MCDNDSimpleKeys.UNSKILLED_CON, serialize(skillsTab.getUnskilledCON()));
        mc.set(MCDNDSimpleKeys.UNSKILLED_DEX, serialize(skillsTab.getUnskilledDEX()));
        mc.set(MCDNDSimpleKeys.UNSKILLED_INT, serialize(skillsTab.getUnskilledINT()));
        mc.set(MCDNDSimpleKeys.UNSKILLED_STR, serialize(skillsTab.getUnskilledSTR()));
        mc.set(MCDNDSimpleKeys.UNSKILLED_WIS, serialize(skillsTab.getUnskilledWIS()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Skill skill) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.SKILL_PROFICIENCY, serialize(skill.getSkillProficiency()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SkillProficiency skillProficiency) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, skillProficiency.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellbookTab spellbookTab, ClassLevels classLevels) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.INVOCATION_COUNT, spellbookTab.getInvocations(classLevels));
        mc.set(MCDNDSimpleKeys.SORCERY_POINTS, spellbookTab.getSorceryPointsMax(classLevels));
        mc.set(MCDNDSimpleKeys.SPELLS, serialize(spellbookTab.getSpells(), this::serialize));
        mc.set(MCDNDSimpleKeys.SPELLCASTER_CLASSES, serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Spell spell) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NEEDS_CONCENTRATION, spell.needsConcentration());
        mc.set(MCDNDSimpleKeys.PREPARED, spell.getPrepared().toString());
        mc.set(MCDNDSimpleKeys.DURATION, spell.getDuration());
        mc.set(MCDNDSimpleKeys.SPELL_LEVEL, spell.getLevel());
        mc.set(MCDNDSimpleKeys.RANGE, spell.getRange());
        mc.set(MCDNDSimpleKeys.SPELL_DAMAGE, serialize(spell.getSpellDamage()));
        mc.set(MCDNDSimpleKeys.SPELL_HEALING, serialize(spell.getSpellHealing()));
        mc.set(MCDNDSimpleKeys.SPELL_DESCRIPTION, spell.getDescription());
        mc.set(MCDNDSimpleKeys.EFFECTS, spell.getEffects());
        mc.set(MCDNDSimpleKeys.SPELL_SAVE, serialize(spell.getSpellSave()));
        mc.set(MCDNDSimpleKeys.SPELLCASTER_CLASS, serialize(spell.getGainedFrom()));
        mc.set(MCDNDSimpleKeys.SPELL_TYPE, serialize(spell.getSpellType()));
        mc.set(MCDNDSimpleKeys.ATTACK_STAT, spell.getAttackStat());
        mc.set(MCDNDSimpleKeys.CAST_TIME, spell.getCastTime());
        mc.set(MCDNDSimpleKeys.TARGET_AREA, spell.getTargetArea());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellDamage spellDamage) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.CAN_CRIT, spellDamage.canCrit());
        mc.set(MCDNDSimpleKeys.DICE, serialize(spellDamage.getDice()));
        mc.set(MCDNDSimpleKeys.BONUS, spellDamage.getBonus());
        mc.set(MCDNDSimpleKeys.DAMAGE_TYPE, spellDamage.getDamageType());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellHealing spellHealing) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.HEAL_AMOUNT, spellHealing.getHealAmount());
        mc.set(MCDNDSimpleKeys.STAT_BONUS, spellHealing.getStatBonus());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellSave spellSave) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.SAVE_DC_TYPE, serialize(spellSave.getSpellcasterClass()));
        mc.set(MCDNDSimpleKeys.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave());
        mc.set(MCDNDSimpleKeys.SAVING_STAT, spellSave.getSavingStat());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SaveDCType saveDCType) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.CUSTOM_DC, saveDCType.getSaveDC(null, 0));
        mc.set(MCDNDSimpleKeys.NAME, saveDCType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellcasterClass spellcasterClass) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, spellcasterClass.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellType spellType) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, spellType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(WeaponsTab weaponsTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.MELEE_WEAPONS, serialize(weaponsTab.getMeleeWeapons(), this::serialize));
        mc.set(MCDNDSimpleKeys.RANGED_WEAPONS, serialize(weaponsTab.getRangedWeapons(), this::serialize));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(AbstractWeapon weapon, MemoryConfiguration weaponData) {
        weaponData.set(MCDNDSimpleKeys.IS_PROFICIENT, weapon.isProficient());
        weaponData.set(MCDNDSimpleKeys.CRIT_DAMAGE_DICE, serialize(weapon.getCritDamageDice()));
        weaponData.set(MCDNDSimpleKeys.DAMAGE_DICE, serialize(weapon.getDamageDice()));
        weaponData.set(MCDNDSimpleKeys.CRIT_MINIMUM, weapon.getCritMin());
        weaponData.set(MCDNDSimpleKeys.MAGIC_BONUS, weapon.getMagicBonus());
        weaponData.set(MCDNDSimpleKeys.ATTACK_STAT, weapon.getAttackStat());
        weaponData.set(MCDNDSimpleKeys.NAME, weapon.getName());
        return weaponData;
    }

    @Override
    protected MemoryConfiguration serialize(MeleeWeapon meleeWeapon) {
        MemoryConfiguration mc = serialize(meleeWeapon, new MemoryConfiguration());
        mc.set(MCDNDSimpleKeys.PLUS_STAT, meleeWeapon.isPlusStat());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(RangedWeapon rangedWeapon) {
        MemoryConfiguration mc = serialize(rangedWeapon, new MemoryConfiguration());
        mc.set(MCDNDSimpleKeys.AMMO, rangedWeapon.getAmmo());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CharacterSheet characterSheet) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.ARMOR_TAB, serialize(characterSheet.getArmorTab()));
        mc.set(MCDNDSimpleKeys.BACKGROUND_TAB, serialize(characterSheet.getBackgroundTab()));
        mc.set(MCDNDSimpleKeys.CLASS_TAB, serialize(characterSheet.getClassTab()));
        mc.set(MCDNDSimpleKeys.CORE_STATS_TAB, serialize(characterSheet.getCoreStatsTab()));
        mc.set(MCDNDSimpleKeys.INVENTORY_TAB, serialize(characterSheet.getInventoryTab()));
        mc.set(MCDNDSimpleKeys.SKILLS_TAB, serialize(characterSheet.getSkillsTab()));
        mc.set(MCDNDSimpleKeys.SPELL_BOOK_TAB, serialize(characterSheet.getSpellbookTab(), characterSheet.getClassTab().getClassLevels()));
        mc.set(MCDNDSimpleKeys.WEAPONS_TAB, serialize(characterSheet.getWeaponsTab()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ArmorType armorType) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, armorType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Recharge recharge) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(MCDNDSimpleKeys.NAME, recharge.getName());
        return mc;
    }
}
