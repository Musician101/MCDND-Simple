package io.musician101.mcdndsimple.sponge.gui.player;

import com.google.common.collect.ImmutableMap;
import io.musician101.mcdndsimple.common.character.player.MCDNDPlayer;
import io.musician101.mcdndsimple.common.character.player.clazz.ClassResource;
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

public class ClassResourceGUI extends SpongeMCDNDSimpleGUI {

    public ClassResourceGUI(@Nonnull MCDNDPlayer mcdndPlayer, int index, @Nonnull Player player) {
        super(player, mcdndPlayer.getCharacterSheet().getClassTab().getClassResources().get(index).getName(), 9);
        ClassResource classResource = mcdndPlayer.getCharacterSheet().getClassTab().getClassResources().get(index);
        setButton(0, SpongeIconBuilder.of(ItemTypes.NAME_TAG, Text.of(classResource.getName())), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

            @Override
            public void process(@Nonnull Player player, @Nonnull String s) {
                classResource.setName(s);
                new ClassResourceGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(1, SpongeIconBuilder.of(ItemTypes.BED, Text.of(MenuText.recharge(classResource.getRecharge()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new RechargeGUI<>(mcdndPlayer, RechargeGUI.CLASS_RESOURCES, index, p)));
        setButton(2, SpongeIconBuilder.of(ItemTypes.REPEATER, Text.of(MenuText.current(classResource))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                classResource.setCurrentCharges(i);
                new ClassResourceGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(3, SpongeIconBuilder.of(ItemTypes.COMPARATOR, Text.of(MenuText.maxUses(classResource.getMaxUses()))), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new SpongeTextInput(SpongeMCDNDSimple.instance(), p) {

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

                classResource.setMaxCharges(i);
                new ClassResourceGUI(mcdndPlayer, index, player);
            }
        }));
        setButton(8, SpongeIconBuilder.of(ItemTypes.BARRIER, Text.of(MenuText.BACK)), ImmutableMap.of(ClickInventoryEvent.Primary.class, p -> new ClassResourcesGUI(mcdndPlayer, p)));
    }
}
