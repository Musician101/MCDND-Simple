package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.AbilityScore;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.weapon.MeleeWeapon;
import io.musician101.mcdndsimple.common.character.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AttackStatGUI extends MCDNDSimpleChestGUI {

    private final CoreStats coreStats;
    private final MeleeWeapon weapon;

    public AttackStatGUI(Player player, MeleeWeapon weapon, CoreStats coreStats, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.ATTACK_STAT, prevGUI);
        this.weapon = weapon;
        this.coreStats = coreStats;
    }

    @Override
    protected void build() {
        WeaponAttackStat[] weaponAttackStats = WeaponAttackStat.values();
        for (int x = 0; x < weaponAttackStats.length; x++) {
            WeaponAttackStat weaponAttackStat = weaponAttackStats[x];
            set(x, addGlowIfConditionsMet(getMaterial(weaponAttackStat), () -> weaponAttackStat == weapon.getAttackStat()));
        }

        setBackButton(8, Material.BARRIER);
    }

    private ItemStack getMaterial(WeaponAttackStat weaponAttackStat) {
        if (weaponAttackStat == WeaponAttackStat.CHA) {
            return createItem(Material.SKULL_ITEM, weaponAttackStat.getName());
        }
        else if (weaponAttackStat == WeaponAttackStat.CON) {
            return createItem(Material.GOLDEN_APPLE, weaponAttackStat.getName());
        }
        else if (weaponAttackStat == WeaponAttackStat.DEX) {
            return createItem(Material.BOW, weaponAttackStat.getName());
        }
        else if (weaponAttackStat == WeaponAttackStat.FINESSE) {
            return createItem(Material.GOLDEN_APPLE, weaponAttackStat.getName());
        }
        else if (weaponAttackStat == WeaponAttackStat.INT) {
            return createItem(Material.BOOK_AND_QUILL, weaponAttackStat.getName());
        }
        else if (weaponAttackStat == WeaponAttackStat.STR) {
            return createItem(Material.IRON_SWORD, weaponAttackStat.getName());
        }
        else if (weaponAttackStat == WeaponAttackStat.WIS) {
            return createItem(Material.ENCHANTED_BOOK, weaponAttackStat.getName());
        }

        return createItem(Material.BARRIER, "I'M AN ERROR! PLEASE REPORT ME!");
    }

    /**
     * @deprecated Add this method to MusicianLibrary
     * Need to look into this so I don't have to pass a value into the method
     * Try supplier
     */
    @Deprecated
    private ItemStack addGlowIfSelected(ItemStack itemStack, Supplier<Boolean> predicate) {
        if (predicate.get()) {
            return addGlow(itemStack);
        }

        return itemStack;
    }

    private String getAbilityScoreShortName(AbilityScore abilityScore) {
        return abilityScore.getShortName().toUpperCase();
    }

    private Consumer<Player> setAttackStat(WeaponAttackStat attackStat) {
        return player -> {
            weapon.setAttackStat(attackStat);
            if (prevGUI == null) {
                player.closeInventory();
            }
            else {
                prevGUI.open();
            }
        };
    }
}
