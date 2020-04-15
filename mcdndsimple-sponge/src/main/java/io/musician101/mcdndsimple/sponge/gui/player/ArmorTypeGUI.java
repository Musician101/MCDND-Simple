package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.Armor;
import io.musician101.mcdndsimple.common.character.player.equipment.armor.ArmorType;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.mcdndsimple.sponge.util.ItemRepresentation;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import java.util.stream.IntStream;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ArmorTypeGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final Armor armor;
    private int page = 1;

    public ArmorTypeGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, MenuText.ARMOR_TYPE, 54);
        this.armor = mcdndPlayer.getCharacterSheet().getArmorTab().getArmorList().get(index);
        updateSlots();
        setButton(49, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ArmorGUI(mcdndPlayer, index, p)));
    }

    private void updateSlots() {
        IntStream.of(0, 45).forEach(x -> {
            try {
                ArmorType armorType = ArmorType.values()[x + (page - 1) * 45];
                ItemStack itemStack = SpongeIconBuilder.builder(ItemRepresentation.armorType(armorType)).name(Text.of(armorType == armor.getArmorType() ? TextColors.GREEN : TextColors.RED, armorType.getName())).build();
                setButton(x, itemStack, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
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
            setButton(45, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.PREVIOUS_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page--;
                updateSlots();
            }));
        }

        int maxPage = Double.valueOf(Math.ceil(ArmorType.values().length / 45D)).intValue();
        if (page < maxPage) {
            removeButton(53);
        }
        else {
            setButton(53, SpongeIconBuilder.of(ItemTypes.ARROW, Text.of(MenuText.NEXT_PAGE)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
                page++;
                updateSlots();
            }));
        }
    }
}
