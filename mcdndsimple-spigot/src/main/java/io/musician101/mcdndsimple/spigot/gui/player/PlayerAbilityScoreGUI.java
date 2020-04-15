package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.Experience;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassLevels;
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

public class PlayerAbilityScoreGUI extends SpigotMCDNDSimpleGUI {

    public static final PlayerAbilityScoreBridger CHA = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getCharisma();
    public static final PlayerAbilityScoreBridger CON = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getConstitution();
    public static final PlayerAbilityScoreBridger DEX = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getDexterity();
    public static final PlayerAbilityScoreBridger INTEL = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getIntelligence();
    public static final PlayerAbilityScoreBridger STR = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getStrength();
    public static final PlayerAbilityScoreBridger WIS = playerSheet -> playerSheet.getCharacterSheet().getCoreStatsTab().getCoreStats().getWisdom();
    @Nonnull
    private final PlayerAbilityScore abilityScore;

    public PlayerAbilityScoreGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull PlayerAbilityScoreBridger bridger, @Nonnull Player player) {
        super(player, bridger.apply(mcdndPlayer).getName(), 9);
        this.abilityScore = bridger.apply(mcdndPlayer);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        ClassLevels classLevels = characterSheet.getClassTab().getClassLevels();
        Experience experience = characterSheet.getCoreStatsTab().getExperience();
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
                new PlayerAbilityScoreGUI(mcdndPlayer, bridger, player);
            }
        }));
        setButton(1, SpigotIconBuilder.of(Material.GUNPOWDER, MenuText.mod(abilityScore)));
        updateIsProficient();
        setButton(3, SpigotIconBuilder.of(Material.GLOWSTONE_DUST, MenuText.saveMod(abilityScore, classLevels, experience)));
        setButton(4, SpigotIconBuilder.of(Material.REDSTONE_LAMP, MenuText.ROLL_SAVE), ImmutableMap.of(ClickType.LEFT, p -> {
            p.closeInventory();
            Dice dice = new Dice(20);
            int saveMod = abilityScore.getSaveMod(classLevels, experience);
            int first = Dice.total(dice.roll());
            int second = Dice.total(dice.roll());
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + mcdndPlayer.getBioAndInfo().getName() + ") has rolled a " + abilityScore.getName() + " saving throw.\nThe results are: ");
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

            sb.append(second + saveMod);
            Bukkit.broadcastMessage(sb.toString());
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsGUI<>(mcdndPlayer, CoreStatsGUI.PLAYER, p)));
    }

    private void updateIsProficient() {
        setButton(2, SpigotIconBuilder.of(Material.PAPER, MenuText.proficient(abilityScore)), ImmutableMap.of(ClickType.LEFT, p -> {
            abilityScore.setIsProficient(!abilityScore.isProficient());
            updateIsProficient();
        }));
    }

    private interface PlayerAbilityScoreBridger extends Function<MCDNDPlayer, PlayerAbilityScore> {

    }
}
