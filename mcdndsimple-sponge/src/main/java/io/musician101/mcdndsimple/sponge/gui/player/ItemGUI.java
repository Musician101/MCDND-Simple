package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDItem;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeBookGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class ItemGUI extends SpongeMCDNDSimpleGUI {

    private final int index;
    @Nonnull
    private final MCDNDPlayer mcdndPlayer;

    public ItemGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory().get(index).getName(), 9);
        this.mcdndPlayer = mcdndPlayer;
        MCDNDItem item = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory().get(index);
        this.index = index;
        setButton(0, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                item.setName(s);
                new ItemGUI(mcdndPlayer, index, player);
            }
        }));
        updateIsCarried();
        setButton(2, SpongeIconBuilder.of(ItemTypes.CHEST, Text.of(MenuText.quantity(item))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                int i;
                try {
                    i = Integer.parseInt(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                item.setQuantity(i);
                new ItemGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.OBSIDIAN, Text.of(MenuText.weight(item))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            protected void process(Player player, String s) {
                double d;
                try {
                    d = Double.parseDouble(s);
                }
                catch (NumberFormatException e) {
                    player.sendMessage(Text.of(TextColors.RED + "That is not a number."));
                    return;
                }

                item.setWeight(d);
                new ItemGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(4, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.DESCRIPTION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, item.getName(), item.getDescription()), (ply, pages) -> {
            item.setDescription(textListToStringList(pages));
            new ItemGUI(mcdndPlayer, index, ply);
        })));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new InventoryGUI(mcdndPlayer, p)));
    }

    private void updateIsCarried() {
        MCDNDItem item = mcdndPlayer.getCharacterSheet().getInventoryTab().getInventory().get(index);
        setButton(1, SpongeIconBuilder.of(ItemTypes.TORCH, Text.of(item.isCarried() ? TextColors.GREEN : TextColors.RED, MenuText.carried(item))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            item.setIsCarried(!item.isCarried());
            updateIsCarried();
        }));
    }
}
