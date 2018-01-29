package io.musician101.mcdndsimple.spigot.util;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemRepresentation {

    private ItemRepresentation() {

    }

    public static ItemStack weaponAttackStat(WeaponAttackStat stat) {
        SpigotIconBuilder builder;
        switch (stat) {
            case CHA:
                builder = SpigotIconBuilder.builder(Material.SKULL_ITEM).durability(3);
                break;
            case CON:
                builder = SpigotIconBuilder.builder(Material.GOLDEN_APPLE);
                break;
            case DEX:
                builder = SpigotIconBuilder.builder(Material.BOW);
                break;
            case FINESSE:
                builder = SpigotIconBuilder.builder(Material.DIAMOND_SWORD);
                break;
            case INT:
                builder = SpigotIconBuilder.builder(Material.BOOK_AND_QUILL);
                break;
            case STR:
                builder = SpigotIconBuilder.builder(Material.IRON_SWORD);
                break;
            case WIS:
                builder = SpigotIconBuilder.builder(Material.ENCHANTED_BOOK);
                break;
                default:
                    return SpigotIconBuilder.builder(Material.BARRIER).name("I'M AN ERROR! PLEASE REPORT ME!").build();
        }

        return builder.name(stat.getName()).build();
    }

    public static ItemStack armor(Armor armor) {
        return SpigotIconBuilder.builder(getArmorMaterial(armor.getArmorType())).name(armor.getName()).description(MenuText.armorClass(armor), MenuText.armorType(armor), MenuText.magicBonus(armor), MenuText.hasSpeedPenalty(armor), MenuText.hasStealthPenalty(armor), MenuText.isUnarmored(armor), MenuText.isWorn(armor)).build();
    }

    public static ItemStack armorType(ArmorType armorType) {
        return SpigotIconBuilder.of(getArmorMaterial(armorType), armorType.getName());
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
        return SpigotIconBuilder.builder(Material.DIAMOND_SWORD).name(weapon.getName()).description(MenuText.isProficient(weapon.isProficient()), MenuText.attackStat(weapon.getAttackStat()), MenuText.magicBonus(weapon), MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)), MenuText.plusStat(weapon.isPlusStat()), MenuText.damageDice(weapon.getDamageDice()), MenuText.damageBonus(weapon.getDamageBonus(coreStats)), MenuText.damageType(weapon.getDamageType()), MenuText.critDamage(weapon.getCritDamageDice()), MenuText.critOn(weapon.getCritMin())).build();
    }

    public static ItemStack rangedWeapon(RangedWeapon weapon, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        return SpigotIconBuilder.builder(Material.BOW).name(weapon.getName()).description(MenuText.isProficient(weapon.isProficient()), MenuText.attackStat(weapon.getAttackStat()), MenuText.magicBonus(weapon), MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)), MenuText.damageDice(weapon.getDamageDice()), MenuText.damageBonus(weapon.getDamageBonus(coreStats)), MenuText.damageType(weapon.getDamageType()), MenuText.critDamage(weapon.getCritDamageDice()), MenuText.critOn(weapon.getCritMin())).build();
    }

    public static ItemStack spell(Spell spell) {
        return SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(spell.getName()).description(MenuText.spellLevel(spell.getLevel()),
        MenuText.spellType(spell.getSpellType()),
        MenuText.gainedFrom(spell.getGainedFrom()),
        MenuText.components(spell.getComponents()),
        MenuText.castTime(spell.getCastTime()),
        MenuText.duration(spell.getDuration()),
        MenuText.target(spell.getTargetArea()),
        MenuText.range(spell.getRange())).build();
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

        return SpigotIconBuilder.of(material, unarmoredBonus.getName());
    }
}
