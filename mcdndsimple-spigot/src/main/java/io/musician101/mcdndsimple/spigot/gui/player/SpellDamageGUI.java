package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.spell.SpellDamage;
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

public class SpellDamageGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final SpellDamage spellDamage;

    public SpellDamageGUI(@Nonnull MCDNDPlayer mcdndPlayer, int level, int index, @Nonnull Player player) {
        super(player, MenuText.DAMAGE, 9);
        this.spellDamage = mcdndPlayer.getCharacterSheet().getSpellbookTab().getSpells().stream().filter(spell -> spell.getLevel() == level).collect(Collectors.toList()).get(index).getSpellDamage();
        updateCanCrit();
        setButton(1, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.DAMAGE_DICE), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Dice dice = Dice.parse(s);
                if (dice == null) {
                    p.sendMessage(ChatColor.RED + Messages.malformedDiceInput(s));
                    return;
                }

                spellDamage.setDamage(dice);
                new SpellDamageGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(2, SpigotIconBuilder.of(Material.STICK, MenuText.otherBonus(spellDamage)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }

                spellDamage.setBonus(i);
                new SpellDamageGUI(mcdndPlayer, level, index, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.ENCHANTED_BOOK, MenuText.damageType(spellDamage.getDamageType())), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                spellDamage.setDamageType(s);
                new SpellDamageGUI(mcdndPlayer, level, index, player);
            }
        }));
    }

    private void updateCanCrit() {
        setButton(0, SpigotIconBuilder.builder(Material.DIAMOND_SWORD).name(MenuText.CAN_CRIT).addGlow(spellDamage.canCrit()).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            spellDamage.setCanCrit(!spellDamage.canCrit());
            updateCanCrit();
        }));
    }
}
