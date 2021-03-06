package io.musician101.mcdndsimple.common.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActionType;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActions;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerHitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerSheet;
import io.musician101.mcdndsimple.common.character.nonplayer.TraitsBackground;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkills;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.DeathSavingThrows;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.player.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.character.player.clazz.GainedFrom;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.player.outputoption.CoreSkillsOutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.OutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.SavingThrowOutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.WeaponsSpellMiscOutputOptions;
import io.musician101.mcdndsimple.common.character.player.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.common.character.player.spell.Prepared;
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
import io.musician101.mcdndsimple.common.character.player.tab.SpellSlots;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import io.musician101.musicianlibrary.java.json.BaseSerializer;
import io.musician101.musicianlibrary.java.json.JsonKey;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.UUID;

public class Keys {

    public static final JsonKey<Dice> ABILITIES_AND_SKILLS = new JsonKey<>("abilities_and_skills", new Dice(1));
    public static final String ACROBATICS = "acrobatics";
    public static final JsonKey<Boolean> ACROBATICS_BOOLEAN = new JsonKey<>("acrobatics", false);
    public static final JsonKey<List<NonPlayerAction>> ACTIONS = new JsonKey<>("actions", new ArrayList<>());
    public static final JsonKey<Integer> AGE = new JsonKey<>("age", 0);
    public static final JsonKey<String> ALIGNMENT = new JsonKey<>("alignment", "");
    public static final JsonKey<Integer> AMMO = new JsonKey<>("ammo", 0);
    public static final JsonKey<Integer> AMOUNT = new JsonKey<>("amount", 0);
    public static final String ANIMAL_HANDLING = "animal_handling";
    public static final JsonKey<Boolean> ANIMAL_HANDLING_BOOLEAN = new JsonKey<>("animal_handling", false);
    public static final String ARCANA = "arcana";
    public static final JsonKey<Boolean> ARCANA_BOOLEAN = new JsonKey<>("arcana", false);
    public static final JsonKey<Integer> ARMOR_CLASS = new JsonKey<>("armor_class", 0);
    public static final JsonKey<String> ARMOR_CLASS_NOTE = new JsonKey<>("armor_class_note", "");
    public static final JsonKey<List<Armor>> ARMOR_LIST = new JsonKey<>("armor_list", new ArrayList<>());
    public static final JsonKey<List<String>> ARMOR_PROFICIENCIES = new JsonKey<>("armor_proficiencies", new ArrayList<>());
    public static final JsonKey<ArmorTab> ARMOR_TAB = new JsonKey<>("armor_tab", new ArmorTab());
    public static final JsonKey<ArmorType> ARMOR_TYPE = new JsonKey<>("armor_type", ArmorType.NONE);
    public static final String ATHLETICS = "athletics";
    public static final JsonKey<Boolean> ATHLETICS_BOOLEAN = new JsonKey<>("athletics", false);
    public static final JsonKey<Dice> ATTACK = new JsonKey<>("attack", new Dice(1));
    public static final JsonKey<Boolean> ATTACK_ROLL_ENABLED = new JsonKey<>("attack_roll_enabled", false);
    public static final JsonKey<WeaponAttackStat> WEAPON_ATTACK_STAT = new JsonKey<>("attack_stat", WeaponAttackStat.STR);
    public static final JsonKey<StatBonus> ATTACK_STAT = new JsonKey<>("attack_stat", StatBonus.STR);
    public static final JsonKey<List<String>> AT_HIGHER_LEVELS = new JsonKey<>("at_higher_levels", new ArrayList<>());
    public static final JsonKey<Boolean> AT_HIGHER_LEVELS_ENABLED = new JsonKey<>("at_higher_levels_enabled", false);
    public static final JsonKey<List<String>> BACKGROUND = new JsonKey<>("background", new ArrayList<>());
    public static final JsonKey<BackgroundTab> BACKGROUND_TAB = new JsonKey<>("background_tab", new BackgroundTab());
    public static final JsonKey<Integer> BARBARIAN = new JsonKey<>("barbarian", 0);
    public static final JsonKey<Integer> BARD = new JsonKey<>("bard", 0);
    public static final JsonKey<Integer> BASE_ARMOR_CLASS = new JsonKey<>("base_armor_class", 0);
    public static final JsonKey<List<String>> BIO = new JsonKey<>("bio", new ArrayList<>());
    public static final JsonKey<BioAndInfo> BIO_AND_INFO = new JsonKey<>("bio_and_info", new BioAndInfo());
    public static final JsonKey<List<String>> BONDS = new JsonKey<>("armor_proficiencies", new ArrayList<>());
    public static final JsonKey<Integer> BONUS = new JsonKey<>("bonus", 0);
    public static final JsonKey<Bonuses> BONUSES = new JsonKey<>("bonuses", new Bonuses());
    public static final JsonKey<Boolean> CAN_CRIT = new JsonKey<>("can_crit", false);
    public static final JsonKey<String> CAST_TIME = new JsonKey<>("cast_time", "");
    public static final JsonKey<Double> CHALLENGE_RATING = new JsonKey<>("challenge_rating", 0D);
    public static final JsonKey<CharacterSheet> CHARACTER_SHEET = new JsonKey<>("character_sheet", new CharacterSheet());
    public static final String CHARISMA = "charisma";
    public static final JsonKey<Boolean> CHARISMA_BOOLEAN = new JsonKey<>("charisma", false);
    public static final JsonKey<String> CLASS = new JsonKey<>("class", "");
    public static final JsonKey<List<ClassAction>> CLASS_ACTIONS = new JsonKey<>("class_actions", new ArrayList<>());
    public static final JsonKey<List<String>> CLASS_FEATURE_NOTES = new JsonKey<>("class_feature_notes", new ArrayList<>());
    public static final JsonKey<ClassLevels> CLASS_LEVELS = new JsonKey<>("class_levels", new ClassLevels());
    public static final JsonKey<List<ClassResource>> CLASS_RESOURCES = new JsonKey<>("class_resources", new ArrayList<>());
    public static final JsonKey<ClassTab> CLASS_TAB = new JsonKey<>("class_tab", new ClassTab());
    public static final JsonKey<Integer> CLERIC = new JsonKey<>("cleric", 0);
    public static final JsonKey<Integer> CLIMB_SPEED = new JsonKey<>("climb_speed", 0);
    public static final JsonKey<List<String>> COMPONENTS = new JsonKey<>("components", new ArrayList<>());
    public static final JsonKey<String> CONDITION_IMMUNITY = new JsonKey<>("condition_immunity", "");
    public static final String CONSTITUTION = "constitution";
    public static final JsonKey<Boolean> CONSTITUTION_BOOLEAN = new JsonKey<>("constitution", false);
    public static final JsonKey<List<UUID>> CONTROLLERS = new JsonKey<>("controllers", new ArrayList<>());
    public static final JsonKey<Integer> COPPER = new JsonKey<>("copper", 0);
    public static final JsonKey<CoreSkillsOutputOptions> CORE_SKILLS_OUTPUT_OPTIONS = new JsonKey<>("core_skills_output_options", new CoreSkillsOutputOptions());
    public static final JsonKey<CoreStatsTab> CORE_STATS_TAB = new JsonKey<>("core_stats_tab", new CoreStatsTab());
    public static final JsonKey<Dice> CRIT_DAMAGE = new JsonKey<>("crit_damage_dice", new Dice(1));
    public static final JsonKey<Integer> CRIT_MINIMUM = new JsonKey<>("crit_minimum", 0);
    public static final JsonKey<Integer> CURRENT = new JsonKey<>("current", 0);
    public static final JsonKey<Integer> CUSTOM_SAVE_DC = new JsonKey<>("custom_save_dc", 0);
    public static final JsonKey<Dice> DAMAGE = new JsonKey<>("damage", new Dice(1));
    public static final JsonKey<Boolean> DAMAGE_ENABLED = new JsonKey<>("damage_enabled", false);
    public static final JsonKey<String> DAMAGE_IMMUNITY = new JsonKey<>("damage_immunity", "");
    public static final JsonKey<String> DAMAGE_RESISTANCE = new JsonKey<>("damage_resistance", "");
    public static final JsonKey<String> DAMAGE_TYPE = new JsonKey<>("damage_type", "");
    public static final JsonKey<String> DAMAGE_VULNERABILITY = new JsonKey<>("damage_vulnerability", "");
    public static final JsonKey<Boolean> DEATH_BOOLEAN = new JsonKey<>("death", false);
    public static final JsonKey<DeathSavingThrows> DEATH_SAVING_THROWS = new JsonKey<>("death_saving_throws", new DeathSavingThrows());
    public static final String DECEPTION = "deception";
    public static final JsonKey<Boolean> DECEPTION_BOOLEAN = new JsonKey<>("deception", false);
    public static final JsonKey<List<String>> DESCRIPTION = new JsonKey<>("description", new ArrayList<>());
    public static final JsonKey<Boolean> DESCRIPTION_ENABLED = new JsonKey<>("description_enabled", false);
    public static final String DEXTERITY = "dexterity";
    public static final JsonKey<Boolean> DEXTERITY_BOOLEAN = new JsonKey<>("dexterity", false);
    public static final JsonKey<Boolean> DM_OUTPUT_ONLY = new JsonKey<>("dm_output_only", false);
    public static final JsonKey<Integer> DRUID = new JsonKey<>("druid", 0);
    public static final JsonKey<String> DURATION = new JsonKey<>("duration", "");
    public static final JsonKey<List<String>> EFFECT = new JsonKey<>("effect", new ArrayList<>());
    public static final JsonKey<List<String>> EFFECTS = new JsonKey<>("effects", new ArrayList<>());
    public static final JsonKey<Boolean> EFFECTS_ENABLED = new JsonKey<>("effects_enabled", false);
    public static final JsonKey<Integer> EIGHTH_LEVEL_SPELLS_USED = new JsonKey<>("eighth_level_spells_used", 0);
    public static final JsonKey<Integer> ELECTRUM = new JsonKey<>("electrum", 0);
    public static final JsonKey<Integer> EXPERIENCE = new JsonKey<>("experience", 0);
    public static final JsonKey<String> EYES = new JsonKey<>("eyes", "");
    public static final JsonKey<Integer> FAIL_COUNT = new JsonKey<>("fail_count", 0);
    public static final JsonKey<Integer> FIFTH_LEVEL_SPELLS_USED = new JsonKey<>("fifth_level_spells_used", 0);
    public static final JsonKey<Integer> FIGHTER = new JsonKey<>("fighter", 0);
    public static final JsonKey<Integer> FIRST_LEVEL_SPELLS_USED = new JsonKey<>("first_level_spells_used", 0);
    public static final JsonKey<List<String>> FLAWS = new JsonKey<>("flaws", new ArrayList<>());
    public static final JsonKey<Integer> FLY_SPEED = new JsonKey<>("fly_speed", 0);
    public static final JsonKey<Integer> FOURTH_LEVEL_SPELLS_USED = new JsonKey<>("fourth_level_spells_used", 0);
    public static final JsonKey<GainedFrom> GAINED_FROM = new JsonKey<>("gained_from", GainedFrom.BARBARIAN);
    public static final JsonKey<SpellcasterClass> GAINED_FROM_SPELL = new JsonKey<>("gained_from", SpellcasterClass.OTHER);
    public static final JsonKey<String> GENDER = new JsonKey<>("gender", "");
    public static final JsonKey<Integer> GOLD = new JsonKey<>("gold", 0);
    public static final JsonKey<String> HAIR = new JsonKey<>("hair", "");
    public static final JsonKey<Boolean> HEALING_ENABLED = new JsonKey<>("healing_enabled", false);
    public static final JsonKey<Dice> HEAL_AMOUNT = new JsonKey<>("heal_amount", new Dice(1));
    public static final JsonKey<String> HEIGHT = new JsonKey<>("height", "");
    public static final String HISTORY = "history";
    public static final JsonKey<Boolean> HISTORY_BOOLEAN = new JsonKey<>("history", false);
    public static final JsonKey<HitDice> HIT_DICE = new JsonKey<>("hit_dice", new HitDice());
    public static final JsonKey<Dice> HIT_DICE_NPC = new JsonKey<>("hit_dice", new Dice(1));
    public static final JsonKey<Boolean> HIT_DICE_BOOLEAN = new JsonKey<>("hit_dice", false);
    public static final JsonKey<HitPoints> HIT_POINTS = new JsonKey<>("hit_points", new HitPoints());
    public static final JsonKey<NonPlayerHitPoints> HIT_POINTS_NPC = new JsonKey<>("hit_points", new NonPlayerHitPoints());
    public static final JsonKey<List<String>> IDEALS = new JsonKey<>("ideals", new ArrayList<>());
    public static final JsonKey<Boolean> INFO_BLOCK_ENABLED = new JsonKey<>("info_block_enabled", false);
    public static final JsonKey<Integer> INITIATIVE_BONUS = new JsonKey<>("initiative_bonus", 0);
    public static final JsonKey<Boolean> INITIATIVE_BOOLEAN = new JsonKey<>("initiative", false);
    public static final String INSIGHT = "insight";
    public static final JsonKey<Boolean> INSIGHT_BOOLEAN = new JsonKey<>("insight", false);
    public static final JsonKey<Boolean> INSPIRATION = new JsonKey<>("inspiration", false);
    public static final String INTELLIGENCE = "intelligence";
    public static final JsonKey<Boolean> INTELLIGENCE_BOOLEAN = new JsonKey<>("intelligence", false);
    public static final String INTIMIDATION = "intimidation";
    public static final JsonKey<Boolean> INTIMIDATION_BOOLEAN = new JsonKey<>("intimidation", false);
    public static final JsonKey<List<MCDNDItem>> INVENTORY = new JsonKey<>("inventory", new ArrayList<>());
    public static final JsonKey<List<String>> INVENTORY_NOTES = new JsonKey<>("inventory_notes", new ArrayList<>());
    public static final JsonKey<InventoryTab> INVENTORY_TAB = new JsonKey<>("inventory_tab", new InventoryTab());
    public static final String INVESTIGATION = "investigation";
    public static final JsonKey<Boolean> INVESTIGATION_BOOLEAN = new JsonKey<>("investigation", false);
    public static final JsonKey<Boolean> IS_CARRIED = new JsonKey<>("is_carried", false);
    public static final JsonKey<Boolean> IS_MULTI_ATTACK = new JsonKey<>("is_multi_attack", false);
    public static final JsonKey<Boolean> IS_PROFICIENT = new JsonKey<>("is_proficient", false);
    public static final JsonKey<Boolean> IS_RITUAL = new JsonKey<>("is_ritual", false);
    public static final JsonKey<List<String>> LANGUAGES = new JsonKey<>("languages", new ArrayList<>());
    public static final JsonKey<Integer> LEVEL = new JsonKey<>("level", 0);
    public static final JsonKey<MacroOptions> MACRO_OPTIONS = new JsonKey<>("macro_options", new MacroOptions());
    public static final JsonKey<Integer> MAGIC_BONUS = new JsonKey<>("magic_bonus", 0);
    public static final JsonKey<Integer> MAXIMUM = new JsonKey<>("maximum", 0);
    public static final JsonKey<Integer> MAX_USES = new JsonKey<>("max_uses", 0);
    public static final String MEDICINE = "medicine";
    public static final JsonKey<Boolean> MEDICINE_BOOLEAN = new JsonKey<>("medicine", false);
    public static final JsonKey<MeleeBonus> MELEE_BONUS = new JsonKey<>("melee_bonus", new MeleeBonus());
    public static final JsonKey<List<MeleeWeapon>> MELEE_WEAPONS = new JsonKey<>("melee_weapons", new ArrayList<>());
    public static final JsonKey<Boolean> MELEE_WEAPONS_BOOLEAN = new JsonKey<>("melee_weapons", false);
    public static final JsonKey<Integer> MONK = new JsonKey<>("monk", 0);
    public static final JsonKey<String> MULTI_ATTACK = new JsonKey<>("multi_attack", "");
    public static final JsonKey<String> NAME = new JsonKey<>("name", "");
    public static final String NATURE = "nature";
    public static final JsonKey<Boolean> NATURE_BOOLEAN = new JsonKey<>("nature", false);
    public static final JsonKey<Boolean> NEEDS_CONCENTRATION = new JsonKey<>("needs_concentration", false);
    public static final JsonKey<Integer> NINTH_LEVEL_SPELLS_USED = new JsonKey<>("ninth_level_spells_used", 0);
    public static final JsonKey<NonPlayerActions> NON_PLAYER_ACTIONS = new JsonKey<>("non_player_actions", new NonPlayerActions());
    public static final JsonKey<NonPlayerActionType> NON_PLAYER_ACTION_TYPE = new JsonKey<>("non_player_action_type", NonPlayerActionType.OTHER);
    public static final JsonKey<NonPlayerSheet> NON_PLAYER_SHEET = new JsonKey<>("non_player_sheet", new NonPlayerSheet());
    public static final JsonKey<NonPlayerSkills> NON_PLAYER_SKILLS = new JsonKey<>("non_player_skills", new NonPlayerSkills());
    public static final JsonKey<List<String>> ON_SUCCESSFUL_SAVE = new JsonKey<>("on_successful_save", new ArrayList<>());
    public static final JsonKey<List<String>> OTHER_NOTES = new JsonKey<>("other_notes", new ArrayList<>());
    public static final JsonKey<List<String>> OUTPUT = new JsonKey<>("output", new ArrayList<>());
    public static final JsonKey<OutputOptions> OUTPUT_OPTIONS = new JsonKey<>("output_options", new OutputOptions());
    public static final JsonKey<Integer> PALADIN = new JsonKey<>("paladin", 0);
    public static final String PERCEPTION = "perception";
    public static final JsonKey<Boolean> PERCEPTION_BOOLEAN = new JsonKey<>("perception", false);
    public static final String PERFORMANCE = "performance";
    public static final JsonKey<List<String>> PERSONALITY_TRAITS = new JsonKey<>("personality_traits", new ArrayList<>());
    public static final String PERSUASION = "persuasion";
    public static final JsonKey<Boolean> PERSUASION_BOOLEAN = new JsonKey<>("persuasion", false);
    public static final JsonKey<Integer> PLATINUM = new JsonKey<>("platinum", 0);
    public static final JsonKey<List<MCDNDPlayer>> PLAYER_SHEETS = new JsonKey<>("player_sheets", new ArrayList<>());
    public static final JsonKey<Boolean> PLUS_STAT = new JsonKey<>("plus_stat", false);
    public static final JsonKey<Prepared> PREPARED = new JsonKey<>("prepared", Prepared.NO);
    public static final JsonKey<Integer> QUANTITY = new JsonKey<>("quantity", 0);
    public static final JsonKey<String> RACE = new JsonKey<>("race", "");
    public static final JsonKey<List<String>> RACIAL_TRAITS = new JsonKey<>("racial_traits", new ArrayList<>());
    public static final JsonKey<String> RANGE = new JsonKey<>("range", "");
    public static final JsonKey<RangedBonus> RANGED_BONUS = new JsonKey<>("ranged_bonus", new RangedBonus());
    public static final JsonKey<List<RangedWeapon>> RANGED_WEAPONS = new JsonKey<>("ranged_weapons", new ArrayList<>());
    public static final JsonKey<Boolean> RANGED_WEAPONS_BOOLEAN = new JsonKey<>("ranged_weapons", false);
    public static final JsonKey<Integer> RANGER = new JsonKey<>("ranger", 0);
    public static final String RELIGION = "religion";
    public static final JsonKey<Boolean> RELIGION_BOOLEAN = new JsonKey<>("religion", false);
    public static final JsonKey<Integer> ROGUE = new JsonKey<>("rogue", 0);
    public static final JsonKey<Dice> SAVES = new JsonKey<>("saves", new Dice(1));
    public static final JsonKey<Dice> SAVE_DC = new JsonKey<>("save_dc", new Dice(1));
    public static final JsonKey<Boolean> SAVING_THROW_ENABLED = new JsonKey<>("saving_throw_enabled", false);
    public static final JsonKey<SavingThrowOutputOptions> SAVING_THROW_OUTPUT_OPTIONS = new JsonKey<>("saving_throw_output_options", new SavingThrowOutputOptions());
    public static final JsonKey<Integer> SCORE = new JsonKey<>("score", 0);
    public static final JsonKey<Boolean> SECOND_ATTACK_ROLL_ENABLED = new JsonKey<>("second_attack_roll_enabled", false);
    public static final JsonKey<Integer> SECOND_LEVEL_SPELLS_USED = new JsonKey<>("second_level_spells_used", 0);
    public static final JsonKey<List<String>> SENSES = new JsonKey<>("senses", new ArrayList<>());
    public static final JsonKey<Integer> SEVENTH_LEVEL_SPELLS_USED = new JsonKey<>("seventh_level_spells_used", 0);
    public static final JsonKey<String> SHORT_NAME = new JsonKey<>("short_name", "");
    public static final JsonKey<Integer> SIDES = new JsonKey<>("sides", 0);
    public static final JsonKey<Integer> SILVER = new JsonKey<>("silver", 0);
    public static final JsonKey<Integer> SIXTH_LEVEL_SPELLS_USED = new JsonKey<>("sixth_level_spells_used", 0);
    public static final JsonKey<String> SIZE = new JsonKey<>("size", "");
    public static final JsonKey<SkillsTab> SKILLS_TAB = new JsonKey<>("skills_tab", new SkillsTab());
    public static final JsonKey<SkillProficiency> SKILL_PROFICIENCY = new JsonKey<>("skill_proficiency", SkillProficiency.NONE);
    public static final String SLEIGHT_OF_HAND = "sleight_of_hand";
    public static final JsonKey<Boolean> SLEIGHT_OF_HAND_BOOLEAN = new JsonKey<>("sleight_of_hand", false);
    public static final JsonKey<Integer> SORCERER = new JsonKey<>("sorcerer", 0);
    public static final JsonKey<Integer> SORCERY_POINTS_USED = new JsonKey<>("sorcery_points_used", 0);
    public static final JsonKey<Integer> SPEED = new JsonKey<>("speed", 0);
    public static final JsonKey<Boolean> SPEED_PENALTY = new JsonKey<>("speed_penalty", false);
    public static final JsonKey<SpellbookTab> SPELLBOOK_TAB = new JsonKey<>("spellbook_tab", new SpellbookTab());
    public static final JsonKey<SpellcasterClass> SPELLCASTER_CLASS = new JsonKey<>("spellcaster_class", SpellcasterClass.OTHER);
    public static final JsonKey<SpellcastingBonus> SPELLCASTING_BONUS = new JsonKey<>("spellcasting_bonus", new SpellcastingBonus());
    public static final JsonKey<List<Spell>> SPELLS = new JsonKey<>("spells", new ArrayList<>());
    public static final JsonKey<Boolean> SPELL_CAST_BOOLEAN = new JsonKey<>("spell_cast", false);
    public static final JsonKey<SpellDamage> SPELL_DAMAGE = new JsonKey<>("spell_damage", new SpellDamage());
    public static final JsonKey<SpellHealing> SPELL_HEALING = new JsonKey<>("spell_healing", new SpellHealing());
    public static final JsonKey<Boolean> SPELL_INFO_BOOLEAN = new JsonKey<>("spell_info", false);
    public static final JsonKey<SpellSave> SPELL_SAVE = new JsonKey<>("spell_save", new SpellSave());
    public static final JsonKey<SpellSlots> SPELL_SLOTS = new JsonKey<>("spell_slots", new SpellSlots());
    public static final JsonKey<SpellType> SPELL_TYPE = new JsonKey<>("spell_type", SpellType.OTHER);
    public static final JsonKey<StatBonus> STAT_BONUS = new JsonKey<>("stat_bonus", StatBonus.NONE);
    public static final String STEALTH = "stealth";
    public static final JsonKey<Boolean> STEALTH_PENALTY = new JsonKey<>("stealth_penalty", false);
    public static final String STRENGTH = "strength";
    public static final JsonKey<Boolean> STRENGTH_BOOLEAN = new JsonKey<>("strength", false);
    public static final JsonKey<Integer> SUCCESS_COUNT = new JsonKey<>("success_count", 0);
    public static final String SURVIVAL = "survival";
    public static final JsonKey<Boolean> SURVIVAL_BOOLEAN = new JsonKey<>("survival", false);
    public static final JsonKey<Integer> SWIM_SPEED = new JsonKey<>("swim_speed", 0);
    public static final JsonKey<String> TARGET_AREA = new JsonKey<>("target_area", "");
    public static final JsonKey<Integer> TEMPORARY = new JsonKey<>("temporary", 0);
    public static final JsonKey<Integer> THIRD_LEVEL_SPELLS_USED = new JsonKey<>("third_level_spells_used", 0);
    public static final JsonKey<List<String>> TOOL_PROFICIENCIES = new JsonKey<>("tool_proficiencies", new ArrayList<>());
    public static final JsonKey<List<String>> TRAITS = new JsonKey<>("traits", new ArrayList<>());
    public static final JsonKey<TraitsBackground> TRAITS_BACKGROUND = new JsonKey<>("traits_background", new TraitsBackground());
    public static final JsonKey<String> TYPE_RACE = new JsonKey<>("type_race", "");
    public static final JsonKey<Boolean> UNARMORED = new JsonKey<>("unarmored", false);
    public static final JsonKey<Integer> UNARMORED_ARMOR_CLASS = new JsonKey<>("unarmored_armor_class", 0);
    public static final JsonKey<UnarmoredBonus> UNARMORED_BONUS = new JsonKey<>("unarmored_bonus", UnarmoredBonus.NONE);
    public static final String UNSKILLED_CHA = "unskilled_cha";
    public static final String UNSKILLED_CON = "unskilled_con";
    public static final String UNSKILLED_DEX = "unskilled_dex";
    public static final String UNSKILLED_INT = "unskilled_int";
    public static final String UNSKILLED_STR = "unskilled_str";
    public static final String UNSKILLED_WIS = "unskilled_wis";
    public static final JsonKey<Integer> USED = new JsonKey<>("used", 0);
    public static final JsonKey<String> VISION = new JsonKey<>("vision", "");
    public static final JsonKey<Integer> WARLOCK = new JsonKey<>("warlock", 0);
    public static final JsonKey<Integer> WARLOCK_SPELL_SLOTS_USED = new JsonKey<>("warlock_spell_slots_used", 0);
    public static final JsonKey<Wealth> WEALTH = new JsonKey<>("wealth", new Wealth());
    public static final JsonKey<WeaponsSpellMiscOutputOptions> WEAPONS_SPELL_MISC_OUTPUT_OPTIONS = new JsonKey<>("weapons_spell_misc_output_options", new WeaponsSpellMiscOutputOptions());
    public static final JsonKey<WeaponsTab> WEAPONS_TAB = new JsonKey<>("weapons_tab", new WeaponsTab());
    public static final JsonKey<List<String>> WEAPON_PROFICIENCIES = new JsonKey<>("weapon_proficiencies", new ArrayList<>());
    public static final String WEIGHT = "weight";
    public static final JsonKey<Double> WEIGHT_DOUBLE = new JsonKey<>("weight", 0D);
    public static final JsonKey<Double> WEIGHT_OTHER = new JsonKey<>("weight_other", 0D);
    public static final String WISDOM = "wisdom";
    public static final JsonKey<Boolean> WISDOM_BOOLEAN = new JsonKey<>("wisdom", false);
    public static final JsonKey<Integer> WIZARD = new JsonKey<>("wizard", 0);
    public static final JsonKey<Boolean> WORN = new JsonKey<>("worn", false);
    public static final JsonKey<Integer> XP = new JsonKey<>("xp", 0);
    private static final String CORE_STATS = "core_stats";
    public static final JsonKey<CoreStats<NonPlayerAbilityScore>> NON_PLAYER_CORE_STATS = new JsonKey<>(CORE_STATS, new CoreStats<>(NonPlayerAbilityScore.class));
    public static final JsonKey<CoreStats<PlayerAbilityScore>> PLAYER_CORE_STATS = new JsonKey<>(CORE_STATS, new CoreStats<>(PlayerAbilityScore.class));
    public static final Gson GSON;

    static {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        @SuppressWarnings("rawtypes") ServiceLoader<BaseSerializer> loader = ServiceLoader.load(BaseSerializer.class);
        loader.forEach(b -> {
            try {
                builder.registerTypeAdapter(Class.forName(b.getClass().getName()), b);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        GSON = builder.create();
    }

    private Keys() {

    }
}
