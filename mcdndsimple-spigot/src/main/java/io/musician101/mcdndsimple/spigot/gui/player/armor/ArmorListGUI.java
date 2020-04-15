package io.musician101.mcdndsimple.spigot.gui.player.armor;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.spigot.SpigotMCDNDSimple;
import io.musician101.mcdndsimple.spigot.gui.SpigotMCDNDSimpleGUI;
import io.musician101.mcdndsimple.spigot.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.spigot.SpigotTextInput;
import io.musician101.musicianlibrary.java.minecraft.spigot.gui.SpigotIconBuilder;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ArmorListGUI extends SpigotMCDNDSimpleGUI {

    @Nonnull
    private final MCDNDPlayer mcdndPlayer;
    private int page = 1;

    public ArmorListGUI(@Nonnull MCDNDPlayer mcdndPlayer, @Nonnull Player player) {
        super(player, MenuText.UNARMORED_BONUS, 54);
        this.mcdndPlayer = mcdndPlayer;
        updateSlots();
        setButton(48, SpigotIconBuilder.of(Material.PAPER, MenuText.NEW_ARMOR), ImmutableMap.of(ClickType.LEFT, p -> new SpigotTextInput(SpigotMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                Armor armor = new Armor();
                armor.setName(s);
                List<Armor> armorList = mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList();
                armorList.add(armor);
                new ArmorGUI(mcdndPlayer, armorList.size() - 1, player);
            }
        }));
        setButton(50, BACK_ICON, ImmutableMap.of(ClickType.LEFT, p -> new ArmorTabGUI(mcdndPlayer, p)));
    }

    private void updateSlots() {
        List<Armor> armorList = mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList();
        IntStream.of(0, 45).forEach(x -> {
            try {
                int index = x + (page - 1) * 45;
                Armor armor = armorList.get(index);
                ItemStack itemStack = ItemRepresentation.armor(armor);
                setButton(x, itemStack, ImmutableMap.of(ClickType.LEFT, p -> new ArmorGUI(mcdndPlayer, index, p), ClickType.RIGHT, p -> {
                    armorList.remove(index);
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

        int maxPage = Double.valueOf(Math.ceil(armorList.size() / 45D)).intValue();
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
