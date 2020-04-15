package io.musician101.mcdndsimple.spigot.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.UnarmoredBonus;
import io.musician101.mcdndsimple.common.character.player.tab.ArmorTab;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.gui.player.armor.ArmorTabGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class UnarmoredBonusGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final ArmorTab armorTab;
    private int page = 1;

    public UnarmoredBonusGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.UNARMORED_BONUS, 54);
        this.armorTab = mcdndPlayer.getCharacterSheet().getArmorTab();
        updateSlots();
        setButton(49, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> new ArmorTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                UnarmoredBonus unarmoredBonus = UnarmoredBonus.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.builder(ItemRepresentation.unarmoredBonus(unarmoredBonus)).name((unarmoredBonus == armorTab.getUnarmoredBonus() ? ChatColor.GREEN : ChatColor.RED) + unarmoredBonus.getName()).build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
                    armorTab.setUnarmoredBonus(unarmoredBonus);
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
