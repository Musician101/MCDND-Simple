package io.musician101.mcdndsimple.common;

import io.musician101.mcdndsimple.common.character.AbstractPlayer;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActions;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerHitPoints;
import io.musician101.mcdndsimple.common.character.nonplayer.TraitsBackground;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkill;
import io.musician101.mcdndsimple.common.character.nonplayer.skill.NonPlayerSkills;
import io.musician101.mcdndsimple.common.character.player.AbilityScore;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.DeathSavingThrows;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.HitDice;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.PlayerSheet;
import io.musician101.mcdndsimple.common.character.player.Rechargeable;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.character.player.bonus.MeleeBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.character.player.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.player.outputoption.CoreSkillsOutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.OutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.SavingThrowOutputOptions;
import io.musician101.mcdndsimple.common.character.player.outputoption.WeaponsSpellMiscOutputOptions;
import io.musician101.mcdndsimple.common.character.player.skill.PlayerSkill;
import io.musician101.mcdndsimple.common.character.player.spell.MacroOptions;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.character.player.spell.SpellSave;
import io.musician101.mcdndsimple.common.character.player.spell.SpellcasterClass;
import io.musician101.mcdndsimple.common.character.player.spell.StatBonus;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.character.player.tab.BackgroundTab;
import io.musician101.mcdndsimple.common.character.player.tab.ClassTab;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.character.player.tab.Initiative;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.character.player.tab.SkillsTab;
import io.musician101.mcdndsimple.common.character.player.tab.SpellbookTab;
import io.musician101.mcdndsimple.common.character.player.tab.WeaponsTab;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.Weapon;
import io.musician101.musicianlibrary.java.minecraft.gui.chest.ChestGUI;
import io.musician101.musicianlibrary.java.minecraft.gui.chest.ChestGUIBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ChestGUIs<B extends ChestGUIBuilder<B, C, G, I, J, P, S, T>, C, G extends ChestGUI<C, G, I, J, P, S>, I, J, P, S, T> {

    public abstract void armor(@Nonnull P player, @Nonnull Armor armor, @Nonnull List<Armor> armorList, @Nullable G prevGUI);

    public abstract void armorList(@Nonnull P player, @Nonnull List<Armor> armorList, int page, @Nullable G prevGUI);

    public abstract void armorTab(@Nonnull P player, @Nonnull ArmorTab armorTab, @Nullable G prevGUI);

    public abstract void armorType(@Nonnull P player, @Nonnull Armor armor, int page, @Nullable G prevGUI);

    public abstract void attackStat(@Nonnull P player, int page, @Nonnull Weapon weapon, @Nullable G prevGUI);

    public abstract void backgroundTab(@Nonnull P player, @Nonnull BackgroundTab backgroundTab, @Nullable G prevGUI);

    public abstract void bioAndInfo(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull PlayerSheet playerSheet, @Nullable G prevGUI);

    public abstract void bonuses(@Nonnull P player, @Nonnull Bonuses bonuses, @Nullable G prevGUI);

    @Nonnull
    protected abstract B builder(int size, int backButtonSlot, @Nonnull String name, @Nonnull P player, @Nullable G prevGUI);

    public abstract void characterSheet(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull CharacterSheet characterSheet, @Nullable G prevGUI);

    public abstract void classAction(@Nonnull P player, @Nonnull ClassAction classAction, @Nonnull List<ClassAction> classActions, @Nullable G prevGUI);

    public abstract void classActions(@Nonnull P player, @Nonnull List<ClassAction> classActions, int page, @Nullable G prevGUI);

    public abstract void classLevels(@Nonnull P player, @Nonnull ClassLevels classLevels, @Nullable G prevGUI);

    public abstract void classResource(@Nonnull P player, @Nonnull ClassResource classResource, @Nonnull List<ClassResource> classResources, @Nullable G prevGUI);

    public abstract void classResources(@Nonnull P player, @Nonnull List<ClassResource> classResources, int page, @Nullable G prevGUI);

    public abstract void classTab(@Nonnull P player, @Nonnull ClassTab classTab, @Nullable G prevGUI);

    public abstract void coreSkillsOutputOptions(@Nonnull P player, @Nonnull CoreSkillsOutputOptions coreSkillsOutputOptions, @Nonnull G prevGUI);

    public abstract void coreStatsTab(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStatsTab coreStatsTab, @Nullable G prevGUI);

    @Nonnull
    protected abstract S createBook(@Nonnull P player, @Nonnull String title, List<String> pages);

    public abstract void deathSavingThrows(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull DeathSavingThrows deathSavingThrows, @Nonnull Dice savingThrowBonus, @Nonnull HitPoints hitPoints, @Nullable G prevGUI);

    public abstract void editControllers(@Nonnull P player, int page, @Nonnull AbstractPlayer abstractPlayer, @Nullable G prevGUI);

    public abstract void experience(@Nonnull P player, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nullable G prevGUI);

    public abstract void gainedFrom(@Nonnull P player, int page, @Nonnull ClassAction classAction, @Nullable G prevGUI);

    public abstract void healing(@Nonnull P player, @Nonnull SpellHealing spellHealing, @Nullable G prevGUI);

    public abstract void hitDice(@Nonnull P player, @Nonnull AbilityScore constitution, @Nonnull BioAndInfo bioAndInfo, @Nonnull HitDice hitDice, @Nullable G prevGUI);

    public abstract void hitPoints(@Nonnull P player, @Nonnull HitPoints hitPoints, @Nullable G prevGUI);

    public abstract void initiative(@Nonnull P player, @Nonnull AbilityScore dex, @Nonnull BioAndInfo bioAndInfo, @Nonnull Initiative initiative, @Nullable G prevGUI);

    public abstract void inventory(@Nonnull P player, @Nonnull List<MCDNDItem> items, int page, @Nullable G prevGUI);

    public abstract void inventoryTab(@Nonnull P player, @Nonnull CoreStats coreStats, @Nonnull InventoryTab inventoryTab, @Nullable G prevGUI);

    public abstract void item(@Nonnull P player, @Nonnull MCDNDItem item, @Nonnull List<MCDNDItem> items, @Nullable G prevGUI);

    public abstract void macroOptions(@Nonnull P player, @Nonnull MacroOptions macroOptions, @Nullable G prevGUI);

    public abstract void meleeBonus(@Nonnull P player, @Nonnull MeleeBonus meleeBonus, @Nullable G prevGUI);

    public abstract void meleeWeapon(P player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, List<MeleeWeapon> weapons, MeleeBonus meleeBonus, MeleeWeapon weapon, G prevGUI);

    public abstract void meleeWeapons(@Nonnull P player, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<MeleeWeapon> meleeWeapons, @Nonnull MeleeBonus meleeBonus, @Nullable G prevGUI);

    public abstract void nonPlayer(@Nonnull P player, @Nonnull NonPlayer nonPlayer, @Nullable G prevGUI);

    public abstract void nonPlayerAbilityScore(@Nonnull P player, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScore abilityScore, @Nullable G prevGUI);

    public abstract void nonPlayerAction(@Nonnull P player, @Nonnull NonPlayerAction nonPlayerAction, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable G prevGUI);

    public abstract void nonPlayerActionType(@Nonnull P player, int page, @Nonnull NonPlayerAction nonPlayerAction, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable G prevGUI);

    public abstract void nonPlayerActions(@Nonnull P player, @Nonnull NonPlayerActions nonPlayerActions, @Nullable G prevGUI);

    public abstract void nonPlayerActions(@Nonnull P player, int page, @Nonnull List<NonPlayerAction> nonPlayerActions, @Nullable G prevGUI);

    public abstract void nonPlayerCoreStats(@Nonnull P player, @Nonnull NonPlayer nonPlayer, @Nonnull CoreStats<NonPlayerAbilityScore> coreStats, @Nonnull G prevGUI);

    public abstract void nonPlayerHitPoints(@Nonnull P player, @Nonnull NonPlayerHitPoints hitPoints, @Nullable G prevGUI);

    public abstract void nonPlayerSheet(@Nonnull P player, @Nonnull NonPlayer nonPlayer, @Nullable G prevGUI);

    public abstract void nonPlayerSkills(@Nonnull P player, @Nonnull CoreStats<NonPlayerAbilityScore> coreStats, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerSkills skills, @Nullable G prevGUI);

    public abstract void nonPlayers(@Nonnull P player, @Nonnull List<NonPlayer> nonPlayers, int page);

    public abstract void outputOptions(@Nonnull P player, @Nonnull OutputOptions outputOptions, @Nullable G prevGUI);

    @Nonnull
    protected abstract <O> B paged(@Nonnull String name, @Nonnull P player, @Nullable G prevGUI, int page, @Nonnull Function<O, S> itemStackMapper, @Nullable Function<O, BiConsumer<G, P>> actionMapper, @Nonnull BiConsumer<P, Integer> pageNavigator, @Nonnull List<O> contents);

    @SafeVarargs
    @Nonnull
    protected final <O> B paged(@Nonnull String name, @Nonnull P player, @Nullable G prevGUI, int page, @Nonnull Function<O, S> itemStackMapper, @Nullable Function<O, BiConsumer<G, P>> actionMapper, @Nonnull BiConsumer<P, Integer> pageNavigator, @Nonnull O... contents) {
        return paged(name, player, prevGUI, page, itemStackMapper, actionMapper, pageNavigator, Arrays.asList(contents));
    }

    public abstract void playerAbilityScore(@Nonnull P player, @Nonnull PlayerAbilityScore abilityScore, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nullable G prevGUI);

    public abstract void playerCoreStats(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats<PlayerAbilityScore> coreStats, @Nonnull Experience experience, @Nullable G prevGUI);

    public abstract void playerSheet(@Nonnull P player, @Nonnull PlayerSheet playerSheet, @Nullable G prevGUI);

    public abstract void players(@Nonnull P player, @Nonnull List<PlayerSheet> playerSheets, int page);

    public abstract void prepared(@Nonnull P player, int page, @Nonnull Spell spell, @Nullable G prevGUI);

    public abstract void rangedBonus(@Nonnull P player, @Nonnull RangedBonus rangedBonus, @Nullable G prevGUI);

    public abstract void rangedWeapon(P player, BioAndInfo bioAndInfo, ClassLevels classLevels, CoreStats coreStats, Experience experience, List<RangedWeapon> weapons, RangedBonus rangedBonus, RangedWeapon weapon, G prevGUI);

    public abstract void rangedWeapons(@Nonnull P player, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<RangedWeapon> rangedWeapons, @Nonnull RangedBonus rangedBonus, @Nullable G prevGUI);

    public abstract <R extends Rechargeable> void recharge(@Nonnull P player, @Nonnull R rechargeable, int page, @Nullable G prevGUI);

    protected abstract void rollHitDie(@Nonnull P player, @Nonnull AbilityScore constitution, @Nonnull BioAndInfo bioAndInfo, int amount, int sides);

    public abstract void savingThrowOutputOptions(@Nonnull P player, @Nonnull SavingThrowOutputOptions savingThrowOutputOptions, @Nullable G prevGUI);

    public abstract void skill(@Nonnull P player, @Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScore abilityScore, @Nonnull NonPlayerSkill skill, @Nullable G prevGUI);

    public abstract void skill(@Nonnull P player, @Nonnull PlayerAbilityScore abilityScore, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull Experience experience, @Nonnull Dice skillBonus, @Nonnull PlayerSkill skill, @Nullable G prevGUI);

    public abstract void skillProficiency(@Nonnull P player, @Nonnull PlayerSkill skill, int page, @Nullable G prevGUI);

    public abstract void skillsTab(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats<PlayerAbilityScore> coreStats, @Nonnull Dice skillBonus, @Nonnull Experience experience, @Nonnull SkillsTab skillsTab, @Nullable G prevGUI);

    public abstract void spell(@Nonnull P player, @Nonnull Spell spell, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable G prevGUI);

    public abstract void spellBook(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull List<Spell> spells, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable G prevGUI);

    public abstract void spellDamage(@Nonnull P player, @Nonnull SpellDamage spellDamage, @Nullable G prevGUI);

    public abstract void spellDashboard(@Nonnull P player, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nullable G prevGUI);

    public abstract void spellDescription(@Nonnull P player, @Nonnull Spell spell, @Nullable G prevGUI);

    public abstract void spellSave(@Nonnull P player, @Nonnull SpellSave spellSave, @Nullable G prevGUI);

    public abstract void spellType(@Nonnull P player, int page, @Nonnull Spell spell, @Nullable G prevGUI);

    public abstract void spellbookTab(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable G prevGUI);

    public abstract void spellcasterClass(@Nonnull P player, int page, @Nonnull Consumer<SpellcasterClass> valueSetter, @Nonnull Predicate<SpellcasterClass> glowApplier, @Nullable G prevGUI);

    @Nonnull
    protected abstract S spellcasterIcon(@Nonnull SpellcasterClass spellCasterClass, @Nonnull Predicate<SpellcasterClass> glowApplier);

    @Nonnull
    protected abstract S spellcasterIcon(@Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellbookTab spellbookTab, @Nonnull SpellcasterClass spellcasterClass);

    public abstract void spellcastingBonus(@Nonnull P player, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable G prevGUI);

    public abstract void spells(@Nonnull P player, int level, @Nonnull List<Spell> spells, int page, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull SpellcastingBonus spellcastingBonus, @Nullable G prevGUI);

    public abstract void statBonus(@Nonnull P player, int page, Consumer<StatBonus> valueSetter, @Nonnull Predicate<StatBonus> glowApplier, @Nullable G prevGUI);

    @Nonnull
    protected abstract S statBonusIcon(@Nonnull StatBonus statBonus, @Nonnull Predicate<StatBonus> glowApplier);

    public abstract void traitsBackground(@Nonnull P player, @Nonnull TraitsBackground traitsBackground, @Nullable G prevGUI);

    public abstract void unarmoredBonus(@Nonnull P player, @Nonnull ArmorTab armorTab, int page, @Nullable G prevGUI);

    public abstract void wealth(@Nonnull P player, @Nonnull Wealth wealth, @Nullable G prevGUI);

    public abstract void weaponsSpellMiscOutputOptions(@Nonnull P player, @Nonnull WeaponsSpellMiscOutputOptions weaponsSpellMiscOutputOptions, @Nonnull G prevGUI);

    public abstract void weaponsTab(@Nonnull P player, @Nonnull BioAndInfo bioAndInfo, @Nonnull ClassLevels classLevels, @Nonnull CoreStats coreStats, @Nonnull Experience experience, @Nonnull MeleeBonus meleeBonus, @Nonnull RangedBonus rangedBonus, @Nonnull WeaponsTab weaponsTab, @Nullable G prevGUI);

    public abstract void weight(@Nonnull P player, @Nonnull CoreStats coreStats, @Nonnull List<MCDNDItem> items, @Nonnull Wealth wealth, @Nonnull Weight weight, @Nullable G prevGUI);
}
