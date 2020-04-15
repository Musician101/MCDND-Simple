package io.musician101.mcdndsimple.spigot.util;

import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemRepresentation {

    private ItemRepresentation() {

    }

    public static ItemStack armor(Armor armor) {
        return SpigotIconBuilder.builder(getArmorMaterial(armor.getArmorType())).name(armor.getName()).description(MenuText.armorClass(armor), MenuText.armorType(armor), MenuText.magicBonus(armor), MenuText.hasSpeedPenalty(armor), MenuText.hasStealthPenalty(armor), MenuText.isUnarmored(armor), MenuText.isWorn(armor)).build();
    }

    public static ItemStack armorType(ArmorType armorType) {
        return SpigotIconBuilder.of(getArmorMaterial(armorType), armorType.getName());
    }

    private static Material getArmorMaterial(ArmorType armorType) {
        switch (armorType) {
            case LIGHT:
                return Material.LEATHER_CHESTPLATE;
            case MEDIUM:
                return Material.IRON_CHESTPLATE;
            case HEAVY:
                return Material.DIAMOND_CHESTPLATE;
            case SHIELD:
                return Material.SHIELD;
            default:
                return Material.GOLDEN_CHESTPLATE;
        }
    }

    public static ItemStack meleeWeapon(MeleeWeapon weapon, ClassLevels classLevels, CoreStats<PlayerAbilityScore> coreStats, Experience experience) {
        return SpigotIconBuilder.builder(Material.DIAMOND_SWORD).name(weapon.getName()).description(MenuText.isProficient(weapon), MenuText.attackStat(weapon.getAttackStat()), MenuText.magicBonus(weapon), MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)), MenuText.plusStat(weapon), MenuText.damageDice(weapon.getDamage()), MenuText.damageBonus(weapon, coreStats), MenuText.damageType(weapon.getDamageType()), MenuText.critDamage(weapon), MenuText.critOn(weapon)).build();
    }

    public static ItemStack rangedWeapon(RangedWeapon weapon, ClassLevels classLevels, CoreStats<PlayerAbilityScore> coreStats, Experience experience) {
        return SpigotIconBuilder.builder(Material.BOW).name(weapon.getName()).description(MenuText.isProficient(weapon), MenuText.attackStat(weapon.getAttackStat()), MenuText.magicBonus(weapon), MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)), MenuText.damageDice(weapon.getDamage()), MenuText.damageBonus(weapon, coreStats), MenuText.damageType(weapon.getDamageType()), MenuText.critDamage(weapon), MenuText.critOn(weapon)).build();
    }

    public static ItemStack spell(Spell spell) {
        return SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name(spell.getName()).description(MenuText.spellLevel(spell.getLevel()), MenuText.spellType(spell.getSpellType()), MenuText.gainedFrom(spell.getGainedFrom()), MenuText.hasComponents(spell.getComponents()), MenuText.castTime(spell.getCastTime()), MenuText.duration(spell), MenuText.target(spell.getTargetArea()), MenuText.range(spell)).build();
    }

    public static ItemStack unarmoredBonus(UnarmoredBonus unarmoredBonus) {
        Material material;
        switch (unarmoredBonus) {
            case BARBARIAN: {
                material = Material.STONE_SWORD;
                break;
            }
            case DRACONIC_RESILIENCE: {
                material = Material.DRAGON_BREATH;
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

    public static ItemStack weaponAttackStat(WeaponAttackStat stat) {
        SpigotIconBuilder builder;
        switch (stat) {
            case CHA:
                builder = SpigotIconBuilder.builder(Material.PLAYER_HEAD);
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
                builder = SpigotIconBuilder.builder(Material.WRITTEN_BOOK);
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
}
