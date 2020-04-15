package io.musician101.mcdndsimple.spigot.gui.player.armor;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ArmorTypeGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final Armor armor;
    private int page = 1;

    public ArmorTypeGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.ARMOR_TYPE, 54);
        this.armor = mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList().get(index);
        updateSlots();
        setButton(49, SpigotIconBuilder.of(Material.BARRIER, MenuText.BACK), ImmutableMap.of(ClickType.LEFT, p -> new ArmorGUI(mcdndPlayer, index, p)));
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                ArmorType armorType = ArmorType.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpigotIconBuilder.builder(ItemRepresentation.armorType(armorType)).name((armorType == armor.getArmorType() ? ChatColor.GREEN : ChatColor.RED) + armorType.getName()).build();
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> {
                    armor.setArmorType(armorType);
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

        int maxPage = Double.valueOf(Math.ceil(ArmorType.values().length / 45D)).intValue();
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
