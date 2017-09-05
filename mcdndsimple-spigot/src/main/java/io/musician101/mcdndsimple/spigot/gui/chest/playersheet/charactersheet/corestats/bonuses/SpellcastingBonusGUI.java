package io.musician101.mcdndsimple.spigot.gui.chest.playersheet.charactersheet.corestats.bonuses;

import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.Reference.MenuText;
import io.musician101.mcdndsimple.common.Reference.Messages;
import io.musician101.mcdndsimple.common.character.bonus.SpellcastingBonus;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.anvil.StringInputAnvilGUI;
import io.musician101.mcdndsimple.spigot.gui.chest.MCDNDSimpleChestGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.chest.AbstractSpigotChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionType;

public class SpellcastingBonusGUI extends MCDNDSimpleChestGUI {

    private final SpellcastingBonus spellcastingBonus;

    public SpellcastingBonusGUI(Player player, SpellcastingBonus rangedBonus, AbstractSpigotChestGUI<SpigotMCDNDSimple> prevGUI) {
        super(player, 9, MenuText.RANGED_BONUSES, prevGUI);
        this.spellcastingBonus = rangedBonus;
    }

    @Override
    protected void build() {
        set(0, createItem(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellcastingBonus.setAttack(dice);
            player.closeInventory();
            open();
        }));

        set(1, setPotionEffect(createItem(Material.POTION, MenuText.DAMAGE_ROLLS), PotionType.STRENGTH), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellcastingBonus.setDamage(dice);
            player.closeInventory();
            open();
        }));

        set(2, setPotionEffect(createItem(Material.POTION, MenuText.SAVE_DC_ROLLS), PotionType.LUCK), player -> new StringInputAnvilGUI(player, (p, s) -> {
            Dice dice = Dice.parse(s);
            if (dice == null) {
                player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                return;
            }

            spellcastingBonus.setSaveDC(dice);
            player.closeInventory();
            open();
        }));

        setBackButton(9, Material.BARRIER);
    }
}
