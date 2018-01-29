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
import io.musician101.mcdndsimple.common.serialization.Keys;
import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
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
        playerSheet.setBioAndInfo(deserializeBioAndInfo(playerData.getNode(playerData, Keys.BIO_AND_INFO)));
        playerSheet.setCharacterSheet(deserializePlayerSheet(playerData.getNode(playerData, Keys.PLAYER_SHEET)));
        playerSheet.setClazz(playerData.getNode(Keys.CLASS).getString(""));
        playerSheet.setName(playerData.getNode(Keys.NAME).getString(""));
        playerSheet.setRace(playerData.getNode(Keys.RACE).getString(""));
        return playerSheet;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(ConfigurationNode abilityScoreData, AbilityScore defaultScore) {
        if (containsKey(abilityScoreData, Keys.NAME) || containsKey(abilityScoreData, Keys.SHORT_NAME)) {
            AbilityScore abilityScore = new AbilityScore(abilityScoreData.getNode(Keys.NAME).getString(), abilityScoreData.getNode(Keys.SHORT_NAME).getString());
            abilityScore.setProficient(abilityScoreData.getNode(Keys.IS_PROFICIENT).getBoolean(false));
            abilityScore.setScore(abilityScoreData.getNode(Keys.SCORE).getInt(0));
            return abilityScore;
        }

        return defaultScore;
    }

    @Override
    protected Armor deserializeArmor(ConfigurationNode armorData) {
        Armor armor = new Armor();
        armor.setSpeedPenalty(armorData.getNode(Keys.SPEED_PENALTY).getBoolean(false));
        armor.setStealthPenalty(armorData.getNode(Keys.STEALTH_PENALTY).getBoolean(false));
        armor.setIsUnarmored(armorData.getNode(Keys.UNARMORED).getBoolean(false));
        armor.setIsWorn(armorData.getNode(Keys.WORN).getBoolean(false));
        armor.setBaseArmorClass(armorData.getNode(Keys.BASE_ARMOR_CLASS).getInt(0));
        armor.setMagicBonus(armorData.getNode(Keys.MAGIC_BONUS).getInt(0));
        armor.setArmorType(deserializeArmorType(armorData.getNode(armorData, Keys.ARMOR_TYPE)));
        armor.setName(armorData.getNode(Keys.NAME).getString(""));
        return armor;
    }

    @Override
    protected ArmorTab deserializeArmorTab(ConfigurationNode armorTabData) {
        ArmorTab armorTab = new ArmorTab();
        armorTab.setArmorClass(armorTabData.getNode(Keys.ARMOR_CLASS).getInt(0));
        armorTab.setUnarmoredClass(armorTabData.getNode(Keys.UNARMORED_ARMOR_CLASS).getInt(0));
        armorTab.setArmor(getConfigurationNodeList(armorTabData, Keys.ARMOR_LIST).stream().map(this::deserializeArmor).collect(Collectors.toList()));
        armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(armorTabData.getNode(armorTabData, Keys.UNARMORED_BONUS)));
        return armorTab;
    }

    @Override
    protected ArmorType deserializeArmorType(ConfigurationNode armorTypeData) {
        return Stream.of(ArmorType.values()).filter(armorType -> containsKey(armorTypeData, Keys.NAME) && armorType.getName().equals(armorTypeData.getNode(Keys.NAME).getString())).findFirst().orElse(ArmorType.NONE);
    }

    @Override
    protected BackgroundTab deserializeBackgroundTab(ConfigurationNode backgroundTabData) {
        BackgroundTab backgroundTab = new BackgroundTab();
        backgroundTab.setWeight(backgroundTabData.getNode(Keys.WEIGHT_DOUBLE).getDouble(0));
        backgroundTab.setAge(backgroundTabData.getNode(Keys.AGE).getInt(0));
        backgroundTab.setArmorProficiencies(getStringList(backgroundTabData, Keys.ARMOR_PROFICIENCIES));
        backgroundTab.setBackground(getStringList(backgroundTabData, Keys.BACKGROUND));
        backgroundTab.setBonds(getStringList(backgroundTabData, Keys.BONDS));
        backgroundTab.setFlaws(getStringList(backgroundTabData, Keys.FLAWS));
        backgroundTab.setIdeals(getStringList(backgroundTabData, Keys.IDEALS));
        backgroundTab.setOtherNotes(getStringList(backgroundTabData, Keys.OTHER_NOTES));
        backgroundTab.setPersonalityTraits(getStringList(backgroundTabData, Keys.PERSONALITY_TRAITS));
        backgroundTab.setRacialTraits(getStringList(backgroundTabData, Keys.RACIAL_TRAITS));
        backgroundTab.setToolProficiencies(getStringList(backgroundTabData, Keys.TOOL_PROFICIENCIES));
        backgroundTab.setWeaponProficiencies(getStringList(backgroundTabData, Keys.WEAPON_PROFICIENCIES));
        backgroundTab.setAlignment(backgroundTabData.getNode(Keys.ALIGNMENT).getString(""));
        backgroundTab.setEyes(backgroundTabData.getNode(Keys.EYES).getString(""));
        backgroundTab.setGender(backgroundTabData.getNode(Keys.GENDER).getString(""));
        backgroundTab.setHair(backgroundTabData.getNode(Keys.HAIR).getString(""));
        backgroundTab.setHeight(backgroundTabData.getNode(Keys.HEIGHT).getString(""));
        backgroundTab.setLanguages(getStringList(backgroundTabData, Keys.LANGUAGES));
        backgroundTab.setSize(backgroundTabData.getNode(Keys.SIZE).getString(""));
        backgroundTab.setVision(backgroundTabData.getNode(Keys.VISION).getString(""));
        return backgroundTab;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(ConfigurationNode bioAndInfoData) {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfo.setName(bioAndInfoData.getNode(Keys.NAME).getString(""));
        bioAndInfo.setBio(getStringList(bioAndInfoData, Keys.BIO));
        return bioAndInfo;
    }

    @Override
    protected Bonuses deserializeBonuses(ConfigurationNode bonusesData) {
        Bonuses bonuses = new Bonuses();
        bonuses.setMelee(deserializeMeleeBonus(bonusesData.getNode(bonusesData, Keys.MELEE_BONUS)));
        bonuses.setRanged(deserializeRangedBonus(bonusesData.getNode(bonusesData, Keys.RANGED_BONUS)));
        bonuses.setSpellcasting(deserializeSpellcastingBonus(bonusesData.getNode(bonusesData, Keys.SPELLCASTING_BONUS)));
        bonuses.setSaves(deserializeDice(bonusesData.getNode(bonusesData, Keys.SAVES)));
        bonuses.setAbilitiesAndSkills(deserializeDice(bonusesData.getNode(bonusesData, Keys.ABILITIES_AND_SKILLS)));
        return bonuses;
    }

    @Override
    protected ClassAction deserializeClassAction(ConfigurationNode classActionData) {
        ClassAction classAction = new ClassAction();
        classAction.setMax(classActionData.getNode(Keys.MAX_USES).getInt(0));
        classAction.setUsedCharges(classActionData.getNode(Keys.USES_LEFT).getInt(0));
        classAction.setRecharge(deserializeRecharge(classActionData.getNode(classActionData, Keys.RECHARGE)));
        classAction.setGainedFrom(classActionData.getNode(Keys.GAINED_FROM).getString(""));
        classAction.setName(classActionData.getNode(Keys.NAME).getString(""));
        return classAction;
    }

    @Override
    protected ClassLevels deserializeClassLevels(ConfigurationNode classLevelsData) {
        ClassLevels classLevels = new ClassLevels();
        classLevels.setBarbarian(classLevelsData.getNode(Keys.BARBARIAN_LEVEL).getInt(0));
        classLevels.setBard(classLevelsData.getNode(Keys.BARD_LEVEL).getInt(0));
        classLevels.setCleric(classLevelsData.getNode(Keys.CLERIC_LEVEL).getInt(0));
        classLevels.setDruid(classLevelsData.getNode(Keys.DRUID_LEVEL).getInt(0));
        classLevels.setFighter(classLevelsData.getNode(Keys.FIGHTER_LEVEL).getInt(0));
        classLevels.setMonk(classLevelsData.getNode(Keys.MONK_LEVEL).getInt(0));
        classLevels.setPaladin(classLevelsData.getNode(Keys.PALADIN_LEVEL).getInt(0));
        classLevels.setRanger(classLevelsData.getNode(Keys.RANGER_LEVEL).getInt(0));
        classLevels.setRogue(classLevelsData.getNode(Keys.ROGUE_LEVEL).getInt(0));
        classLevels.setSorcerer(classLevelsData.getNode(Keys.SORCERER_LEVEL).getInt(0));
        classLevels.setWarlock(classLevelsData.getNode(Keys.WARLOCK_LEVEL).getInt(0));
        classLevels.setWizard(classLevelsData.getNode(Keys.WIZARD_LEVEL).getInt(0));
        return classLevels;
    }

    @Override
    protected ClassResource deserializeClassResource(ConfigurationNode classResourceData) {
        ClassResource classResource = new ClassResource();
        classResource.setCurrentCharges(classResourceData.getNode(Keys.USES_LEFT).getInt(0));
        classResource.setMaxCharges(classResourceData.getNode(Keys.MAX_USES).getInt(0));
        classResource.setRecharge(deserializeRecharge(classResourceData.getNode(classResourceData, Keys.RECHARGE)));
        classResource.setName(classResourceData.getNode(Keys.NAME).getString(""));
        return classResource;
    }

    @Override
    protected ClassTab deserializeClassTab(ConfigurationNode classTabData) {
        ClassTab classTab = new ClassTab();
        classTab.setClassLevels(deserializeClassLevels(classTabData.getNode(classTabData, Keys.CLASS_LEVELS)));
        classTab.setClassActions(getConfigurationNodeList(classTabData, Keys.CLASS_ACTIONS).stream().map(this::deserializeClassAction).collect(Collectors.toList()));
        classTab.setClassResources(getConfigurationNodeList(classTabData, Keys.CLASS_RESOURCES).stream().map(this::deserializeClassResource).collect(Collectors.toList()));
        classTab.setClassFeatureNotes(getStringList(classTabData, Keys.CLASS_FEATURE_NOTES));
        return classTab;
    }

    @Override
    protected Coin deserializeCoin(ConfigurationNode coinData, Coin defaultCoin) {
        Coin coin = new Coin(coinData.getNode(Keys.NAME).getString(), coinData.getNode(Keys.SHORT_NAME).getString());
        coin.setAmount(coinData.getNode(Keys.AMOUNT).getInt(0));
        return coin;
    }

    @Override
    protected CoreStats deserializeCoreStats(ConfigurationNode coreStatsData) {
        CoreStats coreStats = new CoreStats();
        coreStats.setCharisma(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, Keys.CHARISMA_SCORE), new AbilityScore("Charisma", "CHA")));
        coreStats.setConstitution(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, Keys.CONSTITUTION_SCORE), new AbilityScore("Constitution", "CON")));
        coreStats.setDexterity(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, Keys.DEXTERITY_SCORE), new AbilityScore("Dexterity", "DEX")));
        coreStats.setIntelligence(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, Keys.INTELLIGENCE_SCORE), new AbilityScore("Intelligence", "INT")));
        coreStats.setStrength(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, Keys.STRENGTH_SCORE), new AbilityScore("Strength", "STR")));
        coreStats.setWisdom(deserializeAbilityScore(coreStatsData.getNode(coreStatsData, Keys.WISDOM_SCORE), new AbilityScore("Wisdom", "WIS")));
        return coreStats;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(ConfigurationNode coreStatsTabData) {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        coreStatsTab.setBonuses(deserializeBonuses(coreStatsTabData.getNode(coreStatsTabData, Keys.BONUSES)));
        coreStatsTab.setCoreStats(deserializeCoreStats(coreStatsTabData.getNode(coreStatsTabData, Keys.CORE_STATS)));
        coreStatsTab.setExperience(deserializeExperience(coreStatsTabData.getNode(coreStatsTabData, Keys.EXPERIENCE)));
        coreStatsTab.setHitDice(deserializeHitDice(coreStatsTabData.getNode(coreStatsTabData, Keys.HIT_DICE)));
        coreStatsTab.setHitPoints(deserializeHitPoints(coreStatsTabData.getNode(coreStatsTabData, Keys.HIT_POINTS)));
        coreStatsTab.setSpeed(coreStatsTabData.getNode(Keys.SPEED).getInt(0));
        return coreStatsTab;
    }

    @Override
    protected Dice deserializeDice(ConfigurationNode diceData) {
        return new Dice(diceData.getNode(Keys.SIDES).getInt(0), diceData.getNode(Keys.AMOUNT).getInt(0));
    }

    @Override
    protected Experience deserializeExperience(ConfigurationNode experienceData) {
        Experience experience = new Experience();
        experience.setExp(experienceData.getNode(Keys.EXPERIENCE).getInt(0));
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
        hitPoints.setCurrent(hitPointsData.getNode(Keys.CURRENT_HP).getInt(0));
        hitPoints.setMax(hitPointsData.getNode(Keys.MAX_HP).getInt(0));
        hitPoints.setTemp(hitPointsData.getNode(Keys.TEMP_HP).getInt(0));
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(ConfigurationNode inventoryTabData, CoreStats coreStats) {
        InventoryTab inventoryTab = new InventoryTab();
        inventoryTab.setInventory(getConfigurationNodeList(inventoryTabData, Keys.INVENTORY).stream().map(this::deserializeItem).collect(Collectors.toList()));
        inventoryTab.setInventoryNotes(getStringList(inventoryTabData, Keys.INVENTORY_NOTES));
        inventoryTab.setWealth(deserializeWealth(inventoryTabData.getNode(inventoryTabData, Keys.WEALTH)));
        inventoryTab.setWeight(deserializeWeight(inventoryTabData.getNode(inventoryTabData, Keys.WEIGHT_CLASS), coreStats, inventoryTab.getInventory(), inventoryTab.getWealth()));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(ConfigurationNode itemData) {
        MCDNDItem item = new MCDNDItem();
        item.setIsCarried(itemData.getNode(Keys.CARRIED).getBoolean(true));
        item.setWeight(itemData.getNode(Keys.WEIGHT_DOUBLE).getDouble(0));
        item.setDescription(getStringList(itemData, Keys.DESCRIPTION));
        item.setName(itemData.getNode(Keys.NAME).getString(""));
        return item;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(ConfigurationNode meleeBonusData) {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonus.setAttack(deserializeDice(meleeBonusData.getNode(meleeBonusData, Keys.ATTACK)));
        meleeBonus.setDamage(deserializeDice(meleeBonusData.getNode(meleeBonusData, Keys.DAMAGE)));
        return meleeBonus;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(ConfigurationNode meleeWeaponData) {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeapon.setPlusStat(meleeWeaponData.getNode(Keys.PLUS_STAT).getBoolean(false));
        meleeWeapon.setIsProficient(meleeWeaponData.getNode(Keys.IS_PROFICIENT).getBoolean(false));
        meleeWeapon.setCritDamageDice(deserializeDice(meleeWeaponData.getNode(meleeWeaponData, Keys.CRIT_DAMAGE_DICE)));
        meleeWeapon.setDamageDice(deserializeDice(meleeWeaponData.getNode(meleeWeaponData, Keys.DAMAGE_DICE)));
        meleeWeapon.setCritMin(meleeWeaponData.getNode(Keys.CRIT_MINIMUM).getInt(0));
        meleeWeapon.setMagicBonus(meleeWeaponData.getNode(Keys.MAGIC_BONUS).getInt(0));
        meleeWeapon.setAttackStat(deserializeWeaponAttackStat(meleeWeaponData.getNode(meleeWeaponData, Keys.ATTACK_STAT)));
        meleeWeapon.setName(meleeWeaponData.getNode(Keys.NAME).getString(""));
        return meleeWeapon;
    }

    @Override
    protected CharacterSheet deserializePlayerSheet(ConfigurationNode playerSheetData) {
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setArmorTab(deserializeArmorTab(playerSheetData.getNode(playerSheetData, Keys.ARMOR_TAB)));
        characterSheet.setBackgroundTab(deserializeBackgroundTab(playerSheetData.getNode(playerSheetData, Keys.BACKGROUND_TAB)));
        characterSheet.setClassTab(deserializeClassTab(playerSheetData.getNode(playerSheetData, Keys.CLASS_TAB)));
        characterSheet.setCoreStatsTab(deserializeCoreStatsTab(playerSheetData.getNode(playerSheetData, Keys.CORE_STATS_TAB)));
        characterSheet.setInventoryTab(deserializeInventoryTab(playerSheetData.getNode(playerSheetData, Keys.INVENTORY_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSkillsTab(deserializeSkillsTab(playerSheetData.getNode(playerSheetData, Keys.SKILLS_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSpellbookTab(deserializeSpellbookTab(playerSheetData.getNode(playerSheetData, Keys.SPELL_BOOK_TAB), characterSheet.getClassTab().getClassLevels()));
        characterSheet.setWeaponsTab(deserializeWeaponsTab(playerSheetData.getNode(playerSheetData, Keys.WEAPONS_TAB)));
        return characterSheet;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(ConfigurationNode rangedBonusData) {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonus.setAttack(deserializeDice(rangedBonusData.getNode(rangedBonusData, Keys.ATTACK)));
        rangedBonus.setDamage(deserializeDice(rangedBonusData.getNode(rangedBonusData, Keys.DAMAGE)));
        return rangedBonus;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(ConfigurationNode rangedWeaponData) {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeapon.setAmmo(rangedWeaponData.getNode(Keys.PLUS_STAT).getInt(0));
        rangedWeapon.setIsProficient(rangedWeaponData.getNode(Keys.IS_PROFICIENT).getBoolean(false));
        rangedWeapon.setCritDamageDice(deserializeDice(rangedWeaponData.getNode(rangedWeaponData, Keys.CRIT_DAMAGE_DICE)));
        rangedWeapon.setDamageDice(deserializeDice(rangedWeaponData.getNode(rangedWeaponData, Keys.DAMAGE_DICE)));
        rangedWeapon.setCritMin(rangedWeaponData.getNode(Keys.CRIT_MINIMUM).getInt(0));
        rangedWeapon.setMagicBonus(rangedWeaponData.getNode(Keys.MAGIC_BONUS).getInt(0));
        rangedWeapon.setAttackStat(deserializeWeaponAttackStat(rangedWeaponData.getNode(rangedWeaponData, Keys.ATTACK_STAT)));
        rangedWeapon.setName(rangedWeaponData.getNode(Keys.NAME).getString(""));
        return rangedWeapon;
    }

    @Override
    protected Recharge deserializeRecharge(ConfigurationNode rechargeData) {
        return Stream.of(Recharge.values()).filter(recharge -> containsKey(rechargeData, Keys.NAME) && recharge.getName().equals(rechargeData.getNode(Keys.NAME).getString())).findFirst().orElse(Recharge.OTHER);
    }

    @Override
    protected SaveDCType deserializeSaveDCType(ConfigurationNode spellData) {
        if (containsKey(spellData, Keys.NAME)) {
            String name = spellData.getNode(Keys.NAME).getString();
            if ("Custom DC".equals(name)) {
                return SaveDCTypes.custom(spellData.getNode(Keys.CUSTOM_DC).getInt(0));
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
        return Stream.of(SkillProficiency.values()).filter(skillProficiency -> containsKey(skillsProficiencyData, Keys.NAME) && skillProficiency.getName().equals(skillsProficiencyData.getNode(Keys.NAME).getString())).findFirst().orElse(SkillProficiency.NONE);
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
        spell.setNeedsConcentration(spellData.getNode(Keys.NEEDS_CONCENTRATION).getBoolean(false));
        spell.setPrepared(Prepared.valueOf(spellData.getNode(Keys.PREPARED).getString("NO")));
        spell.setDuration(spellData.getNode(Keys.DURATION).getString(""));
        spell.setLevel(spellData.getNode(Keys.SPELL_LEVEL).getInt(0));
        spell.setRange(spellData.getNode(Keys.RANGE).getString(""));
        spell.setSpellDamage(deserializeSpellDamage(spellData.getNode(spellData, Keys.SPELL_DAMAGE)));
        spell.setDescription(getStringList(spellData, Keys.SPELL_DESCRIPTION));
        spell.setEffects(getStringList(spellData, Keys.EFFECTS));
        spell.setSpellSave(deserializeSpellSave(spellData.getNode(spellData, Keys.SPELL_SAVE)));
        spell.setGainedFrom(deserializeSpellcasterClass(spellData.getNode(spellData, Keys.SPELLCASTER_CLASS)));
        spell.setSpellType(deserializeSpellType(spellData.getNode(spellData, Keys.SPELL_TYPE)));
        spell.setSpellHealing(deserializeSpellHealing(spellData.getNode(spellData, Keys.SPELL_HEALING)));
        spell.setAttackStat(deserializeStatBonus(spellData.getNode(Keys.ATTACK_STAT)));
        spell.setCastTime(spellData.getNode(Keys.CAST_TIME).getString(""));
        spell.setTargetArea(spellData.getNode(Keys.TARGET_AREA).getString(""));
        return spell;
    }

    @Override
    protected SpellDamage deserializeSpellDamage(ConfigurationNode spellDamageData) {
        SpellDamage spellDamage = new SpellDamage();
        spellDamage.setCanCrit(spellDamageData.getNode(Keys.CAN_CRIT).getBoolean(false));
        spellDamage.setDice(deserializeDice(spellDamageData.getNode(spellDamageData, Keys.DICE)));
        spellDamageData.getNode(Keys.BONUS).getInt(0);
        spellDamage.setDamageType(spellDamageData.getNode(Keys.DAMAGE_TYPE).getString(""));
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(ConfigurationNode spellHealingData) {
        SpellHealing spellHealing = new SpellHealing();
        deserializeDice(spellHealingData.getNode(spellHealingData, Keys.HEAL_AMOUNT));
        spellHealing.setHealAmount(deserializeDice(spellHealingData.getNode(spellHealingData, Keys.HEAL_AMOUNT)));
        spellHealing.setStatBonus(deserializeStatBonus(spellHealingData.getNode(Keys.STAT_BONUS)));
        return spellHealing;
    }

    @Override
    protected StatBonus deserializeStatBonus(ConfigurationNode statBonusData) {
        return Stream.of(StatBonus.values()).filter(statBonus -> containsKey(statBonusData, Keys.NAME) && statBonus.getName().equals(statBonusData.getNode(Keys.NAME).getString())).findFirst().orElse(StatBonus.NONE);
    }

    @Override
    protected SpellSave deserializeSpellSave(ConfigurationNode spellSaveData) {
        SpellSave spellSave = new SpellSave();
        spellSave.setSaveDCType(deserializeSpellcasterClass(spellSaveData.getNode(spellSaveData, Keys.SAVE_DC_TYPE)));

        spellSave.setOnSuccessfulSave(getStringList(spellSaveData, Keys.SAVE_DC_TYPE));
        spellSave.setSavingStat(spellSaveData.getNode(Keys.SAVING_STAT).getString(""));
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(ConfigurationNode spellTypeData) {
        return Stream.of(SpellType.values()).filter(spellType -> containsKey(spellTypeData, Keys.NAME) && spellType.getName().equals(spellTypeData.getNode(Keys.NAME).getString())).findFirst().orElse(SpellType.OTHER);
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(ConfigurationNode spellbookTabData, ClassLevels classLevels) {
        SpellbookTab spellbookTab = new SpellbookTab();
        spellbookTab.setSpells(getConfigurationNodeList(spellbookTabData, Keys.SPELLS).stream().map(this::deserializeSpell).collect(Collectors.toList()));
        spellbookTab.setSpellcasterClasses(getConfigurationNodeList(spellbookTabData, Keys.SPELLCASTER_CLASSES).stream().map(this::deserializeSpellcasterClass).collect(Collectors.toList()));
        return spellbookTab;
    }

    @Override
    protected SpellcasterClass deserializeSpellcasterClass(ConfigurationNode spellcasterClassData) {
        return Stream.of(SpellcasterClass.values()).filter(spellcasterClass -> containsKey(spellcasterClassData, Keys.NAME) && spellcasterClass.getName().equals(spellcasterClassData.getNode(Keys.NAME).getString())).findFirst().orElse(SpellcasterClass.OTHER);
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(ConfigurationNode spellcastingBonusData) {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonus.setAttack(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, Keys.ATTACK)));
        spellcastingBonus.setDamage(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, Keys.DAMAGE)));
        spellcastingBonus.setSaveDC(deserializeDice(spellcastingBonusData.getNode(spellcastingBonusData, Keys.SAVE_DC)));
        return spellcastingBonus;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(ConfigurationNode unarmoredBonusData) {
        return Stream.of(UnarmoredBonus.values()).filter(unarmoredBonus -> containsKey(unarmoredBonusData, Keys.NAME) && unarmoredBonus.getName().equals(unarmoredBonusData.getNode(Keys.NAME).getString())).findFirst().orElse(UnarmoredBonus.NONE);
    }

    @Override
    protected Wealth deserializeWealth(ConfigurationNode wealthData) {
        Wealth wealth = new Wealth();
        wealth.setCopper(deserializeCoin(wealthData.getNode(wealthData, Keys.COPPER), new Coin("Copper", "CP")));
        wealth.setElectrum(deserializeCoin(wealthData.getNode(wealthData, Keys.ELECTRUM), new Coin("Electrum", "EP")));
        wealth.setGold(deserializeCoin(wealthData.getNode(wealthData, Keys.GOLD), new Coin("Gold", "GP")));
        wealth.setPlatinum(deserializeCoin(wealthData.getNode(wealthData, Keys.PLATINUM), new Coin("Platinum", "PP")));
        wealth.setPlatinum(deserializeCoin(wealthData.getNode(wealthData, Keys.SILVER), new Coin("Silver", "SP")));
        return wealth;
    }

    @Override
    protected WeaponAttackStat deserializeWeaponAttackStat(ConfigurationNode weaponAttackStatData) {
        return Stream.of(WeaponAttackStat.values()).filter(weaponAttackStatus -> containsKey(weaponAttackStatData, Keys.NAME) && weaponAttackStatus.getName().equals(weaponAttackStatData.getNode(Keys.NAME).getString())).findFirst().orElse(WeaponAttackStat.STR);
    }

    @Override
    protected WeaponsTab deserializeWeaponsTab(ConfigurationNode weaponsTabData) {
        WeaponsTab weaponsTab = new WeaponsTab();
        weaponsTab.setMeleeWeapons(getConfigurationNodeList(weaponsTabData, Keys.MELEE_WEAPONS).stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList()));
        weaponsTab.setRangedWeapons(getConfigurationNodeList(weaponsTabData, Keys.RANGED_WEAPONS).stream().map(this::deserializeRangedWeapon).collect(Collectors.toList()));
        return weaponsTab;
    }

    @Override
    protected Weight deserializeWeight(ConfigurationNode weightData, CoreStats coreStats, List<MCDNDItem> inventory, Wealth wealth) {
        Weight weight = new Weight();
        weight.setOther(weightData.getNode(Keys.OTHER).getDouble(0));
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
