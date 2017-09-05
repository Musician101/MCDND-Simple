package io.musician101.mcdndsimple.sponge.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.CharacterSheet;
import io.musician101.mcdndsimple.common.character.PlayerSheet;
import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.ClassResource;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.HitDice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.MCDNDItem;
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
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpongeMCDNDDeserializer extends MCDNDDeserializer<DataContainer>
{
    @Override
    public PlayerSheet deserialize(DataContainer characterSheetData)
    {
        PlayerSheet playerSheet = new PlayerSheet();
        getDataContainer(characterSheetData, SpongeMCDNDSimpleKeys.BIO_AND_INFO).ifPresent(bioData ->
                playerSheet.setBioAndInfo(deserializeBioAndInfo(bioData)));
        getDataContainer(characterSheetData, SpongeMCDNDSimpleKeys.PLAYER_SHEET).ifPresent(playerData ->
                playerSheet.setCharacterSheet(deserializePlayerSheet(playerData)));
        characterSheetData.getString(SpongeMCDNDSimpleKeys.CLASS.getQuery()).ifPresent(playerSheet::setClazz);
        characterSheetData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(playerSheet::setName);
        characterSheetData.getString(SpongeMCDNDSimpleKeys.RACE.getQuery()).ifPresent(playerSheet::setRace);
        return playerSheet;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(DataContainer bioAndInfoData)
    {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfoData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(bioAndInfo::setName);
        bioAndInfoData.getStringList(SpongeMCDNDSimpleKeys.BIO.getQuery()).ifPresent(bioAndInfo::setBio);
        return bioAndInfo;
    }

    @Override
    protected SkillsTab deserializeSkillsTab(DataContainer skillsTabData, CoreStats coreStats)
    {
        SkillsTab skillsTab = new SkillsTab();
        skillsTab.updateSkills(coreStats);
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.ACROBATICS).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAcrobatics().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.ANIMAL_HANDLING).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAnimalHandling().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.ARCANA).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getArcana().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.ATHLETICS).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAthletics().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.DECEPTION).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getDeception().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.HISTORY).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getHistory().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.INSIGHT).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getInsight().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.INTIMIDATION).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getIntimidation().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.INVESTIGATION).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getInvestigation().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.MEDICINE).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getMedicine().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.NATURE).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getNature().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.PERCEPTION).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPerception().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.PERFORMANCE).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPerformance().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.PERSUASION).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPersuasion().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.RELIGION).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getReligion().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.SLEIGHT_OF_HAND).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getSleightOfHand().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.STEALTH).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getStealth().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.SURVIVAL).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getSurvival().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.UNSKILLED_CHA).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledCHA().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.UNSKILLED_CON).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledCON().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.UNSKILLED_DEX).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledDEX().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.UNSKILLED_INT).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledINT().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.UNSKILLED_STR).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledSTR().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, SpongeMCDNDSimpleKeys.UNSKILLED_WIS).ifPresent(skillData ->
                getDataContainer(skillData, SpongeMCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledWIS().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        return skillsTab;
    }

    @Override
    protected SkillProficiency deserializeSkillProficiency(DataContainer skillsProficiencyData)
    {
        return skillsProficiencyData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).map(name ->
        {
            for (SkillProficiency skillProficiency : SkillProficiency.values())
                if (name.equals(skillProficiency.getName()))
                    return skillProficiency;

            return SkillProficiency.NONE;
        }).orElse(SkillProficiency.NONE);
    }

    @Override
    protected ArmorTab deserializeArmorTab(DataContainer armorTabData)
    {
        ArmorTab armorTab = new ArmorTab();
        armorTabData.getInt(SpongeMCDNDSimpleKeys.ARMOR_CLASS.getQuery()).ifPresent(armorTab::setArmorClass);
        armorTabData.getInt(SpongeMCDNDSimpleKeys.UNARMORED_ARMOR_CLASS.getQuery()).ifPresent(armorTab::setUnarmoredClass);
        getDataContainerList(armorTabData, SpongeMCDNDSimpleKeys.ARMOR_LIST).ifPresent(list ->
                armorTab.setArmor(list.stream().map(this::deserializeArmor).collect(Collectors.toList())));
        getDataContainer(armorTabData, SpongeMCDNDSimpleKeys.UNARMORED_BONUS).ifPresent(unarmoredBonusData ->
                armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(unarmoredBonusData)));
        return armorTab;
    }

    @Override
    protected Armor deserializeArmor(DataContainer armorTab)
    {
        Armor armor = new Armor();
        armorTab.getBoolean(SpongeMCDNDSimpleKeys.SPEED_PENALTY.getQuery()).ifPresent(armor::setSpeedPenalty);
        armorTab.getBoolean(SpongeMCDNDSimpleKeys.STEALTH_PENALTY.getQuery()).ifPresent(armor::setStealthPenalty);
        armorTab.getBoolean(SpongeMCDNDSimpleKeys.UNARMORED.getQuery()).ifPresent(armor::setIsUnarmored);
        armorTab.getBoolean(SpongeMCDNDSimpleKeys.WORN.getQuery()).ifPresent(armor::setIsWorn);
        armorTab.getInt(SpongeMCDNDSimpleKeys.BASE_ARMOR_CLASS.getQuery()).ifPresent(armor::setBaseArmorClass);
        armorTab.getInt(SpongeMCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(armor::setMagicBonus);
        getDataContainer(armorTab, SpongeMCDNDSimpleKeys.ARMOR_TYPE).ifPresent(armorTypeData ->
                armor.setArmorType(deserializeArmorType(armorTypeData)));
        armorTab.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(armor::setName);
        return armor;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(DataContainer unarmoredBonusData)
    {
        return unarmoredBonusData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
        {
            for (UnarmoredBonus unarmoredBonus : UnarmoredBonus.values())
                if (unarmoredBonus.getName().equals(name))
                    return Optional.of(unarmoredBonus);

            return Optional.empty();
        }).orElse(UnarmoredBonus.NONE);
    }

    @Override
    protected BackgroundTab deserializeBackgroundTab(DataContainer backgroundTabData)
    {
        BackgroundTab backgroundTab = new BackgroundTab();
        backgroundTabData.getDouble(SpongeMCDNDSimpleKeys.WEIGHT_DOUBLE.getQuery()).ifPresent(backgroundTab::setWeight);
        backgroundTabData.getInt(SpongeMCDNDSimpleKeys.AGE.getQuery()).ifPresent(backgroundTab::setAge);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.ARMOR_PROFICIENCIES.getQuery()).ifPresent(backgroundTab::setArmorProficiencies);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.BACKGROUND.getQuery()).ifPresent(backgroundTab::setBackground);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.BONDS.getQuery()).ifPresent(backgroundTab::setBonds);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.FLAWS.getQuery()).ifPresent(backgroundTab::setFlaws);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.IDEALS.getQuery()).ifPresent(backgroundTab::setIdeals);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.OTHER_NOTES.getQuery()).ifPresent(backgroundTab::setOtherNotes);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.PERSONALITY_TRAITS.getQuery());
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.RACIAL_TRAITS.getQuery()).ifPresent(backgroundTab::setRacialTraits);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.TOOL_PROFICIENCIES.getQuery()).ifPresent(backgroundTab::setToolProficiencies);
        backgroundTabData.getStringList(SpongeMCDNDSimpleKeys.WEAPON_PROFICIENCIES.getQuery()).ifPresent(backgroundTab::setWeaponProficiencies);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.ALIGNMENT.getQuery()).ifPresent(backgroundTab::setAlignment);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.EYES.getQuery()).ifPresent(backgroundTab::setEyes);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.GENDER.getQuery()).ifPresent(backgroundTab::setGender);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.HAIR.getQuery()).ifPresent(backgroundTab::setHair);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.HEIGHT.getQuery()).ifPresent(backgroundTab::setHeight);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.LANGUAGES.getQuery()).ifPresent(backgroundTab::setLanguages);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.SIZE.getQuery()).ifPresent(backgroundTab::setSize);
        backgroundTabData.getString(SpongeMCDNDSimpleKeys.VISION.getQuery()).ifPresent(backgroundTab::setVision);
        return backgroundTab;
    }

    @Override
    protected Recharge deserializeRecharge(DataContainer rechargeData)
    {
        return rechargeData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
        {
            for (Recharge recharge : Recharge.values())
                if (recharge.getName().equals(name))
                    return Optional.of(recharge);


            return Optional.empty();
        }).orElse(Recharge.NONE);
    }

    @Override
    protected ClassTab deserializeClassTab(DataContainer classTabData)
    {
        ClassTab classTab = new ClassTab();
        getDataContainer(classTabData, SpongeMCDNDSimpleKeys.CLASS_LEVELS).ifPresent(data ->
                classTab.setClassLevels(deserializeClassLevels(data)));
        getDataContainerList(classTabData, SpongeMCDNDSimpleKeys.CLASS_ACTIONS).ifPresent(list ->
                classTab.setClassActions(list.stream().map(this::deserializeClassAction).collect(Collectors.toList())));
        getDataContainerList(classTabData, SpongeMCDNDSimpleKeys.CLASS_RESOURCES).ifPresent(list ->
                list.forEach(data -> classTab.addClassResource(deserializeClassResource(data))));
        classTabData.getStringList(SpongeMCDNDSimpleKeys.CLASS_FEATURE_NOTES.getQuery()).ifPresent(classTab::setOtherNotes);
        return classTab;
    }

    @Override
    protected ClassLevels deserializeClassLevels(DataContainer classLevelsData)
    {
        ClassLevels classLevels = new ClassLevels();
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.BARBARIAN_LEVEL.getQuery()).ifPresent(classLevels::setBarbarian);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.BARD_LEVEL.getQuery()).ifPresent(classLevels::setBard);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.CLERIC_LEVEL.getQuery()).ifPresent(classLevels::setCleric);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.DRUID_LEVEL.getQuery()).ifPresent(classLevels::setDruid);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.FIGHTER_LEVEL.getQuery()).ifPresent(classLevels::setFighter);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.MONK_LEVEL.getQuery()).ifPresent(classLevels::setMonk);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.PALADIN_LEVEL.getQuery()).ifPresent(classLevels::setPaladin);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.RANGER_LEVEL.getQuery()).ifPresent(classLevels::setRanger);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.ROGUE_LEVEL.getQuery()).ifPresent(classLevels::setRogue);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.SORCERER_LEVEL.getQuery()).ifPresent(classLevels::setSorcerer);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.WARLOCK_LEVEL.getQuery()).ifPresent(classLevels::setWarlock);
        classLevelsData.getInt(SpongeMCDNDSimpleKeys.WIZARD_LEVEL.getQuery()).ifPresent(classLevels::setWizard);
        return classLevels;
    }

    @Override
    protected ClassAction deserializeClassAction(DataContainer classActionData)
    {
        ClassAction classAction = new ClassAction();
        classActionData.getInt(SpongeMCDNDSimpleKeys.MAX_USES.getQuery()).ifPresent(classAction::setMax);
        classActionData.getInt(SpongeMCDNDSimpleKeys.USES_LEFT.getQuery()).ifPresent(classAction::setUsedCharges);
        getDataContainer(classActionData, SpongeMCDNDSimpleKeys.RECHARGE).ifPresent(rechargeData ->
                classAction.setRecharge(deserializeRecharge(rechargeData)));
        classActionData.getString(SpongeMCDNDSimpleKeys.GAINED_FROM.getQuery()).ifPresent(classAction::setGainedFrom);
        classActionData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(classAction::setName);
        return classAction;
    }

    @Override
    protected ClassResource deserializeClassResource(DataContainer classResourceData)
    {
        ClassResource classResource = new ClassResource();
        classResourceData.getInt(SpongeMCDNDSimpleKeys.USES_LEFT.getQuery()).ifPresent(classResource::setCurrentCharges);
        classResourceData.getInt(SpongeMCDNDSimpleKeys.MAX_USES.getQuery()).ifPresent(classResource::setMaxCharges);
        getDataContainer(classResourceData, SpongeMCDNDSimpleKeys.RECHARGE).ifPresent(rechargeData -> classResource.setRecharge(deserializeRecharge(rechargeData)));
        classResourceData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(classResource::setName);
        return classResource;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(DataContainer coreStatsTabData)
    {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        getDataContainer(coreStatsTabData, SpongeMCDNDSimpleKeys.BONUSES).ifPresent(data ->
                coreStatsTab.setBonuses(deserializeBonuses(data)));
        getDataContainer(coreStatsTabData, SpongeMCDNDSimpleKeys.CORE_STATS).ifPresent(data ->
                coreStatsTab.setCoreStats(deserializeCoreStats(data)));
        getDataContainer(coreStatsTabData, SpongeMCDNDSimpleKeys.EXPERIENCE).ifPresent(data ->
                coreStatsTab.setExperience(deserializeExperience(data)));
        getDataContainer(coreStatsTabData, SpongeMCDNDSimpleKeys.HIT_DICE).ifPresent(data ->
                coreStatsTab.setHitDice(deserializeHitDice(data)));
        getDataContainer(coreStatsTabData, SpongeMCDNDSimpleKeys.HIT_POINTS).ifPresent(data ->
                coreStatsTab.setHitPoints(deserializeHitPoints(data)));
        coreStatsTabData.getInt(SpongeMCDNDSimpleKeys.INITIATIVE_BONUS.getQuery()).ifPresent(coreStatsTab::setInitiativeBonus);
        coreStatsTabData.getInt(SpongeMCDNDSimpleKeys.SPEED.getQuery()).ifPresent(coreStatsTab::setSpeed);
        return coreStatsTab;
    }

    @Override
    protected Bonuses deserializeBonuses(DataContainer bonusesData)
    {
        Bonuses bonuses = new Bonuses();
        getDataContainer(bonusesData, SpongeMCDNDSimpleKeys.MELEE_BONUS).ifPresent(meleeBonusData ->
                bonuses.setMelee(deserializeMeleeBonus(meleeBonusData)));
        getDataContainer(bonusesData, SpongeMCDNDSimpleKeys.RANGED_BONUS).ifPresent(rangedBonusData ->
                bonuses.setRanged(deserializeRangedBonus(rangedBonusData)));
        getDataContainer(bonusesData, SpongeMCDNDSimpleKeys.SPELLCASTING_BONUS).ifPresent(spellcastingBonusData ->
                bonuses.setSpellcasting(deserializeSpellcastingBonus(spellcastingBonusData)));
        bonusesData.getInt(SpongeMCDNDSimpleKeys.SAVES.getQuery()).ifPresent(bonuses::setSaves);
        bonusesData.getInt(SpongeMCDNDSimpleKeys.ABILITIES_AND_SKILLS.getQuery()).ifPresent(bonuses::setAbilitiesAndSkills);
        return bonuses;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(DataContainer meleeBonusData)
    {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonusData.getInt(SpongeMCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(meleeBonus::setAttack);
        meleeBonusData.getInt(SpongeMCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(meleeBonus::setDamage);
        return meleeBonus;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(DataContainer rangedBonusData)
    {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonusData.getInt(SpongeMCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(rangedBonus::setAttack);
        rangedBonusData.getInt(SpongeMCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(rangedBonus::setDamage);
        return rangedBonus;
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(DataContainer spellcastingBonusData)
    {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonusData.getInt(SpongeMCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(spellcastingBonus::setAttack);
        spellcastingBonusData.getInt(SpongeMCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(spellcastingBonus::setDamage);
        spellcastingBonusData.getInt(SpongeMCDNDSimpleKeys.SAVE_DC.getQuery()).ifPresent(spellcastingBonus::setSaveDC);
        return spellcastingBonus;
    }

    @Override
    protected CoreStats deserializeCoreStats(DataContainer coreStatsData)
    {
        CoreStats coreStats = new CoreStats();
        getDataContainer(coreStatsData, SpongeMCDNDSimpleKeys.CHARISMA_SCORE).ifPresent(data ->
                coreStats.setCharisma(deserializeAbilityScore(data, new AbilityScore("Charisma", "CHA"))));
        getDataContainer(coreStatsData, SpongeMCDNDSimpleKeys.CONSTITUTION_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Constitution", "CON"))));
        getDataContainer(coreStatsData, SpongeMCDNDSimpleKeys.DEXTERITY_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Dexterity", "DEX"))));
        getDataContainer(coreStatsData, SpongeMCDNDSimpleKeys.INTELLIGENCE_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Intelligence", "INT"))));
        getDataContainer(coreStatsData, SpongeMCDNDSimpleKeys.STRENGTH_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Strength", "STR"))));
        getDataContainer(coreStatsData, SpongeMCDNDSimpleKeys.WISDOM_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Wisdom", "WIS"))));
        return coreStats;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(DataContainer abilityScoreData, AbilityScore defaultScore)
    {
        return abilityScoreData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
                abilityScoreData.getString(SpongeMCDNDSimpleKeys.SHORT_NAME.getQuery()).flatMap(shortName ->
                {
                    AbilityScore abilityScore = new AbilityScore(name, shortName);
                    abilityScoreData.getBoolean(SpongeMCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(abilityScore::setProficient);
                    abilityScoreData.getInt(SpongeMCDNDSimpleKeys.SCORE.getQuery()).ifPresent(abilityScore::setScore);
                    return Optional.of(abilityScore);
                })).orElse(defaultScore);
    }

    @Override
    protected Experience deserializeExperience(DataContainer experienceData)
    {
        Experience experience = new Experience();
        experienceData.getInt(SpongeMCDNDSimpleKeys.EXPERIENCE.getQuery()).ifPresent(experience::setExp);
        experienceData.getInt(SpongeMCDNDSimpleKeys.OVERALL_LEVEL.getQuery()).ifPresent(experience::setOverallLevel);
        return experience;
    }

    @Override
    protected HitDice deserializeHitDice(DataContainer hitDiceData)
    {
        HitDice hitDice = new HitDice();
        hitDiceData.getKeys(false).forEach(dataQuery ->
                hitDiceData.getInt(dataQuery).ifPresent(amount ->
                        hitDice.updateHitDie(Integer.parseInt(dataQuery.toString()), amount)));
        return hitDice;
    }

    @Override
    protected Dice deserializeDice(DataContainer diceData)
    {
        return diceData.getInt(SpongeMCDNDSimpleKeys.SIDES.getQuery()).map(sides ->
                diceData.getInt(SpongeMCDNDSimpleKeys.AMOUNT.getQuery()).map(amount ->
                        new Dice(amount, sides)).orElse(new Dice(sides))).orElse(new Dice(6));
    }

    @Override
    protected HitPoints deserializeHitPoints(DataContainer hitPointsData)
    {
        HitPoints hitPoints = new HitPoints();
        hitPointsData.getInt(SpongeMCDNDSimpleKeys.CURRENT_HP.getQuery()).ifPresent(hitPoints::setCurrent);
        hitPointsData.getInt(SpongeMCDNDSimpleKeys.MAX_HP.getQuery()).ifPresent(hitPoints::setMax);
        hitPointsData.getInt(SpongeMCDNDSimpleKeys.TEMP_HP.getQuery()).ifPresent(hitPoints::setTemp);
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(DataContainer inventoryTabData, int strengthScore)
    {
        InventoryTab inventoryTab = new InventoryTab();
        getDataContainerList(inventoryTabData, SpongeMCDNDSimpleKeys.INVENTORY).ifPresent(list ->
                inventoryTab.setInventory(list.stream().map(this::deserializeItem).collect(Collectors.toList())));
        inventoryTabData.getStringList(SpongeMCDNDSimpleKeys.INVENTORY_NOTES.getQuery()).ifPresent(inventoryTab::setInventoryNotes);
        getDataContainer(inventoryTabData, SpongeMCDNDSimpleKeys.WEALTH).ifPresent(data ->
                inventoryTab.setWealth(deserializeWealth(data)));
        getDataContainer(inventoryTabData, SpongeMCDNDSimpleKeys.WEIGHT_CLASS).ifPresent(data ->
                inventoryTab.setWeight(deserializeWeight(data, strengthScore, inventoryTab.getInventory(), inventoryTab.getWealth())));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(DataContainer itemData)
    {
        MCDNDItem item = new MCDNDItem();
        itemData.getBoolean(SpongeMCDNDSimpleKeys.CARRIED.getQuery()).ifPresent(item::setIsCarried);
        itemData.getDouble(SpongeMCDNDSimpleKeys.WEIGHT_DOUBLE.getQuery()).ifPresent(item::setWeight);
        itemData.getString(SpongeMCDNDSimpleKeys.DESCRIPTION.getQuery()).ifPresent(item::setDescription);
        itemData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(item::setName);
        return item;
    }

    @Override
    protected Wealth deserializeWealth(DataContainer wealthData)
    {
        Wealth wealth = new Wealth();
        getDataContainer(wealthData, SpongeMCDNDSimpleKeys.COPPER).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Copper", "CP"))));
        getDataContainer(wealthData, SpongeMCDNDSimpleKeys.ELECTRUM).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Electrum", "EP"))));
        getDataContainer(wealthData, SpongeMCDNDSimpleKeys.GOLD).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Gold", "GP"))));
        getDataContainer(wealthData, SpongeMCDNDSimpleKeys.PLATINUM).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Platinum", "PP"))));
        getDataContainer(wealthData, SpongeMCDNDSimpleKeys.SILVER).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Silver", "SP"))));
        return wealth;
    }

    @Override
    protected Coin deserializeCoin(DataContainer coinData, Coin defaultCoin)
    {
        return coinData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
                coinData.getString(SpongeMCDNDSimpleKeys.SHORT_NAME.getQuery()).flatMap(shortName ->
                {
                    Coin coin = new Coin(name, shortName);
                    coinData.getInt(SpongeMCDNDSimpleKeys.AMOUNT.getQuery()).ifPresent(coin::setAmount);
                    return Optional.of(coin);
                })).orElse(defaultCoin);
    }

    @Override
    protected Weight deserializeWeight(DataContainer weightData, int strengthScore, List<MCDNDItem> inventory, Wealth wealth)
    {
        Weight weight = new Weight();
        weightData.getDouble(SpongeMCDNDSimpleKeys.OTHER.getQuery()).ifPresent(weight::setOther);
        weight.setInventoryWeight(inventory);
        weight.setCoinWeight(wealth);
        weight.setCarryingMax(strengthScore);
        weight.setEncumbered(strengthScore);
        weight.setHeavilyEncumbered(strengthScore);
        return weight;
    }

    @Override
    protected SpellbookTab deserializeSpellbookTab(DataContainer spellbookTabData, int sorcererLevel)
    {
        SpellbookTab spellbookTab = new SpellbookTab();
        getDataContainerList(spellbookTabData, SpongeMCDNDSimpleKeys.SPELLS).ifPresent(list ->
                spellbookTab.setSpells(list.stream().map(this::deserializeSpell).collect(Collectors.toList())));
        getDataContainerList(spellbookTabData, SpongeMCDNDSimpleKeys.SPELLCASTER_CLASSES).ifPresent(list ->
                spellbookTab.setSpells(list.stream().map(this::deserializeSpell).collect(Collectors.toList())));
        spellbookTab.setInvocations(sorcererLevel);
        spellbookTab.setSorceryPointsMax(sorcererLevel);
        return spellbookTab;
    }

    @Override
    protected Spell deserializeSpell(DataContainer spellData)
    {
        Spell spell = new Spell();
        spellData.getBoolean(SpongeMCDNDSimpleKeys.NEEDS_CONCENTRATION.getQuery()).ifPresent(spell::setNeedsConcentration);
        spellData.getBoolean(SpongeMCDNDSimpleKeys.IS_PREPARED.getQuery()).ifPresent(spell::setPrepared);
        spellData.getInt(SpongeMCDNDSimpleKeys.DURATION.getQuery()).ifPresent(spell::setDuration);
        spellData.getInt(SpongeMCDNDSimpleKeys.SPELL_LEVEL.getQuery()).ifPresent(spell::setLevel);
        spellData.getInt(SpongeMCDNDSimpleKeys.RANGE.getQuery()).ifPresent(spell::setRange);
        getDataContainer(spellData, SpongeMCDNDSimpleKeys.SPELL_DAMAGE).ifPresent(data ->
                spell.setSpellDamage(deserializeSpellDamage(data)));
        spellData.getStringList(SpongeMCDNDSimpleKeys.SPELL_DESCRIPTION.getQuery()).ifPresent(spell::setDescription);
        spellData.getStringList(SpongeMCDNDSimpleKeys.EFFECTS.getQuery()).ifPresent(spell::setEffects);
        getDataContainer(spellData, SpongeMCDNDSimpleKeys.SPELL_SAVE).ifPresent(data ->
                spell.setSpellSave(deserializeSpellSave(data)));
        getDataContainer(spellData, SpongeMCDNDSimpleKeys.SPELLCASTER_CLASS).ifPresent(data ->
                spell.setGainedFrom(deserializeSpellcasterClass(data)));
        getDataContainer(spellData, SpongeMCDNDSimpleKeys.SPELL_TYPE).ifPresent(data ->
                spell.setSpellType(deserializeSpellType(data)));
        getDataContainer(spellData, SpongeMCDNDSimpleKeys.SPELL_HEALING).ifPresent(data ->
                spell.setSpellHealing(deserializeSpellHealing(data)));
        spellData.getString(SpongeMCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(spell::setAttackStat);
        spellData.getString(SpongeMCDNDSimpleKeys.CAST_TIME.getQuery()).ifPresent(spell::setCastTime);
        spellData.getString(SpongeMCDNDSimpleKeys.TARGET_AREA.getQuery()).ifPresent(spell::setTargetArea);
        return spell;
    }

    @Override
    protected SaveDCType deserializeSaveDCType(DataContainer spellData)//NOSONAR
    {
        return spellData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).map(name ->//NOSONAR
        {
            if ("Custom DC".equals(name))
                return spellData.getInt(SpongeMCDNDSimpleKeys.CUSTOM_DC.getQuery()).map(SaveDCTypes::custom).orElse(SaveDCTypes.custom(0));
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

            return SaveDCTypes.custom(0);
        }).orElse(SaveDCTypes.custom(0));
    }

    @Override
    protected ArmorType deserializeArmorType(DataContainer armorTypeData)
    {
        return armorTypeData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
        {
            for (ArmorType armorType : ArmorType.values())
                if (armorType.getName().equals(name))
                    return Optional.of(armorType);

            return Optional.empty();
        }).orElse(ArmorType.NONE);
    }

    @Override
    protected SpellDamage deserializeSpellDamage(DataContainer spellDamageData)
    {
        SpellDamage spellDamage = new SpellDamage();
        spellDamageData.getBoolean(SpongeMCDNDSimpleKeys.CAN_CRIT.getQuery()).ifPresent(spellDamage::setCanCrit);
        getDataContainer(spellDamageData, SpongeMCDNDSimpleKeys.DICE).ifPresent(data ->
                spellDamage.setDice(deserializeDice(data)));
        spellDamageData.getInt(SpongeMCDNDSimpleKeys.BONUS.getQuery()).ifPresent(spellDamage::setBonus);
        spellDamageData.getString(SpongeMCDNDSimpleKeys.DAMAGE_TYPE.getQuery()).ifPresent(spellDamage::setDamageType);
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(DataContainer spellHealingData)
    {
        SpellHealing spellHealing = new SpellHealing();
        spellHealingData.getInt(SpongeMCDNDSimpleKeys.HEAL_AMOUNT.getQuery()).ifPresent(spellHealing::setHealAmount);
        spellHealingData.getString(SpongeMCDNDSimpleKeys.STAT_BONUS.getQuery()).ifPresent(spellHealing::setStatBonus);
        return spellHealing;
    }

    @Override
    protected SpellSave deserializeSpellSave(DataContainer spellSaveData)
    {
        SpellSave spellSave = new SpellSave();
        getDataContainer(spellSaveData, SpongeMCDNDSimpleKeys.SAVE_DC_TYPE).ifPresent(data ->
                spellSave.setSaveDCType(deserializeSaveDCType(data)));
        spellSaveData.getString(SpongeMCDNDSimpleKeys.SAVE_DC_TYPE.getQuery()).ifPresent(spellSave::setOnSuccessfulSave);
        spellSaveData.getString(SpongeMCDNDSimpleKeys.SAVING_STAT.getQuery()).ifPresent(spellSave::setSavingStat);
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(DataContainer spellTypeData)
    {
        return spellTypeData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).map(name ->
        {
            for (SpellType spellType : SpellType.values())
                if (name.equals(spellType.getName()))
                    return spellType;

            return SpellType.OTHER;
        }).orElse(SpellType.OTHER);
    }

    @Override
    protected SpellcasterClass deserializeSpellcasterClass(DataContainer spellcasterClassData)
    {
        return spellcasterClassData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).map(name ->
        {
            for (SpellcasterClass spellcasterClass : SpellcasterClass.values())
                if (name.equals(spellcasterClass.getName()))
                    return spellcasterClass;

            return SpellcasterClass.OTHER;
        }).orElse(SpellcasterClass.OTHER);
    }

    @Override
    protected WeaponsTab deserializeWeaponsTab(DataContainer weaponsTabData)
    {
        WeaponsTab weaponsTab = new WeaponsTab();
        getDataContainerList(weaponsTabData, SpongeMCDNDSimpleKeys.MELEE_WEAPONS).ifPresent(list ->
                weaponsTab.setMeleeWeapons(list.stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList())));
        getDataContainerList(weaponsTabData, SpongeMCDNDSimpleKeys.RANGED_WEAPONS).ifPresent(list ->
                weaponsTab.setRangedWeapons(list.stream().map(this::deserializeRangedWeapon).collect(Collectors.toList())));
        return weaponsTab;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(DataContainer meleeWeaponData)
    {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeaponData.getBoolean(SpongeMCDNDSimpleKeys.PLUS_STAT.getQuery()).ifPresent(meleeWeapon::setPlusStat);
        meleeWeaponData.getBoolean(SpongeMCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(meleeWeapon::setIsProficient);
        getDataContainer(meleeWeaponData, SpongeMCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data ->
                meleeWeapon.setCritDamageDice(deserializeDice(data)));
        getDataContainer(meleeWeaponData, SpongeMCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data ->
                meleeWeapon.setDamageDice(deserializeDice(data)));
        meleeWeaponData.getInt(SpongeMCDNDSimpleKeys.CRIT_MINIMUM.getQuery()).ifPresent(meleeWeapon::setCritMin);
        meleeWeaponData.getInt(SpongeMCDNDSimpleKeys.DAMAGE_BONUS.getQuery()).ifPresent(meleeWeapon::setDamageBonus);
        meleeWeaponData.getInt(SpongeMCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(meleeWeapon::setMagicBonus);
        meleeWeaponData.getInt(SpongeMCDNDSimpleKeys.TO_HIT.getQuery()).ifPresent(meleeWeapon::setToHit);
        meleeWeaponData.getString(SpongeMCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(meleeWeapon::setAttackStat);
        meleeWeaponData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(meleeWeapon::setName);
        return meleeWeapon;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(DataContainer rangedWeaponData)
    {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeaponData.getInt(SpongeMCDNDSimpleKeys.PLUS_STAT.getQuery()).ifPresent(rangedWeapon::setAmmo);
        rangedWeaponData.getBoolean(SpongeMCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(rangedWeapon::setIsProficient);
        getDataContainer(rangedWeaponData, SpongeMCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data ->
                rangedWeapon.setCritDamageDice(deserializeDice(data)));
        getDataContainer(rangedWeaponData, SpongeMCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data ->
                rangedWeapon.setDamageDice(deserializeDice(data)));
        rangedWeaponData.getInt(SpongeMCDNDSimpleKeys.CRIT_MINIMUM.getQuery()).ifPresent(rangedWeapon::setCritMin);
        rangedWeaponData.getInt(SpongeMCDNDSimpleKeys.DAMAGE_BONUS.getQuery()).ifPresent(rangedWeapon::setDamageBonus);
        rangedWeaponData.getInt(SpongeMCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(rangedWeapon::setMagicBonus);
        rangedWeaponData.getInt(SpongeMCDNDSimpleKeys.TO_HIT.getQuery()).ifPresent(rangedWeapon::setToHit);
        rangedWeaponData.getString(SpongeMCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(rangedWeapon::setAttackStat);
        rangedWeaponData.getString(SpongeMCDNDSimpleKeys.NAME.getQuery()).ifPresent(rangedWeapon::setName);
        return rangedWeapon;
    }

    @Override
    protected CharacterSheet deserializePlayerSheet(DataContainer playerSheetData)
    {
        CharacterSheet characterSheet = new CharacterSheet();
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.ARMOR_TAB).ifPresent(armorTabData ->
                characterSheet.setArmorTab(deserializeArmorTab(armorTabData)));
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.BACKGROUND_TAB).ifPresent(backgroundTabData ->
                characterSheet.setBackgroundTab(deserializeBackgroundTab(backgroundTabData)));
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.CLASS_TAB).ifPresent(classTabData ->
                characterSheet.setClassTab(deserializeClassTab(classTabData)));
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.CORE_STATS_TAB).ifPresent(data ->
                characterSheet.setCoreStatsTab(deserializeCoreStatsTab(data)));
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.INVENTORY_TAB).ifPresent(data ->
                characterSheet.setInventoryTab(deserializeInventoryTab(data, characterSheet.getCoreStatsTab().getCoreStats().getStrength().getScore())));
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.SKILLS_TAB).ifPresent(data ->
                characterSheet.setSkillsTab(deserializeSkillsTab(data, characterSheet.getCoreStatsTab().getCoreStats())));
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.SPELL_BOOK_TAB).ifPresent(data ->
                characterSheet.setSpellbookTab(deserializeSpellbookTab(data, characterSheet.getClassTab().getClassLevels().getSorcerer())));
        getDataContainer(playerSheetData, SpongeMCDNDSimpleKeys.WEAPONS_TAB).ifPresent(data ->
                characterSheet.setWeaponsTab(deserializeWeaponsTab(data)));
        return characterSheet;
    }

    private Optional<DataContainer> getDataContainer(DataContainer dataContainer, Key<Value<DataContainer>> key)
    {
        return dataContainer.getObject(key.getQuery(), DataContainer.class);
    }

    private Optional<List<DataContainer>> getDataContainerList(DataContainer dataContainer, Key<ListValue<DataContainer>> key)
    {
        return dataContainer.getObjectList(key.getQuery(), DataContainer.class);
    }
}
