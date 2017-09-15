package io.musician101.mcdndsimple.spigot.util;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.ClassLevels;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.Experience;
import io.musician101.mcdndsimple.common.character.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.spell.Spell;
import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.weapon.RangedWeapon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemRepresentation {

    private ItemRepresentation() {

    }

    public static ItemStack armor(Armor armor) {
        ItemStack itemStack = new ItemStack(getArmorMaterial(armor.getArmorType()));
        setName(itemStack, armor.getName());
        setLore(itemStack, Arrays.asList("Armor Class: " + armor.getBaseArmorClass(), "Armor Type: " + armor.getArmorType().getName(), "Magic Bonus: " + armor.getMagicBonus(), "Speed Penalty: " + armor.hasSpeedPenalty(), "Stealth Penalty: " + armor.hasStealthPenalty(), "Unarmored?: " + armor.isUnarmored(), "Worn?: " + armor.isWorn()));

        return itemStack;
    }

    public static ItemStack armorType(ArmorType armorType) {
        ItemStack itemStack = new ItemStack(getArmorMaterial(armorType));
        setName(itemStack, armorType.getName());
        return itemStack;
    }

    private static Material getArmorMaterial(ArmorType armorType) {
        Material material = Material.GOLD_CHESTPLATE;
        if (armorType == ArmorType.LIGHT) {
            material = Material.LEATHER_CHESTPLATE;
        }
        else if (armorType == ArmorType.MEDIUM) {
            material = Material.IRON_CHESTPLATE;
        }
        else if (armorType == ArmorType.HEAVY) {
            material = Material.DIAMOND_CHESTPLATE;
        }

        return material;
    }

    public static ItemStack meleeWeapon(MeleeWeapon weapon, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        setName(itemStack, weapon.getName());
        List<String> lore = new ArrayList<>();
        lore.add(MenuText.isProficient(weapon.isProficient()));
        lore.add(MenuText.attackStat(weapon.getAttackStat()));
        lore.add(MenuText.magicBonus(weapon.getMagicBonus()));
        lore.add(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)));
        lore.add(MenuText.damageDice(weapon.getDamageDice()));
        lore.add(MenuText.damageBonus(weapon.getDamageBonus(coreStats)));
        lore.add(MenuText.damageType(weapon.getDamageType()));
        lore.add(MenuText.critDamage(weapon.getCritDamageDice()));
        lore.add(MenuText.critOn(weapon.getCritMin()));
        setLore(itemStack, lore);
        return itemStack;
    }

    public static ItemStack rangedWeapon(RangedWeapon weapon, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        ItemStack itemStack = new ItemStack(Material.BOW);
        setName(itemStack, weapon.getName());
        List<String> lore = new ArrayList<>();
        lore.add(MenuText.isProficient(weapon.isProficient()));
        lore.add(MenuText.attackStat(weapon.getAttackStat()));
        lore.add(MenuText.magicBonus(weapon.getMagicBonus()));
        lore.add(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)));
        lore.add(MenuText.damageDice(weapon.getDamageDice()));
        lore.add(MenuText.damageBonus(weapon.getDamageBonus(coreStats)));
        lore.add(MenuText.damageType(weapon.getDamageType()));
        lore.add(MenuText.critDamage(weapon.getCritDamageDice()));
        lore.add(MenuText.critOn(weapon.getCritMin()));
        lore.add(MenuText.ammo(weapon.getAmmo()));
        setLore(itemStack, lore);
        return itemStack;
    }

    private static void setLore(ItemStack itemStack, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    private static void setName(ItemStack itemStack, String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
    }

    public static ItemStack spell(Spell spell) {
        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        setName(itemStack, spell.getName());
        List<String> lore = new ArrayList<>();
        lore.add(MenuText.spellLevel(spell.getLevel()));
        lore.add(MenuText.spellType(spell.getSpellType()));
        lore.add(MenuText.gainedFrom(spell.getGainedFrom()));
        lore.add(MenuText.components(spell.getComponents()));
        lore.add(MenuText.castTime(spell.getCastTime()));
        lore.add(MenuText.duration(spell.getDuration()));
        lore.add(MenuText.target(spell.getTargetArea()));
        lore.add(MenuText.range(spell.getRange()));
        setLore(itemStack, lore);
        return itemStack;
    }

    public static ItemStack unarmoredBonus(UnarmoredBonus unarmoredBonus) {
        Material material;
        switch (unarmoredBonus) {
            case BARBARIAN: {
                material = Material.STONE_SWORD;
                break;
            }
            case DRACONIC_RESILIENCE: {
                material = Material.DRAGONS_BREATH;
                break;
            }
            case MONK: {
                material = Material.STICK;
                break;
            }
            default: {
                material = Material.CHAINMAIL_CHESTPLATE;
            }
        }

        ItemStack itemStack = new ItemStack(material);
        setName(itemStack, unarmoredBonus.getName());
        return itemStack;
    }
}
