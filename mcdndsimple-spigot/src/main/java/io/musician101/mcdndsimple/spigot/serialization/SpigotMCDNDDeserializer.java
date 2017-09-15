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
import io.musician101.mcdndsimple.common.character.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.spell.Prepared;
import io.musician101.mcdndsimple.common.character.spell.SaveDCType;
import io.musician101.mcdndsimple.common.character.spell.SaveDCTypes;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.common.character.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.spell.SpellType;
import io.musician101.mcdndsimple.common.character.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.spell.StatBonus;
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
import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import io.musician101.mcdndsimple.common.serialization.MCDNDSimpleKeys;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.configuration.MemoryConfiguration;

public class SpigotMCDNDDeserializer extends MCDNDDeserializer<MemoryConfiguration> {

    private boolean containsKey(MemoryConfiguration memoryConfiguration, String key) {
        return memoryConfiguration.contains(key);
    }

    @Override
    public PlayerSheet deserialize(MemoryConfiguration characterSheetData) {
        PlayerSheet playerSheet = new PlayerSheet();
        playerSheet.setBioAndInfo(deserializeBioAndInfo(getMemoryConfiguration(characterSheetData, MCDNDSimpleKeys.BIO_AND_INFO)));
        playerSheet.setCharacterSheet(deserializePlayerSheet(getMemoryConfiguration(characterSheetData, MCDNDSimpleKeys.PLAYER_SHEET)));
        playerSheet.setClazz(characterSheetData.getString(MCDNDSimpleKeys.CLASS, ""));
        playerSheet.setName(characterSheetData.getString(MCDNDSimpleKeys.NAME, ""));
        playerSheet.setRace(characterSheetData.getString(MCDNDSimpleKeys.RACE, ""));
        return playerSheet;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(MemoryConfiguration abilityScoreData, AbilityScore defaultScore) {
        if (containsKey(abilityScoreData, MCDNDSimpleKeys.NAME) || containsKey(abilityScoreData, MCDNDSimpleKeys.SHORT_NAME)) {
            AbilityScore abilityScore = new AbilityScore(abilityScoreData.getString(MCDNDSimpleKeys.NAME), abilityScoreData.getString(MCDNDSimpleKeys.SHORT_NAME));
            abilityScore.setProficient(abilityScoreData.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT, false));
            abilityScore.setScore(abilityScoreData.getInt(MCDNDSimpleKeys.SCORE, 0));
            return abilityScore;
        }

        return defaultScore;
    }

    @Override
    protected Armor deserializeArmor(MemoryConfiguration armorData) {
        Armor armor = new Armor();
        armor.setSpeedPenalty(armorData.getBoolean(MCDNDSimpleKeys.SPEED_PENALTY, false));
        armor.setStealthPenalty(armorData.getBoolean(MCDNDSimpleKeys.STEALTH_PENALTY, false));
        armor.setIsUnarmored(armorData.getBoolean(MCDNDSimpleKeys.UNARMORED, false));
        armor.setIsWorn(armorData.getBoolean(MCDNDSimpleKeys.WORN, false));
        armor.setBaseArmorClass(armorData.getInt(MCDNDSimpleKeys.BASE_ARMOR_CLASS, 0));
        armor.setMagicBonus(armorData.getInt(MCDNDSimpleKeys.MAGIC_BONUS, 0));
        armor.setArmorType(deserializeArmorType(getMemoryConfiguration(armorData, MCDNDSimpleKeys.ARMOR_TYPE)));
        armor.setName(armorData.getString(MCDNDSimpleKeys.NAME, ""));
        return armor;
    }

    @Override
    protected ArmorTab deserializeArmorTab(MemoryConfiguration armorTabData) {
        ArmorTab armorTab = new ArmorTab();
        armorTab.setArmorClass(armorTabData.getInt(MCDNDSimpleKeys.ARMOR_CLASS, 0));
        armorTab.setUnarmoredClass(armorTabData.getInt(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, 0));
        armorTab.setArmor(getMemoryConfigurationList(armorTabData, MCDNDSimpleKeys.ARMOR_LIST).stream().map(this::deserializeArmor).collect(Collectors.toList()));
        armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(getMemoryConfiguration(armorTabData, MCDNDSimpleKeys.UNARMORED_BONUS)));
        return armorTab;
    }

    @Override
    protected ArmorType deserializeArmorType(MemoryConfiguration armorTypeData) {
        return Stream.of(ArmorType.values()).filter(armorType -> containsKey(armorTypeData, MCDNDSimpleKeys.NAME) && armorType.getName().equals(armorTypeData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(ArmorType.NONE);
    }

    @Override
    protected BackgroundTab deserializeBackgroundTab(MemoryConfiguration backgroundTabData) {
        BackgroundTab backgroundTab = new BackgroundTab();
        backgroundTab.setWeight(backgroundTabData.getDouble(MCDNDSimpleKeys.WEIGHT_DOUBLE, 0));
        backgroundTab.setAge(backgroundTabData.getInt(MCDNDSimpleKeys.AGE, 0));
        backgroundTab.setArmorProficiencies(getStringList(backgroundTabData, MCDNDSimpleKeys.ARMOR_PROFICIENCIES));
        backgroundTab.setBackground(getStringList(backgroundTabData, MCDNDSimpleKeys.BACKGROUND));
        backgroundTab.setBonds(getStringList(backgroundTabData, MCDNDSimpleKeys.BONDS));
        backgroundTab.setFlaws(getStringList(backgroundTabData, MCDNDSimpleKeys.FLAWS));
        backgroundTab.setIdeals(getStringList(backgroundTabData, MCDNDSimpleKeys.IDEALS));
        backgroundTab.setOtherNotes(getStringList(backgroundTabData, MCDNDSimpleKeys.OTHER_NOTES));
        backgroundTab.setPersonalityTraits(getStringList(backgroundTabData, MCDNDSimpleKeys.PERSONALITY_TRAITS));
        backgroundTab.setRacialTraits(getStringList(backgroundTabData, MCDNDSimpleKeys.RACIAL_TRAITS));
        backgroundTab.setToolProficiencies(getStringList(backgroundTabData, MCDNDSimpleKeys.TOOL_PROFICIENCIES));
        backgroundTab.setWeaponProficiencies(getStringList(backgroundTabData, MCDNDSimpleKeys.WEAPON_PROFICIENCIES));
        backgroundTab.setAlignment(backgroundTabData.getString(MCDNDSimpleKeys.ALIGNMENT, ""));
        backgroundTab.setEyes(backgroundTabData.getString(MCDNDSimpleKeys.EYES, ""));
        backgroundTab.setGender(backgroundTabData.getString(MCDNDSimpleKeys.GENDER, ""));
        backgroundTab.setHair(backgroundTabData.getString(MCDNDSimpleKeys.HAIR, ""));
        backgroundTab.setHeight(backgroundTabData.getString(MCDNDSimpleKeys.HEIGHT, ""));
        backgroundTab.setLanguages(backgroundTabData.getStringList(MCDNDSimpleKeys.LANGUAGES));
        backgroundTab.setSize(backgroundTabData.getString(MCDNDSimpleKeys.SIZE, ""));
        backgroundTab.setVision(backgroundTabData.getString(MCDNDSimpleKeys.VISION, ""));
        return backgroundTab;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(MemoryConfiguration bioAndInfoData) {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfo.setName(bioAndInfoData.getString(MCDNDSimpleKeys.NAME, ""));
        bioAndInfo.setBio(getStringList(bioAndInfoData, MCDNDSimpleKeys.BIO));
        return bioAndInfo;
    }

    @Override
    protected Bonuses deserializeBonuses(MemoryConfiguration bonusesData) {
        Bonuses bonuses = new Bonuses();
        bonuses.setMelee(deserializeMeleeBonus(getMemoryConfiguration(bonusesData, MCDNDSimpleKeys.MELEE_BONUS)));
        bonuses.setRanged(deserializeRangedBonus(getMemoryConfiguration(bonusesData, MCDNDSimpleKeys.RANGED_BONUS)));
        bonuses.setSpellcasting(deserializeSpellcastingBonus(getMemoryConfiguration(bonusesData, MCDNDSimpleKeys.SPELLCASTING_BONUS)));
        bonuses.setSaves(deserializeDice(getMemoryConfiguration(bonusesData, MCDNDSimpleKeys.SAVES)));
        bonuses.setAbilitiesAndSkills(deserializeDice(getMemoryConfiguration(bonusesData, MCDNDSimpleKeys.ABILITIES_AND_SKILLS)));
        return bonuses;
    }

    @Override
    protected ClassAction deserializeClassAction(MemoryConfiguration classActionData) {
        ClassAction classAction = new ClassAction();
        classAction.setMax(classActionData.getInt(MCDNDSimpleKeys.MAX_USES, 0));
        classAction.setUsedCharges(classActionData.getInt(MCDNDSimpleKeys.USES_LEFT, 0));
        classAction.setRecharge(deserializeRecharge(getMemoryConfiguration(classActionData, MCDNDSimpleKeys.RECHARGE)));
        classAction.setGainedFrom(classActionData.getString(MCDNDSimpleKeys.GAINED_FROM, ""));
        classAction.setName(classActionData.getString(MCDNDSimpleKeys.NAME, ""));
        return classAction;
    }

    @Override
    protected ClassLevels deserializeClassLevels(MemoryConfiguration classLevelsData) {
        ClassLevels classLevels = new ClassLevels();
        classLevels.setBarbarian(classLevelsData.getInt(MCDNDSimpleKeys.BARBARIAN_LEVEL, 0));
        classLevels.setBard(classLevelsData.getInt(MCDNDSimpleKeys.BARD_LEVEL, 0));
        classLevels.setCleric(classLevelsData.getInt(MCDNDSimpleKeys.CLERIC_LEVEL, 0));
        classLevels.setDruid(classLevelsData.getInt(MCDNDSimpleKeys.DRUID_LEVEL, 0));
        classLevels.setFighter(classLevelsData.getInt(MCDNDSimpleKeys.FIGHTER_LEVEL, 0));
        classLevels.setMonk(classLevelsData.getInt(MCDNDSimpleKeys.MONK_LEVEL, 0));
        classLevels.setPaladin(classLevelsData.getInt(MCDNDSimpleKeys.PALADIN_LEVEL, 0));
        classLevels.setRanger(classLevelsData.getInt(MCDNDSimpleKeys.RANGER_LEVEL, 0));
        classLevels.setRogue(classLevelsData.getInt(MCDNDSimpleKeys.ROGUE_LEVEL, 0));
        classLevels.setSorcerer(classLevelsData.getInt(MCDNDSimpleKeys.SORCERER_LEVEL, 0));
        classLevels.setWarlock(classLevelsData.getInt(MCDNDSimpleKeys.WARLOCK_LEVEL, 0));
        classLevels.setWizard(classLevelsData.getInt(MCDNDSimpleKeys.WIZARD_LEVEL, 0));
        return classLevels;
    }

    @Override
    protected ClassResource deserializeClassResource(MemoryConfiguration classResourceData) {
        ClassResource classResource = new ClassResource();
        classResource.setCurrentCharges(classResourceData.getInt(MCDNDSimpleKeys.USES_LEFT, 0));
        classResource.setMaxCharges(classResourceData.getInt(MCDNDSimpleKeys.MAX_USES, 0));
        classResource.setRecharge(deserializeRecharge(getMemoryConfiguration(classResourceData, MCDNDSimpleKeys.RECHARGE)));
        classResource.setName(classResourceData.getString(MCDNDSimpleKeys.NAME, ""));
        return classResource;
    }

    @Override
    protected ClassTab deserializeClassTab(MemoryConfiguration classTabData) {
        ClassTab classTab = new ClassTab();
        classTab.setClassLevels(deserializeClassLevels(getMemoryConfiguration(classTabData, MCDNDSimpleKeys.CLASS_LEVELS)));
        classTab.setClassActions(getMemoryConfigurationList(classTabData, MCDNDSimpleKeys.CLASS_ACTIONS).stream().map(this::deserializeClassAction).collect(Collectors.toList()));
        classTab.setClassResources(getMemoryConfigurationList(classTabData, MCDNDSimpleKeys.CLASS_RESOURCES).stream().map(this::deserializeClassResource).collect(Collectors.toList()));
        classTab.setClassFeatureNotes(getStringList(classTabData, MCDNDSimpleKeys.CLASS_FEATURE_NOTES));
        return classTab;
    }

    @Override
    protected Coin deserializeCoin(MemoryConfiguration coinData, Coin defaultCoin) {
        Coin coin = new Coin(coinData.getString(MCDNDSimpleKeys.NAME), coinData.getString(MCDNDSimpleKeys.SHORT_NAME));
        coin.setAmount(coinData.getInt(MCDNDSimpleKeys.AMOUNT, 0));
        return coin;
    }

    @Override
    protected CoreStats deserializeCoreStats(MemoryConfiguration coreStatsData) {
        CoreStats coreStats = new CoreStats();
        coreStats.setCharisma(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, MCDNDSimpleKeys.CHARISMA_SCORE), new AbilityScore("Charisma", "CHA")));
        coreStats.setConstitution(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, MCDNDSimpleKeys.CONSTITUTION_SCORE), new AbilityScore("Constitution", "CON")));
        coreStats.setDexterity(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, MCDNDSimpleKeys.DEXTERITY_SCORE), new AbilityScore("Dexterity", "DEX")));
        coreStats.setIntelligence(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, MCDNDSimpleKeys.INTELLIGENCE_SCORE), new AbilityScore("Intelligence", "INT")));
        coreStats.setStrength(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, MCDNDSimpleKeys.STRENGTH_SCORE), new AbilityScore("Strength", "STR")));
        coreStats.setWisdom(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, MCDNDSimpleKeys.WISDOM_SCORE), new AbilityScore("Wisdom", "WIS")));
        return coreStats;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(MemoryConfiguration coreStatsTabData) {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        coreStatsTab.setBonuses(deserializeBonuses(getMemoryConfiguration(coreStatsTabData, MCDNDSimpleKeys.BONUSES)));
        coreStatsTab.setCoreStats(deserializeCoreStats(getMemoryConfiguration(coreStatsTabData, MCDNDSimpleKeys.CORE_STATS)));
        coreStatsTab.setExperience(deserializeExperience(getMemoryConfiguration(coreStatsTabData, MCDNDSimpleKeys.EXPERIENCE)));
        coreStatsTab.setHitDice(deserializeHitDice(getMemoryConfiguration(coreStatsTabData, MCDNDSimpleKeys.HIT_DICE)));
        coreStatsTab.setHitPoints(deserializeHitPoints(getMemoryConfiguration(coreStatsTabData, MCDNDSimpleKeys.HIT_POINTS)));
        coreStatsTab.setSpeed(coreStatsTabData.getInt(MCDNDSimpleKeys.SPEED, 0));
        return coreStatsTab;
    }

    @Override
    protected Dice deserializeDice(MemoryConfiguration diceData) {
        return new Dice(diceData.getInt(MCDNDSimpleKeys.SIDES, 0), diceData.getInt(MCDNDSimpleKeys.AMOUNT, 0));
    }

    @Override
    protected Experience deserializeExperience(MemoryConfiguration experienceData) {
        Experience experience = new Experience();
        experience.setExp(experienceData.getInt(MCDNDSimpleKeys.EXPERIENCE, 0));
        return experience;
    }

    @Override
    protected HitDice deserializeHitDice(MemoryConfiguration hitDiceData) {
        HitDice hitDice = new HitDice();
        hitDice.setHitDice(deserializeDice(hitDiceData));
        return hitDice;
    }

    @Override
    protected HitPoints deserializeHitPoints(MemoryConfiguration hitPointsData) {
        HitPoints hitPoints = new HitPoints();
        hitPoints.setCurrent(hitPointsData.getInt(MCDNDSimpleKeys.CURRENT_HP, 0));
        hitPoints.setMax(hitPointsData.getInt(MCDNDSimpleKeys.MAX_HP, 0));
        hitPoints.setTemp(hitPointsData.getInt(MCDNDSimpleKeys.TEMP_HP, 0));
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(MemoryConfiguration inventoryTabData, CoreStats coreStats) {
        InventoryTab inventoryTab = new InventoryTab();
        inventoryTab.setInventory(getMemoryConfigurationList(inventoryTabData, MCDNDSimpleKeys.INVENTORY).stream().map(this::deserializeItem).collect(Collectors.toList()));
        inventoryTab.setInventoryNotes(getStringList(inventoryTabData, MCDNDSimpleKeys.INVENTORY_NOTES));
        inventoryTab.setWealth(deserializeWealth(getMemoryConfiguration(inventoryTabData, MCDNDSimpleKeys.WEALTH)));
        inventoryTab.setWeight(deserializeWeight(getMemoryConfiguration(inventoryTabData, MCDNDSimpleKeys.WEIGHT_CLASS), coreStats, inventoryTab.getInventory(), inventoryTab.getWealth()));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(MemoryConfiguration itemData) {
        MCDNDItem item = new MCDNDItem();
        item.setIsCarried(itemData.getBoolean(MCDNDSimpleKeys.CARRIED));
        item.setWeight(itemData.getDouble(MCDNDSimpleKeys.WEIGHT_DOUBLE));
        item.setDescription(itemData.getStringList(MCDNDSimpleKeys.DESCRIPTION));
        item.setName(itemData.getString(MCDNDSimpleKeys.NAME));
        return item;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(MemoryConfiguration meleeBonusData) {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonus.setAttack(deserializeDice(getMemoryConfiguration(meleeBonusData, MCDNDSimpleKeys.ATTACK)));
        meleeBonus.setDamage(deserializeDice(getMemoryConfiguration(meleeBonusData, MCDNDSimpleKeys.DAMAGE)));
        return meleeBonus;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(MemoryConfiguration meleeWeaponData) {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeapon.setPlusStat(meleeWeaponData.getBoolean(MCDNDSimpleKeys.PLUS_STAT, false));
        meleeWeapon.setIsProficient(meleeWeaponData.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT, false));
        meleeWeapon.setCritDamageDice(deserializeDice(getMemoryConfiguration(meleeWeaponData, MCDNDSimpleKeys.CRIT_DAMAGE_DICE)));
        meleeWeapon.setDamageDice(deserializeDice(getMemoryConfiguration(meleeWeaponData, MCDNDSimpleKeys.DAMAGE_DICE)));
        meleeWeapon.setCritMin(meleeWeaponData.getInt(MCDNDSimpleKeys.CRIT_MINIMUM, 0));
        meleeWeapon.setMagicBonus(meleeWeaponData.getInt(MCDNDSimpleKeys.MAGIC_BONUS, 0));
        meleeWeapon.setAttackStat(deserializeWeaponAttackStat(getMemoryConfiguration(meleeWeaponData, MCDNDSimpleKeys.ATTACK_STAT)));
        meleeWeapon.setName(meleeWeaponData.getString(MCDNDSimpleKeys.NAME, ""));
        return meleeWeapon;
    }

    @Override
    protected CharacterSheet deserializePlayerSheet(MemoryConfiguration playerSheetData) {
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setArmorTab(deserializeArmorTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.ARMOR_TAB)));
        characterSheet.setBackgroundTab(deserializeBackgroundTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.BACKGROUND_TAB)));
        characterSheet.setClassTab(deserializeClassTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.CLASS_TAB)));
        characterSheet.setCoreStatsTab(deserializeCoreStatsTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.CORE_STATS_TAB)));
        characterSheet.setInventoryTab(deserializeInventoryTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.INVENTORY_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSkillsTab(deserializeSkillsTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.SKILLS_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSpellbookTab(deserializeSpellbookTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.SPELL_BOOK_TAB), characterSheet.getClassTab().getClassLevels()));
        characterSheet.setWeaponsTab(deserializeWeaponsTab(getMemoryConfiguration(playerSheetData, MCDNDSimpleKeys.WEAPONS_TAB)));
        return characterSheet;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(MemoryConfiguration rangedBonusData) {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonus.setAttack(deserializeDice(getMemoryConfiguration(rangedBonusData, MCDNDSimpleKeys.ATTACK)));
        rangedBonus.setDamage(deserializeDice(getMemoryConfiguration(rangedBonusData, MCDNDSimpleKeys.DAMAGE)));
        return rangedBonus;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(MemoryConfiguration rangedWeaponData) {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeapon.setAmmo(rangedWeaponData.getInt(MCDNDSimpleKeys.PLUS_STAT, 0));
        rangedWeapon.setIsProficient(rangedWeaponData.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT, false));
        rangedWeapon.setCritDamageDice(deserializeDice(getMemoryConfiguration(rangedWeaponData, MCDNDSimpleKeys.CRIT_DAMAGE_DICE)));
        rangedWeapon.setDamageDice(deserializeDice(getMemoryConfiguration(rangedWeaponData, MCDNDSimpleKeys.DAMAGE_DICE)));
        rangedWeapon.setCritMin(rangedWeaponData.getInt(MCDNDSimpleKeys.CRIT_MINIMUM, 0));
        rangedWeapon.setMagicBonus(rangedWeaponData.getInt(MCDNDSimpleKeys.MAGIC_BONUS, 0));
        rangedWeapon.setAttackStat(deserializeWeaponAttackStat(getMemoryConfiguration(rangedWeaponData, MCDNDSimpleKeys.ATTACK_STAT)));
        rangedWeapon.setName(rangedWeaponData.getString(MCDNDSimpleKeys.NAME, ""));
        return rangedWeapon;
    }

    @Override
    protected Recharge deserializeRecharge(MemoryConfiguration rechargeData) {
        return Stream.of(Recharge.values()).filter(recharge -> containsKey(rechargeData, MCDNDSimpleKeys.NAME) && recharge.getName().equals(rechargeData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(Recharge.OTHER);
    }

    @Override
    protected SaveDCType deserializeSaveDCType(MemoryConfiguration spellData) {
        if (containsKey(spellData, MCDNDSimpleKeys.NAME)) {
            String name = spellData.getString(MCDNDSimpleKeys.NAME);
            if ("Custom DC".equals(name)) {
                return SaveDCTypes.custom(spellData.getInt(MCDNDSimpleKeys.CUSTOM_DC, 0));
            }
            else if ("Arcane Trickster DC".equals(name)) {
                return SaveDCTypes.ARCANE_TRICKSTER;
            }
            else if ("Bard DC".equals(name)) {
                return SaveDCTypes.BARD;
            }
            else if ("Cleric DC".equals(name)) {
                return SaveDCTypes.CLERIC;
            }
            else if ("Druid DC".equals(name)) {
                return SaveDCTypes.DRUID;
            }
            else if ("Eldritch Knight DC".equals(name)) {
                return SaveDCTypes.ELDRITCH_KNIGHT;
            }
            else if ("Paladin DC".equals(name)) {
                return SaveDCTypes.PALADIN;
            }
            else if ("Sorcerer DC".equals(name)) {
                return SaveDCTypes.SORCERER;
            }
            else if ("Warlock DC".equals(name)) {
                return SaveDCTypes.WARLOCK;
            }
            else if ("Wizard DC".equals(name)) {
                return SaveDCTypes.WIZARD;
            }
        }

        return SaveDCTypes.custom(0);
    }

    @Override
    protected SkillProficiency deserializeSkillProficiency(MemoryConfiguration skillsProficiencyData) {
        return Stream.of(SkillProficiency.values()).filter(statBonus -> containsKey(skillsProficiencyData, MCDNDSimpleKeys.NAME) && statBonus.getName().equals(skillsProficiencyData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(SkillProficiency.NONE);
    }

    @Override
    protected SkillsTab deserializeSkillsTab(MemoryConfiguration skillsTabData, CoreStats coreStats) {
        SkillsTab skillsTab = new SkillsTab();
        skillsTab.updateSkills(coreStats);
        return skillsTab;
    }

    @Override
    protected Spell deserializeSpell(MemoryConfiguration spellData) {
        Spell spell = new Spell();
        spell.setNeedsConcentration(spellData.getBoolean(MCDNDSimpleKeys.NEEDS_CONCENTRATION, false));
        spell.setPrepared(Prepared.valueOf(spellData.getString(MCDNDSimpleKeys.PREPARED, "NO")));
        spell.setDuration(spellData.getString(MCDNDSimpleKeys.DURATION, ""));
        spell.setLevel(spellData.getInt(MCDNDSimpleKeys.SPELL_LEVEL, 0));
        spell.setRange(spellData.getString(MCDNDSimpleKeys.RANGE, ""));
        spell.setSpellDamage(deserializeSpellDamage(getMemoryConfiguration(spellData, MCDNDSimpleKeys.SPELL_DAMAGE)));
        spell.setDescription(getStringList(spellData, MCDNDSimpleKeys.SPELL_DESCRIPTION));
        spell.setEffects(getStringList(spellData, MCDNDSimpleKeys.EFFECTS));
        spell.setSpellSave(deserializeSpellSave(getMemoryConfiguration(spellData, MCDNDSimpleKeys.SPELL_SAVE)));
        spell.setGainedFrom(deserializeSpellcasterClass(getMemoryConfiguration(spellData, MCDNDSimpleKeys.SPELLCASTER_CLASS)));
        spell.setSpellType(deserializeSpellType(getMemoryConfiguration(spellData, MCDNDSimpleKeys.SPELL_TYPE)));
        spell.setSpellHealing(deserializeSpellHealing(getMemoryConfiguration(spellData, MCDNDSimpleKeys.SPELL_HEALING)));
        spell.setAttackStat(deserializeStatBonus(getMemoryConfiguration(spellData, MCDNDSimpleKeys.ATTACK_STAT)));
        spell.setCastTime(spellData.getString(MCDNDSimpleKeys.CAST_TIME, ""));
        spell.setTargetArea(spellData.getString(MCDNDSimpleKeys.TARGET_AREA, ""));
        return spell;
    }

    @Override
    protected SpellDamage deserializeSpellDamage(MemoryConfiguration spellDamageData) {
        SpellDamage spellDamage = new SpellDamage();
        spellDamage.setCanCrit(spellDamageData.getBoolean(MCDNDSimpleKeys.CAN_CRIT, false));
        spellDamage.setDice(deserializeDice(getMemoryConfiguration(spellDamageData, MCDNDSimpleKeys.DICE)));
        spellDamageData.getInt(MCDNDSimpleKeys.BONUS, 0);
        spellDamage.setDamageType(spellDamageData.getString(MCDNDSimpleKeys.DAMAGE_TYPE, ""));
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(MemoryConfiguration spellHealingData) {
        SpellHealing spellHealing = new SpellHealing();
        deserializeDice(getMemoryConfiguration(spellHealingData, MCDNDSimpleKeys.HEAL_AMOUNT));
        spellHealing.setHealAmount(deserializeDice(getMemoryConfiguration(spellHealingData, MCDNDSimpleKeys.HEAL_AMOUNT)));
        spellHealing.setStatBonus(deserializeStatBonus(getMemoryConfiguration(spellHealingData, MCDNDSimpleKeys.STAT_BONUS)));
        return spellHealing;
    }

    @Override
    protected StatBonus deserializeStatBonus(MemoryConfiguration statBonusData) {
        return Stream.of(StatBonus.values()).filter(statBonus -> containsKey(statBonusData, MCDNDSimpleKeys.NAME) && statBonus.getName().equals(statBonusData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(StatBonus.NONE);
    }

    @Override
    protected SpellSave deserializeSpellSave(MemoryConfiguration spellSaveData) {
        SpellSave spellSave = new SpellSave();
        spellSave.setSaveDCType(deserializeSpellcasterClass(getMemoryConfiguration(spellSaveData, MCDNDSimpleKeys.SAVE_DC_TYPE)));

        spellSave.setOnSuccessfulSave(getStringList(spellSaveData, MCDNDSimpleKeys.SAVE_DC_TYPE));
        spellSave.setSavingStat(spellSaveData.getString(MCDNDSimpleKeys.SAVING_STAT, ""));
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(MemoryConfiguration spellTypeData) {
        return Stream.of(SpellType.values()).filter(spellType -> containsKey(spellTypeData, MCDNDSimpleKeys.NAME) && spellType.getName().equals(spellTypeData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(SpellType.OTHER);
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(MemoryConfiguration spellbookTabData, ClassLevels classLevels) {
        SpellbookTab spellbookTab = new SpellbookTab();
        spellbookTab.setSpells(getMemoryConfigurationList(spellbookTabData, MCDNDSimpleKeys.SPELLS).stream().map(this::deserializeSpell).collect(Collectors.toList()));
        spellbookTab.setSpellcasterClasses(getMemoryConfigurationList(spellbookTabData, MCDNDSimpleKeys.SPELLCASTER_CLASSES).stream().map(this::deserializeSpellcasterClass).collect(Collectors.toList()));
        return spellbookTab;
    }

    @Override
    protected SpellcasterClass deserializeSpellcasterClass(MemoryConfiguration spellcasterClassData) {
        return Stream.of(SpellcasterClass.values()).filter(spellcasterClass -> containsKey(spellcasterClassData, MCDNDSimpleKeys.NAME) && spellcasterClass.getName().equals(spellcasterClassData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(SpellcasterClass.OTHER);
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(MemoryConfiguration spellcastingBonusData) {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonus.setAttack(deserializeDice(getMemoryConfiguration(spellcastingBonusData, MCDNDSimpleKeys.ATTACK)));
        spellcastingBonus.setDamage(deserializeDice(getMemoryConfiguration(spellcastingBonusData, MCDNDSimpleKeys.DAMAGE)));
        spellcastingBonus.setSaveDC(deserializeDice(getMemoryConfiguration(spellcastingBonusData, MCDNDSimpleKeys.SAVE_DC)));
        return spellcastingBonus;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(MemoryConfiguration unarmoredBonusData) {
        return Stream.of(UnarmoredBonus.values()).filter(unarmoredBonus -> containsKey(unarmoredBonusData, MCDNDSimpleKeys.NAME) && unarmoredBonus.getName().equals(unarmoredBonusData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(UnarmoredBonus.NONE);
    }

    @Override
    protected Wealth deserializeWealth(MemoryConfiguration wealthData) {
        Wealth wealth = new Wealth();
        wealth.setCopper(deserializeCoin(getMemoryConfiguration(wealthData, MCDNDSimpleKeys.COPPER), new Coin("Copper", "CP")));
        wealth.setElectrum(deserializeCoin(getMemoryConfiguration(wealthData, MCDNDSimpleKeys.ELECTRUM), new Coin("Electrum", "EP")));
        wealth.setGold(deserializeCoin(getMemoryConfiguration(wealthData, MCDNDSimpleKeys.GOLD), new Coin("Gold", "GP")));
        wealth.setPlatinum(deserializeCoin(getMemoryConfiguration(wealthData, MCDNDSimpleKeys.PLATINUM), new Coin("Platinum", "PP")));
        wealth.setPlatinum(deserializeCoin(getMemoryConfiguration(wealthData, MCDNDSimpleKeys.SILVER), new Coin("Silver", "SP")));
        return wealth;
    }

    @Override
    protected WeaponAttackStat deserializeWeaponAttackStat(MemoryConfiguration weaponAttackStatData) {
        return Stream.of(WeaponAttackStat.values()).filter(weaponAttackStatus -> containsKey(weaponAttackStatData, MCDNDSimpleKeys.NAME) && weaponAttackStatus.getName().equals(weaponAttackStatData.getString(MCDNDSimpleKeys.NAME))).findFirst().orElse(WeaponAttackStat.STR);
    }

    @Override
    protected WeaponsTab deserializeWeaponsTab(MemoryConfiguration weaponsTabData) {
        WeaponsTab weaponsTab = new WeaponsTab();
        weaponsTab.setMeleeWeapons(getMemoryConfigurationList(weaponsTabData, MCDNDSimpleKeys.MELEE_WEAPONS).stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList()));
        weaponsTab.setRangedWeapons(getMemoryConfigurationList(weaponsTabData, MCDNDSimpleKeys.RANGED_WEAPONS).stream().map(this::deserializeRangedWeapon).collect(Collectors.toList()));
        return weaponsTab;
    }

    @Override
    protected Weight deserializeWeight(MemoryConfiguration weightData, CoreStats coreStats, List<MCDNDItem> inventory, Wealth wealth) {
        Weight weight = new Weight();
        weight.setOther(weightData.getDouble(MCDNDSimpleKeys.OTHER, 0));
        weight.setInventoryWeight(inventory);
        weight.setCoinWeight(wealth);
        weight.setCarryingMax(coreStats);
        return weight;
    }

    private MemoryConfiguration getMemoryConfiguration(MemoryConfiguration memoryConfiguration, String key) {
        return containsKey(memoryConfiguration, key) ? (MemoryConfiguration) memoryConfiguration.getConfigurationSection(key) : new MemoryConfiguration();
    }

    private List<MemoryConfiguration> getMemoryConfigurationList(MemoryConfiguration memoryConfiguration, String key) {
        if (containsKey(memoryConfiguration, key)) {
            return memoryConfiguration.getMapList(key).stream().map(map -> {
                MemoryConfiguration mc = new MemoryConfiguration();
                map.forEach((key1, value) -> mc.set(key1.toString(), value));
                return mc;
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    private List<String> getStringList(MemoryConfiguration memoryConfiguration, String key) {
        return containsKey(memoryConfiguration, key) ? memoryConfiguration.getStringList(key) : new ArrayList<>();
    }
}
