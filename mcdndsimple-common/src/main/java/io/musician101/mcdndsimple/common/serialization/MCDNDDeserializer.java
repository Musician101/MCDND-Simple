package io.musician101.mcdndsimple.common.serialization;

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
import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.weapon.RangedWeapon;

import io.musician101.mcdndsimple.common.character.weapon.WeaponAttackStat;
import java.util.List;

public abstract class MCDNDDeserializer<S>
{
    public abstract PlayerSheet deserialize(S characterSheetData);

    protected abstract BioAndInfo deserializeBioAndInfo(S bioAndInfoData);

    protected abstract SkillsTab deserializeSkillsTab(S skillsTabData, CoreStats coreStats);

    protected abstract SkillProficiency deserializeSkillProficiency(S skillsProficiencyData);

    protected abstract ArmorTab deserializeArmorTab(S armorTabData);

    protected abstract Armor deserializeArmor(S armorTab);

    protected abstract UnarmoredBonus deserializeUnarmoredBonus(S unarmoredBonusData);

    protected abstract BackgroundTab deserializeBackgroundTab(S backgroundTabData);

    protected abstract Recharge deserializeRecharge(S rechargeData);

    protected abstract ClassTab deserializeClassTab(S classTabData);

    protected abstract ClassLevels deserializeClassLevels(S classLevelsData);

    protected abstract ClassAction deserializeClassAction(S classActionData);

    protected abstract ClassResource deserializeClassResource(S classResourceData);

    protected abstract CoreStatsTab deserializeCoreStatsTab(S coreStatsTabData);

    protected abstract Bonuses deserializeBonuses(S bonusesData);

    protected abstract MeleeBonus deserializeMeleeBonus(S meleeBonusData);

    protected abstract RangedBonus deserializeRangedBonus(S rangedBonusData);

    protected abstract SpellcastingBonus deserializeSpellcastingBonus(S spellcastingBonusData);

    protected abstract CoreStats deserializeCoreStats(S coreStatsData);

    protected abstract AbilityScore deserializeAbilityScore(S abilityScoreData, AbilityScore defaultScore);

    protected abstract Experience deserializeExperience(S experienceData);

    protected abstract HitDice deserializeHitDice(S hitDiceData);

    protected abstract Dice deserializeDice(S diceData);

    protected abstract HitPoints deserializeHitPoints(S hitPointsData);

    protected abstract InventoryTab deserializeInventoryTab(S inventoryTabData, CoreStats coreStats);

    protected abstract MCDNDItem deserializeItem(S itemData);

    protected abstract Wealth deserializeWealth(S wealthData);

    protected abstract Coin deserializeCoin(S coinData, Coin coin);

    protected abstract Weight deserializeWeight(S weightData, CoreStats coreStats, List<MCDNDItem> inventory, Wealth wealth);

    protected abstract SpellbookTab deserializeSpellbookTab(S spellbookTabData, ClassLevels classLevels);

    protected abstract Spell deserializeSpell(S spellData);

    protected abstract SaveDCType deserializeSaveDCType(S spellData);

    protected abstract ArmorType deserializeArmorType(S armorTypeData);

    protected abstract SpellDamage deserializeSpellDamage(S spellDamageData);

    protected abstract SpellHealing deserializeSpellHealing(S spellHealingData);

    protected abstract SpellSave deserializeSpellSave(S spellSaveData);

    protected abstract SpellType deserializeSpellType(S spellTypeData);

    protected abstract SpellcasterClass deserializeSpellcasterClass(S spellcasterClassData);

    protected abstract WeaponsTab deserializeWeaponsTab(S weaponsTabData);

    protected abstract MeleeWeapon deserializeMeleeWeapon(S meleeWeaponData);

    protected abstract RangedWeapon deserializeRangedWeapon(S rangedWeaponData);

    protected abstract WeaponAttackStat deserializeWeaponAttackStat(S weaponAttackStatData);

    protected abstract CharacterSheet deserializePlayerSheet(S playerSheetData);
}
