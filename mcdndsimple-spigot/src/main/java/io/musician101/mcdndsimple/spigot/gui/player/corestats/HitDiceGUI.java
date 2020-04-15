package io.musician101.mcdndsimple.spigot.gui.player.corestats;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.common.reference.Messages;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class HitDiceGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;

    public HitDiceGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.HIT_DICE, 18);
        this.mcdndPlayer = mcdndPlayer;
        String[] hitDiceStrings = MenuText.hitDice(mcdndPlayer.getCharacterSheet().getCoreStatsTab().getHitDice());
        setHitDieEditSlot(hitDiceStrings, 0, 6);
        setHitDieEditSlot(hitDiceStrings, 1, 8);
        setHitDieEditSlot(hitDiceStrings, 2, 10);
        setHitDieEditSlot(hitDiceStrings, 3, 12);
        setHitDieRollSlot(9, 6);
        setHitDieRollSlot(10, 8);
        setHitDieRollSlot(11, 10);
        setHitDieRollSlot(12, 12);
        setButton(17, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }

    private void setHitDieEditSlot(String[] strings, int slot, int sides) {
        setButton(slot, SpigotIconBuilder.of(Material.NOTE_BLOCK, strings[slot]), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                mcdndPlayer.getCharacterSheet().getCoreStatsTab().getHitDice().updateHitDie(sides, i);
                new HitDiceGUI(mcdndPlayer, player);
            }
        }));
    }

    private void setHitDieRollSlot(int slot, int sides) {
        setButton(slot, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.rollHitDie(sides)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                player.closeInventory();
                Dice dice = new Dice(i, sides);
                int conMod = mcdndPlayer.getCharacterSheet().getCoreStatsTab().getCoreStats().getConstitution().getMod();
                player.sendMessage(Messages.rolledHitDice(mcdndPlayer.getBioAndInfo(), player.getName(), Dice.total(dice.roll(), conMod), dice));
            }
        }));
    }
}
