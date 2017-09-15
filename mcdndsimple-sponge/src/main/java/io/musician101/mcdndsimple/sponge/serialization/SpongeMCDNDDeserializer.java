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
import ninja.leaping.configurate.ConfigurationNode;

public class SpongeMCDNDDeserializer extends MCDNDDeserializer<ConfigurationNode> {

    private boolean containsKey(ConfigurationNode configurationNode, String key) {
        return configurationNode.getNode(key).isVirtual();
    }

    @Override
    public PlayerSheet deserialize(ConfigurationNode characterSheetData) {
        PlayerSheet playerSheet = new PlayerSheet();
        playerSheet.setBioAndInfo(deserializeBioAndInfo(characterSheetData.getNode(characterSheetData, MCDNDSimpleKeys.BIO_AND_INFO)));
        playerSheet.setCharacterSheet(deserializePlayerSheet(characterSheetData.getNode(characterSheetData, MCDNDSimpleKeys.PLAYER_SHEET)));
        playerSheet.setClazz(characterSheetData.getNode(MCDNDSimpleKeys.CLASS).getString(""));
        playerSheet.setName(characterSheetData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        playerSheet.setRace(characterSheetData.getNode(MCDNDSimpleKeys.RACE).getString(""));
        return playerSheet;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(ConfigurationNode abilityScoreData, AbilityScore defaultScore) {
        if (containsKey(abilityScoreData, MCDNDSimpleKeys.NAME) || containsKey(abilityScoreData, MCDNDSimpleKeys.SHORT_NAME)) {
            AbilityScore abilityScore = new AbilityScore(abilityScoreData.getNode(MCDNDSimpleKeys.NAME).getString(), abilityScoreData.getNode(MCDNDSimpleKeys.SHORT_NAME).getString());
            abilityScore.setProficient(abilityScoreData.getNode(MCDNDSimpleKeys.IS_PROFICIENT).getBoolean(false));
            abilityScore.setScore(abilityScoreData.getNode(MCDNDSimpleKeys.SCORE).getInt(0));
            return abilityScore;
        }

        return defaultScore;
    }

    @Override
    protected Armor deserializeArmor(ConfigurationNode armorData) {
        Armor armor = new Armor();
        armor.setSpeedPenalty(armorData.getNode(MCDNDSimpleKeys.SPEED_PENALTY).getBoolean(false));
        armor.setStealthPenalty(armorData.getNode(MCDNDSimpleKeys.STEALTH_PENALTY).getBoolean(false));
        armor.setIsUnarmored(armorData.getNode(MCDNDSimpleKeys.UNARMORED).getBoolean(false));
        armor.setIsWorn(armorData.getNode(MCDNDSimpleKeys.WORN).getBoolean(false));
        armor.setBaseArmorClass(armorData.getNode(MCDNDSimpleKeys.BASE_ARMOR_CLASS).getInt(0));
        armor.setMagicBonus(armorData.getNode(MCDNDSimpleKeys.MAGIC_BONUS).getInt(0));
        armor.setArmorType(deserializeArmorType(armorData.getNode(armorData, MCDNDSimpleKeys.ARMOR_TYPE)));
        armor.setName(armorData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        return armor;
    }

    @Override
    protected ArmorTab deserializeArmorTab(ConfigurationNode armorTabData) {
        ArmorTab armorTab = new ArmorTab();
        armorTab.setArmorClass(armorTabData.getNode(MCDNDSimpleKeys.ARMOR_CLASS).getInt(0));
        armorTab.setUnarmoredClass(armorTabData.getNode(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS).getInt(0));
        armorTab.setArmor(getConfigurationNodeList(armorTabData, MCDNDSimpleKeys.ARMOR_LIST).stream().map(this::deserializeArmor).collect(Collectors.toList()));
        armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(armorTabData.getNode(armorTabData, MCDNDSimpleKeys.UNARMORED_BONUS)));
        return armorTab;
    }

    @Override
    protected ArmorType deserializeArmorType(ConfigurationNode armorTypeData) {
        return Stream.of(ArmorType.values()).filter(armorType -> containsKey(armorTypeData, MCDNDSimpleKeys.NAME) && armorType.getName().equals(armorTypeData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(ArmorType.NONE);
    }

    @Override
    protected BackgroundTab deserializeBackgroundTab(ConfigurationNode backgroundTabData) {
        BackgroundTab backgroundTab = new BackgroundTab();
        backgroundTab.setWeight(backgroundTabData.getNode(MCDNDSimpleKeys.WEIGHT_DOUBLE).getDouble(0));
        backgroundTab.setAge(backgroundTabData.getNode(MCDNDSimpleKeys.AGE).getInt(0));
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
        backgroundTab.setAlignment(backgroundTabData.getNode(MCDNDSimpleKeys.ALIGNMENT).getString(""));
        backgroundTab.setEyes(backgroundTabData.getNode(MCDNDSimpleKeys.EYES).getString(""));
        backgroundTab.setGender(backgroundTabData.getNode(MCDNDSimpleKeys.GENDER).getString(""));
        backgroundTab.setHair(backgroundTabData.getNode(MCDNDSimpleKeys.HAIR).getString(""));
        backgroundTab.setHeight(backgroundTabData.getNode(MCDNDSimpleKeys.HEIGHT).getString(""));
        backgroundTab.setLanguages(getStringList(backgroundTabData, MCDNDSimpleKeys.LANGUAGES));
        backgroundTab.setSize(backgroundTabData.getNode(MCDNDSimpleKeys.SIZE).getString(""));
        backgroundTab.setVision(backgroundTabData.getNode(MCDNDSimpleKeys.VISION).getString(""));
        return backgroundTab;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(ConfigurationNode bioAndInfoData) {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfo.setName(bioAndInfoData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        bioAndInfo.setBio(getStringList(bioAndInfoData, MCDNDSimpleKeys.BIO));
        return bioAndInfo;
    }

    @Override
    protected Bonuses deserializeBonuses(ConfigurationNode bonusesData) {
        Bonuses bonuses = new Bonuses();
        bonuses.setMelee(deserializeMeleeBonus(bonusesData.getNode(bonusesData, MCDNDSimpleKeys.MELEE_BONUS)));
        bonuses.setRanged(deserializeRangedBonus(bonusesData.getNode(bonusesData, MCDNDSimpleKeys.RANGED_BONUS)));
        bonuses.setSpellcasting(deserializeSpellcastingBonus(bonusesData.getNode(bonusesData, MCDNDSimpleKeys.SPELLCASTING_BONUS)));
        bonuses.setSaves(deserializeDice(bonusesData.getNode(bonusesData, MCDNDSimpleKeys.SAVES)));
        bonuses.setAbilitiesAndSkills(deserializeDice(bonusesData.getNode(bonusesData, MCDNDSimpleKeys.ABILITIES_AND_SKILLS)));
        return bonuses;
    }

    @Override
    protected ClassAction deserializeClassAction(ConfigurationNode classActionData) {
        ClassAction classAction = new ClassAction();
        classAction.setMax(classActionData.getNode(MCDNDSimpleKeys.MAX_USES).getInt(0));
        classAction.setUsedCharges(classActionData.getNode(MCDNDSimpleKeys.USES_LEFT).getInt(0));
        classAction.setRecharge(deserializeRecharge(classActionData.getNode(classActionData, MCDNDSimpleKeys.RECHARGE)));
        classAction.setGainedFrom(classActionData.getNode(MCDNDSimpleKeys.GAINED_FROM).getString(""));
        classAction.setName(classActionData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        return classAction;
    }

    @Override
    protected ClassLevels deserializeClassLevels(ConfigurationNode classLevelsData) {
        ClassLevels classLevels = new ClassLevels();
        classLevels.setBarbarian(classLevelsData.getNode(MCDNDSimpleKeys.BARBARIAN_LEVEL).getInt(0));
        classLevels.setBard(classLevelsData.getNode(MCDNDSimpleKeys.BARD_LEVEL).getInt(0));
        classLevels.setCleric(classLevelsData.getNode(MCDNDSimpleKeys.CLERIC_LEVEL).getInt(0));
        classLevels.setDruid(classLevelsData.getNode(MCDNDSimpleKeys.DRUID_LEVEL).getInt(0));
        classLevels.setFighter(classLevelsData.getNode(MCDNDSimpleKeys.FIGHTER_LEVEL).getInt(0));
        classLevels.setMonk(classLevelsData.getNode(MCDNDSimpleKeys.MONK_LEVEL).getInt(0));
        classLevels.setPaladin(classLevelsData.getNode(MCDNDSimpleKeys.PALADIN_LEVEL).getInt(0));
        classLevels.setRanger(classLevelsData.getNode(MCDNDSimpleKeys.RANGER_LEVEL).getInt(0));
        classLevels.setRogue(classLevelsData.getNode(MCDNDSimpleKeys.ROGUE_LEVEL).getInt(0));
        classLevels.setSorcerer(classLevelsData.getNode(MCDNDSimpleKeys.SORCERER_LEVEL).getInt(0));
        classLevels.setWarlock(classLevelsData.getNode(MCDNDSimpleKeys.WARLOCK_LEVEL).getInt(0));
        classLevels.setWizard(classLevelsData.getNode(MCDNDSimpleKeys.WIZARD_LEVEL).getInt(0));
        return classLevels;
    }

    @Override
    protected ClassResource deserializeClassResource(ConfigurationNode classResourceData) {
        ClassResource classResource = new ClassResource();
        classResource.setCurrentCharges(classResourceData.getNode(MCDNDSimpleKeys.USES_LEFT).getInt(0));
        classResource.setMaxCharges(classResourceData.getNode(MCDNDSimpleKeys.MAX_USES).getInt(0));
        classResource.setRecharge(deserializeRecharge(classResourceData.getNode(classResourceData, MCDNDSimpleKeys.RECHARGE)));
        classResource.setName(classResourceData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        return classResource;
    }

    @Override
    protected ClassTab deserializeClassTab(ConfigurationNode classTabData) {
        ClassTab classTab = new ClassTab();
        classTab.setClassLevels(deserializeClassLevels(classTabData.getNode(classTabData, MCDNDSimpleKeys.CLASS_LEVELS)));
        classTab.setClassActions(getConfigurationNodeList(classTabData, MCDNDSimpleKeys.CLASS_ACTIONS).stream().map(this::deserializeClassAction).collect(Collectors.toList()));
        classTab.setClassResources(getConfigurationNodeList(classTabData, MCDNDSimpleKeys.CLASS_RESOURCES).stream().map(this::deserializeClassResource).collect(Collectors.toList()));
        classTab.setClassFeatureNotes(getStringList(classTabData, MCDNDSimpleKeys.CLASS_FEATURE_NOTES));
        return classTab;
    }

    @Override
    protected Coin deserializeCoin(ConfigurationNode coinData, Coin defaultCoin) {
        Coin coin = new Coin(coinData.getNode(MCDNDSimpleKeys.NAME).getString(), coinData.getNode(MCDNDSimpleKeys.SHORT_NAME).getString());
        coin.setAmount(coinData.getNode(MCDNDSimpleKeys.AMOUNT).getInt(0));
        return coin;
    }

    @Override
    protected CoreStats deserializeCoreStats(ConfigurationNode coreStatsData) {
        CoreStats coreStats = new CoreStats();
        coreStats.setCharisma(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, MCDNDSimpleKeys.CHARISMA_SCORE), new AbilityScore("Charisma", "CHA")));
        coreStats.setConstitution(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, MCDNDSimpleKeys.CONSTITUTION_SCORE), new AbilityScore("Constitution", "CON")));
        coreStats.setDexterity(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, MCDNDSimpleKeys.DEXTERITY_SCORE), new AbilityScore("Dexterity", "DEX")));
        coreStats.setIntelligence(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, MCDNDSimpleKeys.INTELLIGENCE_SCORE), new AbilityScore("Intelligence", "INT")));
        coreStats.setStrength(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, MCDNDSimpleKeys.STRENGTH_SCORE), new AbilityScore("Strength", "STR")));
        coreStats.setWisdom(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, MCDNDSimpleKeys.WISDOM_SCORE), new AbilityScore("Wisdom", "WIS")));
        return coreStats;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(ConfigurationNode coreStatsTabData) {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        coreStatsTab.setBonuses(deserializeBonuses(coreStatsTabData.getNode(coreStatsTabData, MCDNDSimpleKeys.BONUSES)));
        coreStatsTab.setCoreStats(deserializeCoreStats(coreStatsTabData.getNode(coreStatsTabData, MCDNDSimpleKeys.CORE_STATS)));
        coreStatsTab.setExperience(deserializeExperience(coreStatsTabData.getNode(coreStatsTabData, MCDNDSimpleKeys.EXPERIENCE)));
        coreStatsTab.setHitDice(deserializeHitDice(coreStatsTabData.getNode(coreStatsTabData, MCDNDSimpleKeys.HIT_DICE)));
        coreStatsTab.setHitPoints(deserializeHitPoints(coreStatsTabData.getNode(coreStatsTabData, MCDNDSimpleKeys.HIT_POINTS)));
        coreStatsTab.setSpeed(coreStatsTabData.getNode(MCDNDSimpleKeys.SPEED).getInt(0));
        return coreStatsTab;
    }

    @Override
    protected Dice deserializeDice(ConfigurationNode diceData) {
        return new Dice(diceData.getNode(MCDNDSimpleKeys.SIDES).getInt(0), diceData.getNode(MCDNDSimpleKeys.AMOUNT).getInt(0));
    }

    @Override
    protected Experience deserializeExperience(ConfigurationNode experienceData) {
        Experience experience = new Experience();
        experience.setExp(experienceData.getNode(MCDNDSimpleKeys.EXPERIENCE).getInt(0));
        return experience;
    }

    @Override
    protected HitDice deserializeHitDice(ConfigurationNode hitDiceData) {
        HitDice hitDice = new HitDice();
        hitDice.setHitDice(deserializeDice(hitDiceData));
        return hitDice;
    }

    @Override
    protected HitPoints deserializeHitPoints(ConfigurationNode hitPointsData) {
        HitPoints hitPoints = new HitPoints();
        hitPoints.setCurrent(hitPointsData.getNode(MCDNDSimpleKeys.CURRENT_HP).getInt(0));
        hitPoints.setMax(hitPointsData.getNode(MCDNDSimpleKeys.MAX_HP).getInt(0));
        hitPoints.setTemp(hitPointsData.getNode(MCDNDSimpleKeys.TEMP_HP).getInt(0));
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(ConfigurationNode inventoryTabData, CoreStats coreStats) {
        InventoryTab inventoryTab = new InventoryTab();
        inventoryTab.setInventory(getConfigurationNodeList(inventoryTabData, MCDNDSimpleKeys.INVENTORY).stream().map(this::deserializeItem).collect(Collectors.toList()));
        inventoryTab.setInventoryNotes(getStringList(inventoryTabData, MCDNDSimpleKeys.INVENTORY_NOTES));
        inventoryTab.setWealth(deserializeWealth(inventoryTabData.getNode(inventoryTabData, MCDNDSimpleKeys.WEALTH)));
        inventoryTab.setWeight(deserializeWeight(inventoryTabData.getNode(inventoryTabData, MCDNDSimpleKeys.WEIGHT_CLASS), coreStats, inventoryTab.getInventory(), inventoryTab.getWealth()));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(ConfigurationNode itemData) {
        MCDNDItem item = new MCDNDItem();
        item.setIsCarried(itemData.getNode(MCDNDSimpleKeys.CARRIED).getBoolean(true));
        item.setWeight(itemData.getNode(MCDNDSimpleKeys.WEIGHT_DOUBLE).getDouble(0));
        item.setDescription(getStringList(itemData, MCDNDSimpleKeys.DESCRIPTION));
        item.setName(itemData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        return item;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(ConfigurationNode meleeBonusData) {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonus.setAttack(deserializeDice(meleeBonusData.getNode(meleeBonusData, MCDNDSimpleKeys.ATTACK)));
        meleeBonus.setDamage(deserializeDice(meleeBonusData.getNode(meleeBonusData, MCDNDSimpleKeys.DAMAGE)));
        return meleeBonus;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(ConfigurationNode meleeWeaponData) {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeapon.setPlusStat(meleeWeaponData.getNode(MCDNDSimpleKeys.PLUS_STAT).getBoolean(false));
        meleeWeapon.setIsProficient(meleeWeaponData.getNode(MCDNDSimpleKeys.IS_PROFICIENT).getBoolean(false));
        meleeWeapon.setCritDamageDice(deserializeDice(meleeWeaponData.getNode(meleeWeaponData, MCDNDSimpleKeys.CRIT_DAMAGE_DICE)));
        meleeWeapon.setDamageDice(deserializeDice(meleeWeaponData.getNode(meleeWeaponData, MCDNDSimpleKeys.DAMAGE_DICE)));
        meleeWeapon.setCritMin(meleeWeaponData.getNode(MCDNDSimpleKeys.CRIT_MINIMUM).getInt(0));
        meleeWeapon.setMagicBonus(meleeWeaponData.getNode(MCDNDSimpleKeys.MAGIC_BONUS).getInt(0));
        meleeWeapon.setAttackStat(deserializeWeaponAttackStat(meleeWeaponData.getNode(meleeWeaponData, MCDNDSimpleKeys.ATTACK_STAT)));
        meleeWeapon.setName(meleeWeaponData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        return meleeWeapon;
    }

    @Override
    protected CharacterSheet deserializePlayerSheet(ConfigurationNode playerSheetData) {
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setArmorTab(deserializeArmorTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.ARMOR_TAB)));
        characterSheet.setBackgroundTab(deserializeBackgroundTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.BACKGROUND_TAB)));
        characterSheet.setClassTab(deserializeClassTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.CLASS_TAB)));
        characterSheet.setCoreStatsTab(deserializeCoreStatsTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.CORE_STATS_TAB)));
        characterSheet.setInventoryTab(deserializeInventoryTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.INVENTORY_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSkillsTab(deserializeSkillsTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.SKILLS_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSpellbookTab(deserializeSpellbookTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.SPELL_BOOK_TAB), characterSheet.getClassTab().getClassLevels()));
        characterSheet.setWeaponsTab(deserializeWeaponsTab(playerSheetData.getNode(playerSheetData, MCDNDSimpleKeys.WEAPONS_TAB)));
        return characterSheet;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(ConfigurationNode rangedBonusData) {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonus.setAttack(deserializeDice(rangedBonusData.getNode(rangedBonusData, MCDNDSimpleKeys.ATTACK)));
        rangedBonus.setDamage(deserializeDice(rangedBonusData.getNode(rangedBonusData, MCDNDSimpleKeys.DAMAGE)));
        return rangedBonus;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(ConfigurationNode rangedWeaponData) {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeapon.setAmmo(rangedWeaponData.getNode(MCDNDSimpleKeys.PLUS_STAT).getInt(0));
        rangedWeapon.setIsProficient(rangedWeaponData.getNode(MCDNDSimpleKeys.IS_PROFICIENT).getBoolean(false));
        rangedWeapon.setCritDamageDice(deserializeDice(rangedWeaponData.getNode(rangedWeaponData, MCDNDSimpleKeys.CRIT_DAMAGE_DICE)));
        rangedWeapon.setDamageDice(deserializeDice(rangedWeaponData.getNode(rangedWeaponData, MCDNDSimpleKeys.DAMAGE_DICE)));
        rangedWeapon.setCritMin(rangedWeaponData.getNode(MCDNDSimpleKeys.CRIT_MINIMUM).getInt(0));
        rangedWeapon.setMagicBonus(rangedWeaponData.getNode(MCDNDSimpleKeys.MAGIC_BONUS).getInt(0));
        rangedWeapon.setAttackStat(deserializeWeaponAttackStat(rangedWeaponData.getNode(rangedWeaponData, MCDNDSimpleKeys.ATTACK_STAT)));
        rangedWeapon.setName(rangedWeaponData.getNode(MCDNDSimpleKeys.NAME).getString(""));
        return rangedWeapon;
    }

    @Override
    protected Recharge deserializeRecharge(ConfigurationNode rechargeData) {
        return Stream.of(Recharge.values()).filter(recharge -> containsKey(rechargeData, MCDNDSimpleKeys.NAME) && recharge.getName().equals(rechargeData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(Recharge.OTHER);
    }

    @Override
    protected SaveDCType deserializeSaveDCType(ConfigurationNode spellData) {
        if (containsKey(spellData, MCDNDSimpleKeys.NAME)) {
            String name = spellData.getNode(MCDNDSimpleKeys.NAME).getString();
            if ("Custom DC".equals(name)) {
                return SaveDCTypes.custom(spellData.getNode(MCDNDSimpleKeys.CUSTOM_DC).getInt(0));
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
    protected SkillProficiency deserializeSkillProficiency(ConfigurationNode skillsProficiencyData) {
        return Stream.of(SkillProficiency.values()).filter(skillProficiency -> containsKey(skillsProficiencyData, MCDNDSimpleKeys.NAME) && skillProficiency.getName().equals(skillsProficiencyData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(SkillProficiency.NONE);
    }

    @Override
    protected SkillsTab deserializeSkillsTab(ConfigurationNode skillsTabData, CoreStats coreStats) {
        SkillsTab skillsTab = new SkillsTab();
        skillsTab.updateSkills(coreStats);
        return skillsTab;
    }

    @Override
    protected Spell deserializeSpell(ConfigurationNode spellData) {
        Spell spell = new Spell();
        spell.setNeedsConcentration(spellData.getNode(MCDNDSimpleKeys.NEEDS_CONCENTRATION).getBoolean(false));
        spell.setPrepared(Prepared.valueOf(spellData.getNode(MCDNDSimpleKeys.PREPARED).getString("NO")));
        spell.setDuration(spellData.getNode(MCDNDSimpleKeys.DURATION).getString(""));
        spell.setLevel(spellData.getNode(MCDNDSimpleKeys.SPELL_LEVEL).getInt(0));
        spell.setRange(spellData.getNode(MCDNDSimpleKeys.RANGE).getString(""));
        spell.setSpellDamage(deserializeSpellDamage(spellData.getNode(spellData, MCDNDSimpleKeys.SPELL_DAMAGE)));
        spell.setDescription(getStringList(spellData, MCDNDSimpleKeys.SPELL_DESCRIPTION));
        spell.setEffects(getStringList(spellData, MCDNDSimpleKeys.EFFECTS));
        spell.setSpellSave(deserializeSpellSave(spellData.getNode(spellData, MCDNDSimpleKeys.SPELL_SAVE)));
        spell.setGainedFrom(deserializeSpellcasterClass(spellData.getNode(spellData, MCDNDSimpleKeys.SPELLCASTER_CLASS)));
        spell.setSpellType(deserializeSpellType(spellData.getNode(spellData, MCDNDSimpleKeys.SPELL_TYPE)));
        spell.setSpellHealing(deserializeSpellHealing(spellData.getNode(spellData, MCDNDSimpleKeys.SPELL_HEALING)));
        spell.setAttackStat(deserializeStatBonus(spellData.getNode(MCDNDSimpleKeys.ATTACK_STAT)));
        spell.setCastTime(spellData.getNode(MCDNDSimpleKeys.CAST_TIME).getString(""));
        spell.setTargetArea(spellData.getNode(MCDNDSimpleKeys.TARGET_AREA).getString(""));
        return spell;
    }

    @Override
    protected SpellDamage deserializeSpellDamage(ConfigurationNode spellDamageData) {
        SpellDamage spellDamage = new SpellDamage();
        spellDamage.setCanCrit(spellDamageData.getNode(MCDNDSimpleKeys.CAN_CRIT).getBoolean(false));
        spellDamage.setDice(deserializeDice(spellDamageData.getNode(spellDamageData, MCDNDSimpleKeys.DICE)));
        spellDamageData.getNode(MCDNDSimpleKeys.BONUS).getInt(0);
        spellDamage.setDamageType(spellDamageData.getNode(MCDNDSimpleKeys.DAMAGE_TYPE).getString(""));
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(ConfigurationNode spellHealingData) {
        SpellHealing spellHealing = new SpellHealing();
        deserializeDice(spellHealingData.getNode(spellHealingData, MCDNDSimpleKeys.HEAL_AMOUNT));
        spellHealing.setHealAmount(deserializeDice(spellHealingData.getNode(spellHealingData, MCDNDSimpleKeys.HEAL_AMOUNT)));
        spellHealing.setStatBonus(deserializeStatBonus(spellHealingData.getNode(MCDNDSimpleKeys.STAT_BONUS)));
        return spellHealing;
    }

    @Override
    protected StatBonus deserializeStatBonus(ConfigurationNode statBonusData) {
        return Stream.of(StatBonus.values()).filter(statBonus -> containsKey(statBonusData, MCDNDSimpleKeys.NAME) && statBonus.getName().equals(statBonusData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(StatBonus.NONE);
    }

    @Override
    protected SpellSave deserializeSpellSave(ConfigurationNode spellSaveData) {
        SpellSave spellSave = new SpellSave();
        spellSave.setSaveDCType(deserializeSpellcasterClass(spellSaveData.getNode(spellSaveData, MCDNDSimpleKeys.SAVE_DC_TYPE)));

        spellSave.setOnSuccessfulSave(getStringList(spellSaveData, MCDNDSimpleKeys.SAVE_DC_TYPE));
        spellSave.setSavingStat(spellSaveData.getNode(MCDNDSimpleKeys.SAVING_STAT).getString(""));
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(ConfigurationNode spellTypeData) {
        return Stream.of(SpellType.values()).filter(spellType -> containsKey(spellTypeData, MCDNDSimpleKeys.NAME) && spellType.getName().equals(spellTypeData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(SpellType.OTHER);
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(ConfigurationNode spellbookTabData, ClassLevels classLevels) {
        SpellbookTab spellbookTab = new SpellbookTab();
        spellbookTab.setSpells(getConfigurationNodeList(spellbookTabData, MCDNDSimpleKeys.SPELLS).stream().map(this::deserializeSpell).collect(Collectors.toList()));
        spellbookTab.setSpellcasterClasses(getConfigurationNodeList(spellbookTabData, MCDNDSimpleKeys.SPELLCASTER_CLASSES).stream().map(this::deserializeSpellcasterClass).collect(Collectors.toList()));
        return spellbookTab;
    }

    @Override
    protected SpellcasterClass deserializeSpellcasterClass(ConfigurationNode spellcasterClassData) {
        return Stream.of(SpellcasterClass.values()).filter(spellcasterClass -> containsKey(spellcasterClassData, MCDNDSimpleKeys.NAME) && spellcasterClass.getName().equals(spellcasterClassData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(SpellcasterClass.OTHER);
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(ConfigurationNode spellcastingBonusData) {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonus.setAttack(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, MCDNDSimpleKeys.ATTACK)));
        spellcastingBonus.setDamage(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, MCDNDSimpleKeys.DAMAGE)));
        spellcastingBonus.setSaveDC(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, MCDNDSimpleKeys.SAVE_DC)));
        return spellcastingBonus;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(ConfigurationNode unarmoredBonusData) {
        return Stream.of(UnarmoredBonus.values()).filter(unarmoredBonus -> containsKey(unarmoredBonusData, MCDNDSimpleKeys.NAME) && unarmoredBonus.getName().equals(unarmoredBonusData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(UnarmoredBonus.NONE);
    }

    @Override
    protected Wealth deserializeWealth(ConfigurationNode wealthData) {
        Wealth wealth = new Wealth();
        wealth.setCopper(deserializeCoin(wealthData.getNode(wealthData, MCDNDSimpleKeys.COPPER), new Coin("Copper", "CP")));
        wealth.setElectrum(deserializeCoin(wealthData.getNode(wealthData, MCDNDSimpleKeys.ELECTRUM), new Coin("Electrum", "EP")));
        wealth.setGold(deserializeCoin(wealthData.getNode(wealthData, MCDNDSimpleKeys.GOLD), new Coin("Gold", "GP")));
        wealth.setPlatinum(deserializeCoin(wealthData.getNode(wealthData, MCDNDSimpleKeys.PLATINUM), new Coin("Platinum", "PP")));
        wealth.setPlatinum(deserializeCoin(wealthData.getNode(wealthData, MCDNDSimpleKeys.SILVER), new Coin("Silver", "SP")));
        return wealth;
    }

    @Override
    protected WeaponAttackStat deserializeWeaponAttackStat(ConfigurationNode weaponAttackStatData) {
        return Stream.of(WeaponAttackStat.values()).filter(weaponAttackStatus -> containsKey(weaponAttackStatData, MCDNDSimpleKeys.NAME) && weaponAttackStatus.getName().equals(weaponAttackStatData.getNode(MCDNDSimpleKeys.NAME).getString())).findFirst().orElse(WeaponAttackStat.STR);
    }

    @Override
    protected WeaponsTab deserializeWeaponsTab(ConfigurationNode weaponsTabData) {
        WeaponsTab weaponsTab = new WeaponsTab();
        weaponsTab.setMeleeWeapons(getConfigurationNodeList(weaponsTabData, MCDNDSimpleKeys.MELEE_WEAPONS).stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList()));
        weaponsTab.setRangedWeapons(getConfigurationNodeList(weaponsTabData, MCDNDSimpleKeys.RANGED_WEAPONS).stream().map(this::deserializeRangedWeapon).collect(Collectors.toList()));
        return weaponsTab;
    }

    @Override
    protected Weight deserializeWeight(ConfigurationNode weightData, CoreStats coreStats, List<MCDNDItem> inventory, Wealth wealth) {
        Weight weight = new Weight();
        weight.setOther(weightData.getNode(MCDNDSimpleKeys.OTHER).getDouble(0));
        weight.setInventoryWeight(inventory);
        weight.setCoinWeight(wealth);
        weight.setCarryingMax(coreStats);
        return weight;
    }

    private List<? extends ConfigurationNode> getConfigurationNodeList(ConfigurationNode configurationNode, String key) {
        return containsKey(configurationNode, key) ? configurationNode.getNode(key).getChildrenList() : new ArrayList<>();
    }

    private List<String> getStringList(ConfigurationNode configurationNode, String key) {
        return configurationNode.getNode(key).getList(Object::toString, new ArrayList<>());
    }
}
