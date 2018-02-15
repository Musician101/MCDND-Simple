package io.musician101.mcdndsimple.sponge.util;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.character.player.spell.Spell;
import io.musician101.mcdndsimple.common.character.player.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.player.weapon.RangedWeapon;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

public class ItemRepresentation {

    private ItemRepresentation() {

    }

    public static ItemStack armor(Armor armor) {
        List<String> lore = new ArrayList<>();
        lore.add(MenuText.baseAC(armor));
        lore.add(MenuText.armorType(armor));
        lore.add(MenuText.magicBonus(armor.getMagicBonus()));
        lore.add(MenuText.hasSpeedPenalty(armor));
        lore.add(MenuText.hasStealthPenalty(armor));
        lore.add(MenuText.isUnarmored(armor));
        lore.add(MenuText.isWorn(armor));
        return ItemStack.builder().itemType(getArmorMaterial(armor.getArmorType())).add(Keys.DISPLAY_NAME, Text.of(armor.getName())).add(Keys.ITEM_LORE, lore.stream().map(Text::of).collect(Collectors.toList())).build();
    }

    public static ItemStack armorType(ArmorType armorType) {
        return ItemStack.builder().itemType(getArmorMaterial(armorType)).add(Keys.DISPLAY_NAME, Text.of(armorType.getName())).build();
    }

    private static ItemType getArmorMaterial(ArmorType armorType) {
        ItemType material = ItemTypes.GOLDEN_CHESTPLATE;
        if (armorType == ArmorType.LIGHT) {
            material = ItemTypes.LEATHER_CHESTPLATE;
        }
        else if (armorType == ArmorType.MEDIUM) {
            material = ItemTypes.IRON_CHESTPLATE;
        }
        else if (armorType == ArmorType.HEAVY) {
            material = ItemTypes.DIAMOND_CHESTPLATE;
        }

        return material;
    }

    public static ItemStack meleeWeapon(MeleeWeapon weapon, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        List<String> lore = new ArrayList<>();
        lore.add(MenuText.isProficient(weapon.isProficient()));
        lore.add(MenuText.attackStat(weapon.getAttackStat()));
        lore.add(MenuText.magicBonus(weapon.getMagicBonus()));
        lore.add(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)));
        lore.add(MenuText.damageDice(weapon.getDamage()));
        lore.add(MenuText.damageBonus(weapon.getDamageBonus(coreStats)));
        lore.add(MenuText.damageType(weapon.getDamageType()));
        lore.add(MenuText.critDamage(weapon.getCritDamage()));
        lore.add(MenuText.critOn(weapon.getCritMin()));
        return ItemStack.builder().itemType(ItemTypes.DIAMOND_SWORD).add(Keys.DISPLAY_NAME, Text.of(weapon.getName())).add(Keys.ITEM_LORE, lore.stream().map(Text::of).collect(Collectors.toList())).build();
    }

    public static ItemStack rangedWeapon(RangedWeapon weapon, ClassLevels classLevels, CoreStats coreStats, Experience experience) {
        List<String> lore = new ArrayList<>();
        lore.add(MenuText.isProficient(weapon.isProficient()));
        lore.add(MenuText.attackStat(weapon.getAttackStat()));
        lore.add(MenuText.magicBonus(weapon.getMagicBonus()));
        lore.add(MenuText.toHit(weapon.getToHit(classLevels, coreStats, experience)));
        lore.add(MenuText.damageDice(weapon.getDamage()));
        lore.add(MenuText.damageBonus(weapon.getDamageBonus(coreStats)));
        lore.add(MenuText.damageType(weapon.getDamageType()));
        lore.add(MenuText.critDamage(weapon.getCritDamage()));
        lore.add(MenuText.critOn(weapon.getCritMin()));
        lore.add(MenuText.ammo(weapon.getAmmo()));
        return ItemStack.builder().itemType(ItemTypes.BOW).add(Keys.DISPLAY_NAME, Text.of(weapon.getName())).add(Keys.ITEM_LORE, lore.stream().map(Text::of).collect(Collectors.toList())).build();
    }

    public static ItemStack spell(Spell spell) {
        List<String> lore = new ArrayList<>();
        lore.add(MenuText.spellLevel(spell.getLevel()));
        lore.add(MenuText.spellType(spell.getSpellType()));
        lore.add(MenuText.gainedFrom(spell.getGainedFrom()));
        lore.add(MenuText.components(spell.getComponents()));
        lore.add(MenuText.castTime(spell.getCastTime()));
        lore.add(MenuText.duration(spell.getDuration()));
        lore.add(MenuText.target(spell.getTargetArea()));
        lore.add(MenuText.range(spell.getRange()));
        return ItemStack.builder().itemType(ItemTypes.ENCHANTED_BOOK).add(Keys.ITEM_LORE, lore.stream().map(Text::of).collect(Collectors.toList())).build();
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

        return ItemStack.builder().itemType(itemType).add(Keys.DISPLAY_NAME, Text.of(unarmoredBonus.getName())).build();
    }
}
