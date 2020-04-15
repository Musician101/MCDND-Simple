package io.musician101.mcdndsimple.spigot.gui.player.corestats;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.Dice;
import io.musician101.mcdndsimple.common.character.HitPoints;
import io.musician101.mcdndsimple.common.character.player.BioAndInfo;
import io.musician101.mcdndsimple.common.character.player.DeathSavingThrows;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.CoreStatsTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class DeathSavingThrowsGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final DeathSavingThrows deathSavingThrows;

    public DeathSavingThrowsGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.DEATH_SAVING_THROWS, 9);
        CoreStatsTab coreStatsTab = mcdndPlayer.getCharacterSheet().getCoreStatsTab();
        this.deathSavingThrows = coreStatsTab.getDeathSavingThrows();
        BioAndInfo bioAndInfo = mcdndPlayer.getBioAndInfo();
        HitPoints hitPoints = coreStatsTab.getHitPoints();
        updateSuccessCount();
        updateFailCount();
        setButton(2, SpigotIconBuilder.of(Material.PLAYER_HEAD, MenuText.failCount(deathSavingThrows.getFailCount())), ImmutableMap.of(ClickType.LEFT, p -> {
            int result = Dice.total(new Dice(20).roll());
            int total = result + Dice.total(coreStatsTab.getBonuses().getSaves().roll());
            String newLine = "\n";
            StringBuilder sb = new StringBuilder(p.getName() + " (as " + bioAndInfo.getName() + ") has rolled a Death Saving Throw." + newLine + "The results are ");
            if (result == 20) {
                sb.append(ChatColor.GREEN);
            }
            else if (result == 1) {
                sb.append(ChatColor.RED);
            }

            sb.append(total).append(ChatColor.RESET).append(".").append(newLine);
            if (total > 10) {
                if (result == 1) {
                    deathSavingThrows.addFailCount();
                }

                deathSavingThrows.addFailCount();
                sb.append(bioAndInfo.getName()).append(" now has ").append(ChatColor.RED).append(deathSavingThrows.getFailCount()).append(ChatColor.RESET).append(" failed Death Saving Throws!");
            }
            else {
                deathSavingThrows.addSuccessCount();
                sb.append(bioAndInfo.getName()).append(" now has ").append(ChatColor.GREEN).append(deathSavingThrows.getSuccessCount()).append(ChatColor.RESET).append(" successful Death Saving Throws!");
                if (result == 20) {
                    sb.append(ChatColor.GOLD).append("They have also gained 1HP.");
                    hitPoints.setCurrent(1);
                }
            }

            Bukkit.broadcastMessage(sb.toString());
        }));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CoreStatsTabGUI(mcdndPlayer, p)));
    }

    private void updateFailCount() {
        setButton(1, SpigotIconBuilder.of(Material.PLAYER_HEAD, MenuText.failCount(deathSavingThrows.getFailCount())), ImmutableMap.of(ClickType.LEFT, p -> {
            deathSavingThrows.addFailCount();
            updateFailCount();
        }, ClickType.RIGHT, p -> {
            deathSavingThrows.removeFailCount();
            updateFailCount();
        }));
    }

    private void updateSuccessCount() {
        setButton(0, SpigotIconBuilder.builder(Material.PLAYER_HEAD).name(MenuText.successCount(deathSavingThrows.getSuccessCount())).build(), ImmutableMap.of(ClickType.LEFT, p -> {
            deathSavingThrows.addSuccessCount();
            updateSuccessCount();
        }, ClickType.RIGHT, p -> {
            deathSavingThrows.removeSuccessCount();
            updateSuccessCount();
        }));
    }
}
