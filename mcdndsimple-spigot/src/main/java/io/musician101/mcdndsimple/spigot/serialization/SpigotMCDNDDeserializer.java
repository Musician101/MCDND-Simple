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
import io.musician101.mcdndsimple.common.character.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.Weight;
import io.musician101.mcdndsimple.common.character.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.equipment.armor.MCDNDArmorType;
import io.musician101.mcdndsimple.common.character.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.spell.SaveDCType;
import io.musician101.mcdndsimple.common.character.spell.SaveDCTypes;
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
import io.musician101.mcdndsimple.common.serialization.MCDNDDeserializer;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpigotMCDNDDeserializer extends MCDNDDeserializer<MemoryConfiguration>
{
    @Override
    public CharacterSheet deserialize(MemoryConfiguration characterSheetData)
    {
        CharacterSheet characterSheet = new CharacterSheet();
        getMemoryConfiguration(characterSheetData, SpigotMCDNDSimpleKeys.BIO_AND_INFO).ifPresent(bioData ->
                characterSheet.setBioAndInfo(deserializeBioAndInfo(bioData)));
        getMemoryConfiguration(characterSheetData, SpigotMCDNDSimpleKeys.PLAYER_SHEET).ifPresent(playerData ->
                characterSheet.setPlayerSheet(deserializePlayerSheet(playerData)));
        characterSheet.setClazz(characterSheetData.getString(SpigotMCDNDSimpleKeys.CLASS, ""));
        characterSheet.setName(characterSheetData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        characterSheet.setRace(characterSheetData.getString(SpigotMCDNDSimpleKeys.RACE, ""));
        return characterSheet;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(MemoryConfiguration bioAndInfoData)
    {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfo.setName(bioAndInfoData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        getStringList(bioAndInfoData, SpigotMCDNDSimpleKeys.BIO).ifPresent(bioAndInfo::setBio);
        return bioAndInfo;
    }

    @Override
    protected SkillsTab deserializeSkillsTab(MemoryConfiguration skillsTabData, CoreStats coreStats)
    {
        SkillsTab skillsTab = new SkillsTab();
        skillsTab.updateSkills(coreStats);
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.ACROBATICS).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAcrobatics().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.ANIMAL_HANDLING).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAnimalHandling().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.ARCANA).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getArcana().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.ATHLETICS).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAthletics().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.DECEPTION).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getDeception().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.HISTORY).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getHistory().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.INSIGHT).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getInsight().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.INTIMIDATION).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getIntimidation().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.INVESTIGATION).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getInvestigation().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.MEDICINE).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getMedicine().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.NATURE).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getNature().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.PERCEPTION).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPerception().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.PERFORMANCE).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPerformance().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.PERSUASION).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPersuasion().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.RELIGION).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getReligion().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.SLEIGHT_OF_HAND).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getSleightOfHand().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.STEALTH).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getStealth().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.SURVIVAL).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getSurvival().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.UNSKILLED_CHA).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledCHA().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.UNSKILLED_CON).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledCON().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.UNSKILLED_DEX).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledDEX().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.UNSKILLED_INT).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledINT().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.UNSKILLED_STR).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledSTR().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getMemoryConfiguration(skillsTabData, SpigotMCDNDSimpleKeys.UNSKILLED_WIS).ifPresent(skillData ->
                getMemoryConfiguration(skillData, SpigotMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledWIS().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
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
        getMemoryConfigurationList(armorTabData, SpigotMCDNDSimpleKeys.ARMOR_LIST).ifPresent(list ->
                armorTab.setArmor(list.stream().map(this::deserializeArmor).collect(Collectors.toList())));
        getMemoryConfiguration(armorTabData, SpigotMCDNDSimpleKeys.UNARMORED_BONUS).ifPresent(unarmoredBonusData ->
                armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(unarmoredBonusData)));
        return armorTab;
    }

    @Override
    protected Armor deserializeArmor(MemoryConfiguration armorTab)
    {
        Armor armor = new Armor();
        armor.setSpeedPenalty(armorTab.getBoolean(SpigotMCDNDSimpleKeys.SPEED_PENALTY, false));
        armor.setStealthPenalty(armorTab.getBoolean(SpigotMCDNDSimpleKeys.STEALTH_PENALTY, false));
        armor.setUnarmored(armorTab.getBoolean(SpigotMCDNDSimpleKeys.UNARMORED, false));
        armor.setWorn(armorTab.getBoolean(SpigotMCDNDSimpleKeys.WORN, false));
        armor.setBaseArmorClass(armorTab.getInt(SpigotMCDNDSimpleKeys.BASE_ARMOR_CLASS, 0));
        armor.setMagicBonus(armorTab.getInt(SpigotMCDNDSimpleKeys.MAGIC_BONUS, 0));
        getMemoryConfiguration(armorTab, SpigotMCDNDSimpleKeys.ARMOR_TYPE).ifPresent(armorTypeData ->
                armor.setArmorType(deserializeArmorType(armorTypeData)));
        armor.setName(armorTab.getString(SpigotMCDNDSimpleKeys.NAME, ""));
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
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.ARMOR_PROFICIENCIES).ifPresent(backgroundTab::setArmorProficiencies);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.BACKGROUND).ifPresent(backgroundTab::setBackground);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.BONDS).ifPresent(backgroundTab::setBonds);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.FLAWS).ifPresent(backgroundTab::setFlaws);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.IDEALS).ifPresent(backgroundTab::setIdeals);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.OTHER_NOTES).ifPresent(backgroundTab::setOtherNotes);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.PERSONALITY_TRAITS);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.RACIAL_TRAITS).ifPresent(backgroundTab::setRacialTraits);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.TOOL_PROFICIENCIES).ifPresent(backgroundTab::setToolProficiencies);
        getStringList(backgroundTabData, SpigotMCDNDSimpleKeys.WEAPON_PROFICIENCIES).ifPresent(backgroundTab::setWeaponProficiencies);
        backgroundTab.setAlignment(backgroundTabData.getString(SpigotMCDNDSimpleKeys.ALIGNMENT, ""));
        backgroundTab.setEyes(backgroundTabData.getString(SpigotMCDNDSimpleKeys.EYES, ""));
        backgroundTab.setGender(backgroundTabData.getString(SpigotMCDNDSimpleKeys.GENDER, ""));
        backgroundTab.setHair(backgroundTabData.getString(SpigotMCDNDSimpleKeys.HAIR, ""));
        backgroundTab.setHeight(backgroundTabData.getString(SpigotMCDNDSimpleKeys.HEIGHT, ""));
        backgroundTab.setLanguages(backgroundTabData.getString(SpigotMCDNDSimpleKeys.LANGUAGES, ""));
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
        getMemoryConfiguration(classTabData, SpigotMCDNDSimpleKeys.CLASS_LEVELS).ifPresent(data ->
                classTab.setClassLevels(deserializeClassLevels(data)));
        getMemoryConfigurationList(classTabData, SpigotMCDNDSimpleKeys.CLASS_ACTIONS).ifPresent(list ->
                classTab.setClassActions(list.stream().map(this::deserializeClassAction).collect(Collectors.toList())));
        getMemoryConfigurationList(classTabData, SpigotMCDNDSimpleKeys.CLASS_RESOURCES).ifPresent(list ->
                list.forEach(data -> classTab.addClassResource(deserializeClassResource(data))));
        getStringList(classTabData, SpigotMCDNDSimpleKeys.CLASS_FEATURE_NOTES).ifPresent(classTab::setOtherNotes);
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
        classAction.setUsesLeft(classActionData.getInt(SpigotMCDNDSimpleKeys.USES_LEFT, 0));
        getMemoryConfiguration(classActionData, SpigotMCDNDSimpleKeys.RECHARGE).ifPresent(rechargeData ->
                classAction.setRecharge(deserializeRecharge(rechargeData)));
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
        getMemoryConfiguration(classResourceData, SpigotMCDNDSimpleKeys.RECHARGE).ifPresent(rechargeData -> classResource.setRecharge(deserializeRecharge(rechargeData)));
        classResource.setName(classResourceData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return classResource;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(MemoryConfiguration coreStatsTabData)
    {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.BONUSES).ifPresent(data ->
                coreStatsTab.setBonuses(deserializeBonuses(data)));
        getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.CORE_STATS).ifPresent(data ->
                coreStatsTab.setCoreStats(deserializeCoreStats(data)));
        getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.EXPERIENCE).ifPresent(data ->
                coreStatsTab.setExperience(deserializeExperience(data)));
        getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.HIT_DICE).ifPresent(data ->
                coreStatsTab.setHitDice(deserializeHitDice(data)));
        getMemoryConfiguration(coreStatsTabData, SpigotMCDNDSimpleKeys.HIT_POINTS).ifPresent(data ->
                coreStatsTab.setHitPoints(deserializeHitPoints(data)));
        coreStatsTab.setInitiativeBonus(coreStatsTabData.getInt(SpigotMCDNDSimpleKeys.INITIATIVE_BONUS, 0));
        coreStatsTab.setSpeed(coreStatsTabData.getInt(SpigotMCDNDSimpleKeys.SPEED, 0));
        return coreStatsTab;
    }

    @Override
    protected Bonuses deserializeBonuses(MemoryConfiguration bonusesData)
    {
        Bonuses bonuses = new Bonuses();
        getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.MELEE_BONUS).ifPresent(meleeBonusData ->
                bonuses.setMelee(deserializeMeleeBonus(meleeBonusData)));
        getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.RANGED_BONUS).ifPresent(rangedBonusData ->
                bonuses.setRanged(deserializeRangedBonus(rangedBonusData)));
        getMemoryConfiguration(bonusesData, SpigotMCDNDSimpleKeys.SPELLCASTING_BONUS).ifPresent(spellcastingBonusData ->
                bonuses.setSpellcasting(deserializeSpellcastingBonus(spellcastingBonusData)));
        bonuses.setSaves(bonusesData.getInt(SpigotMCDNDSimpleKeys.SAVES, 0));
        bonuses.setAbilitiesAndSkills(bonusesData.getInt(SpigotMCDNDSimpleKeys.ABILITIES_AND_SKILLS, 0));
        return bonuses;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(MemoryConfiguration meleeBonusData)
    {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonus.setAttack(meleeBonusData.getInt(SpigotMCDNDSimpleKeys.ATTACK, 0));
        meleeBonus.setDamage(meleeBonusData.getInt(SpigotMCDNDSimpleKeys.DAMAGE, 0));
        return meleeBonus;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(MemoryConfiguration rangedBonusData)
    {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonus.setAttack(rangedBonusData.getInt(SpigotMCDNDSimpleKeys.ATTACK, 0));
        rangedBonus.setDamage(rangedBonusData.getInt(SpigotMCDNDSimpleKeys.DAMAGE, 0));
        return rangedBonus;
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(MemoryConfiguration spellcastingBonusData)
    {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonus.setAttack(spellcastingBonusData.getInt(SpigotMCDNDSimpleKeys.ATTACK, 0));
        spellcastingBonus.setDamage(spellcastingBonusData.getInt(SpigotMCDNDSimpleKeys.DAMAGE, 0));
        spellcastingBonus.setSaveDC(spellcastingBonusData.getInt(SpigotMCDNDSimpleKeys.SAVE_DC, 0));
        return spellcastingBonus;
    }

    @Override
    protected CoreStats deserializeCoreStats(MemoryConfiguration coreStatsData)
    {
        CoreStats coreStats = new CoreStats();
        getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.CHARISMA_SCORE).ifPresent(data ->
                coreStats.setCharisma(deserializeAbilityScore(data, new AbilityScore("Charisma", "CHA"))));
        getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.CONSTITUTION_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Constitution", "CON"))));
        getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.DEXTERITY_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Dexterity", "DEX"))));
        getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.INTELLIGENCE_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Intelligence", "INT"))));
        getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.STRENGTH_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Strength", "STR"))));
        getMemoryConfiguration(coreStatsData, SpigotMCDNDSimpleKeys.WISDOM_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Wisdom", "WIS"))));
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
        experience.setOverallLevel(experienceData.getInt(SpigotMCDNDSimpleKeys.OVERALL_LEVEL, 0));
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
    protected InventoryTab deserializeInventoryTab(MemoryConfiguration inventoryTabData, int strengthScore)
    {
        InventoryTab inventoryTab = new InventoryTab();
        getMemoryConfigurationList(inventoryTabData, SpigotMCDNDSimpleKeys.INVENTORY).ifPresent(list ->
                inventoryTab.setInventory(list.stream().map(this::deserializeItem).collect(Collectors.toList())));
        getStringList(inventoryTabData, SpigotMCDNDSimpleKeys.INVENTORY_NOTES).ifPresent(inventoryTab::setInventoryNotes);
        getMemoryConfiguration(inventoryTabData, SpigotMCDNDSimpleKeys.WEALTH).ifPresent(data ->
                inventoryTab.setWealth(deserializeWealth(data)));
        getMemoryConfiguration(inventoryTabData, SpigotMCDNDSimpleKeys.WEIGHT_CLASS).ifPresent(data ->
                inventoryTab.setWeight(deserializeWeight(data, strengthScore, inventoryTab.getInventory(), inventoryTab.getWealth())));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(MemoryConfiguration itemData)
    {
        MCDNDItem item = new MCDNDItem();
        item.setCarried(itemData.getBoolean(SpigotMCDNDSimpleKeys.CARRIED));
        item.setWeight(itemData.getDouble(SpigotMCDNDSimpleKeys.WEIGHT_DOUBLE));
        item.setDescription(itemData.getString(SpigotMCDNDSimpleKeys.DESCRIPTION));
        item.setName(itemData.getString(SpigotMCDNDSimpleKeys.NAME));
        return item;
    }

    @Override
    protected Wealth deserializeWealth(MemoryConfiguration wealthData)
    {
        Wealth wealth = new Wealth();
        getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.COPPER).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Copper", "CP"))));
        getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.ELECTRUM).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Electrum", "EP"))));
        getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.GOLD).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Gold", "GP"))));
        getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.PLATINUM).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Platinum", "PP"))));
        getMemoryConfiguration(wealthData, SpigotMCDNDSimpleKeys.SILVER).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Silver", "SP"))));
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
    protected Weight deserializeWeight(MemoryConfiguration weightData, int strengthScore, List<MCDNDItem> inventory, Wealth wealth)
    {
        Weight weight = new Weight();
        weight.setOther(weightData.getDouble(SpigotMCDNDSimpleKeys.OTHER, 0));
        weight.setInventoryWeight(inventory);
        weight.setCoinWeight(wealth);
        weight.setCarryingMax(strengthScore);
        weight.setEncumbered(strengthScore);
        weight.setHeavilyEncumbered(strengthScore);
        return weight;
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(MemoryConfiguration spellbookTabData, int sorcererLevel)
    {
        SpellbookTab spellbookTab = new SpellbookTab();
        getMemoryConfigurationList(spellbookTabData, SpigotMCDNDSimpleKeys.SPELLS).ifPresent(list ->
                spellbookTab.setSpells(list.stream().map(this::deserializeSpell).collect(Collectors.toList())));
        getMemoryConfigurationList(spellbookTabData, SpigotMCDNDSimpleKeys.SPELLCASTER_CLASSES).ifPresent(list ->
                spellbookTab.setSpells(list.stream().map(this::deserializeSpell).collect(Collectors.toList())));
        spellbookTab.setInvocations(sorcererLevel);
        spellbookTab.setSorceryPoints(sorcererLevel);
        return spellbookTab;
    }

    @Override
    protected Spell deserializeSpell(MemoryConfiguration spellData)
    {
        Spell spell = new Spell();
        spell.setNeedsConcentration(spellData.getBoolean(SpigotMCDNDSimpleKeys.NEEDS_CONCENTRATION, false));
        spell.setPrepared(spellData.getBoolean(SpigotMCDNDSimpleKeys.IS_PREPARED, false));
        spell.setDuration(spellData.getInt(SpigotMCDNDSimpleKeys.DURATION, 0));
        spell.setLevel(spellData.getInt(SpigotMCDNDSimpleKeys.SPELL_LEVEL, 0));
        spell.setRange(spellData.getInt(SpigotMCDNDSimpleKeys.RANGE, 0));
        getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_DAMAGE).ifPresent(data ->
                spell.setSpellDamage(deserializeSpellDamage(data)));
        getStringList(spellData, SpigotMCDNDSimpleKeys.SPELL_DESCRIPTION).ifPresent(spell::setDescription);
        getStringList(spellData, SpigotMCDNDSimpleKeys.EFFECTS).ifPresent(spell::setEffects);
        getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_SAVE).ifPresent(data ->
                spell.setSpellSave(deserializeSpellSave(data)));
        getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELLCASTER_CLASS).ifPresent(data ->
                spell.setGainedFrom(deserializeSpellcasterClass(data)));
        getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_TYPE).ifPresent(data ->
                spell.setSpellType(deserializeSpellType(data)));
        getMemoryConfiguration(spellData, SpigotMCDNDSimpleKeys.SPELL_HEALING).ifPresent(data ->
                spell.setSpellHealing(deserializeSpellHealing(data)));
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
    protected MCDNDArmorType deserializeArmorType(MemoryConfiguration armorTypeData)
    {
        if (containsKey(armorTypeData, SpigotMCDNDSimpleKeys.NAME))
            for (MCDNDArmorType armorType : MCDNDArmorType.values())
                if (armorType.getName().equals(armorTypeData.getString(SpigotMCDNDSimpleKeys.NAME)))
                    return armorType;

        return MCDNDArmorType.NONE;
    }

    @Override
    protected SpellDamage deserializeSpellDamage(MemoryConfiguration spellDamageData)
    {
        SpellDamage spellDamage = new SpellDamage();
        spellDamage.setCanCrit(spellDamageData.getBoolean(SpigotMCDNDSimpleKeys.CAN_CRIT, false));
        getMemoryConfiguration(spellDamageData, SpigotMCDNDSimpleKeys.DICE).ifPresent(data ->
                spellDamage.setDice(deserializeDice(data)));
        spellDamageData.getInt(SpigotMCDNDSimpleKeys.BONUS, 0);
        spellDamage.setDamageType(spellDamageData.getString(SpigotMCDNDSimpleKeys.DAMAGE_TYPE, ""));
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(MemoryConfiguration spellHealingData)
    {
        SpellHealing spellHealing = new SpellHealing();
        spellHealing.setHealAmount(spellHealingData.getInt(SpigotMCDNDSimpleKeys.HEAL_AMOUNT, 0));
        spellHealing.setStatBonus(spellHealingData.getString(SpigotMCDNDSimpleKeys.STAT_BONUS, ""));
        return spellHealing;
    }

    @Override
    protected SpellSave deserializeSpellSave(MemoryConfiguration spellSaveData)
    {
        SpellSave spellSave = new SpellSave();
        getMemoryConfiguration(spellSaveData, SpigotMCDNDSimpleKeys.SAVE_DC_TYPE).ifPresent(data ->
                spellSave.setSaveDCType(deserializeSaveDCType(data)));
        spellSave.setOnSuccessfulSave(spellSaveData.getString(SpigotMCDNDSimpleKeys.SAVE_DC_TYPE, ""));
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
        getMemoryConfigurationList(weaponsTabData, SpigotMCDNDSimpleKeys.MELEE_WEAPONS).ifPresent(list ->
                weaponsTab.setMeleeWeapons(list.stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList())));
        getMemoryConfigurationList(weaponsTabData, SpigotMCDNDSimpleKeys.RANGED_WEAPONS).ifPresent(list ->
                weaponsTab.setRangedWeapons(list.stream().map(this::deserializeRangedWeapon).collect(Collectors.toList())));
        return weaponsTab;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(MemoryConfiguration meleeWeaponData)
    {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeapon.setPlusStat(meleeWeaponData.getBoolean(SpigotMCDNDSimpleKeys.PLUS_STAT, false));
        meleeWeapon.setProficient(meleeWeaponData.getBoolean(SpigotMCDNDSimpleKeys.IS_PROFICIENT, false));
        getMemoryConfiguration(meleeWeaponData, SpigotMCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data ->
                meleeWeapon.setCritDamageDice(deserializeDice(data)));
        getMemoryConfiguration(meleeWeaponData, SpigotMCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data ->
                meleeWeapon.setDamageDice(deserializeDice(data)));
        meleeWeapon.setCritMin(meleeWeaponData.getInt(SpigotMCDNDSimpleKeys.CRIT_MINIMUM, 0));
        meleeWeapon.setDamageBonus(meleeWeaponData.getInt(SpigotMCDNDSimpleKeys.DAMAGE_BONUS, 0));
        meleeWeapon.setMagicBonus(meleeWeaponData.getInt(SpigotMCDNDSimpleKeys.MAGIC_BONUS, 0));
        meleeWeapon.setToHit(meleeWeaponData.getInt(SpigotMCDNDSimpleKeys.TO_HIT, 0));
        meleeWeapon.setAttackStat(meleeWeaponData.getString(SpigotMCDNDSimpleKeys.ATTACK_STAT, ""));
        meleeWeapon.setName(meleeWeaponData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return meleeWeapon;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(MemoryConfiguration rangedWeaponData)
    {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeapon.setAmmo(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.PLUS_STAT, 0));
        rangedWeapon.setProficient(rangedWeaponData.getBoolean(SpigotMCDNDSimpleKeys.IS_PROFICIENT, false));
        getMemoryConfiguration(rangedWeaponData, SpigotMCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data ->
                rangedWeapon.setCritDamageDice(deserializeDice(data)));
        getMemoryConfiguration(rangedWeaponData, SpigotMCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data ->
                rangedWeapon.setDamageDice(deserializeDice(data)));
        rangedWeapon.setCritMin(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.CRIT_MINIMUM, 0));
        rangedWeapon.setDamageBonus(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.DAMAGE_BONUS, 0));
        rangedWeapon.setMagicBonus(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.MAGIC_BONUS, 0));
        rangedWeapon.setToHit(rangedWeaponData.getInt(SpigotMCDNDSimpleKeys.TO_HIT, 0));
        rangedWeapon.setAttackStat(rangedWeaponData.getString(SpigotMCDNDSimpleKeys.ATTACK_STAT, ""));
        rangedWeapon.setName(rangedWeaponData.getString(SpigotMCDNDSimpleKeys.NAME, ""));
        return rangedWeapon;
    }

    @Override
    protected PlayerSheet deserializePlayerSheet(MemoryConfiguration playerSheetData)
    {
        PlayerSheet playerSheet = new PlayerSheet();
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.ARMOR_TAB).ifPresent(armorTabData ->
                playerSheet.setArmorTab(deserializeArmorTab(armorTabData)));
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.BACKGROUND_TAB).ifPresent(backgroundTabData ->
                playerSheet.setBackgroundTab(deserializeBackgroundTab(backgroundTabData)));
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.CLASS_TAB).ifPresent(classTabData ->
                playerSheet.setClassTab(deserializeClassTab(classTabData)));
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.CORE_STATS_TAB).ifPresent(data ->
                playerSheet.setCoreStatsTab(deserializeCoreStatsTab(data)));
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.INVENTORY_TAB).ifPresent(data ->
                playerSheet.setInventoryTab(deserializeInventoryTab(data, playerSheet.getCoreStatsTab().getCoreStats().getStrength().getScore())));
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.SKILLS_TAB).ifPresent(data ->
                playerSheet.setSkillsTab(deserializeSkillsTab(data, playerSheet.getCoreStatsTab().getCoreStats())));
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.SPELL_BOOK_TAB).ifPresent(data ->
                playerSheet.setSpellbookTab(deserializeSpellbookTab(data, playerSheet.getClassTab().getClassLevels().getSorcerer())));
        getMemoryConfiguration(playerSheetData, SpigotMCDNDSimpleKeys.WEAPONS_TAB).ifPresent(data ->
                playerSheet.setWeaponsTab(deserializeWeaponsTab(data)));
        return playerSheet;
    }

    private Optional<MemoryConfiguration> getMemoryConfiguration(MemoryConfiguration memoryConfiguration, String key)//NOSONAR
    {
        return containsKey(memoryConfiguration, key) ? Optional.of((MemoryConfiguration) memoryConfiguration.getConfigurationSection(key)) : Optional.empty();
    }

    private Optional<List<MemoryConfiguration>> getMemoryConfigurationList(MemoryConfiguration memoryConfiguration, String key)//NOSONAR
    {
        if (containsKey(memoryConfiguration, key))
        {
            return Optional.of(memoryConfiguration.getMapList(key).stream().map(map ->
            {
                MemoryConfiguration mc = new MemoryConfiguration();
                map.entrySet().forEach(entry -> mc.set(entry.getKey().toString(), entry.getValue()));
                return mc;
            }).collect(Collectors.toList()));
        }

        return Optional.empty();
    }

    private Optional<List<String>> getStringList(MemoryConfiguration memoryConfiguration, String key)//NOSONAR
    {
        return containsKey(memoryConfiguration, key) ? Optional.of(memoryConfiguration.getStringList(key)) : Optional.empty();
    }

    private boolean containsKey(MemoryConfiguration memoryConfiguration, String key)
    {
        return memoryConfiguration.contains(key);
    }
}
