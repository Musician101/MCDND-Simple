package io.musician101.mcdndsimple.sponge.gui.player;

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
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.List;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class WeightGUI extends SpongeMCDNDSimpleGUI {

    public WeightGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.WEIGHT, 9);
        CharacterSheet characterSheet = mcdndPlayer.getCharacterSheet();
        CoreStats<PlayerAbilityScore> coreStats = characterSheet.getCoreStatsTab().getCoreStats();
        InventoryTab inventoryTab = characterSheet.getInventoryTab();
        List<MCDNDItem> items = inventoryTab.getInventory();
        Wealth wealth = inventoryTab.getWealth();
        Weight weight = inventoryTab.getWeight();
        setButton(0, SpongeIconBuilder.of(ItemTypes.CHEST, Text.of(MenuText.inventoryWeight(items, weight))));
        setButton(1, SpongeIconBuilder.of(ItemTypes.EMERALD, Text.of(MenuText.coinWeight(wealth, weight))));
        setButton(2, SpongeIconBuilder.of(ItemTypes.STICK, Text.of(MenuText.otherWeight(weight))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                double d;
                try {
                    d = Double.parseDouble(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }
                weight.setOther(d);
                new WeightGUI(mcdndPlayer, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.STONE, Text.of(MenuText.totalWeight(items, wealth, weight))));
        setButton(4, SpongeIconBuilder.of(ItemTypes.CHEST, Text.of(MenuText.carryingMax(coreStats, weight))));
        setButton(5, SpongeIconBuilder.of(ItemTypes.PISTON, Text.of(MenuText.pushDragLift(coreStats, weight))));
        setButton(6, SpongeIconBuilder.of(ItemTypes.STONE, Text.of(MenuText.encumbered(coreStats, weight))));
        setButton(7, SpongeIconBuilder.of(ItemTypes.OBSIDIAN, Text.of(MenuText.heavilyEncumbered(coreStats, weight))));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InventoryTabGUI(mcdndPlayer, p)));
    }
}
