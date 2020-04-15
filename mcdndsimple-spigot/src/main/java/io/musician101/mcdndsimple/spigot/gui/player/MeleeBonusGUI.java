package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.bonus.MeleeBonus;
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

public class MeleeBonusGUI extends SpigotMCDNDSimpleGUI {

    public MeleeBonusGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.MELEE_BONUSES, 9);
        MeleeBonus meleeBonus = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getBonuses().getMelee();
        setButton(0, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.ATTACK_ROLLS), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                meleeBonus.setAttack(dice);
                new MeleeBonusGUI(mcdndPlayer, player);
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

                meleeBonus.setDamage(dice);
                new MeleeBonusGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new BonusesGUI(mcdndPlayer, p)));
    }
}
