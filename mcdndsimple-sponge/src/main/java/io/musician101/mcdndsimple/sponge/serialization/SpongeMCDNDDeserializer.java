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
import io.musician101.mcdndsimple.sponge.data.key.MCDNDSimpleKeys;
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
    public CharacterSheet deserialize(DataContainer characterSheetData)
    {
        CharacterSheet characterSheet = new CharacterSheet();
        getDataContainer(characterSheetData, MCDNDSimpleKeys.BIO_AND_INFO).ifPresent(bioData ->
                characterSheet.setBioAndInfo(deserializeBioAndInfo(bioData)));
        getDataContainer(characterSheetData, MCDNDSimpleKeys.PLAYER_SHEET).ifPresent(playerData ->
                characterSheet.setPlayerSheet(deserializePlayerSheet(playerData)));
        characterSheetData.getString(MCDNDSimpleKeys.CLASS.getQuery()).ifPresent(characterSheet::setClazz);
        characterSheetData.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(characterSheet::setName);
        characterSheetData.getString(MCDNDSimpleKeys.RACE.getQuery()).ifPresent(characterSheet::setRace);
        return characterSheet;
    }

    @Override
    protected BioAndInfo deserializeBioAndInfo(DataContainer bioAndInfoData)
    {
        BioAndInfo bioAndInfo = new BioAndInfo();
        bioAndInfoData.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(bioAndInfo::setName);
        bioAndInfoData.getStringList(MCDNDSimpleKeys.BIO.getQuery()).ifPresent(bioAndInfo::setBio);
        return bioAndInfo;
    }

    @Override
    protected SkillsTab deserializeSkillsTab(DataContainer skillsTabData, CoreStats coreStats)
    {
        SkillsTab skillsTab = new SkillsTab();
        skillsTab.updateSkills(coreStats);
        getDataContainer(skillsTabData, MCDNDSimpleKeys.ACROBATICS).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAcrobatics().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.ANIMAL_HANDLING).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAnimalHandling().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.ARCANA).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getArcana().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.ATHLETICS).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getAthletics().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.DECEPTION).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getDeception().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.HISTORY).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getHistory().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.INSIGHT).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getInsight().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.INTIMIDATION).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getIntimidation().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.INVESTIGATION).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getInvestigation().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.MEDICINE).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getMedicine().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.NATURE).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getNature().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.PERCEPTION).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPerception().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.PERFORMANCE).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPerformance().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.PERSUASION).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getPersuasion().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.RELIGION).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getReligion().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.SLEIGHT_OF_HAND).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getSleightOfHand().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.STEALTH).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getStealth().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.SURVIVAL).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getSurvival().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.UNSKILLED_CHA).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledCHA().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.UNSKILLED_CON).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledCON().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.UNSKILLED_DEX).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledDEX().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.UNSKILLED_INT).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledINT().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.UNSKILLED_STR).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledSTR().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        getDataContainer(skillsTabData, MCDNDSimpleKeys.UNSKILLED_WIS).ifPresent(skillData ->
                getDataContainer(skillData, MCDNDSimpleKeys.SKILL_PROFICIENCY).ifPresent(skillProficiencyData ->
                        skillsTab.getUnskilledWIS().setSkillProficiency(deserializeSkillProficiency(skillProficiencyData))));
        return skillsTab;
    }

    @Override
    protected SkillProficiency deserializeSkillProficiency(DataContainer skillsProficiencyData)
    {
        return skillsProficiencyData.getString(MCDNDSimpleKeys.NAME.getQuery()).map(name ->
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
        armorTabData.getInt(MCDNDSimpleKeys.ARMOR_CLASS.getQuery()).ifPresent(armorTab::setArmorClass);
        armorTabData.getInt(MCDNDSimpleKeys.UNARMORED_ARMOR_CLASS.getQuery()).ifPresent(armorTab::setUnarmoredClass);
        getDataContainerList(armorTabData, MCDNDSimpleKeys.ARMOR_LIST).ifPresent(list ->
                armorTab.setArmor(list.stream().map(this::deserializeArmor).collect(Collectors.toList())));
        getDataContainer(armorTabData, MCDNDSimpleKeys.UNARMORED_BONUS).ifPresent(unarmoredBonusData ->
                armorTab.setUnarmoredBonus(deserializeUnarmoredBonus(unarmoredBonusData)));
        return armorTab;
    }

    @Override
    protected Armor deserializeArmor(DataContainer armorTab)
    {
        Armor armor = new Armor();
        armorTab.getBoolean(MCDNDSimpleKeys.SPEED_PENALTY.getQuery()).ifPresent(armor::setSpeedPenalty);
        armorTab.getBoolean(MCDNDSimpleKeys.STEALTH_PENALTY.getQuery()).ifPresent(armor::setStealthPenalty);
        armorTab.getBoolean(MCDNDSimpleKeys.UNARMORED.getQuery()).ifPresent(armor::setUnarmored);
        armorTab.getBoolean(MCDNDSimpleKeys.WORN.getQuery()).ifPresent(armor::setWorn);
        armorTab.getInt(MCDNDSimpleKeys.BASE_ARMOR_CLASS.getQuery()).ifPresent(armor::setBaseArmorClass);
        armorTab.getInt(MCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(armor::setMagicBonus);
        getDataContainer(armorTab, MCDNDSimpleKeys.ARMOR_TYPE).ifPresent(armorTypeData ->
                armor.setArmorType(deserializeArmorType(armorTypeData)));
        armorTab.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(armor::setName);
        return armor;
    }

    @Override
    protected UnarmoredBonus deserializeUnarmoredBonus(DataContainer unarmoredBonusData)
    {
        return unarmoredBonusData.getString(MCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
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
        backgroundTabData.getDouble(MCDNDSimpleKeys.WEIGHT_DOUBLE.getQuery()).ifPresent(backgroundTab::setWeight);
        backgroundTabData.getInt(MCDNDSimpleKeys.AGE.getQuery()).ifPresent(backgroundTab::setAge);
        backgroundTabData.getStringList(MCDNDSimpleKeys.ARMOR_PROFICIENCIES.getQuery()).ifPresent(backgroundTab::setArmorProficiencies);
        backgroundTabData.getStringList(MCDNDSimpleKeys.BACKGROUND.getQuery()).ifPresent(backgroundTab::setBackground);
        backgroundTabData.getStringList(MCDNDSimpleKeys.BONDS.getQuery()).ifPresent(backgroundTab::setBonds);
        backgroundTabData.getStringList(MCDNDSimpleKeys.FLAWS.getQuery()).ifPresent(backgroundTab::setFlaws);
        backgroundTabData.getStringList(MCDNDSimpleKeys.IDEALS.getQuery()).ifPresent(backgroundTab::setIdeals);
        backgroundTabData.getStringList(MCDNDSimpleKeys.OTHER_NOTES.getQuery()).ifPresent(backgroundTab::setOtherNotes);
        backgroundTabData.getStringList(MCDNDSimpleKeys.PERSONALITY_TRAITS.getQuery());
        backgroundTabData.getStringList(MCDNDSimpleKeys.RACIAL_TRAITS.getQuery()).ifPresent(backgroundTab::setRacialTraits);
        backgroundTabData.getStringList(MCDNDSimpleKeys.TOOL_PROFICIENCIES.getQuery()).ifPresent(backgroundTab::setToolProficiencies);
        backgroundTabData.getStringList(MCDNDSimpleKeys.WEAPON_PROFICIENCIES.getQuery()).ifPresent(backgroundTab::setWeaponProficiencies);
        backgroundTabData.getString(MCDNDSimpleKeys.ALIGNMENT.getQuery()).ifPresent(backgroundTab::setAlignment);
        backgroundTabData.getString(MCDNDSimpleKeys.EYES.getQuery()).ifPresent(backgroundTab::setEyes);
        backgroundTabData.getString(MCDNDSimpleKeys.GENDER.getQuery()).ifPresent(backgroundTab::setGender);
        backgroundTabData.getString(MCDNDSimpleKeys.HAIR.getQuery()).ifPresent(backgroundTab::setHair);
        backgroundTabData.getString(MCDNDSimpleKeys.HEIGHT.getQuery()).ifPresent(backgroundTab::setHeight);
        backgroundTabData.getString(MCDNDSimpleKeys.LANGUAGES.getQuery()).ifPresent(backgroundTab::setLanguages);
        backgroundTabData.getString(MCDNDSimpleKeys.SIZE.getQuery()).ifPresent(backgroundTab::setSize);
        backgroundTabData.getString(MCDNDSimpleKeys.VISION.getQuery()).ifPresent(backgroundTab::setVision);
        return backgroundTab;
    }

    @Override
    protected Recharge deserializeRecharge(DataContainer rechargeData)
    {
        return rechargeData.getString(MCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
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
        getDataContainer(classTabData, MCDNDSimpleKeys.CLASS_LEVELS).ifPresent(data ->
                classTab.setClassLevels(deserializeClassLevels(data)));
        getDataContainerList(classTabData, MCDNDSimpleKeys.CLASS_ACTIONS).ifPresent(list ->
                classTab.setClassActions(list.stream().map(this::deserializeClassAction).collect(Collectors.toList())));
        getDataContainerList(classTabData, MCDNDSimpleKeys.CLASS_RESOURCES).ifPresent(list ->
                list.forEach(data -> classTab.addClassResource(deserializeClassResource(data))));
        classTabData.getStringList(MCDNDSimpleKeys.CLASS_FEATURE_NOTES.getQuery()).ifPresent(classTab::setOtherNotes);
        return classTab;
    }

    @Override
    protected ClassLevels deserializeClassLevels(DataContainer classLevelsData)
    {
        ClassLevels classLevels = new ClassLevels();
        classLevelsData.getInt(MCDNDSimpleKeys.BARBARIAN_LEVEL.getQuery()).ifPresent(classLevels::setBarbarian);
        classLevelsData.getInt(MCDNDSimpleKeys.BARD_LEVEL.getQuery()).ifPresent(classLevels::setBard);
        classLevelsData.getInt(MCDNDSimpleKeys.CLERIC_LEVEL.getQuery()).ifPresent(classLevels::setCleric);
        classLevelsData.getInt(MCDNDSimpleKeys.DRUID_LEVEL.getQuery()).ifPresent(classLevels::setDruid);
        classLevelsData.getInt(MCDNDSimpleKeys.FIGHTER_LEVEL.getQuery()).ifPresent(classLevels::setFighter);
        classLevelsData.getInt(MCDNDSimpleKeys.MONK_LEVEL.getQuery()).ifPresent(classLevels::setMonk);
        classLevelsData.getInt(MCDNDSimpleKeys.PALADIN_LEVEL.getQuery()).ifPresent(classLevels::setPaladin);
        classLevelsData.getInt(MCDNDSimpleKeys.RANGER_LEVEL.getQuery()).ifPresent(classLevels::setRanger);
        classLevelsData.getInt(MCDNDSimpleKeys.ROGUE_LEVEL.getQuery()).ifPresent(classLevels::setRogue);
        classLevelsData.getInt(MCDNDSimpleKeys.SORCERER_LEVEL.getQuery()).ifPresent(classLevels::setSorcerer);
        classLevelsData.getInt(MCDNDSimpleKeys.WARLOCK_LEVEL.getQuery()).ifPresent(classLevels::setWarlock);
        classLevelsData.getInt(MCDNDSimpleKeys.WIZARD_LEVEL.getQuery()).ifPresent(classLevels::setWizard);
        return classLevels;
    }

    @Override
    protected ClassAction deserializeClassAction(DataContainer classActionData)
    {
        ClassAction classAction = new ClassAction();
        classActionData.getInt(MCDNDSimpleKeys.MAX_USES.getQuery()).ifPresent(classAction::setMax);
        classActionData.getInt(MCDNDSimpleKeys.USES_LEFT.getQuery()).ifPresent(classAction::setUsesLeft);
        getDataContainer(classActionData, MCDNDSimpleKeys.RECHARGE).ifPresent(rechargeData ->
                classAction.setRecharge(deserializeRecharge(rechargeData)));
        classActionData.getString(MCDNDSimpleKeys.GAINED_FROM.getQuery()).ifPresent(classAction::setGainedFrom);
        classActionData.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(classAction::setName);
        return classAction;
    }

    @Override
    protected ClassResource deserializeClassResource(DataContainer classResourceData)
    {
        ClassResource classResource = new ClassResource();
        classResourceData.getInt(MCDNDSimpleKeys.USES_LEFT.getQuery()).ifPresent(classResource::setCurrentCharges);
        classResourceData.getInt(MCDNDSimpleKeys.MAX_USES.getQuery()).ifPresent(classResource::setMaxCharges);
        getDataContainer(classResourceData, MCDNDSimpleKeys.RECHARGE).ifPresent(rechargeData -> classResource.setRecharge(deserializeRecharge(rechargeData)));
        classResourceData.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(classResource::setName);
        return classResource;
    }

    @Override
    protected CoreStatsTab deserializeCoreStatsTab(DataContainer coreStatsTabData)
    {
        CoreStatsTab coreStatsTab = new CoreStatsTab();
        getDataContainer(coreStatsTabData, MCDNDSimpleKeys.BONUSES).ifPresent(data ->
                coreStatsTab.setBonuses(deserializeBonuses(data)));
        getDataContainer(coreStatsTabData, MCDNDSimpleKeys.CORE_STATS).ifPresent(data ->
                coreStatsTab.setCoreStats(deserializeCoreStats(data)));
        getDataContainer(coreStatsTabData, MCDNDSimpleKeys.EXPERIENCE).ifPresent(data ->
                coreStatsTab.setExperience(deserializeExperience(data)));
        getDataContainer(coreStatsTabData, MCDNDSimpleKeys.HIT_DICE).ifPresent(data ->
                coreStatsTab.setHitDice(deserializeHitDice(data)));
        getDataContainer(coreStatsTabData, MCDNDSimpleKeys.HIT_POINTS).ifPresent(data ->
                coreStatsTab.setHitPoints(deserializeHitPoints(data)));
        coreStatsTabData.getInt(MCDNDSimpleKeys.INITIATIVE_BONUS.getQuery()).ifPresent(coreStatsTab::setInitiativeBonus);
        coreStatsTabData.getInt(MCDNDSimpleKeys.SPEED.getQuery()).ifPresent(coreStatsTab::setSpeed);
        return coreStatsTab;
    }

    @Override
    protected Bonuses deserializeBonuses(DataContainer bonusesData)
    {
        Bonuses bonuses = new Bonuses();
        getDataContainer(bonusesData, MCDNDSimpleKeys.MELEE_BONUS).ifPresent(meleeBonusData ->
                bonuses.setMelee(deserializeMeleeBonus(meleeBonusData)));
        getDataContainer(bonusesData, MCDNDSimpleKeys.RANGED_BONUS).ifPresent(rangedBonusData ->
                bonuses.setRanged(deserializeRangedBonus(rangedBonusData)));
        getDataContainer(bonusesData, MCDNDSimpleKeys.SPELLCASTING_BONUS).ifPresent(spellcastingBonusData ->
                bonuses.setSpellcasting(deserializeSpellcastingBonus(spellcastingBonusData)));
        bonusesData.getInt(MCDNDSimpleKeys.SAVES.getQuery()).ifPresent(bonuses::setSaves);
        bonusesData.getInt(MCDNDSimpleKeys.ABILITIES_AND_SKILLS.getQuery()).ifPresent(bonuses::setAbilitiesAndSkills);
        return bonuses;
    }

    @Override
    protected MeleeBonus deserializeMeleeBonus(DataContainer meleeBonusData)
    {
        MeleeBonus meleeBonus = new MeleeBonus();
        meleeBonusData.getInt(MCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(meleeBonus::setAttack);
        meleeBonusData.getInt(MCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(meleeBonus::setDamage);
        return meleeBonus;
    }

    @Override
    protected RangedBonus deserializeRangedBonus(DataContainer rangedBonusData)
    {
        RangedBonus rangedBonus = new RangedBonus();
        rangedBonusData.getInt(MCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(rangedBonus::setAttack);
        rangedBonusData.getInt(MCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(rangedBonus::setDamage);
        return rangedBonus;
    }

    @Override
    protected SpellcastingBonus deserializeSpellcastingBonus(DataContainer spellcastingBonusData)
    {
        SpellcastingBonus spellcastingBonus = new SpellcastingBonus();
        spellcastingBonusData.getInt(MCDNDSimpleKeys.ATTACK.getQuery()).ifPresent(spellcastingBonus::setAttack);
        spellcastingBonusData.getInt(MCDNDSimpleKeys.DAMAGE.getQuery()).ifPresent(spellcastingBonus::setDamage);
        spellcastingBonusData.getInt(MCDNDSimpleKeys.SAVE_DC.getQuery()).ifPresent(spellcastingBonus::setSaveDC);
        return spellcastingBonus;
    }

    @Override
    protected CoreStats deserializeCoreStats(DataContainer coreStatsData)
    {
        CoreStats coreStats = new CoreStats();
        getDataContainer(coreStatsData, MCDNDSimpleKeys.CHARISMA_SCORE).ifPresent(data ->
                coreStats.setCharisma(deserializeAbilityScore(data, new AbilityScore("Charisma", "CHA"))));
        getDataContainer(coreStatsData, MCDNDSimpleKeys.CONSTITUTION_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Constitution", "CON"))));
        getDataContainer(coreStatsData, MCDNDSimpleKeys.DEXTERITY_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Dexterity", "DEX"))));
        getDataContainer(coreStatsData, MCDNDSimpleKeys.INTELLIGENCE_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Intelligence", "INT"))));
        getDataContainer(coreStatsData, MCDNDSimpleKeys.STRENGTH_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Strength", "STR"))));
        getDataContainer(coreStatsData, MCDNDSimpleKeys.WISDOM_SCORE).ifPresent(data ->
                coreStats.setDexterity(deserializeAbilityScore(data, new AbilityScore("Wisdom", "WIS"))));
        return coreStats;
    }

    @Override
    protected AbilityScore deserializeAbilityScore(DataContainer abilityScoreData, AbilityScore defaultScore)
    {
        return abilityScoreData.getString(MCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
                abilityScoreData.getString(MCDNDSimpleKeys.SHORT_NAME.getQuery()).flatMap(shortName ->
                {
                    AbilityScore abilityScore = new AbilityScore(name, shortName);
                    abilityScoreData.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(abilityScore::setProficient);
                    abilityScoreData.getInt(MCDNDSimpleKeys.SCORE.getQuery()).ifPresent(abilityScore::setScore);
                    return Optional.of(abilityScore);
                })).orElse(defaultScore);
    }

    @Override
    protected Experience deserializeExperience(DataContainer experienceData)
    {
        Experience experience = new Experience();
        experienceData.getInt(MCDNDSimpleKeys.EXPERIENCE.getQuery()).ifPresent(experience::setExp);
        experienceData.getInt(MCDNDSimpleKeys.OVERALL_LEVEL.getQuery()).ifPresent(experience::setOverallLevel);
        return experience;
    }

    @Override
    protected HitDice deserializeHitDice(DataContainer hitDiceData)
    {
        HitDice hitDice = new HitDice();
        hitDiceData.getKeys(false).forEach(dataQuery ->
                hitDiceData.getInt(dataQuery).ifPresent(amount ->
                        hitDice.addHitDie(Integer.parseInt(dataQuery.toString()), amount)));
        return hitDice;
    }

    @Override
    protected Dice deserializeDice(DataContainer diceData)
    {
        return diceData.getInt(MCDNDSimpleKeys.SIDES.getQuery()).map(sides ->
                diceData.getInt(MCDNDSimpleKeys.AMOUNT.getQuery()).map(amount ->
                        new Dice(amount, sides)).orElse(new Dice(sides))).orElse(new Dice(6));
    }

    @Override
    protected HitPoints deserializeHitPoints(DataContainer hitPointsData)
    {
        HitPoints hitPoints = new HitPoints();
        hitPointsData.getInt(MCDNDSimpleKeys.CURRENT_HP.getQuery()).ifPresent(hitPoints::setCurrent);
        hitPointsData.getInt(MCDNDSimpleKeys.MAX_HP.getQuery()).ifPresent(hitPoints::setMax);
        hitPointsData.getInt(MCDNDSimpleKeys.TEMP_HP.getQuery()).ifPresent(hitPoints::setTemp);
        return hitPoints;
    }

    @Override
    protected InventoryTab deserializeInventoryTab(DataContainer inventoryTabData, int strengthScore)
    {
        InventoryTab inventoryTab = new InventoryTab();
        getDataContainerList(inventoryTabData, MCDNDSimpleKeys.INVENTORY).ifPresent(list ->
                inventoryTab.setInventory(list.stream().map(this::deserializeItem).collect(Collectors.toList())));
        inventoryTabData.getStringList(MCDNDSimpleKeys.INVENTORY_NOTES.getQuery()).ifPresent(inventoryTab::setInventoryNotes);
        getDataContainer(inventoryTabData, MCDNDSimpleKeys.WEALTH).ifPresent(data ->
                inventoryTab.setWealth(deserializeWealth(data)));
        getDataContainer(inventoryTabData, MCDNDSimpleKeys.WEIGHT_CLASS).ifPresent(data ->
                inventoryTab.setWeight(deserializeWeight(data, strengthScore, inventoryTab.getInventory(), inventoryTab.getWealth())));
        return inventoryTab;
    }

    @Override
    protected MCDNDItem deserializeItem(DataContainer itemData)
    {
        MCDNDItem item = new MCDNDItem();
        itemData.getBoolean(MCDNDSimpleKeys.CARRIED.getQuery()).ifPresent(item::setCarried);
        itemData.getDouble(MCDNDSimpleKeys.WEIGHT_DOUBLE.getQuery()).ifPresent(item::setWeight);
        itemData.getString(MCDNDSimpleKeys.DESCRIPTION.getQuery()).ifPresent(item::setDescription);
        itemData.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(item::setName);
        return item;
    }

    @Override
    protected Wealth deserializeWealth(DataContainer wealthData)
    {
        Wealth wealth = new Wealth();
        getDataContainer(wealthData, MCDNDSimpleKeys.COPPER).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Copper", "CP"))));
        getDataContainer(wealthData, MCDNDSimpleKeys.ELECTRUM).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Electrum", "EP"))));
        getDataContainer(wealthData, MCDNDSimpleKeys.GOLD).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Gold", "GP"))));
        getDataContainer(wealthData, MCDNDSimpleKeys.PLATINUM).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Platinum", "PP"))));
        getDataContainer(wealthData, MCDNDSimpleKeys.SILVER).ifPresent(data ->
                wealth.setCopper(deserializeCoin(data, new Coin("Silver", "SP"))));
        return wealth;
    }

    @Override
    protected Coin deserializeCoin(DataContainer coinData, Coin defaultCoin)
    {
        return coinData.getString(MCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
                coinData.getString(MCDNDSimpleKeys.SHORT_NAME.getQuery()).flatMap(shortName ->
                {
                    Coin coin = new Coin(name, shortName);
                    coinData.getInt(MCDNDSimpleKeys.AMOUNT.getQuery()).ifPresent(coin::setAmount);
                    return Optional.of(coin);
                })).orElse(defaultCoin);
    }

    @Override
    protected Weight deserializeWeight(DataContainer weightData, int strengthScore, List<MCDNDItem> inventory, Wealth wealth)
    {
        Weight weight = new Weight();
        weightData.getDouble(MCDNDSimpleKeys.OTHER.getQuery()).ifPresent(weight::setOther);
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
        getDataContainerList(spellbookTabData, MCDNDSimpleKeys.SPELLS).ifPresent(list ->
                spellbookTab.setSpells(list.stream().map(this::deserializeSpell).collect(Collectors.toList())));
        getDataContainerList(spellbookTabData, MCDNDSimpleKeys.SPELLCASTER_CLASSES).ifPresent(list ->
                spellbookTab.setSpells(list.stream().map(this::deserializeSpell).collect(Collectors.toList())));
        spellbookTab.setInvocations(sorcererLevel);
        spellbookTab.setSorceryPoints(sorcererLevel);
        return spellbookTab;
    }

    @Override
    protected Spell deserializeSpell(DataContainer spellData)
    {
        Spell spell = new Spell();
        spellData.getBoolean(MCDNDSimpleKeys.NEEDS_CONCENTRATION.getQuery()).ifPresent(spell::setNeedsConcentration);
        spellData.getBoolean(MCDNDSimpleKeys.IS_PREPARED.getQuery()).ifPresent(spell::setPrepared);
        spellData.getInt(MCDNDSimpleKeys.DURATION.getQuery()).ifPresent(spell::setDuration);
        spellData.getInt(MCDNDSimpleKeys.SPELL_LEVEL.getQuery()).ifPresent(spell::setLevel);
        spellData.getInt(MCDNDSimpleKeys.RANGE.getQuery()).ifPresent(spell::setRange);
        getDataContainer(spellData, MCDNDSimpleKeys.SPELL_DAMAGE).ifPresent(data ->
                spell.setSpellDamage(deserializeSpellDamage(data)));
        spellData.getStringList(MCDNDSimpleKeys.SPELL_DESCRIPTION.getQuery()).ifPresent(spell::setDescription);
        spellData.getStringList(MCDNDSimpleKeys.EFFECTS.getQuery()).ifPresent(spell::setEffects);
        getDataContainer(spellData, MCDNDSimpleKeys.SPELL_SAVE).ifPresent(data ->
                spell.setSpellSave(deserializeSpellSave(data)));
        getDataContainer(spellData, MCDNDSimpleKeys.SPELLCASTER_CLASS).ifPresent(data ->
                spell.setGainedFrom(deserializeSpellcasterClass(data)));
        getDataContainer(spellData, MCDNDSimpleKeys.SPELL_TYPE).ifPresent(data ->
                spell.setSpellType(deserializeSpellType(data)));
        getDataContainer(spellData, MCDNDSimpleKeys.SPELL_HEALING).ifPresent(data ->
                spell.setSpellHealing(deserializeSpellHealing(data)));
        spellData.getString(MCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(spell::setAttackStat);
        spellData.getString(MCDNDSimpleKeys.CAST_TIME.getQuery()).ifPresent(spell::setCastTime);
        spellData.getString(MCDNDSimpleKeys.TARGET_AREA.getQuery()).ifPresent(spell::setTargetArea);
        return spell;
    }

    @Override
    protected SaveDCType deserializeSaveDCType(DataContainer spellData)//NOSONAR
    {
        return spellData.getString(MCDNDSimpleKeys.NAME.getQuery()).map(name ->//NOSONAR
        {
            if ("Custom DC".equals(name))
                return spellData.getInt(MCDNDSimpleKeys.CUSTOM_DC.getQuery()).map(SaveDCTypes::custom).orElse(SaveDCTypes.custom(0));
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
    protected MCDNDArmorType deserializeArmorType(DataContainer armorTypeData)
    {
        return armorTypeData.getString(MCDNDSimpleKeys.NAME.getQuery()).flatMap(name ->
        {
            for (MCDNDArmorType armorType : MCDNDArmorType.values())
                if (armorType.getName().equals(name))
                    return Optional.of(armorType);

            return Optional.empty();
        }).orElse(MCDNDArmorType.NONE);
    }

    @Override
    protected SpellDamage deserializeSpellDamage(DataContainer spellDamageData)
    {
        SpellDamage spellDamage = new SpellDamage();
        spellDamageData.getBoolean(MCDNDSimpleKeys.CAN_CRIT.getQuery()).ifPresent(spellDamage::setCanCrit);
        getDataContainer(spellDamageData, MCDNDSimpleKeys.DICE).ifPresent(data ->
                spellDamage.setDice(deserializeDice(data)));
        spellDamageData.getInt(MCDNDSimpleKeys.CAN_CRIT.getQuery()).ifPresent(spellDamage::setBonus);
        spellDamageData.getString(MCDNDSimpleKeys.CAN_CRIT.getQuery()).ifPresent(spellDamage::setDamageType);
        return spellDamage;
    }

    @Override
    protected SpellHealing deserializeSpellHealing(DataContainer spellHealingData)
    {
        SpellHealing spellHealing = new SpellHealing();
        spellHealingData.getInt(MCDNDSimpleKeys.HEAL_AMOUNT.getQuery()).ifPresent(spellHealing::setHealAmount);
        spellHealingData.getString(MCDNDSimpleKeys.STAT_BONUS.getQuery()).ifPresent(spellHealing::setStatBonus);
        return spellHealing;
    }

    @Override
    protected SpellSave deserializeSpellSave(DataContainer spellSaveData)
    {
        SpellSave spellSave = new SpellSave();
        getDataContainer(spellSaveData, MCDNDSimpleKeys.SAVE_DC_TYPE).ifPresent(data ->
                spellSave.setSaveDCType(deserializeSaveDCType(data)));
        spellSaveData.getString(MCDNDSimpleKeys.SAVE_DC_TYPE.getQuery()).ifPresent(spellSave::setOnSuccessfulSave);
        spellSaveData.getString(MCDNDSimpleKeys.SAVING_STAT.getQuery()).ifPresent(spellSave::setSavingStat);
        return spellSave;
    }

    @Override
    protected SpellType deserializeSpellType(DataContainer spellTypeData)
    {
        return spellTypeData.getString(MCDNDSimpleKeys.NAME.getQuery()).map(name ->
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
        return spellcasterClassData.getString(MCDNDSimpleKeys.NAME.getQuery()).map(name ->
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
        getDataContainerList(weaponsTabData, MCDNDSimpleKeys.MELEE_WEAPONS).ifPresent(list ->
                weaponsTab.setMeleeWeapons(list.stream().map(this::deserializeMeleeWeapon).collect(Collectors.toList())));
        getDataContainerList(weaponsTabData, MCDNDSimpleKeys.RANGED_WEAPONS).ifPresent(list ->
                weaponsTab.setRangedWeapons(list.stream().map(this::deserializeRangedWeapon).collect(Collectors.toList())));
        return weaponsTab;
    }

    @Override
    protected MeleeWeapon deserializeMeleeWeapon(DataContainer meleeWeaponData)
    {
        MeleeWeapon meleeWeapon = new MeleeWeapon();
        meleeWeaponData.getBoolean(MCDNDSimpleKeys.PLUS_STAT.getQuery()).ifPresent(meleeWeapon::setPlusStat);
        meleeWeaponData.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(meleeWeapon::setProficient);
        getDataContainer(meleeWeaponData, MCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data ->
                meleeWeapon.setCritDamageDice(deserializeDice(data)));
        getDataContainer(meleeWeaponData, MCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data ->
                meleeWeapon.setDamageDice(deserializeDice(data)));
        meleeWeaponData.getInt(MCDNDSimpleKeys.CRIT_MINIMUM.getQuery()).ifPresent(meleeWeapon::setCritMin);
        meleeWeaponData.getInt(MCDNDSimpleKeys.DAMAGE_BONUS.getQuery()).ifPresent(meleeWeapon::setDamageBonus);
        meleeWeaponData.getInt(MCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(meleeWeapon::setMagicBonus);
        meleeWeaponData.getInt(MCDNDSimpleKeys.TO_HIT.getQuery()).ifPresent(meleeWeapon::setToHit);
        meleeWeaponData.getString(MCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(meleeWeapon::setAttackStat);
        meleeWeaponData.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(meleeWeapon::setName);
        return meleeWeapon;
    }

    @Override
    protected RangedWeapon deserializeRangedWeapon(DataContainer rangedWeaponData)
    {
        RangedWeapon rangedWeapon = new RangedWeapon();
        rangedWeaponData.getInt(MCDNDSimpleKeys.PLUS_STAT.getQuery()).ifPresent(rangedWeapon::setAmmo);
        rangedWeaponData.getBoolean(MCDNDSimpleKeys.IS_PROFICIENT.getQuery()).ifPresent(rangedWeapon::setProficient);
        getDataContainer(rangedWeaponData, MCDNDSimpleKeys.CRIT_DAMAGE_DICE).ifPresent(data ->
                rangedWeapon.setCritDamageDice(deserializeDice(data)));
        getDataContainer(rangedWeaponData, MCDNDSimpleKeys.DAMAGE_DICE).ifPresent(data ->
                rangedWeapon.setDamageDice(deserializeDice(data)));
        rangedWeaponData.getInt(MCDNDSimpleKeys.CRIT_MINIMUM.getQuery()).ifPresent(rangedWeapon::setCritMin);
        rangedWeaponData.getInt(MCDNDSimpleKeys.DAMAGE_BONUS.getQuery()).ifPresent(rangedWeapon::setDamageBonus);
        rangedWeaponData.getInt(MCDNDSimpleKeys.MAGIC_BONUS.getQuery()).ifPresent(rangedWeapon::setMagicBonus);
        rangedWeaponData.getInt(MCDNDSimpleKeys.TO_HIT.getQuery()).ifPresent(rangedWeapon::setToHit);
        rangedWeaponData.getString(MCDNDSimpleKeys.ATTACK_STAT.getQuery()).ifPresent(rangedWeapon::setAttackStat);
        rangedWeaponData.getString(MCDNDSimpleKeys.NAME.getQuery()).ifPresent(rangedWeapon::setName);
        return rangedWeapon;
    }

    @Override
    protected PlayerSheet deserializePlayerSheet(DataContainer playerSheetData)
    {
        PlayerSheet playerSheet = new PlayerSheet();
        getDataContainer(playerSheetData, MCDNDSimpleKeys.ARMOR_TAB).ifPresent(armorTabData ->
                playerSheet.setArmorTab(deserializeArmorTab(armorTabData)));
        getDataContainer(playerSheetData, MCDNDSimpleKeys.BACKGROUND_TAB).ifPresent(backgroundTabData ->
                playerSheet.setBackgroundTab(deserializeBackgroundTab(backgroundTabData)));
        getDataContainer(playerSheetData, MCDNDSimpleKeys.CLASS_TAB).ifPresent(classTabData ->
                playerSheet.setClassTab(deserializeClassTab(classTabData)));
        getDataContainer(playerSheetData, MCDNDSimpleKeys.CORE_STATS_TAB).ifPresent(data ->
                playerSheet.setCoreStatsTab(deserializeCoreStatsTab(data)));
        getDataContainer(playerSheetData, MCDNDSimpleKeys.INVENTORY_TAB).ifPresent(data ->
                playerSheet.setInventoryTab(deserializeInventoryTab(data, playerSheet.getCoreStatsTab().getCoreStats().getStrength().getScore())));
        getDataContainer(playerSheetData, MCDNDSimpleKeys.SKILLS_TAB).ifPresent(data ->
                playerSheet.setSkillsTab(deserializeSkillsTab(data, playerSheet.getCoreStatsTab().getCoreStats())));
        getDataContainer(playerSheetData, MCDNDSimpleKeys.SPELL_BOOK_TAB).ifPresent(data ->
                playerSheet.setSpellbookTab(deserializeSpellbookTab(data, playerSheet.getClassTab().getClassLevels().getSorcerer())));
        getDataContainer(playerSheetData, MCDNDSimpleKeys.WEAPONS_TAB).ifPresent(data ->
                playerSheet.setWeaponsTab(deserializeWeaponsTab(data)));
        return playerSheet;
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
