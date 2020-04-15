package io.musician101.mcdndsimple.spigot.gui.player.corestats;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.bonus.Bonuses;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.MeleeBonusGUI;
import io.musician101.mcdndsimple.spigot.gui.player.RangedBonusGUI;
import io.musician101.mcdndsimple.spigot.gui.player.SpellcastingBonusGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.potion.PotionType;

public class BonusesGUI extends SpigotMCDNDSimpleGUI {

    public BonusesGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.BONUSES_PENALTIES, 9);
        Bonuses bonuses = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getBonuses();
        setButton(0, SpigotIconBuilder.of(Material.DIAMOND_SWORD, MenuText.MELEE_BONUSES), ImmutableMap.of(ClickType.LEFT, p -> new MeleeBonusGUI(mcdndPlayer, p)));
        setButton(1, SpigotIconBuilder.of(Material.BOW, MenuText.RANGED_BONUSES), ImmutableMap.of(ClickType.LEFT, p -> new RangedBonusGUI(mcdndPlayer, p)));
        setButton(2, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.SPELLCASTING_BONUSES), ImmutableMap.of(ClickType.LEFT, p -> new SpellcastingBonusGUI(mcdndPlayer, p)));
        setButton(3, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.STRENGTH).name(MenuText.SAVING_THROW_BONUSES).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                bonuses.setSaves(dice);
                new BonusesGUI(mcdndPlayer, player);
            }
        }));
        setButton(4, SpigotIconBuilder.builder(Material.POTION).potionEffect(PotionType.LUCK).name(MenuText.ABILITY_SKILL_CHECK_ROLLS).build(), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                bonuses.setAbilitiesAndSkills(dice);
                new BonusesGUI(mcdndPlayer, player);
            }
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }
}
