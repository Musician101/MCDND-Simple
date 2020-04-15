package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellHealing;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class HealingGUI extends SpigotMCDNDSimpleGUI {

    public HealingGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.HEALING, 9);
        SpellHealing spellHealing = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellHealing();
        setButton(0, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.healAmount(spellHealing.getHealAmount())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    player.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                spellHealing.setHealAmount(dice);
                new HealingGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.REDSTONE, MenuText.STAT_BONUS), ImmutableMap.of(ClickType.LEFT, p -> new StatBonusGUI(mcdndPlayer, level, index, StatBonusGUI.SPELL_HEALING, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new SpellGUI(mcdndPlayer, level, index, p)));
    }
}
