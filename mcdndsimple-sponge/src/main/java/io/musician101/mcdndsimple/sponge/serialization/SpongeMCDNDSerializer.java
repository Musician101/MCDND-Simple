package io.musician101.mcdndsimple.sponge.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.ClassAction;
import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.ClassResource;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.HitPoints;
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
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;

public class SpongeMCDNDSerializer extends MCDNDSerializer<ConfigurationNode> {

    @Override
    public ConfigurationNode serialize(PlayerSheet playerSheet) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.BIO_AND_INFO).setValue(serialize(playerSheet.getBioAndInfo()));
        mc.getNode(Keys.PLAYER_SHEET).setValue(serialize(playerSheet.getCharacterSheet()));
        mc.getNode(Keys.CLASS).setValue(playerSheet.getClazz());
        mc.getNode(Keys.NAME).setValue(playerSheet.getName());
        mc.getNode(Keys.RACE).setValue(playerSheet.getRace());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(BioAndInfo bioAndInfo) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(bioAndInfo.getName());
        mc.getNode(Keys.BIO).setValue(bioAndInfo.getBio());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ArmorTab armorTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.ARMOR_CLASS).setValue(armorTab.getArmorClass());
        mc.getNode(Keys.UNARMORED_ARMOR_CLASS).setValue(armorTab.getUnarmoredClass());
        mc.getNode(Keys.ARMOR_LIST).setValue(serialize(armorTab.getArmorList(), this::serialize));
        mc.getNode(Keys.UNARMORED_BONUS).setValue(serialize(armorTab.getUnarmoredBonus()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Armor armor) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.SPEED_PENALTY).setValue(armor.hasSpeedPenalty());
        mc.getNode(Keys.STEALTH_PENALTY).setValue(armor.hasStealthPenalty());
        mc.getNode(Keys.UNARMORED).setValue(armor.isUnarmored());
        mc.getNode(Keys.WORN).setValue(armor.isWorn());
        mc.getNode(Keys.BASE_ARMOR_CLASS).setValue(armor.getBaseArmorClass());
        mc.getNode(Keys.MAGIC_BONUS).setValue(armor.getMagicBonus());
        mc.getNode(Keys.ARMOR_TYPE).setValue(serialize(armor.getArmorType()));
        mc.getNode(Keys.NAME).setValue(armor.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(UnarmoredBonus unarmoredBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(unarmoredBonus.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(BackgroundTab backgroundTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.WEIGHT_DOUBLE).setValue(backgroundTab.getWeight());
        mc.getNode(Keys.AGE).setValue(backgroundTab.getAge());
        mc.getNode(Keys.ARMOR_PROFICIENCIES).setValue(backgroundTab.getArmorProficiencies());
        mc.getNode(Keys.BACKGROUND).setValue(backgroundTab.getBackground());
        mc.getNode(Keys.BONDS).setValue(backgroundTab.getBonds());
        mc.getNode(Keys.FLAWS).setValue(backgroundTab.getFlaws());
        mc.getNode(Keys.IDEALS).setValue(backgroundTab.getIdeals());
        mc.getNode(Keys.OTHER_NOTES).setValue(backgroundTab.getOtherNotes());
        mc.getNode(Keys.PERSONALITY_TRAITS).setValue(backgroundTab.getPersonalityTraits());
        mc.getNode(Keys.RACIAL_TRAITS).setValue(backgroundTab.getRacialTraits());
        mc.getNode(Keys.TOOL_PROFICIENCIES).setValue(backgroundTab.getToolProficiencies());
        mc.getNode(Keys.WEAPON_PROFICIENCIES).setValue(backgroundTab.getWeaponProficiencies());
        mc.getNode(Keys.ALIGNMENT).setValue(backgroundTab.getAlignment());
        mc.getNode(Keys.EYES).setValue(backgroundTab.getEyes());
        mc.getNode(Keys.GENDER).setValue(backgroundTab.getGender());
        mc.getNode(Keys.HAIR).setValue(backgroundTab.getHair());
        mc.getNode(Keys.HEIGHT).setValue(backgroundTab.getHeight());
        mc.getNode(Keys.LANGUAGES).setValue(backgroundTab.getLanguages());
        mc.getNode(Keys.SIZE).setValue(backgroundTab.getSize());
        mc.getNode(Keys.VISION).setValue(backgroundTab.getVision());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassTab classTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.CLASS_LEVELS).setValue(serialize(classTab.getClassLevels()));
        mc.getNode(Keys.CLASS_ACTIONS).setValue(serialize(classTab.getClassActions(), this::serialize));
        mc.getNode(Keys.CLASS_RESOURCES).setValue(serialize(classTab.getClassResources(), this::serialize));
        mc.getNode(Keys.CLASS_FEATURE_NOTES).setValue(classTab.getClassFeatureNotes());
        mc.getNode(Keys.OTHER_NOTES).setValue(classTab.getOtherNotes());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassLevels classLevels) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.BARBARIAN_LEVEL).setValue(classLevels.getBarbarian());
        mc.getNode(Keys.BARD_LEVEL).setValue(classLevels.getBard());
        mc.getNode(Keys.CLERIC_LEVEL).setValue(classLevels.getCleric());
        mc.getNode(Keys.DRUID_LEVEL).setValue(classLevels.getDruid());
        mc.getNode(Keys.FIGHTER_LEVEL).setValue(classLevels.getFighter());
        mc.getNode(Keys.MONK_LEVEL).setValue(classLevels.getMonk());
        mc.getNode(Keys.PALADIN_LEVEL).setValue(classLevels.getPaladin());
        mc.getNode(Keys.RANGER_LEVEL).setValue(classLevels.getRanger());
        mc.getNode(Keys.ROGUE_LEVEL).setValue(classLevels.getRogue());
        mc.getNode(Keys.SORCERER_LEVEL).setValue(classLevels.getSorcerer());
        mc.getNode(Keys.WARLOCK_LEVEL).setValue(classLevels.getWarlock());
        mc.getNode(Keys.WIZARD_LEVEL).setValue(classLevels.getWizard());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassAction classAction) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.MAX_USES).setValue(classAction.getMax());
        mc.getNode(Keys.USES_LEFT).setValue(classAction.getUsedCharges());
        mc.getNode(Keys.RECHARGE).setValue(serialize(classAction.getRecharge()));
        mc.getNode(Keys.GAINED_FROM).setValue(classAction.getGainedFrom());
        mc.getNode(Keys.NAME).setValue(classAction.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ClassResource classResource) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.USES_LEFT).setValue(classResource.getUsesLeft());
        mc.getNode(Keys.MAX_USES).setValue(classResource.getMaxUses());
        mc.getNode(Keys.RECHARGE).setValue(serialize(classResource.getRecharge()));
        mc.getNode(Keys.NAME).setValue(classResource.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CoreStatsTab coreStatsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.BONUSES).setValue(serialize(coreStatsTab.getBonuses()));
        mc.getNode(Keys.CORE_STATS).setValue(serialize(coreStatsTab.getCoreStats()));
        mc.getNode(Keys.EXPERIENCE).setValue(serialize(coreStatsTab.getExperience()));
        mc.getNode(Keys.HIT_DICE).setValue(serialize(coreStatsTab.getHitDice()));
        mc.getNode(Keys.HIT_POINTS).setValue(serialize(coreStatsTab.getHitPoints()));
        mc.getNode(Keys.SPEED).setValue(coreStatsTab.getSpeed());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Bonuses bonuses) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.MELEE_BONUS).setValue(serialize(bonuses.getMelee()));
        mc.getNode(Keys.RANGED_BONUS).setValue(serialize(bonuses.getRanged()));
        mc.getNode(Keys.SPELLCASTING_BONUS).setValue(serialize(bonuses.getSpellcasting()));
        mc.getNode(Keys.SAVES).setValue(bonuses.getSaves());
        mc.getNode(Keys.ABILITIES_AND_SKILLS).setValue(bonuses.getAbilitiesAndSkills());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(MeleeBonus meleeBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.ATTACK).setValue(meleeBonus.getAttack());
        mc.getNode(Keys.DAMAGE).setValue(meleeBonus.getDamage());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(RangedBonus rangedBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.ATTACK).setValue(rangedBonus.getAttack());
        mc.getNode(Keys.DAMAGE).setValue(rangedBonus.getDamage());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellcastingBonus spellcastingBonus) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.ATTACK).setValue(spellcastingBonus.getAttack());
        mc.getNode(Keys.DAMAGE).setValue(spellcastingBonus.getDamage());
        mc.getNode(Keys.SAVE_DC).setValue(spellcastingBonus.getSaveDC());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CoreStats coreStats) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.CHARISMA_SCORE).setValue(serialize(coreStats.getCharisma()));
        mc.getNode(Keys.CONSTITUTION_SCORE).setValue(serialize(coreStats.getConstitution()));
        mc.getNode(Keys.DEXTERITY_SCORE).setValue(serialize(coreStats.getDexterity()));
        mc.getNode(Keys.INTELLIGENCE_SCORE).setValue(serialize(coreStats.getIntelligence()));
        mc.getNode(Keys.STRENGTH_SCORE).setValue(serialize(coreStats.getStrength()));
        mc.getNode(Keys.WISDOM_SCORE).setValue(serialize(coreStats.getWisdom()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(AbilityScore abilityScore) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.PROFICIENT).setValue(abilityScore.isProficient());
        mc.getNode(Keys.SCORE).setValue(abilityScore.getScore());
        mc.getNode(Keys.SHORT_NAME).setValue(abilityScore.getShortName());
        mc.getNode(Keys.NAME).setValue(abilityScore.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Experience experience) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.EXPERIENCE_AMOUNT).setValue(experience.getXP());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(HitDice hitDice) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.HIT_DICE_MAP).setValue(hitDice.getHitDice());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Dice dice) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.AMOUNT).setValue(dice.getAmount());
        mc.getNode(Keys.SIDES).setValue(dice.getSides());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(HitPoints hitPoints) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.CURRENT_HP).setValue(hitPoints.getCurrent());
        mc.getNode(Keys.MAX_HP).setValue(hitPoints.getMax());
        mc.getNode(Keys.TEMP_HP).setValue(hitPoints.getTemp());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(InventoryTab inventoryTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.INVENTORY).setValue(serialize(inventoryTab.getInventory(), this::serialize));
        mc.getNode(Keys.INVENTORY_NOTES).setValue(inventoryTab.getInventoryNotes());
        mc.getNode(Keys.WEALTH).setValue(serialize(inventoryTab.getWealth()));
        mc.getNode(Keys.WEIGHT_CLASS).setValue(serialize(inventoryTab.getWeight()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(MCDNDItem item) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.CARRIED).setValue(item.isCarried());
        mc.getNode(Keys.WEIGHT_DOUBLE).setValue(item.getWeight());
        mc.getNode(Keys.DESCRIPTION).setValue(item.getDescription());
        mc.getNode(Keys.NAME).setValue(item.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Wealth wealth) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.COPPER).setValue(serialize(wealth.getCopper()));
        mc.getNode(Keys.ELECTRUM).setValue(serialize(wealth.getElectrum()));
        mc.getNode(Keys.GOLD).setValue(serialize(wealth.getGold()));
        mc.getNode(Keys.PLATINUM).setValue(serialize(wealth.getPlatinum()));
        mc.getNode(Keys.SILVER).setValue(serialize(wealth.getSilver()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Coin coin) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(coin.getName());
        mc.getNode(Keys.AMOUNT).setValue(coin.getAmount());
        mc.getNode(Keys.SHORT_NAME).setValue(coin.getShortName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Weight weight) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.OTHER).setValue(weight.getOther());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SkillsTab skillsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.ACROBATICS).setValue(serialize(skillsTab.getAcrobatics()));
        mc.getNode(Keys.ANIMAL_HANDLING).setValue(serialize(skillsTab.getAnimalHandling()));
        mc.getNode(Keys.ARCANA).setValue(serialize(skillsTab.getArcana()));
        mc.getNode(Keys.ATHLETICS).setValue(serialize(skillsTab.getAthletics()));
        mc.getNode(Keys.DECEPTION).setValue(serialize(skillsTab.getDeception()));
        mc.getNode(Keys.HISTORY).setValue(serialize(skillsTab.getHistory()));
        mc.getNode(Keys.INSIGHT).setValue(serialize(skillsTab.getInsight()));
        mc.getNode(Keys.INTIMIDATION).setValue(serialize(skillsTab.getIntimidation()));
        mc.getNode(Keys.INVESTIGATION).setValue(serialize(skillsTab.getInvestigation()));
        mc.getNode(Keys.MEDICINE).setValue(serialize(skillsTab.getMedicine()));
        mc.getNode(Keys.NATURE).setValue(serialize(skillsTab.getNature()));
        mc.getNode(Keys.PERFORMANCE).setValue(serialize(skillsTab.getPerformance()));
        mc.getNode(Keys.PERCEPTION).setValue(serialize(skillsTab.getPerception()));
        mc.getNode(Keys.PERSUASION).setValue(serialize(skillsTab.getPersuasion()));
        mc.getNode(Keys.RELIGION).setValue(serialize(skillsTab.getReligion()));
        mc.getNode(Keys.SLEIGHT_OF_HAND).setValue(serialize(skillsTab.getSleightOfHand()));
        mc.getNode(Keys.STEALTH).setValue(serialize(skillsTab.getStealth()));
        mc.getNode(Keys.SURVIVAL).setValue(serialize(skillsTab.getSurvival()));
        mc.getNode(Keys.UNSKILLED_CHA).setValue(serialize(skillsTab.getUnskilledCHA()));
        mc.getNode(Keys.UNSKILLED_CON).setValue(serialize(skillsTab.getUnskilledCON()));
        mc.getNode(Keys.UNSKILLED_DEX).setValue(serialize(skillsTab.getUnskilledDEX()));
        mc.getNode(Keys.UNSKILLED_INT).setValue(serialize(skillsTab.getUnskilledINT()));
        mc.getNode(Keys.UNSKILLED_STR).setValue(serialize(skillsTab.getUnskilledSTR()));
        mc.getNode(Keys.UNSKILLED_WIS).setValue(serialize(skillsTab.getUnskilledWIS()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(PlayerSkill skill) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.SKILL_PROFICIENCY).setValue(serialize(skill.getSkillProficiency()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SkillProficiency skillProficiency) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(skillProficiency.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellbookTab spellbookTab, ClassLevels classLevels) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.INVOCATION_COUNT).setValue(spellbookTab.getInvocations(classLevels));
        mc.getNode(Keys.SORCERY_POINTS).setValue(spellbookTab.getSorceryPointsMax(classLevels));
        mc.getNode(Keys.SPELLS).setValue(serialize(spellbookTab.getSpells(), this::serialize));
        mc.getNode(Keys.SPELLCASTER_CLASSES).setValue(serialize(spellbookTab.getSpellcasterClasses(), this::serialize));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Spell spell) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NEEDS_CONCENTRATION).setValue(spell.needsConcentration());
        mc.getNode(Keys.PREPARED).setValue(spell.getPrepared().toString());
        mc.getNode(Keys.DURATION).setValue(spell.getDuration());
        mc.getNode(Keys.SPELL_LEVEL).setValue(spell.getLevel());
        mc.getNode(Keys.RANGE).setValue(spell.getRange());
        mc.getNode(Keys.SPELL_DAMAGE).setValue(serialize(spell.getSpellDamage()));
        mc.getNode(Keys.SPELL_HEALING).setValue(serialize(spell.getSpellHealing()));
        mc.getNode(Keys.SPELL_DESCRIPTION).setValue(spell.getDescription());
        mc.getNode(Keys.EFFECTS).setValue(spell.getEffects());
        mc.getNode(Keys.SPELL_SAVE).setValue(serialize(spell.getSpellSave()));
        mc.getNode(Keys.SPELLCASTER_CLASS).setValue(serialize(spell.getGainedFrom()));
        mc.getNode(Keys.SPELL_TYPE).setValue(serialize(spell.getSpellType()));
        mc.getNode(Keys.ATTACK_STAT).setValue(spell.getAttackStat().getName());
        mc.getNode(Keys.CAST_TIME).setValue(spell.getCastTime());
        mc.getNode(Keys.TARGET_AREA).setValue(spell.getTargetArea());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellDamage spellDamage) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.CAN_CRIT).setValue(spellDamage.canCrit());
        mc.getNode(Keys.DICE).setValue(serialize(spellDamage.getDice()));
        mc.getNode(Keys.BONUS).setValue(spellDamage.getBonus());
        mc.getNode(Keys.DAMAGE_TYPE).setValue(spellDamage.getDamageType());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellHealing spellHealing) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.HEAL_AMOUNT).setValue(spellHealing.getHealAmount());
        mc.getNode(Keys.STAT_BONUS).setValue(spellHealing.getStatBonus().getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellSave spellSave) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.SAVE_DC_TYPE).setValue(serialize(spellSave.getSpellcasterClass()));
        mc.getNode(Keys.ON_SUCCESSFUL_SAVE).setValue(spellSave.getOnSuccessfulSave());
        mc.getNode(Keys.SAVING_STAT).setValue(spellSave.getSavingStat());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SaveDCType saveDCType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.CUSTOM_DC).setValue(saveDCType.getSaveDC(null, 0));
        mc.getNode(Keys.NAME).setValue(saveDCType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellcasterClass spellcasterClass) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(spellcasterClass.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(SpellType spellType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(spellType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(WeaponsTab weaponsTab) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.MELEE_WEAPONS).setValue(serialize(weaponsTab.getMeleeWeapons(), this::serialize));
        mc.getNode(Keys.RANGED_WEAPONS).setValue(serialize(weaponsTab.getRangedWeapons(), this::serialize));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(AbstractWeapon weapon, ConfigurationNode weaponData) {
        weaponData.getNode(Keys.IS_PROFICIENT).setValue(weapon.isProficient());
        weaponData.getNode(Keys.CRIT_DAMAGE_DICE).setValue(serialize(weapon.getCritDamageDice()));
        weaponData.getNode(Keys.DAMAGE_DICE).setValue(serialize(weapon.getDamageDice()));
        weaponData.getNode(Keys.CRIT_MINIMUM).setValue(weapon.getCritMin());
        weaponData.getNode(Keys.MAGIC_BONUS).setValue(weapon.getMagicBonus());
        weaponData.getNode(Keys.ATTACK_STAT).setValue(weapon.getAttackStat());
        weaponData.getNode(Keys.NAME).setValue(weapon.getName());
        return weaponData;
    }

    @Override
    protected ConfigurationNode serialize(MeleeWeapon meleeWeapon) {
        ConfigurationNode mc = serialize(meleeWeapon, SimpleConfigurationNode.root());
        mc.getNode(Keys.PLUS_STAT).setValue(meleeWeapon.isPlusStat());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(RangedWeapon rangedWeapon) {
        ConfigurationNode mc = serialize(rangedWeapon, SimpleConfigurationNode.root());
        mc.getNode(Keys.AMMO).setValue(rangedWeapon.getAmmo());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(CharacterSheet characterSheet) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.ARMOR_TAB).setValue(serialize(characterSheet.getArmorTab()));
        mc.getNode(Keys.BACKGROUND_TAB).setValue(serialize(characterSheet.getBackgroundTab()));
        mc.getNode(Keys.CLASS_TAB).setValue(serialize(characterSheet.getClassTab()));
        mc.getNode(Keys.CORE_STATS_TAB).setValue(serialize(characterSheet.getCoreStatsTab()));
        mc.getNode(Keys.INVENTORY_TAB).setValue(serialize(characterSheet.getInventoryTab()));
        mc.getNode(Keys.SKILLS_TAB).setValue(serialize(characterSheet.getSkillsTab()));
        mc.getNode(Keys.SPELL_BOOK_TAB).setValue(serialize(characterSheet.getSpellbookTab(), characterSheet.getClassTab().getClassLevels()));
        mc.getNode(Keys.WEAPONS_TAB).setValue(serialize(characterSheet.getWeaponsTab()));
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(ArmorType armorType) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(armorType.getName());
        return mc;
    }

    @Override
    protected ConfigurationNode serialize(Recharge recharge) {
        ConfigurationNode mc = SimpleConfigurationNode.root();
        mc.getNode(Keys.NAME).setValue(recharge.getName());
        return mc;
    }
}
