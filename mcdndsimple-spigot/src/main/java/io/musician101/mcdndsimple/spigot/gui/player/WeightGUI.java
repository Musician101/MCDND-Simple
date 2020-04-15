package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.CoreStats;
import io.musician101.mcdndsimple.common.character.player.CharacterSheet;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.PlayerAbilityScore;
import io.musician101.mcdndsimple.common.character.player.Weight;
import io.musician101.mcdndsimple.common.character.player.equipment.currency.Wealth;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class WeightGUI extends SpigotMCDNDSimpleGUI {

    public WeightGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.WEIGHT, 9);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        CoreStats<PlayerAbilityScore> coreStats = characterSheet.getCoreStatsTab().getCoreStats();
        InventoryTab inventoryTab = characterSheet.getInventoryTab();
        List<MCDNDItem> items = inventoryTab.getInventory();
        Wealth wealth = inventoryTab.getWealth();
        Weight weight = inventoryTab.getWeight();
        setButton(0, SpigotIconBuilder.of(Material.CHEST, MenuText.inventoryWeight(items, weight)));
        setButton(1, SpigotIconBuilder.of(Material.EMERALD, MenuText.coinWeight(wealth, weight)));
        setButton(2, SpigotIconBuilder.of(Material.STICK, MenuText.otherWeight(weight)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                double d;
                try {
                    d = Double.parseDouble(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "That is not a number.");
                    return;
                }
                weight.setOther(d);
                new WeightGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.STONE, MenuText.totalWeight(items, wealth, weight)));
        setButton(4, SpigotIconBuilder.of(Material.CHEST, MenuText.carryingMax(coreStats, weight)));
        setButton(5, SpigotIconBuilder.of(Material.PISTON, MenuText.pushDragLift(coreStats, weight)));
        setButton(6, SpigotIconBuilder.of(Material.STONE, MenuText.encumbered(coreStats, weight)));
        setButton(7, SpigotIconBuilder.of(Material.OBSIDIAN, MenuText.heavilyEncumbered(coreStats, weight)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new InventoryTabGUI(mcdndPlayer, p)));
    }
}
