package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.tab.InventoryTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class InventoryTabGUI extends SpigotMCDNDSimpleGUI {

    public InventoryTabGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.INVENTORY, 9);
        InventoryTab inventoryTab = mcdndPlayer.getCharacterSheet().getInventoryTab();
        setButton(0, SpigotIconBuilder.builder(Material.EMERALD).name(MenuText.COIN_CARRIED).description(MenuText.coinCarriedDescription(inventoryTab.getWealth())).build(), ImmutableMap.of(ClickType.LEFT, p -> new WealthGUI(mcdndPlayer, p)));
        setButton(1, SpigotIconBuilder.of(Material.STONE, MenuText.WEIGHT), ImmutableMap.of(ClickType.LEFT, p -> new WeightGUI(mcdndPlayer, p)));
        setButton(2, SpigotIconBuilder.of(Material.CHEST, MenuText.INVENTORY), ImmutableMap.of(ClickType.LEFT, p -> new InventoryGUI(mcdndPlayer, p)));
        setButton(3, SpigotIconBuilder.of(Material.BOOK, MenuText.INVENTORY_NOTES), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, MenuText.INVENTORY_NOTES, inventoryTab.getInventoryNotes()), (ply, pages) -> {
            inventoryTab.setInventoryNotes(pages);
            new InventoryTabGUI(mcdndPlayer, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new CharacterSheetGUI(mcdndPlayer, p)));
    }
}
