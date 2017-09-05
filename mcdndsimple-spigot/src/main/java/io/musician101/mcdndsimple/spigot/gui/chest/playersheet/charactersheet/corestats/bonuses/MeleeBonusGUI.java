package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats.bonuses;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.bonus.MeleeBonus;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionType;

public class MeleeBonusGUI extends MCDNDSimpleChestGUI {

    private final MeleeBonus meleeBonus;

    public MeleeBonusGUI(Player player, MeleeBonus meleeBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.MELEE_BONUSES, prevGUI);
        this.meleeBonus = meleeBonus;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            meleeBonus.setAttack(dice);
            delayedOpen();
        }));

        set(1, setPotionEffect(createItem(Material.POTION, MenuText.DAMAGE_ROLLS), PotionType.STRENGTH), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            meleeBonus.setDamage(dice);
            delayedOpen();
        }));

        setBackButton(9, Material.BARRIER);
    }
}
