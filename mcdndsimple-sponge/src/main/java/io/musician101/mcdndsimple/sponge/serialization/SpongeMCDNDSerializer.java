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
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;

public class SpongeMCDNDSerializer extends MCDNDSerializer<ConfigurationNode> {

    @Override
    public ConfigurationNode serialize(PlayerSheet playerSheet) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.BIO_AND_INFO).setValue(serialize(playerSheet.getBioAndInfo()));
        mc.getNode(MCDNDSimpleKeys.PLAYER_SHEET).setValue(serialize(playerSheet.getCharacterSheet()));
        mc.getNode(MCDNDSimpleKeys.CLASS).setValue(playerSheet.getClazz());
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(playerSheet.getName());
        mc.getNode(MCDNDSimpleKeys.RACE).setValue(playerSheet.getRace());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(BioAndInfo bioAndInfo) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(bioAndInfo.getName());
        mc.getNode(MCDNDSimpleKeys.BIO).setValue(bioAndInfo.getBio());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ArmorTab armorTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.ARMOR_CLASS).setValue(armorTab.getArmorClass());
        mc.getNode(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS).setValue(armorTab.getUnarmoredClass());
        mc.getNode(MCDNDSimpleKeys.ARMOR_LIST).setValue(serialize(armorTab.getArmorList(), this::serialize));
        mc.getNode(MCDNDSimpleKeys.UNARMORED_BONUS).setValue(serialize(armorTab.getUnarmoredBonus()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Armor armor) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.SPEED_PENALTY).setValue(armor.hasSpeedPenalty());
        mc.getNode(MCDNDSimpleKeys.STEALTH_PENALTY).setValue(armor.hasStealthPenalty());
        mc.getNode(MCDNDSimpleKeys.UNARMORED).setValue(armor.isUnarmored());
        mc.getNode(MCDNDSimpleKeys.WORN).setValue(armor.isWorn());
        mc.getNode(MCDNDSimpleKeys.BASE_ARMOR_CLASS).setValue(armor.getBaseArmorClass());
        mc.getNode(MCDNDSimpleKeys.MAGIC_BONUS).setValue(armor.getMagicBonus());
        mc.getNode(MCDNDSimpleKeys.ARMOR_TYPE).setValue(serialize(armor.getArmorType()));
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(armor.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(UnarmoredBonus unarmoredBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(unarmoredBonus.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(BackgroundTab backgroundTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.WEIGHT_DOUBLE).setValue(backgroundTab.getWeight());
        mc.getNode(MCDNDSimpleKeys.AGE).setValue(backgroundTab.getAge());
        mc.getNode(MCDNDSimpleKeys.ARMOR_PROFICIENCIES).setValue(backgroundTab.getArmorProficiencies());
        mc.getNode(MCDNDSimpleKeys.BACKGROUND).setValue(backgroundTab.getBackground());
        mc.getNode(MCDNDSimpleKeys.BONDS).setValue(backgroundTab.getBonds());
        mc.getNode(MCDNDSimpleKeys.FLAWS).setValue(backgroundTab.getFlaws());
        mc.getNode(MCDNDSimpleKeys.IDEALS).setValue(backgroundTab.getIdeals());
        mc.getNode(MCDNDSimpleKeys.OTHER_NOTES).setValue(backgroundTab.getOtherNotes());
        mc.getNode(MCDNDSimpleKeys.PERSONALITY_TRAITS).setValue(backgroundTab.getPersonalityTraits());
        mc.getNode(MCDNDSimpleKeys.RACIAL_TRAITS).setValue(backgroundTab.getRacialTraits());
        mc.getNode(MCDNDSimpleKeys.TOOL_PROFICIENCIES).setValue(backgroundTab.getToolProficiencies());
        mc.getNode(MCDNDSimpleKeys.WEAPON_PROFICIENCIES).setValue(backgroundTab.getWeaponProficiencies());
        mc.getNode(MCDNDSimpleKeys.ALIGNMENT).setValue(backgroundTab.getAlignment());
        mc.getNode(MCDNDSimpleKeys.EYES).setValue(backgroundTab.getEyes());
        mc.getNode(MCDNDSimpleKeys.GENDER).setValue(backgroundTab.getGender());
        mc.getNode(MCDNDSimpleKeys.HAIR).setValue(backgroundTab.getHair());
        mc.getNode(MCDNDSimpleKeys.HEIGHT).setValue(backgroundTab.getHeight());
        mc.getNode(MCDNDSimpleKeys.LANGUAGES).setValue(backgroundTab.getLanguages());
        mc.getNode(MCDNDSimpleKeys.SIZE).setValue(backgroundTab.getSize());
        mc.getNode(MCDNDSimpleKeys.VISION).setValue(backgroundTab.getVision());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassTab classTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.CLASS_LEVELS).setValue(serialize(classTab.getClassLevels()));
        mc.getNode(MCDNDSimpleKeys.CLASS_ACTIONS).setValue(serialize(classTab.getClassActions(), this::serialize));
        mc.getNode(MCDNDSimpleKeys.CLASS_RESOURCES).setValue(serialize(classTab.getClassResources(), this::serialize));
        mc.getNode(MCDNDSimpleKeys.CLASS_FEATURE_NOTES).setValue(classTab.getClassFeatureNotes());
        mc.getNode(MCDNDSimpleKeys.OTHER_NOTES).setValue(classTab.getOtherNotes());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassLevels classLevels) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.BARBARIAN_LEVEL).setValue(classLevels.getBarbarian());
        mc.getNode(MCDNDSimpleKeys.BARD_LEVEL).setValue(classLevels.getBard());
        mc.getNode(MCDNDSimpleKeys.CLERIC_LEVEL).setValue(classLevels.getCleric());
        mc.getNode(MCDNDSimpleKeys.DRUID_LEVEL).setValue(classLevels.getDruid());
        mc.getNode(MCDNDSimpleKeys.FIGHTER_LEVEL).setValue(classLevels.getFighter());
        mc.getNode(MCDNDSimpleKeys.MONK_LEVEL).setValue(classLevels.getMonk());
        mc.getNode(MCDNDSimpleKeys.PALADIN_LEVEL).setValue(classLevels.getPaladin());
        mc.getNode(MCDNDSimpleKeys.RANGER_LEVEL).setValue(classLevels.getRanger());
        mc.getNode(MCDNDSimpleKeys.ROGUE_LEVEL).setValue(classLevels.getRogue());
        mc.getNode(MCDNDSimpleKeys.SORCERER_LEVEL).setValue(classLevels.getSorcerer());
        mc.getNode(MCDNDSimpleKeys.WARLOCK_LEVEL).setValue(classLevels.getWarlock());
        mc.getNode(MCDNDSimpleKeys.WIZARD_LEVEL).setValue(classLevels.getWizard());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassAction classAction) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.MAX_USES).setValue(classAction.getMax());
        mc.getNode(MCDNDSimpleKeys.USES_LEFT).setValue(classAction.getUsedCharges());
        mc.getNode(MCDNDSimpleKeys.RECHARGE).setValue(serialize(classAction.getRecharge()));
        mc.getNode(MCDNDSimpleKeys.GAINED_FROM).setValue(classAction.getGainedFrom());
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(classAction.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassResource classResource) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.USES_LEFT).setValue(classResource.getUsesLeft());
        mc.getNode(MCDNDSimpleKeys.MAX_USES).setValue(classResource.getMaxUses());
        mc.getNode(MCDNDSimpleKeys.RECHARGE).setValue(serialize(classResource.getRecharge()));
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(classResource.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CoreStatsTab coreStatsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.BONUSES).setValue(serialize(coreStatsTab.getBonuses()));
        mc.getNode(MCDNDSimpleKeys.CORE_STATS).setValue(serialize(coreStatsTab.getCoreStats()));
        mc.getNode(MCDNDSimpleKeys.EXPERIENCE).setValue(serialize(coreStatsTab.getExperience()));
        mc.getNode(MCDNDSimpleKeys.HIT_DICE).setValue(serialize(coreStatsTab.getHitDice()));
        mc.getNode(MCDNDSimpleKeys.HIT_POINTS).setValue(serialize(coreStatsTab.getHitPoints()));
        mc.getNode(MCDNDSimpleKeys.SPEED).setValue(coreStatsTab.getSpeed());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Bonuses bonuses) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.MELEE_BONUS).setValue(serialize(bonuses.getMelee()));
        mc.getNode(MCDNDSimpleKeys.RANGED_BONUS).setValue(serialize(bonuses.getRanged()));
        mc.getNode(MCDNDSimpleKeys.SPELLCASTING_BONUS).setValue(serialize(bonuses.getSpellcasting()));
        mc.getNode(MCDNDSimpleKeys.SAVES).setValue(bonuses.getSaves());
        mc.getNode(MCDNDSimpleKeys.ABILITIES_AND_SKILLS).setValue(bonuses.getAbilitiesAndSkills());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(MeleeBonus meleeBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.ATTACK).setValue(meleeBonus.getAttack());
        mc.getNode(MCDNDSimpleKeys.DAMAGE).setValue(meleeBonus.getDamage());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(RangedBonus rangedBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.ATTACK).setValue(rangedBonus.getAttack());
        mc.getNode(MCDNDSimpleKeys.DAMAGE).setValue(rangedBonus.getDamage());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellcastingBonus spellcastingBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.ATTACK).setValue(spellcastingBonus.getAttack());
        mc.getNode(MCDNDSimpleKeys.DAMAGE).setValue(spellcastingBonus.getDamage());
        mc.getNode(MCDNDSimpleKeys.SAVE_DC).setValue(spellcastingBonus.getSaveDC());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CoreStats coreStats) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.CHARISMA_SCORE).setValue(serialize(coreStats.getCharisma()));
        mc.getNode(MCDNDSimpleKeys.CONSTITUTION_SCORE).setValue(serialize(coreStats.getConstitution()));
        mc.getNode(MCDNDSimpleKeys.DEXTERITY_SCORE).setValue(serialize(coreStats.getDexterity()));
        mc.getNode(MCDNDSimpleKeys.INTELLIGENCE_SCORE).setValue(serialize(coreStats.getIntelligence()));
        mc.getNode(MCDNDSimpleKeys.STRENGTH_SCORE).setValue(serialize(coreStats.getStrength()));
        mc.getNode(MCDNDSimpleKeys.WISDOM_SCORE).setValue(serialize(coreStats.getWisdom()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(AbilityScore abilityScore) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.PROFICIENT).setValue(abilityScore.isProficient());
        mc.getNode(MCDNDSimpleKeys.SCORE).setValue(abilityScore.getScore());
        mc.getNode(MCDNDSimpleKeys.SHORT_NAME).setValue(abilityScore.getShortName());
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(abilityScore.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Experience experience) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.EXPERIENCE_AMOUNT).setValue(experience.getXP());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(HitDice hitDice) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.HIT_DICE_MAP).setValue(hitDice.getHitDice());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Dice dice) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.AMOUNT).setValue(dice.getAmount());
        mc.getNode(MCDNDSimpleKeys.SIDES).setValue(dice.getSides());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(HitPoints hitPoints) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.CURRENT_HP).setValue(hitPoints.getCurrent());
        mc.getNode(MCDNDSimpleKeys.MAX_HP).setValue(hitPoints.getMax());
        mc.getNode(MCDNDSimpleKeys.TEMP_HP).setValue(hitPoints.getTemp());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(InventoryTab inventoryTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.INVENTORY).setValue(serialize(inventoryTab.getInventory(), this::serialize));
        mc.getNode(MCDNDSimpleKeys.INVENTORY_NOTES).setValue(inventoryTab.getInventoryNotes());
        mc.getNode(MCDNDSimpleKeys.WEALTH).setValue(serialize(inventoryTab.getWealth()));
        mc.getNode(MCDNDSimpleKeys.WEIGHT_CLASS).setValue(serialize(inventoryTab.getWeight()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(MCDNDItem item) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.CARRIED).setValue(item.isCarried());
        mc.getNode(MCDNDSimpleKeys.WEIGHT_DOUBLE).setValue(item.getWeight());
        mc.getNode(MCDNDSimpleKeys.DESCRIPTION).setValue(item.getDescription());
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(item.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Wealth wealth) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.COPPER).setValue(serialize(wealth.getCopper()));
        mc.getNode(MCDNDSimpleKeys.ELECTRUM).setValue(serialize(wealth.getElectrum()));
        mc.getNode(MCDNDSimpleKeys.GOLD).setValue(serialize(wealth.getGold()));
        mc.getNode(MCDNDSimpleKeys.PLATINUM).setValue(serialize(wealth.getPlatinum()));
        mc.getNode(MCDNDSimpleKeys.SILVER).setValue(serialize(wealth.getSilver()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Coin coin) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(coin.getName());
        mc.getNode(MCDNDSimpleKeys.AMOUNT).setValue(coin.getAmount());
        mc.getNode(MCDNDSimpleKeys.SHORT_NAME).setValue(coin.getShortName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Weight weight) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.OTHER).setValue(weight.getOther());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SkillsTab skillsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.ACROBATICS).setValue(serialize(skillsTab.getAcrobatics()));
        mc.getNode(MCDNDSimpleKeys.ANIMAL_HANDLING).setValue(serialize(skillsTab.getAnimalHandling()));
        mc.getNode(MCDNDSimpleKeys.ARCANA).setValue(serialize(skillsTab.getArcana()));
        mc.getNode(MCDNDSimpleKeys.ATHLETICS).setValue(serialize(skillsTab.getAthletics()));
        mc.getNode(MCDNDSimpleKeys.DECEPTION).setValue(serialize(skillsTab.getDeception()));
        mc.getNode(MCDNDSimpleKeys.HISTORY).setValue(serialize(skillsTab.getHistory()));
        mc.getNode(MCDNDSimpleKeys.INSIGHT).setValue(serialize(skillsTab.getInsight()));
        mc.getNode(MCDNDSimpleKeys.INTIMIDATION).setValue(serialize(skillsTab.getIntimidation()));
        mc.getNode(MCDNDSimpleKeys.INVESTIGATION).setValue(serialize(skillsTab.getInvestigation()));
        mc.getNode(MCDNDSimpleKeys.MEDICINE).setValue(serialize(skillsTab.getMedicine()));
        mc.getNode(MCDNDSimpleKeys.NATURE).setValue(serialize(skillsTab.getNature()));
        mc.getNode(MCDNDSimpleKeys.PERFORMANCE).setValue(serialize(skillsTab.getPerformance()));
        mc.getNode(MCDNDSimpleKeys.PERCEPTION).setValue(serialize(skillsTab.getPerception()));
        mc.getNode(MCDNDSimpleKeys.PERSUASION).setValue(serialize(skillsTab.getPersuasion()));
        mc.getNode(MCDNDSimpleKeys.RELIGION).setValue(serialize(skillsTab.getReligion()));
        mc.getNode(MCDNDSimpleKeys.SLEIGHT_OF_HAND).setValue(serialize(skillsTab.getSleightOfHand()));
        mc.getNode(MCDNDSimpleKeys.STEALTH).setValue(serialize(skillsTab.getStealth()));
        mc.getNode(MCDNDSimpleKeys.SURVIVAL).setValue(serialize(skillsTab.getSurvival()));
        mc.getNode(MCDNDSimpleKeys.UNSKILLED_CHA).setValue(serialize(skillsTab.getUnskilledCHA()));
        mc.getNode(MCDNDSimpleKeys.UNSKILLED_CON).setValue(serialize(skillsTab.getUnskilledCON()));
        mc.getNode(MCDNDSimpleKeys.UNSKILLED_DEX).setValue(serialize(skillsTab.getUnskilledDEX()));
        mc.getNode(MCDNDSimpleKeys.UNSKILLED_INT).setValue(serialize(skillsTab.getUnskilledINT()));
        mc.getNode(MCDNDSimpleKeys.UNSKILLED_STR).setValue(serialize(skillsTab.getUnskilledSTR()));
        mc.getNode(MCDNDSimpleKeys.UNSKILLED_WIS).setValue(serialize(skillsTab.getUnskilledWIS()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Skill skill) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.SKILL_PROFICIENCY).setValue(serialize(skill.getSkillProficiency()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SkillProficiency skillProficiency) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(skillProficiency.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellbookTab spellbookTab, ClassLevels classLevels) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.INVOCATION_COUNT).setValue(spellbookTab.getInvocations(classLevels));
        mc.getNode(MCDNDSimpleKeys.SORCERY_POINTS).setValue(spellbookTab.getSorceryPointsMax(classLevels));
        mc.getNode(MCDNDSimpleKeys.SPELLS).setValue(serialize(spellbookTab.getSpells(), this::serialize));
        mc.getNode(MCDNDSimpleKeys.SPELLCASTER_CLASSES).setValue(serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Spell spell) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NEEDS_CONCENTRATION).setValue(spell.needsConcentration());
        mc.getNode(MCDNDSimpleKeys.PREPARED).setValue(spell.getPrepared().toString());
        mc.getNode(MCDNDSimpleKeys.DURATION).setValue(spell.getDuration());
        mc.getNode(MCDNDSimpleKeys.SPELL_LEVEL).setValue(spell.getLevel());
        mc.getNode(MCDNDSimpleKeys.RANGE).setValue(spell.getRange());
        mc.getNode(MCDNDSimpleKeys.SPELL_DAMAGE).setValue(serialize(spell.getSpellDamage()));
        mc.getNode(MCDNDSimpleKeys.SPELL_HEALING).setValue(serialize(spell.getSpellHealing()));
        mc.getNode(MCDNDSimpleKeys.SPELL_DESCRIPTION).setValue(spell.getDescription());
        mc.getNode(MCDNDSimpleKeys.EFFECTS).setValue(spell.getEffects());
        mc.getNode(MCDNDSimpleKeys.SPELL_SAVE).setValue(serialize(spell.getSpellSave()));
        mc.getNode(MCDNDSimpleKeys.SPELLCASTER_CLASS).setValue(serialize(spell.getGainedFrom()));
        mc.getNode(MCDNDSimpleKeys.SPELL_TYPE).setValue(serialize(spell.getSpellType()));
        mc.getNode(MCDNDSimpleKeys.ATTACK_STAT).setValue(spell.getAttackStat().getName());
        mc.getNode(MCDNDSimpleKeys.CAST_TIME).setValue(spell.getCastTime());
        mc.getNode(MCDNDSimpleKeys.TARGET_AREA).setValue(spell.getTargetArea());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellDamage spellDamage) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.CAN_CRIT).setValue(spellDamage.canCrit());
        mc.getNode(MCDNDSimpleKeys.DICE).setValue(serialize(spellDamage.getDice()));
        mc.getNode(MCDNDSimpleKeys.BONUS).setValue(spellDamage.getBonus());
        mc.getNode(MCDNDSimpleKeys.DAMAGE_TYPE).setValue(spellDamage.getDamageType());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellHealing spellHealing) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.HEAL_AMOUNT).setValue(spellHealing.getHealAmount());
        mc.getNode(MCDNDSimpleKeys.STAT_BONUS).setValue(spellHealing.getStatBonus().getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellSave spellSave) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.SAVE_DC_TYPE).setValue(serialize(spellSave.getSpellcasterClass()));
        mc.getNode(MCDNDSimpleKeys.ON_SUCCESSFUL_SAVE).setValue(spellSave.getOnSuccessfulSave());
        mc.getNode(MCDNDSimpleKeys.SAVING_STAT).setValue(spellSave.getSavingStat());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SaveDCType saveDCType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.CUSTOM_DC).setValue(saveDCType.getSaveDC(null, 0));
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(saveDCType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellcasterClass spellcasterClass) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(spellcasterClass.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellType spellType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(spellType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(WeaponsTab weaponsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.MELEE_WEAPONS).setValue(serialize(weaponsTab.getMeleeWeapons(), this::serialize));
        mc.getNode(MCDNDSimpleKeys.RANGED_WEAPONS).setValue(serialize(weaponsTab.getRangedWeapons(), this::serialize));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(AbstractWeapon weapon, ConfigurationNode weaponData) {
        weaponData.getNode(MCDNDSimpleKeys.IS_PROFICIENT).setValue(weapon.isProficient());
        weaponData.getNode(MCDNDSimpleKeys.CRIT_DAMAGE_DICE).setValue(serialize(weapon.getCritDamageDice()));
        weaponData.getNode(MCDNDSimpleKeys.DAMAGE_DICE).setValue(serialize(weapon.getDamageDice()));
        weaponData.getNode(MCDNDSimpleKeys.CRIT_MINIMUM).setValue(weapon.getCritMin());
        weaponData.getNode(MCDNDSimpleKeys.MAGIC_BONUS).setValue(weapon.getMagicBonus());
        weaponData.getNode(MCDNDSimpleKeys.ATTACK_STAT).setValue(weapon.getAttackStat());
        weaponData.getNode(MCDNDSimpleKeys.NAME).setValue(weapon.getName());
        return weaponData;
    }

    @Override
    protected ConfigurationNode serialize(MeleeWeapon meleeWeapon) {
        ConfigurationNode mc = serialize(meleeWeapon, SimpleConfigurationNode.root());
        mc.getNode(MCDNDSimpleKeys.PLUS_STAT).setValue(meleeWeapon.isPlusStat());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(RangedWeapon rangedWeapon) {
        ConfigurationNode mc = serialize(rangedWeapon, SimpleConfigurationNode.root());
        mc.getNode(MCDNDSimpleKeys.AMMO).setValue(rangedWeapon.getAmmo());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CharacterSheet characterSheet) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.ARMOR_TAB).setValue(serialize(characterSheet.getArmorTab()));
        mc.getNode(MCDNDSimpleKeys.BACKGROUND_TAB).setValue(serialize(characterSheet.getBackgroundTab()));
        mc.getNode(MCDNDSimpleKeys.CLASS_TAB).setValue(serialize(characterSheet.getClassTab()));
        mc.getNode(MCDNDSimpleKeys.CORE_STATS_TAB).setValue(serialize(characterSheet.getCoreStatsTab()));
        mc.getNode(MCDNDSimpleKeys.INVENTORY_TAB).setValue(serialize(characterSheet.getInventoryTab()));
        mc.getNode(MCDNDSimpleKeys.SKILLS_TAB).setValue(serialize(characterSheet.getSkillsTab()));
        mc.getNode(MCDNDSimpleKeys.SPELL_BOOK_TAB).setValue(serialize(characterSheet.getSpellbookTab(), characterSheet.getClassTab().getClassLevels()));
        mc.getNode(MCDNDSimpleKeys.WEAPONS_TAB).setValue(serialize(characterSheet.getWeaponsTab()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ArmorType armorType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(armorType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Recharge recharge) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(MCDNDSimpleKeys.NAME).setValue(recharge.getName());
        return mc;
    }
}
