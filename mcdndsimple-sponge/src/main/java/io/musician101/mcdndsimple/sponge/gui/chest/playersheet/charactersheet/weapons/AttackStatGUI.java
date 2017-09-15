package io.musician101.mcdndsimple.sponge.gui.chest.playersheet.charactersheet.weapons;

import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.character.weapon.AbstractWeapon;
import io.musician101.mcdndsimple.common.character.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.config.AbstractConfig;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.chest.AbstractSpongeChestGUI;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;


public class AttackStatGUI<W extends AbstractWeapon> extends MCDNDSimpleChestGUI {

    private final W weapon;

    public AttackStatGUI(Player player, W weapon, AbstractSpongeChestGUI<AbstractConfig, SpongeMCDNDSimple> prevGUI) {
        super(player, MenuText.ATTACK_STAT, 9, prevGUI);
        this.weapon = weapon;
    }

    @Override
    protected void build() {
        WeaponAttackStat[] weaponAttackStats = WeaponAttackStat.values();
        for (int x = 0; x < weaponAttackStats.length; x++) {
            WeaponAttackStat weaponAttackStat = weaponAttackStats[x];
            set(x, addGlowIfConditionsMet(getItemType(weaponAttackStat), () -> weaponAttackStat == weapon.getAttackStat()));
        }

        setBackButton(8, ClickInventoryEvent.class, ItemTypes.BARRIER);
    }

    private ItemStack getItemType(WeaponAttackStat weaponAttackStat) {
        if (weaponAttackStat == WeaponAttackStat.CHA) {
            return createItem(ItemTypes.SKULL, Text.of(weaponAttackStat.getName()));
        }
        else if (weaponAttackStat == WeaponAttackStat.CON) {
            return createItem(ItemTypes.GOLDEN_APPLE, Text.of(weaponAttackStat.getName()));
        }
        else if (weaponAttackStat == WeaponAttackStat.DEX) {
            return createItem(ItemTypes.BOW, Text.of(weaponAttackStat.getName()));
        }
        else if (weaponAttackStat == WeaponAttackStat.FINESSE) {
            return createItem(ItemTypes.GOLDEN_APPLE, Text.of(weaponAttackStat.getName()));
        }
        else if (weaponAttackStat == WeaponAttackStat.INT) {
            return createItem(ItemTypes.WRITABLE_BOOK, Text.of(weaponAttackStat.getName()));
        }
        else if (weaponAttackStat == WeaponAttackStat.STR) {
            return createItem(ItemTypes.IRON_SWORD, Text.of(weaponAttackStat.getName()));
        }
        else if (weaponAttackStat == WeaponAttackStat.WIS) {
            return createItem(ItemTypes.ENCHANTED_BOOK, Text.of(weaponAttackStat.getName()));
        }

        return createItem(ItemTypes.BARRIER, Text.of("I'M AN ERROR! PLEASE REPORT ME!"));
    }
}
