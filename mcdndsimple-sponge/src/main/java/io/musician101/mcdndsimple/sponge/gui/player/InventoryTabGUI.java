package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class InventoryTabGUI extends SpongeMCDNDSimpleGUI {

    public InventoryTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.INVENTORY, 9);
        InventoryTab inventoryTab = mcdndPlayer.getCharacterSheet().getInventoryTab();
        setButton(0, SpongeIconBuilder.builder(ItemTypes.EMERALD).name(Text.of(MenuText.COIN_CARRIED)).description(stringArrayToTextList(MenuText.coinCarriedDescription(inventoryTab.getWealth()))).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new WealthGUI(mcdndPlayer, p)));
        setButton(1, SpongeIconBuilder.of(ItemTypes.STONE, Text.of(MenuText.WEIGHT)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new WeightGUI(mcdndPlayer, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.CHEST, Text.of(MenuText.INVENTORY)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InventoryGUI(mcdndPlayer, p)));
        setButton(3, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.INVENTORY_NOTES)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, MenuText.INVENTORY_NOTES, inventoryTab.getInventoryNotes()), (ply, pages) -> {
            inventoryTab.setInventoryNotes(textListToStringList(pages));
            new InventoryTabGUI(mcdndPlayer, ply);
        })));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
