package io.musician101.mcdndsimple.sponge.gui.nonplayer;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayer;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerActions;
import io.musician101.mcdndsimple.common.reference.MenuText;
import io.musician101.mcdndsimple.sponge.SpongeMCDNDSimple;
import io.musician101.mcdndsimple.sponge.gui.SpongeMCDNDSimpleGUI;
import io.musician101.musicianlibrary.java.minecraft.sponge.SpongeTextInput;
import io.musician101.musicianlibrary.java.minecraft.sponge.gui.SpongeIconBuilder;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

public class NonPlayerActionsGUI extends SpongeMCDNDSimpleGUI {

    public NonPlayerActionsGUI(@Nonnull NonPlayer nonPlayer, @Nonnull Player player) {
        super(player, MenuText.ACTIONS, 9);
        NonPlayerActions nonPlayerActions = nonPlayer.getNonPlayerActions();
        setButton(0, SpongeIconBuilder.builder(ItemTypes.IRON_SWORD).name(Text.of(MenuText.MULTI_ATTACK)).description(Text.of(nonPlayerActions.getMultiAttack())).build(), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                nonPlayerActions.setMultiAttack(s);
                new NonPlayerActionsGUI(nonPlayer, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.GOLDEN_SWORD, Text.of(MenuText.ACTIONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerActionsListGUI(nonPlayer, p)));
        setButton(8, BACK_ICON, ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new NonPlayerGUI(nonPlayer, p)));
    }
}
