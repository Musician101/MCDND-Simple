package io.musician101.mcdndsimple.sponge.serialization;

import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.data.value.mutable.MapValue;
import org.spongepowered.api.data.value.mutable.Value;

import static org.spongepowered.api.data.DataQuery.of;
import static org.spongepowered.api.data.key.KeyFactory.makeListKey;
import static org.spongepowered.api.data.key.KeyFactory.makeMapKey;
import static org.spongepowered.api.data.key.KeyFactory.makeSingleKey;

public class SpongeMCDNDSimpleKeys
{
    public static final Key<Value<Integer>> ABILITIES_AND_SKILLS = makeSingleKey(token(), token(), of("AbilitiesAndSkills"), SpongeMCDNDSimple.getPluginContainer().getId() + ":abilities_and_skills", "Abilities and Skills");
    public static final Key<Value<DataContainer>> ACROBATICS = makeSingleKey(token(), token(), of("Acrobatics"), SpongeMCDNDSimple.getPluginContainer().getId() + ":acrobatics", "Acrobatics");
    public static final Key<Value<Integer>> AGE = makeSingleKey(token(), token(), of("Age"), SpongeMCDNDSimple.getPluginContainer().getId() + ":age", "Age");
    public static final Key<Value<String>> ALIGNMENT = makeSingleKey(token(), token(), of("Alignment"), SpongeMCDNDSimple.getPluginContainer().getId() + ":alignment", "Alignment");
    public static final Key<Value<Integer>> AMMO = makeSingleKey(token(), token(), of("Ammo"), SpongeMCDNDSimple.getPluginContainer().getId() + ":ammo", "Ammo");
    public static final Key<Value<Integer>> AMOUNT = makeSingleKey(token(), token(), of("Amount"), SpongeMCDNDSimple.getPluginContainer().getId() + ":amount", "Amount");
    public static final Key<Value<DataContainer>> ANIMAL_HANDLING = makeSingleKey(token(), token(), of("AnimalHandling"), SpongeMCDNDSimple.getPluginContainer().getId() + ":animal_handling", "Animal Handling");
    public static final Key<Value<DataContainer>> ARCANA = makeSingleKey(token(), token(), of("Arcana"), SpongeMCDNDSimple.getPluginContainer().getId() + ":arcana", "Arcana");
    public static final Key<Value<Integer>> ARMOR_CLASS = makeSingleKey(token(), token(), of("ArmorClass"), SpongeMCDNDSimple.getPluginContainer().getId() + ":armor_class", "Armor Class");
    public static final Key<ListValue<DataContainer>> ARMOR_LIST = makeListKey(token(), token(), of("ArmorList"), SpongeMCDNDSimple.getPluginContainer().getId() + ":armor_list", "Armor List");
    public static final Key<ListValue<String>> ARMOR_PROFICIENCIES = makeListKey(token(), token(), of("ArmorProficiencies"), SpongeMCDNDSimple.getPluginContainer().getId() + ":armor_proficiencies", "Armor Proficiencies");
    public static final Key<Value<DataContainer>> ARMOR_TAB = makeSingleKey(token(), token(), of("ArmorTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":armor_tab", "Armor Tab");
    public static final Key<Value<DataContainer>> ARMOR_TYPE = makeSingleKey(token(), token(), of("ArmorType"), SpongeMCDNDSimple.getPluginContainer().getId() + ":armor_type", "Armor Type");
    public static final Key<Value<DataContainer>> ATHLETICS = makeSingleKey(token(), token(), of("Athletics"), SpongeMCDNDSimple.getPluginContainer().getId() + ":athletics", "Athletics");
    public static final Key<Value<Integer>> ATTACK = makeSingleKey(token(), token(), of("Attack"), SpongeMCDNDSimple.getPluginContainer().getId() + ":attack", "attack");
    public static final Key<Value<String>> ATTACK_STAT = makeSingleKey(token(), token(), of("AttackStat"), SpongeMCDNDSimple.getPluginContainer().getId() + ":attack_stat", "Attack Stat");
    public static final Key<ListValue<String>> BACKGROUND = makeListKey(token(), token(), of("Background"), SpongeMCDNDSimple.getPluginContainer().getId() + ":background", "Background");
    public static final Key<Value<DataContainer>> BACKGROUND_TAB = makeSingleKey(token(), token(), of("BackgroundTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":background_tab", "Background Tab");
    public static final Key<Value<Integer>> BARBARIAN_LEVEL = makeSingleKey(token(), token(), of("BarbarianLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":barbarian_level", "Barbarian Level");
    public static final Key<Value<Integer>> BARD_LEVEL = makeSingleKey(token(), token(), of("BardLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":bard_level", "Bard Level");
    public static final Key<Value<Integer>> BASE_ARMOR_CLASS = makeSingleKey(token(), token(), of("BaseArmorClass"), SpongeMCDNDSimple.getPluginContainer().getId() + ":base_armor_class", "Base Armor Class");
    public static final Key<ListValue<String>> BIO = makeListKey(token(), token(), of("Bio"), SpongeMCDNDSimple.getPluginContainer().getId() + ":bio", "Bio");
    public static final Key<Value<DataContainer>> BIO_AND_INFO = makeSingleKey(token(), token(), of("BioAndInfo"), SpongeMCDNDSimple.getPluginContainer().getId() + ":bio_and_info", "Bio and Info");
    public static final Key<ListValue<String>> BONDS = makeListKey(token(), token(), of("Bonds"), SpongeMCDNDSimple.getPluginContainer().getId() + ":bonds", "Bonds");
    public static final Key<Value<Integer>> BONUS = makeSingleKey(token(), token(), of("Bonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":bonus", "Bonus");
    public static final Key<Value<DataContainer>> BONUSES = makeSingleKey(token(), token(), of("Bonuses"), SpongeMCDNDSimple.getPluginContainer().getId() + ":bonuses", "Bonuses");
    public static final Key<Value<Boolean>> CAN_CRIT = makeSingleKey(token(), token(), of("CanCrit"), SpongeMCDNDSimple.getPluginContainer().getId() + ":can_crit", "Can Crit");
    public static final Key<Value<Boolean>> CARRIED = makeSingleKey(token(), token(), of("Carried"), SpongeMCDNDSimple.getPluginContainer().getId() + ":carried", "Carried");
    public static final Key<Value<String>> CAST_TIME = makeSingleKey(token(), token(), of("CastTime"), SpongeMCDNDSimple.getPluginContainer().getId() + ":cast_time", "Cast Time");
    public static final Key<Value<DataContainer>> CHARISMA_SCORE = makeSingleKey(token(), token(), of("CharismaScore"), SpongeMCDNDSimple.getPluginContainer().getId() + ":charisma_score", "Charisma Score");
    public static final Key<Value<String>> CLASS = makeSingleKey(token(), token(), of("Class"), SpongeMCDNDSimple.getPluginContainer().getId() + ":class", "Class");
    public static final Key<ListValue<DataContainer>> CLASS_ACTIONS = makeListKey(token(), token(), of("ClassActions"), SpongeMCDNDSimple.getPluginContainer().getId() + ":class_actions", "Class Actions");
    public static final Key<ListValue<String>> CLASS_FEATURE_NOTES = makeSingleKey(token(), token(), of("ClassFeatureNotes"), SpongeMCDNDSimple.getPluginContainer().getId() + ":class_feature_notes", "Class Feature Notes");
    public static final Key<Value<DataContainer>> CLASS_LEVELS = makeSingleKey(token(), token(), of("ClassLevels"), SpongeMCDNDSimple.getPluginContainer().getId() + ":class_levels", "Class Levels");
    public static final Key<ListValue<DataContainer>> CLASS_RESOURCES = makeListKey(token(), token(), of("ClassResources"), SpongeMCDNDSimple.getPluginContainer().getId() + ":class_resources", "Class Resources");
    public static final Key<Value<DataContainer>> CLASS_TAB = makeSingleKey(token(), token(), of("ClassTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":class_tab", "Class Tab");
    public static final Key<Value<Integer>> CLERIC_LEVEL = makeSingleKey(token(), token(), of("ClericLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":cleric_level", "Cleric Level");
    public static final Key<Value<DataContainer>> CONSTITUTION_SCORE = makeSingleKey(token(), token(), of("ConstitutionScore"), SpongeMCDNDSimple.getPluginContainer().getId() + ":constitution_score", "Constitution Score");
    public static final Key<Value<DataContainer>> COPPER = makeSingleKey(token(), token(), of("Copper"), SpongeMCDNDSimple.getPluginContainer().getId() + ":copper", "Copper");
    public static final Key<Value<DataContainer>> CORE_STATS = makeSingleKey(token(), token(), of("CoreStats"), SpongeMCDNDSimple.getPluginContainer().getId() + ":core_stats", "Core Stats");
    public static final Key<Value<DataContainer>> CORE_STATS_TAB = makeSingleKey(token(), token(), of("CoreStatsTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":core_stats_tab", "Core Stats Tab");
    public static final Key<Value<DataContainer>> CRIT_DAMAGE_DICE = makeSingleKey(token(), token(), of("CritDamageDice"), SpongeMCDNDSimple.getPluginContainer().getId() + ":crit_damage_dice", "Crit Damage Dice");
    public static final Key<Value<Integer>> CRIT_MINIMUM = makeSingleKey(token(), token(), of("CritMinimum"), SpongeMCDNDSimple.getPluginContainer().getId() + ":crit_minimum", "Crit Minimum");
    public static final Key<Value<Integer>> CURRENT_HP = makeSingleKey(token(), token(), of("CurrentHP"), SpongeMCDNDSimple.getPluginContainer().getId() + ":current_hp", "Current HP");
    public static final Key<Value<Integer>> CUSTOM_DC = makeSingleKey(token(), token(), of("CustomDC"), SpongeMCDNDSimple.getPluginContainer().getId() + ":custom_dc", "Custom DC");
    public static final Key<Value<Integer>> DAMAGE = makeSingleKey(token(), token(), of("Damage"), SpongeMCDNDSimple.getPluginContainer().getId() + ":damage", "Damage");
    public static final Key<Value<Integer>> DAMAGE_BONUS = makeSingleKey(token(), token(), of("DamageBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":damage_bonus", "Damage Bonus");
    public static final Key<Value<DataContainer>> DAMAGE_DICE = makeSingleKey(token(), token(), of("DamageDice"), SpongeMCDNDSimple.getPluginContainer().getId() + ":Damage Dice", "Damage Dice");
    public static final Key<Value<String>> DAMAGE_TYPE = makeSingleKey(token(), token(), of("DamageType"), SpongeMCDNDSimple.getPluginContainer().getId() + ":damage_type", "Damage Type");
    public static final Key<Value<DataContainer>> DECEPTION = makeSingleKey(token(), token(), of("Deception"), SpongeMCDNDSimple.getPluginContainer().getId() + ":deception", "Deception");
    public static final Key<Value<String>> DESCRIPTION = makeSingleKey(token(), token(), of("Description"), SpongeMCDNDSimple.getPluginContainer().getId() + ":description", "Description");
    public static final Key<Value<DataContainer>> DEXTERITY_SCORE = makeSingleKey(token(), token(), of("DexterityScore"), SpongeMCDNDSimple.getPluginContainer().getId() + ":dexterity_score", "Dexterity Score");
    public static final Key<Value<DataContainer>> DICE = makeSingleKey(token(), token(), of("Dice"), SpongeMCDNDSimple.getPluginContainer().getId() + ":dice", "dice");
    public static final Key<Value<Integer>> DRUID_LEVEL = makeSingleKey(token(), token(), of("DruidLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":druid_level", "Druid Level");
    public static final Key<Value<Integer>> DURATION = makeSingleKey(token(), token(), of("Duration"), SpongeMCDNDSimple.getPluginContainer().getId() + ":duration", "Duration");
    public static final Key<ListValue<String>> EFFECTS = makeListKey(token(), token(), of("Effects"), SpongeMCDNDSimple.getPluginContainer().getId() + ":effects", "Effects");
    public static final Key<Value<DataContainer>> ELECTRUM = makeSingleKey(token(), token(), of("Electrum"), SpongeMCDNDSimple.getPluginContainer().getId() + ":electrum", "Electrum");
    public static final Key<Value<DataContainer>> EXPERIENCE = makeSingleKey(token(), token(), of("Experience"), SpongeMCDNDSimple.getPluginContainer().getId() + ":experience", "Experience");
    public static final Key<Value<Integer>> EXPERIENCE_AMOUNT = makeSingleKey(token(), token(), of("ExperienceAmount"), SpongeMCDNDSimple.getPluginContainer().getId() + ":experience_amount", "Experience Amount");
    public static final Key<Value<String>> EYES = makeSingleKey(token(), token(), of("Eyes"), SpongeMCDNDSimple.getPluginContainer().getId() + ":eyes", "Eyes");
    public static final Key<Value<Integer>> FIGHTER_LEVEL = makeSingleKey(token(), token(), of("FighterLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":fighter_level", "Fighter Level");
    public static final Key<ListValue<String>> FLAWS = makeListKey(token(), token(), of("Flaws"), SpongeMCDNDSimple.getPluginContainer().getId() + ":flaws", "Flaws");
    public static final Key<Value<String>> GAINED_FROM = makeSingleKey(token(), token(), of("GainedFrom"), SpongeMCDNDSimple.getPluginContainer().getId() + ":gained_from", "Gained From");
    public static final Key<Value<String>> GENDER = makeSingleKey(token(), token(), of("Gender"), SpongeMCDNDSimple.getPluginContainer().getId() + ":gender", "Gender");
    public static final Key<Value<DataContainer>> GOLD = makeSingleKey(token(), token(), of("Gold"), SpongeMCDNDSimple.getPluginContainer().getId() + ":gold", "Gold");
    public static final Key<Value<String>> HAIR = makeSingleKey(token(), token(), of("Hair"), SpongeMCDNDSimple.getPluginContainer().getId() + ":hair", "Hair");
    public static final Key<Value<Integer>> HEAL_AMOUNT = makeSingleKey(token(), token(), of("HealAmount"), SpongeMCDNDSimple.getPluginContainer().getId() + ":heal_amount", "Heal Amount");
    public static final Key<Value<String>> HEIGHT = makeSingleKey(token(), token(), of("Height"), SpongeMCDNDSimple.getPluginContainer().getId() + ":height", "Height");
    public static final Key<Value<DataContainer>> HISTORY = makeSingleKey(token(), token(), of("History"), SpongeMCDNDSimple.getPluginContainer().getId() + ":history", "History");
    public static final Key<Value<DataContainer>> HIT_DICE = makeSingleKey(token(), token(), of("HitDice"), SpongeMCDNDSimple.getPluginContainer().getId() + ":hit_dice", "Hit Dice");
    public static final Key<MapValue<Integer, Integer>> HIT_DICE_MAP = makeMapKey(token(), token(), of("HitDiceMap"), SpongeMCDNDSimple.getPluginContainer().getId() + ":hit_dice_map", "Hit Dice Map");
    public static final Key<Value<DataContainer>> HIT_POINTS = makeSingleKey(token(), token(), of("HitPoints"), SpongeMCDNDSimple.getPluginContainer().getId() + ":hit_points", "Hit Points");
    public static final Key<ListValue<String>> IDEALS = makeListKey(token(), token(), of("Ideals"), SpongeMCDNDSimple.getPluginContainer().getId() + ":ideals", "Ideals");
    public static final Key<Value<Integer>> INITIATIVE_BONUS = makeSingleKey(token(), token(), of("InitiativeBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":initiative_bonus", "Initiative Bonus");
    public static final Key<Value<DataContainer>> INSIGHT = makeSingleKey(token(), token(), of("Insight"), SpongeMCDNDSimple.getPluginContainer().getId() + ":insight", "Insight");
    public static final Key<Value<DataContainer>> INTELLIGENCE_SCORE = makeSingleKey(token(), token(), of("IntelligenceScore"), SpongeMCDNDSimple.getPluginContainer().getId() + ":intelligence_score", "Intelligence Score");
    public static final Key<Value<DataContainer>> INTIMIDATION = makeSingleKey(token(), token(), of("Intimidation"), SpongeMCDNDSimple.getPluginContainer().getId() + ":intimidation", "Intimidation");
    public static final Key<ListValue<DataContainer>> INVENTORY = makeListKey(token(), token(), of("Inventory"), SpongeMCDNDSimple.getPluginContainer().getId() + ":inventory", "Inventory");
    public static final Key<ListValue<String>> INVENTORY_NOTES = makeListKey(token(), token(), of("InventoryNotes"), SpongeMCDNDSimple.getPluginContainer().getId() + ":inventory_notes", "Inventory Notes");
    public static final Key<Value<DataContainer>> INVENTORY_TAB = makeSingleKey(token(), token(), of("InventoryTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":inventory_tab", "Inventory Tab");
    public static final Key<Value<DataContainer>> INVESTIGATION = makeSingleKey(token(), token(), of("Investigation"), SpongeMCDNDSimple.getPluginContainer().getId() + ":investigation", "Investigation");
    public static final Key<Value<Integer>> INVOCATION_COUNT = makeSingleKey(token(), token(), of("InvocationCount"), SpongeMCDNDSimple.getPluginContainer().getId() + ":invocation_count", "Invocation Count");
    public static final Key<Value<Boolean>> IS_PREPARED = makeSingleKey(token(), token(), of("IsPrepared"), SpongeMCDNDSimple.getPluginContainer().getId() + ":is_prepared", "Is Prepared");
    public static final Key<Value<Boolean>> IS_PROFICIENT = makeSingleKey(token(), token(), of("IsProficient"), SpongeMCDNDSimple.getPluginContainer().getId() + ":is_proficient", "Is Proficient");
    public static final Key<Value<String>> LANGUAGES = makeSingleKey(token(), token(), of("Languages"), SpongeMCDNDSimple.getPluginContainer().getId() + ":languages", "Languages");
    public static final Key<Value<Integer>> MAGIC_BONUS = makeSingleKey(token(), token(), of("MagicBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":magic_bonus", "Magic Bonus");
    public static final Key<Value<Integer>> MAX_HP = makeSingleKey(token(), token(), of("MaxHP"), SpongeMCDNDSimple.getPluginContainer().getId() + ":max_hp", "Max HP");
    public static final Key<Value<Integer>> MAX_USES = makeSingleKey(token(), token(), of("MaxUses"), SpongeMCDNDSimple.getPluginContainer().getId() + ":max_uses", "Max Uses");
    public static final Key<Value<DataContainer>> MEDICINE = makeSingleKey(token(), token(), of("Medicine"), SpongeMCDNDSimple.getPluginContainer().getId() + ":medicine", "Medicine");
    public static final Key<Value<DataContainer>> MELEE_BONUS = makeSingleKey(token(), token(), of("MeleeBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":melee_bonus", "Melee Bonus");
    public static final Key<ListValue<DataContainer>> MELEE_WEAPONS = makeListKey(token(), token(), of("MeleeWeapons"), SpongeMCDNDSimple.getPluginContainer().getId() + ":melee_weapons", "Melee Weapons");
    public static final Key<Value<Integer>> MONK_LEVEL = makeSingleKey(token(), token(), of("MonkLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":monk_level", "Monk Level");
    public static final Key<Value<String>> NAME = makeSingleKey(token(), token(), of("Name"), SpongeMCDNDSimple.getPluginContainer().getId() + ":name", "Name");
    public static final Key<Value<DataContainer>> NATURE = makeSingleKey(token(), token(), of("Nature"), SpongeMCDNDSimple.getPluginContainer().getId() + ":nature", "Nature");
    public static final Key<Value<Boolean>> NEEDS_CONCENTRATION = makeSingleKey(token(), token(), of("NeedsConcentration"), SpongeMCDNDSimple.getPluginContainer().getId() + ":needs_concentration", "Needs Concentration");
    public static final Key<Value<String>> ON_SUCCESSFUL_SAVE = makeSingleKey(token(), token(), of("OnSuccessfulSave"), SpongeMCDNDSimple.getPluginContainer().getId() + ":on_successful_save", "On Successful Save");
    public static final Key<Value<Double>> OTHER = makeSingleKey(token(), token(), of("Other"), SpongeMCDNDSimple.getPluginContainer().getId() + ":other", "Other");
    public static final Key<ListValue<String>> OTHER_NOTES = makeListKey(token(), token(), of("OtherNotes"), SpongeMCDNDSimple.getPluginContainer().getId() + ":other_notes", "Other Notes");
    public static final Key<Value<Integer>> OVERALL_LEVEL = makeSingleKey(token(), token(), of("OverallLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":overall_level", "Overall Level");
    public static final Key<Value<Integer>> PALADIN_LEVEL = makeSingleKey(token(), token(), of("PaladinLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":paladin_level", "Paladin Level");
    public static final Key<Value<DataContainer>> PERCEPTION = makeSingleKey(token(), token(), of("Perception"), SpongeMCDNDSimple.getPluginContainer().getId() + ":perception", "Perception");
    public static final Key<Value<DataContainer>> PERFORMANCE = makeSingleKey(token(), token(), of("Performance"), SpongeMCDNDSimple.getPluginContainer().getId() + ":performance", "Performance");
    public static final Key<ListValue<String>> PERSONALITY_TRAITS = makeListKey(token(), token(), of("PersonalityTraits"), SpongeMCDNDSimple.getPluginContainer().getId() + ":personality_traits", "Personality Traits");
    public static final Key<Value<DataContainer>> PERSUASION = makeSingleKey(token(), token(), of("Persuasion"), SpongeMCDNDSimple.getPluginContainer().getId() + ":persuasion", "Persuasion");
    public static final Key<Value<DataContainer>> PLATINUM = makeSingleKey(token(), token(), of("Platinum"), SpongeMCDNDSimple.getPluginContainer().getId() + ":platinum", "platinum");
    public static final Key<Value<DataContainer>> PLAYER_SHEET = makeSingleKey(token(), token(), of("PlayerSheet"), SpongeMCDNDSimple.getPluginContainer().getId() + ":player_sheet", "Player Sheet");
    public static final Key<Value<Boolean>> PLUS_STAT = makeSingleKey(token(), token(), of("PlusStat"), SpongeMCDNDSimple.getPluginContainer().getId() + ":plus_stat", "Plus Stat");
    public static final Key<Value<Boolean>> PROFICIENT = makeSingleKey(token(), token(), of("Proficient"), SpongeMCDNDSimple.getPluginContainer().getId() + ":proficient", "Proficient");
    public static final Key<Value<String>> RACE = makeSingleKey(token(), token(), of("Race"), SpongeMCDNDSimple.getPluginContainer().getId() + ":race", "Race");
    public static final Key<ListValue<String>> RACIAL_TRAITS = makeListKey(token(), token(), of("RacialTraits"), SpongeMCDNDSimple.getPluginContainer().getId() + ":racial_traits", "Racial Traits");
    public static final Key<Value<Integer>> RANGE = makeSingleKey(token(), token(), of("Range"), SpongeMCDNDSimple.getPluginContainer().getId() + ":range", "Range");
    public static final Key<Value<DataContainer>> RANGED_BONUS = makeSingleKey(token(), token(), of("RangedBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":ranged_bonus", "Ranged Bonus");
    public static final Key<ListValue<DataContainer>> RANGED_WEAPONS = makeListKey(token(), token(), of("RangedWeapons"), SpongeMCDNDSimple.getPluginContainer().getId() + ":ranged_weapons", "Ranged Weapons");
    public static final Key<Value<Integer>> RANGER_LEVEL = makeSingleKey(token(), token(), of("RangerLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":Ranger_level", "Ranger Level");
    public static final Key<Value<DataContainer>> RECHARGE = makeSingleKey(token(), token(), of("Recharge"), SpongeMCDNDSimple.getPluginContainer().getId() + ":recharge", "Recharge");
    public static final Key<Value<DataContainer>> RELIGION = makeSingleKey(token(), token(), of("Religion"), SpongeMCDNDSimple.getPluginContainer().getId() + ":religion", "Religion");
    public static final Key<Value<Integer>> ROGUE_LEVEL = makeSingleKey(token(), token(), of("RogueLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":rogue_level", "Rogue Level");
    public static final Key<Value<Integer>> SAVES = makeSingleKey(token(), token(), of("Saves"), SpongeMCDNDSimple.getPluginContainer().getId() + ":saves", "Saves");
    public static final Key<Value<Integer>> SAVE_DC = makeSingleKey(token(), token(), of("SaveDC"), SpongeMCDNDSimple.getPluginContainer().getId() + ":save_dc", "Save DC");
    public static final Key<Value<DataContainer>> SAVE_DC_TYPE = makeSingleKey(token(), token(), of("SaveDCType"), SpongeMCDNDSimple.getPluginContainer().getId() + ":save_dc_type", "Save DC Type");
    public static final Key<Value<String>> SAVING_STAT = makeSingleKey(token(), token(), of("SavingStat"), SpongeMCDNDSimple.getPluginContainer().getId() + ":saving_stat", "Saving Stat");
    public static final Key<Value<Integer>> SCORE = makeSingleKey(token(), token(), of("Score"), SpongeMCDNDSimple.getPluginContainer().getId() + ":score", "Score");
    public static final Key<Value<String>> SHORT_NAME = makeSingleKey(token(), token(), of("ShortName"), SpongeMCDNDSimple.getPluginContainer().getId() + ":short_name", "Short Name");
    public static final Key<Value<Integer>> SIDES = makeSingleKey(token(), token(), of("Sides"), SpongeMCDNDSimple.getPluginContainer().getId() + ":sides", "Sides");
    public static final Key<Value<DataContainer>> SILVER = makeSingleKey(token(), token(), of("Silver"), SpongeMCDNDSimple.getPluginContainer().getId() + ":silver", "Silver");
    public static final Key<Value<String>> SIZE = makeSingleKey(token(), token(), of("Size"), SpongeMCDNDSimple.getPluginContainer().getId() + ":size", "Size");
    public static final Key<Value<DataContainer>> SKILLS_TAB = makeSingleKey(token(), token(), of("SkillsTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":skills_tab", "Skills Tab");
    public static final Key<Value<DataContainer>> SKILL_PROFICIENCY = makeSingleKey(token(), token(), of("SkillProficiency"), SpongeMCDNDSimple.getPluginContainer().getId() + ":skill_proficiency", "Skill Proficiency");
    public static final Key<Value<DataContainer>> SLEIGHT_OF_HAND = makeSingleKey(token(), token(), of("SleightOfHand"), SpongeMCDNDSimple.getPluginContainer().getId() + ":sleight_of_hand", "Sleight Of Hand");
    public static final Key<Value<Integer>> SORCERER_LEVEL = makeSingleKey(token(), token(), of("SorcererLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":sorcerer_level", "Sorcerer Level");
    public static final Key<Value<Integer>> SORCERY_POINTS = makeSingleKey(token(), token(), of("SorceryPoints"), SpongeMCDNDSimple.getPluginContainer().getId() + ":sorcery_points", "Sorcery Points");
    public static final Key<Value<Integer>> SPEED = makeSingleKey(token(), token(), of("Speed"), SpongeMCDNDSimple.getPluginContainer().getId() + ":speed", "Speed");
    public static final Key<Value<Boolean>> SPEED_PENALTY = makeSingleKey(token(), token(), of("SpeedPenalty"), SpongeMCDNDSimple.getPluginContainer().getId() + ":speed_penalty", "Speed Penalty");
    public static final Key<Value<DataContainer>> SPELLCASTER_CLASS = makeSingleKey(token(), token(), of("SpellcasterClass"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spellcaster_class", "Spellcaster Class");
    public static final Key<ListValue<DataContainer>> SPELLCASTER_CLASSES = makeListKey(token(), token(), of("SpellcasterClasses"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spellcaster_classes", "Spellcaster Classes");
    public static final Key<Value<DataContainer>> SPELLCASTING_BONUS = makeSingleKey(token(), token(), of("SpellcastingBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spellcasting_bonus", "Spellcasting Bonus");
    public static final Key<ListValue<DataContainer>> SPELLS = makeListKey(token(), token(), of("Spells"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spells", "Spells");
    public static final Key<Value<DataContainer>> SPELL_BOOK_TAB = makeSingleKey(token(), token(), of("SpellBookTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spell_book_tab", "Spell Book Tab");
    public static final Key<Value<DataContainer>> SPELL_DAMAGE = makeSingleKey(token(), token(), of("SpellDamage"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spell_damage", "Spell Damage");
    public static final Key<ListValue<String>> SPELL_DESCRIPTION = makeListKey(token(), token(), of("SpellDescription"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spell_description", "Spell Description");
    public static final Key<Value<DataContainer>> SPELL_HEALING = makeSingleKey(token(), token(), of("SpellHealing"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spell_healing", "Spell Healing");
    public static final Key<Value<Integer>> SPELL_LEVEL = makeSingleKey(token(), token(), of("SpellLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spell_level", "SpellLevel");
    public static final Key<Value<DataContainer>> SPELL_SAVE = makeSingleKey(token(), token(), of("SpellSave"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spell_save", "Spell Save");
    public static final Key<Value<DataContainer>> SPELL_TYPE = makeSingleKey(token(), token(), of("SpellType"), SpongeMCDNDSimple.getPluginContainer().getId() + ":spell_type", "Spell Type");
    public static final Key<Value<String>> STAT_BONUS = makeSingleKey(token(), token(), of("StatBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":stat_bonus", "Stat Bonus");
    public static final Key<Value<DataContainer>> STEALTH = makeSingleKey(token(), token(), of("Stealth"), SpongeMCDNDSimple.getPluginContainer().getId() + ":stealth", "Stealth");
    public static final Key<Value<Boolean>> STEALTH_PENALTY = makeSingleKey(token(), token(), of("StealthPenalty"), SpongeMCDNDSimple.getPluginContainer().getId() + ":stealth_penalty", "Stealth Penalty");
    public static final Key<Value<DataContainer>> STRENGTH_SCORE = makeSingleKey(token(), token(), of("StrengthScore"), SpongeMCDNDSimple.getPluginContainer().getId() + ":strength_score", "Strength Score");
    public static final Key<Value<DataContainer>> SURVIVAL = makeSingleKey(token(), token(), of("Survival"), SpongeMCDNDSimple.getPluginContainer().getId() + ":survival", "Survival");
    public static final Key<Value<String>> TARGET_AREA = makeSingleKey(token(), token(), of("TargetArea"), SpongeMCDNDSimple.getPluginContainer().getId() + ":target_area", "TargetArea");
    public static final Key<Value<Integer>> TEMP_HP = makeSingleKey(token(), token(), of("TempHP"), SpongeMCDNDSimple.getPluginContainer().getId() + ":temp_hp", "Temp HP");
    public static final Key<ListValue<String>> TOOL_PROFICIENCIES = makeListKey(token(), token(), of("ToolProficiencies"), SpongeMCDNDSimple.getPluginContainer().getId() + ":tool_proficiencies", "Tool Proficiencies");
    public static final Key<Value<Integer>> TO_HIT = makeSingleKey(token(), token(), of("ToHit"), SpongeMCDNDSimple.getPluginContainer().getId() + ":to_hit", "To Hit");
    public static final Key<Value<Boolean>> UNARMORED = makeSingleKey(token(), token(), of("Unarmored"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unarmored", "Unarmored");
    public static final Key<Value<Integer>> UNARMORED_ARMOR_CLASS = makeSingleKey(token(), token(), of("UnarmoredArmorClass"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unarmored_armor_class", "Unarmored Armor Class");
    public static final Key<Value<DataContainer>> UNARMORED_BONUS = makeSingleKey(token(), token(), of("UnarmoredBonus"), SpongeMCDNDSimple.getPluginContainer().getId() + ":armor_class", "Unarmored Bonus");
    public static final Key<Value<DataContainer>> UNSKILLED_CHA = makeSingleKey(token(), token(), of("UnskilledCHA"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unskilled_cha", "Unskilled CHA");
    public static final Key<Value<DataContainer>> UNSKILLED_CON = makeSingleKey(token(), token(), of("UnskilledCON"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unskilled_con", "Unskilled CON");
    public static final Key<Value<DataContainer>> UNSKILLED_DEX = makeSingleKey(token(), token(), of("UnskilledDEX"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unskilled_dex", "Unskilled DEX");
    public static final Key<Value<DataContainer>> UNSKILLED_INT = makeSingleKey(token(), token(), of("UnskilledINT"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unskilled_int", "Unskilled INT");
    public static final Key<Value<DataContainer>> UNSKILLED_STR = makeSingleKey(token(), token(), of("UnskilledSTR"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unskilled_str", "Unskilled STR");
    public static final Key<Value<DataContainer>> UNSKILLED_WIS = makeSingleKey(token(), token(), of("UnskilledWIS"), SpongeMCDNDSimple.getPluginContainer().getId() + ":unskilled_wis", "Unskilled WIS");
    public static final Key<Value<Integer>> USES_LEFT = makeSingleKey(token(), token(), of("ExpendedUses"), SpongeMCDNDSimple.getPluginContainer().getId() + ":expended_uses", "Expended Uses");
    public static final Key<Value<String>> VISION = makeSingleKey(token(), token(), of("Vision"), SpongeMCDNDSimple.getPluginContainer().getId() + ":vision", "Vision");
    public static final Key<Value<Integer>> WARLOCK_LEVEL = makeSingleKey(token(), token(), of("WarlockLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":warlock_level", "Warlock Level");
    public static final Key<Value<DataContainer>> WEALTH = makeSingleKey(token(), token(), of("Wealth"), SpongeMCDNDSimple.getPluginContainer().getId() + ":wealth", "Wealth");
    public static final Key<Value<DataContainer>> WEAPONS_TAB = makeSingleKey(token(), token(), of("WeaponTab"), SpongeMCDNDSimple.getPluginContainer().getId() + ":weapon_tab", "Weapon Tab");
    public static final Key<ListValue<String>> WEAPON_PROFICIENCIES = makeListKey(token(), token(), of("WeaponProficiencies"), SpongeMCDNDSimple.getPluginContainer().getId() + ":weapon_proficiencies", "Weapon Proficiencies");
    public static final Key<Value<DataContainer>> WEIGHT_CLASS = makeSingleKey(token(), token(), of("WeightClass"), SpongeMCDNDSimple.getPluginContainer().getId() + ":weight_class", "Weight Class");
    public static final Key<Value<Double>> WEIGHT_DOUBLE = makeSingleKey(token(), token(), of("WeightDouble"), SpongeMCDNDSimple.getPluginContainer().getId() + ":weight_double", "Weight Double");
    public static final Key<Value<DataContainer>> WISDOM_SCORE = makeSingleKey(token(), token(), of("WisdomScore"), SpongeMCDNDSimple.getPluginContainer().getId() + ":wisdom_score", "Wisdom Score");
    public static final Key<Value<Integer>> WIZARD_LEVEL = makeSingleKey(token(), token(), of("WizardLevel"), SpongeMCDNDSimple.getPluginContainer().getId() + ":wizard_level", "Wizard Level");
    public static final Key<Value<Boolean>> WORN = makeSingleKey(token(), token(), of("Worn"), SpongeMCDNDSimple.getPluginContainer().getId() + ":worn", "Worn");

    private SpongeMCDNDSimpleKeys()
    {

    }

    private static <E> MCDNDSimpleTypeToken<E> token()
    {
        return new MCDNDSimpleTypeToken<>();
    }
}