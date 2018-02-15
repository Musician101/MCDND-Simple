package io.musician101.mcdndsimple.common.serialization;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.musicianlibrary.java.json.JsonKeyCatalog;
import io.musician101.musicianlibrary.java.json.JsonKeyImpl;
import io.musician101.musicianlibrary.java.json.adapter.list.ObjectListSerializer;
import io.musician101.musicianlibrary.java.json.adapter.list.StringListSerializer;
import java.util.List;
import java.util.UUID;

import static io.musician101.musicianlibrary.java.json.JsonKeyImpl.booleanKey;
import static io.musician101.musicianlibrary.java.json.JsonKeyImpl.doubleKey;
import static io.musician101.musicianlibrary.java.json.JsonKeyImpl.integerKey;
import static io.musician101.musicianlibrary.java.json.JsonKeyImpl.listKey;
import static io.musician101.musicianlibrary.java.json.JsonKeyImpl.stringKey;

//TODO left off here, changing keys so they have a serializer and deserializer
@JsonKeyCatalog
public class Keys {

    public static final String ABILITIES_AND_SKILLS = "abilities_and_skills";
    public static final JsonKeyImpl<JsonPrimitive, Integer> AGE = integerKey("age");
    public static final JsonKeyImpl<JsonPrimitive, String> ALIGNMENT = stringKey("alignment");
    public static final JsonKeyImpl<JsonPrimitive, Integer> ARMOR_CLASS = integerKey("armor_class");
    public static final JsonKeyImpl<JsonArray, List<Armor>> ARMOR_LIST = listKey("armor_list", new ObjectListSerializer<>());
    public static final JsonKeyImpl<JsonArray, List<String>> ARMOR_PROFICIENCIES = listKey("armor_proficiencies", new StringListSerializer());
    public static final String ARMOR_TAB = "armor_tab";
    public static final String ARMOR_TYPE = "armor_type";
    public static final JsonKeyImpl<JsonArray, List<String>> BACKGROUND = listKey("background", new StringListSerializer());
    public static final String BACKGROUND_TAB = "background_tab";
    public static final JsonKeyImpl<JsonPrimitive, Integer> BASE_ARMOR_CLASS = integerKey("base_armor_class");
    public static final JsonKeyImpl<JsonArray, List<String>> BIO = listKey("bio", new StringListSerializer());
    public static final String BIO_AND_INFO = "bio_and_info";
    public static final JsonKeyImpl<JsonArray, List<String>> BONDS = listKey("armor_proficiencies", new StringListSerializer());
    public static final String CHARACTER_SHEET = "character_sheet";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> CHARISMA_BOOLEAN = booleanKey("charisma");
    public static final JsonKeyImpl<JsonPrimitive, String> CLASS = stringKey("class");
    public static final String CLASS_TAB = "class_tab";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> CONSTITUTION_BOOLEAN = booleanKey("constitution");
    public static final String CORE_SKILLS_OUTPUT_OPTIONS = "core_skills_output_options";
    public static final String CORE_STATS_TAB = "core_stats_tab";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> DEATH_BOOLEAN = booleanKey("death");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> DEXTERITY_BOOLEAN = booleanKey("dexterity");
    public static final JsonKeyImpl<JsonPrimitive, String> EYES = stringKey("eyes");
    public static final JsonKeyImpl<JsonArray, List<String>> FLAWS = listKey("flaws", new StringListSerializer());
    public static final String GAINED_FROM = "gained_from";
    public static final JsonKeyImpl<JsonPrimitive, String> GENDER = stringKey("gender");
    public static final JsonKeyImpl<JsonPrimitive, String> HAIR = stringKey("hair");
    public static final JsonKeyImpl<JsonPrimitive, String> HEIGHT = stringKey("height");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> HIT_DICE_BOOLEAN = booleanKey("hit_dice");
    public static final JsonKeyImpl<JsonArray, List<String>> IDEALS = listKey("ideals", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, Boolean> INITIATIVE_BOOLEAN = booleanKey("initiative");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> INTELLIGENCE_BOOLEAN = booleanKey("intelligence");
    public static final String INVENTORY_TAB = "inventory_tab";
    public static final JsonKeyImpl<JsonArray, List<String>> LANGUAGES = listKey("languages", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, Integer> MAGIC_BONUS = integerKey("magic_bonus");
    public static final JsonKeyImpl<JsonPrimitive, Integer> MAX_USES = integerKey("max_uses");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> MELEE_WEAPONS_BOOLEAN = booleanKey("melee_weapons");
    public static final JsonKeyImpl<JsonPrimitive, String> NAME = stringKey("name");
    public static final JsonKeyImpl<JsonArray, List<String>> OTHER_NOTES = listKey("other_notes", new StringListSerializer());
    public static final JsonKeyImpl<JsonArray, List<String>> OUTPUT = listKey("output", new StringListSerializer());
    public static final String OUTPUT_OPTIONS = "output_options";
    public static final JsonKeyImpl<JsonArray, List<String>> PERSONALITY_TRAITS = listKey("personality_traits", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, String> RACE = stringKey("race");
    public static final JsonKeyImpl<JsonArray, List<String>> RACIAL_TRAITS = listKey("racial_traits", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, Boolean> RANGED_WEAPONS_BOOLEAN = booleanKey("ranged_weapons");
    public static final String SAVING_THROW_OUTPUT_OPTIONS = "saving_throw_output_options";
    public static final JsonKeyImpl<JsonPrimitive, String> SIZE = stringKey("size");
    public static final String SKILLS_TAB = "skills_tab";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> SPEED_PENALTY = booleanKey("speed_penalty");
    public static final String SPELLBOOK_TAB = "spellbook_tab";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> SPELL_CAST_BOOLEAN = booleanKey("spell_cast");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> SPELL_INFO_BOOLEAN = booleanKey("spell_info");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> STEALTH_PENALTY = booleanKey("stealth_penalty");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> STRENGTH_BOOLEAN = booleanKey("strength");
    public static final JsonKeyImpl<JsonArray, List<String>> TOOL_PROFICIENCIES = listKey("tool_proficiencies", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, Boolean> UNARMORED = booleanKey("unarmored");
    public static final JsonKeyImpl<JsonPrimitive, Integer> UNARMORED_ARMOR_CLASS = integerKey("unarmored_armor_class");
    public static final String UNARMORED_BONUS = "unarmored_bonus";
    public static final JsonKeyImpl<JsonPrimitive, Integer> USED = integerKey("used");
    public static final JsonKeyImpl<JsonPrimitive, String> VISION = stringKey("vision");
    public static final String WEAPONS_SPELL_MISC_OUTPUT_OPTIONS = "weapons_spell_misc_output_options";
    public static final String WEAPONS_TAB = "weapons_tab";
    public static final JsonKeyImpl<JsonArray, List<String>> WEAPON_PROFICIENCIES = listKey("weapon_proficiencies", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, Double> WEIGHT_DOUBLE = doubleKey("weight");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> WISDOM_BOOLEAN = booleanKey("wisdom");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> WORN = booleanKey("worn");
    public static final JsonKeyImpl<JsonArray, List<ClassAction>> CLASS_ACTIONS = listKey("class_actions", new ObjectListSerializer<>());
    public static final JsonKeyImpl<JsonArray, List<String>> CLASS_FEATURE_NOTES = listKey("class_feature_notes", new StringListSerializer());
    public static final String CLASS_LEVELS = "class_levels";
    public static final JsonKeyImpl<JsonPrimitive, Integer> BARBARIAN = integerKey("barbarian");
    public static final JsonKeyImpl<JsonPrimitive, Integer> BARD = integerKey("bard");
    public static final JsonKeyImpl<JsonPrimitive, Integer> CLERIC = integerKey("cleric");
    public static final JsonKeyImpl<JsonPrimitive, Integer> DRUID = integerKey("druid");
    public static final JsonKeyImpl<JsonPrimitive, Integer> FIGHTER = integerKey("fighter");
    public static final JsonKeyImpl<JsonPrimitive, Integer> MONK = integerKey("monk");
    public static final JsonKeyImpl<JsonPrimitive, Integer> PALADIN = integerKey("paladin");
    public static final JsonKeyImpl<JsonPrimitive, Integer> RANGER = integerKey("ranger");
    public static final JsonKeyImpl<JsonPrimitive, Integer> ROGUE = integerKey("rogue");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SORCERER = integerKey("sorcerer");
    public static final JsonKeyImpl<JsonPrimitive, Integer> WARLOCK = integerKey("warlock");
    public static final JsonKeyImpl<JsonPrimitive, Integer> WIZARD = integerKey("wizard");
    public static final JsonKeyImpl<JsonArray, List<ClassResource>> CLASS_RESOURCES = listKey("class_resources", new ObjectListSerializer<>());
    public static final String BONUSES = "bonuses";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> INSPIRATION = booleanKey("inspiration");
    public static final String CORE_STATS = "core_stats";
    public static final JsonKeyImpl<JsonPrimitive, Integer> EXPERIENCE = integerKey("experience");
    public static final String HIT_DICE = "hit_dice";
    public static final String HIT_POINTS = "hit_points";
    public static final JsonKeyImpl<JsonPrimitive, Integer> INITIATIVE_BONUS = integerKey("initiative_bonus");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SPEED = integerKey("speed");
    public static final String SAVES = "saves";
    public static final String MELEE_BONUS = "melee_bonus";
    public static final String RANGED_BONUS = "ranged_bonus";
    public static final String SPELLCASTING_BONUS = "spellcasting_bonus";
    public static final String ATTACK = "attack";
    public static final String DAMAGE = "damage";
    public static final String SAVE_DC = "save_dc";
    public static final String CHARISMA = "charisma";
    public static final String CONSTITUTION = "constitution";
    public static final String DEXTERITY = "dexterity";
    public static final String INTELLIGENCE = "intelligence";
    public static final String STRENGTH = "strength";
    public static final String WISDOM = "wisdom";
    public static final JsonKeyImpl<JsonPrimitive, String> SHORT_NAME = stringKey("short_name");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SCORE = integerKey("score");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> IS_PROFICIENT = booleanKey("is_proficient");
    public static final JsonKeyImpl<JsonPrimitive, Integer> CURRENT = integerKey("current");
    public static final JsonKeyImpl<JsonPrimitive, Integer> MAXIMUM = integerKey("maximum");
    public static final JsonKeyImpl<JsonPrimitive, Integer> TEMPORARY = integerKey("temporary");
    public static final JsonKeyImpl<JsonArray, List<MCDNDItem>> INVENTORY = listKey("inventory", new ObjectListSerializer<>());
    public static final JsonKeyImpl<JsonArray, List<String>> INVENTORY_NOTES = listKey("inventory_notes", new StringListSerializer());
    public static final String WEALTH = "wealth";
    public static final String WEIGHT = "weight";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> IS_CARRIED = booleanKey("is_carried");
    public static final JsonKeyImpl<JsonPrimitive, Integer> QUANTITY = integerKey("quantity");
    public static final JsonKeyImpl<JsonArray, List<String>> DESCRIPTION = listKey("description", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, Boolean> ACROBATICS_BOOLEAN = booleanKey("acrobatics");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> ANIMAL_HANDLING_BOOLEAN = booleanKey("animal_handling");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> ARCANA_BOOLEAN = booleanKey("arcana");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> ATHLETICS_BOOLEAN = booleanKey("athletics");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> DECEPTION_BOOLEAN = booleanKey("deception");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> HISTORY_BOOLEAN = booleanKey("history");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> INSIGHT_BOOLEAN = booleanKey("insight");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> INTIMIDATION_BOOLEAN = booleanKey("intimidation");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> INVESTIGATION_BOOLEAN = booleanKey("investigation");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> MEDICINE_BOOLEAN = booleanKey("medicine");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> NATURE_BOOLEAN = booleanKey("nature");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> PERCEPTION_BOOLEAN = booleanKey("perception");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> PERSUASION_BOOLEAN = booleanKey("persuasion");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> RELIGION_BOOLEAN = booleanKey("religion");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> SLEIGHT_OF_HAND_BOOLEAN = booleanKey("sleight_of_hand");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> SURVIVAL_BOOLEAN = booleanKey("survival");
    public static final String ACROBATICS = "acrobatics";
    public static final String ANIMAL_HANDLING = "animal_handling";
    public static final String ARCANA = "arcana";
    public static final String ATHLETICS = "athletics";
    public static final String DECEPTION = "deception";
    public static final String HISTORY = "history";
    public static final String INSIGHT = "insight";
    public static final String INTIMIDATION = "intimidation";
    public static final String INVESTIGATION = "investigation";
    public static final String MEDICINE = "medicine";
    public static final String NATURE = "nature";
    public static final String PERCEPTION = "perception";
    public static final String PERFORMANCE = "performance";
    public static final String PERSUASION = "persuasion";
    public static final String RELIGION = "religion";
    public static final String SLEIGHT_OF_HAND = "sleight_of_hand";
    public static final String STEALTH = "stealth";
    public static final String SURVIVAL = "survival";
    public static final String UNSKILLED_CHA = "unskilled_cha";
    public static final String UNSKILLED_CON = "unskilled_con";
    public static final String UNSKILLED_DEX = "unskilled_dex";
    public static final String UNSKILLED_INT = "unskilled_int";
    public static final String UNSKILLED_STR = "unskilled_str";
    public static final String UNSKILLED_WIS = "unskilled_wis";
    public static final JsonKeyImpl<JsonPrimitive, Integer> BONUS = integerKey("bonus");
    public static final String SKILL_PROFICIENCY = "skill_proficiency";
    public static final JsonKeyImpl<JsonPrimitive, Integer> FIRST_LEVEL_SPELLS_USED = integerKey("first_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SECOND_LEVEL_SPELLS_USED = integerKey("second_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> THIRD_LEVEL_SPELLS_USED = integerKey("third_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> FOURTH_LEVEL_SPELLS_USED = integerKey("fourth_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> FIFTH_LEVEL_SPELLS_USED = integerKey("fifth_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SIXTH_LEVEL_SPELLS_USED = integerKey("sixth_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SEVENTH_LEVEL_SPELLS_USED = integerKey("seventh_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> EIGHTH_LEVEL_SPELLS_USED = integerKey("eighth_level_spells_used");
    public static final JsonKeyImpl<JsonPrimitive, Integer> NINTH_LEVEL_SPELLS_USED = integerKey("ninth_level_spells_used");
    public static final String SPELLCASTER_CLASS = "spellcaster_class";
    public static final String PREPARED = "prepared";
    public static final String MACRO_OPTIONS = "macro_options";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> AT_HIGHER_LEVELS_ENABLED = booleanKey("at_higher_levels_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> DAMAGE_ENABLED = booleanKey("damage_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> DESCRIPTION_ENABLED = booleanKey("description_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> EFFECTS_ENABLED = booleanKey("effects_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> ATTACK_ROLL_ENABLED = booleanKey("attack_roll_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> HEALING_ENABLED = booleanKey("healing_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> INFO_BLOCK_ENABLED = booleanKey("info_block_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> SAVING_THROW_ENABLED = booleanKey("saving_throw_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> SECOND_ATTACK_ROLL_ENABLED = booleanKey("second_attack_roll_enabled");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> CAN_CRIT = booleanKey("can_crit");
    public static final String SPELL_DAMAGE = "spell_damage";
    public static final JsonKeyImpl<JsonPrimitive, String> DAMAGE_TYPE = stringKey("damage_type");
    public static final String HEAL_AMOUNT = "heal_amount";
    public static final String STAT_BONUS = "stat_bonus";
    public static final String SPELL_SAVE = "spell_save";
    public static final JsonKeyImpl<JsonPrimitive, Integer> CUSTOM_SAVE_DC = integerKey("custom_save_dc");
    public static final JsonKeyImpl<JsonArray, List<String>> ON_SUCCESSFUL_SAVE = listKey("on_successful_save", new StringListSerializer());
    public static final String SPELL_TYPE = "spell_type";
    public static final JsonKeyImpl<JsonArray, List<String>> AT_HIGHER_LEVELS = listKey("at_higher_levels", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, String> CAST_TIME = stringKey("cast_time");
    public static final JsonKeyImpl<JsonArray, List<String>> COMPONENTS = listKey("components", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, String> DURATION = stringKey("duration");
    public static final JsonKeyImpl<JsonArray, List<String>> EFFECTS = listKey("effects", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, Boolean> IS_RITUAL = booleanKey("is_ritual");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> NEEDS_CONCENTRATION = booleanKey("needs_concentration");
    public static final JsonKeyImpl<JsonPrimitive, Integer> LEVEL = integerKey("level");
    public static final JsonKeyImpl<JsonPrimitive, String> RANGE = stringKey("range");
    public static final String SPELL_HEALING = "spell_healing";
    public static final JsonKeyImpl<JsonPrimitive, String> TARGET_AREA = stringKey("target_area");
    public static final String ATTACK_STAT = "attack_stat";
    public static final JsonKeyImpl<JsonPrimitive, Integer> SORCERY_POINTS_USED = integerKey("sorcery_points_used");
    public static final String SPELL_SLOTS = "spell_slots";
    public static final JsonKeyImpl<JsonArray, List<Spell>> SPELLS = listKey("spells", new ObjectListSerializer<>());
    public static final JsonKeyImpl<JsonPrimitive, Integer> WARLOCK_SPELL_SLOTS_USED = integerKey("warlock_spell_slots_used");
    public static final String CRIT_DAMAGE = "crit_damage_dice";
    public static final JsonKeyImpl<JsonPrimitive, Integer> CRIT_MINIMUM = integerKey("crit_minimum");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> PLUS_STAT = booleanKey("plus_stat");
    public static final JsonKeyImpl<JsonPrimitive, Integer> AMMO = integerKey("ammo");
    public static final JsonKeyImpl<JsonArray, List<MeleeWeapon>> MELEE_WEAPONS = listKey("melee_weapons", new ObjectListSerializer<>());
    public static final JsonKeyImpl<JsonArray, List<RangedWeapon>> RANGED_WEAPONS = listKey("ranged_weapons", new ObjectListSerializer<>());
    public static final JsonKeyImpl<JsonArray, List<PlayerSheet>> PLAYER_SHEETS = listKey("player_sheets", new ObjectListSerializer<>());
    public static final String NON_PLAYER_SHEET = "non_player_sheets";
    public static final JsonKeyImpl<JsonArray, List<UUID>> CONTROLLERS = listKey("controllers", new ObjectListSerializer<>());
    public static final String NON_PLAYER_ACTIONS = "non_player_actions";
    public static final String NON_PLAYER_ACTION_TYPE = "non_player_action_type";
    public static final JsonKeyImpl<JsonPrimitive, Boolean> IS_MULTI_ATTACK = booleanKey("is_multi_attack");
    public static final JsonKeyImpl<JsonArray, List<String>> EFFECT = listKey("effect", new StringListSerializer());
    public static final JsonKeyImpl<JsonArray, List<NonPlayerAction>> ACTIONS = listKey("actions", new ObjectListSerializer<>());
    public static final JsonKeyImpl<JsonPrimitive, String> MULTI_ATTACK = stringKey("multi_attack");
    public static final JsonKeyImpl<JsonPrimitive, Integer> COPPER = integerKey("copper");
    public static final JsonKeyImpl<JsonPrimitive, Integer> ELECTRUM = integerKey("electrum");
    public static final JsonKeyImpl<JsonPrimitive, Integer> GOLD = integerKey("gold");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SILVER = integerKey("silver");
    public static final JsonKeyImpl<JsonPrimitive, Integer> PLATINUM = integerKey("platinum");
    public static final JsonKeyImpl<JsonPrimitive, Double> WEIGHT_OTHER = doubleKey("weight_other");
    public static final JsonKeyImpl<JsonPrimitive, Boolean> DM_OUTPUT_ONLY = booleanKey("dm_output_only");
    public static final JsonKeyImpl<JsonPrimitive, Double> CHALLENGE_RATING = doubleKey("challenge_rating");
    public static final JsonKeyImpl<JsonPrimitive, Integer> CLIMB_SPEED = integerKey("climb_speed");
    public static final JsonKeyImpl<JsonPrimitive, Integer> FLY_SPEED = integerKey("fly_speed");
    public static final JsonKeyImpl<JsonPrimitive, Integer> SWIM_SPEED = integerKey("swim_speed");
    public static final JsonKeyImpl<JsonPrimitive, Integer> XP = integerKey("xp");
    public static final JsonKeyImpl<JsonPrimitive, String> ARMOR_CLASS_NOTE = stringKey("armor_class_note");
    public static final JsonKeyImpl<JsonArray, List<String>> SENSES = listKey("senses", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, String> TYPE_RACE = stringKey("type_race");
    public static final String NON_PLAYER_SKILLS = "non_player_skills";
    public static final JsonKeyImpl<JsonArray, List<String>> TRAITS = listKey("traits", new StringListSerializer());
    public static final JsonKeyImpl<JsonPrimitive, String> CONDITION_IMMUNITY = stringKey("condition_immunity");
    public static final JsonKeyImpl<JsonPrimitive, String> DAMAGE_IMMUNITY = stringKey("damage_immunity");
    public static final JsonKeyImpl<JsonPrimitive, String> DAMAGE_RESISTANCE = stringKey("damage_resistance");
    public static final JsonKeyImpl<JsonPrimitive, String> DAMAGE_VULNERABILITY = stringKey("damage_vulnerability");
    public static final String TRAITS_BACKGROUND = "traits_background";

    private Keys() {

    }
}
