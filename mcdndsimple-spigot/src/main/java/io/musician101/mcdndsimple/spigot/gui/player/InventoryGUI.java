package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class InventoryGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public InventoryGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.INVENTORY, 54);
        this.mcdndPlayer = mcdndPlayer;
        updateSlots();
        setButton(48, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_ARMOR), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                MCDNDItem item = new MCDNDItem();
                item.setName(s);
                List<MCDNDItem> items = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory();
                items.add(item);
                new ItemGUI(mcdndPlayer, items.size() - 1, player);
            }
        }));
        setButton(50, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new InventoryTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<MCDNDItem> items = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                MCDNDItem item = items.get(index);
                ItemStack itemStack = SpigotIconBuilder.builder(Material.CHEST).name(item.getName()).description(Stream.concat(Stream.of(ChatColor.GREEN + "LEFT-CLICK" + ChatColor.RESET + " to edit.", ChatColor.RED + "RIGHT-CLICK" + ChatColor.RESET + " to delete."), Arrays.stream(MenuText.itemDesc(item))).collect(Collectors.toList())).build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> new ItemGUI(mcdndPlayer, index, p), ClickType.RIGHT, p -> {
                    items.remove(index);
                    updateSlots();
                }));
            }
            catch (IndexOutOfBoundsException e) {
                removeButton(x);
            }
        });

        if (page == 1) {
            removeButton(45);
        }
        else {
            setButton(45, SpigotIconBuilder.of(Material.ARROW, MenuText.PREVIOUS_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(items.size() / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpigotIconBuilder.of(Material.ARROW, MenuText.NEXT_PAGE), ImmutableMap.of(ClickType.LEFT, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
