package io.musician101.mcdndsimple.spigot.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCAction;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCActionType;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCActions;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCHitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.NPCSkills;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.TraitsBackground;
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
import org.bukkit.configuration.MemoryConfiguration;

public class SpigotMCDNDDeserializer extends MCDNDDeserializer<MemoryConfiguration> {

    private boolean containsKey(MemoryConfiguration memoryConfiguration, String key) {
        return memoryConfiguration.contains(key);
    }

    @Override
    public NonPlayerSheet deserializeNPC(MemoryConfiguration nonPlayerData) {
        NonPlayerSheet nonPlayerSheet = new NonPlayerSheet();
        nonPlayerSheet.setName(nonPlayerData.getString(Keys.NAME));
        nonPlayerSheet.setClazz(nonPlayerData.getString(Keys.CLASS));
        nonPlayerSheet.setRace(nonPlayerData.getString(Keys.RACE));
        nonPlayerSheet.setActions(deserializeNPCActions(getMemoryConfiguration(nonPlayerData, Keys.NPC_ACTIONS)));
        nonPlayerSheet.setNPCSheet(deserializeNPCSheet(getMemoryConfiguration(nonPlayerData, Keys.CHARACTER_SHEET)));
        NPCSkills skills = new NPCSkills();
        skills.updateSkills(nonPlayerSheet.getNPCSheet().getCoreStats());
        nonPlayerSheet.setSkills(skills);
        nonPlayerSheet.setTraitsBackground(deserializeTraitsBackground(getMemoryConfiguration(nonPlayerData, Keys.TRAITS_BACKGROUND)));
        return nonPlayerSheet;
    }

    @Override
    public NPCActions deserializeNPCActions(MemoryConfiguration npcActionsData) {
        NPCActions npcActions = new NPCActions();
        npcActions.setActions(getMemoryConfigurationList(npcActionsData, Keys.NPC_ACTIONS).stream().map(this::deserializeNPCAction).collect(Collectors.toList()));
        npcActions.setMultiAttack(npcActionsData.getString(Keys.MULTI_ATTACK));
        return npcActions;
    }

    @Override
    public NPCAction deserializeNPCAction(MemoryConfiguration npcActionData) {
        NPCAction npcAction = new NPCAction();
        npcAction.setActionType(deserializeNPCActionType(getMemoryConfiguration(npcActionData, Keys.ACTION_TYPE)));
        npcAction.setDescription(npcActionData.getStringList(Keys.DESCRIPTION));
        npcAction.setEffect(npcActionData.getStringList(Keys.EFFECTS));
        npcAction.setMultiAttack(npcActionData.getBoolean(Keys.MULTI_ATTACK));
        npcAction.setName(npcActionData.getString(Keys.NAME));
        return npcAction;
    }

    @Override
    public NPCActionType deserializeNPCActionType(MemoryConfiguration npcActionTypeData) {
        return Stream.of(NPCActionType.values()).filter(npcActionType -> containsKey(npcActionTypeData, Keys.NAME) && npcActionType.getName().equals(npcActionTypeData.getName())).findFirst().orElse(NPCActionType.NORMAL);
    }

    @Override
    public NPCSheet deserializeNPCSheet(MemoryConfiguration npcSheetData) {
        NPCSheet npcSheet = new NPCSheet();
        npcSheet.setAlignment(npcSheetData.getString(Keys.ALIGNMENT));
        npcSheet.setArmorClass(npcSheetData.getInt(Keys.ARMOR_CLASS));
        npcSheet.setArmorClassNote(npcSheetData.getString(Keys.ARMOR_CLASS_NOTE));
        npcSheet.setChallengeRating(npcSheetData.getDouble(Keys.CHALLENGE_RATING));
        npcSheet.setClimbSpeed(npcSheetData.getInt(Keys.CLIMB_SPEED));
        npcSheet.setCoreStats(deserializeCoreStats(getMemoryConfiguration(npcSheetData, Keys.CORE_STATS)));
        npcSheet.setDMOutputOnly(npcSheetData.getBoolean(Keys.DM_OUTPUT_ONLY));
        npcSheet.setFlySpeed(npcSheetData.getInt(Keys.FLY_SPEED));
        npcSheet.setHealth(deserializeNPCHitPoints(getMemoryConfiguration(npcSheetData, Keys.HIT_POINTS)));
        npcSheet.setLanguages(npcSheetData.getString(Keys.LANGUAGES));
        npcSheet.setSenses(npcSheetData.getString(Keys.SENSES));
        npcSheet.setSize(npcSheetData.getString(Keys.SIZE));
        npcSheet.setSpeed(npcSheetData.getInt(Keys.SPEED));
        npcSheet.setSwimSpeed(npcSheetData.getInt(Keys.SWIM_SPEED));
        npcSheet.setTypeRace(npcSheetData.getString(Keys.TYPE_RACE));
        npcSheet.setXp(npcSheetData.getInt(Keys.EXPERIENCE));
        return npcSheet;
    }

    @Override
    public NPCHitPoints deserializeNPCHitPoints(MemoryConfiguration npcHitPointsData) {
        NPCHitPoints npcHitPoints = (NPCHitPoints) deserializeHitPoints(npcHitPointsData);
        npcHitPoints.setHitDice(deserializeDice(getMemoryConfiguration(npcHitPointsData, Keys.HIT_DICE)));
        return npcHitPoints;
    }

    @Override
    public TraitsBackground deserializeTraitsBackground(MemoryConfiguration traitsBackgroundData) {
        TraitsBackground traitsBackground = new TraitsBackground();
        traitsBackground.setConditionImmunity(traitsBackgroundData.getString(Keys.CONDITION_IMMUNITY));
        traitsBackground.setDamageImmunity(traitsBackgroundData.getString(Keys.DAMAGE_IMMUNITY));
        traitsBackground.setDamageResistance(traitsBackgroundData.getString(Keys.DAMAGE_RESISTANCE));
        traitsBackground.setDamageVulnerability(traitsBackgroundData.getString(Keys.DAMAGE_VULNERABILITY));
        traitsBackground.setTraits(traitsBackgroundData.getStringList(Keys.TRAITS));
        return traitsBackground;
    }

    @Override
    public PlayerSheet deserializePC(MemoryConfiguration playerData) {
        PlayerSheet playerSheet = new PlayerSheet();
        playerSheet.setBioAndInfo(deserializeBioAndInfo(getMemoryConfiguration(playerData, Keys.BIO_AND_INFO)));
        playerSheet.setCharacterSheet(deserializePlayerSheet(getMemoryConfiguration(playerData, Keys.PLAYER_SHEET)));
        playerSheet.setClazz(playerData.getString(Keys.CLASS, ""));
        playerSheet.setName(playerData.getString(Keys.NAME, ""));
        playerSheet.setRace(playerData.getString(Keys.RACE, ""));
        return playerSheet;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(MemoryConfiguration abilityScoreData, AbilityScore defaultScore) {
        if (containsKey(abilityScoreData, Keys.NAME) || containsKey(abilityScoreData, Keys.SHORT_NAME)) {
            AbilityScore abilityScore = new AbilityScore(abilityScoreData.getString(Keys.NAME), abilityScoreData.getString(Keys.SHORT_NAME));
            abilityScore.setProficient(abilityScoreData.getBoolean(Keys.IS_PROFICIENT, false));
            abilityScore.setScore(abilityScoreData.getInt(Keys.SCORE, 0));
            return abilityScore;
        }

        return defaultScore;
    }

    @Override
    protected Armor deserializeArmor(MemoryConfiguration armorData) {
        Armor armor = new Armor();
        armor.setSpeedPenalty(armorData.getBoolean(Keys.SPEED_PENALTY, false));
        armor.setStealthPenalty(armorData.getBoolean(Keys.STEALTH_PENALTY, false));
        armor.setIsUnarmored(armorData.getBoolean(Keys.UNARMORED, false));
        armor.setIsWorn(armorData.getBoolean(Keys.WORN, false));
        armor.setBaseArmorClass(armorData.getInt(Keys.BASE_ARMOR_CLASS, 0));
        armor.setMagicBonus(armorData.getInt(Keys.MAGIC_BONUS, 0));
        armor.setArmorType(deserializeArmorType(getMemoryConfiguration(armorData, Keys.ARMOR_TYPE)));
        armor.setName(armorData.getString(Keys.NAME, ""));
        return armor;
    }

    @Override
    protected ArmorTab deserializeArmorTab(MemoryConfiguration armorTabData) {
        ArmorTab armorTab = new ArmorTab();
        armorTab.setArmorClass(armorTabData.getInt(Keys.ARMOR_CLASS, 0));
        armorTab.setUnarmoredClass(armorTabData.getInt(Keys.UNARMORED_ARMOR_CLASS, 0));
        armorTab.setArmor(getMemoryConfigurationList(armorTabData, Keys.ARMOR_LIST).stream().map(this::deserializeArmor).collect(Collectors.toList()));
        armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(getMemoryConfiguration(armorTabData, Keys.UNARMORED_BONUS)));
        return armorTab;
    }

    @Override
    protected ArmorType deserializeArmorType(MemoryConfiguration armorTypeData) {
        return Stream.of(ArmorType.values()).filter(armorType -> containsKey(armorTypeData, Keys.NAME) && armorType.getName().equals(armorTypeData.getString(Keys.NAME))).findFirst().orElse(ArmorType.NONE);
    }

    @Override
    protected BackgroundTab deserializeBackgroundTab(MemoryConfiguration backgroundTabData) {
        BackgroundTab backgroundTab = new BackgroundTab();
        backgroundTab.setWeight(backgroundTabData.getDouble(Keys.WEIGHT_DOUBLE, 0));
        backgroundTab.setAge(backgroundTabData.getInt(Keys.AGE, 0));
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
        backgroundTab.setAlignment(backgroundTabData.getString(Keys.ALIGNMENT, ""));
        backgroundTab.setEyes(backgroundTabData.getString(Keys.EYES, ""));
        backgroundTab.setGender(backgroundTabData.getString(Keys.GENDER, ""));
        backgroundTab.setHair(backgroundTabData.getString(Keys.HAIR, ""));
        backgroundTab.setHeight(backgroundTabData.getString(Keys.HEIGHT, ""));
        backgroundTab.setLanguages(backgroundTabData.getStringList(Keys.LANGUAGES));
        backgroundTab.setSize(backgroundTabData.getString(Keys.SIZE, ""));
        backgroundTab.setVision(backgroundTabData.getString(Keys.VISION, ""));
        return backgroundTab;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(MemoryConfiguration bioAndInfoData) {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfo.setName(bioAndInfoData.getString(Keys.NAME, ""));
        bioAndInfo.setBio(getStringList(bioAndInfoData, Keys.BIO));
        return bioAndInfo;
    }

    @Override
    protected Bonuses deserializeBonuses(MemoryConfiguration bonusesData) {
        Bonuses bonuses = new Bonuses();
        bonuses.setMelee(deserializeMeleeBonus(getMemoryConfiguration(bonusesData, Keys.MELEE_BONUS)));
        bonuses.setRanged(deserializeRangedBonus(getMemoryConfiguration(bonusesData, Keys.RANGED_BONUS)));
        bonuses.setSpellcasting(deserializeSpellcastingBonus(getMemoryConfiguration(bonusesData, Keys.SPELLCASTING_BONUS)));
        bonuses.setSaves(deserializeDice(getMemoryConfiguration(bonusesData, Keys.SAVES)));
        bonuses.setAbilitiesAndSkills(deserializeDice(getMemoryConfiguration(bonusesData, Keys.ABILITIES_AND_SKILLS)));
        return bonuses;
    }

    @Override
    protected ClassAction deserializeClassAction(MemoryConfiguration classActionData) {
        ClassAction classAction = new ClassAction();
        classAction.setMax(classActionData.getInt(Keys.MAX_USES, 0));
        classAction.setUsedCharges(classActionData.getInt(Keys.USES_LEFT, 0));
        classAction.setRecharge(deserializeRecharge(getMemoryConfiguration(classActionData, Keys.RECHARGE)));
        classAction.setGainedFrom(classActionData.getString(Keys.GAINED_FROM, ""));
        classAction.setName(classActionData.getString(Keys.NAME, ""));
        return classAction;
    }

    @Override
    protected ClassLevels deserializeClassLevels(MemoryConfiguration classLevelsData) {
        ClassLevels classLevels = new ClassLevels();
        classLevels.setBarbarian(classLevelsData.getInt(Keys.BARBARIAN_LEVEL, 0));
        classLevels.setBard(classLevelsData.getInt(Keys.BARD_LEVEL, 0));
        classLevels.setCleric(classLevelsData.getInt(Keys.CLERIC_LEVEL, 0));
        classLevels.setDruid(classLevelsData.getInt(Keys.DRUID_LEVEL, 0));
        classLevels.setFighter(classLevelsData.getInt(Keys.FIGHTER_LEVEL, 0));
        classLevels.setMonk(classLevelsData.getInt(Keys.MONK_LEVEL, 0));
        classLevels.setPaladin(classLevelsData.getInt(Keys.PALADIN_LEVEL, 0));
        classLevels.setRanger(classLevelsData.getInt(Keys.RANGER_LEVEL, 0));
        classLevels.setRogue(classLevelsData.getInt(Keys.ROGUE_LEVEL, 0));
        classLevels.setSorcerer(classLevelsData.getInt(Keys.SORCERER_LEVEL, 0));
        classLevels.setWarlock(classLevelsData.getInt(Keys.WARLOCK_LEVEL, 0));
        classLevels.setWizard(classLevelsData.getInt(Keys.WIZARD_LEVEL, 0));
        return classLevels;
    }

    @Override
    protected ClassResource deserializeClassResource(MemoryConfiguration classResourceData) {
        ClassResource classResource = new ClassResource();
        classResource.setCurrentCharges(classResourceData.getInt(Keys.USES_LEFT, 0));
        classResource.setMaxCharges(classResourceData.getInt(Keys.MAX_USES, 0));
        classResource.setRecharge(deserializeRecharge(getMemoryConfiguration(classResourceData, Keys.RECHARGE)));
        classResource.setName(classResourceData.getString(Keys.NAME, ""));
        return classResource;
    }

    @Override
    protected ClassTab deserializeClassTab(MemoryConfiguration classTabData) {
        ClassTab classTab = new ClassTab();
        classTab.setClassLevels(deserializeClassLevels(getMemoryConfiguration(classTabData, Keys.CLASS_LEVELS)));
        classTab.setClassActions(getMemoryConfigurationList(classTabData, Keys.CLASS_ACTIONS).stream().map(this::deserializeClassAction).collect(Collectors.toList()));
        classTab.setClassResources(getMemoryConfigurationList(classTabData, Keys.CLASS_RESOURCES).stream().map(this::deserializeClassResource).collect(Collectors.toList()));
        classTab.setClassFeatureNotes(getStringList(classTabData, Keys.CLASS_FEATURE_NOTES));
        return classTab;
    }

    @Override
    protected Coin deserializeCoin(MemoryConfiguration coinData, Coin defaultCoin) {
        Coin coin = new Coin(coinData.getString(Keys.NAME), coinData.getString(Keys.SHORT_NAME));
        coin.setAmount(coinData.getInt(Keys.AMOUNT, 0));
        return coin;
    }

    @Override
    protected CoreStats deserializeCoreStats(MemoryConfiguration coreStatsData) {
        CoreStats coreStats = new CoreStats();
        coreStats.setCharisma(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, Keys.CHARISMA_SCORE), new AbilityScore("Charisma", "CHA")));
        coreStats.setConstitution(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, Keys.CONSTITUTION_SCORE), new AbilityScore("Constitution", "CON")));
        coreStats.setDexterity(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, Keys.DEXTERITY_SCORE), new AbilityScore("Dexterity", "DEX")));
        coreStats.setIntelligence(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, Keys.INTELLIGENCE_SCORE), new AbilityScore("Intelligence", "INT")));
        coreStats.setStrength(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, Keys.STRENGTH_SCORE), new AbilityScore("Strength", "STR")));
        coreStats.setWisdom(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, Keys.WISDOM_SCORE), new AbilityScore("Wisdom", "WIS")));
        return coreStats;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(MemoryConfiguration coreStatsTabData) {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        coreStatsTab.setBonuses(deserializeBonuses(getMemoryConfiguration(coreStatsTabData, Keys.BONUSES)));
        coreStatsTab.setCoreStats(deserializeCoreStats(getMemoryConfiguration(coreStatsTabData, Keys.CORE_STATS)));
        coreStatsTab.setExperience(deserializeExperience(getMemoryConfiguration(coreStatsTabData, Keys.EXPERIENCE)));
        coreStatsTab.setHitDice(deserializeHitDice(getMemoryConfiguration(coreStatsTabData, Keys.HIT_DICE)));
        coreStatsTab.setHitPoints(deserializeHitPoints(getMemoryConfiguration(coreStatsTabData, Keys.HIT_POINTS)));
        coreStatsTab.setSpeed(coreStatsTabData.getInt(Keys.SPEED, 0));
        return coreStatsTab;
    }

    @Override
    protected Dice deserializeDice(MemoryConfiguration diceData) {
        return new Dice(diceData.getInt(Keys.SIDES, 0), diceData.getInt(Keys.AMOUNT, 0));
    }

    @Override
    protected Experience deserializeExperience(MemoryConfiguration experienceData) {
        Experience experience = new Experience();
        experience.setExp(experienceData.getInt(Keys.EXPERIENCE, 0));
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
        hitPoints.setCurrent(hitPointsData.getInt(Keys.CURRENT_HP, 0));
        hitPoints.setMax(hitPointsData.getInt(Keys.MAX_HP, 0));
        hitPoints.setTemp(hitPointsData.getInt(Keys.TEMP_HP, 0));
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(MemoryConfiguration inventoryTabData, CoreStats coreStats) {
        InventoryTab inventoryTab = new InventoryTab();
        inventoryTab.setInventory(getMemoryConfigurationList(inventoryTabData, Keys.INVENTORY).stream().map(this::deserializeItem).collect(Collectors.toList()));
        inventoryTab.setInventoryNotes(getStringList(inventoryTabData, Keys.INVENTORY_NOTES));
        inventoryTab.setWealth(deserializeWealth(getMemoryConfiguration(inventoryTabData, Keys.WEALTH)));
        inventoryTab.setWeight(deserializeWeight(getMemoryConfiguration(inventoryTabData, Keys.WEIGHT_CLASS), coreStats, inventoryTab.getInventory(), inventoryTab.getWealth()));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(MemoryConfiguration itemData) {
        MCDNDItem item = new MCDNDItem();
        item.setIsCarried(itemData.getBoolean(Keys.CARRIED));
        item.setWeight(itemData.getDouble(Keys.WEIGHT_DOUBLE));
        item.setDescription(itemData.getStringList(Keys.DESCRIPTION));
        item.setName(itemData.getString(Keys.NAME));
        return item;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(MemoryConfiguration meleeBonusData) {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonus.setAttack(deserializeDice(getMemoryConfiguration(meleeBonusData, Keys.ATTACK)));
        meleeBonus.setDamage(deserializeDice(getMemoryConfiguration(meleeBonusData, Keys.DAMAGE)));
        return meleeBonus;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(MemoryConfiguration meleeWeaponData) {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeapon.setPlusStat(meleeWeaponData.getBoolean(Keys.PLUS_STAT, false));
        meleeWeapon.setIsProficient(meleeWeaponData.getBoolean(Keys.IS_PROFICIENT, false));
        meleeWeapon.setCritDamageDice(deserializeDice(getMemoryConfiguration(meleeWeaponData, Keys.CRIT_DAMAGE_DICE)));
        meleeWeapon.setDamageDice(deserializeDice(getMemoryConfiguration(meleeWeaponData, Keys.DAMAGE_DICE)));
        meleeWeapon.setCritMin(meleeWeaponData.getInt(Keys.CRIT_MINIMUM, 0));
        meleeWeapon.setMagicBonus(meleeWeaponData.getInt(Keys.MAGIC_BONUS, 0));
        meleeWeapon.setAttackStat(deserializeWeaponAttackStat(getMemoryConfiguration(meleeWeaponData, Keys.ATTACK_STAT)));
        meleeWeapon.setName(meleeWeaponData.getString(Keys.NAME, ""));
        return meleeWeapon;
    }

    @Override
    protected CharacterSheet deserializePlayerSheet(MemoryConfiguration playerSheetData) {
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setArmorTab(deserializeArmorTab(getMemoryConfiguration(playerSheetData, Keys.ARMOR_TAB)));
        characterSheet.setBackgroundTab(deserializeBackgroundTab(getMemoryConfiguration(playerSheetData, Keys.BACKGROUND_TAB)));
        characterSheet.setClassTab(deserializeClassTab(getMemoryConfiguration(playerSheetData, Keys.CLASS_TAB)));
        characterSheet.setCoreStatsTab(deserializeCoreStatsTab(getMemoryConfiguration(playerSheetData, Keys.CORE_STATS_TAB)));
        characterSheet.setInventoryTab(deserializeInventoryTab(getMemoryConfiguration(playerSheetData, Keys.INVENTORY_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSkillsTab(deserializeSkillsTab(getMemoryConfiguration(playerSheetData, Keys.SKILLS_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSpellbookTab(deserializeSpellbookTab(getMemoryConfiguration(playerSheetData, Keys.SPELL_BOOK_TAB), characterSheet.getClassTab().getClassLevels()));
        characterSheet.setWeaponsTab(deserializeWeaponsTab(getMemoryConfiguration(playerSheetData, Keys.WEAPONS_TAB)));
        return characterSheet;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(MemoryConfiguration rangedBonusData) {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonus.setAttack(deserializeDice(getMemoryConfiguration(rangedBonusData, Keys.ATTACK)));
        rangedBonus.setDamage(deserializeDice(getMemoryConfiguration(rangedBonusData, Keys.DAMAGE)));
        return rangedBonus;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(MemoryConfiguration rangedWeaponData) {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeapon.setAmmo(rangedWeaponData.getInt(Keys.PLUS_STAT, 0));
        rangedWeapon.setIsProficient(rangedWeaponData.getBoolean(Keys.IS_PROFICIENT, false));
        rangedWeapon.setCritDamageDice(deserializeDice(getMemoryConfiguration(rangedWeaponData, Keys.CRIT_DAMAGE_DICE)));
        rangedWeapon.setDamageDice(deserializeDice(getMemoryConfiguration(rangedWeaponData, Keys.DAMAGE_DICE)));
        rangedWeapon.setCritMin(rangedWeaponData.getInt(Keys.CRIT_MINIMUM, 0));
        rangedWeapon.setMagicBonus(rangedWeaponData.getInt(Keys.MAGIC_BONUS, 0));
        rangedWeapon.setAttackStat(deserializeWeaponAttackStat(getMemoryConfiguration(rangedWeaponData, Keys.ATTACK_STAT)));
        rangedWeapon.setName(rangedWeaponData.getString(Keys.NAME, ""));
        return rangedWeapon;
    }

    @Override
    protected Recharge deserializeRecharge(MemoryConfiguration rechargeData) {
        return Stream.of(Recharge.values()).filter(recharge -> containsKey(rechargeData, Keys.NAME) && recharge.getName().equals(rechargeData.getString(Keys.NAME))).findFirst().orElse(Recharge.OTHER);
    }

    @Override
    protected SaveDCType deserializeSaveDCType(MemoryConfiguration spellData) {
        if (containsKey(spellData, Keys.NAME)) {
            String name = spellData.getString(Keys.NAME);
            if ("Custom DC".equals(name)) {
                return SaveDCTypes.custom(spellData.getInt(Keys.CUSTOM_DC, 0));
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
        return Stream.of(SkillProficiency.values()).filter(statBonus -> containsKey(skillsProficiencyData, Keys.NAME) && statBonus.getName().equals(skillsProficiencyData.getString(Keys.NAME))).findFirst().orElse(SkillProficiency.NONE);
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
        spell.setNeedsConcentration(spellData.getBoolean(Keys.NEEDS_CONCENTRATION, false));
        spell.setPrepared(Prepared.valueOf(spellData.getString(Keys.PREPARED, "NO")));
        spell.setDuration(spellData.getString(Keys.DURATION, ""));
        spell.setLevel(spellData.getInt(Keys.SPELL_LEVEL, 0));
        spell.setRange(spellData.getString(Keys.RANGE, ""));
        spell.setSpellDamage(deserializeSpellDamage(getMemoryConfiguration(spellData, Keys.SPELL_DAMAGE)));
        spell.setDescription(getStringList(spellData, Keys.SPELL_DESCRIPTION));
        spell.setEffects(getStringList(spellData, Keys.EFFECTS));
        spell.setSpellSave(deserializeSpellSave(getMemoryConfiguration(spellData, Keys.SPELL_SAVE)));
        spell.setGainedFrom(deserializeSpellcasterClass(getMemoryConfiguration(spellData, Keys.SPELLCASTER_CLASS)));
        spell.setSpellType(deserializeSpellType(getMemoryConfiguration(spellData, Keys.SPELL_TYPE)));
        spell.setSpellHealing(deserializeSpellHealing(getMemoryConfiguration(spellData, Keys.SPELL_HEALING)));
        spell.setAttackStat(deserializeStatBonus(getMemoryConfiguration(spellData, Keys.ATTACK_STAT)));
        spell.setCastTime(spellData.getString(Keys.CAST_TIME, ""));
        spell.setTargetArea(spellData.getString(Keys.TARGET_AREA, ""));
        return spell;
    }

    @Override
    protected SpellDamage deserializeSpellDamage(MemoryConfiguration spellDamageData) {
        SpellDamage spellDamage = new SpellDamage();
        spellDamage.setCanCrit(spellDamageData.getBoolean(Keys.CAN_CRIT, false));
        spellDamage.setDice(deserializeDice(getMemoryConfiguration(spellDamageData, Keys.DICE)));
        spellDamageData.getInt(Keys.BONUS, 0);
        spellDamage.setDamageType(spellDamageData.getString(Keys.DAMAGE_TYPE, ""));
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(MemoryConfiguration spellHealingData) {
        SpellHealing spellHealing = new SpellHealing();
        deserializeDice(getMemoryConfiguration(spellHealingData, Keys.HEAL_AMOUNT));
        spellHealing.setHealAmount(deserializeDice(getMemoryConfiguration(spellHealingData, Keys.HEAL_AMOUNT)));
        spellHealing.setStatBonus(deserializeStatBonus(getMemoryConfiguration(spellHealingData, Keys.STAT_BONUS)));
        return spellHealing;
    }

    @Override
    protected StatBonus deserializeStatBonus(MemoryConfiguration statBonusData) {
        return Stream.of(StatBonus.values()).filter(statBonus -> containsKey(statBonusData, Keys.NAME) && statBonus.getName().equals(statBonusData.getString(Keys.NAME))).findFirst().orElse(StatBonus.NONE);
    }

    @Override
    protected SpellSave deserializeSpellSave(MemoryConfiguration spellSaveData) {
        SpellSave spellSave = new SpellSave();
        spellSave.setSaveDCType(deserializeSpellcasterClass(getMemoryConfiguration(spellSaveData, Keys.SAVE_DC_TYPE)));

        spellSave.setOnSuccessfulSave(getStringList(spellSaveData, Keys.SAVE_DC_TYPE));
        spellSave.setSavingStat(spellSaveData.getString(Keys.SAVING_STAT, ""));
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(MemoryConfiguration spellTypeData) {
        return Stream.of(SpellType.values()).filter(spellType -> containsKey(spellTypeData, Keys.NAME) && spellType.getName().equals(spellTypeData.getString(Keys.NAME))).findFirst().orElse(SpellType.OTHER);
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(MemoryConfiguration spellbookTabData, ClassLevels classLevels) {
        SpellbookTab spellbookTab = new SpellbookTab();
        spellbookTab.setSpells(getMemoryConfigurationList(spellbookTabData, Keys.SPELLS).stream().map(this::deserializeSpell).collect(Collectors.toList()));
        spellbookTab.setSpellcasterClasses(getMemoryConfigurationList(spellbookTabData, Keys.SPELLCASTER_CLASSES).stream().map(this::deserializeSpellcasterClass).collect(Collectors.toList()));
        return spellbookTab;
    }

    @Override
    protected SpellcasterClass deserializeSpellcasterClass(MemoryConfiguration spellcasterClassData) {
        return Stream.of(SpellcasterClass.values()).filter(spellcasterClass -> containsKey(spellcasterClassData, Keys.NAME) && spellcasterClass.getName().equals(spellcasterClassData.getString(Keys.NAME))).findFirst().orElse(SpellcasterClass.OTHER);
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(MemoryConfiguration spellcastingBonusData) {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonus.setAttack(deserializeDice(getMemoryConfiguration(spellcastingBonusData, Keys.ATTACK)));
        spellcastingBonus.setDamage(deserializeDice(getMemoryConfiguration(spellcastingBonusData, Keys.DAMAGE)));
        spellcastingBonus.setSaveDC(deserializeDice(getMemoryConfiguration(spellcastingBonusData, Keys.SAVE_DC)));
        return spellcastingBonus;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(MemoryConfiguration unarmoredBonusData) {
        return Stream.of(UnarmoredBonus.values()).filter(unarmoredBonus -> containsKey(unarmoredBonusData, Keys.NAME) && unarmoredBonus.getName().equals(unarmoredBonusData.getString(Keys.NAME))).findFirst().orElse(UnarmoredBonus.NONE);
    }

    @Override
    protected Wealth deserializeWealth(MemoryConfiguration wealthData) {
        Wealth wealth = new Wealth();
        wealth.setCopper(deserializeCoin(getMemoryConfiguration(wealthData, Keys.COPPER), new Coin("Copper", "CP")));
        wealth.setElectrum(deserializeCoin(getMemoryConfiguration(wealthData, Keys.ELECTRUM), new Coin("Electrum", "EP")));
        wealth.setGold(deserializeCoin(getMemoryConfiguration(wealthData, Keys.GOLD), new Coin("Gold", "GP")));
        wealth.setPlatinum(deserializeCoin(getMemoryConfiguration(wealthData, Keys.PLATINUM), new Coin("Platinum", "PP")));
        wealth.setPlatinum(deserializeCoin(getMemoryConfiguration(wealthData, Keys.SILVER), new Coin("Silver", "SP")));
        return wealth;
    }

    @Override
    protected WeaponAttackStat deserializeWeaponAttackStat(MemoryConfiguration weaponAttackStatData) {
        return Stream.of(WeaponAttackStat.values()).filter(weaponAttackStatus -> containsKey(weaponAttackStatData, Keys.NAME) && weaponAttackStatus.getName().equals(weaponAttackStatData.getString(Keys.NAME))).findFirst().orElse(WeaponAttackStat.STR);
    }

    @Override
    protected WeaponsTab deserializeWeaponsTab(MemoryConfiguration weaponsTabData) {
        WeaponsTab weaponsTab = new WeaponsTab();
        weaponsTab.setMeleeWeapons(getMemoryConfigurationList(weaponsTabData, Keys.MELEE_WEAPONS).stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList()));
        weaponsTab.setRangedWeapons(getMemoryConfigurationList(weaponsTabData, Keys.RANGED_WEAPONS).stream().map(this::deserializeRangedWeapon).collect(Collectors.toList()));
        return weaponsTab;
    }

    @Override
    protected Weight deserializeWeight(MemoryConfiguration weightData, CoreStats coreStats, List<MCDNDItem> inventory, Wealth wealth) {
        Weight weight = new Weight();
        weight.setOther(weightData.getDouble(Keys.OTHER, 0));
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
