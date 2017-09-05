package io.musician101.mcdndsimple.common.serialization;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.BioAndInfo;
import io.musician101.mcdndsimple.common.character.PlayerSheet;
import io.musician101.mcdndsimple.common.character.ClassAction;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.ClassResource;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.HitDice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.MCDNDItem;
import io.musician101.mcdndsimple.common.character.CharacterSheet;
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
import io.musician101.mcdndsimple.common.character.skill.Skill;
import io.musician101.mcdndsimple.common.character.skill.SkillProficiency;
import io.musician101.mcdndsimple.common.character.spell.SaveDCType;
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
import io.musician101.mcdndsimple.common.character.weapon.AbstractWeapon;
import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.weapon.RangedWeapon;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class MCDNDSerializer<S>
{
    public abstract S serialize(PlayerSheet playerSheet);

    protected abstract S serialize(BioAndInfo bioAndInfo);

    protected abstract S serialize(ArmorTab armorTab);

    protected abstract S serialize(Armor armor);

    protected abstract S serialize(UnarmoredBonus unarmoredBonus);

    protected abstract S serialize(BackgroundTab backgroundTab);

    protected abstract S serialize(ClassTab classTab);

    protected abstract S serialize(ClassLevels classLevels);

    protected abstract S serialize(ClassAction classAction);

    protected abstract S serialize(ClassResource classResource);

    protected abstract S serialize(CoreStatsTab coreStatsTab);

    protected abstract S serialize(Bonuses bonuses);

    protected abstract S serialize(MeleeBonus meleeBonus);

    protected abstract S serialize(RangedBonus rangedBonus);

    protected abstract S serialize(SpellcastingBonus spellcastingBonus);

    protected abstract S serialize(CoreStats coreStats);

    protected abstract S serialize(AbilityScore abilityScore);

    protected abstract S serialize(Experience experience);

    protected abstract S serialize(HitDice hitDice);

    protected abstract S serialize(Dice dice);

    protected abstract S serialize(HitPoints hitPoints);

    protected abstract S serialize(InventoryTab inventoryTab);

    protected abstract S serialize(MCDNDItem item);

    protected abstract S serialize(Wealth wealth);

    protected abstract S serialize(Coin coin);

    protected abstract S serialize(Weight weight);

    protected abstract S serialize(SkillsTab skillsTab);

    protected abstract S serialize(Skill skill);

    protected abstract S serialize(SkillProficiency skillProficiency);

    protected abstract S serialize(SpellbookTab spellbookTab, ClassLevels classLevels);

    protected abstract S serialize(Spell spell);

    protected abstract S serialize(SpellDamage spellDamage);

    protected abstract S serialize(SpellHealing spellHealing);

    protected abstract S serialize(SpellSave spellSave);

    protected abstract S serialize(SaveDCType saveDCType);

    protected abstract S serialize(SpellcasterClass spellcasterClass);

    protected abstract S serialize(SpellType spellType);

    protected abstract S serialize(WeaponsTab weaponsTab);

    // Adding simplicity and avoiding recursive calls
    protected abstract S serialize(AbstractWeapon abstractWeapon, S weaponData);

    protected abstract S serialize(MeleeWeapon meleeWeapon);

    protected abstract S serialize(RangedWeapon rangedWeapon);

    protected abstract S serialize(CharacterSheet characterSheet);

    protected abstract S serialize(ArmorType armorType);

    protected abstract S serialize(Recharge recharge);

    protected <E> List<S> serialize(List<E> list, Function<E, S> mapper)
    {
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}
