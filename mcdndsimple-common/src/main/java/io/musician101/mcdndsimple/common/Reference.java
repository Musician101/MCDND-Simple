package io.musician101.mcdndsimple.common;

import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.Recharge;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Coin;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.spell.Prepared;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.player.spell.SpellType;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.weapon.AbstractWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reference {

    private Reference() {

    }

    public static class Commands {

        public static final String CALLBACK_DESC = "An internal command for running code from clickable text in chat.";
        public static final String CALLBACK_NAME = "callback";
        public static final String CHARACTER_DESC = "Edit a character's sheet.";
        public static final String CHARACTER_NAME = "character";
        public static final String CREATE_COMMAND = "create";
        public static final String CREATE_COMMAND_DESC = "Create a PC or NPC_NAME.";
        public static final String NAME = "name";
        public static final String NPC_DESC = "Open up the player sheet for an NPC.";
        public static final String NPC_NAME = "npc";
        public static final String PC = "pc";
        public static final String CREATE_ARGUMENT = NPC_NAME + " | " + PC;
        public static final String PLAYER_SHEET_DESC = "Open up the player sheet for a character.";
        public static final String PLAYER_SHEET_NAME = "playersheet";
        public static final String SPELL_NAME = "spell";
        public static final String UUID = "UUID";

        private Commands() {

        }
    }

    public static class MenuText {

        public static final String ABILITY_SKILL_CHECK_ROLLS = "Ability/Skill Check Rolls";
        public static final String ACROBATICS = "Acrobatics";
        public static final String ANIMAL_HANDLING = "Animal Handling";
        public static final String ARCANA = "Arcana";
        public static final String ARMOR = "Armor";
        public static final String ARMOR_PROFICIENCIES = "Armor Proficiencies";
        public static final String ARMOR_TYPE = "Armor Type";
        public static final String ATHLETICS = "Athletics";
        public static final String ATTACK = "Attack";
        public static final String ATTACK_ROLLS = "Attack Rolls";
        public static final String ATTACK_STAT = "Attack Stat";
        public static final String AT_HIGHER_LEVELS = "At Higher Levels";
        public static final String BACKGROUND = "Background";
        public static final String BARBARIAN = "Barbarian";
        public static final String BARD = "Bard";
        public static final String BIO = "Bio";
        public static final String BIO_AND_INFO = "Bio and Info";
        public static final String BONDS = "Bonds";
        public static final String BONUS = "Bonus";
        public static final String BONUSES_PENALTIES = "Bonuses/Penalties";
        public static final String CANTRIPS = "Cantrips";
        public static final String CAN_CRIT = "Can Crit?";
        public static final String CAST_SPELL = "Cast Spell";
        public static final String CHARACTER_SHEET = "Character Sheet";
        public static final String CHARISMA = "Charisma";
        public static final String CLASS = "Class";
        public static final String CLASS_ACTIONS = "Class Actions";
        public static final String CLASS_FEATURE_NOTES = "Class Feature Notes";
        public static final String CLASS_LEVELS = "Class Levels";
        public static final String CLASS_RESOURCES = "Class Resources";
        public static final String CLERIC = "Cleric";
        public static final String CLICK_TO_VIEW = "Click to view.";
        public static final String COIN_CARRIED = "Coin Carried";
        public static final String COMPONENTS = "Components";
        public static final String CONSTITUTION = "Constitution";
        //TODO rename to CORE_SKILLS_OUTPUT_OPTIONS
        public static final String CORE_SKILLS_OUTPUT_SKILLS = "Core Skills Output Options";
        public static final String CORE_STATS = "Core Stats";
        public static final String D10_DESC = "Fighter, Paladin, Ranger";
        public static final String D12_DESC = "Barbarian";
        public static final String D6_DESC = "Sorcerer, Wizard";
        public static final String[] D8_DESC = {"Bard, Cleric, Druid", "Rogue, Monk, Warlock"};
        public static final String DAMAGE = "Damage";
        public static final String DAMAGE_DICE = "Damage Dice";
        public static final String DAMAGE_ROLLS = "Damage Rolls";
        public static final String DAMAGE_TYPE = "Damage Type";
        public static final String DEATH = "Death";
        public static final String DECEPTION = "Deception";
        public static final String DELETE = "Delete";
        public static final String DESCRIPTION = "Description";
        public static final String DEXTERITY = "Dexterity";
        public static final String DRUID = "Druid";
        public static final String EFFECTS = "Effects";
        public static final String FIGHTER = "Fighter";
        public static final String FINESSE = "Finesse";
        public static final String FLAWS = "Flaws";
        public static final String GAINED_FROM = "Gained From";
        public static final String HEALING = "Healing";
        public static final String HISTORY = "History";
        public static final String HIT_DICE = "Hit Dice";
        public static final String HIT_POINTS = "Hit Points";
        public static final String IDEALS = "Ideals";
        public static final String INFO_BLOCK = "Info block";
        public static final String INITIATIVE = "Initiative";
        public static final String INSIGHT = "Insight";
        public static final String INSPIRATION = "Inspiration";
        public static final String INTELLIGENCE = "Intelligence";
        public static final String INTIMIDATION = "Intimidation";
        public static final String INVENTORY = "Inventory";
        public static final String INVENTORY_NOTES = "Inventory Notes";
        public static final String INVESTIGATION = "Investigation";
        public static final String KNOWN_LANGUAGES = "Known Languages";
        public static final String LEVEL_AND_XP = "Level and XP";
        public static final String MEDICINE = "Medicine";
        public static final String MELEE_BONUSES = "Melee Bonuses";
        public static final String MELEE_WEAPONS = "Melee Weapons";
        public static final String MONK = "Monk";
        public static final String NATURE = "Nature";
        public static final String NEW_CLASS_ACTION = "New Class Action";
        public static final String NEW_ITEM = "New Item";
        public static final String NEW_MELEE_WEAPON = "New Melee Weapon";
        public static final String NEW_RANGED_WEAPON = "New Ranged Weapon";
        public static final String NEXT_PAGE = "Next Page";
        /**
         * @deprecated need to use a tiny bit more often in some cases
         */
        @Deprecated
        public static final String NONE = "None";
        public static final String ON_SUCCESSFUL_SAVE = "On A Successful Save";
        public static final String OTHER_BONUS = "Other Bonus";
        public static final String OTHER_NOTES = "Other Notes";
        public static final String OUTPUT = "Output";
        public static final String OUTPUT_OPTIONS = "Output Options";
        public static final String PALADIN = "Paladin";
        public static final String PERCEPTION = "Perception";
        public static final String PERFORMANCE = "Performance";
        public static final String PERSONALITY_TRAITS = "Personality Traits";
        public static final String PERSUASION = "Persuasion";
        public static final String PLAYER_SHEET = "Player Sheet";
        public static final String PLUS_STAT = "Plus Stat?";
        public static final String PREVIOUS_PAGE = "Previous Page";
        public static final String PROFICIENT = "Proficient";
        public static final String RACIAL_TRAITS = "Racial Traits";
        public static final String RANGED_BONUSES = "Ranged Bonuses";
        public static final String RANGED_WEAPONS = "Ranged Weapons";
        public static final String RANGER = "Ranger";
        public static final String RECHARGE = "Recharge";
        public static final String RELIGION = "Religion";
        public static final String RENAME = "Rename";
        public static final String ROGUE = "Rogue";
        public static final String ROLL = "Roll";
        public static final String ROLL_ATTACK = "Roll Attack";
        public static final String ROLL_INITIATIVE = "Roll Initiative";
        public static final String ROLL_SAVE = "Roll Save";
        public static final String SAVE = "Save";
        public static final String SAVE_DC = "Save DC";
        public static final String SAVE_DC_ROLLS = "Save DC Rolls";
        public static final String SAVING_STAT = "Saving Stat";
        public static final String SAVING_THROW = "Saving Throw";
        public static final String SAVING_THROWS = "Saving Throws";
        public static final String SAVING_THROW_BONUSES = "Saving Throw Rolls";
        public static final String SAVING_THROW_OUTPUT_OPTIONS = "Saving Throw Output Options";
        public static final String SCHOOL = "School";
        public static final String SECOND_ATTACK = "Second Attack";
        public static final String SKILLS = "Skills";
        public static final String SLEIGHT_OF_HAND = "Sleight of Hand";
        public static final String SORCERER = "Sorcerer";
        public static final String SORCERY_POINTS = "Sorcery Points";
        public static final String SPELLBOOK = "Spellbook";
        public static final String SPELLCASTING_BONUSES = "Spellcasting Bonuses";
        public static final String SPELLS = "Spells";
        public static final String SPELLS_DASHBOARD = "Spell Dashboard";
        public static final String SPELL_CAST = "Spell Cast";
        public static final String SPELL_CAST_MACRO_DISPLAY_OPTIONS = "Spell Cast Macro Display Options";
        public static final String SPELL_DASHBOARD = "Spell Dashboard";
        public static final String SPELL_EFFECTS = "Spell Effects";
        public static final String SPELL_INFO = "Spell Info";
        public static final String SPELL_SLOTS = "Spell Slots";
        public static final String SPELL_SLOT_1 = "Spell Slot 1";
        public static final String SPELL_SLOT_2 = "Spell Slot 2";
        public static final String SPELL_SLOT_3 = "Spell Slot 3";
        public static final String SPELL_SLOT_4 = "Spell Slot 4";
        public static final String SPELL_SLOT_5 = "Spell Slot 5";
        public static final String SPELL_SLOT_6 = "Spell Slot 6";
        public static final String SPELL_SLOT_7 = "Spell Slot 7";
        public static final String SPELL_SLOT_8 = "Spell Slot 8";
        public static final String SPELL_SLOT_9 = "Spell Slot 9";
        public static final String STAT_BONUS = "Stat Bonus";
        public static final String STEALTH = "Stealth";
        public static final String STRENGTH = "Strength";
        public static final String SURVIVAL = "Survival";
        public static final String TARGET = "Target/Area of Effect";
        public static final String TOOL_PROFICIENCIES = "Tool Proficiencies";
        public static final String TOTAL_IN_GOLD = "Total in Gold";
        public static final String UNARMORED_BONUS = "Unarmored Bonus";
        public static final String WARLOCK = "Warlock";
        public static final String WEAPONS = "Weapons";
        public static final String WEAPONS_SPELL_MISC_OUTPUT_OPTIONS = "Weapons/Spell/Misc Output Options";
        public static final String WEAPON_PROFICIENCIES = "Weapon Proficiencies";
        public static final String WEIGHT = "Weight";
        public static final String WISDOM = "Wisdom";
        public static final String WIZARD = "Wizard";

        private MenuText() {

        }

        public static String age(BackgroundTab backgroundTab) {
            return "Age: " + backgroundTab.getAge();
        }

        public static String alignment(BackgroundTab backgroundTab) {
            return "Alignment: " + backgroundTab.getAlignment();
        }

        public static String ammo(int ammo) {
            return "Ammo: " + ammo;
        }

        public static String armorClass(Armor armor) {
            return "Armor Class: " + armor.getBaseArmorClass();
        }

        public static String armorType(Armor armor) {
            return "Armor Type: " + armor.getArmorType().getName();
        }

        public static String armoredAC(ArmorTab armorTab) {
            return "AC (armored): " + armorTab.getArmorClass();
        }

        public static String attackStat(WeaponAttackStat attackStat) {
            return "Attack Stat: " + attackStat.getName();
        }

        public static String baseAC(Armor armor) {
            return "Base AC: " + armor.getBaseArmorClass();
        }

        public static String bonus(PlayerSkill skill) {
            return BONUS + ": " + skill.getBonus();
        }

        public static String carried(MCDNDItem item) {
            return item.isCarried() ? "Carried" : "Not Carried";
        }

        public static String carryingMax(double carryingMax) {
            return "Carrying Max: " + carryingMax;
        }

        public static String castTime(String castTime) {
            return "Cast Time: " + castTime;
        }

        public static String clazz(SpellcasterClass spellcasterClass) {
            return "Class: " + spellcasterClass.getName();
        }

        public static String coin(Coin coin) {
            return coin.getShortName() + ": " + coin.getAmount();
        }

        public static String[] coinCarriedDescription(Wealth wealth) {
            return new String[]{coin(wealth.getCopper()), coin(wealth.getSilver()), coin(wealth.getElectrum()), coin(wealth.getElectrum()), coin(wealth.getGold()), coin(wealth.getPlatinum())};
        }

        public static String coinWeight(double weight) {
            return "Coin: " + weight;
        }

        public static String components(String components) {
            return "Components: " + components;
        }

        public static String concentration(boolean needsConcentration) {
            return "Needs Concentration? " + (needsConcentration ? "Yes" : "No");
        }

        public static String critDamage(Dice critDamageDice) {
            return "Crit Damage: " + critDamageDice.getAmount() + "d" + critDamageDice.getSides();
        }

        public static String critOn(int critMin) {
            return "Crit On: " + critMin;
        }

        public static String current(ArmorTab armorTab) {
            return "Current: " + armorTab.getUnarmoredBonus().getName();
        }

        public static String current(int usesLeft) {
            return "Current: " + usesLeft;
        }

        public static String currentHitPoints(HitPoints hitPoints) {
            return "Current: " + hitPoints.getCurrent();
        }

        public static String currentXP(Experience experience) {
            return "Current XP: " + experience.getXP();
        }

        public static String customDC(int customDC) {
            return "Custom DC: " + customDC;
        }

        public static String damageBonus(int damageBonus) {
            return "Damage Bonus: " + damageBonus;
        }

        public static String damageDice(Dice damageDice) {
            return "Damage Dice: " + damageDice.getAmount() + "d" + damageDice.getSides();
        }

        public static String damageType(String damageType) {
            return "Damage Type: " + damageType;
        }

        public static String duration(String duration) {
            return "Duration: " + duration;
        }

        public static String encumbered(double encumbered) {
            return "Encumbered: " + encumbered;
        }

        public static String[] experience(Experience experience, ClassLevels classLevels) {
            return new String[]{overallLevel(experience, classLevels), proficiencyBonus(experience, classLevels), currentXP(experience), xpForNextLevel(experience, classLevels)};
        }

        public static String eyes(BackgroundTab backgroundTab) {
            return "Eyes: " + backgroundTab.getEyes();
        }

        public static String gainedFrom(SpellcasterClass spellcasterClass) {
            return "Gained from " + spellcasterClass.getName();
        }

        public static String gender(BackgroundTab backgroundTab) {
            return "Gender: " + backgroundTab.getGender();
        }

        public static String hair(BackgroundTab backgroundTab) {
            return "Hair: " + backgroundTab.getHair();
        }

        public static String hasComponents(List<String> components) {
            return "Has Components: " + (components.isEmpty() ? "No" : "Yes");
        }

        public static String hasSpeedPenalty(Armor armor) {
            return (armor.hasSpeedPenalty() ? "" : "No ") + "Speed Penalty";
        }

        public static String hasStealthPenalty(Armor armor) {
            return (armor.hasStealthPenalty() ? "" : "No ") + "Stealth Penalty";
        }

        public static String healAmount(Dice healAmount) {
            return "Heal Amount: " + healAmount.getAmount() + "d" + healAmount.getSides();
        }

        public static String heavilyEncumbered(double heavilyEncumbered) {
            return "Heavily Encumbered: " + heavilyEncumbered;
        }

        public static String height(BackgroundTab backgroundTab) {
            return "Height: " + backgroundTab.getHeight();
        }

        public static String[] hitDice(HitDice hitDice) {
            List<String> list = new ArrayList<>();
            hitDice.getHitDice().forEach((sides, amount) -> list.add(amount + " x d" + sides));
            return list.toArray(new String[0]);
        }

        public static String[] hitPoints(HitPoints hitPoints) {
            return new String[]{"HP: " + hitPoints.getMax() + " / " + hitPoints.getMax(), "Temp HP: " + hitPoints.getTemp()};
        }

        public static String initiative(Initiative initiative, AbilityScore dex) {
            return INITIATIVE + ": " + initiative.getInitiative(dex);
        }

        public static String inventoryWeight(double weight) {
            return INVENTORY + ": " + weight;
        }

        public static String isProficient(boolean proficient) {
            return "Proficient? " + (proficient ? "Yes" : "No");
        }

        public static String isUnarmored(Armor armor) {
            return "Is" + (armor.isUnarmored() ? " " : " Not ") + "Unarmored";
        }

        public static String isWorn(Armor armor) {
            return "Is" + (armor.isWorn() ? " " : " Not ") + "Worn";
        }

        public static String[] itemDesc(MCDNDItem item) {
            return new String[]{"Carried? " + (item.isCarried() ? "Yes" : "No"), "Quantity: " + item.getQuantity(), "Weight: " + item.getWeight(), "Description: " + item.getDescription()};
        }

        public static String level(int level) {
            return "Level: " + level;
        }

        public static String magicBonus(Armor armor) {
            return "Magic Bonus: " + armor.getMagicBonus();
        }

        public static String magicBonus(AbstractWeapon weapon) {
            return "Magic Bonus: " + weapon.getMagicBonus();
        }

        public static String maxHitPoints(HitPoints hitPoints) {
            return "Max: " + hitPoints.getMax();
        }

        public static String maxUses(int max) {
            return "Max Uses: " + max;
        }

        public static String mod(AbilityScore score) {
            return "Mod: " + score.getMod();
        }

        public static String name(BioAndInfo bioAndInfo) {
            return "".equals(bioAndInfo.getName()) ? "Not Set" : bioAndInfo.getName();
        }

        public static String otherBonus(SpellDamage spellDamage) {
            return "Other Bonus: " + spellDamage.getBonus();
        }

        public static String otherWeight(double weight) {
            return "Other: " + weight;
        }

        public static String overallLevel(Experience experience, ClassLevels classLevels) {
            return "Overall Level: " + experience.getOverallLevel(classLevels);
        }

        public static String plusStat(boolean plusStat) {
            return PLUS_STAT + " " + (plusStat ? "Yes" : "No");
        }

        public static String prepared(Prepared prepared) {
            return "Prepared? " + prepared.getName();
        }

        public static String proficiencyBonus(Experience experience, ClassLevels classLevels) {
            return "Proficiency Bonus: " + experience.getProficiencyBonus(classLevels);
        }

        public static String proficient(AbilityScore score) {
            return "Proficient? " + (score.isProficient() ? "Yes" : "No");
        }

        public static String pushDragLift(double pushDragLift) {
            return "Push/Drag/Lift: " + pushDragLift;
        }

        public static String quantity(MCDNDItem item) {
            return "Quantity: " + item.getQuantity();
        }

        public static String range(String range) {
            return "Range: " + range;
        }

        public static String recharge(Recharge recharge) {
            return "Recharge: " + recharge.getName();
        }

        public static String ritual(boolean isRitual) {
            return "Ritual? " + (isRitual ? "Yes" : "No");
        }

        public static String rollHitDie(int sides) {
            return "Roll d" + sides + "(s)";
        }

        public static String saveMod(AbilityScore score, ClassLevels classLevels, Experience experience) {
            return "Save Mod: " + score.getSaveMod(classLevels, experience);
        }

        public static String score(AbilityScore score) {
            return "Score: " + score.getScore();
        }

        public static String[] scoreLore(AbilityScore score, ClassLevels classLevels, Experience experience) {
            return new String[]{score(score), mod(score), proficient(score), saveMod(score, classLevels, experience)};
        }

        public static String size(BackgroundTab backgroundTab) {
            return "Size: " + backgroundTab.getSize();
        }

        public static String[] sorceryPoints(ClassLevels classLevels, SpellbookTab spellbookTab) {
            return new String[]{"Sorcerer Specific", "Used: " + spellbookTab.getSorceryPointsUsed(), "Max: " + spellbookTab.getSorceryPointsMax(classLevels)};
        }

        public static String speed(CoreStatsTab coreStatsTab) {
            return "Speed: " + coreStatsTab.getSpeed();
        }

        public static String spellLevel(int level) {
            return "Spell Level: " + (level == 0 ? "Cantrip" : "Level " + level);
        }

        public static String[] spellSlots(int spellSlotsUsed, int maxSpellSlots, int spellLevel) {
            return new String[]{"Warlock Specific", "Used: " + spellSlotsUsed, "Max: " + maxSpellSlots, "Level: " + spellLevel};
        }

        public static String[] spellSlots(ClassLevels classLevels, SpellbookTab spellbookTab) {
            return new String[]{"Warlock Specific", "Used: " + spellbookTab.getWarlockSpellSlotsUsed(), "Max: " + warlockSlots(classLevels), "Level: " + warlockSpellAmount(classLevels)};
        }

        public static String spellType(SpellType spellType) {
            return "Spell Type: " + spellType.getName();
        }

        public static String[] spellcastingTable(ClassLevels classLevels, CoreStats coreStats, Experience experience, SpellcasterClass spellcasterClass) {
            return new String[]{"Cantrips Known: " + spellcasterClass.getCantripsAmount(classLevels), "Spells Known: " + spellcasterClass.getSpellsAmount(classLevels), "Can Prepare: " + spellcasterClass.getPreparedSpells(coreStats, classLevels), "Save DC: " + spellcasterClass.getSpellSaveDC(classLevels, coreStats, experience)};
        }

        public static String target(String targetArea) {
            return "Target/AoE: " + targetArea;
        }

        public static String tempHitPoints(HitPoints hitPoints) {
            return "Temp: " + hitPoints.getTemp();
        }

        public static String toHit(int toHit) {
            return "To Hit: " + toHit;
        }

        public static String total(Wealth wealth) {
            return "Total: " + ((wealth.getCopper().getAmount() / 100) + (wealth.getSilver().getAmount() / 10) + (wealth.getElectrum().getAmount() / 2) + wealth.getGold().getAmount() + (wealth.getPlatinum().getAmount() * 10));
        }

        public static String total(AbilityScore abilityScore, ClassLevels classLevels, Experience experience, PlayerSkill skill) {
            return "Total: " + skill.getTotal(abilityScore, classLevels, experience);
        }

        public static String total(int amount) {
            return "Total: " + amount;
        }

        public static String total(int level, List<Spell> spells) {
            return "Total: " + spells.stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).size();
        }

        public static String totalAC(Armor armor) {
            return "Total AC: " + armor.getTotalArmorClass();
        }

        public static String totalInitiative(Initiative initiative, AbilityScore dex) {
            return "Total: " + initiative.getInitiative(dex);
        }

        public static String totalWeight(List<MCDNDItem> items, Wealth wealth, Weight weight) {
            return "Total: " + (weight.getInventory(items) + weight.getCoin(wealth) + weight.getOther());
        }

        public static String unarmoredAC(ArmorTab armorTab) {
            return "Armor Class (unarmored)" + armorTab.getUnarmoredClass();
        }

        public static String used(int used) {
            return "Used: " + used;
        }

        public static String vision(BackgroundTab backgroundTab) {
            return "Vision: " + backgroundTab.getVision();
        }

        private static int warlockSlots(ClassLevels classLevels) {
            SpellcasterClass sc = SpellcasterClass.WARLOCK;
            int level = classLevels.getWarlock();
            switch (level) {
                case 1:
                case 2:
                    return sc.get1stLevelAmount(classLevels);
                case 3:
                case 4:
                    return sc.get2ndLevelAmount(classLevels);
                case 5:
                case 6:
                    return sc.get3rdLevelAmount(classLevels);
                case 7:
                case 8:
                    return sc.get4thLevelAmount(classLevels);
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    return sc.get5thLevelAmount(classLevels);
                default:
                    return 0;

            }
        }

        private static int warlockSpellAmount(ClassLevels classLevels) {
            switch (classLevels.getWarlock()) {
                case 1:
                    return 1;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    return 2;
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    return 3;
                case 17:
                case 18:
                case 19:
                case 20:
                    return 4;
                default:
                    return 0;
            }
        }

        public static String weight(BackgroundTab backgroundTab) {
            return "Weight: " + backgroundTab.getWeight();
        }

        public static String weight(MCDNDItem item) {
            return "Weight: " + item.getWeight();
        }

        public static String xpForNextLevel(Experience experience, ClassLevels classLevels) {
            return "XP for next level: " + experience.getXPForNextLevel(classLevels);
        }
    }

    public static class Messages {

        public static final String LOAD_COMPLETE = "Let the adventures begin...";
        public static final String PREFIX = "[MCDNDS] ";
        public static final String CLASS_ACTION_DELETED = PREFIX + "Class action deleted.";
        public static final String CLASS_RESOURCE_DELETED = PREFIX + "Class resource deleted.";
        public static final String ITEM_DELETED = PREFIX + "Item deleted.";
        public static final String NO_PERMISSION = PREFIX + "You do not have permission to run this command.";
        public static final String PLAYER_ONLY = PREFIX + "Only a player can run this command.";
        public static final String CHARACTER_DNE = PREFIX + "That character does not exist or you do not have access to that character.";
        public static final String CHARACTER_ALREADY_EXISTS = PREFIX + "That character already exists.";
        public static final String CHARACTER_CREATED = PREFIX + "Character created.";
        public static final String ARMOR_DELETED = PREFIX + "Armor delted.";

        private Messages() {

        }

        public static String[] initiative(BioAndInfo bioAndInfo, String name, String result) {
            return new String[]{name + " (as " + bioAndInfo.getName() + ") has rolled for Initiative.", "The result is " + result};
        }

        public static String malformedDiceInput(String diceInput) {
            return "Invalid dice input: " + diceInput;
        }

        public static String[] rolledHitDice(BioAndInfo bioAndInfo, String name, int result, Dice dice) {
            return new String[]{name + " (as " + bioAndInfo.getName() + ") has expended a hit die " + dice.getAmount() + "d" + dice.getAmount() + ".", "The result was " + result + "."};
        }

        //TODO need to format for crit and crit miss
        //TODO this need to include bonuses
        @Deprecated
        public static String[] savingThrow(AbilityScore score, BioAndInfo bioAndInfo, String name, int first, int second) {
            return new String[]{name + " (as " + bioAndInfo.getName() + ") has rolled a " + score.getName() + " saving throw.", "The results are: " + first + " | " + second};
        }
    }

    public static class Permissions {

        private static final String BASE = "mcdnd.";
        public static final String CALLBACK = BASE + Commands.CALLBACK_NAME;
        public static final String CHARACTER = BASE + Commands.PLAYER_SHEET_NAME;
        public static final String DM = BASE + "dm";
        public static final String NPC = BASE + Commands.NPC_NAME;

        private Permissions() {

        }
    }
}
