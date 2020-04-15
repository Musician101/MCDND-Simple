package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.weapon.Weapon;
import io.musician101.mcdndsimple.common.character.player.weapon.WeaponAttackStat;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AttackStatGUI extends SpigotMCDNDSimpleGUI {

    private final int index;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public AttackStatGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.ATTACK_STAT, 54);
        this.mcdndPlayer = mcdndPlayer;
        this.index = index;
        updateSlots();
        setButton(49, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new MeleeWeaponGUI(mcdndPlayer, index, p)));
    }

    private void updateSlots() {
        Weapon weapon = mcdndPlayer.getCharacterSheet().getWeaponsTab().getMeleeWeapons().get(index);
        IntStream.of(0, 45).forEach(x -> {
            try {
                WeaponAttackStat attackStat = WeaponAttackStat.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.builder(ItemRepresentation.weaponAttackStat(attackStat)).name((attackStat == weapon.getAttackStat() ? ChatColor.GREEN : ChatColor.RED) + attackStat.getName()).build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
                    weapon.setAttackStat(attackStat);
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException e) {
                removeButton(x);
            }
        });

        if (page == 1) {
            removeButton(45);
        }
        else {
            setButton(45, SpigotIconBuilder.of(Material.ARROW, MenuText.PREVIOUS_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(WeaponAttackStat.values().length / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpigotIconBuilder.of(Material.ARROW, MenuText.NEXT_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
