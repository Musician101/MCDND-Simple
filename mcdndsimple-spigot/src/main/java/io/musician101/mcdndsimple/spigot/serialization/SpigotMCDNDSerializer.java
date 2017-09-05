package io.musician101.mcdndsimple.spigot.serialization;

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
import org.bukkit.configuration.MemoryConfiguration;

public class SpigotMCDNDSerializer extends MCDNDSerializer<MemoryConfiguration>
{
    @Override
    public MemoryConfiguration serialize(PlayerSheet playerSheet)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.BIO_AND_INFO, serialize(playerSheet.getBioAndInfo()));
        mc.set(SpigotMCDNDSimpleKeys.PLAYER_SHEET, serialize(playerSheet.getCharacterSheet()));
        mc.set(SpigotMCDNDSimpleKeys.CLASS, playerSheet.getClazz());
        mc.set(SpigotMCDNDSimpleKeys.NAME, playerSheet.getName());
        mc.set(SpigotMCDNDSimpleKeys.RACE, playerSheet.getRace());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(BioAndInfo bioAndInfo)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, bioAndInfo.getName());
        mc.set(SpigotMCDNDSimpleKeys.BIO, bioAndInfo.getBio());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ArmorTab armorTab)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.ARMOR_CLASS, armorTab.getArmorClass());
        mc.set(SpigotMCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, armorTab.getUnarmoredClass());
        mc.set(SpigotMCDNDSimpleKeys.ARMOR_LIST, serialize(armorTab.getArmorList(), this::serialize));
        mc.set(SpigotMCDNDSimpleKeys.UNARMORED_BONUS, serialize(armorTab.getUnarmoredBonus()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Armor armor)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.SPEED_PENALTY, armor.hasSpeedPenalty());
        mc.set(SpigotMCDNDSimpleKeys.STEALTH_PENALTY, armor.hasStealthPenalty());
        mc.set(SpigotMCDNDSimpleKeys.UNARMORED, armor.isUnarmored());
        mc.set(SpigotMCDNDSimpleKeys.WORN, armor.isWorn());
        mc.set(SpigotMCDNDSimpleKeys.BASE_ARMOR_CLASS, armor.getBaseArmorClass());
        mc.set(SpigotMCDNDSimpleKeys.MAGIC_BONUS, armor.getMagicBonus());
        mc.set(SpigotMCDNDSimpleKeys.ARMOR_TYPE, serialize(armor.getArmorType()));
        mc.set(SpigotMCDNDSimpleKeys.NAME, armor.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(UnarmoredBonus unarmoredBonus)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, unarmoredBonus.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(BackgroundTab backgroundTab)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.WEIGHT_DOUBLE, backgroundTab.getWeight());
        mc.set(SpigotMCDNDSimpleKeys.AGE, backgroundTab.getAge());
        mc.set(SpigotMCDNDSimpleKeys.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies());
        mc.set(SpigotMCDNDSimpleKeys.BACKGROUND, backgroundTab.getBackground());
        mc.set(SpigotMCDNDSimpleKeys.BONDS, backgroundTab.getBonds());
        mc.set(SpigotMCDNDSimpleKeys.FLAWS, backgroundTab.getFlaws());
        mc.set(SpigotMCDNDSimpleKeys.IDEALS, backgroundTab.getIdeals());
        mc.set(SpigotMCDNDSimpleKeys.OTHER_NOTES, backgroundTab.getOtherNotes());
        mc.set(SpigotMCDNDSimpleKeys.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits());
        mc.set(SpigotMCDNDSimpleKeys.RACIAL_TRAITS, backgroundTab.getRacialTraits());
        mc.set(SpigotMCDNDSimpleKeys.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies());
        mc.set(SpigotMCDNDSimpleKeys.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies());
        mc.set(SpigotMCDNDSimpleKeys.ALIGNMENT, backgroundTab.getAlignment());
        mc.set(SpigotMCDNDSimpleKeys.EYES, backgroundTab.getEyes());
        mc.set(SpigotMCDNDSimpleKeys.GENDER, backgroundTab.getGender());
        mc.set(SpigotMCDNDSimpleKeys.HAIR, backgroundTab.getHair());
        mc.set(SpigotMCDNDSimpleKeys.HEIGHT, backgroundTab.getHeight());
        mc.set(SpigotMCDNDSimpleKeys.LANGUAGES, backgroundTab.getLanguages());
        mc.set(SpigotMCDNDSimpleKeys.SIZE, backgroundTab.getSize());
        mc.set(SpigotMCDNDSimpleKeys.VISION, backgroundTab.getVision());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassTab classTab)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.CLASS_LEVELS, serialize(classTab.getClassLevels()));
        mc.set(SpigotMCDNDSimpleKeys.CLASS_ACTIONS, serialize(classTab.getClassActions(), this::serialize));
        mc.set(SpigotMCDNDSimpleKeys.CLASS_RESOURCES, serialize(classTab.getClassResources(), this::serialize));
        mc.set(SpigotMCDNDSimpleKeys.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes());
        mc.set(SpigotMCDNDSimpleKeys.OTHER_NOTES, classTab.getOtherNotes());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassLevels classLevels)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.BARBARIAN_LEVEL, classLevels.getBarbarian());
        mc.set(SpigotMCDNDSimpleKeys.BARD_LEVEL, classLevels.getBard());
        mc.set(SpigotMCDNDSimpleKeys.CLERIC_LEVEL, classLevels.getCleric());
        mc.set(SpigotMCDNDSimpleKeys.DRUID_LEVEL, classLevels.getDruid());
        mc.set(SpigotMCDNDSimpleKeys.FIGHTER_LEVEL, classLevels.getFighter());
        mc.set(SpigotMCDNDSimpleKeys.MONK_LEVEL, classLevels.getMonk());
        mc.set(SpigotMCDNDSimpleKeys.PALADIN_LEVEL, classLevels.getPaladin());
        mc.set(SpigotMCDNDSimpleKeys.RANGER_LEVEL, classLevels.getRanger());
        mc.set(SpigotMCDNDSimpleKeys.ROGUE_LEVEL, classLevels.getRogue());
        mc.set(SpigotMCDNDSimpleKeys.SORCERER_LEVEL, classLevels.getSorcerer());
        mc.set(SpigotMCDNDSimpleKeys.WARLOCK_LEVEL, classLevels.getWarlock());
        mc.set(SpigotMCDNDSimpleKeys.WIZARD_LEVEL, classLevels.getWizard());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassAction classAction)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.MAX_USES, classAction.getMax());
        mc.set(SpigotMCDNDSimpleKeys.USES_LEFT, classAction.getUsedCharges());
        mc.set(SpigotMCDNDSimpleKeys.RECHARGE, serialize(classAction.getRecharge()));
        mc.set(SpigotMCDNDSimpleKeys.GAINED_FROM, classAction.getGainedFrom());
        mc.set(SpigotMCDNDSimpleKeys.NAME, classAction.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassResource classResource)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.USES_LEFT, classResource.getUsesLeft());
        mc.set(SpigotMCDNDSimpleKeys.MAX_USES, classResource.getMaxUses());
        mc.set(SpigotMCDNDSimpleKeys.RECHARGE, serialize(classResource.getRecharge()));
        mc.set(SpigotMCDNDSimpleKeys.NAME, classResource.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CoreStatsTab coreStatsTab)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.BONUSES, serialize(coreStatsTab.getBonuses()));
        mc.set(SpigotMCDNDSimpleKeys.CORE_STATS, serialize(coreStatsTab.getCoreStats()));
        mc.set(SpigotMCDNDSimpleKeys.EXPERIENCE, serialize(coreStatsTab.getExperience()));
        mc.set(SpigotMCDNDSimpleKeys.HIT_DICE, serialize(coreStatsTab.getHitDice()));
        mc.set(SpigotMCDNDSimpleKeys.HIT_POINTS, serialize(coreStatsTab.getHitPoints()));
        mc.set(SpigotMCDNDSimpleKeys.SPEED, coreStatsTab.getSpeed());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Bonuses bonuses)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.MELEE_BONUS, serialize(bonuses.getMelee()));
        mc.set(SpigotMCDNDSimpleKeys.RANGED_BONUS, serialize(bonuses.getRanged()));
        mc.set(SpigotMCDNDSimpleKeys.SPELLCASTING_BONUS, serialize(bonuses.getSpellcasting()));
        mc.set(SpigotMCDNDSimpleKeys.SAVES, bonuses.getSaves());
        mc.set(SpigotMCDNDSimpleKeys.ABILITIES_AND_SKILLS, bonuses.getAbilitiesAndSkills());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(MeleeBonus meleeBonus)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.ATTACK, meleeBonus.getAttack());
        mc.set(SpigotMCDNDSimpleKeys.DAMAGE, meleeBonus.getDamage());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(RangedBonus rangedBonus)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.ATTACK, rangedBonus.getAttack());
        mc.set(SpigotMCDNDSimpleKeys.DAMAGE, rangedBonus.getDamage());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellcastingBonus spellcastingBonus)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.ATTACK, spellcastingBonus.getAttack());
        mc.set(SpigotMCDNDSimpleKeys.DAMAGE, spellcastingBonus.getDamage());
        mc.set(SpigotMCDNDSimpleKeys.SAVE_DC, spellcastingBonus.getSaveDC());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CoreStats coreStats)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.CHARISMA_SCORE, serialize(coreStats.getCharisma()));
        mc.set(SpigotMCDNDSimpleKeys.CONSTITUTION_SCORE, serialize(coreStats.getConstitution()));
        mc.set(SpigotMCDNDSimpleKeys.DEXTERITY_SCORE, serialize(coreStats.getDexterity()));
        mc.set(SpigotMCDNDSimpleKeys.INTELLIGENCE_SCORE, serialize(coreStats.getIntelligence()));
        mc.set(SpigotMCDNDSimpleKeys.STRENGTH_SCORE, serialize(coreStats.getStrength()));
        mc.set(SpigotMCDNDSimpleKeys.WISDOM_SCORE, serialize(coreStats.getWisdom()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(AbilityScore abilityScore)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.PROFICIENT, abilityScore.isProficient());
        mc.set(SpigotMCDNDSimpleKeys.SCORE, abilityScore.getScore());
        mc.set(SpigotMCDNDSimpleKeys.SHORT_NAME, abilityScore.getShortName());
        mc.set(SpigotMCDNDSimpleKeys.NAME, abilityScore.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Experience experience)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.EXPERIENCE_AMOUNT, experience.getXP());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(HitDice hitDice)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.HIT_DICE_MAP, hitDice.getHitDice());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Dice dice)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.AMOUNT, dice.getAmount());
        mc.set(SpigotMCDNDSimpleKeys.SIDES, dice.getSides());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(HitPoints hitPoints)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.CURRENT_HP, hitPoints.getCurrent());
        mc.set(SpigotMCDNDSimpleKeys.MAX_HP, hitPoints.getMax());
        mc.set(SpigotMCDNDSimpleKeys.TEMP_HP, hitPoints.getTemp());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(InventoryTab inventoryTab)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.INVENTORY, serialize(inventoryTab.getInventory(), this::serialize));
        mc.set(SpigotMCDNDSimpleKeys.INVENTORY_NOTES, inventoryTab.getInventoryNotes());
        mc.set(SpigotMCDNDSimpleKeys.WEALTH, serialize(inventoryTab.getWealth()));
        mc.set(SpigotMCDNDSimpleKeys.WEIGHT_CLASS, serialize(inventoryTab.getWeight()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(MCDNDItem item)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.CARRIED, item.isCarried());
        mc.set(SpigotMCDNDSimpleKeys.WEIGHT_DOUBLE, item.getWeight());
        mc.set(SpigotMCDNDSimpleKeys.DESCRIPTION, item.getDescription());
        mc.set(SpigotMCDNDSimpleKeys.NAME, item.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Wealth wealth)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.COPPER, serialize(wealth.getCopper()));
        mc.set(SpigotMCDNDSimpleKeys.ELECTRUM, serialize(wealth.getElectrum()));
        mc.set(SpigotMCDNDSimpleKeys.GOLD, serialize(wealth.getGold()));
        mc.set(SpigotMCDNDSimpleKeys.PLATINUM, serialize(wealth.getPlatinum()));
        mc.set(SpigotMCDNDSimpleKeys.SILVER, serialize(wealth.getSilver()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Coin coin)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, coin.getName());
        mc.set(SpigotMCDNDSimpleKeys.AMOUNT, coin.getAmount());
        mc.set(SpigotMCDNDSimpleKeys.SHORT_NAME, coin.getShortName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Weight weight)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.OTHER, weight.getOther());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SkillsTab skillsTab)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.ACROBATICS, serialize(skillsTab.getAcrobatics()));
        mc.set(SpigotMCDNDSimpleKeys.ANIMAL_HANDLING, serialize(skillsTab.getAnimalHandling()));
        mc.set(SpigotMCDNDSimpleKeys.ARCANA, serialize(skillsTab.getArcana()));
        mc.set(SpigotMCDNDSimpleKeys.ATHLETICS, serialize(skillsTab.getAthletics()));
        mc.set(SpigotMCDNDSimpleKeys.DECEPTION, serialize(skillsTab.getDeception()));
        mc.set(SpigotMCDNDSimpleKeys.HISTORY, serialize(skillsTab.getHistory()));
        mc.set(SpigotMCDNDSimpleKeys.INSIGHT, serialize(skillsTab.getInsight()));
        mc.set(SpigotMCDNDSimpleKeys.INTIMIDATION, serialize(skillsTab.getIntimidation()));
        mc.set(SpigotMCDNDSimpleKeys.INVESTIGATION, serialize(skillsTab.getInvestigation()));
        mc.set(SpigotMCDNDSimpleKeys.MEDICINE, serialize(skillsTab.getMedicine()));
        mc.set(SpigotMCDNDSimpleKeys.NATURE, serialize(skillsTab.getNature()));
        mc.set(SpigotMCDNDSimpleKeys.PERFORMANCE, serialize(skillsTab.getPerformance()));
        mc.set(SpigotMCDNDSimpleKeys.PERCEPTION, serialize(skillsTab.getPerception()));
        mc.set(SpigotMCDNDSimpleKeys.PERSUASION, serialize(skillsTab.getPersuasion()));
        mc.set(SpigotMCDNDSimpleKeys.RELIGION, serialize(skillsTab.getReligion()));
        mc.set(SpigotMCDNDSimpleKeys.SLEIGHT_OF_HAND, serialize(skillsTab.getSleightOfHand()));
        mc.set(SpigotMCDNDSimpleKeys.STEALTH, serialize(skillsTab.getStealth()));
        mc.set(SpigotMCDNDSimpleKeys.SURVIVAL, serialize(skillsTab.getSurvival()));
        mc.set(SpigotMCDNDSimpleKeys.UNSKILLED_CHA, serialize(skillsTab.getUnskilledCHA()));
        mc.set(SpigotMCDNDSimpleKeys.UNSKILLED_CON, serialize(skillsTab.getUnskilledCON()));
        mc.set(SpigotMCDNDSimpleKeys.UNSKILLED_DEX, serialize(skillsTab.getUnskilledDEX()));
        mc.set(SpigotMCDNDSimpleKeys.UNSKILLED_INT, serialize(skillsTab.getUnskilledINT()));
        mc.set(SpigotMCDNDSimpleKeys.UNSKILLED_STR, serialize(skillsTab.getUnskilledSTR()));
        mc.set(SpigotMCDNDSimpleKeys.UNSKILLED_WIS, serialize(skillsTab.getUnskilledWIS()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Skill skill)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY, serialize(skill.getSkillProficiency()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SkillProficiency skillProficiency)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, skillProficiency.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellbookTab spellbookTab, ClassLevels classLevels)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.INVOCATION_COUNT, spellbookTab.getInvocations(classLevels));
        mc.set(SpigotMCDNDSimpleKeys.SORCERY_POINTS, spellbookTab.getSorceryPointsMax(classLevels));
        mc.set(SpigotMCDNDSimpleKeys.SPELLS, serialize(spellbookTab.getSpells(), this::serialize));
        mc.set(SpigotMCDNDSimpleKeys.SPELLCASTER_CLASSES, serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Spell spell)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NEEDS_CONCENTRATION, spell.needsConcentration());
        mc.set(SpigotMCDNDSimpleKeys.PREPARED, spell.getPrepared().toString());
        mc.set(SpigotMCDNDSimpleKeys.DURATION, spell.getDuration());
        mc.set(SpigotMCDNDSimpleKeys.SPELL_LEVEL, spell.getLevel());
        mc.set(SpigotMCDNDSimpleKeys.RANGE, spell.getRange());
        mc.set(SpigotMCDNDSimpleKeys.SPELL_DAMAGE, serialize(spell.getSpellDamage()));
        mc.set(SpigotMCDNDSimpleKeys.SPELL_HEALING, serialize(spell.getSpellHealing()));
        mc.set(SpigotMCDNDSimpleKeys.SPELL_DESCRIPTION, spell.getDescription());
        mc.set(SpigotMCDNDSimpleKeys.EFFECTS, spell.getEffects());
        mc.set(SpigotMCDNDSimpleKeys.SPELL_SAVE, serialize(spell.getSpellSave()));
        mc.set(SpigotMCDNDSimpleKeys.SPELLCASTER_CLASS, serialize(spell.getGainedFrom()));
        mc.set(SpigotMCDNDSimpleKeys.SPELL_TYPE, serialize(spell.getSpellType()));
        mc.set(SpigotMCDNDSimpleKeys.ATTACK_STAT, spell.getAttackStat());
        mc.set(SpigotMCDNDSimpleKeys.CAST_TIME, spell.getCastTime());
        mc.set(SpigotMCDNDSimpleKeys.TARGET_AREA, spell.getTargetArea());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellDamage spellDamage)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.CAN_CRIT, spellDamage.canCrit());
        mc.set(SpigotMCDNDSimpleKeys.DICE, serialize(spellDamage.getDice()));
        mc.set(SpigotMCDNDSimpleKeys.BONUS, spellDamage.getBonus());
        mc.set(SpigotMCDNDSimpleKeys.DAMAGE_TYPE, spellDamage.getDamageType());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellHealing spellHealing)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.HEAL_AMOUNT, spellHealing.getHealAmount());
        mc.set(SpigotMCDNDSimpleKeys.STAT_BONUS, spellHealing.getStatBonus());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellSave spellSave)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.SAVE_DC_TYPE, serialize(spellSave.getSpellcasterClass()));
        mc.set(SpigotMCDNDSimpleKeys.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave());
        mc.set(SpigotMCDNDSimpleKeys.SAVING_STAT, spellSave.getSavingStat());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SaveDCType saveDCType)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.CUSTOM_DC, saveDCType.getSaveDC(null, 0));
        mc.set(SpigotMCDNDSimpleKeys.NAME, saveDCType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellcasterClass spellcasterClass)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, spellcasterClass.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellType spellType)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, spellType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(WeaponsTab weaponsTab)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.MELEE_WEAPONS, serialize(weaponsTab.getMeleeWeapons(), this::serialize));
        mc.set(SpigotMCDNDSimpleKeys.RANGED_WEAPONS, serialize(weaponsTab.getRangedWeapons(), this::serialize));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(AbstractWeapon weapon, MemoryConfiguration weaponData)
    {
        weaponData.set(SpigotMCDNDSimpleKeys.IS_PROFICIENT, weapon.isProficient());
        weaponData.set(SpigotMCDNDSimpleKeys.CRIT_DAMAGE_DICE, serialize(weapon.getCritDamageDice()));
        weaponData.set(SpigotMCDNDSimpleKeys.DAMAGE_DICE, serialize(weapon.getDamageDice()));
        weaponData.set(SpigotMCDNDSimpleKeys.CRIT_MINIMUM, weapon.getCritMin());
        weaponData.set(SpigotMCDNDSimpleKeys.MAGIC_BONUS, weapon.getMagicBonus());
        weaponData.set(SpigotMCDNDSimpleKeys.ATTACK_STAT, weapon.getAttackStat());
        weaponData.set(SpigotMCDNDSimpleKeys.NAME, weapon.getName());
        return weaponData;
    }

    @Override
    protected MemoryConfiguration serialize(MeleeWeapon meleeWeapon)
    {
        MemoryConfiguration mc = serialize(meleeWeapon, new MemoryConfiguration());
        mc.set(SpigotMCDNDSimpleKeys.PLUS_STAT, meleeWeapon.isPlusStat());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(RangedWeapon rangedWeapon)
    {
        MemoryConfiguration mc = serialize(rangedWeapon, new MemoryConfiguration());
        mc.set(SpigotMCDNDSimpleKeys.AMMO, rangedWeapon.getAmmo());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CharacterSheet characterSheet)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.ARMOR_TAB, serialize(characterSheet.getArmorTab()));
        mc.set(SpigotMCDNDSimpleKeys.BACKGROUND_TAB, serialize(characterSheet.getBackgroundTab()));
        mc.set(SpigotMCDNDSimpleKeys.CLASS_TAB, serialize(characterSheet.getClassTab()));
        mc.set(SpigotMCDNDSimpleKeys.CORE_STATS_TAB, serialize(characterSheet.getCoreStatsTab()));
        mc.set(SpigotMCDNDSimpleKeys.INVENTORY_TAB, serialize(characterSheet.getInventoryTab()));
        mc.set(SpigotMCDNDSimpleKeys.SKILLS_TAB, serialize(characterSheet.getSkillsTab()));
        mc.set(SpigotMCDNDSimpleKeys.SPELL_BOOK_TAB, serialize(characterSheet.getSpellbookTab(), characterSheet.getClassTab().getClassLevels()));
        mc.set(SpigotMCDNDSimpleKeys.WEAPONS_TAB, serialize(characterSheet.getWeaponsTab()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ArmorType armorType)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, armorType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Recharge recharge)
    {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(SpigotMCDNDSimpleKeys.NAME, recharge.getName());
        return mc;
    }
}
