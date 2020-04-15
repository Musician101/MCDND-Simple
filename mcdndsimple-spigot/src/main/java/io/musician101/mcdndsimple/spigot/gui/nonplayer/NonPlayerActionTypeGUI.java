package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActionType;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class NonPlayerActionTypeGUI extends SpigotMCDNDSimpleGUI {

    private final int index;
    @Nonnull
    private final NonPlayer nonPlayer;
    private int page = 1;

    public NonPlayerActionTypeGUI(@Nonnull NonPlayer nonPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.ACTION_TYPE, 54);
        this.nonPlayer = nonPlayer;
        this.index = index;
        updateSlots();
        setButton(49, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerActionGUI(nonPlayer, index, p)));
    }

    private void updateSlots() {
        NonPlayerAction nonPlayerAction = nonPlayer.getNonPlayerActions().getActions().get(index);
        IntStream.of(0, 45).forEach(x -> {
            try {
                NonPlayerActionType nonPlayerActionType = NonPlayerActionType.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.of(Material.ENCHANTED_BOOK, (nonPlayerActionType == nonPlayerAction.getActionType() ? ChatColor.GREEN : ChatColor.RED) + nonPlayerActionType.getName());
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> updateSlots()));
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

        int maxPage = Double.valueOf(Math.ceil(NonPlayerActionType.values().length / 45D)).intValue();
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
