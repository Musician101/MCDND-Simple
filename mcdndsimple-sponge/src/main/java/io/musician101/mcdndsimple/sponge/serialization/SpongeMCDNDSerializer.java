package io.musician101.mcdndsimple.sponge.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
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
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
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
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;

public class SpongeMCDNDSerializer extends MCDNDSerializer<ConfigurationNode> {

    @Override
    public ConfigurationNode serialize(PlayerSheet playerSheet) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.BIO_AND_INFO).setValue(serialize(playerSheet.getBioAndInfo()));
        mc.getNode(JsonUtils.PLAYER_SHEET).setValue(serialize(playerSheet.getCharacterSheet()));
        mc.getNode(JsonUtils.CLASS).setValue(playerSheet.getClazz());
        mc.getNode(JsonUtils.NAME).setValue(playerSheet.getName());
        mc.getNode(JsonUtils.RACE).setValue(playerSheet.getRace());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(BioAndInfo bioAndInfo) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(bioAndInfo.getName());
        mc.getNode(JsonUtils.BIO).setValue(bioAndInfo.getBio());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ArmorTab armorTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.ARMOR_CLASS).setValue(armorTab.getArmorClass());
        mc.getNode(JsonUtils.UNARMORED_ARMOR_CLASS).setValue(armorTab.getUnarmoredClass());
        mc.getNode(JsonUtils.ARMOR_LIST).setValue(serialize(armorTab.getArmorList(), this::serialize));
        mc.getNode(JsonUtils.UNARMORED_BONUS).setValue(serialize(armorTab.getUnarmoredBonus()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Armor armor) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.SPEED_PENALTY).setValue(armor.hasSpeedPenalty());
        mc.getNode(JsonUtils.STEALTH_PENALTY).setValue(armor.hasStealthPenalty());
        mc.getNode(JsonUtils.UNARMORED).setValue(armor.isUnarmored());
        mc.getNode(JsonUtils.WORN).setValue(armor.isWorn());
        mc.getNode(JsonUtils.BASE_ARMOR_CLASS).setValue(armor.getBaseArmorClass());
        mc.getNode(JsonUtils.MAGIC_BONUS).setValue(armor.getMagicBonus());
        mc.getNode(JsonUtils.ARMOR_TYPE).setValue(serialize(armor.getArmorType()));
        mc.getNode(JsonUtils.NAME).setValue(armor.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(UnarmoredBonus unarmoredBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(unarmoredBonus.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(BackgroundTab backgroundTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.WEIGHT_DOUBLE).setValue(backgroundTab.getWeight());
        mc.getNode(JsonUtils.AGE).setValue(backgroundTab.getAge());
        mc.getNode(JsonUtils.ARMOR_PROFICIENCIES).setValue(backgroundTab.getArmorProficiencies());
        mc.getNode(JsonUtils.BACKGROUND).setValue(backgroundTab.getBackground());
        mc.getNode(JsonUtils.BONDS).setValue(backgroundTab.getBonds());
        mc.getNode(JsonUtils.FLAWS).setValue(backgroundTab.getFlaws());
        mc.getNode(JsonUtils.IDEALS).setValue(backgroundTab.getIdeals());
        mc.getNode(JsonUtils.OTHER_NOTES).setValue(backgroundTab.getOtherNotes());
        mc.getNode(JsonUtils.PERSONALITY_TRAITS).setValue(backgroundTab.getPersonalityTraits());
        mc.getNode(JsonUtils.RACIAL_TRAITS).setValue(backgroundTab.getRacialTraits());
        mc.getNode(JsonUtils.TOOL_PROFICIENCIES).setValue(backgroundTab.getToolProficiencies());
        mc.getNode(JsonUtils.WEAPON_PROFICIENCIES).setValue(backgroundTab.getWeaponProficiencies());
        mc.getNode(JsonUtils.ALIGNMENT).setValue(backgroundTab.getAlignment());
        mc.getNode(JsonUtils.EYES).setValue(backgroundTab.getEyes());
        mc.getNode(JsonUtils.GENDER).setValue(backgroundTab.getGender());
        mc.getNode(JsonUtils.HAIR).setValue(backgroundTab.getHair());
        mc.getNode(JsonUtils.HEIGHT).setValue(backgroundTab.getHeight());
        mc.getNode(JsonUtils.LANGUAGES).setValue(backgroundTab.getLanguages());
        mc.getNode(JsonUtils.SIZE).setValue(backgroundTab.getSize());
        mc.getNode(JsonUtils.VISION).setValue(backgroundTab.getVision());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassTab classTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.CLASS_LEVELS).setValue(serialize(classTab.getClassLevels()));
        mc.getNode(JsonUtils.CLASS_ACTIONS).setValue(serialize(classTab.getClassActions(), this::serialize));
        mc.getNode(JsonUtils.CLASS_RESOURCES).setValue(serialize(classTab.getClassResources(), this::serialize));
        mc.getNode(JsonUtils.CLASS_FEATURE_NOTES).setValue(classTab.getClassFeatureNotes());
        mc.getNode(JsonUtils.OTHER_NOTES).setValue(classTab.getOtherNotes());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassLevels classLevels) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.BARBARIAN_LEVEL).setValue(classLevels.getBarbarian());
        mc.getNode(JsonUtils.BARD_LEVEL).setValue(classLevels.getBard());
        mc.getNode(JsonUtils.CLERIC_LEVEL).setValue(classLevels.getCleric());
        mc.getNode(JsonUtils.DRUID_LEVEL).setValue(classLevels.getDruid());
        mc.getNode(JsonUtils.FIGHTER_LEVEL).setValue(classLevels.getFighter());
        mc.getNode(JsonUtils.MONK_LEVEL).setValue(classLevels.getMonk());
        mc.getNode(JsonUtils.PALADIN_LEVEL).setValue(classLevels.getPaladin());
        mc.getNode(JsonUtils.RANGER_LEVEL).setValue(classLevels.getRanger());
        mc.getNode(JsonUtils.ROGUE_LEVEL).setValue(classLevels.getRogue());
        mc.getNode(JsonUtils.SORCERER_LEVEL).setValue(classLevels.getSorcerer());
        mc.getNode(JsonUtils.WARLOCK_LEVEL).setValue(classLevels.getWarlock());
        mc.getNode(JsonUtils.WIZARD_LEVEL).setValue(classLevels.getWizard());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassAction classAction) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.MAX_USES).setValue(classAction.getMaxUses());
        mc.getNode(JsonUtils.USES_LEFT).setValue(classAction.getUsed());
        mc.getNode(JsonUtils.RECHARGE).setValue(serialize(classAction.getRecharge()));
        mc.getNode(JsonUtils.GAINED_FROM).setValue(classAction.getGainedFrom());
        mc.getNode(JsonUtils.NAME).setValue(classAction.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassResource classResource) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.USES_LEFT).setValue(classResource.getUsesLeft());
        mc.getNode(JsonUtils.MAX_USES).setValue(classResource.getMaxUses());
        mc.getNode(JsonUtils.RECHARGE).setValue(serialize(classResource.getRecharge()));
        mc.getNode(JsonUtils.NAME).setValue(classResource.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CoreStatsTab coreStatsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.BONUSES).setValue(serialize(coreStatsTab.getBonuses()));
        mc.getNode(JsonUtils.CORE_STATS).setValue(serialize(coreStatsTab.getCoreStats()));
        mc.getNode(JsonUtils.EXPERIENCE).setValue(serialize(coreStatsTab.getExperience()));
        mc.getNode(JsonUtils.HIT_DICE).setValue(serialize(coreStatsTab.getHitDice()));
        mc.getNode(JsonUtils.HIT_POINTS).setValue(serialize(coreStatsTab.getHitPoints()));
        mc.getNode(JsonUtils.SPEED).setValue(coreStatsTab.getSpeed());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Bonuses bonuses) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.MELEE_BONUS).setValue(serialize(bonuses.getMelee()));
        mc.getNode(JsonUtils.RANGED_BONUS).setValue(serialize(bonuses.getRanged()));
        mc.getNode(JsonUtils.SPELLCASTING_BONUS).setValue(serialize(bonuses.getSpellcasting()));
        mc.getNode(JsonUtils.SAVES).setValue(bonuses.getSaves());
        mc.getNode(JsonUtils.ABILITIES_AND_SKILLS).setValue(bonuses.getAbilitiesAndSkills());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(MeleeBonus meleeBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.ATTACK).setValue(meleeBonus.getAttack());
        mc.getNode(JsonUtils.DAMAGE).setValue(meleeBonus.getDamage());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(RangedBonus rangedBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.ATTACK).setValue(rangedBonus.getAttack());
        mc.getNode(JsonUtils.DAMAGE).setValue(rangedBonus.getDamage());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellcastingBonus spellcastingBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.ATTACK).setValue(spellcastingBonus.getAttack());
        mc.getNode(JsonUtils.DAMAGE).setValue(spellcastingBonus.getDamage());
        mc.getNode(JsonUtils.SAVE_DC).setValue(spellcastingBonus.getSaveDC());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CoreStats coreStats) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.CHARISMA_SCORE).setValue(serialize(coreStats.getCharisma()));
        mc.getNode(JsonUtils.CONSTITUTION_SCORE).setValue(serialize(coreStats.getConstitution()));
        mc.getNode(JsonUtils.DEXTERITY_SCORE).setValue(serialize(coreStats.getDexterity()));
        mc.getNode(JsonUtils.INTELLIGENCE_SCORE).setValue(serialize(coreStats.getIntelligence()));
        mc.getNode(JsonUtils.STRENGTH_SCORE).setValue(serialize(coreStats.getStrength()));
        mc.getNode(JsonUtils.WISDOM_SCORE).setValue(serialize(coreStats.getWisdom()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(AbilityScore abilityScore) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.PROFICIENT).setValue(abilityScore.isProficient());
        mc.getNode(JsonUtils.SCORE).setValue(abilityScore.getScore());
        mc.getNode(JsonUtils.SHORT_NAME).setValue(abilityScore.getShortName());
        mc.getNode(JsonUtils.NAME).setValue(abilityScore.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Experience experience) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.EXPERIENCE_AMOUNT).setValue(experience.getXP());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(HitDice hitDice) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.HIT_DICE_MAP).setValue(hitDice.getHitDice());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Dice dice) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.AMOUNT).setValue(dice.getAmount());
        mc.getNode(JsonUtils.SIDES).setValue(dice.getSides());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(HitPoints hitPoints) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.CURRENT_HP).setValue(hitPoints.getCurrent());
        mc.getNode(JsonUtils.MAX_HP).setValue(hitPoints.getMax());
        mc.getNode(JsonUtils.TEMP_HP).setValue(hitPoints.getTemp());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(InventoryTab inventoryTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.INVENTORY).setValue(serialize(inventoryTab.getInventory(), this::serialize));
        mc.getNode(JsonUtils.INVENTORY_NOTES).setValue(inventoryTab.getInventoryNotes());
        mc.getNode(JsonUtils.WEALTH).setValue(serialize(inventoryTab.getWealth()));
        mc.getNode(JsonUtils.WEIGHT_CLASS).setValue(serialize(inventoryTab.getWeight()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(MCDNDItem item) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.CARRIED).setValue(item.isCarried());
        mc.getNode(JsonUtils.WEIGHT_DOUBLE).setValue(item.getWeight());
        mc.getNode(JsonUtils.DESCRIPTION).setValue(item.getDescription());
        mc.getNode(JsonUtils.NAME).setValue(item.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Wealth wealth) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.COPPER).setValue(serialize(wealth.getCopper()));
        mc.getNode(JsonUtils.ELECTRUM).setValue(serialize(wealth.getElectrum()));
        mc.getNode(JsonUtils.GOLD).setValue(serialize(wealth.getGold()));
        mc.getNode(JsonUtils.PLATINUM).setValue(serialize(wealth.getPlatinum()));
        mc.getNode(JsonUtils.SILVER).setValue(serialize(wealth.getSilver()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Coin coin) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(coin.getName());
        mc.getNode(JsonUtils.AMOUNT).setValue(coin.getAmount());
        mc.getNode(JsonUtils.SHORT_NAME).setValue(coin.getShortName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Weight weight) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.OTHER).setValue(weight.getOther());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SkillsTab skillsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.ACROBATICS).setValue(serialize(skillsTab.getAcrobatics()));
        mc.getNode(JsonUtils.ANIMAL_HANDLING).setValue(serialize(skillsTab.getAnimalHandling()));
        mc.getNode(JsonUtils.ARCANA).setValue(serialize(skillsTab.getArcana()));
        mc.getNode(JsonUtils.ATHLETICS).setValue(serialize(skillsTab.getAthletics()));
        mc.getNode(JsonUtils.DECEPTION).setValue(serialize(skillsTab.getDeception()));
        mc.getNode(JsonUtils.HISTORY).setValue(serialize(skillsTab.getHistory()));
        mc.getNode(JsonUtils.INSIGHT).setValue(serialize(skillsTab.getInsight()));
        mc.getNode(JsonUtils.INTIMIDATION).setValue(serialize(skillsTab.getIntimidation()));
        mc.getNode(JsonUtils.INVESTIGATION).setValue(serialize(skillsTab.getInvestigation()));
        mc.getNode(JsonUtils.MEDICINE).setValue(serialize(skillsTab.getMedicine()));
        mc.getNode(JsonUtils.NATURE).setValue(serialize(skillsTab.getNature()));
        mc.getNode(JsonUtils.PERFORMANCE).setValue(serialize(skillsTab.getPerformance()));
        mc.getNode(JsonUtils.PERCEPTION).setValue(serialize(skillsTab.getPerception()));
        mc.getNode(JsonUtils.PERSUASION).setValue(serialize(skillsTab.getPersuasion()));
        mc.getNode(JsonUtils.RELIGION).setValue(serialize(skillsTab.getReligion()));
        mc.getNode(JsonUtils.SLEIGHT_OF_HAND).setValue(serialize(skillsTab.getSleightOfHand()));
        mc.getNode(JsonUtils.STEALTH).setValue(serialize(skillsTab.getStealth()));
        mc.getNode(JsonUtils.SURVIVAL).setValue(serialize(skillsTab.getSurvival()));
        mc.getNode(JsonUtils.UNSKILLED_CHA).setValue(serialize(skillsTab.getUnskilledCHA()));
        mc.getNode(JsonUtils.UNSKILLED_CON).setValue(serialize(skillsTab.getUnskilledCON()));
        mc.getNode(JsonUtils.UNSKILLED_DEX).setValue(serialize(skillsTab.getUnskilledDEX()));
        mc.getNode(JsonUtils.UNSKILLED_INT).setValue(serialize(skillsTab.getUnskilledINT()));
        mc.getNode(JsonUtils.UNSKILLED_STR).setValue(serialize(skillsTab.getUnskilledSTR()));
        mc.getNode(JsonUtils.UNSKILLED_WIS).setValue(serialize(skillsTab.getUnskilledWIS()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(PlayerSkill skill) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.SKILL_PROFICIENCY).setValue(serialize(skill.getSkillProficiency()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SkillProficiency skillProficiency) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(skillProficiency.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellbookTab spellbookTab, ClassLevels classLevels) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.INVOCATION_COUNT).setValue(spellbookTab.getInvocations(classLevels));
        mc.getNode(JsonUtils.SORCERY_POINTS).setValue(spellbookTab.getSorceryPointsMax(classLevels));
        mc.getNode(JsonUtils.SPELLS).setValue(serialize(spellbookTab.getSpells(), this::serialize));
        mc.getNode(JsonUtils.SPELLCASTER_CLASSES).setValue(serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Spell spell) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NEEDS_CONCENTRATION).setValue(spell.needsConcentration());
        mc.getNode(JsonUtils.PREPARED).setValue(spell.getPrepared().toString());
        mc.getNode(JsonUtils.DURATION).setValue(spell.getDuration());
        mc.getNode(JsonUtils.SPELL_LEVEL).setValue(spell.getLevel());
        mc.getNode(JsonUtils.RANGE).setValue(spell.getRange());
        mc.getNode(JsonUtils.SPELL_DAMAGE).setValue(serialize(spell.getSpellDamage()));
        mc.getNode(JsonUtils.SPELL_HEALING).setValue(serialize(spell.getSpellHealing()));
        mc.getNode(JsonUtils.SPELL_DESCRIPTION).setValue(spell.getDescription());
        mc.getNode(JsonUtils.EFFECTS).setValue(spell.getEffects());
        mc.getNode(JsonUtils.SPELL_SAVE).setValue(serialize(spell.getSpellSave()));
        mc.getNode(JsonUtils.SPELLCASTER_CLASS).setValue(serialize(spell.getGainedFrom()));
        mc.getNode(JsonUtils.SPELL_TYPE).setValue(serialize(spell.getSpellType()));
        mc.getNode(JsonUtils.ATTACK_STAT).setValue(spell.getAttackStat().getName());
        mc.getNode(JsonUtils.CAST_TIME).setValue(spell.getCastTime());
        mc.getNode(JsonUtils.TARGET_AREA).setValue(spell.getTargetArea());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellDamage spellDamage) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.CAN_CRIT).setValue(spellDamage.canCrit());
        mc.getNode(JsonUtils.DICE).setValue(serialize(spellDamage.getDamage()));
        mc.getNode(JsonUtils.BONUS).setValue(spellDamage.getBonus());
        mc.getNode(JsonUtils.DAMAGE_TYPE).setValue(spellDamage.getDamageType());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellHealing spellHealing) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.HEAL_AMOUNT).setValue(spellHealing.getHealAmount());
        mc.getNode(JsonUtils.STAT_BONUS).setValue(spellHealing.getStatBonus().getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellSave spellSave) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.SAVE_DC_TYPE).setValue(serialize(spellSave.getSaveDCType()));
        mc.getNode(JsonUtils.ON_SUCCESSFUL_SAVE).setValue(spellSave.getOnSuccessfulSave());
        mc.getNode(JsonUtils.SAVING_STAT).setValue(spellSave.getSavingStat());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SaveDCType saveDCType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.CUSTOM_DC).setValue(saveDCType.getSaveDC(null, 0));
        mc.getNode(JsonUtils.NAME).setValue(saveDCType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellcasterClass spellcasterClass) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(spellcasterClass.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellType spellType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(spellType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(WeaponsTab weaponsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.MELEE_WEAPONS).setValue(serialize(weaponsTab.getMeleeWeapons(), this::serialize));
        mc.getNode(JsonUtils.RANGED_WEAPONS).setValue(serialize(weaponsTab.getRangedWeapons(), this::serialize));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(AbstractWeapon weapon, ConfigurationNode weaponData) {
        weaponData.getNode(JsonUtils.IS_PROFICIENT).setValue(weapon.isProficient());
        weaponData.getNode(JsonUtils.CRIT_DAMAGE_DICE).setValue(serialize(weapon.getCritDamage()));
        weaponData.getNode(JsonUtils.DAMAGE_DICE).setValue(serialize(weapon.getDamage()));
        weaponData.getNode(JsonUtils.CRIT_MINIMUM).setValue(weapon.getCritMin());
        weaponData.getNode(JsonUtils.MAGIC_BONUS).setValue(weapon.getMagicBonus());
        weaponData.getNode(JsonUtils.ATTACK_STAT).setValue(weapon.getAttackStat());
        weaponData.getNode(JsonUtils.NAME).setValue(weapon.getName());
        return weaponData;
    }

    @Override
    protected ConfigurationNode serialize(MeleeWeapon meleeWeapon) {
        ConfigurationNode mc = serialize(meleeWeapon, SimpleConfigurationNode.root());
        mc.getNode(JsonUtils.PLUS_STAT).setValue(meleeWeapon.plusStat());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(RangedWeapon rangedWeapon) {
        ConfigurationNode mc = serialize(rangedWeapon, SimpleConfigurationNode.root());
        mc.getNode(JsonUtils.AMMO).setValue(rangedWeapon.getAmmo());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CharacterSheet characterSheet) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.ARMOR_TAB).setValue(serialize(characterSheet.getArmorTab()));
        mc.getNode(JsonUtils.BACKGROUND_TAB).setValue(serialize(characterSheet.getBackgroundTab()));
        mc.getNode(JsonUtils.CLASS_TAB).setValue(serialize(characterSheet.getClassTab()));
        mc.getNode(JsonUtils.CORE_STATS_TAB).setValue(serialize(characterSheet.getCoreStatsTab()));
        mc.getNode(JsonUtils.INVENTORY_TAB).setValue(serialize(characterSheet.getInventoryTab()));
        mc.getNode(JsonUtils.SKILLS_TAB).setValue(serialize(characterSheet.getSkillsTab()));
        mc.getNode(JsonUtils.SPELL_BOOK_TAB).setValue(serialize(characterSheet.getSpellbookTab(), characterSheet.getClassTab().getClassLevels()));
        mc.getNode(JsonUtils.WEAPONS_TAB).setValue(serialize(characterSheet.getWeaponsTab()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ArmorType armorType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(armorType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Recharge recharge) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(JsonUtils.NAME).setValue(recharge.getName());
        return mc;
    }
}
