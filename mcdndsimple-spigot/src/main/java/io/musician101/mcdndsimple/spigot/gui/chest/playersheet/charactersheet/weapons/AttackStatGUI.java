package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.weapon.AbstractWeapon;
import io.musician101.mcdndsimple.common.character.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AttackStatGUI<W extends AbstractWeapon> extends MCDNDSimpleChestGUI {

    private final W weapon;

    public AttackStatGUI(Player player, W weapon, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.ATTACK_STAT, prevGUI);
        this.weapon = weapon;
    }

    @Override
    protected void build() {
        WeaponAttackStat[] weaponAttackStats = WeaponAttackStat.values();
        for (int x = 0; x < weaponAttackStats.length; x++) {
            WeaponAttackStat weaponAttackStat = weaponAttackStats[x];
            set(x, ClickType.LEFT, addGlowIfConditionsMet(getMaterial(weaponAttackStat), () -> weaponAttackStat == weapon.getAttackStat()), player -> weapon.setAttackStat(weaponAttackStat));
        }

        setBackButton(8, ClickType.LEFT, Material.BARRIER);
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
}
