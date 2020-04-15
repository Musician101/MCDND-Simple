package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassAction;
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
import org.spongepowered.api.text.format.TextColors;

public class ClassActionGUI extends SpongeMCDNDSimpleGUI {

    public ClassActionGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index).getName(), 9);
        ClassAction classAction = mcdndPlayer.getCharacterSheet().getClassTab().getClassActions().get(index);
        setButton(0, SpongeIconBuilder.of(ItemTypes.NAME_TAG, Text.of(MenuText.RENAME)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                classAction.setName(s);
                new ClassActionGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.REDSTONE_TORCH, Text.of(MenuText.used(classAction.getUsed()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                classAction.setUsed(i);
                new ClassActionGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(2, SpongeIconBuilder.of(ItemTypes.TORCH, Text.of(MenuText.maxUses(classAction.getMaxUses()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new RechargeGUI<>(mcdndPlayer, RechargeGUI.CLASS_ACTIONS, index, p)));
        setButton(3, SpongeIconBuilder.of(ItemTypes.BED, Text.of(MenuText.recharge(classAction.getRecharge()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                classAction.setUsed(i);
                new ClassActionGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(4, SpongeIconBuilder.of(ItemTypes.DIAMOND_SWORD, Text.of(MenuText.GAINED_FROM)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new GainedFromGUI(mcdndPlayer, index, p)));
        setButton(5, SpongeIconBuilder.of(ItemTypes.REDSTONE, Text.of(MenuText.OUTPUT_OPTIONS)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new OutputOptionsGUI(mcdndPlayer, index, p)));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassActionsGUI(mcdndPlayer, p)));
    }
}
