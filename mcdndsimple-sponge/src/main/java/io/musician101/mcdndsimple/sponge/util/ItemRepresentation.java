package io.musician101.mcdndsimple.sponge.util;

import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

import static org.spongepowered.api.text.Text.of;

public class ItemRepresentation {

    private ItemRepresentation() {

    }

    public static ItemStack armor(Armor armor) {
        return SpongeIconBuilder.builder(getArmorMaterial(armor.getArmorType())).name(of(armor.getName())).description(of(MenuText.armorClass(armor)), of(MenuText.armorType(armor)), of(MenuText.magicBonus(armor)), of(MenuText.hasSpeedPenalty(armor)), of(MenuText.hasStealthPenalty(armor)), of(MenuText.isUnarmored(armor)), of(MenuText.isWorn(armor))).build();
    }

    public static ItemStack armorType(ArmorType armorType) {
        return SpongeIconBuilder.of(getArmorMaterial(armorType), of(armorType.getName()));
    }

    private static ItemType getArmorMaterial(ArmorType armorType) {
        ItemType itemType = ItemTypes.GOLDEN_CHESTPLATE;
        switch (armorType) {
            case LIGHT:
                itemType = ItemTypes.LEATHER_CHESTPLATE;
                break;
            case MEDIUM:
                itemType = ItemTypes.IRON_CHESTPLATE;
                break;
            case HEAVY:
                itemType = ItemTypes.DIAMOND_CHESTPLATE;
                break;
        }

        return itemType;
    }

    public static ItemStack meleeWeapon(MeleeWeapon weapon, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        return SpongeIconBuilder.builder(ItemTypes.DIAMOND_SWORD).name(of(weapon.getName())).description(of(MenuText.isProficient(weapon)), of(MenuText.attackStat(weapon.getAttackStat())), of(MenuText.magicBonus(weapon)), of(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience))), of(MenuText.plusStat(weapon)), of(MenuText.damageDice(weapon.getDamage())), of(MenuText.damageBonus(weapon, coreStats)), of(MenuText.damageType(weapon.getDamageType())), of(MenuText.critDamage(weapon)), of(MenuText.critOn(weapon))).build();
    }

    public static ItemStack rangedWeapon(RangedWeapon weapon, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        return SpongeIconBuilder.builder(ItemTypes.BOW).name(of(weapon.getName())).description(of(MenuText.isProficient(weapon)), of(MenuText.attackStat(weapon.getAttackStat())), of(MenuText.magicBonus(weapon)), of(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience))), of(MenuText.damageDice(weapon.getDamage())), of(MenuText.damageBonus(weapon, coreStats)), of(MenuText.damageType(weapon.getDamageType())), of(MenuText.critDamage(weapon), MenuText.critOn(weapon))).build();
    }

    public static ItemStack spell(Spell spell) {
        return SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK).name(of(spell.getName())).description(of(MenuText.spellLevel(spell.getLevel())), of(MenuText.spellType(spell.getSpellType())), of(MenuText.gainedFrom(spell.getGainedFrom())), of(MenuText.hasComponents(spell.getComponents())), of(MenuText.castTime(spell.getCastTime())), of(MenuText.duration(spell)), of(MenuText.target(spell.getTargetArea())), of(MenuText.range(spell))).build();
    }

    public static ItemStack unarmoredBonus(UnarmoredBonus unarmoredBonus) {
        ItemType itemType;
        switch (unarmoredBonus) {
            case BARBARIAN: {
                itemType = ItemTypes.STONE_SWORD;
                break;
            }
            case DRACONIC_RESILIENCE: {
                itemType = ItemTypes.DRAGON_BREATH;
                break;
            }
            case MONK: {
                itemType = ItemTypes.STICK;
                break;
            }
            default: {
                itemType = ItemTypes.CHAINMAIL_CHESTPLATE;
            }
        }

        return SpongeIconBuilder.of(itemType, of(unarmoredBonus.getName()));
    }

    public static ItemStack weaponAttackStat(WeaponAttackStat stat) {
        SpongeIconBuilder builder;
        switch (stat) {
            case CHA:
                builder = SpongeIconBuilder.builder(ItemTypes.SKULL).durability(3);
                break;
            case CON:
                builder = SpongeIconBuilder.builder(ItemTypes.GOLDEN_APPLE);
                break;
            case DEX:
                builder = SpongeIconBuilder.builder(ItemTypes.BOW);
                break;
            case FINESSE:
                builder = SpongeIconBuilder.builder(ItemTypes.DIAMOND_SWORD);
                break;
            case INT:
                builder = SpongeIconBuilder.builder(ItemTypes.WRITABLE_BOOK);
                break;
            case STR:
                builder = SpongeIconBuilder.builder(ItemTypes.IRON_SWORD);
                break;
            case WIS:
                builder = SpongeIconBuilder.builder(ItemTypes.ENCHANTED_BOOK);
                break;
            default:
                return SpongeIconBuilder.builder(ItemTypes.BARRIER).name(of("I'M AN ERROR! PLEASE REPORT ME!")).build();
        }

        return builder.name(of(stat.getName())).build();
    }
}
