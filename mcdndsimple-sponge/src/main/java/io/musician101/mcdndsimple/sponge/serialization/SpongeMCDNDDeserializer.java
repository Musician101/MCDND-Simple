package io.musician101.mcdndsimple.sponge.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
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
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.player.spell.Prepared;
import io.musician101.mcdndsimple.common.character.player.spell.SaveDCType;
import io.musician101.mcdndsimple.common.character.player.spell.SaveDCTypes;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.player.spell.SpellType;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.spell.StatBonus;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
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
    public PlayerSheet deserializePC(ConfigurationNode playerData) {
        PlayerSheet playerSheet = new PlayerSheet();
        playerSheet.setBioAndInfo(deserializeBioAndInfo(playerData.getNode(playerData, JsonUtils.BIO_AND_INFO)));
        playerSheet.setCharacterSheet(deserializePlayerSheet(playerData.getNode(playerData, JsonUtils.PLAYER_SHEET)));
        playerSheet.setClazz(playerData.getNode(JsonUtils.CLASS).getString(""));
        playerSheet.setName(playerData.getNode(JsonUtils.NAME).getString(""));
        playerSheet.setRace(playerData.getNode(JsonUtils.RACE).getString(""));
        return playerSheet;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(ConfigurationNode abilityScoreData, AbilityScore defaultScore) {
        if (containsKey(abilityScoreData, JsonUtils.NAME) || containsKey(abilityScoreData, JsonUtils.SHORT_NAME)) {
            AbilityScore abilityScore = new AbilityScore(abilityScoreData.getNode(JsonUtils.NAME).getString(), abilityScoreData.getNode(JsonUtils.SHORT_NAME).getString());
            abilityScore.setIsProficient(abilityScoreData.getNode(JsonUtils.IS_PROFICIENT).getBoolean(false));
            abilityScore.setScore(abilityScoreData.getNode(JsonUtils.SCORE).getInt(0));
            return abilityScore;
        }

        return defaultScore;
    }

    @Override
    protected Armor deserializeArmor(ConfigurationNode armorData) {
        Armor armor = new Armor();
        armor.setSpeedPenalty(armorData.getNode(JsonUtils.SPEED_PENALTY).getBoolean(false));
        armor.setStealthPenalty(armorData.getNode(JsonUtils.STEALTH_PENALTY).getBoolean(false));
        armor.setIsUnarmored(armorData.getNode(JsonUtils.UNARMORED).getBoolean(false));
        armor.setIsWorn(armorData.getNode(JsonUtils.WORN).getBoolean(false));
        armor.setBaseArmorClass(armorData.getNode(JsonUtils.BASE_ARMOR_CLASS).getInt(0));
        armor.setMagicBonus(armorData.getNode(JsonUtils.MAGIC_BONUS).getInt(0));
        armor.setArmorType(deserializeArmorType(armorData.getNode(armorData, JsonUtils.ARMOR_TYPE)));
        armor.setName(armorData.getNode(JsonUtils.NAME).getString(""));
        return armor;
    }

    @Override
    protected ArmorTab deserializeArmorTab(ConfigurationNode armorTabData) {
        ArmorTab armorTab = new ArmorTab();
        armorTab.setArmorClass(armorTabData.getNode(JsonUtils.ARMOR_CLASS).getInt(0));
        armorTab.setUnarmoredClass(armorTabData.getNode(JsonUtils.UNARMORED_ARMOR_CLASS).getInt(0));
        armorTab.setArmor(getConfigurationNodeList(armorTabData, JsonUtils.ARMOR_LIST).stream().map(this::deserializeArmor).collect(Collectors.toList()));
        armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(armorTabData.getNode(armorTabData, JsonUtils.UNARMORED_BONUS)));
        return armorTab;
    }

    @Override
    protected ArmorType deserializeArmorType(ConfigurationNode armorTypeData) {
        return Stream.of(ArmorType.values()).filter(armorType -> containsKey(armorTypeData, JsonUtils.NAME) && armorType.getName().equals(armorTypeData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(ArmorType.NONE);
    }

    @Override
    protected BackgroundTab deserializeBackgroundTab(ConfigurationNode backgroundTabData) {
        BackgroundTab backgroundTab = new BackgroundTab();
        backgroundTab.setWeight(backgroundTabData.getNode(JsonUtils.WEIGHT_DOUBLE).getDouble(0));
        backgroundTab.setAge(backgroundTabData.getNode(JsonUtils.AGE).getInt(0));
        backgroundTab.setArmorProficiencies(getStringList(backgroundTabData, JsonUtils.ARMOR_PROFICIENCIES));
        backgroundTab.setBackground(getStringList(backgroundTabData, JsonUtils.BACKGROUND));
        backgroundTab.setBonds(getStringList(backgroundTabData, JsonUtils.BONDS));
        backgroundTab.setFlaws(getStringList(backgroundTabData, JsonUtils.FLAWS));
        backgroundTab.setIdeals(getStringList(backgroundTabData, JsonUtils.IDEALS));
        backgroundTab.setOtherNotes(getStringList(backgroundTabData, JsonUtils.OTHER_NOTES));
        backgroundTab.setPersonalityTraits(getStringList(backgroundTabData, JsonUtils.PERSONALITY_TRAITS));
        backgroundTab.setRacialTraits(getStringList(backgroundTabData, JsonUtils.RACIAL_TRAITS));
        backgroundTab.setToolProficiencies(getStringList(backgroundTabData, JsonUtils.TOOL_PROFICIENCIES));
        backgroundTab.setWeaponProficiencies(getStringList(backgroundTabData, JsonUtils.WEAPON_PROFICIENCIES));
        backgroundTab.setAlignment(backgroundTabData.getNode(JsonUtils.ALIGNMENT).getString(""));
        backgroundTab.setEyes(backgroundTabData.getNode(JsonUtils.EYES).getString(""));
        backgroundTab.setGender(backgroundTabData.getNode(JsonUtils.GENDER).getString(""));
        backgroundTab.setHair(backgroundTabData.getNode(JsonUtils.HAIR).getString(""));
        backgroundTab.setHeight(backgroundTabData.getNode(JsonUtils.HEIGHT).getString(""));
        backgroundTab.setLanguages(getStringList(backgroundTabData, JsonUtils.LANGUAGES));
        backgroundTab.setSize(backgroundTabData.getNode(JsonUtils.SIZE).getString(""));
        backgroundTab.setVision(backgroundTabData.getNode(JsonUtils.VISION).getString(""));
        return backgroundTab;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(ConfigurationNode bioAndInfoData) {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfo.setName(bioAndInfoData.getNode(JsonUtils.NAME).getString(""));
        bioAndInfo.setBio(getStringList(bioAndInfoData, JsonUtils.BIO));
        return bioAndInfo;
    }

    @Override
    protected Bonuses deserializeBonuses(ConfigurationNode bonusesData) {
        Bonuses bonuses = new Bonuses();
        bonuses.setMelee(deserializeMeleeBonus(bonusesData.getNode(bonusesData, JsonUtils.MELEE_BONUS)));
        bonuses.setRanged(deserializeRangedBonus(bonusesData.getNode(bonusesData, JsonUtils.RANGED_BONUS)));
        bonuses.setSpellcasting(deserializeSpellcastingBonus(bonusesData.getNode(bonusesData, JsonUtils.SPELLCASTING_BONUS)));
        bonuses.setSaves(deserializeDice(bonusesData.getNode(bonusesData, JsonUtils.SAVES)));
        bonuses.setAbilitiesAndSkills(deserializeDice(bonusesData.getNode(bonusesData, JsonUtils.ABILITIES_AND_SKILLS)));
        return bonuses;
    }

    @Override
    protected ClassAction deserializeClassAction(ConfigurationNode classActionData) {
        ClassAction classAction = new ClassAction();
        classAction.setMaxUses(classActionData.getNode(JsonUtils.MAX_USES).getInt(0));
        classAction.setUsed(classActionData.getNode(JsonUtils.USES_LEFT).getInt(0));
        classAction.setRecharge(deserializeRecharge(classActionData.getNode(classActionData, JsonUtils.RECHARGE)));
        classAction.setGainedFrom(classActionData.getNode(JsonUtils.GAINED_FROM).getString(""));
        classAction.setName(classActionData.getNode(JsonUtils.NAME).getString(""));
        return classAction;
    }

    @Override
    protected ClassLevels deserializeClassLevels(ConfigurationNode classLevelsData) {
        ClassLevels classLevels = new ClassLevels();
        classLevels.setBarbarian(classLevelsData.getNode(JsonUtils.BARBARIAN_LEVEL).getInt(0));
        classLevels.setBard(classLevelsData.getNode(JsonUtils.BARD_LEVEL).getInt(0));
        classLevels.setCleric(classLevelsData.getNode(JsonUtils.CLERIC_LEVEL).getInt(0));
        classLevels.setDruid(classLevelsData.getNode(JsonUtils.DRUID_LEVEL).getInt(0));
        classLevels.setFighter(classLevelsData.getNode(JsonUtils.FIGHTER_LEVEL).getInt(0));
        classLevels.setMonk(classLevelsData.getNode(JsonUtils.MONK_LEVEL).getInt(0));
        classLevels.setPaladin(classLevelsData.getNode(JsonUtils.PALADIN_LEVEL).getInt(0));
        classLevels.setRanger(classLevelsData.getNode(JsonUtils.RANGER_LEVEL).getInt(0));
        classLevels.setRogue(classLevelsData.getNode(JsonUtils.ROGUE_LEVEL).getInt(0));
        classLevels.setSorcerer(classLevelsData.getNode(JsonUtils.SORCERER_LEVEL).getInt(0));
        classLevels.setWarlock(classLevelsData.getNode(JsonUtils.WARLOCK_LEVEL).getInt(0));
        classLevels.setWizard(classLevelsData.getNode(JsonUtils.WIZARD_LEVEL).getInt(0));
        return classLevels;
    }

    @Override
    protected ClassResource deserializeClassResource(ConfigurationNode classResourceData) {
        ClassResource classResource = new ClassResource();
        classResource.setCurrentCharges(classResourceData.getNode(JsonUtils.USES_LEFT).getInt(0));
        classResource.setMaxCharges(classResourceData.getNode(JsonUtils.MAX_USES).getInt(0));
        classResource.setRecharge(deserializeRecharge(classResourceData.getNode(classResourceData, JsonUtils.RECHARGE)));
        classResource.setName(classResourceData.getNode(JsonUtils.NAME).getString(""));
        return classResource;
    }

    @Override
    protected ClassTab deserializeClassTab(ConfigurationNode classTabData) {
        ClassTab classTab = new ClassTab();
        classTab.setClassLevels(deserializeClassLevels(classTabData.getNode(classTabData, JsonUtils.CLASS_LEVELS)));
        classTab.setClassActions(getConfigurationNodeList(classTabData, JsonUtils.CLASS_ACTIONS).stream().map(this::deserializeClassAction).collect(Collectors.toList()));
        classTab.setClassResources(getConfigurationNodeList(classTabData, JsonUtils.CLASS_RESOURCES).stream().map(this::deserializeClassResource).collect(Collectors.toList()));
        classTab.setClassFeatureNotes(getStringList(classTabData, JsonUtils.CLASS_FEATURE_NOTES));
        return classTab;
    }

    @Override
    protected Coin deserializeCoin(ConfigurationNode coinData, Coin defaultCoin) {
        Coin coin = new Coin(coinData.getNode(JsonUtils.NAME).getString(), coinData.getNode(JsonUtils.SHORT_NAME).getString());
        coin.setAmount(coinData.getNode(JsonUtils.AMOUNT).getInt(0));
        return coin;
    }

    @Override
    protected CoreStats deserializeCoreStats(ConfigurationNode coreStatsData) {
        CoreStats coreStats = new CoreStats();
        coreStats.setCharisma(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, JsonUtils.CHARISMA_SCORE), new AbilityScore("Charisma", "CHA")));
        coreStats.setConstitution(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, JsonUtils.CONSTITUTION_SCORE), new AbilityScore("Constitution", "CON")));
        coreStats.setDexterity(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, JsonUtils.DEXTERITY_SCORE), new AbilityScore("Dexterity", "DEX")));
        coreStats.setIntelligence(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, JsonUtils.INTELLIGENCE_SCORE), new AbilityScore("Intelligence", "INT")));
        coreStats.setStrength(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, JsonUtils.STRENGTH_SCORE), new AbilityScore("Strength", "STR")));
        coreStats.setWisdom(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, JsonUtils.WISDOM_SCORE), new AbilityScore("Wisdom", "WIS")));
        return coreStats;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(ConfigurationNode coreStatsTabData) {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        coreStatsTab.setBonuses(deserializeBonuses(coreStatsTabData.getNode(coreStatsTabData, JsonUtils.BONUSES)));
        coreStatsTab.setCoreStats(deserializeCoreStats(coreStatsTabData.getNode(coreStatsTabData, JsonUtils.CORE_STATS)));
        coreStatsTab.setExperience(deserializeExperience(coreStatsTabData.getNode(coreStatsTabData, JsonUtils.EXPERIENCE)));
        coreStatsTab.setHitDice(deserializeHitDice(coreStatsTabData.getNode(coreStatsTabData, JsonUtils.HIT_DICE)));
        coreStatsTab.setHitPoints(deserializeHitPoints(coreStatsTabData.getNode(coreStatsTabData, JsonUtils.HIT_POINTS)));
        coreStatsTab.setSpeed(coreStatsTabData.getNode(JsonUtils.SPEED).getInt(0));
        return coreStatsTab;
    }

    @Override
    protected Dice deserializeDice(ConfigurationNode diceData) {
        return new Dice(diceData.getNode(JsonUtils.SIDES).getInt(0), diceData.getNode(JsonUtils.AMOUNT).getInt(0));
    }

    @Override
    protected Experience deserializeExperience(ConfigurationNode experienceData) {
        Experience experience = new Experience();
        experience.setExp(experienceData.getNode(JsonUtils.EXPERIENCE).getInt(0));
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
        hitPoints.setCurrent(hitPointsData.getNode(JsonUtils.CURRENT_HP).getInt(0));
        hitPoints.setMax(hitPointsData.getNode(JsonUtils.MAX_HP).getInt(0));
        hitPoints.setTemp(hitPointsData.getNode(JsonUtils.TEMP_HP).getInt(0));
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(ConfigurationNode inventoryTabData, CoreStats coreStats) {
        InventoryTab inventoryTab = new InventoryTab();
        inventoryTab.setInventory(getConfigurationNodeList(inventoryTabData, JsonUtils.INVENTORY).stream().map(this::deserializeItem).collect(Collectors.toList()));
        inventoryTab.setInventoryNotes(getStringList(inventoryTabData, JsonUtils.INVENTORY_NOTES));
        inventoryTab.setWealth(deserializeWealth(inventoryTabData.getNode(inventoryTabData, JsonUtils.WEALTH)));
        inventoryTab.setWeight(deserializeWeight(inventoryTabData.getNode(inventoryTabData, JsonUtils.WEIGHT_CLASS), coreStats, inventoryTab.getInventory(), inventoryTab.getWealth()));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(ConfigurationNode itemData) {
        MCDNDItem item = new MCDNDItem();
        item.setIsCarried(itemData.getNode(JsonUtils.CARRIED).getBoolean(true));
        item.setWeight(itemData.getNode(JsonUtils.WEIGHT_DOUBLE).getDouble(0));
        item.setDescription(getStringList(itemData, JsonUtils.DESCRIPTION));
        item.setName(itemData.getNode(JsonUtils.NAME).getString(""));
        return item;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(ConfigurationNode meleeBonusData) {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonus.setAttack(deserializeDice(meleeBonusData.getNode(meleeBonusData, JsonUtils.ATTACK)));
        meleeBonus.setDamage(deserializeDice(meleeBonusData.getNode(meleeBonusData, JsonUtils.DAMAGE)));
        return meleeBonus;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(ConfigurationNode meleeWeaponData) {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeapon.setPlusStat(meleeWeaponData.getNode(JsonUtils.PLUS_STAT).getBoolean(false));
        meleeWeapon.setIsProficient(meleeWeaponData.getNode(JsonUtils.IS_PROFICIENT).getBoolean(false));
        meleeWeapon.setCritDamage(deserializeDice(meleeWeaponData.getNode(meleeWeaponData, JsonUtils.CRIT_DAMAGE_DICE)));
        meleeWeapon.setDamage(deserializeDice(meleeWeaponData.getNode(meleeWeaponData, JsonUtils.DAMAGE_DICE)));
        meleeWeapon.setCritMin(meleeWeaponData.getNode(JsonUtils.CRIT_MINIMUM).getInt(0));
        meleeWeapon.setMagicBonus(meleeWeaponData.getNode(JsonUtils.MAGIC_BONUS).getInt(0));
        meleeWeapon.setAttackStat(deserializeWeaponAttackStat(meleeWeaponData.getNode(meleeWeaponData, JsonUtils.ATTACK_STAT)));
        meleeWeapon.setName(meleeWeaponData.getNode(JsonUtils.NAME).getString(""));
        return meleeWeapon;
    }

    @Override
    protected CharacterSheet deserializePlayerSheet(ConfigurationNode playerSheetData) {
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setArmorTab(deserializeArmorTab(playerSheetData.getNode(playerSheetData, JsonUtils.ARMOR_TAB)));
        characterSheet.setBackgroundTab(deserializeBackgroundTab(playerSheetData.getNode(playerSheetData, JsonUtils.BACKGROUND_TAB)));
        characterSheet.setClassTab(deserializeClassTab(playerSheetData.getNode(playerSheetData, JsonUtils.CLASS_TAB)));
        characterSheet.setCoreStatsTab(deserializeCoreStatsTab(playerSheetData.getNode(playerSheetData, JsonUtils.CORE_STATS_TAB)));
        characterSheet.setInventoryTab(deserializeInventoryTab(playerSheetData.getNode(playerSheetData, JsonUtils.INVENTORY_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSkillsTab(deserializeSkillsTab(playerSheetData.getNode(playerSheetData, JsonUtils.SKILLS_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSpellbookTab(deserializeSpellbookTab(playerSheetData.getNode(playerSheetData, JsonUtils.SPELL_BOOK_TAB), characterSheet.getClassTab().getClassLevels()));
        characterSheet.setWeaponsTab(deserializeWeaponsTab(playerSheetData.getNode(playerSheetData, JsonUtils.WEAPONS_TAB)));
        return characterSheet;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(ConfigurationNode rangedBonusData) {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonus.setAttack(deserializeDice(rangedBonusData.getNode(rangedBonusData, JsonUtils.ATTACK)));
        rangedBonus.setDamage(deserializeDice(rangedBonusData.getNode(rangedBonusData, JsonUtils.DAMAGE)));
        return rangedBonus;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(ConfigurationNode rangedWeaponData) {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeapon.setAmmo(rangedWeaponData.getNode(JsonUtils.PLUS_STAT).getInt(0));
        rangedWeapon.setIsProficient(rangedWeaponData.getNode(JsonUtils.IS_PROFICIENT).getBoolean(false));
        rangedWeapon.setCritDamage(deserializeDice(rangedWeaponData.getNode(rangedWeaponData, JsonUtils.CRIT_DAMAGE_DICE)));
        rangedWeapon.setDamage(deserializeDice(rangedWeaponData.getNode(rangedWeaponData, JsonUtils.DAMAGE_DICE)));
        rangedWeapon.setCritMin(rangedWeaponData.getNode(JsonUtils.CRIT_MINIMUM).getInt(0));
        rangedWeapon.setMagicBonus(rangedWeaponData.getNode(JsonUtils.MAGIC_BONUS).getInt(0));
        rangedWeapon.setAttackStat(deserializeWeaponAttackStat(rangedWeaponData.getNode(rangedWeaponData, JsonUtils.ATTACK_STAT)));
        rangedWeapon.setName(rangedWeaponData.getNode(JsonUtils.NAME).getString(""));
        return rangedWeapon;
    }

    @Override
    protected Recharge deserializeRecharge(ConfigurationNode rechargeData) {
        return Stream.of(Recharge.values()).filter(recharge -> containsKey(rechargeData, JsonUtils.NAME) && recharge.getName().equals(rechargeData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(Recharge.OTHER);
    }

    @Override
    protected SaveDCType deserializeSaveDCType(ConfigurationNode spellData) {
        if (containsKey(spellData, JsonUtils.NAME)) {
            String name = spellData.getNode(JsonUtils.NAME).getString();
            if ("Custom DC".equals(name)) {
                return SaveDCTypes.custom(spellData.getNode(JsonUtils.CUSTOM_DC).getInt(0));
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
        return Stream.of(SkillProficiency.values()).filter(skillProficiency -> containsKey(skillsProficiencyData, JsonUtils.NAME) && skillProficiency.getName().equals(skillsProficiencyData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(SkillProficiency.NONE);
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
        spell.setNeedsConcentration(spellData.getNode(JsonUtils.NEEDS_CONCENTRATION).getBoolean(false));
        spell.setPrepared(Prepared.valueOf(spellData.getNode(JsonUtils.PREPARED).getString("NO")));
        spell.setDuration(spellData.getNode(JsonUtils.DURATION).getString(""));
        spell.setLevel(spellData.getNode(JsonUtils.SPELL_LEVEL).getInt(0));
        spell.setRange(spellData.getNode(JsonUtils.RANGE).getString(""));
        spell.setSpellDamage(deserializeSpellDamage(spellData.getNode(spellData, JsonUtils.SPELL_DAMAGE)));
        spell.setDescription(getStringList(spellData, JsonUtils.SPELL_DESCRIPTION));
        spell.setEffects(getStringList(spellData, JsonUtils.EFFECTS));
        spell.setSpellSave(deserializeSpellSave(spellData.getNode(spellData, JsonUtils.SPELL_SAVE)));
        spell.setGainedFrom(deserializeSpellcasterClass(spellData.getNode(spellData, JsonUtils.SPELLCASTER_CLASS)));
        spell.setSpellType(deserializeSpellType(spellData.getNode(spellData, JsonUtils.SPELL_TYPE)));
        spell.setSpellHealing(deserializeSpellHealing(spellData.getNode(spellData, JsonUtils.SPELL_HEALING)));
        spell.setAttackStat(deserializeStatBonus(spellData.getNode(JsonUtils.ATTACK_STAT)));
        spell.setCastTime(spellData.getNode(JsonUtils.CAST_TIME).getString(""));
        spell.setTargetArea(spellData.getNode(JsonUtils.TARGET_AREA).getString(""));
        return spell;
    }

    @Override
    protected SpellDamage deserializeSpellDamage(ConfigurationNode spellDamageData) {
        SpellDamage spellDamage = new SpellDamage();
        spellDamage.setCanCrit(spellDamageData.getNode(JsonUtils.CAN_CRIT).getBoolean(false));
        spellDamage.setDamage(deserializeDice(spellDamageData.getNode(spellDamageData, JsonUtils.DICE)));
        spellDamageData.getNode(JsonUtils.BONUS).getInt(0);
        spellDamage.setDamageType(spellDamageData.getNode(JsonUtils.DAMAGE_TYPE).getString(""));
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(ConfigurationNode spellHealingData) {
        SpellHealing spellHealing = new SpellHealing();
        deserializeDice(spellHealingData.getNode(spellHealingData, JsonUtils.HEAL_AMOUNT));
        spellHealing.setHealAmount(deserializeDice(spellHealingData.getNode(spellHealingData, JsonUtils.HEAL_AMOUNT)));
        spellHealing.setStatBonus(deserializeStatBonus(spellHealingData.getNode(JsonUtils.STAT_BONUS)));
        return spellHealing;
    }

    @Override
    protected StatBonus deserializeStatBonus(ConfigurationNode statBonusData) {
        return Stream.of(StatBonus.values()).filter(statBonus -> containsKey(statBonusData, JsonUtils.NAME) && statBonus.getName().equals(statBonusData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(StatBonus.NONE);
    }

    @Override
    protected SpellSave deserializeSpellSave(ConfigurationNode spellSaveData) {
        SpellSave spellSave = new SpellSave();
        spellSave.setSaveDCType(deserializeSpellcasterClass(spellSaveData.getNode(spellSaveData, JsonUtils.SAVE_DC_TYPE)));

        spellSave.setOnSuccessfulSave(getStringList(spellSaveData, JsonUtils.SAVE_DC_TYPE));
        spellSave.setSavingStat(spellSaveData.getNode(JsonUtils.SAVING_STAT).getString(""));
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(ConfigurationNode spellTypeData) {
        return Stream.of(SpellType.values()).filter(spellType -> containsKey(spellTypeData, JsonUtils.NAME) && spellType.getName().equals(spellTypeData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(SpellType.OTHER);
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(ConfigurationNode spellbookTabData, ClassLevels classLevels) {
        SpellbookTab spellbookTab = new SpellbookTab();
        spellbookTab.setSpells(getConfigurationNodeList(spellbookTabData, JsonUtils.SPELLS).stream().map(this::deserializeSpell).collect(Collectors.toList()));
        spellbookTab.setSpellcasterClasses(getConfigurationNodeList(spellbookTabData, JsonUtils.SPELLCASTER_CLASSES).stream().map(this::deserializeSpellcasterClass).collect(Collectors.toList()));
        return spellbookTab;
    }

    @Override
    protected SpellcasterClass deserializeSpellcasterClass(ConfigurationNode spellcasterClassData) {
        return Stream.of(SpellcasterClass.values()).filter(spellcasterClass -> containsKey(spellcasterClassData, JsonUtils.NAME) && spellcasterClass.getName().equals(spellcasterClassData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(SpellcasterClass.OTHER);
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(ConfigurationNode spellcastingBonusData) {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonus.setAttack(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, JsonUtils.ATTACK)));
        spellcastingBonus.setDamage(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, JsonUtils.DAMAGE)));
        spellcastingBonus.setSaveDC(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, JsonUtils.SAVE_DC)));
        return spellcastingBonus;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(ConfigurationNode unarmoredBonusData) {
        return Stream.of(UnarmoredBonus.values()).filter(unarmoredBonus -> containsKey(unarmoredBonusData, JsonUtils.NAME) && unarmoredBonus.getName().equals(unarmoredBonusData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(UnarmoredBonus.NONE);
    }

    @Override
    protected Wealth deserializeWealth(ConfigurationNode wealthData) {
        Wealth wealth = new Wealth();
        wealth.setCopper(deserializeCoin(wealthData.getNode(wealthData, JsonUtils.COPPER), new Coin("Copper", "CP")));
        wealth.setElectrum(deserializeCoin(wealthData.getNode(wealthData, JsonUtils.ELECTRUM), new Coin("Electrum", "EP")));
        wealth.setGold(deserializeCoin(wealthData.getNode(wealthData, JsonUtils.GOLD), new Coin("Gold", "GP")));
        wealth.setPlatinum(deserializeCoin(wealthData.getNode(wealthData, JsonUtils.PLATINUM), new Coin("Platinum", "PP")));
        wealth.setPlatinum(deserializeCoin(wealthData.getNode(wealthData, JsonUtils.SILVER), new Coin("Silver", "SP")));
        return wealth;
    }

    @Override
    protected WeaponAttackStat deserializeWeaponAttackStat(ConfigurationNode weaponAttackStatData) {
        return Stream.of(WeaponAttackStat.values()).filter(weaponAttackStatus -> containsKey(weaponAttackStatData, JsonUtils.NAME) && weaponAttackStatus.getName().equals(weaponAttackStatData.getNode(JsonUtils.NAME).getString())).findFirst().orElse(WeaponAttackStat.STR);
    }

    @Override
    protected WeaponsTab deserializeWeaponsTab(ConfigurationNode weaponsTabData) {
        WeaponsTab weaponsTab = new WeaponsTab();
        weaponsTab.setMeleeWeapons(getConfigurationNodeList(weaponsTabData, JsonUtils.MELEE_WEAPONS).stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList()));
        weaponsTab.setRangedWeapons(getConfigurationNodeList(weaponsTabData, JsonUtils.RANGED_WEAPONS).stream().map(this::deserializeRangedWeapon).collect(Collectors.toList()));
        return weaponsTab;
    }

    @Override
    protected Weight deserializeWeight(ConfigurationNode weightData, CoreStats coreStats, List<MCDNDItem> inventory, Wealth wealth) {
        Weight weight = new Weight();
        weight.setOther(weightData.getNode(JsonUtils.OTHER).getDouble(0));
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
