package io.musician101.mcdndsimple.spigot.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class NonPlayerActionsListGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final NonPlayer nonPlayer;
    private int page = 1;

    public NonPlayerActionsListGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.ACTIONS, 54);
        this.nonPlayer = nonPlayer;
        updateSlots();
        setButton(48, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_NON_PLAYER_ACTION), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                NonPlayerAction classAction = new NonPlayerAction();
                classAction.setName(s);
                List<NonPlayerAction> nonPlayerActions = nonPlayer.getNonPlayerActions().getActions();
                nonPlayerActions.add(classAction);
                new NonPlayerActionGUI(nonPlayer, nonPlayerActions.size() - 1, player);
            }
        }));
        setButton(50, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerActionsGUI(nonPlayer, p)));
    }

    private void updateSlots() {
        List<NonPlayerAction> classActions = nonPlayer.getNonPlayerActions().getActions();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                NonPlayerAction classAction = classActions.get(index);
                ItemStack itemStack = SpigotIconBuilder.of(Material.NETHER_STAR, classAction.getName());
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> new NonPlayerActionGUI(nonPlayer, index, p), ClickType.RIGHT, p -> {
                    classActions.remove(index);
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

        int maxPage = Double.valueOf(Math.ceil(classActions.size() / 45D)).intValue();
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
