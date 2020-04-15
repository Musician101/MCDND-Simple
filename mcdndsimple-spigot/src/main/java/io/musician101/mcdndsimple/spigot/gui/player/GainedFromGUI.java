package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
import io.musician101.mcdndsimple.common.character.player.clazz.GainedFrom;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.clazz.ClassActionGUI;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class GainedFromGUI extends SpigotMCDNDSimpleGUI {

    private final int index;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public GainedFromGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.GAINED_FROM, 54);
        this.mcdndPlayer = mcdndPlayer;
        this.index = index;
        updateSlots();
        setButton(49, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> new ClassActionGUI(mcdndPlayer, index, p)));
    }

    private void updateSlots() {
        ClassAction classAction = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index);
        IntStream.of(0, 45).forEach(x -> {
            try {
                GainedFrom gainedFrom = GainedFrom.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.builder(Material.ENCHANTED_BOOK).name((gainedFrom == classAction.getGainedFrom() ? ChatColor.GREEN : ChatColor.RED) + gainedFrom.getName()).build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
                    classAction.setGainedFrom(gainedFrom);
                    updateSlots();
                }));
            }
            catch (ArrayIndexOutOfBoundsException e) {
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

        int maxPage = Double.valueOf(Math.ceil(UnarmoredBonus.values().length / 45D)).intValue();
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
