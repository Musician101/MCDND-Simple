package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.bonus.RangedBonus;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.corestats.BonusesGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class RangedBonusGUI extends SpigotMCDNDSimpleGUI {

    public RangedBonusGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.RANGED_BONUSES, 9);
        RangedBonus rangedBonus = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getBonuses().getRanged();
        setButton(0, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                rangedBonus.setAttack(dice);
                new RangedBonusGUI(mcdndPlayer, player);
            }
        }));
        setButton(1, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.STRENGTH).name(MenuText.DAMAGE_ROLLS).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                rangedBonus.setDamage(dice);
                new RangedBonusGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new BonusesGUI(mcdndPlayer, p)));
    }
}
