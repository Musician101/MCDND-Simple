package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerAction;
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

public class NonPlayerActionGUI extends SpongeMCDNDSimpleGUI {

    @Nonnull
    private final NonPlayerAction nonPlayerAction;

    public NonPlayerActionGUI(@Nonnull NonPlayer nonPlayer, int index, @Nonnull Player player) {
        super(player, nonPlayer.getNonPlayerActions().getActions().get(index).getName(), 9);
        nonPlayerAction = nonPlayer.getNonPlayerActions().getActions().get(index);
        setButton(0, SpongeIconBuilder.of(ItemTypes.PAPER, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerAction.setName(s);
                new NonPlayerActionGUI(nonPlayer, index, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.IRON_SWORD, Text.of(MenuText.actionType(nonPlayerAction.getActionType()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerActionTypeGUI(nonPlayer, index, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.DESCRIPTION)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, nonPlayerAction.getName(), nonPlayerAction.getDescription()), (ply, pages) -> {
            nonPlayerAction.setDescription(textListToStringList(pages));
            new NonPlayerActionGUI(nonPlayer, index, ply);
        })));
        setButton(3, SpongeIconBuilder.of(ItemTypes.BOOK, Text.of(MenuText.EFFECT)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeBookGUI(SpongeMCDNDSimple.instance(), p, createBook(p, nonPlayerAction.getName(), nonPlayerAction.getEffect()), (ply, pages) -> {
            nonPlayerAction.setEffect(textListToStringList(pages));
            new NonPlayerActionGUI(nonPlayer, index, ply);
        })));
        updateIsMultiAttack();
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerActionsListGUI(nonPlayer, p)));
    }

    private void updateIsMultiAttack() {
        setButton(4, SpongeIconBuilder.builder(ItemTypes.GOLDEN_SWORD).name(Text.of((nonPlayerAction.isMultiAttack() ? TextColors.GREEN : TextColors.RED), MenuText.EFFECT)).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> {
            nonPlayerAction.setIsMultiAttack(!nonPlayerAction.isMultiAttack());
            updateIsMultiAttack();
        }));
    }
}
