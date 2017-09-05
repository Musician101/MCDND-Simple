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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.configuration.MemoryConfiguration;

public class SpigotMCDNDDeserializer extends MCDNDDeserializer<MemoryConfiguration>
{
    @Override
    public PlayerSheet deserialize(MemoryConfiguration characterSheetData)
    {
        PlayerSheet playerSheet = new PlayerSheet();
        playerSheet.setBioAndInfo(deserializeBioAndInfo(getMemoryConfiguration(characterSheetData, SpigotMCDNDSimpleKeys.BIO_AND_INFO)));
        playerSheet.setCharacterSheet(deserializePlayerSheet(getMemoryConfiguration(characterSheetData, SpigotMCDNDSimpleKeys.PLAYER_SHEET)));
        playerSheet.setClazz(characterSheetData.getString(SpigotMCDNDSimpleKeys.CLASS, ""));
        playerSheet.setName(characterSheetData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        playerSheet.setRace(characterSheetData.getString(SpigotMCDNDSimpleKeys.RACE, ""));
        return playerSheet;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(MemoryConfiguration bioAndInfoData)
    {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfo.setName(bioAndInfoData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        bioAndInfo.setBio(getStringList(bioAndInfoData, SpigotMCDNDSimpleKeys.BIO));
        return bioAndInfo;
    }

    @Override
    protected SkillsTab deserializeSkillsTab(MemoryConfiguration skillsTabData, CoreStats coreStats)
    {
        SkillsTab skillsTab = new SkillsTab();
        skillsTab.updateSkills(coreStats);
        return skillsTab;
    }

    @Override
    protected SkillProficiency deserializeSkillProficiency(MemoryConfiguration skillsProficiencyData)
    {
        if (containsKey(skillsProficiencyData, SpigotMCDNDSimpleKeys.NAME))
            for (SkillProficiency skillProficiency : SkillProficiency.values())
                if (skillsProficiencyData.getString(SpigotMCDNDSimpleKeys.NAME).equals(skillProficiency.getName()))
                    return skillProficiency;

        return SkillProficiency.NONE;
    }

    @Override
    protected ArmorTab deserializeArmorTab(MemoryConfiguration armorTabData)
    {
        ArmorTab armorTab = new ArmorTab();
        armorTab.setArmorClass(armorTabData.getInt(SpigotMCDNDSimpleKeys.ARMOR_CLASS, 0));
        armorTab.setUnarmoredClass(armorTabData.getInt(SpigotMCDNDSimpleKeys.UNARMORED_ARMOR_CLASS, 0));
        armorTab.setArmor(getMemoryConfigurationList(armorTabData, SpigotMCDNDSimpleKeys.ARMOR_LIST).stream().map(this::deserializeArmor).collect(Collectors.toList()));
        armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(getMemoryConfiguration(armorTabData, SpigotMCDNDSimpleKeys.UNARMORED_BONUS)));
        return armorTab;
    }

    @Override
    protected Armor deserializeArmor(MemoryConfiguration armorData)
    {
        Armor armor = new Armor();
        armor.setSpeedPenalty(armorData.getBoolean(SpigotMCDNDSimpleKeys.SPEED_PENALTY, false));
        armor.setStealthPenalty(armorData.getBoolean(SpigotMCDNDSimpleKeys.STEALTH_PENALTY, false));
        armor.setIsUnarmored(armorData.getBoolean(SpigotMCDNDSimpleKeys.UNARMORED, false));
        armor.setIsWorn(armorData.getBoolean(SpigotMCDNDSimpleKeys.WORN, false));
        armor.setBaseArmorClass(armorData.getInt(SpigotMCDNDSimpleKeys.BASE_ARMOR_CLASS, 0));
        armor.setMagicBonus(armorData.getInt(SpigotMCDNDSimpleKeys.MAGIC_BONUS, 0));
        armor.setArmorType(deserializeArmorType(getMemoryConfiguration(armorData, SpigotMCDNDSimpleKeys.ARMOR_TYPE)));
        armor.setName(armorData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return armor;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(MemoryConfiguration unarmoredBonusData)
    {
        if (containsKey(unarmoredBonusData, SpigotMCDNDSimpleKeys.NAME))
        {
            for (UnarmoredBonus unarmoredBonus : UnarmoredBonus.values())
                if (unarmoredBonusData.getString(SpigotMCDNDSimpleKeys.NAME).equals(unarmoredBonus.getName()))
                    return unarmoredBonus;
        }

        return UnarmoredBonus.NONE;
    }

    @Override
    protected BackgroundTab deserializeBackgroundTab(MemoryConfiguration backgroundTabData)
    {
        BackgroundTab backgroundTab = new BackgroundTab();
        backgroundTab.setWeight(backgroundTabData.getDouble(SpigotMCDNDSimpleKeys.WEIGHT_DOUBLE, 0));
        backgroundTab.setAge(backgroundTabData.getInt(SpigotMCDNDSimpleKeys.AGE, 0));
        backgroundTab.setArmorProficiencies(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.ARMOR_PROFICIENCIES));
        backgroundTab.setBackground(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.BACKGROUND));
        backgroundTab.setBonds(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.BONDS));
        backgroundTab.setFlaws(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.FLAWS));
        backgroundTab.setIdeals(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.IDEALS));
        backgroundTab.setOtherNotes(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.OTHER_NOTES));
        backgroundTab.setPersonalityTraits(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.PERSONALITY_TRAITS));
        backgroundTab.setRacialTraits(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.RACIAL_TRAITS));
        backgroundTab.setToolProficiencies(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.TOOL_PROFICIENCIES));
        backgroundTab.setWeaponProficiencies(getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.WEAPON_PROFICIENCIES));
        backgroundTab.setAlignment(backgroundTabData.getString(SpigotMCDNDSimpleKeys.ALIGNMENT, ""));
        backgroundTab.setEyes(backgroundTabData.getString(SpigotMCDNDSimpleKeys.EYES, ""));
        backgroundTab.setGender(backgroundTabData.getString(SpigotMCDNDSimpleKeys.GENDER, ""));
        backgroundTab.setHair(backgroundTabData.getString(SpigotMCDNDSimpleKeys.HAIR, ""));
        backgroundTab.setHeight(backgroundTabData.getString(SpigotMCDNDSimpleKeys.HEIGHT, ""));
        backgroundTab.setLanguages(backgroundTabData.getStringList(SpigotMCDNDSimpleKeys.LANGUAGES));
        backgroundTab.setSize(backgroundTabData.getString(SpigotMCDNDSimpleKeys.SIZE, ""));
        backgroundTab.setVision(backgroundTabData.getString(SpigotMCDNDSimpleKeys.VISION, ""));
        return backgroundTab;
    }

    @Override
    protected Recharge deserializeRecharge(MemoryConfiguration rechargeData)
    {
        if (containsKey(rechargeData, SpigotMCDNDSimpleKeys.NAME))
            for (Recharge recharge : Recharge.values())
                if (recharge.getName().equals(rechargeData.getString(SpigotMCDNDSimpleKeys.NAME)))
                    return recharge;

        return Recharge.NONE;
    }

    @Override
    protected ClassTab deserializeClassTab(MemoryConfiguration classTabData)
    {
        ClassTab classTab = new ClassTab();
        classTab.setClassLevels(deserializeClassLevels(getMemoryConfiguration(classTabData, SpigotMCDNDSimpleKeys.CLASS_LEVELS)));
        classTab.setClassActions(getMemoryConfigurationList(classTabData, SpigotMCDNDSimpleKeys.CLASS_ACTIONS).stream().map(this::deserializeClassAction).collect(Collectors.toList()));
        classTab.setClassResources(getMemoryConfigurationList(classTabData, SpigotMCDNDSimpleKeys.CLASS_RESOURCES).stream().map(this::deserializeClassResource).collect(Collectors.toList()));
        classTab.setClassFeatureNotes(getStringList(classTabData, SpigotMCDNDSimpleKeys.CLASS_FEATURE_NOTES));
        return classTab;
    }

    @Override
    protected ClassLevels deserializeClassLevels(MemoryConfiguration classLevelsData)
    {
        ClassLevels classLevels = new ClassLevels();
        classLevels.setBarbarian(classLevelsData.getInt(SpigotMCDNDSimpleKeys.BARBARIAN_LEVEL, 0));
        classLevels.setBard(classLevelsData.getInt(SpigotMCDNDSimpleKeys.BARD_LEVEL, 0));
        classLevels.setCleric(classLevelsData.getInt(SpigotMCDNDSimpleKeys.CLERIC_LEVEL, 0));
        classLevels.setDruid(classLevelsData.getInt(SpigotMCDNDSimpleKeys.DRUID_LEVEL, 0));
        classLevels.setFighter(classLevelsData.getInt(SpigotMCDNDSimpleKeys.FIGHTER_LEVEL, 0));
        classLevels.setMonk(classLevelsData.getInt(SpigotMCDNDSimpleKeys.MONK_LEVEL, 0));
        classLevels.setPaladin(classLevelsData.getInt(SpigotMCDNDSimpleKeys.PALADIN_LEVEL, 0));
        classLevels.setRanger(classLevelsData.getInt(SpigotMCDNDSimpleKeys.RANGER_LEVEL, 0));
        classLevels.setRogue(classLevelsData.getInt(SpigotMCDNDSimpleKeys.ROGUE_LEVEL, 0));
        classLevels.setSorcerer(classLevelsData.getInt(SpigotMCDNDSimpleKeys.SORCERER_LEVEL, 0));
        classLevels.setWarlock(classLevelsData.getInt(SpigotMCDNDSimpleKeys.WARLOCK_LEVEL, 0));
        classLevels.setWizard(classLevelsData.getInt(SpigotMCDNDSimpleKeys.WIZARD_LEVEL, 0));
        return classLevels;
    }

    @Override
    protected ClassAction deserializeClassAction(MemoryConfiguration classActionData)
    {
        ClassAction classAction = new ClassAction();
        classAction.setMax(classActionData.getInt(SpigotMCDNDSimpleKeys.MAX_USES, 0));
        classAction.setUsedCharges(classActionData.getInt(SpigotMCDNDSimpleKeys.USES_LEFT, 0));
        classAction.setRecharge(deserializeRecharge(getMemoryConfiguration(classActionData, SpigotMCDNDSimpleKeys.RECHARGE)));
        classAction.setGainedFrom(classActionData.getString(SpigotMCDNDSimpleKeys.GAINED_FROM, ""));
        classAction.setName(classActionData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return classAction;
    }

    @Override
    protected ClassResource deserializeClassResource(MemoryConfiguration classResourceData)
    {
        ClassResource classResource = new ClassResource();
        classResource.setCurrentCharges(classResourceData.getInt(SpigotMCDNDSimpleKeys.USES_LEFT, 0));
        classResource.setMaxCharges(classResourceData.getInt(SpigotMCDNDSimpleKeys.MAX_USES, 0));
        classResource.setRecharge(deserializeRecharge(getMemoryConfiguration(classResourceData, SpigotMCDNDSimpleKeys.RECHARGE)));
        classResource.setName(classResourceData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return classResource;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(MemoryConfiguration coreStatsTabData)
    {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        coreStatsTab.setBonuses(deserializeBonuses(getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.BONUSES)));
        coreStatsTab.setCoreStats(deserializeCoreStats(getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.CORE_STATS)));
        coreStatsTab.setExperience(deserializeExperience(getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.EXPERIENCE)));
        coreStatsTab.setHitDice(deserializeHitDice(getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.HIT_DICE)));
        coreStatsTab.setHitPoints(deserializeHitPoints(getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.HIT_POINTS)));
        coreStatsTab.setSpeed(coreStatsTabData.getInt(SpigotMCDNDSimpleKeys.SPEED, 0));
        return coreStatsTab;
    }

    @Override
    protected Bonuses deserializeBonuses(MemoryConfiguration bonusesData)
    {
        Bonuses bonuses = new Bonuses();
        bonuses.setMelee(deserializeMeleeBonus(getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.MELEE_BONUS)));
        bonuses.setRanged(deserializeRangedBonus(getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.RANGED_BONUS)));
        bonuses.setSpellcasting(deserializeSpellcastingBonus(getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.SPELLCASTING_BONUS)));
        bonuses.setSaves(deserializeDice(getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.SAVES)));
        bonuses.setAbilitiesAndSkills(deserializeDice(getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.ABILITIES_AND_SKILLS)));
        return bonuses;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(MemoryConfiguration meleeBonusData)
    {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonus.setAttack(deserializeDice(getMemoryConfiguration(meleeBonusData, SpigotMCDNDSimpleKeys.ATTACK)));
        meleeBonus.setDamage(deserializeDice(getMemoryConfiguration(meleeBonusData, SpigotMCDNDSimpleKeys.DAMAGE)));
        return meleeBonus;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(MemoryConfiguration rangedBonusData)
    {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonus.setAttack(deserializeDice(getMemoryConfiguration(rangedBonusData, SpigotMCDNDSimpleKeys.ATTACK)));
        rangedBonus.setDamage(deserializeDice(getMemoryConfiguration(rangedBonusData, SpigotMCDNDSimpleKeys.DAMAGE)));
        return rangedBonus;
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(MemoryConfiguration spellcastingBonusData)
    {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonus.setAttack(deserializeDice(getMemoryConfiguration(spellcastingBonusData, SpigotMCDNDSimpleKeys.ATTACK)));
        spellcastingBonus.setDamage(deserializeDice(getMemoryConfiguration(spellcastingBonusData, SpigotMCDNDSimpleKeys.DAMAGE)));
        spellcastingBonus.setSaveDC(deserializeDice(getMemoryConfiguration(spellcastingBonusData, SpigotMCDNDSimpleKeys.SAVE_DC)));
        return spellcastingBonus;
    }

    @Override
    protected CoreStats deserializeCoreStats(MemoryConfiguration coreStatsData)
    {
        CoreStats coreStats = new CoreStats();
        coreStats.setCharisma(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.CHARISMA_SCORE), new AbilityScore("Charisma", "CHA")));
        coreStats.setConstitution(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.CONSTITUTION_SCORE), new AbilityScore("Constitution", "CON")));
        coreStats.setDexterity(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.DEXTERITY_SCORE), new AbilityScore("Dexterity", "DEX")));
        coreStats.setIntelligence(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.INTELLIGENCE_SCORE), new AbilityScore("Intelligence", "INT")));
        coreStats.setStrength(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.STRENGTH_SCORE), new AbilityScore("Strength", "STR")));
        coreStats.setWisdom(deserializeAbilityScore(getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.WISDOM_SCORE), new AbilityScore("Wisdom", "WIS")));
        return coreStats;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(MemoryConfiguration abilityScoreData, AbilityScore defaultScore)
    {
        if (containsKey(abilityScoreData, SpigotMCDNDSimpleKeys.NAME) || containsKey(abilityScoreData, SpigotMCDNDSimpleKeys.SHORT_NAME))
        {
            AbilityScore abilityScore = new AbilityScore(abilityScoreData.getString(SpigotMCDNDSimpleKeys.NAME), abilityScoreData.getString(SpigotMCDNDSimpleKeys.SHORT_NAME));
            abilityScore.setProficient(abilityScoreData.getBoolean(SpigotMCDNDSimpleKeys.IS_PROFICIENT, false));
            abilityScore.setScore(abilityScoreData.getInt(SpigotMCDNDSimpleKeys.SCORE, 0));
            return abilityScore;
        }

        return defaultScore;
    }

    @Override
    protected Experience deserializeExperience(MemoryConfiguration experienceData)
    {
        Experience experience = new Experience();
        experience.setExp(experienceData.getInt(SpigotMCDNDSimpleKeys.EXPERIENCE, 0));
        return experience;
    }

    @Override
    protected HitDice deserializeHitDice(MemoryConfiguration hitDiceData)
    {
        HitDice hitDice = new HitDice();
        hitDiceData.getKeys(false).forEach(key -> hitDiceData.getInt(key, 0));
        return hitDice;
    }

    @Override
    protected Dice deserializeDice(MemoryConfiguration diceData)
    {
        return new Dice(diceData.getInt(SpigotMCDNDSimpleKeys.SIDES, 0), diceData.getInt(SpigotMCDNDSimpleKeys.AMOUNT, 0));
    }

    @Override
    protected HitPoints deserializeHitPoints(MemoryConfiguration hitPointsData)
    {
        HitPoints hitPoints = new HitPoints();
        hitPoints.setCurrent(hitPointsData.getInt(SpigotMCDNDSimpleKeys.CURRENT_HP, 0));
        hitPoints.setMax(hitPointsData.getInt(SpigotMCDNDSimpleKeys.MAX_HP, 0));
        hitPoints.setTemp(hitPointsData.getInt(SpigotMCDNDSimpleKeys.TEMP_HP, 0));
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(MemoryConfiguration inventoryTabData, CoreStats coreStats)
    {
        InventoryTab inventoryTab = new InventoryTab();
        inventoryTab.setInventory(getMemoryConfigurationList(inventoryTabData, SpigotMCDNDSimpleKeys.INVENTORY).stream().map(this::deserializeItem).collect(Collectors.toList()));
        inventoryTab.setInventoryNotes(getStringList(inventoryTabData, SpigotMCDNDSimpleKeys.INVENTORY_NOTES));
        inventoryTab.setWealth(deserializeWealth(getMemoryConfiguration(inventoryTabData, SpigotMCDNDSimpleKeys.WEALTH)));
        inventoryTab.setWeight(deserializeWeight(getMemoryConfiguration(inventoryTabData, SpigotMCDNDSimpleKeys.WEIGHT_CLASS), coreStats, inventoryTab.getInventory(), inventoryTab.getWealth()));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(MemoryConfiguration itemData)
    {
        MCDNDItem item = new MCDNDItem();
        item.setIsCarried(itemData.getBoolean(SpigotMCDNDSimpleKeys.CARRIED));
        item.setWeight(itemData.getDouble(SpigotMCDNDSimpleKeys.WEIGHT_DOUBLE));
        item.setDescription(itemData.getStringList(SpigotMCDNDSimpleKeys.DESCRIPTION));
        item.setName(itemData.getString(SpigotMCDNDSimpleKeys.NAME));
        return item;
    }

    @Override
    protected Wealth deserializeWealth(MemoryConfiguration wealthData)
    {
        Wealth wealth = new Wealth();
        wealth.setCopper(deserializeCoin(getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.COPPER), new Coin("Copper", "CP")));
        wealth.setElectrum(deserializeCoin(getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.ELECTRUM), new Coin("Electrum", "EP")));
        wealth.setGold(deserializeCoin(getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.GOLD), new Coin("Gold", "GP")));
        wealth.setPlatinum(deserializeCoin(getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.PLATINUM), new Coin("Platinum", "PP")));
        wealth.setPlatinum(deserializeCoin(getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.SILVER), new Coin("Silver", "SP")));
        return wealth;
    }

    @Override
    protected Coin deserializeCoin(MemoryConfiguration coinData, Coin defaultCoin)
    {
        Coin coin = new Coin(coinData.getString(SpigotMCDNDSimpleKeys.NAME), coinData.getString(SpigotMCDNDSimpleKeys.SHORT_NAME));
        coin.setAmount(coinData.getInt(SpigotMCDNDSimpleKeys.AMOUNT, 0));
        return coin;
    }

    @Override
    protected Weight deserializeWeight(MemoryConfiguration weightData, CoreStats coreStats, List<MCDNDItem> inventory, Wealth wealth)
    {
        Weight weight = new Weight();
        weight.setOther(weightData.getDouble(SpigotMCDNDSimpleKeys.OTHER, 0));
        weight.setInventoryWeight(inventory);
        weight.setCoinWeight(wealth);
        weight.setCarryingMax(coreStats);
        return weight;
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(MemoryConfiguration spellbookTabData, ClassLevels classLevels)
    {
        SpellbookTab spellbookTab = new SpellbookTab();
        spellbookTab.setSpells(getMemoryConfigurationList(spellbookTabData, SpigotMCDNDSimpleKeys.SPELLS).stream().map(this::deserializeSpell).collect(Collectors.toList()));
        spellbookTab.setSpellcasterClasses(getMemoryConfigurationList(spellbookTabData, SpigotMCDNDSimpleKeys.SPELLCASTER_CLASSES).stream().map(this::deserializeSpellcasterClass).collect(Collectors.toList()));
        return spellbookTab;
    }

    @Override
    protected Spell deserializeSpell(MemoryConfiguration spellData)
    {
        Spell spell = new Spell();
        spell.setNeedsConcentration(spellData.getBoolean(SpigotMCDNDSimpleKeys.NEEDS_CONCENTRATION, false));
        spell.setPrepared(Prepared.valueOf(spellData.getString(SpigotMCDNDSimpleKeys.PREPARED, "NO")));
        spell.setDuration(spellData.getString(SpigotMCDNDSimpleKeys.DURATION, ""));
        spell.setLevel(spellData.getInt(SpigotMCDNDSimpleKeys.SPELL_LEVEL, 0));
        spell.setRange(spellData.getString(SpigotMCDNDSimpleKeys.RANGE, ""));
        spell.setSpellDamage(deserializeSpellDamage(getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_DAMAGE)));
        spell.setDescription(getStringList(spellData, SpigotMCDNDSimpleKeys.SPELL_DESCRIPTION));
        spell.setEffects(getStringList(spellData, SpigotMCDNDSimpleKeys.EFFECTS));
        spell.setSpellSave(deserializeSpellSave(getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_SAVE)));
        spell.setGainedFrom(deserializeSpellcasterClass(getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELLCASTER_CLASS)));
        spell.setSpellType(deserializeSpellType(getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_TYPE)));
        spell.setSpellHealing(deserializeSpellHealing(getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_HEALING)));
        spell.setAttackStat(spellData.getString(SpigotMCDNDSimpleKeys.ATTACK_STAT, ""));
        spell.setCastTime(spellData.getString(SpigotMCDNDSimpleKeys.CAST_TIME, ""));
        spell.setTargetArea(spellData.getString(SpigotMCDNDSimpleKeys.TARGET_AREA, ""));
        return spell;
    }

    @Override
    protected SaveDCType deserializeSaveDCType(MemoryConfiguration spellData)//NOSONAR
    {
        if (containsKey(spellData, SpigotMCDNDSimpleKeys.NAME))
        {
            String name = spellData.getString(SpigotMCDNDSimpleKeys.NAME);
            if ("Custom DC".equals(name))
                return SaveDCTypes.custom(spellData.getInt(SpigotMCDNDSimpleKeys.CUSTOM_DC, 0));
            else if ("Arcane Trickster DC".equals(name))
                return SaveDCTypes.ARCANE_TRICKSTER;
            else if ("Bard DC".equals(name))
                return SaveDCTypes.BARD;
            else if ("Cleric DC".equals(name))
                return SaveDCTypes.CLERIC;
            else if ("Druid DC".equals(name))
                return SaveDCTypes.DRUID;
            else if ("Eldritch Knight DC".equals(name))
                return SaveDCTypes.ELDRITCH_KNIGHT;
            else if ("Paladin DC".equals(name))
                return SaveDCTypes.PALADIN;
            else if ("Sorcerer DC".equals(name))
                return SaveDCTypes.SORCERER;
            else if ("Warlock DC".equals(name))
                return SaveDCTypes.WARLOCK;
            else if ("Wizard DC".equals(name))
                return SaveDCTypes.WIZARD;
        }

        return SaveDCTypes.custom(0);
    }

    @Override
    protected ArmorType deserializeArmorType(MemoryConfiguration armorTypeData)
    {
        if (containsKey(armorTypeData, SpigotMCDNDSimpleKeys.NAME))
            for (ArmorType armorType : ArmorType.values())
                if (armorType.getName().equals(armorTypeData.getString(SpigotMCDNDSimpleKeys.NAME)))
                    return armorType;

        return ArmorType.NONE;
    }

    @Override
    protected SpellDamage deserializeSpellDamage(MemoryConfiguration spellDamageData)
    {
        SpellDamage spellDamage = new SpellDamage();
        spellDamage.setCanCrit(spellDamageData.getBoolean(SpigotMCDNDSimpleKeys.CAN_CRIT, false));
        spellDamage.setDice(deserializeDice(getMemoryConfiguration(spellDamageData, SpigotMCDNDSimpleKeys.DICE)));
        spellDamageData.getInt(SpigotMCDNDSimpleKeys.BONUS, 0);
        spellDamage.setDamageType(spellDamageData.getString(SpigotMCDNDSimpleKeys.DAMAGE_TYPE, ""));
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(MemoryConfiguration spellHealingData)
    {
        SpellHealing spellHealing = new SpellHealing();
        deserializeDice(getMemoryConfiguration(spellHealingData, SpigotMCDNDSimpleKeys.HEAL_AMOUNT));
        spellHealing.setHealAmount(deserializeDice(getMemoryConfiguration(spellHealingData, SpigotMCDNDSimpleKeys.HEAL_AMOUNT)));
        spellHealing.setStatBonus(spellHealingData.getString(SpigotMCDNDSimpleKeys.STAT_BONUS, ""));
        return spellHealing;
    }

    @Override
    protected SpellSave deserializeSpellSave(MemoryConfiguration spellSaveData)
    {
        SpellSave spellSave = new SpellSave();
        spellSave.setSaveDCType(deserializeSpellcasterClass(getMemoryConfiguration(spellSaveData, SpigotMCDNDSimpleKeys.SAVE_DC_TYPE)));

        spellSave.setOnSuccessfulSave(getStringList(spellSaveData, SpigotMCDNDSimpleKeys.SAVE_DC_TYPE));
        spellSave.setSavingStat(spellSaveData.getString(SpigotMCDNDSimpleKeys.SAVING_STAT, ""));
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(MemoryConfiguration spellTypeData)
    {
        if (containsKey(spellTypeData, SpigotMCDNDSimpleKeys.NAME))
            for (SpellType spellType : SpellType.values())
                if (spellType.getName().equals(spellTypeData.getString(SpigotMCDNDSimpleKeys.NAME)))
                    return spellType;

        return SpellType.OTHER;
    }

    @Override
    protected SpellcasterClass deserializeSpellcasterClass(MemoryConfiguration spellcasterClassData)
    {
        if (containsKey(spellcasterClassData, SpigotMCDNDSimpleKeys.NAME))
            for (SpellcasterClass spellcasterClass : SpellcasterClass.values())
                if (spellcasterClassData.getString(SpigotMCDNDSimpleKeys.NAME).equals(spellcasterClass.getName()))
                    return spellcasterClass;

        return SpellcasterClass.OTHER;
    }

    @Override
    protected WeaponsTab deserializeWeaponsTab(MemoryConfiguration weaponsTabData)
    {
        WeaponsTab weaponsTab = new WeaponsTab();
        weaponsTab.setMeleeWeapons(getMemoryConfigurationList(weaponsTabData, SpigotMCDNDSimpleKeys.MELEE_WEAPONS).stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList()));
        weaponsTab.setRangedWeapons(getMemoryConfigurationList(weaponsTabData, SpigotMCDNDSimpleKeys.RANGED_WEAPONS).stream().map(this::deserializeRangedWeapon).collect(Collectors.toList()));
        return weaponsTab;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(MemoryConfiguration meleeWeaponData)
    {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeapon.setPlusStat(meleeWeaponData.getBoolean(SpigotMCDNDSimpleKeys.PLUS_STAT, false));
        meleeWeapon.setIsProficient(meleeWeaponData.getBoolean(SpigotMCDNDSimpleKeys.IS_PROFICIENT, false));
        meleeWeapon.setCritDamageDice(deserializeDice(getMemoryConfiguration(meleeWeaponData, SpigotMCDNDSimpleKeys.CRIT_DAMAGE_DICE)));
        meleeWeapon.setDamageDice(deserializeDice(getMemoryConfiguration(meleeWeaponData, SpigotMCDNDSimpleKeys.DAMAGE_DICE)));
        meleeWeapon.setCritMin(meleeWeaponData.getInt(SpigotMCDNDSimpleKeys.CRIT_MINIMUM, 0));
        meleeWeapon.setMagicBonus(meleeWeaponData.getInt(SpigotMCDNDSimpleKeys.MAGIC_BONUS, 0));
        meleeWeapon.setAttackStat(deserializeWeaponAttackStat(getMemoryConfiguration(meleeWeaponData, SpigotMCDNDSimpleKeys.ATTACK_STAT)));
        meleeWeapon.setName(meleeWeaponData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return meleeWeapon;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(MemoryConfiguration rangedWeaponData)
    {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeapon.setAmmo(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.PLUS_STAT, 0));
        rangedWeapon.setIsProficient(rangedWeaponData.getBoolean(SpigotMCDNDSimpleKeys.IS_PROFICIENT, false));
        rangedWeapon.setCritDamageDice(deserializeDice(getMemoryConfiguration(rangedWeaponData, SpigotMCDNDSimpleKeys.CRIT_DAMAGE_DICE)));
        rangedWeapon.setDamageDice(deserializeDice(getMemoryConfiguration(rangedWeaponData, SpigotMCDNDSimpleKeys.DAMAGE_DICE)));
        rangedWeapon.setCritMin(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.CRIT_MINIMUM, 0));
        rangedWeapon.setMagicBonus(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.MAGIC_BONUS, 0));
        rangedWeapon.setAttackStat(deserializeWeaponAttackStat(getMemoryConfiguration(rangedWeaponData, SpigotMCDNDSimpleKeys.ATTACK_STAT)));
        rangedWeapon.setName(rangedWeaponData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return rangedWeapon;
    }

    @Override
    protected WeaponAttackStat deserializeWeaponAttackStat(MemoryConfiguration weaponAttackStatData) {
        if (containsKey(weaponAttackStatData, SpigotMCDNDSimpleKeys.ATTACK_STAT)) {
            for (WeaponAttackStat weaponAttackStat : WeaponAttackStat.values()) {
                if (weaponAttackStatData.getString(SpigotMCDNDSimpleKeys.NAME).equals(weaponAttackStat.getName())) {
                    return weaponAttackStat;
                }
            }
        }

        return WeaponAttackStat.STR;
    }

    @Override
    protected CharacterSheet deserializePlayerSheet(MemoryConfiguration playerSheetData)
    {
        CharacterSheet characterSheet = new CharacterSheet();
        characterSheet.setArmorTab(deserializeArmorTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.ARMOR_TAB)));
        characterSheet.setBackgroundTab(deserializeBackgroundTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.BACKGROUND_TAB)));
        characterSheet.setClassTab(deserializeClassTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.CLASS_TAB)));
        characterSheet.setCoreStatsTab(deserializeCoreStatsTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.CORE_STATS_TAB)));
        characterSheet.setInventoryTab(deserializeInventoryTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.INVENTORY_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSkillsTab(deserializeSkillsTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.SKILLS_TAB), characterSheet.getCoreStatsTab().getCoreStats()));
        characterSheet.setSpellbookTab(deserializeSpellbookTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.SPELL_BOOK_TAB), characterSheet.getClassTab().getClassLevels()));
        characterSheet.setWeaponsTab(deserializeWeaponsTab(getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.WEAPONS_TAB)));
        return characterSheet;
    }

    private MemoryConfiguration getMemoryConfiguration(MemoryConfiguration memoryConfiguration, String key)//NOSONAR
    {
        return containsKey(memoryConfiguration, key) ? (MemoryConfiguration) memoryConfiguration.getConfigurationSection(key) : new MemoryConfiguration();
    }

    private List<MemoryConfiguration> getMemoryConfigurationList(MemoryConfiguration memoryConfiguration, String key)//NOSONAR
    {
        if (containsKey(memoryConfiguration, key))
        {
            return memoryConfiguration.getMapList(key).stream().map(map ->
            {
                MemoryConfiguration mc = new MemoryConfiguration();
                map.forEach((key1, value) -> mc.set(key1.toString(), value));
                return mc;
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    private List<String> getStringList(MemoryConfiguration memoryConfiguration, String key)//NOSONAR
    {
        return containsKey(memoryConfiguration, key) ? memoryConfiguration.getStringList(key) : new ArrayList<>();
    }

    private boolean containsKey(MemoryConfiguration memoryConfiguration, String key)
    {
        return memoryConfiguration.contains(key);
    }
}
