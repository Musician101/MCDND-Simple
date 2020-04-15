package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotBookGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ItemGUI extends SpigotMCDNDSimpleGUI {

    private final int index;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;

    public ItemGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory().get(index).getName(), 9);
        this.mcdndPlayer = mcdndPlayer;
        MCDNDItem item = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory().get(index);
        this.index = index;
        setButton(0, SpigotIconBuilder.of(Material.PAPER, MenuText.RENAME), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                item.setName(s);
                new ItemGUI(mcdndPlayer, index, player);
            }
        }));
        updateIsCarried();
        setButton(2, SpigotIconBuilder.of(Material.CHEST, MenuText.quantity(item)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                item.setQuantity(i);
                new ItemGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(3, SpigotIconBuilder.of(Material.OBSIDIAN, MenuText.weight(item)), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

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

                item.setWeight(d);
                new ItemGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(4, SpigotIconBuilder.of(Material.BOOK, MenuText.DESCRIPTION), ImmutableMap.of(ClickType.LEFT, p -> new SpigotBookGUI<>(SpigotMCDNDSimple.instance(), p, createBook(p, item.getName(), item.getDescription()), (ply, pages) -> {
            item.setDescription(pages);
            new ItemGUI(mcdndPlayer, index, ply);
        })));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new InventoryGUI(mcdndPlayer, p)));
    }

    private void updateIsCarried() {
        MCDNDItem item = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory().get(index);
        setButton(1, SpigotIconBuilder.of(Material.TORCH, (item.isCarried() ? ChatColor.GREEN : ChatColor.RED) + MenuText.carried(item)), ImmutableMap.of(ClickType.LEFT, p -> {
            item.setIsCarried(!item.isCarried());
            updateIsCarried();
        }));
    }
}
