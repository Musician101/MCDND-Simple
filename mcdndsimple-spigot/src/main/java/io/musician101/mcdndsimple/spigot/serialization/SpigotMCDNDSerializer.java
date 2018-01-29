package io.musician101.mcdndsimple.spigot.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCAction;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCActionType;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCActions;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCHitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.TraitsBackground;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.ClassAction;
import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.ClassResource;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.common.character.player.Recharge;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.player.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.player.spell.SaveDCType;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.player.spell.SpellType;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.AbstractWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.mcdndsimple.common.serialization.MCDNDSerializer;
import java.util.stream.Collectors;
import org.bukkit.configuration.MemoryConfiguration;

public class SpigotMCDNDSerializer extends MCDNDSerializer<MemoryConfiguration> {

    @Override
    public MemoryConfiguration serialize(NonPlayerSheet nonPlayerSheet) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NPC_ACTIONS, serialize(nonPlayerSheet.getActions()));
        mc.set(Keys.PLAYER_SHEET, serialize(nonPlayerSheet.getNPCSheet()));
        mc.set(Keys.TRAITS_BACKGROUND, serialize(nonPlayerSheet.getTraitsBackground()));
        mc.set(Keys.CLASS, nonPlayerSheet.getClazz());
        mc.set(Keys.NAME, nonPlayerSheet.getName());
        mc.set(Keys.RACE, nonPlayerSheet.getRace());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(NPCActions npcActions) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NPC_ACTIONS, npcActions.getActions().stream().map(this::serialize).collect(Collectors.toList()));
        mc.set(Keys.MULTI_ATTACK, npcActions.getMultiAttack());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(NPCAction npcAction) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.MULTI_ATTACK, npcAction.isMultiAttack());
        mc.set(Keys.DESCRIPTION, npcAction.getDescription());
        mc.set(Keys.EFFECTS, npcAction.getEffect());
        mc.set(Keys.ACTION_TYPE, serialize(npcAction.getActionType()));
        mc.set(Keys.NAME, npcAction.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(NPCActionType npcActionType) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, npcActionType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(NPCSheet NPCSheet) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.DM_OUTPUT_ONLY, NPCSheet.isDMOutputOnly());
        mc.set(Keys.CORE_STATS, serialize(NPCSheet.getCoreStats()));
        mc.set(Keys.CHALLENGE_RATING, NPCSheet.getChallengeRating());
        mc.set(Keys.ARMOR_CLASS, NPCSheet.getArmorClass());
        mc.set(Keys.CLIMB_SPEED, NPCSheet.getClimbSpeed());
        mc.set(Keys.FLY_SPEED, NPCSheet.getFlySpeed());
        mc.set(Keys.SPEED, NPCSheet.getSpeed());
        mc.set(Keys.SWIM_SPEED, NPCSheet.getSwimSpeed());
        mc.set(Keys.EXPERIENCE, NPCSheet.getXP());
        mc.set(Keys.HIT_POINTS, serialize(NPCSheet.getHealth()));
        mc.set(Keys.ARMOR_CLASS_NOTE, NPCSheet.getArmorClassNote());
        mc.set(Keys.ALIGNMENT, NPCSheet.getAlignment());
        mc.set(Keys.LANGUAGES, NPCSheet.getLanguages());
        mc.set(Keys.SENSES, NPCSheet.getSenses());
        mc.set(Keys.SIZE, NPCSheet.getSize());
        mc.set(Keys.TYPE_RACE, NPCSheet.getTypeRace());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(NPCHitPoints npcHitPoints) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.HIT_DICE, serialize(npcHitPoints.getHitDice()));
        mc.set(Keys.CURRENT_HP, npcHitPoints.getCurrent());
        mc.set(Keys.MAX_HP, npcHitPoints.getMax());
        mc.set(Keys.TEMP_HP, npcHitPoints.getTemp());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(TraitsBackground traitsBackground) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.TRAITS, traitsBackground.getTraits());
        mc.set(Keys.CONDITION_IMMUNITY, traitsBackground.getConditionImmunity());
        mc.set(Keys.DAMAGE_IMMUNITY, traitsBackground.getDamageImmunity());
        mc.set(Keys.DAMAGE_RESISTANCE, traitsBackground.getDamageResistance());
        mc.set(Keys.DAMAGE_VULNERABILITY, traitsBackground.getDamageVulnerability());
        return mc;
    }

    @Override
    public MemoryConfiguration serialize(PlayerSheet playerSheet) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.BIO_AND_INFO, serialize(playerSheet.getBioAndInfo()));
        mc.set(Keys.PLAYER_SHEET, serialize(playerSheet.getCharacterSheet()));
        mc.set(Keys.CLASS, playerSheet.getClazz());
        mc.set(Keys.NAME, playerSheet.getName());
        mc.set(Keys.RACE, playerSheet.getRace());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(BioAndInfo bioAndInfo) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, bioAndInfo.getName());
        mc.set(Keys.BIO, bioAndInfo.getBio());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ArmorTab armorTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.ARMOR_CLASS, armorTab.getArmorClass());
        mc.set(Keys.UNARMORED_ARMOR_CLASS, armorTab.getUnarmoredClass());
        mc.set(Keys.ARMOR_LIST, serialize(armorTab.getArmorList(), this::serialize));
        mc.set(Keys.UNARMORED_BONUS, serialize(armorTab.getUnarmoredBonus()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Armor armor) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.SPEED_PENALTY, armor.hasSpeedPenalty());
        mc.set(Keys.STEALTH_PENALTY, armor.hasStealthPenalty());
        mc.set(Keys.UNARMORED, armor.isUnarmored());
        mc.set(Keys.WORN, armor.isWorn());
        mc.set(Keys.BASE_ARMOR_CLASS, armor.getBaseArmorClass());
        mc.set(Keys.MAGIC_BONUS, armor.getMagicBonus());
        mc.set(Keys.ARMOR_TYPE, serialize(armor.getArmorType()));
        mc.set(Keys.NAME, armor.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(UnarmoredBonus unarmoredBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, unarmoredBonus.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(BackgroundTab backgroundTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.WEIGHT_DOUBLE, backgroundTab.getWeight());
        mc.set(Keys.AGE, backgroundTab.getAge());
        mc.set(Keys.ARMOR_PROFICIENCIES, backgroundTab.getArmorProficiencies());
        mc.set(Keys.BACKGROUND, backgroundTab.getBackground());
        mc.set(Keys.BONDS, backgroundTab.getBonds());
        mc.set(Keys.FLAWS, backgroundTab.getFlaws());
        mc.set(Keys.IDEALS, backgroundTab.getIdeals());
        mc.set(Keys.OTHER_NOTES, backgroundTab.getOtherNotes());
        mc.set(Keys.PERSONALITY_TRAITS, backgroundTab.getPersonalityTraits());
        mc.set(Keys.RACIAL_TRAITS, backgroundTab.getRacialTraits());
        mc.set(Keys.TOOL_PROFICIENCIES, backgroundTab.getToolProficiencies());
        mc.set(Keys.WEAPON_PROFICIENCIES, backgroundTab.getWeaponProficiencies());
        mc.set(Keys.ALIGNMENT, backgroundTab.getAlignment());
        mc.set(Keys.EYES, backgroundTab.getEyes());
        mc.set(Keys.GENDER, backgroundTab.getGender());
        mc.set(Keys.HAIR, backgroundTab.getHair());
        mc.set(Keys.HEIGHT, backgroundTab.getHeight());
        mc.set(Keys.LANGUAGES, backgroundTab.getLanguages());
        mc.set(Keys.SIZE, backgroundTab.getSize());
        mc.set(Keys.VISION, backgroundTab.getVision());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassTab classTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.CLASS_LEVELS, serialize(classTab.getClassLevels()));
        mc.set(Keys.CLASS_ACTIONS, serialize(classTab.getClassActions(), this::serialize));
        mc.set(Keys.CLASS_RESOURCES, serialize(classTab.getClassResources(), this::serialize));
        mc.set(Keys.CLASS_FEATURE_NOTES, classTab.getClassFeatureNotes());
        mc.set(Keys.OTHER_NOTES, classTab.getOtherNotes());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassLevels classLevels) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.BARBARIAN_LEVEL, classLevels.getBarbarian());
        mc.set(Keys.BARD_LEVEL, classLevels.getBard());
        mc.set(Keys.CLERIC_LEVEL, classLevels.getCleric());
        mc.set(Keys.DRUID_LEVEL, classLevels.getDruid());
        mc.set(Keys.FIGHTER_LEVEL, classLevels.getFighter());
        mc.set(Keys.MONK_LEVEL, classLevels.getMonk());
        mc.set(Keys.PALADIN_LEVEL, classLevels.getPaladin());
        mc.set(Keys.RANGER_LEVEL, classLevels.getRanger());
        mc.set(Keys.ROGUE_LEVEL, classLevels.getRogue());
        mc.set(Keys.SORCERER_LEVEL, classLevels.getSorcerer());
        mc.set(Keys.WARLOCK_LEVEL, classLevels.getWarlock());
        mc.set(Keys.WIZARD_LEVEL, classLevels.getWizard());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassAction classAction) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.MAX_USES, classAction.getMax());
        mc.set(Keys.USES_LEFT, classAction.getUsedCharges());
        mc.set(Keys.RECHARGE, serialize(classAction.getRecharge()));
        mc.set(Keys.GAINED_FROM, classAction.getGainedFrom());
        mc.set(Keys.NAME, classAction.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ClassResource classResource) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.USES_LEFT, classResource.getUsesLeft());
        mc.set(Keys.MAX_USES, classResource.getMaxUses());
        mc.set(Keys.RECHARGE, serialize(classResource.getRecharge()));
        mc.set(Keys.NAME, classResource.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CoreStatsTab coreStatsTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.BONUSES, serialize(coreStatsTab.getBonuses()));
        mc.set(Keys.CORE_STATS, serialize(coreStatsTab.getCoreStats()));
        mc.set(Keys.EXPERIENCE, serialize(coreStatsTab.getExperience()));
        mc.set(Keys.HIT_DICE, serialize(coreStatsTab.getHitDice()));
        mc.set(Keys.HIT_POINTS, serialize(coreStatsTab.getHitPoints()));
        mc.set(Keys.SPEED, coreStatsTab.getSpeed());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Bonuses bonuses) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.MELEE_BONUS, serialize(bonuses.getMelee()));
        mc.set(Keys.RANGED_BONUS, serialize(bonuses.getRanged()));
        mc.set(Keys.SPELLCASTING_BONUS, serialize(bonuses.getSpellcasting()));
        mc.set(Keys.SAVES, bonuses.getSaves());
        mc.set(Keys.ABILITIES_AND_SKILLS, bonuses.getAbilitiesAndSkills());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(MeleeBonus meleeBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.ATTACK, meleeBonus.getAttack());
        mc.set(Keys.DAMAGE, meleeBonus.getDamage());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(RangedBonus rangedBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.ATTACK, rangedBonus.getAttack());
        mc.set(Keys.DAMAGE, rangedBonus.getDamage());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellcastingBonus spellcastingBonus) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.ATTACK, spellcastingBonus.getAttack());
        mc.set(Keys.DAMAGE, spellcastingBonus.getDamage());
        mc.set(Keys.SAVE_DC, spellcastingBonus.getSaveDC());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CoreStats coreStats) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.CHARISMA_SCORE, serialize(coreStats.getCharisma()));
        mc.set(Keys.CONSTITUTION_SCORE, serialize(coreStats.getConstitution()));
        mc.set(Keys.DEXTERITY_SCORE, serialize(coreStats.getDexterity()));
        mc.set(Keys.INTELLIGENCE_SCORE, serialize(coreStats.getIntelligence()));
        mc.set(Keys.STRENGTH_SCORE, serialize(coreStats.getStrength()));
        mc.set(Keys.WISDOM_SCORE, serialize(coreStats.getWisdom()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(AbilityScore abilityScore) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.PROFICIENT, abilityScore.isProficient());
        mc.set(Keys.SCORE, abilityScore.getScore());
        mc.set(Keys.SHORT_NAME, abilityScore.getShortName());
        mc.set(Keys.NAME, abilityScore.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Experience experience) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.EXPERIENCE_AMOUNT, experience.getXP());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(HitDice hitDice) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.HIT_DICE_MAP, hitDice.getHitDice());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Dice dice) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.AMOUNT, dice.getAmount());
        mc.set(Keys.SIDES, dice.getSides());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(HitPoints hitPoints) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.CURRENT_HP, hitPoints.getCurrent());
        mc.set(Keys.MAX_HP, hitPoints.getMax());
        mc.set(Keys.TEMP_HP, hitPoints.getTemp());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(InventoryTab inventoryTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.INVENTORY, serialize(inventoryTab.getInventory(), this::serialize));
        mc.set(Keys.INVENTORY_NOTES, inventoryTab.getInventoryNotes());
        mc.set(Keys.WEALTH, serialize(inventoryTab.getWealth()));
        mc.set(Keys.WEIGHT_CLASS, serialize(inventoryTab.getWeight()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(MCDNDItem item) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.CARRIED, item.isCarried());
        mc.set(Keys.WEIGHT_DOUBLE, item.getWeight());
        mc.set(Keys.DESCRIPTION, item.getDescription());
        mc.set(Keys.NAME, item.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Wealth wealth) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.COPPER, serialize(wealth.getCopper()));
        mc.set(Keys.ELECTRUM, serialize(wealth.getElectrum()));
        mc.set(Keys.GOLD, serialize(wealth.getGold()));
        mc.set(Keys.PLATINUM, serialize(wealth.getPlatinum()));
        mc.set(Keys.SILVER, serialize(wealth.getSilver()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Coin coin) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, coin.getName());
        mc.set(Keys.AMOUNT, coin.getAmount());
        mc.set(Keys.SHORT_NAME, coin.getShortName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Weight weight) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.OTHER, weight.getOther());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SkillsTab skillsTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.ACROBATICS, serialize(skillsTab.getAcrobatics()));
        mc.set(Keys.ANIMAL_HANDLING, serialize(skillsTab.getAnimalHandling()));
        mc.set(Keys.ARCANA, serialize(skillsTab.getArcana()));
        mc.set(Keys.ATHLETICS, serialize(skillsTab.getAthletics()));
        mc.set(Keys.DECEPTION, serialize(skillsTab.getDeception()));
        mc.set(Keys.HISTORY, serialize(skillsTab.getHistory()));
        mc.set(Keys.INSIGHT, serialize(skillsTab.getInsight()));
        mc.set(Keys.INTIMIDATION, serialize(skillsTab.getIntimidation()));
        mc.set(Keys.INVESTIGATION, serialize(skillsTab.getInvestigation()));
        mc.set(Keys.MEDICINE, serialize(skillsTab.getMedicine()));
        mc.set(Keys.NATURE, serialize(skillsTab.getNature()));
        mc.set(Keys.PERFORMANCE, serialize(skillsTab.getPerformance()));
        mc.set(Keys.PERCEPTION, serialize(skillsTab.getPerception()));
        mc.set(Keys.PERSUASION, serialize(skillsTab.getPersuasion()));
        mc.set(Keys.RELIGION, serialize(skillsTab.getReligion()));
        mc.set(Keys.SLEIGHT_OF_HAND, serialize(skillsTab.getSleightOfHand()));
        mc.set(Keys.STEALTH, serialize(skillsTab.getStealth()));
        mc.set(Keys.SURVIVAL, serialize(skillsTab.getSurvival()));
        mc.set(Keys.UNSKILLED_CHA, serialize(skillsTab.getUnskilledCHA()));
        mc.set(Keys.UNSKILLED_CON, serialize(skillsTab.getUnskilledCON()));
        mc.set(Keys.UNSKILLED_DEX, serialize(skillsTab.getUnskilledDEX()));
        mc.set(Keys.UNSKILLED_INT, serialize(skillsTab.getUnskilledINT()));
        mc.set(Keys.UNSKILLED_STR, serialize(skillsTab.getUnskilledSTR()));
        mc.set(Keys.UNSKILLED_WIS, serialize(skillsTab.getUnskilledWIS()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(PlayerSkill skill) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.SKILL_PROFICIENCY, serialize(skill.getSkillProficiency()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SkillProficiency skillProficiency) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, skillProficiency.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellbookTab spellbookTab, ClassLevels classLevels) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.INVOCATION_COUNT, spellbookTab.getInvocations(classLevels));
        mc.set(Keys.SORCERY_POINTS, spellbookTab.getSorceryPointsMax(classLevels));
        mc.set(Keys.SPELLS, serialize(spellbookTab.getSpells(), this::serialize));
        mc.set(Keys.SPELLCASTER_CLASSES, serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Spell spell) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NEEDS_CONCENTRATION, spell.needsConcentration());
        mc.set(Keys.PREPARED, spell.getPrepared().toString());
        mc.set(Keys.DURATION, spell.getDuration());
        mc.set(Keys.SPELL_LEVEL, spell.getLevel());
        mc.set(Keys.RANGE, spell.getRange());
        mc.set(Keys.SPELL_DAMAGE, serialize(spell.getSpellDamage()));
        mc.set(Keys.SPELL_HEALING, serialize(spell.getSpellHealing()));
        mc.set(Keys.SPELL_DESCRIPTION, spell.getDescription());
        mc.set(Keys.EFFECTS, spell.getEffects());
        mc.set(Keys.SPELL_SAVE, serialize(spell.getSpellSave()));
        mc.set(Keys.SPELLCASTER_CLASS, serialize(spell.getGainedFrom()));
        mc.set(Keys.SPELL_TYPE, serialize(spell.getSpellType()));
        mc.set(Keys.ATTACK_STAT, spell.getAttackStat());
        mc.set(Keys.CAST_TIME, spell.getCastTime());
        mc.set(Keys.TARGET_AREA, spell.getTargetArea());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellDamage spellDamage) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.CAN_CRIT, spellDamage.canCrit());
        mc.set(Keys.DICE, serialize(spellDamage.getDice()));
        mc.set(Keys.BONUS, spellDamage.getBonus());
        mc.set(Keys.DAMAGE_TYPE, spellDamage.getDamageType());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellHealing spellHealing) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.HEAL_AMOUNT, spellHealing.getHealAmount());
        mc.set(Keys.STAT_BONUS, spellHealing.getStatBonus());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellSave spellSave) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.SAVE_DC_TYPE, serialize(spellSave.getSpellcasterClass()));
        mc.set(Keys.ON_SUCCESSFUL_SAVE, spellSave.getOnSuccessfulSave());
        mc.set(Keys.SAVING_STAT, spellSave.getSavingStat());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SaveDCType saveDCType) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.CUSTOM_DC, saveDCType.getSaveDC(null, 0));
        mc.set(Keys.NAME, saveDCType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellcasterClass spellcasterClass) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, spellcasterClass.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(SpellType spellType) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, spellType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(WeaponsTab weaponsTab) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.MELEE_WEAPONS, serialize(weaponsTab.getMeleeWeapons(), this::serialize));
        mc.set(Keys.RANGED_WEAPONS, serialize(weaponsTab.getRangedWeapons(), this::serialize));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(AbstractWeapon weapon, MemoryConfiguration weaponData) {
        weaponData.set(Keys.IS_PROFICIENT, weapon.isProficient());
        weaponData.set(Keys.CRIT_DAMAGE_DICE, serialize(weapon.getCritDamageDice()));
        weaponData.set(Keys.DAMAGE_DICE, serialize(weapon.getDamageDice()));
        weaponData.set(Keys.CRIT_MINIMUM, weapon.getCritMin());
        weaponData.set(Keys.MAGIC_BONUS, weapon.getMagicBonus());
        weaponData.set(Keys.ATTACK_STAT, weapon.getAttackStat());
        weaponData.set(Keys.NAME, weapon.getName());
        return weaponData;
    }

    @Override
    protected MemoryConfiguration serialize(MeleeWeapon meleeWeapon) {
        MemoryConfiguration mc = serialize(meleeWeapon, new MemoryConfiguration());
        mc.set(Keys.PLUS_STAT, meleeWeapon.isPlusStat());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(RangedWeapon rangedWeapon) {
        MemoryConfiguration mc = serialize(rangedWeapon, new MemoryConfiguration());
        mc.set(Keys.AMMO, rangedWeapon.getAmmo());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(CharacterSheet characterSheet) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.ARMOR_TAB, serialize(characterSheet.getArmorTab()));
        mc.set(Keys.BACKGROUND_TAB, serialize(characterSheet.getBackgroundTab()));
        mc.set(Keys.CLASS_TAB, serialize(characterSheet.getClassTab()));
        mc.set(Keys.CORE_STATS_TAB, serialize(characterSheet.getCoreStatsTab()));
        mc.set(Keys.INVENTORY_TAB, serialize(characterSheet.getInventoryTab()));
        mc.set(Keys.SKILLS_TAB, serialize(characterSheet.getSkillsTab()));
        mc.set(Keys.SPELL_BOOK_TAB, serialize(characterSheet.getSpellbookTab(), characterSheet.getClassTab().getClassLevels()));
        mc.set(Keys.WEAPONS_TAB, serialize(characterSheet.getWeaponsTab()));
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(ArmorType armorType) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, armorType.getName());
        return mc;
    }

    @Override
    protected MemoryConfiguration serialize(Recharge recharge) {
        MemoryConfiguration mc = new MemoryConfiguration();
        mc.set(Keys.NAME, recharge.getName());
        return mc;
    }
}
