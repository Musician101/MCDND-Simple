package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAbilityScore;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.CoreStatsGUI;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.function.Function;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class NonPlayerAbilityScoreGUI extends SpigotMCDNDSimpleGUI {

    public static final NonPlayerAbilityScoreBridger CHA = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getCharisma();
    public static final NonPlayerAbilityScoreBridger CON = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getConstitution();
    public static final NonPlayerAbilityScoreBridger DEX = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getDexterity();
    public static final NonPlayerAbilityScoreBridger INTEL = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getIntelligence();
    public static final NonPlayerAbilityScoreBridger STR = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getStrength();
    public static final NonPlayerAbilityScoreBridger WIS = nonPlayer -> nonPlayer.getNonPlayerSheet().getCoreStats().getWisdom();

    public NonPlayerAbilityScoreGUI(@Nonnull NonPlayer nonPlayer, @Nonnull NonPlayerAbilityScoreBridger bridger, @Nonnull Player player) {
        super(player, bridger.apply(nonPlayer).getName(), 9);
        NonPlayerAbilityScore abilityScore = bridger.apply(nonPlayer);
        setButton(0, SpigotIconBuilder.of(Material.PAPER, MenuText.score(abilityScore)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                abilityScore.setScore(i);
                new NonPlayerAbilityScoreGUI(nonPlayer, bridger, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.GUNPOWDER, MenuText.mod(abilityScore)));
        setButton(2, SpigotIconBuilder.of(Material.PAPER, MenuText.bonus(abilityScore)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                abilityScore.setBonus(i);
                new NonPlayerAbilityScoreGUI(nonPlayer, bridger, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.GLOWSTONE_DUST, MenuText.saveTotal(abilityScore)));
        setButton(4, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.ROLL_SAVE), ImmutableMap.of(ClickType.LEFT, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveTotal();
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(nonPlayer.getName() + " has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
            if (first == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (first == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(first + saveMod).append(ChatColor.RESET).append(" | ");
            if (second == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (second == 1) {
                sb.append(ChatColor.RED);
            }

            Bukkit.broadcastMessage(sb.append(second + saveMod).toString());
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsGUI<>(nonPlayer, CoreStatsGUI.NPC, p)));
    }

    private interface NonPlayerAbilityScoreBridger extends Function<NonPlayer, NonPlayerAbilityScore> {

    }
}
