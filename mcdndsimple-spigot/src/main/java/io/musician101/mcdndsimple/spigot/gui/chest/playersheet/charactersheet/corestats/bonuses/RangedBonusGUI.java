package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats.bonuses;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.bonus.RangedBonus;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class RangedBonusGUI extends MCDNDSimpleChestGUI {

    private final RangedBonus rangedBonus;

    public RangedBonusGUI(Player player, RangedBonus rangedBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.RANGED_BONUSES, prevGUI);
        this.rangedBonus = rangedBonus;
    }

    @Override
    protected void build() {
        set(0, ClickType.LEFT, createItem(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            rangedBonus.setAttack(dice);
            open();
        }));

        set(1, ClickType.LEFT, setPotionEffect(createItem(Material.POTION, MenuText.DAMAGE_ROLLS), PotionType.STRENGTH), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            rangedBonus.setDamage(dice);
            open();
        }));

        setBackButton(8, ClickType.LEFT, Material.BARRIER);
    }
}
